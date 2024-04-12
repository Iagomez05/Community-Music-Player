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
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

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

        public void MostrarGetPlaylist(string respuesta)
        {
            // Deserializar el mensaje JSON en una lista de objetos
            List<PlaylistItem> playlist = JsonConvert.DeserializeObject<List<PlaylistItem>>(respuesta);

            // Iterar sobre la lista de objetos y agregar cada elemento al ListView
            foreach (var item in playlist)
            {
                // Dividir el nombre del artista y el título de la canción
                string[] partesNombre = item.name.Split(',');

                // Verificar si se dividió correctamente
                if (partesNombre.Length >= 2)
                {
                    // Asignar el nombre del artista y el título de la canción
                    item.songTitle = partesNombre[0].Trim();
                    item.artist = partesNombre[1].Trim(); // Trim() elimina los espacios en blanco al principio y al final
                }
                else
                {
                    // En caso de que no se pueda dividir correctamente, asignar todo a la propiedad de la canción
                    item.songTitle = item.name;
                }

                // Agregar el elemento al ListView
                ListViewItem listItem = new ListViewItem(item.songTitle);
                listItem.SubItems.Add(item.artist);
                listItem.SubItems.Add(item.likes.ToString());
                listItem.SubItems.Add(item.dislikes.ToString());
                listItem.SubItems.Add(item.id);
                listView.Items.Add(listItem);
            }
        }

        public void UpdatePlaylist(string respuesta)
        {
            listView.Items.Clear();
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
            if (this.listView.SelectedItems.Count > 0)
            {
                // Obtener el ID de la canción del cuarto subítem del elemento seleccionado
                string iddecancion = this.listView.SelectedItems[0].SubItems[0].Text;
                JObject jsonObject = new JObject();
                jsonObject.Add("command", "Vote up");
                jsonObject.Add("id", iddecancion);
                StringWriter sw = new StringWriter();
                JsonWriter writer = new JsonTextWriter(sw);
                jsonObject.WriteTo(writer);
                string json = sw.ToString();

                Program.SendMessageToServer(json);
            }
            else
            {
                Console.WriteLine("no se ha seleccionado ninguna cancion");

            }
        }

        private void btnVotedwn_Click(object sender, EventArgs e)
        {
            if (this.listView.SelectedItems.Count > 0)
            {
                // Obtener el ID de la canción del cuarto subítem del elemento seleccionado
                string iddecancion = this.listView.SelectedItems[0].SubItems[0].Text;
                JObject jsonObject = new JObject();
                jsonObject.Add("command", "Vote down");
                jsonObject.Add("id", iddecancion);
                StringWriter sw = new StringWriter();
                JsonWriter writer = new JsonTextWriter(sw);
                jsonObject.WriteTo(writer);
                string json = sw.ToString();

                Program.SendMessageToServer(json);
            }
            else
            {

                Console.WriteLine("no se ha seleccionado ninguna cancion");
            }
        }
     


        private void listView_SelectedIndexChanged(object sender, EventArgs e)
        {


        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            JObject jsonObject = new JObject();
            jsonObject.Add("command", "Update");
            StringWriter sw = new StringWriter();
            JsonWriter writer = new JsonTextWriter(sw);
            jsonObject.WriteTo(writer);
            string json = sw.ToString();

            Program.SendMessageToServer(json);
        }
    }
    public class PlaylistItem
    {
        public string name { get; set; }
        public string songTitle { get; set; }
        public string artist { get; set; }
        public int likes { get; set; }
        public int dislikes { get; set; }
        public string id { get; set; }
    }
}