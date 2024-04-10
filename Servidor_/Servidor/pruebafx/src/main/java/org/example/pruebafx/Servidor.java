package org.example.pruebafx;

import org.apache.log4j.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Servidor {
    private static final Logger LOG = Log.getLogger(Servidor.class);
    private final ControllerClass controller;

    public Servidor(ControllerClass controller) {
        this.controller = controller;
    }
    
    public void iniciarServidor(int puerto) {
        new Thread(() -> {
            ServerSocket servidor = null;
            try {
                servidor = new ServerSocket(puerto);
                System.out.println("Servidor iniciado en el puerto " + puerto);
                LOG.info("Servidor iniciado en el puerto " + puerto);

                while (true) {
                    Socket cliente = servidor.accept();
                    System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                    LOG.info("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

                    String mensajeCliente;
                    while ((mensajeCliente = entrada.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                        // Parsear el mensaje JSON
                        JsonObject jsonMensaje = new Gson().fromJson(mensajeCliente, JsonObject.class);
                        String comando = jsonMensaje.get("command").getAsString();

                        switch (comando) {
                            case "GetPlaylist":
                                // Lee el archivo JSON y obtén la información de la lista de reproducción
                                JSONArray playlist = leerPlaylistDesdeArchivo("C:\\datos1\\Community-Music-Player\\info.json");
                                // Envía la lista de reproducción al cliente
                                salida.println(playlist.toString());
                                break;


                            case "Update":
                                String updateList = controller.UpdateList();
                                salida.println(updateList);
                                break;

                            case "Vote Up":
                                System.out.println("ok,like");
                                salida.println("ok, like");
                                String id_like = jsonMensaje.get("id").getAsString();
                                //controller.songList.addLike(id_like);
                                break;

                            case "Vote Down":
                                System.out.println("ok, dislike");
                                salida.println("ok, dislike");
                                String id_dislike = jsonMensaje.get("id").getAsString();
                                //controller.songList.addDislike(id_dislike);
                                break;

                            case "FIN":
                                salida.println("Cerrando conexión...");
                                LOG.info("Info:: Conexion cerrada");
                                break;

                            default:
                                salida.println("ERROR");
                                LOG.error("Error:: Comando erroneo");
                                break;
                        }

                        if (comando.equals("FIN")) {
                            LOG.info("Info:: Fin del servidor");
                            break;
                        }
                    }

                    cliente.close();
                    System.out.println("Conexión cerrada con el cliente.");
                    LOG.info("Info:: Conexión cerrada con el cliente.");
                }
            } catch (IOException e) {
                System.err.println("Error de entrada/salida: " + e.getMessage());
                LOG.error("Error de entrada/salida: " + e.getMessage());
            } finally {
                if (servidor != null) {
                    try {
                        servidor.close();
                        System.out.println("Servidor cerrado.");
                        LOG.info("Info:: Servidor cerrado.");
                    } catch (IOException e) {
                        System.err.println("Error al cerrar el servidor: " + e.getMessage());
                        LOG.error("Error al cerrar el servidor: " + e.getMessage());
                    }
                }
            }
        }).start();
    }
    private JSONArray leerPlaylistDesdeArchivo(String rutaArchivo) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaArchivo));
            return (JSONArray) obj;
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return new JSONArray(); // Devuelve una lista vacía en caso de error
        }
    }


}