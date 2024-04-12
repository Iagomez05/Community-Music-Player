
namespace CommunityMusicP
{
    partial class Cliente
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Cliente));
            label1 = new Label();
            btnEmpezarV = new Button();
            btnsalir = new Button();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Bernard MT Condensed", 25.8000011F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label1.ForeColor = Color.DarkRed;
            label1.Location = new Point(65, 88);
            label1.Name = "label1";
            label1.Size = new Size(702, 52);
            label1.TabIndex = 0;
            label1.Text = "¡Bienvenido a Community Music Playlist!";
            // 
            // btnEmpezarV
            // 
            btnEmpezarV.BackColor = Color.DarkRed;
            btnEmpezarV.ForeColor = SystemColors.ControlLightLight;
            btnEmpezarV.Location = new Point(300, 226);
            btnEmpezarV.Name = "btnEmpezarV";
            btnEmpezarV.Size = new Size(204, 56);
            btnEmpezarV.TabIndex = 1;
            btnEmpezarV.Text = "Empezar a votar";
            btnEmpezarV.UseVisualStyleBackColor = false;
            btnEmpezarV.Click += btnEmpezarV_Click;
            // 
            // btnsalir
            // 
            btnsalir.BackColor = Color.DarkRed;
            btnsalir.ForeColor = SystemColors.ButtonHighlight;
            btnsalir.Location = new Point(696, 12);
            btnsalir.Name = "btnsalir";
            btnsalir.Size = new Size(94, 40);
            btnsalir.TabIndex = 2;
            btnsalir.Text = "SALIR";
            btnsalir.UseVisualStyleBackColor = false;
            btnsalir.Click += btnsalir_Click;
            // 
            // Cliente
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.WhiteSmoke;
            ClientSize = new Size(802, 453);
            Controls.Add(btnsalir);
            Controls.Add(btnEmpezarV);
            Controls.Add(label1);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Icon = (Icon)resources.GetObject("$this.Icon");
            MaximizeBox = false;
            Name = "Cliente";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Community Music Player";
            Load += Cliente_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        private void label1_Click(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }

        #endregion

        private Label label1;
        private Button btnEmpezarV;
        private Button btnsalir;
    }
}
