using System.Net;
using System.Net.Sockets;
using System.Text;

namespace CommunityMusicP
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // Inicializa la configuraci�n de la aplicaci�n
            ApplicationConfiguration.Initialize();
            // Llama al m�todo Socketcliente para establecer la conexi�n con el servidor
            Console.WriteLine("aqui sirve");
            Socketcliente();
            // Ejecuta la aplicaci�n y muestra el formulario Cliente
            Application.Run(new Cliente());


        }
        static void Socketcliente()
        {
            try
            {
                // Establecer la direcci�n IP y el puerto del servidor
                IPAddress ipAddress = IPAddress.Parse("127.0.0.1");
                int puerto = 7500;

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
                byte[] buffer = new byte[1024];
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
            }
        }
    }
}