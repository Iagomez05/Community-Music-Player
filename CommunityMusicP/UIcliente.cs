using System.Text;

namespace CommunityMusicP
{
    public partial class Cliente : Form
    {

        private static System.Windows.Forms.Timer timer;
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

            // Crear una instancia del segundo formulario
            Program.SendMessageToServer("GetPlaylist");

            // Mostrar el segundo formulario
            form2.Show();
            this.Hide();
        }


    }
}
