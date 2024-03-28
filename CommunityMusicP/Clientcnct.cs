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
                log.Info("Info:: Mesaje enviado"); // Log del error, que se guarda en el archivo de logs
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al enviar el mensaje: " + ex.Message);
                log.Error("Error: " + ex.Message); // Log del error, que se guarda en el archivo de logs
            }

        }
    }
}
