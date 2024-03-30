namespace CommunityMusicP
{
    partial class Clientcnct
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Clientcnct));
            panel1 = new Panel();
            btnVotedwn = new Button();
            btnVoteup = new Button();
            listView1 = new ListView();
            Cancion = new ColumnHeader();
            Artista = new ColumnHeader();
            Album = new ColumnHeader();
            panel2 = new Panel();
            panel1.SuspendLayout();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.Controls.Add(btnVotedwn);
            panel1.Controls.Add(btnVoteup);
            panel1.Location = new Point(1, 421);
            panel1.Name = "panel1";
            panel1.Size = new Size(482, 150);
            panel1.TabIndex = 1;
            // 
            // btnVotedwn
            // 
            btnVotedwn.ForeColor = Color.DarkRed;
            btnVotedwn.Location = new Point(275, 24);
            btnVotedwn.Name = "btnVotedwn";
            btnVotedwn.Size = new Size(140, 101);
            btnVotedwn.TabIndex = 1;
            btnVotedwn.Text = "Vote-down";
            btnVotedwn.UseVisualStyleBackColor = true;
            btnVotedwn.Click += btnVotedwn_Click;
            // 
            // btnVoteup
            // 
            btnVoteup.ForeColor = Color.DarkRed;
            btnVoteup.Location = new Point(91, 24);
            btnVoteup.Name = "btnVoteup";
            btnVoteup.Size = new Size(140, 101);
            btnVoteup.TabIndex = 0;
            btnVoteup.Text = "Vote-up";
            btnVoteup.UseVisualStyleBackColor = true;
            btnVoteup.Click += btnVoteup_Click;
            // 
            // listView1
            // 
            listView1.BackColor = Color.WhiteSmoke;
            listView1.Columns.AddRange(new ColumnHeader[] { Cancion, Artista, Album });
            listView1.ForeColor = Color.RosyBrown;
            listView1.Location = new Point(1, 2);
            listView1.Name = "listView1";
            listView1.Size = new Size(944, 413);
            listView1.TabIndex = 2;
            listView1.UseCompatibleStateImageBehavior = false;
            listView1.View = View.Details;
            listView1.SelectedIndexChanged += listView1_SelectedIndexChanged;
            // 
            // Cancion
            // 
            Cancion.Text = "Canción";
            Cancion.Width = 300;
            // 
            // Artista
            // 
            Artista.Text = "Artista";
            Artista.Width = 300;
            // 
            // Album
            // 
            Album.Text = "Album";
            Album.Width = 300;
            // 
            // panel2
            // 
            panel2.Location = new Point(489, 421);
            panel2.Name = "panel2";
            panel2.Size = new Size(456, 150);
            panel2.TabIndex = 3;
            // 
            // Clientcnct
            // 
            AutoScaleDimensions = new SizeF(8F, 21F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.WhiteSmoke;
            ClientSize = new Size(951, 571);
            Controls.Add(panel2);
            Controls.Add(listView1);
            Controls.Add(panel1);
            Font = new Font("Impact", 10.2F, FontStyle.Regular, GraphicsUnit.Point, 0);
            ForeColor = SystemColors.AppWorkspace;
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Icon = (Icon)resources.GetObject("$this.Icon");
            MaximizeBox = false;
            Name = "Clientcnct";
            Text = "Community Music Player";
            Load += Clientcnct_Load;
            panel1.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private Button btnsalir;
        private Panel panel1;
        private ListView listView1;
        private ColumnHeader Cancion;
        private ColumnHeader Artista;
        private ColumnHeader Album;
        private Button button3;
        private Button btnVotedwn;
        private Button btnVoteup;
        private Panel panel2;
    }
}