package org.otaku.pictureViewer.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.otaku.pictureViewer.util.SystemUtil;

import net.coobird.thumbnailator.Thumbnails;

public class SinglePicturePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH_PERCENT = 22;
	public static final int HEIGHT_PERCENT = 25;
	
	public static final int TEXT_HEIGHT_PERCENT = 3;
	
	PictureListPanel picturesPanel;
	File file;
	JLabel imageLabel;
	
	public SinglePicturePanel(PictureListPanel picturesPanel, File file) {
		this.picturesPanel = picturesPanel;
		this.file = file;
	}

	public void init() {
		JPanel parent = picturesPanel.contentPanel;
		int width = SystemUtil.getWidthPixelOfPercent(parent, WIDTH_PERCENT);
		int height = SystemUtil.getWidthPixelOfPercent(parent, HEIGHT_PERCENT);
		int textHeight = SystemUtil.getWidthPixelOfPercent(parent, TEXT_HEIGHT_PERCENT);
		setPreferredSize(new Dimension(width, height));
		imageLabel = new JLabel();
		imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		imageLabel.setPreferredSize(new Dimension(width, width));
		imageLabel.setBackground(new Color(255, 255, 255, 0));
		add(imageLabel);
		JLabel textLabel = new JLabel(file.getName());
		textLabel.setPreferredSize(new Dimension(width, textHeight));
		textLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(textLabel);
		//如果要实现：鼠标放上去，右上角显示勾子，那么用绝对定位来实现吧。(jcheckbox)
		parent.add(this);
	}
	
	public void loadImage() {
		//生成缩略图
		try {
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			int height = image.getHeight();
			int panelWidth = imageLabel.getPreferredSize().width;
			int thumbnailWidth = -1;
			int thumbnailHeight = -1;
			if (width > height) {
				thumbnailWidth = panelWidth;
				thumbnailHeight = (int)(thumbnailWidth * 1.0 / width * height);
			} else {
				thumbnailHeight = panelWidth;
				thumbnailWidth = (int)(thumbnailHeight * 1.0 / height * width);
			}
			BufferedImage thumbnail = Thumbnails.of(image).size(thumbnailWidth, thumbnailHeight).asBufferedImage();
			//update ui in swing thread
			SwingUtilities.invokeLater(() -> {
				imageLabel.setIcon(new ImageIcon(thumbnail));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
