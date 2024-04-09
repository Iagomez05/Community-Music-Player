using System.Net;
using System.Net.Sockets;
using System.Text;

namespace CommunityMusicP
{
    internal static class Program
    {
        // Declara clienteSocket como una variable estática para que sea accesible desde otros métodos
        public static Socket clienteSocket;
        private static Clientcnct clienteForm;


        static void Main()
        {
            // Inicializa la configuración de la aplicación
            ApplicationConfiguration.Initialize();
            // Llama al método Socketcliente para establecer la conexión con el servidor
            Console.WriteLine("HOLAAAA MUNDOOOOO");
            clienteForm = new Clientcnct();
            Socketcliente();

            // Crear una instancia del primer formulario y pasar la instancia de Clientcnct
            Application.Run(new Cliente(clienteForm));
        }

        public static void Socketcliente()
        {
            try
            {
                // Establecer la dirección IP y el puerto del servidor
                IPAddress ipAddress = IPAddress.Parse("127.0.0.1");
                int puerto = 10500;

                // Crear el socket TCP/IP
                clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                // Conectar el socket al servidor
                clienteSocket.Connect(ipAddress, puerto);

                Console.WriteLine("Conexión establecida con el servidor.");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
            }
        }

        public static void SendMessageToServer(string message)
        {
            try
            {
                // Enviar datos al servidor
                byte[] mensajeBytes = Encoding.UTF8.GetBytes(message + "\n"); // Agregar un salto de línea al final del mensaje
                Program.clienteSocket.Send(mensajeBytes);

                // Recibir respuesta del servidor
                byte[] buffer = new byte[1024];
                int bytesRecibidos = Program.clienteSocket.Receive(buffer);
                string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);

                // Enviar la respuesta al formulario de cliente
                clienteForm.MostrarRespuesta(respuesta);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
            }
        }
    }
}