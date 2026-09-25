namespace CommunityMusicP
{
    partial class Cliente
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Cliente));
            headerPanel = new Panel();
            connectionStatus = new Label();
            brandSubtitle = new Label();
            brandLabel = new Label();
            btnsalir = new Button();
            heroPanel = new Panel();
            heroAccent = new Panel();
            featureLabel = new Label();
            heroDescription = new Label();
            label1 = new Label();
            eyebrowLabel = new Label();
            btnEmpezarV = new Button();
            footerLabel = new Label();
            headerPanel.SuspendLayout();
            heroPanel.SuspendLayout();
            SuspendLayout();
            //
            // headerPanel
            //
            headerPanel.BackColor = Color.FromArgb(15, 21, 28);
            headerPanel.Controls.Add(connectionStatus);
            headerPanel.Controls.Add(brandSubtitle);
            headerPanel.Controls.Add(brandLabel);
            headerPanel.Controls.Add(btnsalir);
            headerPanel.Dock = DockStyle.Top;
            headerPanel.Location = new Point(0, 0);
            headerPanel.Name = "headerPanel";
            headerPanel.Padding = new Padding(28, 16, 24, 16);
            headerPanel.Size = new Size(940, 86);
            headerPanel.TabIndex = 0;
            //
            // connectionStatus
            //
            connectionStatus.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            connectionStatus.AutoSize = true;
            connectionStatus.BackColor = Color.FromArgb(24, 33, 43);
            connectionStatus.Font = new Font("Segoe UI Semibold", 9F);
            connectionStatus.ForeColor = Color.FromArgb(61, 220, 151);
            connectionStatus.Location = new Point(672, 30);
            connectionStatus.Name = "connectionStatus";
            connectionStatus.Padding = new Padding(12, 6, 12, 6);
            connectionStatus.Size = new Size(112, 32);
            connectionStatus.TabIndex = 3;
            connectionStatus.Text = "●  CONNECTED";
            //
            // brandSubtitle
            //
            brandSubtitle.AutoSize = true;
            brandSubtitle.Font = new Font("Segoe UI", 9F);
            brandSubtitle.ForeColor = Color.FromArgb(137, 150, 166);
            brandSubtitle.Location = new Point(30, 49);
            brandSubtitle.Name = "brandSubtitle";
            brandSubtitle.Size = new Size(173, 20);
            brandSubtitle.TabIndex = 2;
            brandSubtitle.Text = "Collaborative voting client";
            //
            // brandLabel
            //
            brandLabel.AutoSize = true;
            brandLabel.Font = new Font("Segoe UI", 14F, FontStyle.Bold);
            brandLabel.ForeColor = Color.FromArgb(244, 246, 250);
            brandLabel.Location = new Point(28, 16);
            brandLabel.Name = "brandLabel";
            brandLabel.Size = new Size(244, 32);
            brandLabel.TabIndex = 1;
            brandLabel.Text = "COMMUNITY MUSIC";
            //
            // btnsalir
            //
            btnsalir.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            btnsalir.BackColor = Color.FromArgb(25, 34, 44);
            btnsalir.Cursor = Cursors.Hand;
            btnsalir.FlatAppearance.BorderColor = Color.FromArgb(43, 56, 70);
            btnsalir.FlatAppearance.MouseOverBackColor = Color.FromArgb(45, 32, 40);
            btnsalir.FlatStyle = FlatStyle.Flat;
            btnsalir.Font = new Font("Segoe UI Semibold", 9F);
            btnsalir.ForeColor = Color.FromArgb(214, 222, 232);
            btnsalir.Location = new Point(811, 25);
            btnsalir.Name = "btnsalir";
            btnsalir.Size = new Size(101, 38);
            btnsalir.TabIndex = 0;
            btnsalir.Text = "Exit";
            btnsalir.UseVisualStyleBackColor = false;
            btnsalir.Click += btnsalir_Click;
            //
            // heroPanel
            //
            heroPanel.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            heroPanel.BackColor = Color.FromArgb(19, 26, 34);
            heroPanel.Controls.Add(heroAccent);
            heroPanel.Controls.Add(featureLabel);
            heroPanel.Controls.Add(heroDescription);
            heroPanel.Controls.Add(label1);
            heroPanel.Controls.Add(eyebrowLabel);
            heroPanel.Controls.Add(btnEmpezarV);
            heroPanel.Location = new Point(38, 122);
            heroPanel.Name = "heroPanel";
            heroPanel.Padding = new Padding(50);
            heroPanel.Size = new Size(864, 390);
            heroPanel.TabIndex = 1;
            //
            // heroAccent
            //
            heroAccent.BackColor = Color.FromArgb(124, 92, 252);
            heroAccent.Location = new Point(0, 0);
            heroAccent.Name = "heroAccent";
            heroAccent.Size = new Size(7, 390);
            heroAccent.TabIndex = 5;
            //
            // featureLabel
            //
            featureLabel.AutoSize = true;
            featureLabel.Font = new Font("Segoe UI", 10F);
            featureLabel.ForeColor = Color.FromArgb(156, 135, 255);
            featureLabel.Location = new Point(53, 245);
            featureLabel.Name = "featureLabel";
            featureLabel.Size = new Size(315, 23);
            featureLabel.TabIndex = 4;
            featureLabel.Text = "Live playlist  ·  Community votes  ·  TCP";
            //
            // heroDescription
            //
            heroDescription.Font = new Font("Segoe UI", 11F);
            heroDescription.ForeColor = Color.FromArgb(157, 168, 182);
            heroDescription.Location = new Point(53, 158);
            heroDescription.Name = "heroDescription";
            heroDescription.Size = new Size(540, 70);
            heroDescription.TabIndex = 3;
            heroDescription.Text = "Connect to the shared session, explore the current queue and help decide what plays next.";
            //
            // label1
            //
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 28F, FontStyle.Bold);
            label1.ForeColor = Color.FromArgb(244, 246, 250);
            label1.Location = new Point(48, 82);
            label1.Name = "label1";
            label1.Size = new Size(568, 62);
            label1.TabIndex = 2;
            label1.Text = "Shape the shared playlist.";
            //
            // eyebrowLabel
            //
            eyebrowLabel.AutoSize = true;
            eyebrowLabel.Font = new Font("Segoe UI", 9F, FontStyle.Bold);
            eyebrowLabel.ForeColor = Color.FromArgb(156, 135, 255);
            eyebrowLabel.Location = new Point(53, 48);
            eyebrowLabel.Name = "eyebrowLabel";
            eyebrowLabel.Size = new Size(147, 20);
            eyebrowLabel.TabIndex = 1;
            eyebrowLabel.Text = "READY TO LISTEN";
            //
            // btnEmpezarV
            //
            btnEmpezarV.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            btnEmpezarV.BackColor = Color.FromArgb(124, 92, 252);
            btnEmpezarV.Cursor = Cursors.Hand;
            btnEmpezarV.FlatAppearance.BorderSize = 0;
            btnEmpezarV.FlatAppearance.MouseOverBackColor = Color.FromArgb(143, 116, 255);
            btnEmpezarV.FlatStyle = FlatStyle.Flat;
            btnEmpezarV.Font = new Font("Segoe UI Semibold", 11F);
            btnEmpezarV.ForeColor = Color.White;
            btnEmpezarV.Location = new Point(620, 294);
            btnEmpezarV.Name = "btnEmpezarV";
            btnEmpezarV.Size = new Size(190, 52);
            btnEmpezarV.TabIndex = 0;
            btnEmpezarV.Text = "Open playlist  →";
            btnEmpezarV.UseVisualStyleBackColor = false;
            btnEmpezarV.Click += btnEmpezarV_Click;
            //
            // footerLabel
            //
            footerLabel.Anchor = AnchorStyles.Bottom | AnchorStyles.Left;
            footerLabel.AutoSize = true;
            footerLabel.Font = new Font("Segoe UI", 9F);
            footerLabel.ForeColor = Color.FromArgb(100, 114, 130);
            footerLabel.Location = new Point(38, 536);
            footerLabel.Name = "footerLabel";
            footerLabel.Size = new Size(287, 20);
            footerLabel.TabIndex = 2;
            footerLabel.Text = "Desktop client · C# · Windows Forms";
            //
            // Cliente
            //
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(11, 15, 20);
            ClientSize = new Size(940, 580);
            Controls.Add(footerLabel);
            Controls.Add(heroPanel);
            Controls.Add(headerPanel);
            Font = new Font("Segoe UI", 9F);
            ForeColor = Color.FromArgb(244, 246, 250);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Icon = (Icon)resources.GetObject("$this.Icon");
            MaximizeBox = false;
            Name = "Cliente";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Community Music Player · Client";
            Load += Cliente_Load;
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            heroPanel.ResumeLayout(false);
            heroPanel.PerformLayout();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Panel headerPanel;
        private Label connectionStatus;
        private Label brandSubtitle;
        private Label brandLabel;
        private Button btnsalir;
        private Panel heroPanel;
        private Panel heroAccent;
        private Label featureLabel;
        private Label heroDescription;
        private Label label1;
        private Label eyebrowLabel;
        private Button btnEmpezarV;
        private Label footerLabel;
    }
}
