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

        public void MostrarRespuesta(string respuesta)
        {
            // Dividir la respuesta en líneas
            string[] lineasRespuesta = respuesta.Split(new[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);

            foreach (string linea in lineasRespuesta)
            {
                // Parsear la línea en partes (canción, artista, álbum, etc.)
                string[] partesRespuesta = linea.Split(',');

                // Crear un nuevo ListViewItem para mostrar la línea en el ListView
                ListViewItem item = new ListViewItem(partesRespuesta[0]); // Primera parte: canción

                // Añadir las partes restantes como subítems
                for (int i = 1; i < partesRespuesta.Length; i++)
                {
                    item.SubItems.Add(partesRespuesta[i]);
                }

                // Añadir el item al ListView
                this.listView.Items.Add(item);
            }
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
            SendVoteToServer("VoteUp");
        }

        private void btnVotedwn_Click(object sender, EventArgs e)
        {
            SendVoteToServer("VoteDown");
        }


        private void listView_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        public static void SendVoteToServer(string message)
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
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.ToString());
            }
        }

    }
}