package Frame;

import LayoutPackaging.*;
import Net.*;
import IconPackaging.*;
import Packaging.*;
import Packaging.search;
import School_office.GetScore;
import School_office.Office;
import School_office.Plan;
import Test.*;
import freemarker.template.TemplateException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
//import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
//import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//import javax.swing.event.HyperlinkListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import Crawling.CrawMain;
import Document.DocExport;
import Document.DocPreview;
import Document.PreviewDialog;
//import javafx.scene.layout.Border;
import Document.Recruit;
import Document.TableCellTextAreaRenderer;



public class AppFrame extends JFrame{

	private Container container;
	private JPanel menu;
	private JPanel area;
	private JScrollPane menuJscroll;
	private JScrollPane areaJscroll;
	
	//�ɼ���ѯģ�����&���ģ��
	private String name = "�����";
	private String major = "�������";
	private String college = "���ܿ�ѧ�빤��ѧԺ";
	private String stuNum = "2016052376";
	private JLabel timeLabelYear1 = new JLabel("��");
	private JLabel timeLabelMonth1 = new JLabel("��");
	private JLabel timeLabelDay1 = new JLabel("��");
	private JLabel timeLabelYear2 = new JLabel("��");
	private JLabel timeLabelMonth2 = new JLabel("��");
	private JLabel timeLabelDay2 = new JLabel("��");
	private JLabel arrowLabel = new JLabel("------");
	
	private String reasonStr;
	private String year1;
	private String year2;
	private String month1;
	private String month2;
	private String day1;
	private String day2;

	//����������
	private JTextField searchText;
	private JButton searchButton;
	private Object[][] tableData_result;
	private	int searchclickedRow=0;
	public String key="";
	

	//�������
	private Object[][] content;
	private Net net;
	private static AppFrame appFrame;
	private int listNum;
	private String listType;
	private int listNumMax;
	private int clickedRow;
	private HashMap<String, ArrayList<Recruit>> datacalendar;
	private Object[][] inforContent;
	private JTable inforTable;
	private String elec;
	
	public String Item = "������ѧԺ";
	private int index = 0;
	
	//++++++++������Ϣ��д�������++++++5/8�¼ӵ�
		TextField stu_id,stu_name,dorm_num,card_num,forum_account;
		//�����ı���
		JPasswordField uni_password,jwc_password,jnu_password,forum_password;
		//+++++++++++++
		
		//+++++++++++ѧ��������Ϣ����5/8�¼ӵ�
		private StuInfo stuInfo=new StuInfo();
		private StuInfoPreserve stuInfoPreserve = new StuInfoPreserve();
		//+++++++++++++
		

		//+++++++++++5/8�¼ӵ�
		public StuInfo getStuInfo() {
			return stuInfo;
		}

		public void setStuInfo(StuInfo stuInfo) {
			this.stuInfo = stuInfo;
		}
		//++++++++++++++++++

	private static Logger Appframe_log=Logger.getLogger(AppFrame.class);
	public void launch() {
		readStuInfo();
		this.setLayout(new GridBagLayout());
		this.setTitle("���ϴ�ѧѧ�����ܹ���ϵͳ");
		this.setIconImage(new ImageIcon("./images/jnuicon30.jpg").getImage());;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		container = this.getContentPane();

		addMenu();
		addArea();


		appFrame = this;
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//****************
	//editor�������             *
	//���ܣ������߲˵���   *
	//****************
	void addMenu() {
		menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setLayout(new GridBagLayout());
//		addSearch();
		addSpace();
		addGrade();
		addDocument();
		addMessage();
		addJob();
		addLife();
		addForum();
		addInformation();
		menuJscroll = new JScrollPane(menu);
		container.add(menuJscroll, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setAnchor(GBC.WEST));
	}

	//******************************
	//editor:zzf                   *
	//���ܣ�����ÿ��addΪ��߲˵�����ĸ�ѡ�� *
	//******************************
	void addSpace() {
		JPanel space = new JPanel();
		space.setBackground(null);
		menu.add(space, new GBC(GBC.REMAINDER, 2, 2).setFill(GBC.BOTH));
	}

	JPanel addSearch() {
		JPanel search = new JPanel();
		JPanel space = new JPanel();
		space.setMinimumSize(new Dimension(20, 40));
		search.setBackground(null);
		ImageIcon searchIcon = new ImageIcon("./images/search.png");
		searchButton = new JButton("����");
		searchButton.setSize(10,15);
		searchButton.setIcon(new IconSizeAdapt(searchIcon, searchButton).getAdaptIcon());
		searchButton.setContentAreaFilled(false);
		searchText = new JTextField(14);

		search.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//��������,editor:�¼���
				search searching=new search();
				key=searchText.getText();
				tableData_result=searching.find_searchresult(key,appFrame);
				//*****************
				listNumMax = 1;
				listType = "search";
				  area.removeAll(); 
				  area.add(informLayout(tableData_result),BorderLayout.CENTER); 
				  area.revalidate();

//				area.removeAll();
//				area.add(searchLayout(tableData_result), BorderLayout.CENTER);
//				area.revalidate();
				
			}
		});

		search.add(searchText);
		search.add(searchButton);
		search.add(space);
		search.setMaximumSize(new Dimension(80, 40));
//		addSpace();
//		menu.add(search, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
		return search;
	}

	void addGrade() {
//		JPanel grade = new JPanel();
		JTree gradeTree;
		DefaultMutableTreeNode gradeRoot;
		DefaultMutableTreeNode gradeCheck;
		DefaultMutableTreeNode lessonSelect;
		gradeRoot = new DefaultMutableTreeNode("�ɼ���ѯ");
		gradeCheck = new DefaultMutableTreeNode("��ѯ�ɼ�");
		lessonSelect = new DefaultMutableTreeNode("ѡ��");
		gradeRoot.add(gradeCheck);
		gradeRoot.add(lessonSelect);
		gradeTree = new JTree(gradeRoot);
		new TreeRootIcon(gradeTree, this);
//		grade.add(gradeTree);
		addSpace();
		menu.add(gradeTree, new GBC(GBC.REMAINDER, 5, 5).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addDocument() {
//		JPanel document = new JPanel();
		JTree documentTree;
		DefaultMutableTreeNode documentRoot;
		DefaultMutableTreeNode documentWrite;
		documentRoot = new DefaultMutableTreeNode("�ĵ��༭");
		documentWrite = new DefaultMutableTreeNode("�ĵ���д");
		documentRoot.add(documentWrite);
		documentTree = new JTree(documentRoot);
		new TreeRootIcon(documentTree, this);
//		document.add(documentTree);
		addSpace();
		menu.add(documentTree, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addMessage() {
//		JPanel message = new JPanel();
		JTree messageTree;
		DefaultMutableTreeNode messageRoot;
		DefaultMutableTreeNode messageCheck;
		messageRoot = new DefaultMutableTreeNode("֪ͨ");
		messageCheck = new DefaultMutableTreeNode("�鿴֪ͨ");
		messageRoot.add(messageCheck);
		messageTree = new JTree(messageRoot);
		new TreeRootIcon(messageTree, this);
//		message.add(messageTree);
		addSpace();
		menu.add(messageTree, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addJob() {
//		JPanel job = new JPanel();
		JTree jobTree;
		DefaultMutableTreeNode jobRoot;
		DefaultMutableTreeNode jobCheck;
		jobRoot = new DefaultMutableTreeNode("��ҵ");
		jobCheck = new DefaultMutableTreeNode("��ҵ��Ϣ��ѯ");
		jobRoot.add(jobCheck);
		jobTree = new JTree(jobRoot);
		new TreeRootIcon(jobTree, this);
//		job.add(jobTree); 
		addSpace();
		menu.add(jobTree, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addLife() {
//		JPanel life = new JPanel();
		JTree lifeTree;
		DefaultMutableTreeNode lifeRoot;
		DefaultMutableTreeNode card;
		DefaultMutableTreeNode elec;
		lifeRoot = new DefaultMutableTreeNode("����");
		card = new DefaultMutableTreeNode("У԰��");
		elec = new DefaultMutableTreeNode("ˮ��");
		lifeRoot.add(card);
		lifeRoot.add(elec);
		lifeTree = new JTree(lifeRoot);
		new TreeRootIcon(lifeTree, this);
//		life.add(lifeTree);
		addSpace();
		menu.add(lifeTree, new GBC(GBC.REMAINDER, 5, 5).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addForum() {
//		JPanel forum = new JPanel();
		JTree forumTree;
		DefaultMutableTreeNode forumRoot;
		DefaultMutableTreeNode forumCheck;
		forumRoot = new DefaultMutableTreeNode("��̳");
		forumCheck = new DefaultMutableTreeNode("�����̳");
		forumRoot.add(forumCheck);
		forumTree = new JTree(forumRoot);
		new TreeRootIcon(forumTree, this);
//		forum.add(forumTree);
		addSpace();
		menu.add(forumTree, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
	}

	void addInformation() {
//		JPanel information = new JPanel();
		JTree informationTree;
		DefaultMutableTreeNode informationRoot;
		DefaultMutableTreeNode informationManage;
		informationRoot = new DefaultMutableTreeNode("������Ϣ");
		informationManage = new DefaultMutableTreeNode("������Ϣ����");
		informationRoot.add(informationManage);
		informationTree = new JTree(informationRoot);
		new TreeRootIcon(informationTree, this);
//		information.add(informationTree);
		addSpace();
		menu.add(informationTree, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
		addSpace();
		addSpace();
		addSpace();
		addSpace();
		addSpace();
	}

	//����Ա�ڴ�area��JPanel�������Ĳ��֣�ע�⣬��Ҫֱ�������area���Լ��ٽ�һ������������������Լ��½����������������������area
	//****************
	//editor�������             *
	//���ܣ�����ұ���ʾ��    *
	//****************
	void addArea() {
		area = new JPanel();
		area.setLayout(new BorderLayout());
		area.setBackground(Color.WHITE);
		areaJscroll = new JScrollPane(area);
		container.add(areaJscroll, new GBC(1, 0, 10, 1).setFill(GBC.BOTH).setWeight(10, 1));
	}


	//*************************
	//editor���¼���                                    *
	//���ܣ�������Ϣ����������������    *
	//*************************
	boolean  true_false_flag=true;
	public JPanel infoLayout()
	{
		//TextField stu_id,stu_name,uni_password,jwc_password,jnu_password,dorm_num,card_num,forum_account,forum_password;
		
		stu_id = new TextField(70);
		stu_name = new TextField(70);
		uni_password = new JPasswordField(85);
		jwc_password = new JPasswordField(85);
		jnu_password= new JPasswordField(85);
		dorm_num = new TextField(70);
		card_num = new TextField(70);
		forum_account = new TextField(70);
		forum_password= new JPasswordField(85);
		
		//*********�����������Ϊ"*"***************//
		uni_password.setEchoChar('*');
		jwc_password.setEchoChar('*');
		jnu_password.setEchoChar('*');
		forum_password.setEchoChar('*');
		//*********�����������Ϊ"*"***************//

		JLabel t_stu_id,t_name,t_uni_password,t_jwc_password,t_jnu_password,t_dorm_num,t_card_num,t_forum_account,t_forum_password;
		t_stu_id=new JLabel("ѧ��        ");
		t_name=new JLabel("����        ");
		t_uni_password=new JLabel("ͳһ����    ");
		t_jwc_password=new JLabel("��������  ");
		t_jnu_password=new JLabel("�����ߴ�����");
		t_dorm_num=new JLabel("�����      ");
		t_card_num=new JLabel("ѧ��������  ");
		t_forum_account=new JLabel("��̳�˺�    ");
		t_forum_password=new JLabel("��̳����    ");

		JPanel jp_stu_id,jp_name,jp_uni_password,jp_jwc_password,jp_jnu_password,jp_dorm_num,jp_card_num,jp_forum_account,jp_forum_password;

		jp_stu_id=new JPanel();
		jp_name=new JPanel();
		jp_uni_password=new JPanel();
		jp_jwc_password=new JPanel();
		jp_jnu_password=new JPanel();
		jp_dorm_num=new JPanel();
		jp_card_num=new JPanel();
		jp_forum_account=new JPanel();
		jp_forum_password=new JPanel();

		jp_stu_id.add(t_stu_id);
		jp_stu_id.add(stu_id);
		jp_name.add(t_name);
		jp_name.add(stu_name);
		jp_uni_password.add(t_uni_password);
		jp_uni_password.add(uni_password);
		jp_jwc_password.add(t_jwc_password);
		jp_jwc_password.add(jwc_password);
		jp_jnu_password.add(t_jnu_password);
		jp_jnu_password.add(jnu_password);
		jp_dorm_num.add(t_dorm_num);
		jp_dorm_num.add(dorm_num);
		jp_card_num.add(t_card_num);
		jp_card_num.add(card_num);
		jp_forum_account.add(t_forum_account);
		jp_forum_account.add(forum_account);
		jp_forum_password.add(t_forum_password);
		jp_forum_password.add(forum_password);

		JPanel confirm_btns=new JPanel();
		JButton ensure_btn=new JButton("ȷ��");
		JButton cancel_btn=new JButton("ȡ��");
		confirm_btns.add(ensure_btn);
		confirm_btns.add(cancel_btn);

		JPanel jp_title=new JPanel();
		Font textsize = new Font("����",Font.PLAIN,35);
		JLabel title=new JLabel("������Ϣ");
		title.setFont(textsize);
		jp_title.add(title);

		Box box = Box.createVerticalBox();
		box.add(jp_title);
		box.add(jp_stu_id);
		box.add(jp_name);
		box.add(jp_uni_password);
		box.add(jp_jwc_password);
		box.add(jp_jnu_password);
		box.add(jp_dorm_num);
		box.add(jp_card_num);
		box.add(jp_forum_account);
		box.add(jp_forum_password);
		box.add(confirm_btns);

		JPanel info=new JPanel();
		info.add(box);
		
		
		stu_id.setText(stuInfo.getStuid());
		stu_name.setText(stuInfo.getStuname());
		uni_password.setText(stuInfo.getUnipassword());
		jwc_password.setText(stuInfo.getJwcpassword());
		jnu_password.setText(stuInfo.getJnupasssword());
		dorm_num.setText(stuInfo.getDormnum());
		card_num.setText(stuInfo.getCardnum());
		forum_account.setText(stuInfo.getForumaccount());
		forum_password.setText(stuInfo.getFornumpassword());
		
		
		ensure_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				
				String wrongstring="";
				int wrongaccout=0;
				stu_id.setForeground(Color.black);
				stu_name.setForeground(Color.black);
				uni_password.setForeground(Color.black);
				jwc_password.setForeground(Color.black);
				jnu_password.setForeground(Color.black);
				dorm_num.setForeground(Color.black);
				card_num.setForeground(Color.black);
				forum_account.setForeground(Color.black);
				forum_password.setForeground(Color.black);
				
				true_false_flag=true;
				StuInfo judgeInfo=new StuInfo();
				judgeInfo.setInfo_complete(true);
				judgeInfo.setStuid(stu_id.getText().toString());
				judgeInfo.setStuname(stu_name.getText().toString());
				judgeInfo.setUnipassword(uni_password.getText().toString());
				judgeInfo.setJwcpassword(jwc_password.getText().toString());
				judgeInfo.setJnupasssword(jnu_password.getText().toString());
				judgeInfo.setDormnum(dorm_num.getText().toString());
				judgeInfo.setCardnum(card_num.getText().toString());
				judgeInfo.setForumaccount(forum_account.getText().toString());
				judgeInfo.setFornumpassword(forum_password.getText().toString());
				setName(stu_name.getText().toString());
				setStuNum(stu_id.getText().toString());
				
				JudgeFormat judgeformat=new JudgeFormat();
				Map<String, Boolean> jubgemap = judgeformat.JInfoFormat(judgeInfo);
				if(!jubgemap.get("stuid"))
				{
					stu_id.setForeground(Color.red);
					wrongstring+="ѧ��\r\n";
					wrongaccout++;
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("stuname"))
				{
					stu_name.setForeground(Color.red);
					wrongstring+="����\r\n";
					wrongaccout++;
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("upw"))
				{
					uni_password.setForeground(Color.red);
					wrongstring+="ͳһ����\r\n";
					wrongaccout++;
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("jwcpw"))
				{
					jwc_password.setForeground(Color.red);
					wrongstring+="��������\r\n";
					wrongaccout++;
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("jnupw"))
				{
					jnu_password.setForeground(Color.red);
					wrongstring+="�����ߴ�����\r\n";
					wrongaccout++;
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("dormnum"))
				{
					dorm_num.setForeground(Color.red);
					wrongaccout++;
					wrongstring+="�����\r\n";
					judgeInfo.setInfo_complete(false);
				}
				if(!jubgemap.get("cardnum"))
				{
					wrongstring+="ѧ��������\r\n";
					wrongaccout++;
					card_num.setForeground(Color.red);
					judgeInfo.setInfo_complete(false);
				}
				
				if(!jubgemap.get("faccount"))
				{
					wrongstring+="��̳�˺�\r\n";
					wrongaccout++;
					forum_account.setForeground(Color.red);
					judgeInfo.setInfo_complete(false);
				}
				
				if(!jubgemap.get("fpw"))
				{
					wrongstring+="��̳����\r\n";
					wrongaccout++;
					forum_password.setForeground(Color.red);
					judgeInfo.setInfo_complete(false);
				}
				
				if(!judgeInfo.getInfo_complete())
				{
					JOptionPane.showMessageDialog(null, wrongstring+"����"+wrongaccout+"������", "������ʾ",JOptionPane.ERROR_MESSAGE);
				}else
				{
					judgeInfo.setInfo_complete(true);
					stuInfo=judgeInfo;
					JOptionPane.showMessageDialog(null, "����ɹ���");
					stuInfoPreserve.writeObjectToFile(stuInfo);
				}
			}
		});
		

		return info;
	}

	public void readStuInfo() {
		if(stuInfoPreserve.readObjectFromFile() != null) {
			stuInfo = (StuInfo)stuInfoPreserve.readObjectFromFile();
		}
	}
	

	public JScrollPane searchLayout(Object[][] tableData)
	{
		JPanel jp_search=new JPanel();
		JTable table;
		DefaultTableModel model = null;
		//�����ά������Ϊ�������
		//����һά������Ϊ�б���
		Object[] columnTitle = {"����" , "��������", " "};
		//�Զ�ά�����һά����������һ��JTable����
		model = new DefaultTableModel(tableData, columnTitle);
		table = new JTable(model);
		  
		table.getColumnModel().getColumn(0).setPreferredWidth(600);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.removeColumn(table.getColumnModel().getColumn(2));
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setBackground(new Color(102, 153, 204));
		cellRenderer.setForeground(Color.WHITE);
		cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		TableColumn column = table.getTableHeader().getColumnModel().getColumn(0);
		column.setHeaderRenderer(cellRenderer);
		column = table.getTableHeader().getColumnModel().getColumn(1);
		column.setHeaderRenderer(cellRenderer);
//		column = table.getTableHeader().getColumnModel().getColumn(2);
//		column.setHeaderRenderer(cellRenderer);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);
		//������ռ��
//		table.getColumnModel().getColumn(0).setPreferredWidth(200);
//		table.getColumnModel().getColumn(1).setPreferredWidth(50);
//		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		//�����־�����ʾ
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		
		table.setPreferredScrollableViewportSize(new Dimension(700,300));//���ñ��ĳ���
		table.setRowHeight(30);//�����п�
		
		JScrollPane JSP= new JScrollPane(table);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!tableData[searchclickedRow][2].equals(""))
				{
				searchclickedRow = table.rowAtPoint(arg0.getPoint());
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					appFrame.getArea().removeAll();
					new searchExplorer(appFrame ,String.valueOf(tableData[searchclickedRow][2]),tableData);
					appFrame.getArea().revalidate();
				}else {
					
					JOptionPane.showMessageDialog(null, "δ��������ת���鿴֪ͨ����ȡ��ϸ��Ϣ", "������ʾ",JOptionPane.ERROR_MESSAGE);
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�޷�����url,��ת��֪ͨ���鿴", "������ʾ",JOptionPane.ERROR_MESSAGE);
				}
				
				System.out.println("--------");
			}
		});
		
		return JSP;

	}


	//**********************
	//editor��zzf           *
	//���ܣ���ѯ�ɼ������ѡ�ν���  *
	//**********************
	public JPanel gradesearchLayout() {
		Object[] columnTitle={"ѧ��","ѧ��","�γ�����" ,"ѧ��","�ɼ�","����","��������","��������","�γ����","��ѧ���","ȡ����"};
		Object[][] tabledata= new Object[50][columnTitle.length];
		
		
		JComboBox choose_semaster=new JComboBox();
		choose_semaster.addItem("��һ��");
		choose_semaster.addItem("��һ��");
		choose_semaster.addItem("�����");
		choose_semaster.addItem("�����");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		choose_semaster.setSelectedIndex(4);
		JButton query = new JButton("��ѯ");
		
		JPanel grade_search_panel = new JPanel(new BorderLayout());
		JPanel title_info = new JPanel(new BorderLayout());	
		JTable gradeTable = new JTable(tabledata, columnTitle);
		
		
		
		
		GetScore getScore=new GetScore(stuInfo.getStuid(), stuInfo.getJwcpassword(),0);
		org.jsoup.nodes.Document doc=getScore.getScoredoc();
		
		Office result=new Office();
		String grade_time=choose_semaster.getSelectedItem().toString();
	
		
		//System.out.println("grade��ֵ�ǣ�"+grade_time);
		grade_search_panel.validate();
		grade_search_panel.repaint();
		String[] grade_message=result.grade_message("������",doc);	
		int row=0;
		for(int i=0;i<grade_message.length/11-1;i++){
			for(int j=0;j<11;j++){
				
				tabledata[i][j]=grade_message[row];
				row++;
			}	
		}
		
				
		
		
		JPanel buttonSet = new JPanel();
		
		//������
		ImageIcon jnuIcon = new ImageIcon("./images/jnuicon40.jpg");
		JLabel classLabel = new JLabel("�ɼ���ѯ");
		JLabel nameLabel = new JLabel("����:" + name);
		JLabel majorLabel = new JLabel("רҵ:" + major);
		JLabel collegeLabel = new JLabel("ѧԺ:" + college);
		Box rightLabel = Box.createVerticalBox();
		rightLabel.add(nameLabel);
		rightLabel.add(majorLabel);
		rightLabel.add(collegeLabel);
		
		classLabel.setFont(new Font("����", Font.PLAIN, 40));
		classLabel.setSize(new Dimension(0, 40));
//		classLabel.setPreferredSize(new Dimension(0, 40));
		classLabel.setIcon(new IconSizeAdapt(jnuIcon, classLabel).getAdaptIcon());
		JPanel space = new JPanel();
		space.setBackground(Color.WHITE);
		space.setPreferredSize(new Dimension(0, 40));
		
		JPanel classPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel space1 = new JPanel();
		JPanel space2 = new JPanel();
		space1.setBackground(Color.WHITE);
		space2.setBackground(Color.WHITE);
		space1.setPreferredSize(new Dimension(20, 40));
		space2.setPreferredSize(new Dimension(40, 40));
		classPanel.add(space1, BorderLayout.WEST);
		classPanel.add(classLabel, BorderLayout.CENTER);
		rightPanel.add(rightLabel, BorderLayout.CENTER);
		rightPanel.add(space2, BorderLayout.EAST);
		classPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		
		title_info.add(classPanel, BorderLayout.WEST);
		title_info.add(space, BorderLayout.CENTER);
		title_info.add(rightPanel, BorderLayout.EAST);
		title_info.setBackground(Color.WHITE);
		
		//��ʼ���б��ʽ
		gradeTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		gradeTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		gradeTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		gradeTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		gradeTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		gradeTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(102, 153, 204));
        cellRenderer.setForeground(Color.WHITE);
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        int count = 0;
        for(Object object : columnTitle) {
        	TableColumn column = gradeTable.getTableHeader().getColumnModel().getColumn(count);
       
        	column.setHeaderRenderer(cellRenderer);
        	count++;
        }
		
		gradeTable.getTableHeader().setResizingAllowed(false);
		gradeTable.getTableHeader().setReorderingAllowed(false);
 
		gradeTable.setRowHeight(30);
		gradeTable.setGridColor(new Color(157, 103, 62));
		
		JScrollPane gradeJscroll = new JScrollPane(gradeTable);
		gradeJscroll.revalidate();
		
		//buttonSet
		JLabel term = new JLabel("ѧ��");
		
	
		JButton checkMethod = new JButton("�鿴�˲���������");
		buttonSet.add(term);
		buttonSet.add(choose_semaster);
		buttonSet.add(query);
		buttonSet.add(checkMethod);
		
		grade_search_panel.add(title_info, BorderLayout.NORTH);
		grade_search_panel.add(gradeJscroll, BorderLayout.CENTER);
		grade_search_panel.add(buttonSet, BorderLayout.SOUTH);
		
		//��ѯ��ť����¼�
		query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				grade_search_panel.validate();
				grade_search_panel.repaint();
				for(int i=0;i<30;i++){
					for(int j=0;j<11;j++){
						tabledata[i][j]="";
					}
				}
				Office result=new Office();
				String grade_time=choose_semaster.getSelectedItem().toString();
				//System.out.println("grade��ֵ�ǣ�"+grade_time);
				String[] grade_message=result.grade_message(grade_time,doc);	
				int row=0;
				for(int i=0;i<grade_message.length/11-1;i++){
					for(int j=0;j<11;j++){
						
						tabledata[i][j]=grade_message[row];
						row++;
					}	
				}
				
				
			}
		});
		
		
		

		checkMethod.addActionListener(new ActionListener() {
			
			Plan plan=new Plan();
			Object[] columnTitle2={"�γ����","�γ̱��","�γ�����","ѧ����","��ѧʱ","ѧ��","֪ʶȺ","�ѻ�ɼ�"};
			Object[][] tabledata2= new Object[100][columnTitle2.length];
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				grade_search_panel.remove(title_info);
				grade_search_panel.remove(buttonSet);
				grade_search_panel.remove(gradeJscroll);
				
				JTable jTable=new JTable(tabledata2,columnTitle2);
				jTable.setRowHeight(40);
				//ҳ�沼������
				grade_search_panel.add(jTable,BorderLayout.CENTER);
				grade_search_panel.validate();
				JLabel classLabel2 = new JLabel("�˲�����������Ϣ");
				classLabel2.setFont(new Font("����", Font.PLAIN, 40));
				classLabel2.setSize(new Dimension(0, 40));
				classLabel2.setIcon(new IconSizeAdapt(jnuIcon, classLabel2).getAdaptIcon());
				JPanel classPanel2 = new JPanel(new BorderLayout());
				classPanel2.add(classLabel2, BorderLayout.CENTER);
				grade_search_panel.add(classPanel2, BorderLayout.NORTH);			
				
				//��ȡ �˲�����������Ϣ
				Document doc1=getScore.getTrain();
				Document doc2=getScore.getSelectTrain(2);
				Document doc3=getScore.getSelectTrain(3);
				Document doc4=getScore.getSelectTrain(4);
				Document doc5=getScore.getSelectTrain(5);
				Document doc6=getScore.getSelectTrain(6);
				//Document doc7=getScore.getSelectTrain(7);			
				Plan plan=new Plan();
				String content1[]=new String[plan.pStrings(doc1).length];
				String content2[]=new String[plan.pStrings(doc2).length];
				String content3[]=new String[plan.pStrings(doc3).length];
				String content4[]=new String[plan.pStrings(doc4).length];
				String content5[]=new String[plan.pStrings(doc5).length];
				String content6[]=new String[plan.pStrings(doc6).length];
				//String content7[]=new String[plan.pStrings(doc7).length];
				content1=plan.pStrings(doc1);
				content2=plan.pStrings(doc2);
				content3=plan.pStrings(doc3);
				content4=plan.pStrings(doc4);
				content5=plan.pStrings(doc5);
				content6=plan.pStrings(doc6);
				//content7=plan.pStrings(doc7);
				
				String[] content=new String[content1.length-8+content2.length-8+content3.length-8+content4.length-8+content5.length-8+content6.length];
				System.arraycopy(content1, 0, content,0,content1.length-8);
				System.arraycopy(content2, 0, content,content1.length-8,content2.length-8);
				System.arraycopy(content3, 0, content,content1.length-8+content2.length-8,content3.length-8);
				System.arraycopy(content4, 0, content,content1.length-8+content2.length-8+content3.length-8,content4.length-8);
				System.arraycopy(content5, 0, content,content1.length-8+content2.length-8+content3.length-8+content4.length-8,content5.length);
				System.arraycopy(content6, 0, content,content1.length-8+content2.length-8+content3.length-8+content4.length-8+content5.length-8,content6.length);
				//System.arraycopy(content7, 0, content,content1.length-8+content2.length-8+content3.length-8+content4.length-8+content5.length-8+content6.length-8,content7.length);
				
				//����Ϣ�������
				tabledata2[0][0]="�γ����";
				tabledata2[0][1]="�γ̱��";
				tabledata2[0][2]="�γ�����";
				tabledata2[0][3]="ѧ����";
				tabledata2[0][4]="��ѧʱ";
				tabledata2[0][5]="ѧ��";
				tabledata2[0][6]="֪ʶȺ";
				tabledata2[0][7]="�ѻ�ɼ�";
				
				int i=1,j=0;
				
				for (int k = 0; k < content.length; k++) {
					
					if(content[k].equals("���޿�")||content[k].equals("ѡ�޿�")||content[k].equals("����ѡ�޿�")){
						tabledata2[i][j]=content[k];
						j++;
						k++;
						while(content[k].equals("���޿�")==false&&content[k].equals("ѡ�޿�")==false&&content[k].equals("����ѡ�޿�")==false){
							tabledata2[i][j]=content[k];
							//System.out.println("i=="+i+"���Ǳ������");
							
							k++;
							if(k==8)
								break;
							j++;
							if(j==8)
								break;
						}
			
						k--;
					}
					i++;	
					j=0;
				}
				
				jTable.setVisible(true);
				grade_search_panel.setVisible(true);
				grade_search_panel.validate();	
				
			}
		});
	
		
		
		return grade_search_panel;
	}



	public JPanel chooseClassLayout() {

		Object[] columnTitle={"���","�γ̱��","�γ�" ,"ѧ��","��ѧ","���","ʱ�䰲��","��ʦ","�Ͽεص�","��ע","����ʱ��","ѡ��"};
		Object[][] tabledata= new Object[20][columnTitle.length];

		JPanel choose_class_panel = new JPanel(new BorderLayout());
		JPanel title_info = new JPanel(new BorderLayout());
		JTable classTable = new JTable(tabledata, columnTitle);
		JPanel buttonSet = new JPanel();

		//������
		ImageIcon jnuIcon = new ImageIcon("./images/jnuicon40.jpg");
		JLabel classLabel = new JLabel("ѡ��ϵͳ");
		JLabel nameLabel = new JLabel("����:" + name);
		JLabel majorLabel = new JLabel("רҵ:" + major);
		JLabel collegeLabel = new JLabel("ѧԺ:" + college);
		Box rightLabel = Box.createVerticalBox();
		rightLabel.add(nameLabel);
		rightLabel.add(majorLabel);
		rightLabel.add(collegeLabel);
		
		JPanel classPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel space = new JPanel();
		JPanel space1 = new JPanel();
		JPanel space2 = new JPanel();
		space.setPreferredSize(new Dimension(0, 40));
		space.setBackground(Color.WHITE);
		space1.setPreferredSize(new Dimension(20, 40));
		space1.setBackground(Color.WHITE);
		space2.setPreferredSize(new Dimension(40, 40));
		space2.setBackground(Color.WHITE);
		
		classLabel.setFont(new Font("����", Font.PLAIN, 40));
		classLabel.setSize(40, 40);
		classLabel.setIcon(new IconSizeAdapt(jnuIcon, classLabel).getAdaptIcon());
		classPanel.add(space1, BorderLayout.WEST);
		classPanel.add(classLabel, BorderLayout.CENTER);
		rightPanel.add(rightLabel, BorderLayout.CENTER);
		rightPanel.add(space2, BorderLayout.EAST);
		classPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		
		
		title_info.add(classPanel, BorderLayout.WEST);
		title_info.add(space, BorderLayout.CENTER);
		title_info.add(rightPanel, BorderLayout.EAST);
		title_info.setBackground(Color.WHITE);
	
		//��ʼ���б��ʽ
		classTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		classTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		classTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		classTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		classTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		classTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		classTable.getColumnModel().getColumn(6).setPreferredWidth(80);
		classTable.getColumnModel().getColumn(7).setPreferredWidth(110);
		classTable.getColumnModel().getColumn(8).setPreferredWidth(160);
		classTable.getColumnModel().getColumn(9).setPreferredWidth(230);
		classTable.getColumnModel().getColumn(10).setPreferredWidth(70);
		classTable.getColumnModel().getColumn(11).setPreferredWidth(40);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(102, 153, 204));
        cellRenderer.setForeground(Color.WHITE);
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        int count = 0;
        for(Object object : columnTitle) {
        	TableColumn column = classTable.getTableHeader().getColumnModel().getColumn(count);
        	column.setHeaderRenderer(cellRenderer);
        	count++;
        }
		
		classTable.getTableHeader().setResizingAllowed(false);
		classTable.getTableHeader().setReorderingAllowed(false);
 
		classTable.setRowHeight(30);
		classTable.setGridColor(new Color(157, 103, 62));
		
		JScrollPane classJscroll = new JScrollPane(classTable);
		
		//�·��Զ��������ð�ť
		JButton button = new JButton("�Զ�������Ϣ����");
		buttonSet.add(button);
		
		choose_class_panel.add(title_info, BorderLayout.NORTH);
		choose_class_panel.add(classJscroll, BorderLayout.CENTER);
		choose_class_panel.add(buttonSet, BorderLayout.SOUTH);
		
		return choose_class_panel;
	}
	

	//**********************
	//editor��zzf           *
	//���ܣ��ĵ��༭����       	    *
	//**********************
	public JPanel documentLayout() {
		JPanel docu = new JPanel(new BorderLayout());
		
		//ģ���б�
		String[] head = {"ģ��"};
		String[][] docuList = {
								{"�������"},
								{"ѡ������"},
								{"����ѧ������"}
								};
		JTable docuTable = new JTable(docuList, head);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(102, 153, 204));
        cellRenderer.setForeground(Color.WHITE);
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		TableColumn column = docuTable.getTableHeader().getColumnModel().getColumn(0);
    	column.setHeaderRenderer(cellRenderer);
		docuTable.getTableHeader().setResizingAllowed(false);
		docuTable.getTableHeader().setReorderingAllowed(false);
		docuTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		
		JScrollPane docuMenu = new JScrollPane(docuTable);
		docuMenu.setPreferredSize(new Dimension(150, 0));
		
		//ģ������
		Box docuArea = Box.createVerticalBox();
		//ģ������-��ٸ�����Ϣ
		
		Box left = Box.createVerticalBox();
		Box right= Box.createVerticalBox();
		
		JLabel stuNumLabel = new JLabel("ѧ��:" + stuNum);
		JLabel nameLabel = new JLabel("����:" + name);
		JLabel collegeLabel = new JLabel("ѧԺ:" + college);
		JLabel majorLabel = new JLabel("רҵ:" + major);
		
		left.add(nameLabel);
		left.add(collegeLabel);
		right.add(stuNumLabel);
		right.add(majorLabel);
		
		Box personInfor = Box.createHorizontalBox();
		JPanel space = new JPanel();
		space.setMaximumSize(new Dimension(20, 0));
		personInfor.add(left);
		personInfor.add(space);
		personInfor.add(right);
		
		
		//ģ������-���ԭ��
		JPanel reason = new JPanel(new BorderLayout());
		JLabel reasonLabel = new JLabel("���ԭ��:");
		JTextArea reasonText = new JTextArea();
		reasonText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		reason.add(reasonLabel, BorderLayout.WEST);
		reason.add(reasonText, BorderLayout.CENTER);
		reason.setBackground(Color.WHITE);
		
		//ģ������-���ԭ��
		JPanel time = new JPanel();
		JLabel timeLabel = new JLabel("���ʱ��:");
		JTextField timeTextYear1 = new JTextField(5);
		JTextField timeTextYear2 = new JTextField(5);

		JTextField timeTextMonth1 = new JTextField(3);
		JTextField timeTextMonth2 = new JTextField(3);

		JTextField timeTextDay1 = new JTextField(3);
		JTextField timeTextDay2 = new JTextField(3);
		
		time.add(timeLabel);
		time.add(timeTextYear1);
		time.add(timeLabelYear1);
		time.add(timeTextMonth1);
		time.add(timeLabelMonth1);
		time.add(timeTextDay1);
		time.add(timeLabelDay1);
		time.add(arrowLabel);
		time.add(timeTextYear2);
		time.add(timeLabelYear2);
		time.add(timeTextMonth2);
		time.add(timeLabelMonth2);
		time.add(timeTextDay2);
		time.add(timeLabelDay2);
		time.setBackground(Color.WHITE);
		
		//ģ������-��ť
		JPanel button = new JPanel();
		JButton preview = new JButton("Ԥ��");
preview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reasonStr=reasonText.getText().toString();
				year1=timeTextYear1.getText().toString();
				year2=timeTextYear2.getText().toString();
				month1=timeTextMonth1.getText().toString();
				month2=timeTextMonth2.getText().toString();
				day1=timeTextDay1.getText().toString();
				day2=timeTextDay2.getText().toString();
				//��ģ���д���html
				try {
					DocPreview dp=new DocPreview(appFrame);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				try {
					PreviewDialog dialog = new PreviewDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
			}
		});
			
		JButton export = new JButton("����");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȡ������е�����
				reasonStr=reasonText.getText().toString();
				year1=timeTextYear1.getText().toString();
				year2=timeTextYear2.getText().toString();
				month1=timeTextMonth1.getText().toString();
				month2=timeTextMonth2.getText().toString();
				day1=timeTextDay1.getText().toString();
				day2=timeTextDay2.getText().toString();
//				System.out.println("year1 : " + year1);
				try {
					DocExport export=new DocExport(appFrame);
				} catch (IOException | TemplateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button.add(preview);
		button.add(export);
		button.setBackground(Color.WHITE);
		
		docuArea.add(personInfor);
		docuArea.add(reason);
		docuArea.add(time);
		docuArea.add(button);
		
		docu.add(docuMenu, BorderLayout.WEST);
		docu.add(docuArea, BorderLayout.CENTER);
		docu.setBackground(Color.WHITE);
		return docu;
	}
	

	//**********************
	//editor��zzf           *
	//���ܣ��鿴֪ͨ����       	    *
	//**********************
	public JPanel informLayout(Object[][] newcontent) {
		Box title = Box.createHorizontalBox();
		JButton schoolBut = new JButton("ѧУ����");
		JButton insideinfoBut = new JButton("У��֪ͨ");
		JButton meetingBut = new JButton("ÿ�ܻ���");
		JButton academicBut = new JButton("ѧ������");
		JButton officeBut = new JButton("����");
		JButton collegeBut = new JButton("ѧԺ֪ͨ");
		schoolBut.setContentAreaFilled(false);
		insideinfoBut.setContentAreaFilled(false);
		meetingBut.setContentAreaFilled(false);
		academicBut.setContentAreaFilled(false);
		officeBut.setContentAreaFilled(false);
		collegeBut.setContentAreaFilled(false);
		
		schoolBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-ѧУ����");
				//System.out.println("�鿴֪ͨ-ѧУ����");
				iniContent();
				listNum = 1;
				listType = "infor_sch";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		insideinfoBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-У��֪ͨ");
				//System.out.println("�鿴֪ͨ-У��֪ͨ");
				iniContent();
				listNum = 1;
				listType = "infor_schIn";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		meetingBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-ÿ�ܻ���");
				//System.out.println("�鿴֪ͨ-ÿ�ܻ���");
				iniContent();
				listNum = 1;
				listType = "infor_meeting";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		academicBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-ѧ������");
				//System.out.println("�鿴֪ͨ-ѧ������");
				iniContent();
				listNum = 1;
				listType = "infor_lec";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		
		officeBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-����");
				//System.out.println("�鿴֪ͨ-ѧ������");
				iniContent();
				listNum = 1;
				listType = "infor_office";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		collegeBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴֪ͨ-ѧԺ֪ͨ");
				//System.out.println("�鿴֪ͨ-ѧ������");
				iniContent();
				listNum = 1;
				switch (Item) {
				case "������ѧԺ":
					listType = "college_business";
					break;

				case "����ѧԺ":
					listType = "college_translation";
					break;
					
				case "����ѧԺ":
					listType = "college_Humanities";
					break;
				}
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		JPanel space = new JPanel();
		
		

		title.add(schoolBut);
		title.add(insideinfoBut);
		title.add(meetingBut);
		title.add(academicBut);
		title.add(officeBut);
		title.add(collegeBut);
		title.add(space);
		title.add(addSearch());
		title.setPreferredSize(new Dimension(0, 40));
		
		String[] head = {"����", "��������", ""};
//		String[][] content = {
//								{"", ""},
//								{"", ""}
//							};
		
		JTable informTable = new JTable(newcontent, head);
		informTable.getColumnModel().getColumn(0).setPreferredWidth(800);
		informTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		informTable.removeColumn(informTable.getColumnModel().getColumn(2));
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(102, 153, 204));
        cellRenderer.setForeground(Color.WHITE);
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		TableColumn column = informTable.getTableHeader().getColumnModel().getColumn(0);
    	column.setHeaderRenderer(cellRenderer);
    	column = informTable.getTableHeader().getColumnModel().getColumn(1);
    	column.setHeaderRenderer(cellRenderer);
		informTable.getTableHeader().setResizingAllowed(false);
		informTable.getTableHeader().setReorderingAllowed(false);
		informTable.setRowHeight(25);
		informTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickedRow = informTable.rowAtPoint(arg0.getPoint());
				Appframe_log.info("�鿴֪ͨ-��"+ listNum + "ҳ����" + (clickedRow+1) + "��");
				//System.out.println("�鿴֪ͨ-��"+ listNum + "ҳ����" + (clickedRow+1) + "��");
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					appFrame.getArea().removeAll();
					new ItemPreserve(listType, appFrame, clickedRow);
					new Explorer(appFrame ,String.valueOf(newcontent[clickedRow][2]));
					appFrame.getArea().revalidate();
				}else {
					appFrame.getArea().removeAll();
					JEditorPane editorPane = new JEditorPane();
					editorPane.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(editorPane);
//					editorPane.addHyperlinkListener( this);
					String filePath = "./data/" + listType + "_page" + listNum + "/" + "page_" + listNum + "_" + (clickedRow+1) + ".html";
					File file = new File(filePath);
					String path = file.getAbsolutePath();
					path = "file:///" + path;
					
					//System.out.println(path);
//					String path = String.valueOf(content[clickedRow][2]);
					try {
						editorPane.setPage(path);
						JButton back = new JButton("����");
						back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Backbutton(appFrame);
							}
						});
						appFrame.getArea().add(scrollPane, BorderLayout.CENTER);
						appFrame.getArea().add(back, BorderLayout.SOUTH);
						appFrame.getArea().revalidate();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "���������ļ������ڣ���ȡʧ��", "�����ļ�����", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
				}
				System.out.println("--------");
			}
		});
		
//		TableColumnModel tableColumnModel = informTable.getColumnModel();
//		TableColumn tableColumn = tableColumnModel.getColumn(2);
//		informTable.removeColumn(tableColumn);
		
		JScrollPane informScroll = new JScrollPane(informTable);
		
		JButton prev = new JButton("��һҳ");
		JButton next = new JButton("��һҳ");
		JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.debug("�����һҳ");
				//System.out.println("��һҳ");
				iniContent();
				if(listNum > 1) {
					listNum--;
					net = new Net("http://www.baidu.com");
					if(net.getConnect()) {
						new CrawMain(listType, appFrame, true);
					}else {
						new CrawMain(listType, appFrame, false);
					}
					appFrame.getArea().removeAll();
					appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
					appFrame.getArea().revalidate();
				}else {
					JOptionPane.showMessageDialog(null, "���ڵ�һҳ", "��ʾ", JOptionPane.WARNING_MESSAGE);
					Appframe_log.warn("���ڵ�һҳ");
				}
				System.out.println("--------");
			}
		});
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.debug("�����һҳ");
				//System.out.println("��һҳ");
				iniContent();
				if(listNum < listNumMax) {
					listNum++;
					net = new Net("http://www.baidu.com");
					if(net.getConnect()) {
						new CrawMain(listType, appFrame, true);
					}else {
						new CrawMain(listType, appFrame, false);
					}
					appFrame.getArea().removeAll();
					appFrame.getArea().add(informLayout(content), BorderLayout.CENTER);
					appFrame.getArea().revalidate();
				}else {
					JOptionPane.showMessageDialog(null, "�������һҳ", "��ʾ", JOptionPane.WARNING_MESSAGE);
					Appframe_log.warn("�������һҳ");
				}
				System.out.println("--------");
			}
		});

		
		//���ѡ���
				JComboBox choose_college=new JComboBox();
				choose_college.addItem("������ѧԺ");
				choose_college.addItem("����ѧԺ");
				choose_college.addItem("����ѧԺ");
				choose_college.addItem("���ܿ�ѧ�빤��ѧԺ");
				choose_college.addItem("��װѧԺ");

				choose_college.setSelectedIndex(index);
				//item��ѡ�е�ѧԺstring
				choose_college.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					index=((JComboBox)e.getSource()).getSelectedIndex();//��ȡ��ѡ���������
					Item=((JComboBox)e.getSource()).getSelectedItem().toString();//��ȡ����
					collegeBut.doClick();
					}
				});
				
		
		JLabel listNumLabel = new JLabel(listNum + "/" + listNumMax);
		button.add(prev);
		button.add(listNumLabel);
		button.add(next);
		button.add(choose_college);
		
		JPanel informArea = new JPanel(new BorderLayout());
		
		informArea.add(title, BorderLayout.NORTH);
		informArea.add(informScroll, BorderLayout.CENTER);
		informArea.add(button, BorderLayout.SOUTH);
		
		return informArea;
	}

	//****************
	//editor��zzf     *
	//���ܣ���ҵ��Ϣ��ѯ     *
	//****************
	public JPanel workinfoLayout() {
		Box title = Box.createHorizontalBox();
		JButton newBut = new JButton("���Ŷ�̬");
		JButton inforBut = new JButton("֪ͨ���");
		JButton hotPointBut = new JButton("��Ƹ�ȵ�");
		JButton politicalBut = new JButton("���߽��");
		newBut.setContentAreaFilled(false);
		inforBut.setContentAreaFilled(false);
		hotPointBut.setContentAreaFilled(false);
		politicalBut.setContentAreaFilled(false);
		
		newBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("�鿴��ҵ��Ϣ-���Ŷ�̬");
				//System.out.println("�鿴��ҵ��Ϣ-���Ŷ�̬");
				iniContent();
				listNum = 1;
				listType = "work_new";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		inforBut.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Appframe_log.info("�鿴��ҵ��Ϣ-֪ͨ���");
				//System.out.println("�鿴��ҵ��Ϣ-֪ͨ���");
				iniContent();
				listNum = 1;
				listType = "work_ad";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		hotPointBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Appframe_log.info("�鿴��ҵ��Ϣ-��Ƹ�ȵ�");
				//System.out.println("�鿴��ҵ��Ϣ-��Ƹ�ȵ�");
				iniContent50();//��������ҳĬ��һҳ50�У�������Ҫ����content
				listNum = 1;
				listType = "work_hotpoint";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		politicalBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Appframe_log.info("�鿴��ҵ��Ϣ-���߽��");
				//System.out.println("�鿴��ҵ��Ϣ-���߽��");
				iniContent();
				listNum = 1;
				listType = "work_policy";
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					new CrawMain(listType, appFrame, true);
				}else {
					new CrawMain(listType, appFrame, false);
				}
				appFrame.getArea().removeAll();
				appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
				appFrame.getArea().revalidate();
				System.out.println("--------");
			}
		});
		
		title.add(newBut);
		title.add(inforBut);
		title.add(hotPointBut);
		title.add(politicalBut);
		title.setPreferredSize(new Dimension(0, 40));
		
		String[] head = {"����", "��������", ""};
//		String[][] content = {
//								{"", ""},
//								{"", ""}
//							};
		
		JTable informTable = new JTable(content, head);
		informTable.getColumnModel().getColumn(0).setPreferredWidth(700);
		informTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		informTable.removeColumn(informTable.getColumnModel().getColumn(2));
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(102, 153, 204));
        cellRenderer.setForeground(Color.WHITE);
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		TableColumn column = informTable.getTableHeader().getColumnModel().getColumn(0);
    	column.setHeaderRenderer(cellRenderer);
    	column = informTable.getTableHeader().getColumnModel().getColumn(1);
    	column.setHeaderRenderer(cellRenderer);
		informTable.getTableHeader().setResizingAllowed(false);
		informTable.getTableHeader().setReorderingAllowed(false);
		informTable.setRowHeight(25);
		informTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickedRow = informTable.rowAtPoint(arg0.getPoint());
				Appframe_log.info("�鿴֪ͨ-��"+ listNum + "ҳ����" + (clickedRow+1) + "��");
				//System.out.println("�鿴֪ͨ-��"+ listNum + "ҳ����" + (clickedRow+1) + "��");
				net = new Net("http://www.baidu.com");
				if(net.getConnect()) {
					appFrame.getArea().removeAll();
					new ItemPreserve(listType, appFrame, clickedRow);
					new Explorer(appFrame ,String.valueOf(content[clickedRow][2]));
					appFrame.getArea().revalidate();
				}else {
					appFrame.getArea().removeAll();
					JEditorPane editorPane = new JEditorPane();
					editorPane.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(editorPane);
//					editorPane.addHyperlinkListener( this);
					String filePath = "./data/" + listType + "_page" + listNum + "/" + "page_" + listNum + "_" + (clickedRow+1) + ".html";
					File file = new File(filePath);
					String path = file.getAbsolutePath();
					path = "file:///" + path;
					Appframe_log.info("·����:"+path);
					System.out.println(path);
//					String path = String.valueOf(content[clickedRow][2]);
					try {
						editorPane.setPage(path);
						JButton back = new JButton("����");
						back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new Backbutton(appFrame);
							}
						});
						appFrame.getArea().add(scrollPane, BorderLayout.CENTER);
						appFrame.getArea().add(back, BorderLayout.SOUTH);
						appFrame.getArea().revalidate();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "���������ļ������ڣ���ȡʧ��", "�����ļ�����", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
				}
				System.out.println("--------");
			}
		});
		
		JScrollPane informScroll = new JScrollPane(informTable);
		
		Box rightArea = Box.createVerticalBox();
		
		JLabel calendarLabel = new JLabel("��Ƹ����");
		calendarLabel.setSize(new Dimension(0, 12));
		calendarLabel.setIcon(new IconSizeAdapt(new ImageIcon("./images/calendar.png"), calendarLabel).getAdaptIcon());
//		System.out.println(calendarLabel.getFont().getSize());

		Chooser chooser = Chooser.getInstance();
		JScrollPane calendarScroll = new JScrollPane(chooser.getPanel());
		calendarScroll.setPreferredSize(new Dimension(240,185));
//		calendarScroll.setMaximumSize(new Dimension(220, 165));
		
		//lql
		String[] inforHead = {"��Ƹ��Ϣ"};
		inforContent = new Object[10][1];
		
		inforTable = new JTable(inforContent, inforHead);
		column = inforTable.getTableHeader().getColumnModel().getColumn(0);
    	column.setHeaderRenderer(cellRenderer);
    	inforTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
    	
		inforTable.getTableHeader().setResizingAllowed(false);
		inforTable.getTableHeader().setReorderingAllowed(false);
		inforTable.setRowHeight(80);
		JScrollPane inforScroll = new JScrollPane(inforTable);
		
		//lql
		rightArea.add(calendarLabel);
		rightArea.add(calendarScroll);
		rightArea.add(inforScroll);
		rightArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		rightArea.setPreferredSize(new Dimension(240, 0));
		
		JButton prev = new JButton("��һҳ");
		JButton next = new JButton("��һҳ");
		JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("��һҳ");
				//System.out.println("��һҳ");
				iniContent();
				if(listNum > 1) {
					listNum--;
					net = new Net("http://www.baidu.com");
					if(net.getConnect()) {
						new CrawMain(listType, appFrame, true);
					}else {
						new CrawMain(listType, appFrame, false);
					}
					appFrame.getArea().removeAll();
					appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
					appFrame.getArea().revalidate();
				}else {
					JOptionPane.showMessageDialog(null, "���ڵ�һҳ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				System.out.println("--------");
			}
		});
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Appframe_log.info("��һҳ");
				//System.out.println("��һҳ");
				if(listType.equals("work_hotpoint")) {
					iniContent50();
				}else {
					iniContent();
				}
				if(listNum < listNumMax) {
					listNum++;
					net = new Net("http://www.baidu.com");
					if(net.getConnect()) {
						new CrawMain(listType, appFrame, true);
					}else {
						new CrawMain(listType, appFrame, false);
					}
					appFrame.getArea().removeAll();
					appFrame.getArea().add(workinfoLayout(), BorderLayout.CENTER);
					appFrame.getArea().revalidate();
				}else {
					JOptionPane.showMessageDialog(null, "�������һҳ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				System.out.println("--------");
			}
		});
		
		JLabel listNumLabel = new JLabel(listNum + "/" + listNumMax);
		
		button.add(prev);
		button.add(listNumLabel);
		button.add(next);
		
		JPanel informArea = new JPanel(new BorderLayout());
		informArea.add(rightArea, BorderLayout.EAST);
		informArea.add(title, BorderLayout.NORTH);
		informArea.add(informScroll, BorderLayout.CENTER);
		informArea.add(button, BorderLayout.SOUTH);
		informArea.setPreferredSize(new Dimension(0, 0));
		
		return informArea;
	}

	//*************
	//editor������  *
	//���ܣ������ѯ    *
	//*************
	public Box campusCardLayout() {
		//�¼���
		Box box_show_all = Box.createVerticalBox();
		
		Box box_stuinfo=Box.createVerticalBox();
		
		Font small_textsize = new Font("����",Font.PLAIN,20);
		Font big_textsize = new Font("����",Font.PLAIN,30);
		
		Box box_jnu_name=Box.createHorizontalBox();
		JLabel jnu_name=new JLabel("���ϴ�ѧ");
		jnu_name.setFont(big_textsize);
		box_jnu_name.add(jnu_name);
		box_jnu_name.add(Box.createHorizontalGlue());
		
		box_stuinfo.add(box_jnu_name);
		
		Box box_stu_name_card=Box.createHorizontalBox();
		JLabel stu_name_card=new JLabel();
		stu_name_card.setText("�޹���123456");
		stu_name_card.setFont(small_textsize);
		box_stu_name_card.add(stu_name_card);
		box_stu_name_card.add(Box.createHorizontalGlue());
		
		box_stuinfo.add(Box.createVerticalStrut(10));
		box_stuinfo.add(box_stu_name_card);
		
		Box box_title=Box.createHorizontalBox();
		ImageIcon icon=new ImageIcon("./images/jnuicon30.jpg");
		icon.setImage(icon.getImage().getScaledInstance(70, 70,
				Image.SCALE_DEFAULT));
		JLabel jnu_pic=new JLabel(icon);
		box_title.add(Box.createHorizontalStrut(10));
		box_title.add(jnu_pic);
		box_title.add(box_stuinfo);
		
		Box box_remamin=Box.createHorizontalBox();
		JLabel remamin_txt=new JLabel("���:");
		remamin_txt.setFont(small_textsize);
		JLabel remamin_show=new JLabel();
		remamin_show.setFont(small_textsize);
		remamin_show.setText("5");
		box_remamin.add(Box.createHorizontalStrut(10));
		box_remamin.add(remamin_txt);
		box_remamin.add(remamin_show);
		box_remamin.add(Box.createHorizontalGlue());
		
		Box box_addmoney=Box.createHorizontalBox();
		JLabel addmoney_txt=new JLabel("��ֵ���:");
		addmoney_txt.setFont(small_textsize);
		TextField addmoney=new TextField(20);
		addmoney.setPreferredSize(new Dimension(200, 30));
		addmoney.setMaximumSize(new Dimension(200,30));
		JButton btn_addmoney=new JButton("ȷ��");
		box_addmoney.add(Box.createHorizontalStrut(10));
		box_addmoney.add(addmoney_txt);
		box_addmoney.add(addmoney);
		box_addmoney.add(Box.createHorizontalStrut(10));
		box_addmoney.add(btn_addmoney);
		box_addmoney.add(Box.createHorizontalGlue());
		
		box_show_all.add(Box.createVerticalStrut(10));
		box_show_all.add(box_title);
		box_show_all.add(Box.createVerticalStrut(10));
		box_show_all.add(box_remamin);
		box_show_all.add(Box.createVerticalStrut(10));
		box_show_all.add(box_addmoney);
		box_show_all.add(Box.createVerticalStrut(10));
		
		box_show_all.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JPanel space1 = new JPanel();
		space1.setPreferredSize(new Dimension(200, 10));
		space1.setMinimumSize(new Dimension(200, 10));;
		JPanel space2 = new JPanel();
		space2.setPreferredSize(new Dimension(200, 10));
		space2.setMinimumSize(new Dimension(200, 10));
		JPanel space3 = new JPanel();
		space3.setPreferredSize(new Dimension(10, 200));
		space3.setMinimumSize(new Dimension(10, 200));
		
		JPanel space4 = new JPanel();
		space4.setPreferredSize(new Dimension(10,200));
		space4.setMinimumSize(new Dimension(10, 200));
	        
		Box hor = Box.createHorizontalBox();
		Box ver = Box.createVerticalBox();
		
		hor.add(space1);
		hor.add(box_show_all);
		hor.add(space2);
		
		ver.setPreferredSize(new Dimension(0,0));
		ver.add(space3);
		ver.add(hor);
		ver.add(space4);
		
		return ver;
	
	}
	
	public Box shuidianLayout() {
		//�¼���
		Box box_show_all = Box.createVerticalBox();
		
		JTextField water_billadd,electric_billadd;
		JLabel water,electric;
		JLabel water_bill,electric_bill;
		JLabel dorm;
		JLabel dorm_num;
		JButton btn_wateradd,btn_electricadd;
		
		water_billadd=new JTextField(40);
		water_billadd.setPreferredSize(new Dimension(200, 30));
		water_billadd.setMaximumSize(new Dimension(200,30));
		electric_billadd=new JTextField(40);
		electric_billadd.setPreferredSize(new Dimension(200, 30));
		electric_billadd.setMaximumSize(new Dimension(200, 30));
		
		water=new JLabel("ˮ�ѣ�");
		electric=new JLabel("��ѣ�");
		Font small_textsize = new Font("����",Font.PLAIN,20);
		water.setFont(small_textsize);
		electric.setFont(small_textsize);
		
		water_bill=new JLabel();
		electric_bill=new JLabel();
		water_bill.setText("30");
		electric_bill.setText(elec);
		water_bill.setFont(small_textsize);
		electric_bill.setFont(small_textsize);
		
		dorm=new JLabel("����ţ�");
		dorm_num=new JLabel();
		dorm_num.setText("4531");
		Font textsize = new Font("����",Font.PLAIN,30);
		dorm.setFont(textsize);
		dorm_num.setFont(textsize);
		
		btn_wateradd=new JButton("ˮ�ѳ�ֵ");
		btn_electricadd=new JButton("��ѳ�ֵ");
		
		Box box_title = Box.createHorizontalBox();
		box_title.add(Box.createHorizontalStrut(10));
		ImageIcon icon=new ImageIcon("./images/jnuicon30.jpg");
		icon.setImage(icon.getImage().getScaledInstance(50, 50,
				Image.SCALE_DEFAULT));
		JLabel jnu_pic=new JLabel(icon);
		box_title.add(jnu_pic);
		box_title.add(dorm);
		box_title.add(dorm_num);
		box_title.add(Box.createHorizontalGlue());
		
		Box box_waterbill = Box.createHorizontalBox();
		box_waterbill.add(Box.createHorizontalStrut(10));
		box_waterbill.add(water);
		box_waterbill.add(water_bill);
		box_waterbill.add(Box.createHorizontalGlue());
		
		Box box_electricbill = Box.createHorizontalBox();
		box_electricbill.add(Box.createHorizontalStrut(10));
		box_electricbill.add(electric);
		box_electricbill.add(electric_bill);
		box_electricbill.add(Box.createHorizontalGlue());
		
		Box box_wateradd = Box.createHorizontalBox();
		box_wateradd.add(Box.createHorizontalStrut(10));
		box_wateradd.add(water_billadd);
		box_wateradd.add(btn_wateradd);
		box_wateradd.add(Box.createHorizontalGlue());
		
		Box box_electricadd = Box.createHorizontalBox();
		box_electricadd.add(Box.createHorizontalStrut(10));
		box_electricadd.add(electric_billadd);
		box_electricadd.add(btn_electricadd);
		box_electricadd.add(Box.createHorizontalGlue());
		
		box_show_all.add(Box.createVerticalStrut(15));
		box_show_all.add(box_title);
		box_show_all.add(Box.createVerticalStrut(15));
		box_show_all.add(box_waterbill);
		box_show_all.add(Box.createVerticalStrut(15));
		box_show_all.add(box_electricbill);
		box_show_all.add(Box.createVerticalStrut(15));
		box_show_all.add(box_wateradd);
		box_show_all.add(Box.createVerticalStrut(15));
		box_show_all.add(box_electricadd);
		box_show_all.add(Box.createVerticalStrut(15));
		
		box_show_all.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JPanel space1 = new JPanel();
		space1.setPreferredSize(new Dimension(200, 10));
		space1.setMinimumSize(new Dimension(200, 10));;
		JPanel space2 = new JPanel();
		space2.setPreferredSize(new Dimension(200, 10));
		space2.setMinimumSize(new Dimension(200, 10));
		JPanel space3 = new JPanel();
		space3.setPreferredSize(new Dimension(10, 200));
		space3.setMinimumSize(new Dimension(10, 200));
		
		JPanel space4 = new JPanel();
		space4.setPreferredSize(new Dimension(10,200));
		space4.setMinimumSize(new Dimension(10, 200));
		
		Box hor = Box.createHorizontalBox();
		Box ver = Box.createVerticalBox();
		
		hor.add(space1);
		hor.add(box_show_all);
		hor.add(space2);
		
		ver.setPreferredSize(new Dimension(0,0));
		ver.add(space3);
		ver.add(hor);
		ver.add(space4);
		
		return ver;
		
		
	}
	
	
	
	public void iniContent() {
		content = new Object[30][3];
	}
	
	public void iniContent50() {
		content = new Object[50][3];
	}

	//********************
	//editor��zzf         *
	//���ܣ�getter&setter *
	//********************
	public JPanel getArea() {
		return area;
	}

	public JScrollPane getAreaJscroll() {
		return areaJscroll;
	}

	public JTextField getSearchText() {
		return searchText;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public Object[][] getTableData_result() {
		return tableData_result;
	}

	public Object[][] getContent() {
		return content;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public void setListNumMax(int listNumMax) {
		this.listNumMax = listNumMax;
	}

	public String getListType() {
		return listType;
	}
	
	//��ȡѧ��
		public String getStuNum() {
			return stuNum;
		}
		//��ȡ����
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public void setStuNum(String stuNum) {
			this.stuNum = stuNum;
		}

		//��ȡרҵ
		public String getMajor() {
			return major;
		}
		//��ȡѧԺ
		public String getCollege() {
			return college;
		}
		//��ȡԭ��
		public String getReasonStr() {
			return reasonStr;
		}
		
		public void setReasonStr(String reasonStr) {
			this.reasonStr = reasonStr;
		}

		//��ȡʱ��
		public String getYear1() {
			return year1;
		}

		public String getYear2() {
			return year2;
		}

		public String getMonth1() {
			return month1;
		}

		public String getMonth2() {
			return month2;
		}

		public void setMajor(String major) {
			this.major = major;
		}

		public void setCollege(String college) {
			this.college = college;
		}

		public String getDay1() {
			return day1;
		}

		public String getDay2() {
			return day2;
		}
		
		
		
		
		public void setYear1(String year1) {
			this.year1 = year1;
		}

		public void setYear2(String year2) {
			this.year2 = year2;
		}

		public void setMonth1(String month1) {
			this.month1 = month1;
		}

		public void setMonth2(String month2) {
			this.month2 = month2;
		}

		public void setDay1(String day1) {
			this.day1 = day1;
		}

		public void setDay2(String day2) {
			this.day2 = day2;
		}

		public HashMap<String, ArrayList<Recruit>> getdatacalendar() {
			if(datacalendar==null) {
				datacalendar=new HashMap<>();
			}
			return datacalendar;
		}
		public Object[][] getInforContent() {
			return inforContent;
		}
		public static AppFrame getAppFrame() {
			return appFrame;
		}
		public JTable getInforTable() {
			return inforTable;
		}
		
		public int getListNumMax() {
			return listNumMax;		
		}

		public String getElec() {
			return elec;
		}

		public void setElec(String elec) {
			this.elec = elec;
		}
}
