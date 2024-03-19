using lectorIni;
using log4net;
using log4net.Config;
using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Cliente
{
    class Program
    {
        private static IniReader lector = new IniReader();
        private static readonly ILog log = LogManager.GetLogger(typeof(Program));

        static void Main(string[] args)
        {
            XmlConfigurator.Configure(new FileInfo("log4net.config"));

            try
            {
                // Establecer la dirección IP y el puerto del servidor
                string ipString = lector.LeerConfiguracion("data1.ini", "Sockets", "IP");
                IPAddress ipAddress = IPAddress.Parse(ipString);
                int value = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Puerto"));
                int puerto = value;
                Console.WriteLine("Datos leidos");

                // Crear el socket TCP/IP
                Socket clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                // Conectar el socket al servidor de los datos obtenidos del archivo de configuración ini
                clienteSocket.Connect(ipAddress, puerto);

                Console.WriteLine("Conexión establecida con el servidor.");
                log.Info("Conexión establecida con el servidor.");

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
                    int BYTE = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Byte"));
                    byte[] buffer = new byte[BYTE];
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
                Console.WriteLine("Error: " + ex.Message);
                log.Error("Error: " + ex.Message);
                
            }
        }
    }
}