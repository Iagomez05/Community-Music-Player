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
            button1 = new Button();
            btnVotedwn = new Button();
            btnVoteup = new Button();
            listView = new ListView();
            Cancion = new ColumnHeader();
            Artista = new ColumnHeader();
            Votesup = new ColumnHeader();
            Votesdown = new ColumnHeader();
            panel2 = new Panel();
            id = new ColumnHeader();
            panel1.SuspendLayout();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.Controls.Add(button1);
            panel1.Controls.Add(btnVotedwn);
            panel1.Controls.Add(btnVoteup);
            panel1.Location = new Point(1, 421);
            panel1.Name = "panel1";
            panel1.Size = new Size(482, 150);
            panel1.TabIndex = 1;
            // 
            // button1
            // 
            button1.ForeColor = Color.DarkRed;
            button1.Location = new Point(378, 36);
            button1.Name = "button1";
            button1.Size = new Size(77, 54);
            button1.TabIndex = 2;
            button1.Text = "Update";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // btnVotedwn
            // 
            btnVotedwn.ForeColor = Color.DarkRed;
            btnVotedwn.Location = new Point(232, 13);
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
            btnVoteup.Location = new Point(46, 13);
            btnVoteup.Name = "btnVoteup";
            btnVoteup.Size = new Size(140, 101);
            btnVoteup.TabIndex = 0;
            btnVoteup.Text = "Vote-up";
            btnVoteup.UseVisualStyleBackColor = true;
            btnVoteup.Click += btnVoteup_Click;
            // 
            // listView
            // 
            listView.BackColor = Color.WhiteSmoke;
            listView.Columns.AddRange(new ColumnHeader[] { Cancion, Artista, Votesup, Votesdown, id });
            listView.ForeColor = Color.RosyBrown;
            listView.FullRowSelect = true;
            listView.GridLines = true;
            listView.Location = new Point(1, 2);
            listView.Name = "listView";
            listView.Size = new Size(944, 413);
            listView.TabIndex = 2;
            listView.UseCompatibleStateImageBehavior = false;
            listView.View = View.Details;
            listView.SelectedIndexChanged += listView_SelectedIndexChanged;
            // 
            // Cancion
            // 
            Cancion.Text = "Canción";
            Cancion.Width = 320;
            // 
            // Artista
            // 
            Artista.Text = "Artista";
            Artista.Width = 210;
            // 
            // Votesup
            // 
            Votesup.Text = "Votesup";
            Votesup.Width = 210;
            // 
            // Votesdown
            // 
            Votesdown.Text = "VotesDown";
            Votesdown.Width = 210;
            // 
            // panel2
            // 
            panel2.Location = new Point(489, 421);
            panel2.Name = "panel2";
            panel2.Size = new Size(456, 150);
            panel2.TabIndex = 3;
            // 
            // id
            // 
            id.Text = "id";
            // 
            // Clientcnct
            // 
            AutoScaleDimensions = new SizeF(8F, 21F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.WhiteSmoke;
            ClientSize = new Size(951, 571);
            Controls.Add(panel2);
            Controls.Add(listView);
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

        private Panel panel1;
        private ListView listView;
        private ColumnHeader Cancion;
        private ColumnHeader Artista;
        private Button btnVotedwn;
        private Button btnVoteup;
        private Panel panel2;
        private ColumnHeader Votesup;
        private ColumnHeader Votesdown;
        private Button button1;
        private ColumnHeader id;
    }
}