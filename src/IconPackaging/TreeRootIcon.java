package IconPackaging;

import Frame.AppFrame;
import Net.Net;
import Packaging.ElecRead;
import Net.Explorer;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.log4j.Logger;

import Crawling.CrawMain;

//**********************
//editor：zzf           *
//功能：初始化树图标       	    *
//**********************
public class TreeRootIcon {
	private JTree jTree;
	private AppFrame appFrame;
	private Net net;
	
	private static Logger select_log=Logger.getLogger(TreeRootIcon.class);
	
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
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击查询成绩");
								//System.out.println("查询成绩");
								appFrame.iniContent();
								net = new Net("https://jwxt.jnu.edu.cn/");
								if(net.getConnect()) {
									appFrame.getArea().removeAll();
									appFrame.getArea().add(appFrame.gradesearchLayout(), BorderLayout.CENTER);
									appFrame.getArea().revalidate();
								}
//								break;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
	
						case "选课":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击选课");
								//System.out.println("选课");
								appFrame.iniContent();
								net = new Net("https://jwxt.jnu.edu.cn/");
								if(net.getConnect()) {
									appFrame.getArea().removeAll();
									appFrame.getArea().add(appFrame.chooseClassLayout(), BorderLayout.CENTER);
									appFrame.getArea().revalidate();
								}
//								break;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
					}
					break;
					
				case "文档编辑":
//					if(appFrame.getStuInfo().getInfo_complete())
//					{
					switch (nodestr) {
						case "文档填写":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击文档填写");
								//System.out.println("文档填写");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.documentLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
//								break;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
					}
					break;
//					}
					
				case "通知":
					switch (nodestr) {
						case "查看通知":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击查看通知");
								//System.out.println("查看通知");
								appFrame.iniContent();
								appFrame.setListNum(1);
								appFrame.setListType("infor_sch");
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									new CrawMain("infor_sch", appFrame, true);
								}else {
									new CrawMain("infor_sch", appFrame, false);
								}
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.informLayout(appFrame.getContent()), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
					}
					break;
					
				case "就业":
					switch (nodestr) {
						case "就业信息查询":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击就业信息查询");
								//System.out.println("就业信息查询");
								appFrame.iniContent();
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									new CrawMain("work_new", appFrame, true);
									//lql
									new CrawMain("calendar", appFrame, true);
									//lql
								}else {
									new CrawMain("work_new", appFrame, false);
									//lql
									new CrawMain("calendar", appFrame, false);
									//lql
								}
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.workinfoLayout(), BorderLayout.CENTER);
								appFrame.revalidate();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
					}
					break;
					
					
					
				case "生活":
					switch (nodestr) {
						case "校园卡":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击校园卡");
								//System.out.println("校园卡");
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									appFrame.getArea().removeAll();
									appFrame.getArea().add(appFrame.campusCardLayout(), BorderLayout.CENTER);
									appFrame.revalidate();
								}
//								break;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;

						case "水电":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击查询水电");
								//System.out.println("水电");
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									try {
										new ElecRead(appFrame);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									appFrame.getArea().removeAll();
									appFrame.getArea().add(appFrame.shuidianLayout(), BorderLayout.CENTER);
									appFrame.revalidate();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
						}
						break;
					
				case "论坛":
					switch (nodestr) {
						case "浏览论坛":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("点击浏览论坛");
								//System.out.println("浏览论坛");
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									appFrame.getArea().removeAll();
									new Explorer(appFrame, "http://bbs.jnlts.com/");
									appFrame.getArea().revalidate();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "个人信息未填写完全", "错误提示",JOptionPane.ERROR_MESSAGE);
								select_log.error("个人信息未填写完全");
							}
							break;
					}
					break;
					
				case "个人信息":
					switch (nodestr) {
						case "个人信息管理":
							select_log.debug("点击个人信息管理");
							//System.out.println("个人信息管理");
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
