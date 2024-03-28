using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using lectorIni;
using log4net;
using log4net.Config;

namespace Cliente
{
    class Program
    {
        private static IniReader lector = new IniReader();
        private static readonly ILog log = LogManager.GetLogger(typeof(Program));
        static void Socketcliente(string[] args)
        {
            XmlConfigurator.Configure(new FileInfo("log4net.config"));
            try
            {
                XmlConfigurator.Configure(new FileInfo("log4net.config"));
                // Establecer la dirección IP y el puerto del servidor
                string ipString = lector.LeerConfiguracion("data1.ini", "Sockets", "IP");
                IPAddress ipAddress = IPAddress.Parse(ipString);
                int value = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Puerto1"));
                int puerto = value;

                // Crear el socket TCP/IP
                Socket clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                // Conectar el socket al servidor
                clienteSocket.Connect(ipAddress, puerto);

                Console.WriteLine("Conexión establecida con el servidor.");
                log.Info("Conexion establecida con el servidor.");

                while (true)
                {
                    // Enviar datos al servidor
                    Console.Write("Ingrese el comando: ");
                    string comando = Console.ReadLine() + "\n"; // Agregar un salto de línea al final del comando
                    byte[] comandoBytes = Encoding.UTF8.GetBytes(comando);
                    clienteSocket.Send(comandoBytes);

                    // Si el comando es 'Get playlist', 'Vote up' o 'Vote down', esperar 'ok' del servidor
                    if (comando.Trim().Equals("Get playlist") || comando.Trim().Equals("Vote up") || comando.Trim().Equals("Vote down"))
                    {
                        int BYTE = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Byte"));
                        byte[] buffer = new byte[BYTE];
                        int bytesRecibidos = clienteSocket.Receive(buffer);
                        string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);
                        Console.WriteLine("Respuesta del servidor: " + respuesta);
                        log.Info("Respuesta del servidor: " + respuesta);
                    }
                    else // En caso contrario, esperar 'ERROR' del servidor
                    {
                        byte[] buffer = new byte[1024];
                        int bytesRecibidos = clienteSocket.Receive(buffer);
                        string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);
                        Console.WriteLine("Respuesta del servidor: " + respuesta);
                        log.Info("Respuesta del servidor: " + respuesta);
                    }
                }

                // Cerrar el socket
                clienteSocket.Shutdown(SocketShutdown.Both);
                clienteSocket.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
                log.Error("Error: " + ex.ToString());
            }
        }
    }
}
