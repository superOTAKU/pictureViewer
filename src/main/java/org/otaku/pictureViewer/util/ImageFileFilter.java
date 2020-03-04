package org.otaku.pictureViewer.util;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter {
	
	public static final ImageFileFilter INSTANCE_NO_DIR = new ImageFileFilter(false);
	
	public static final ImageFileFilter INSTANCE_WITH_DIR = new ImageFileFilter(true);
	
	private boolean acceptDir;
	
	public ImageFileFilter(boolean acceptDir) {
		this.acceptDir = acceptDir;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return acceptDir;
		}
		String name = file.getName();
		if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")) {
			return true;
		}
		return false;
	}

}
