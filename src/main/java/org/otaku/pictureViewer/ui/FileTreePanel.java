package org.otaku.pictureViewer.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import org.otaku.pictureViewer.util.ImageFileFilter;
import org.otaku.pictureViewer.util.SystemUtil;

public class FileTreePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	MainWindow mainWindow;
	
	JTree fileTree;
	
	public FileTreePanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * 	自己负责自己的显示，以及所有监听器初始化等
	 */
	public void init() {
		setLayout(new BorderLayout());
		SystemUtil.setBoundsOfPercent(this, mainWindow.contentPane, 0, 0, 30, 100);
		setPreferredSize(SystemUtil.getDimensionOfPercent(mainWindow.contentPane, 30, 100));
		mainWindow.contentPane.add(this);
		
		fileTree = new JTree(getFileSystemRootTreeNode());
		fileTree.setCellRenderer(new FileTreeCellRenderer());
		fileTree.addTreeSelectionListener(new FileTreeSelectionListener());
		JScrollPane jsp = new JScrollPane(fileTree);
		add(jsp);
	}
	
	private static DefaultMutableTreeNode getFileSystemRootTreeNode() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("My Computer");
		for (File f : File.listRoots()) {
			root.add(new DefaultMutableTreeNode(f));
		}
		return root;
	}
	
	private class FileTreeSelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			TreePath path = e.getPath();
			Object object = path.getLastPathComponent();
			if (!(object instanceof DefaultMutableTreeNode)) {
				return;
			}
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)object;
			Object userObject = treeNode.getUserObject();
			if (!(userObject instanceof File)) {
				return;
			}
			File f = (File)userObject;
			if (f.isDirectory()) {
				treeNode.removeAllChildren();
				File[] fs = f.listFiles(ImageFileFilter.INSTANCE_WITH_DIR);
				if (fs != null) {
					for (File cf : fs) {
						treeNode.add(new DefaultMutableTreeNode(cf));
					}
				}
				//右边显示图像
				File[] picFiles = f.listFiles(ImageFileFilter.INSTANCE_NO_DIR);
				FileTreePanel.this.mainWindow.picturesPanel.showPictures(picFiles);
			}
		}
		
	}
	
	private static class FileTreeCellRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			JLabel label = (JLabel)super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if (value instanceof DefaultMutableTreeNode) {
				Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
				if (userObject instanceof File) {
					File f = (File)userObject;
					if (f.getParentFile() == null) {
						label.setText(f.getPath());
					} else {
						label.setText(f.getName());
					}
					if (f.isDirectory()) {
						if (leaf && tree.isEnabled()) {
							label.setIcon(getClosedIcon());
						}
					}
				}
			}
			return label;
		}
	}

}
