using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Cliente
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                // Establecer la dirección IP y el puerto del servidor
                IPAddress ipAddress = IPAddress.Parse("127.0.0.1");
                int puerto = 7500;

                // Crear el socket TCP/IP
                Socket clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                // Conectar el socket al servidor
                clienteSocket.Connect(ipAddress, puerto);

                Console.WriteLine("Conexión establecida con el servidor.");

                while (true)
                {
                    // Enviar datos al servidor
                    Console.Write("Ingrese el mensaje a enviar ('FIN' para cerrar la conexión): ");
                    string mensaje = Console.ReadLine() + "\n"; // Agregar un salto de línea al final del mensaje
                    byte[] mensajeBytes = Encoding.UTF8.GetBytes(mensaje);
                    clienteSocket.Send(mensajeBytes);

                    // Si el mensaje es 'FIN', salir del bucle
                    if (mensaje.Trim().Equals("FIN"))
                    {
                        break;
                    }

                    // Recibir respuesta del servidor
                    byte[] buffer = new byte[1024];
                    int bytesRecibidos = clienteSocket.Receive(buffer);
                    string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);
                    Console.WriteLine("Respuesta del servidor: " + respuesta);
                }

                // Cerrar el socket
                clienteSocket.Shutdown(SocketShutdown.Both);
                clienteSocket.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
            }
        }
    }
}
