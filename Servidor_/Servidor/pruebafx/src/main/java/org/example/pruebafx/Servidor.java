package org.example.pruebafx;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final Logger LOG = Log.getLogger(Servidor.class);

    INI iniReader = new INI("data.ini");
    final int valor = iniReader.getIntValue("puerto");

    private volatile boolean running = true;

    public void iniciarServidor() {
        new Thread(() -> {
            try {
                ServerSocket servidor = new ServerSocket(valor);
                System.out.println("Servidor iniciado en el puerto " + valor);
                LOG.info("Servidor iniciado en el puerto " + valor);

                while (running) {
                    Socket cliente = servidor.accept();
                    System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                    LOG.info("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

                    String mensajeCliente;
                    while ((mensajeCliente = entrada.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                        switch (mensajeCliente) {
                            case "Get playlist":
                                salida.println("ok, generando playlist");
                                //songList.generateRandomList();
                                break;
                            case "Vote up":
                                salida.println("ok, like");
                                // Lógica para incrementar el contador de "likes"
                                break;
                            case "Vote down":
                                salida.println("ok, dislike");
                                // Lógica para incrementar el contador de "dislikes"
                                break;
                            case "FIN":
                                salida.println("Cerrando conexión...");
                                LOG.info("Info:: Conexion cerrada");
                                detenerServidor(); // Llama al método para detener el servidor
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
                servidor.close(); // Cerrar el ServerSocket cuando se detiene el servidor
            } catch (IOException e) {
                System.err.println("Error de entrada/salida: " + e.getMessage());
                LOG.error("Error de entrada/salida: " + e.getMessage());
            }
        }).start();
    }

    public void detenerServidor() {
        running = false;
    }
}