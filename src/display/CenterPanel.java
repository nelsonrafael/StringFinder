package display;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class CenterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;

	public CenterPanel() {
		super(new GridLayout(1, 0));

		rootNode = new DefaultMutableTreeNode("Results");

		treeModel = new DefaultTreeModel(rootNode);

		tree = new JTree(treeModel);

		tree.setEditable(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);

		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}

	public String getCurrentNode() throws Exception {
		String result = "";

		TreePath currentSelection = tree.getSelectionPath();

		result = currentSelection.toString();

		String[] parts = result.split(",");
		result = "";

		for (int i = 1; i < parts.length; i++) {
			String temp = parts[i].trim();
			result += temp;
			if (i < parts.length - 1)
				result += "\\";
			else
				result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	public void clear() {
		rootNode.removeAllChildren();
		treeModel.reload();
	}

	public void removeNode(DefaultMutableTreeNode node) {
		((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

}
