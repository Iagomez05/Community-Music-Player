using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json.Linq;
using log4net;
using log4net.Config;
using lectorIni;

namespace CommunityMusicP
{
    internal static class Program
    {
        private static Socket? clientSocket;
        private static Clientcnct? clienteForm;
        private static readonly ILog log = LogManager.GetLogger(typeof(Program));

        static void Main()
        {
            XmlConfigurator.Configure(new FileInfo("log4net.config"));
            ApplicationConfiguration.Initialize();
            clienteForm = new Clientcnct();
            Socketcliente();
            Application.Run(new Cliente(clienteForm));
        }

        public static void Socketcliente()
        {
            try
            {
                IniReader settings = new IniReader();
                const string settingsPath = "data1.ini";
                string host = settings.LeerConfiguracion(settingsPath, "Sockets", "IP");
                int port = int.Parse(settings.LeerConfiguracion(settingsPath, "Sockets", "Puerto"));

                clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                clientSocket.Connect(host, port);

                Console.WriteLine("Conexión establecida con el servidor.");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
                log.Error("Error: " + ex.ToString());
            }
        }

        public static void SendMessageToServer(string message)
        {
            try
            {
                if (clientSocket is null || clienteForm is null)
                {
                    throw new InvalidOperationException("The client is not connected to the server.");
                }

                clientSocket.Send(Encoding.UTF8.GetBytes(message + "\n"));
                byte[] buffer = new byte[64 * 1024];
                int received = clientSocket.Receive(buffer);
                string respuesta = Encoding.UTF8.GetString(buffer, 0, received);
                dynamic jsonData = JObject.Parse(message);

                // Obtener el valor de la propiedad "command" del mensaje JSON
                string command = jsonData.command;

                // Replantear los casos basados en el valor de la propiedad "command"
                switch (command)
                {
                    case "GetPlaylist":
                        // Enviar la respuesta al formulario de cliente
                        clienteForm.MostrarGetPlaylist(respuesta);
                        SendMessageToServer($"{{\"command\": \"Update\"}}");
                        break;
                    case "Update":
                        clienteForm.UpdatePlaylist(respuesta);
                        break;
                    case "FIN":
                        clientSocket.Close();
                        break;
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
                log.Error("Error: " + ex.ToString());
            }
        }
    }
}
