import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeRootIcon {
	private JTree jTree;
	private AppFrame appFrame;
	public TreeRootIcon(JTree jTree,AppFrame appFrame) {
		this.jTree = jTree;
		this.appFrame = appFrame;
		jTree.setToggleClickCount(1);
		addMouseListener();
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
		ImageIcon iconRight = new ImageIcon("./images/RightArrow.png");
		Image imageRight = iconRight.getImage().getScaledInstance(cellRenderer.getFont().getSize(), cellRenderer.getFont().getSize(), iconRight.getImage().SCALE_DEFAULT);
		iconRight = new ImageIcon(imageRight);
		ImageIcon iconDown = new ImageIcon("./images/DownArrow.png");
		Image imageDown = iconDown.getImage().getScaledInstance(cellRenderer.getFont().getSize(), cellRenderer.getFont().getSize(), iconDown.getImage().SCALE_DEFAULT);
		iconDown = new ImageIcon(imageDown);
		cellRenderer.setClosedIcon(iconRight);
		cellRenderer.setOpenIcon(iconDown);
		cellRenderer.setLeafIcon(null);
	}
	
	//*****************************
	//editor�������                                               *
	//���ܣ����ڵ���������Ӧʱ����ɼ��ز���*
	//*****************************
	private void addMouseListener() {
		jTree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				JTree tree = (JTree)e.getSource();
//				int treeRow = tree.getRowForLocation(e.getX(), e.getY());
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
				String parentstr = "";
				String nodestr = node.toString();
				if(parent != null) {
					parentstr = parent.toString();
				}
				switch (parentstr) {
					case "�ɼ���ѯ":
						switch (nodestr) {
							case "��ѯ�ɼ�":
								System.out.println("��ѯ�ɼ�");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.gradesearchLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
		
							case "ѡ��":
								System.out.println("ѡ��");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.chooseClassLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
					case "�ĵ��༭":
						switch (nodestr) {
							case "�ĵ���д":
								System.out.println("�ĵ���д");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.documentLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
						
					case "֪ͨ":
						switch (nodestr) {
							case "�鿴֪ͨ":
								System.out.println("�鿴֪ͨ");
								break;
						}
						break;
						
					case "��ҵ":
						switch (nodestr) {
							case "��ҵ��Ϣ��ѯ":
								System.out.println("��ҵ��Ϣ��ѯ");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.workinfoLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
						}
						break;
						
					case "����":
						switch (nodestr) {
							case "У԰��":
								System.out.println("У԰��");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.campusCardLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
	
							case "ˮ��":
								System.out.println("ˮ��");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.shuidianLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
						}
						
						break;
						
					case "��̳":
						switch (nodestr) {
							case "�����̳":
								System.out.println("�����̳");
								appFrame.getArea().removeAll();
								new Forum(appFrame.getArea());
								appFrame.getArea().revalidate();
								break;
						}
						break;
						
					case "������Ϣ":
						switch (nodestr) {
							case "������Ϣ����":
								System.out.println("������Ϣ����");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.infoLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
					default:
						break;
				}
//				System.out.println(treeRow);
				System.out.println("--------");
			
			}
		});
				
	}
	
}
