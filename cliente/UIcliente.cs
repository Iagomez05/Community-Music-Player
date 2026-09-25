using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace CommunityMusicP
{
    public partial class Cliente : Form
    {

        private Clientcnct form2;

        public Cliente(Clientcnct form2)
        {
            InitializeComponent();
            this.form2 = form2;
        }


        private void Cliente_Load(object sender, EventArgs e)
        {
            bool connected = Program.IsConnected;
            connectionStatus.Text = connected ? "●  CONNECTED" : "●  OFFLINE";
            connectionStatus.ForeColor = connected
                ? Color.FromArgb(61, 220, 151)
                : Color.FromArgb(255, 128, 138);
            btnEmpezarV.Enabled = connected;
            btnEmpezarV.Text = connected ? "Open playlist  →" : "Server unavailable";
        }
        private void btnsalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        private void btnEmpezarV_Click(object sender, EventArgs e)
        {

            // Crear una instancia del segundo formulario
            string jsonMessage = $"{{\"command\": \"GetPlaylist\"}}";
            Program.SendMessageToServer(jsonMessage);

            // Mostrar el segundo formulario
            form2.Show();
            this.Hide();
        }


    }
}
