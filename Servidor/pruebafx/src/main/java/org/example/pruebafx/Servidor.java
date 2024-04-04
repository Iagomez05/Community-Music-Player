package org.example.pruebafx;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Servidor {
    private static final Logger LOG = Log.getLogger(Servidor.class);


    public void iniciarServidor (int puerto) {
        ControllerClass controller = new ControllerClass();

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
                        controller.getSongInfo1();

                        switch (mensajeCliente) {
                            case "GetPlaylist":
                                salida.println("cancion1, artista1, likes, dislikes, id  \n cancion2, arist2, likes, dislikes, id2 \n");
                                // Obtener la información de las canciones
                                //controller.getSongInfo1();
                                // Enviar la información al cliente
                                //salida.println(songInfo);
                                break;
                            case "VoteUp":
                                salida.println("ok, like");
                                // Lógica para incrementar el contador de "likes"
                                break;
                            case "VoteDown":
                                salida.println("ok, dislike");
                                // Lógica para incrementar el contador de "dislikes"
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

                        if (mensajeCliente.equals("FIN")) {
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
}
