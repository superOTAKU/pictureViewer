package org.otaku.pictureViewer.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.otaku.pictureViewer.util.SystemUtil;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	FileTreePanel fileTree;
	
	PicturesPanel picturesPanel;
	
	JPanel contentPane;
	
	public void init() {
		contentPane = new JPanel();
		contentPane.setPreferredSize(SystemUtil.getDimensionOfPercent(70, 70));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		fileTree = new FileTreePanel(this);
		picturesPanel = new PicturesPanel(this);
		fileTree.init();
		picturesPanel.init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
}
