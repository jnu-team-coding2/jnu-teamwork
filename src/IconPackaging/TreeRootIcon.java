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
//editor��zzf           *
//���ܣ���ʼ����ͼ��       	    *
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
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("�����ѯ�ɼ�");
								//System.out.println("��ѯ�ɼ�");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
	
						case "ѡ��":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("���ѡ��");
								//System.out.println("ѡ��");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
					}
					break;
					
				case "�ĵ��༭":
//					if(appFrame.getStuInfo().getInfo_complete())
//					{
					switch (nodestr) {
						case "�ĵ���д":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("����ĵ���д");
								//System.out.println("�ĵ���д");
								appFrame.getArea().removeAll();
								appFrame.getArea().add(appFrame.documentLayout(), BorderLayout.CENTER);
								appFrame.getArea().revalidate();
//								break;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
					}
					break;
//					}
					
				case "֪ͨ":
					switch (nodestr) {
						case "�鿴֪ͨ":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("����鿴֪ͨ");
								//System.out.println("�鿴֪ͨ");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
					}
					break;
					
				case "��ҵ":
					switch (nodestr) {
						case "��ҵ��Ϣ��ѯ":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("�����ҵ��Ϣ��ѯ");
								//System.out.println("��ҵ��Ϣ��ѯ");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
					}
					break;
					
					
					
				case "����":
					switch (nodestr) {
						case "У԰��":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("���У԰��");
								//System.out.println("У԰��");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;

						case "ˮ��":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("�����ѯˮ��");
								//System.out.println("ˮ��");
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
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
						}
						break;
					
				case "��̳":
					switch (nodestr) {
						case "�����̳":
							if(appFrame.getStuInfo().getInfo_complete())
							{
								select_log.debug("��������̳");
								//System.out.println("�����̳");
								net = new Net("http://www.baidu.com");
								if(net.getConnect()) {
									appFrame.getArea().removeAll();
									new Explorer(appFrame, "http://bbs.jnlts.com/");
									appFrame.getArea().revalidate();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "������Ϣδ��д��ȫ", "������ʾ",JOptionPane.ERROR_MESSAGE);
								select_log.error("������Ϣδ��д��ȫ");
							}
							break;
					}
					break;
					
				case "������Ϣ":
					switch (nodestr) {
						case "������Ϣ����":
							select_log.debug("���������Ϣ����");
							//System.out.println("������Ϣ����");
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
