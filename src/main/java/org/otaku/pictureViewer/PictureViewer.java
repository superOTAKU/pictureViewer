package org.otaku.pictureViewer;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.otaku.pictureViewer.ui.MainWindow;

/**
 * design goal:
 * 		1. all main components can change their ui independently.
 * 		2. don't want to manage all the component hierarchy, unless necessary
 * 		3. how to manage listener ?
 * 
 * @author ljf
 * @date 2020年3月2日 下午4:29:47
 *
 */
public class PictureViewer {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}
			new MainWindow().init();
		});
	}
}
