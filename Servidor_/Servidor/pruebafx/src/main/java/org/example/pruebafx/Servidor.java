package org.example.pruebafx;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final Logger LOG = Log.getLogger(Servidor.class);
    private final ControllerClass controller;

    public Servidor(ControllerClass controller) {
        this.controller = controller;
    }

    public void iniciarServidor(int puerto) {
        Thread serverThread = new Thread(() -> acceptClients(puerto), "music-server");
        serverThread.setDaemon(true);
        serverThread.start();
    }

    private void acceptClients(int puerto) {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            LOG.info("Server started on port {}", puerto);

            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                LOG.info("Client connected: {}", client.getInetAddress().getHostAddress());

                Thread clientThread = new Thread(
                        () -> handleClient(client),
                        "music-client-" + client.getPort()
                );
                clientThread.setDaemon(true);
                clientThread.start();
            }
        } catch (IOException e) {
            LOG.error("The TCP server stopped unexpectedly", e);
        }
    }

    private void handleClient(Socket client) {
        LinkedList playlist = controller.getListaAleatoria();

        try (client;
             BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true)) {

            String message;
            while ((message = input.readLine()) != null) {
                JsonObject request = new Gson().fromJson(message, JsonObject.class);
                String command = request.get("command").getAsString();

                switch (command) {
                    case "GetPlaylist" -> output.println(readPlaylist("info.json"));
                    case "Update" -> {
                        synchronized (playlist) {
                            output.println(controller.UpdateList());
                        }
                    }
                    case "Vote Up" -> applyVote(request, playlist, output, true);
                    case "Vote Down" -> applyVote(request, playlist, output, false);
                    case "FIN" -> {
                        output.println("Closing connection");
                        return;
                    }
                    default -> output.println("ERROR: unknown command");
                }
            }
        } catch (Exception e) {
            LOG.error("Error while processing a client connection", e);
        } finally {
            LOG.info("Client connection closed");
        }
    }

    private void applyVote(JsonObject request, LinkedList playlist, PrintWriter output, boolean upvote) {
        if (!request.has("id")) {
            output.println("ERROR: missing song id");
            return;
        }

        String id = request.get("id").getAsString();
        if (id.startsWith("#")) {
            id = id.substring(1);
        }

        synchronized (playlist) {
            SongData song = playlist.findByID(id);
            if (song == null) {
                output.println("ERROR: unknown song id");
                return;
            }

            if (upvote) {
                song.addLike();
            } else {
                song.addDislike();
            }
        }

        output.println(upvote ? "OK: vote up" : "OK: vote down");
    }

    private JSONArray readPlaylist(String path) {
        try (FileReader reader = new FileReader(path)) {
            return (JSONArray) new JSONParser().parse(reader);
        } catch (Exception e) {
            LOG.error("Could not read playlist file {}", path, e);
            return new JSONArray();
        }
    }
}
