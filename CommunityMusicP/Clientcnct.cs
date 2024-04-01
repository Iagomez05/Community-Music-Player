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
            // Parsear la respuesta del servidor y dividirla en partes (canción, artista, álbum, etc.)
            string[] partesRespuesta = respuesta.Split(',');

            // Crear un nuevo ListViewItem para mostrar la respuesta en el ListView
            ListViewItem item = new ListViewItem(partesRespuesta[0]); // Primera parte: canción

            // Añadir las partes restantes como subítems
            for (int i = 1; i < partesRespuesta.Length; i++)
            {
                item.SubItems.Add(partesRespuesta[i]);
            }

            // Añadir el item al ListView
            this.listView.Items.Add(item);
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
            Program.SendMessageToServer("VoteUp");
        }

        private void btnVotedwn_Click(object sender, EventArgs e)
        {
            Program.SendMessageToServer("VoteDown");
        }


        private void listView_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

    }
}