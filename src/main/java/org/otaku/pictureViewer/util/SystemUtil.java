package org.otaku.pictureViewer.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SystemUtil {
	private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	public static int getWidthPixelOfPercent(int percent) {
		Dimension screenSize = toolkit.getScreenSize();
		return (int)(screenSize.getWidth() * percent * 0.01);
	}
	
	public static int getWidthPixelOfPercent(Component parent, int percent) {
		return (int)(parent.getPreferredSize().getWidth() * percent * 0.01);
	}
	
	public static int getHeightPixelOfPercent(int percent) {
		Dimension screenSize = toolkit.getScreenSize();
		return (int)(screenSize.getHeight() * percent * 0.01);
	}
	
	public static int getHeightPixelOfPercent(Component parent, int percent) {
		return (int)(parent.getPreferredSize().getHeight() * percent * 0.01);
	}
	
	public static Dimension getDimensionOfPercent(int widthPercent, int heightPercent) {
		return new Dimension(getWidthPixelOfPercent(widthPercent), getHeightPixelOfPercent(heightPercent));
	}
	
	public static Dimension getDimensionOfPercent(Component parent, int widthPercent, int heightPercent) {
		return new Dimension(getWidthPixelOfPercent(parent, widthPercent), getHeightPixelOfPercent(parent, heightPercent));
	}
	
	public static void setBoundsOfPercent(Component component, Component parent, int xp, int yp, int wp, int hp) {
		double w = parent.getPreferredSize().getWidth();
		double h = parent.getPreferredSize().getHeight();
		component.setBounds((int)(xp * w * 0.01),
				(int)(yp * h * 0.01),
				(int)(wp * w * 0.01),
				(int)(hp * h * 0.01));
	}
	
}
