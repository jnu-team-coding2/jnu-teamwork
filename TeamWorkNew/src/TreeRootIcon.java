import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

//**********************
//editor：zzf           *
//功能：初始化树图标       	    *
//**********************
public class TreeRootIcon {
	private JTree jTree;
	private AppFrame appFrame;
	public TreeRootIcon(JTree jTree,AppFrame appFrame) {
		this.jTree = jTree;
		this.appFrame = appFrame;
		jTree.setToggleClickCount(1);
		jTree.setBackground(null);
		addMouseListener();
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
		ImageIcon iconadd = new ImageIcon("./images/add.png");
		Image imageadd = iconadd.getImage().getScaledInstance(cellRenderer.getFont().getSize(), cellRenderer.getFont().getSize(), iconadd.getImage().SCALE_DEFAULT);
//		System.out.println(cellRenderer.getFont().getSize());
		iconadd = new ImageIcon(imageadd);
//		ImageIcon iconDown = new ImageIcon("./images/DownArrow.png");
//		Image imageDown = iconDown.getImage().getScaledInstance(cellRenderer.getFont().getSize(), cellRenderer.getFont().getSize(), iconDown.getImage().SCALE_DEFAULT);
//		iconDown = new ImageIcon(imageDown);
		cellRenderer.setBackground(null);
		cellRenderer.setOpenIcon(iconadd);
		cellRenderer.setClosedIcon(iconadd);
		cellRenderer.setLeafIcon(null);
	}
	
	//*****************************
	//editor：张泽锋                                               *
	//功能：树节点添加鼠标响应时间完成加载布局*
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
					case "成绩查询":
						switch (nodestr) {
							case "查询成绩":
								System.out.println("查询成绩");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.gradesearchLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
		
							case "选课":
								System.out.println("选课");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.chooseClassLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
					case "文档编辑":
						switch (nodestr) {
							case "文档填写":
								System.out.println("文档填写");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.documentLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
						
					case "通知":
						switch (nodestr) {
							case "查看通知":
								System.out.println("查看通知");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.informLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
								break;
						}
						break;
						
					case "就业":
						switch (nodestr) {
							case "就业信息查询":
								System.out.println("就业信息查询");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.workinfoLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
						}
						break;
						
					case "生活":
						switch (nodestr) {
							case "校园卡":
								System.out.println("校园卡");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.campusCardLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
	
							case "水电":
								System.out.println("水电");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.shuidianLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
								break;
						}
						
						break;
						
					case "论坛":
						switch (nodestr) {
							case "浏览论坛":
								System.out.println("浏览论坛");
								appFrame.getArea().removeAll();
								new Forum(appFrame.getArea());
								appFrame.getArea().revalidate();
								break;
						}
						break;
						
					case "个人信息":
						switch (nodestr) {
							case "个人信息管理":
								System.out.println("个人信息管理");
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
