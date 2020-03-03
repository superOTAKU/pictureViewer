package org.otaku.pictureViewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.otaku.pictureViewer.util.SystemUtil;

public class PicturesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	MainWindow mainWindow;

	public PicturesPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void init() {
		SystemUtil.setBoundsOfPercent(this, mainWindow.contentPane, 30, 0, 70, 100);
		setPreferredSize(SystemUtil.getDimensionOfPercent(mainWindow.contentPane, 70, 100));
		setLayout(new BorderLayout());
		mainWindow.contentPane.add(this);
		
		JPanel panel = new JPanel();
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		
		Dimension deliDimension = SystemUtil.getDimensionOfPercent(this, 2, 2);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, (int)deliDimension.getWidth(), (int)deliDimension.getHeight()));
		panel.setBackground(Color.BLUE);
		int width = SystemUtil.getWidthPixelOfPercent(this, 22);
		for (int i = 0; i < 10; i++) {
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(width, width));
			jp.setBackground(Color.pink);
			panel.add(jp);
		}
		panel.setPreferredSize(new Dimension(getPreferredSize().width, width * 3 + deliDimension.height * 6));
		
	}
	
}
