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

namespace CommunityMusicP
{
    public partial class Clientcnct : Form
    {
        private IPAddress ipAddress = IPAddress.Parse("127.0.0.1");
        private int puerto = 7500;
        public Clientcnct()
        {
            InitializeComponent();
        }

        private void Clientcnct_Load(object sender, EventArgs e)
        {

        }

        private void btnsalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnVoteup_Click(object sender, EventArgs e)
        {
            try
            {
                // Crear el socket TCP/IP
                using (Socket clienteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp))
                {
                    // Conectar el socket al servidor
                    clienteSocket.Connect(ipAddress, puerto);

                    // Mensaje a enviar
                    string mensaje = "Mensaje desde el botón Vote Up";

                    // Convertir el mensaje a bytes
                    byte[] mensajeBytes = Encoding.UTF8.GetBytes(mensaje);

                    // Enviar el mensaje al servidor
                    clienteSocket.Send(mensajeBytes);

                    // Cerrar la conexión
                    clienteSocket.Shutdown(SocketShutdown.Both);
                    clienteSocket.Close();
                }

                MessageBox.Show("Mensaje enviado al servidor");
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al enviar el mensaje: " + ex.Message);
            }

        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
