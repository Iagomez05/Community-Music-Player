using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using log4net; //Biblioteca para el manejo de logs
using log4net.Config; //Biblioteca para configurar el log
//using Capa_Acceso_Datos.Txt;//Uso de la clase IniReader para leer archivos INI

namespace CommunityMusicP
{
    public partial class Clientcnct : Form
    {
        // Instancia de log para el manejo de logs
        private static readonly ILog log = LogManager.GetLogger(typeof(Clientcnct));
        public Clientcnct()
        {
            XmlConfigurator.Configure(new FileInfo("log4net.config"));
            InitializeComponent();
        }

        public void MostrarGetPlaylist(string respuesta)
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

        public void UpdatePlaylist(string respuesta)
        {
            listView.Items.Clear();
            btnVoteup.Enabled = false;
            btnVotedwn.Enabled = false;
            selectionLabel.Text = "No track selected for voting";
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
            bool connected = Program.IsConnected;
            connectionStatus.Text = connected ? "●  SESSION ONLINE" : "●  PREVIEW MODE";
            connectionStatus.ForeColor = connected
                ? Color.FromArgb(61, 220, 151)
                : Color.FromArgb(156, 135, 255);
            ResizePlaylistColumns();
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
                string iddecancion = this.listView.SelectedItems[0].SubItems[4].Text;

                // Formatear el mensaje en formato JSON
                string jsonMessageU = $"{{\"command\": \"Vote Up\", \"id\": \"{iddecancion}\"}}";

                // Enviar el mensaje formateado al servidor
                Program.SendMessageToServer(jsonMessageU);

            }
            else
            {
                Console.WriteLine("no se ha seleccionado ninguna cancion");

            }
            string jsonMessage = $"{{\"command\": \"Update\"}}";
            Program.SendMessageToServer(jsonMessage);
            log.Info("Info:: Se ha votado por una canción");
        }

        private void btnVotedwn_Click(object sender, EventArgs e)
        {
            if (this.listView.SelectedItems.Count > 0)
            {
                // Obtener el ID de la canción del cuarto subítem del elemento seleccionado
                string iddecancion = this.listView.SelectedItems[0].SubItems[4].Text;

                // Formatear el mensaje en formato JSON
                string jsonMessageD = $"{{\"command\": \"Vote Down\", \"id\": \"{iddecancion}\"}}";
                log.Info("Info:: Se ha votado por una canción");

                // Enviar el mensaje formateado al servidor
                Program.SendMessageToServer(jsonMessageD);
            }
            else
            {

                Console.WriteLine("no se ha seleccionado ninguna cancion");
            }
            string jsonMessage = $"{{\"command\": \"Update\"}}";
            Program.SendMessageToServer(jsonMessage);
            log.Info("Info:: Se ha votado por una canción");
        }



        private void listView_SelectedIndexChanged(object sender, EventArgs e)
        {
            bool hasSelection = listView.SelectedItems.Count > 0;
            btnVoteup.Enabled = hasSelection;
            btnVotedwn.Enabled = hasSelection;
            selectionLabel.Text = hasSelection
                ? $"Voting on: {listView.SelectedItems[0].Text}"
                : "No track selected for voting";
        }

        private void listView_Resize(object sender, EventArgs e)
        {
            ResizePlaylistColumns();
        }

        private void ResizePlaylistColumns()
        {
            int availableWidth = Math.Max(600, listView.ClientSize.Width - 2);
            Cancion.Width = (int)(availableWidth * 0.38);
            Artista.Width = (int)(availableWidth * 0.29);
            Votesup.Width = (int)(availableWidth * 0.15);
            Votesdown.Width = availableWidth - Cancion.Width - Artista.Width - Votesup.Width;
            id.Width = 0;
        }

        private void listView_DrawColumnHeader(object sender, DrawListViewColumnHeaderEventArgs e)
        {
            using SolidBrush background = new SolidBrush(Color.FromArgb(24, 33, 43));
            using SolidBrush foreground = new SolidBrush(Color.FromArgb(157, 168, 182));
            using Font headerFont = new Font("Segoe UI Semibold", 9F);
            e.Graphics.FillRectangle(background, e.Bounds);
            TextRenderer.DrawText(
                e.Graphics,
                e.Header?.Text ?? string.Empty,
                headerFont,
                new Rectangle(e.Bounds.X + 12, e.Bounds.Y, e.Bounds.Width - 12, e.Bounds.Height),
                foreground.Color,
                TextFormatFlags.Left | TextFormatFlags.VerticalCenter
            );
        }

        private void listView_DrawItem(object sender, DrawListViewItemEventArgs e)
        {
            if (listView.View != View.Details)
            {
                e.DrawDefault = true;
            }
        }

        private void listView_DrawSubItem(object sender, DrawListViewSubItemEventArgs e)
        {
            ListViewItem? item = e.Item;
            if (item is null)
            {
                return;
            }

            bool selected = item.Selected;
            Color backgroundColor = selected
                ? Color.FromArgb(43, 37, 80)
                : (item.Index % 2 == 0 ? Color.FromArgb(15, 21, 28) : Color.FromArgb(17, 24, 32));
            Color textColor = selected ? Color.White : Color.FromArgb(203, 211, 221);

            using SolidBrush background = new SolidBrush(backgroundColor);
            e.Graphics.FillRectangle(background, e.Bounds);
            TextRenderer.DrawText(
                e.Graphics,
                e.SubItem?.Text ?? string.Empty,
                listView.Font,
                new Rectangle(e.Bounds.X + 12, e.Bounds.Y, e.Bounds.Width - 12, e.Bounds.Height),
                textColor,
                TextFormatFlags.Left | TextFormatFlags.VerticalCenter | TextFormatFlags.EndEllipsis
            );
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string jsonMessage = $"{{\"command\": \"FIN\"}}";
            Program.SendMessageToServer(jsonMessage);
            this.Close();
        }
    }
}
