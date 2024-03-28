package org.example.pruebafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {
    private static final Logger LOG = Log.getLogger(Main.class);
    private final linkedList songList = new linkedList();

    @Override
    public void start(Stage stage) throws IOException {
        INI iniReader = new INI("data.ini");
        String musicPlayer = iniReader.getValue("MusicP");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(musicPlayer));
        Scene scene = new Scene(fxmlLoader.load(), 969, 591);
        stage.setTitle("Music Player");
        stage.setScene(scene);
        stage.show();

        // Especificar la ruta del archivo WAV
        final int valor = iniReader.getIntValue("puerto");

        // Iniciar el servidor en un hilo separado
        new Thread(() -> {


            try {
                // Se crea un ServerSocket que estará a la escucha en el puerto especificado
                ServerSocket servidor = new ServerSocket(valor);
                System.out.println("Servidor iniciado en el puerto " + valor);
                LOG.info("Info:: Servidor iniciado en el puerto " + valor);

                while (true) {
                    // Se acepta la conexión entrante de un cliente, el programa se bloquea hasta que se recibe una conexión
                    Socket cliente = servidor.accept();
                    System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                    LOG.info("Info:: Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                    // Se preparan los objetos de entrada y salida para la comunicación con el cliente
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

                    // Se espera a recibir mensajes del cliente hasta que se reciba el comando para cerrar la conexión
                    String mensajeCliente;
                    while ((mensajeCliente = entrada.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                        // Verificar y responder según el comando recibido
                        switch (mensajeCliente) {
                            case "Get playlist":
                                salida.println("ok, generando playlist");
                                songList.generateRandomList();
                            case "Vote up": //Falta correcion aqui y en la funcion de dislike
                                salida.println("ok, like");
                                songList.addLike(0); //Posiblemente se le pase el id en ves de la posicion
                            case "Vote down": //Falta correcion aqui y en la funcion de dislike
                                salida.println("ok, dislike");
                                songList.addDislike(0); //Posiblemente se le pase el id en ves de la posicion
                            case "FIN":
                                salida.println("Cerrando conexión...");
                                LOG.info("Info:: Conexion cerrada");
                                break;
                            default:
                                salida.println("ERROR");
                                LOG.error("Error:: Commando erroneo");
                                break;
                        }

                        // Salir del bucle si se recibe el comando FIN
                        if (mensajeCliente.equals("FIN")) {
                            break;
                        }
                    }

                    // Cerrar la conexión con el cliente
                    cliente.close();
                    System.out.println("Conexión cerrada con el cliente.");
                    LOG.info("Info:: Conexión cerrada con el cliente.");
                }
            } catch (IOException e) {
                // En caso de algún error de entrada/salida, se imprime el mensaje de error
                System.err.println("Error de entrada/salida: " + e.getMessage());
                LOG.error("Error:: Error de entrada/salida: " + e.getMessage());
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
