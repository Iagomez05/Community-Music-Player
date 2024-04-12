package org.example.pruebafx;

import org.apache.log4j.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.parser.ParseException;

public class Servidor {
    private static final Logger LOG = Log.getLogger(Servidor.class);
    private final ControllerClass controller;
    private static ArrayList<String> idList = new ArrayList<>();
    private static ArrayList<String> miau = new ArrayList<>();
    private static ArrayList<String> miau2 = new ArrayList<>();

    public Servidor(ControllerClass controller) {
        this.controller = controller;
    }
    
    public void iniciarServidor(int puerto) {
        new Thread(() -> {
            ServerSocket servidor = null;
            LinkedList listaAleatoria = controller.getListaAleatoria();
            int numberr = 0;
            int numbersong = controller.getNumbersong();
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
                                JSONArray playlist = leerPlaylistDesdeArchivo("info.json");
                                // Envía la lista de reproducción al cliente
                                salida.println(playlist.toString());
                                for (int i = 0; i < listaAleatoria.size(); i++) {
                                    SongData song = listaAleatoria.get(i);
                                    System.out.println("ID: " + song.getId() + ", Titulo: " + song.getTitle() + ", Artista: " + song.getArtist());
                                }

                                break;


                            case "Update":
                                String updateList = controller.UpdateList();
                                salida.println(updateList);
                                break;

                            case "Vote Up":
                                System.out.println("ok,like");
                                salida.println("ok, like");
                                String id_like = jsonMensaje.get("id").getAsString();
                                // Obtiene la cadena sin el primer carácter
                                id_like = id_like.substring(1);


                                miau.add(id_like);
                                System.out.println(miau.get(0));
                                String firstID = miau.get(0);
                                int ContadorLikesCancion = 0;
                                while (true){
                                    String ID = listaAleatoria.get(ContadorLikesCancion).getId();
                                    if (ID.equals(firstID)){
                                        listaAleatoria.get(ContadorLikesCancion).addLike();
                                        System.out.println("se dio like a la canción" );
                                        miau.clear();
                                        break;
                                    }
                                    ContadorLikesCancion++;
                                }
                                for (int i = 0; i < listaAleatoria.size(); i++) {
                                    SongData song = listaAleatoria.get(i);
                                    System.out.println("ID: " + song.getId() + ", Likes: " + song.getLikes());
                                }
                                //controller.songList.addLike(id_like);
                                break;

                            case "Vote Down":
                                System.out.println("ok,dislike");
                                salida.println("ok, dislike");
                                String id_dislike = "";
                                id_dislike = jsonMensaje.get("id").getAsString();
                                // Obtiene la cadena sin el primer carácter
                                id_dislike = id_dislike.substring(1);
                                System.out.println(116);
                                miau2.add(id_dislike);
                                System.out.println(miau2.get(0));
                                if (!listaAleatoria.isEmpty() && !miau2.isEmpty() && numbersong < listaAleatoria.size()) {
                                    String firstIDD = miau2.get(0);
                                    int ContadorLikesCancionn = 0;
                                    System.out.println(121);
                                    while (true) {
                                        String ID = listaAleatoria.get(ContadorLikesCancionn).getId();
                                        if (ID.equals(firstIDD)) {
                                            listaAleatoria.get(ContadorLikesCancionn).addDislike();
                                            System.out.println("se dio like a la canción");
                                            miau2.clear();
                                            break;
                                        }
                                        ContadorLikesCancionn++;
                                    }
                                } else {
                                    // Manejo de casos en que las listas están vacías o numbersong está fuera de rango
                                    System.out.print("La lista está vacía");
                                }
                                for (int i = 0; i < listaAleatoria.size(); i++) {
                                    SongData song = listaAleatoria.get(i);
                                    System.out.println("ID: " + song.getId() + ", Likes: " + song.getLikes());
                                }
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

    private static void leer_GuardarArchivo(String rutaJSON) {
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(rutaJSON));

            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;

                // Access individual elements
                String command = (String) jsonObject.get("command");
                String id = (String) jsonObject.get("id");

                idList.add(id); // Adding id value to the ArrayList
            }
            else {
                System.err.println("Unexpected JSON structure: neither object nor array found.");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        for (String id : idList) {
            System.out.println(id);
        }
    }


}