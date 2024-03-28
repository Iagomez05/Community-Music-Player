package org.example.pruebafx;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final Logger LOG = Log.getLogger(Servidor.class);

    public static void main(String[] args) {
        INI iniReader = new INI("data.ini");
        final int puerto1 = iniReader.getIntValue("puerto1");
        System.out.println(puerto1);


        try {
            // Se crea un ServerSocket que estará a la escucha en el puerto especificado
            ServerSocket servidor = new ServerSocket(puerto1);
            System.out.println("Servidor iniciado en el puerto " + puerto1);
            LOG.info("Info:: Servidor iniciado en el puerto " + puerto1);

            // Se acepta la conexión entrante de un cliente, el programa se bloquea hasta que se recibe una conexión
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            LOG.info("Info:: Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            // Se preparan los objetos de entrada y salida para la comunicación con el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);

            // Se lee el mensaje enviado por el cliente
            String mensajeCliente = entrada.readLine();
            System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

            // Se procesa el mensaje recibido (en este caso, simplemente se envía de vuelta al cliente)
            salida.println("Mensaje recibido: " + mensajeCliente);

            // Se cierra la conexión con el cliente y el servidor
            cliente.close();
            servidor.close();
        } catch (IOException e) {
            // En caso de algún error de entrada/salida, se imprime el mensaje de error
            System.err.println("Error de entrada/salida: " + e.getMessage());
            LOG.error("Error:: entrada/salida"+ e.getMessage());
        }
    }
}
