namespace CommunityMusicP
{
    partial class Clientcnct
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Clientcnct));
            headerPanel = new Panel();
            connectionStatus = new Label();
            headerSubtitle = new Label();
            headerTitle = new Label();
            listCard = new Panel();
            listView = new ListView();
            Cancion = new ColumnHeader();
            Artista = new ColumnHeader();
            Votesup = new ColumnHeader();
            Votesdown = new ColumnHeader();
            id = new ColumnHeader();
            listCaption = new Label();
            listTitle = new Label();
            panel1 = new Panel();
            selectionLabel = new Label();
            button1 = new Button();
            btnVotedwn = new Button();
            btnVoteup = new Button();
            headerPanel.SuspendLayout();
            listCard.SuspendLayout();
            panel1.SuspendLayout();
            SuspendLayout();
            //
            // headerPanel
            //
            headerPanel.BackColor = Color.FromArgb(15, 21, 28);
            headerPanel.Controls.Add(connectionStatus);
            headerPanel.Controls.Add(headerSubtitle);
            headerPanel.Controls.Add(headerTitle);
            headerPanel.Dock = DockStyle.Top;
            headerPanel.Location = new Point(0, 0);
            headerPanel.Name = "headerPanel";
            headerPanel.Size = new Size(1120, 92);
            headerPanel.TabIndex = 0;
            //
            // connectionStatus
            //
            connectionStatus.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            connectionStatus.AutoSize = true;
            connectionStatus.BackColor = Color.FromArgb(24, 33, 43);
            connectionStatus.Font = new Font("Segoe UI Semibold", 9F);
            connectionStatus.ForeColor = Color.FromArgb(61, 220, 151);
            connectionStatus.Location = new Point(928, 31);
            connectionStatus.Name = "connectionStatus";
            connectionStatus.Padding = new Padding(12, 6, 12, 6);
            connectionStatus.Size = new Size(148, 32);
            connectionStatus.TabIndex = 2;
            connectionStatus.Text = "●  SESSION ONLINE";
            //
            // headerSubtitle
            //
            headerSubtitle.AutoSize = true;
            headerSubtitle.Font = new Font("Segoe UI", 9F);
            headerSubtitle.ForeColor = Color.FromArgb(137, 150, 166);
            headerSubtitle.Location = new Point(32, 52);
            headerSubtitle.Name = "headerSubtitle";
            headerSubtitle.Size = new Size(306, 20);
            headerSubtitle.TabIndex = 1;
            headerSubtitle.Text = "Vote for the tracks you want to hear next";
            //
            // headerTitle
            //
            headerTitle.AutoSize = true;
            headerTitle.Font = new Font("Segoe UI", 18F, FontStyle.Bold);
            headerTitle.ForeColor = Color.FromArgb(244, 246, 250);
            headerTitle.Location = new Point(28, 14);
            headerTitle.Name = "headerTitle";
            headerTitle.Size = new Size(309, 41);
            headerTitle.TabIndex = 0;
            headerTitle.Text = "Collaborative playlist";
            //
            // listCard
            //
            listCard.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            listCard.BackColor = Color.FromArgb(19, 26, 34);
            listCard.Controls.Add(listView);
            listCard.Controls.Add(listCaption);
            listCard.Controls.Add(listTitle);
            listCard.Location = new Point(28, 116);
            listCard.Name = "listCard";
            listCard.Padding = new Padding(20);
            listCard.Size = new Size(1064, 446);
            listCard.TabIndex = 1;
            //
            // listView
            //
            listView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            listView.BackColor = Color.FromArgb(15, 21, 28);
            listView.BorderStyle = BorderStyle.FixedSingle;
            listView.Columns.AddRange(new ColumnHeader[] { Cancion, Artista, Votesup, Votesdown, id });
            listView.Font = new Font("Segoe UI", 10F);
            listView.ForeColor = Color.FromArgb(203, 211, 221);
            listView.FullRowSelect = true;
            listView.HeaderStyle = ColumnHeaderStyle.Nonclickable;
            listView.HideSelection = false;
            listView.Location = new Point(20, 78);
            listView.MultiSelect = false;
            listView.Name = "listView";
            listView.OwnerDraw = true;
            listView.Size = new Size(1024, 348);
            listView.TabIndex = 2;
            listView.UseCompatibleStateImageBehavior = false;
            listView.View = View.Details;
            listView.DrawColumnHeader += listView_DrawColumnHeader;
            listView.DrawItem += listView_DrawItem;
            listView.DrawSubItem += listView_DrawSubItem;
            listView.Resize += listView_Resize;
            listView.SelectedIndexChanged += listView_SelectedIndexChanged;
            //
            // Cancion
            //
            Cancion.Text = "TRACK";
            Cancion.Width = 420;
            //
            // Artista
            //
            Artista.Text = "ARTIST";
            Artista.Width = 300;
            //
            // Votesup
            //
            Votesup.Text = "UPVOTES";
            Votesup.Width = 145;
            //
            // Votesdown
            //
            Votesdown.Text = "DOWNVOTES";
            Votesdown.Width = 160;
            //
            // id
            //
            id.Text = "ID";
            id.Width = 0;
            //
            // listCaption
            //
            listCaption.AutoSize = true;
            listCaption.Font = new Font("Segoe UI", 9F);
            listCaption.ForeColor = Color.FromArgb(137, 150, 166);
            listCaption.Location = new Point(22, 47);
            listCaption.Name = "listCaption";
            listCaption.Size = new Size(353, 20);
            listCaption.TabIndex = 1;
            listCaption.Text = "Select a song to activate the voting controls.";
            //
            // listTitle
            //
            listTitle.AutoSize = true;
            listTitle.Font = new Font("Segoe UI", 14F, FontStyle.Bold);
            listTitle.ForeColor = Color.FromArgb(244, 246, 250);
            listTitle.Location = new Point(18, 12);
            listTitle.Name = "listTitle";
            listTitle.Size = new Size(177, 32);
            listTitle.TabIndex = 0;
            listTitle.Text = "Current queue";
            //
            // panel1
            //
            panel1.BackColor = Color.FromArgb(15, 21, 28);
            panel1.Controls.Add(selectionLabel);
            panel1.Controls.Add(button1);
            panel1.Controls.Add(btnVotedwn);
            panel1.Controls.Add(btnVoteup);
            panel1.Dock = DockStyle.Bottom;
            panel1.Location = new Point(0, 588);
            panel1.Name = "panel1";
            panel1.Size = new Size(1120, 112);
            panel1.TabIndex = 2;
            //
            // selectionLabel
            //
            selectionLabel.AutoSize = true;
            selectionLabel.Font = new Font("Segoe UI", 9F);
            selectionLabel.ForeColor = Color.FromArgb(137, 150, 166);
            selectionLabel.Location = new Point(30, 19);
            selectionLabel.Name = "selectionLabel";
            selectionLabel.Size = new Size(199, 20);
            selectionLabel.TabIndex = 3;
            selectionLabel.Text = "No track selected for voting";
            //
            // button1
            //
            button1.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            button1.BackColor = Color.FromArgb(25, 34, 44);
            button1.Cursor = Cursors.Hand;
            button1.FlatAppearance.BorderColor = Color.FromArgb(43, 56, 70);
            button1.FlatAppearance.MouseOverBackColor = Color.FromArgb(45, 32, 40);
            button1.FlatStyle = FlatStyle.Flat;
            button1.Font = new Font("Segoe UI Semibold", 10F);
            button1.ForeColor = Color.FromArgb(214, 222, 232);
            button1.Location = new Point(883, 35);
            button1.Name = "button1";
            button1.Size = new Size(207, 48);
            button1.TabIndex = 2;
            button1.Text = "Leave session";
            button1.UseVisualStyleBackColor = false;
            button1.Click += button1_Click;
            //
            // btnVotedwn
            //
            btnVotedwn.BackColor = Color.FromArgb(25, 34, 44);
            btnVotedwn.Cursor = Cursors.Hand;
            btnVotedwn.Enabled = false;
            btnVotedwn.FlatAppearance.BorderColor = Color.FromArgb(54, 66, 80);
            btnVotedwn.FlatAppearance.MouseOverBackColor = Color.FromArgb(62, 39, 50);
            btnVotedwn.FlatStyle = FlatStyle.Flat;
            btnVotedwn.Font = new Font("Segoe UI Semibold", 10F);
            btnVotedwn.ForeColor = Color.FromArgb(255, 150, 160);
            btnVotedwn.Location = new Point(427, 45);
            btnVotedwn.Name = "btnVotedwn";
            btnVotedwn.Size = new Size(180, 48);
            btnVotedwn.TabIndex = 1;
            btnVotedwn.Text = "Downvote  ▼";
            btnVotedwn.UseVisualStyleBackColor = false;
            btnVotedwn.Click += btnVotedwn_Click;
            //
            // btnVoteup
            //
            btnVoteup.BackColor = Color.FromArgb(124, 92, 252);
            btnVoteup.Cursor = Cursors.Hand;
            btnVoteup.Enabled = false;
            btnVoteup.FlatAppearance.BorderSize = 0;
            btnVoteup.FlatAppearance.MouseOverBackColor = Color.FromArgb(143, 116, 255);
            btnVoteup.FlatStyle = FlatStyle.Flat;
            btnVoteup.Font = new Font("Segoe UI Semibold", 10F);
            btnVoteup.ForeColor = Color.White;
            btnVoteup.Location = new Point(231, 45);
            btnVoteup.Name = "btnVoteup";
            btnVoteup.Size = new Size(180, 48);
            btnVoteup.TabIndex = 0;
            btnVoteup.Text = "Upvote  ▲";
            btnVoteup.UseVisualStyleBackColor = false;
            btnVoteup.Click += btnVoteup_Click;
            //
            // Clientcnct
            //
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(11, 15, 20);
            ClientSize = new Size(1120, 700);
            Controls.Add(panel1);
            Controls.Add(listCard);
            Controls.Add(headerPanel);
            Font = new Font("Segoe UI", 9F);
            ForeColor = Color.FromArgb(244, 246, 250);
            Icon = (Icon)resources.GetObject("$this.Icon");
            MinimumSize = new Size(980, 640);
            Name = "Clientcnct";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Community Music Player · Collaborative Playlist";
            Load += Clientcnct_Load;
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            listCard.ResumeLayout(false);
            listCard.PerformLayout();
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private Panel headerPanel;
        private Label connectionStatus;
        private Label headerSubtitle;
        private Label headerTitle;
        private Panel listCard;
        private Label listCaption;
        private Label listTitle;
        private Panel panel1;
        private Label selectionLabel;
        private ListView listView;
        private ColumnHeader Cancion;
        private ColumnHeader Artista;
        private Button btnVotedwn;
        private Button btnVoteup;
        private ColumnHeader Votesup;
        private ColumnHeader Votesdown;
        private Button button1;
        private ColumnHeader id;
    }
}
