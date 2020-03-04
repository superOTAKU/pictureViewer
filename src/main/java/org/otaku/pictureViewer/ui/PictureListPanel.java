package org.otaku.pictureViewer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.otaku.pictureViewer.util.SystemUtil;

public class PictureListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final int GAP_PERCENT = 2;

	MainWindow mainWindow;
	
	JPanel contentPanel;
	
	private ExecutorService service = Executors.newCachedThreadPool();
	
	//记录选中的文件，后续可以做复制/粘贴等操作...
	private LinkedHashSet<SinglePicturePanel> selectedFiles = new LinkedHashSet<>();
	
	public PictureListPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void init() {
		SystemUtil.setBoundsOfPercent(this, mainWindow.contentPane, 30, 0, 70, 100);
		setPreferredSize(SystemUtil.getDimensionOfPercent(mainWindow.contentPane, 70, 100));
		setLayout(new BorderLayout());
		mainWindow.contentPane.add(this);
		
		JPanel panel = new JPanel();
		contentPanel = panel;
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		
		int gap = SystemUtil.getWidthPixelOfPercent(this, GAP_PERCENT);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, gap, gap));
	}
	
	public void showPictures(File[] picFiles) {
		contentPanel.removeAll();
		contentPanel.setPreferredSize(getPreferredSize());
		if (picFiles == null || picFiles.length == 0) {
			contentPanel.setPreferredSize(new Dimension(getPreferredSize().width, 0));
			contentPanel.revalidate();
			return;
		}
		for (File f : picFiles) {
			SinglePicturePanel singlePicturePanel = new SinglePicturePanel(this, f);
			singlePicturePanel.init();
			service.execute(() -> {
				singlePicturePanel.loadImage();
			});
		}
		
		int picCountPerRow = 100 / SinglePicturePanel.WIDTH_PERCENT;
		int rowCount = picFiles.length / picCountPerRow + (picFiles.length % picCountPerRow != 0 ? 1 : 0);
		int cellHeight = SystemUtil.getWidthPixelOfPercent(contentPanel, SinglePicturePanel.HEIGHT_PERCENT);
		int gap = SystemUtil.getWidthPixelOfPercent(this, GAP_PERCENT);
		contentPanel.setPreferredSize(new Dimension(getPreferredSize().width, rowCount * cellHeight + gap * (rowCount + 1)));
		contentPanel.revalidate();
	}
	
	public void selectPicturePanel(SinglePicturePanel picturePanel) {
		selectedFiles.add(picturePanel);
	}
	
	public void clearSelectedPicturePanels() {
		selectedFiles.clear();
	}
	
}
