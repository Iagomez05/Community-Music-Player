using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using log4net; //Biblioteca para el manejo de logs
using log4net.Config; //Biblioteca para configurar el log
using lectorIni; //Uso de la clase IniReader para leer archivos INI

namespace CommunityMusicP
{
    public partial class Clientcnct : Form
    {
        // Instancia de log para el manejo de logs
        private static readonly ILog log = LogManager.GetLogger(typeof(Clientcnct));
        // Instancia de IniReader para leer archivos INI
        private IniReader lector = new IniReader();
        private IPAddress ipAddress;
        private int puerto;
        public Clientcnct()
        {
            XmlConfigurator.Configure(new FileInfo("log4net.config"));
            InitializeComponent();
            // Leer la configuración del archivo INI
            string rutaArchivo = "data1.ini"; // Asegúrate de proporcionar la ruta correcta
            ipAddress = IPAddress.Parse(lector.LeerConfiguracion(rutaArchivo, "Sockets", "IP"));
            puerto = int.Parse(lector.LeerConfiguracion(rutaArchivo, "Sockets", "Puerto"));
        }

        private void Clientcnct_Load(object sender, EventArgs e)
        {

        }

        private void btnsalir_Click(object sender, EventArgs e)
        {
            this.Close();
            log.Info("Info:: Cerrando la aplicación"); // Log del error, que se guarda en el archivo de logs
        }

        private void btnVoteup_Click(object sender, EventArgs e)
        {
            SendMessageToServer("voteUp");
        }

        private void btnVotedwn_Click(object sender, EventArgs e)
        {
            SendMessageToServer("voteDown");
        }


        private void SendMessageToServer(string message)
        {
            try
            {
                // Llama al método Socketcliente para establecer la conexión con el servidor
                Program.Socketcliente();

                // Enviar datos al servidor
                byte[] mensajeBytes = Encoding.UTF8.GetBytes(message + "\n"); // Agregar un salto de línea al final del mensaje
                Program.clienteSocket.Send(mensajeBytes);

                // Recibir respuesta del servidor
                int BYTE = int.Parse(lector.LeerConfiguracion("data1.ini", "Sockets", "Byte"));
                byte[] buffer = new byte[BYTE];
                int bytesRecibidos = Program.clienteSocket.Receive(buffer);
                string respuesta = Encoding.UTF8.GetString(buffer, 0, bytesRecibidos);
                Console.WriteLine("Respuesta del servidor: " + respuesta);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
                log.Error("Error: " + ex.Message);
            }
        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        
    }
}
