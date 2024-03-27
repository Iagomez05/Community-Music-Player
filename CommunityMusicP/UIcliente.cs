namespace CommunityMusicP
{
    public partial class Cliente : Form
    {
        public Cliente()
        {
            InitializeComponent();
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
            Clientcnct form2 = new Clientcnct();

            // Mostrar el segundo formulario
            form2.Show();
            this.Hide();
        }
    }
}
