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

        }
        private void btnsalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        private void btnEmpezarV_Click(object sender, EventArgs e)
        {

            JObject jsonObject = new JObject();
            jsonObject.Add("command", "GetPlaylist");
            StringWriter sw = new StringWriter();
            JsonWriter writer = new JsonTextWriter(sw);
            jsonObject.WriteTo(writer);
            string json = sw.ToString();

            Program.SendMessageToServer(json);

            // Mostrar el segundo formulario
            form2.Show();
            this.Hide();
        }


    }
}
