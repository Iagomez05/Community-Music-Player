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
            btnsalir = new Button();
            panel1 = new Panel();
            btnVotedwn = new Button();
            btnVoteup = new Button();
            listView1 = new ListView();
            Cancion = new ColumnHeader();
            Votesups = new ColumnHeader();
            Votesdown = new ColumnHeader();
            panel2 = new Panel();
            panel1.SuspendLayout();
            SuspendLayout();
            // 
            // btnsalir
            // 
            btnsalir.ForeColor = SystemColors.ActiveCaptionText;
            btnsalir.Location = new Point(636, 281);
            btnsalir.Name = "btnsalir";
            btnsalir.Size = new Size(67, 28);
            btnsalir.TabIndex = 0;
            btnsalir.Text = "SALIR";
            btnsalir.UseVisualStyleBackColor = true;
            btnsalir.Click += btnsalir_Click;
            // 
            // panel1
            // 
            panel1.Controls.Add(btnVotedwn);
            panel1.Controls.Add(btnVoteup);
            panel1.Location = new Point(1, 435);
            panel1.Name = "panel1";
            panel1.Size = new Size(469, 136);
            panel1.TabIndex = 1;
            // 
            // btnVotedwn
            // 
            btnVotedwn.ForeColor = Color.DarkRed;
            btnVotedwn.Location = new Point(248, 38);
            btnVotedwn.Name = "btnVotedwn";
            btnVotedwn.Size = new Size(179, 68);
            btnVotedwn.TabIndex = 1;
            btnVotedwn.Text = "Vote-down";
            btnVotedwn.UseVisualStyleBackColor = true;
            // 
            // btnVoteup
            // 
            btnVoteup.ForeColor = Color.DarkRed;
            btnVoteup.Location = new Point(35, 38);
            btnVoteup.Name = "btnVoteup";
            btnVoteup.Size = new Size(179, 68);
            btnVoteup.TabIndex = 0;
            btnVoteup.Text = "Vote-up";
            btnVoteup.UseVisualStyleBackColor = true;
            btnVoteup.Click += btnVoteup_Click;
            // 
            // listView1
            // 
            listView1.BackColor = SystemColors.ControlText;
            listView1.Columns.AddRange(new ColumnHeader[] { Cancion, Votesups, Votesdown });
            listView1.ForeColor = Color.RosyBrown;
            listView1.Location = new Point(1, 2);
            listView1.Name = "listView1";
            listView1.Size = new Size(949, 427);
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
            // Votesups
            // 
            Votesups.Text = "Votes-up";
            Votesups.Width = 150;
            // 
            // Votesdown
            // 
            Votesdown.Text = "Votes-down";
            Votesdown.Width = 150;
            // 
            // panel2
            // 
            panel2.Location = new Point(476, 435);
            panel2.Name = "panel2";
            panel2.Size = new Size(474, 136);
            panel2.TabIndex = 3;
            // 
            // Clientcnct
            // 
            AutoScaleDimensions = new SizeF(8F, 21F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.Black;
            ClientSize = new Size(951, 571);
            Controls.Add(panel2);
            Controls.Add(listView1);
            Controls.Add(panel1);
            Controls.Add(btnsalir);
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
        private ColumnHeader Votesups;
        private ColumnHeader Votesdown;
        private Button btnVotedwn;
        private Button btnVoteup;
        private Panel panel2;
    }
}