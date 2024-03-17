import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        LeerIni iniReader = new LeerIni("data.ini");
        int valor = iniReader.getIntValue("puerto");
        final int puerto = valor;
        
        try {
            // Se crea un ServerSocket que estará a la escucha en el puerto especificado
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            
            while (true) {
                // Se acepta la conexión entrante de un cliente, el programa se bloquea hasta que se recibe una conexión
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                
                // Se preparan los objetos de entrada y salida para la comunicación con el cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true);
                
                // Se espera a recibir mensajes del cliente hasta que se reciba el comando para cerrar la conexión
                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    System.out.println("Mensaje recibido del cliente: " + mensajeCliente);
                    
                    // Verificar si el mensaje es el comando para cerrar la conexión
                    if (mensajeCliente.equals("FIN")) {
                        break;  // Salir del bucle si se recibe el comando FIN
                    }
                    
                    // Procesar el mensaje recibido (en este caso, simplemente se envía de vuelta al cliente)
                    salida.println("Mensaje recibido: " + mensajeCliente);
                }
                
                // Cerrar la conexión con el cliente
                cliente.close();
                System.out.println("Conexión cerrada con el cliente.");
            }
        } catch (IOException e) {
            // En caso de algún error de entrada/salida, se imprime el mensaje de error
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
