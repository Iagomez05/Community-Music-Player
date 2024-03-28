using System.Net;
using System.Net.Sockets;
using System.Text;
using lectorIni;
using log4net;
using log4net.Config;

namespace CommunityMusicP
{
    internal static class Program
    {
        private static IniReader lector = new IniReader();
        private static readonly ILog log = LogManager.GetLogger(typeof(Program));
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // Configura el log
            XmlConfigurator.Configure(new FileInfo("log4net.config"));
            // Inicializa la configuraci�n de la aplicaci�n
            ApplicationConfiguration.Initialize();
            // Llama al m�todo Socketcliente para establecer la conexi�n con el servidor
            Console.WriteLine("aqui sirve");
            Socketcliente();
            // Ejecuta la aplicaci�n y muestra el formulario Cliente
            log.Info("Aplicaci�n iniciada");
            Application.Run(new Cliente());


        }
        static void Socketcliente()
        {
            try
            {
                // Establecer la dirección IP y el puerto del servidor
                string ipString = lector.LeerConfiguracion("data1.ini", "Sockets", "IP");
                IPAddress ipAddress = IPAddress.Parse(ipString);
                int value = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Puerto"));
                int puerto = value;

                // Crear el socket TCP/IP
                Socket clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                // Conectar el socket al servidor
                clienteSocket.Connect(ipAddress, puerto);

                Console.WriteLine("Conexi�n establecida con el servidor.");

                // Enviar datos al servidor
                Console.Write("Ingrese el mensaje a enviar: ");
                string mensaje = Console.ReadLine() + "\n"; // Agregar un salto de l�nea al final del mensaje
                byte[] mensajeBytes = Encoding.UTF8.GetBytes(mensaje);
                clienteSocket.Send(mensajeBytes);

                // Recibir respuesta del servidor
                int BYTE = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Byte"));
                byte[] buffer = new byte[BYTE];
                int bytesRecibidos = clienteSocket.Receive(buffer);
                string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);
                Console.WriteLine("Respuesta del servidor: " + respuesta);

                // Cerrar el socket
                clienteSocket.Shutdown(SocketShutdown.Both);
                clienteSocket.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
                log.Error("Error: " + ex.Message);
            }
        }
    }
}