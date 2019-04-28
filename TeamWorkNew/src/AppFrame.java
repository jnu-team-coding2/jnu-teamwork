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
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;

import javafx.scene.layout.Border;



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

	//*********************
	//editor:�¼���
	JTextField searchText;
	JButton searchButton;
	Object[][] tableData_result;
	//*******************

	void launch() {
		this.setLayout(new GridBagLayout());
		this.setTitle("���ϴ�ѧѧ�����ܹ���ϵͳ");
		this.setIconImage(new ImageIcon("./images/jnuicon30.jpg").getImage());;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		container = this.getContentPane();

		addMenu();
		addArea();

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
		addSearch();
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

	void addSearch() {
		JPanel search = new JPanel();
		search.setBackground(null);
		ImageIcon searchIcon = new ImageIcon("./images/search.png");
		searchButton = new JButton("����");
		searchButton.setSize(10,15);
		searchButton.setIcon(new IconSizeAdapt(searchIcon, searchButton).getAdaptIcon());
		searchButton.setContentAreaFilled(false);
		searchText = new JTextField(8);

		search.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//��������,editor:�¼���
				search searching=new search();
				tableData_result=searching.find_searchresult(searchText.getText());
				//*****************

				area.removeAll();
				area.add(searchLayout(tableData_result), BorderLayout.CENTER);
				area.revalidate();
			}
		});

		search.add(searchText);
		search.add(searchButton);
		addSpace();
		menu.add(search, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH).setAnchor(GBC.WEST));
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
	JPanel infoLayout()
	{
		TextField stu_id,name,uni_password,jwc_password,jnu_password,dorm_num,card_num,forum_account,forum_password;

		stu_id = new TextField(70);
		name = new TextField(70);
		uni_password = new TextField(70);
		jwc_password = new TextField(70);
		jnu_password= new TextField(70);
		dorm_num = new TextField(70);
		card_num = new TextField(70);
		forum_account = new TextField(70);
		forum_password= new TextField(70);

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
		jp_name.add(name);
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

		return info;
	}


	JScrollPane searchLayout(Object[][] tableData)
	{
		JPanel jp_search=new JPanel();
		JTable table;
		DefaultTableModel model = null;
		//�����ά������Ϊ�������
		//����һά������Ϊ�б���
		Object[] columnTitle = {"����" , "������λ" , "��������"};
		//�Զ�ά�����һά����������һ��JTable����
		model = new DefaultTableModel(tableData, columnTitle);
		table = new JTable(model);
		  
		table.getColumnModel().getColumn(0).setPreferredWidth(600);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setBackground(new Color(102, 153, 204));
		cellRenderer.setForeground(Color.WHITE);
		cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		TableColumn column = table.getTableHeader().getColumnModel().getColumn(0);
		column.setHeaderRenderer(cellRenderer);
		column = table.getTableHeader().getColumnModel().getColumn(1);
		column.setHeaderRenderer(cellRenderer);
		column = table.getTableHeader().getColumnModel().getColumn(2);
		column.setHeaderRenderer(cellRenderer);
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
		
		return JSP;

	}


	//**********************
	//editor��zzf           *
	//���ܣ���ѯ�ɼ������ѡ�ν���  *
	//**********************
	JPanel gradesearchLayout() {
		Object[] columnTitle={"�γ̺�","�γ���","ѧ��" ,"ѧ��","�ɼ�","����"};
		Object[][] tabledata= new Object[30][columnTitle.length];
		JPanel grade_search_panel = new JPanel(new BorderLayout());
		JPanel title_info = new JPanel(new BorderLayout());
		JTable gradeTable = new JTable(tabledata, columnTitle);
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

		//buttonSet
		JLabel term = new JLabel("ѧ��");
		JComboBox choose_semaster=new JComboBox();
		choose_semaster.addItem("  ");
		choose_semaster.addItem("��һ��");
		choose_semaster.addItem("��һ��");
		choose_semaster.addItem("�����");
		choose_semaster.addItem("�����");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		choose_semaster.addItem("������");
		JButton query = new JButton("��ѯ");
		JButton checkMethod = new JButton("�鿴�˲���������");
		buttonSet.add(term);
		buttonSet.add(choose_semaster);
		buttonSet.add(query);
		buttonSet.add(checkMethod);
		
		grade_search_panel.add(title_info, BorderLayout.NORTH);
		grade_search_panel.add(gradeJscroll, BorderLayout.CENTER);
		grade_search_panel.add(buttonSet, BorderLayout.SOUTH);
		
		
		return grade_search_panel;
	}

	JPanel chooseClassLayout() {

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
	JPanel documentLayout() {
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
		JButton export = new JButton("����");
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
	JPanel informLayout() {
		Box title = Box.createHorizontalBox();
		JButton schoolBut = new JButton("ѧУ����");
		JButton insideinfoBut = new JButton("У��֪ͨ");
		JButton meetingBut = new JButton("ÿ�ܻ���");
		JButton academicBut = new JButton("ѧ������");
		schoolBut.setContentAreaFilled(false);
		insideinfoBut.setContentAreaFilled(false);
		meetingBut.setContentAreaFilled(false);
		academicBut.setContentAreaFilled(false);
		
		title.add(schoolBut);
		title.add(insideinfoBut);
		title.add(meetingBut);
		title.add(academicBut);
		title.setPreferredSize(new Dimension(0, 40));
		
		String[] head = {"����", "��������"};
		String[][] content = {
								{"", ""},
								{"", ""}
							};
		
		JTable informTable = new JTable(content, head);
		informTable.getColumnModel().getColumn(0).setPreferredWidth(800);
		informTable.getColumnModel().getColumn(1).setPreferredWidth(100);
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
		
		JScrollPane informScroll = new JScrollPane(informTable);
		
		JPanel informArea = new JPanel(new BorderLayout());
		
		informArea.add(title, BorderLayout.NORTH);
		informArea.add(informScroll, BorderLayout.CENTER);
		
		return informArea;
	}

	//****************
	//editor��zzf     *
	//���ܣ���ҵ��Ϣ��ѯ     *
	//****************
	JPanel workinfoLayout() {
		Box title = Box.createHorizontalBox();
		JButton newBut = new JButton("ѧУ����");
		JButton inforBut = new JButton("У��֪ͨ");
		JButton hotPointBut = new JButton("ÿ�ܻ���");
		JButton politicalBut = new JButton("ѧ������");
		newBut.setContentAreaFilled(false);
		inforBut.setContentAreaFilled(false);
		hotPointBut.setContentAreaFilled(false);
		politicalBut.setContentAreaFilled(false);
		
		title.add(newBut);
		title.add(inforBut);
		title.add(hotPointBut);
		title.add(politicalBut);
		title.setPreferredSize(new Dimension(0, 40));
		
		String[] head = {"����", "��������"};
		String[][] content = {
								{"", ""},
								{"", ""}
							};
		
		JTable informTable = new JTable(content, head);
		informTable.getColumnModel().getColumn(0).setPreferredWidth(700);
		informTable.getColumnModel().getColumn(1).setPreferredWidth(100);
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
		
		JScrollPane informScroll = new JScrollPane(informTable);
		
		Box rightArea = Box.createVerticalBox();
		
		JLabel calendarLabel = new JLabel("��Ƹ����");
		calendarLabel.setSize(new Dimension(0, 12));
		calendarLabel.setIcon(new IconSizeAdapt(new ImageIcon("./images/calendar.png"), calendarLabel).getAdaptIcon());
		System.out.println(calendarLabel.getFont().getSize());

		Chooser chooser = Chooser.getInstance();
		JScrollPane calendarScroll = new JScrollPane(chooser.getPanel());
		calendarScroll.setPreferredSize(new Dimension(220,165));
//		calendarScroll.setMaximumSize(new Dimension(220, 165));
		
		String[] inforHead = {"��Ƹ��Ϣ"};
		String[][] inforContent = {
									{""},
									{""},
		};
		JTable inforTable = new JTable(inforContent, inforHead);
		column = inforTable.getTableHeader().getColumnModel().getColumn(0);
    	column.setHeaderRenderer(cellRenderer);
		inforTable.getTableHeader().setResizingAllowed(false);
		inforTable.getTableHeader().setReorderingAllowed(false);
		inforTable.setRowHeight(80);
		JScrollPane inforScroll = new JScrollPane(inforTable);
		
		rightArea.add(calendarLabel);
		rightArea.add(calendarScroll);
		rightArea.add(inforScroll);
		rightArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		rightArea.setPreferredSize(new Dimension(220, 0));
		
		JPanel informArea = new JPanel(new BorderLayout());
		informArea.add(rightArea, BorderLayout.EAST);
		informArea.add(title, BorderLayout.NORTH);
		informArea.add(informScroll, BorderLayout.CENTER);
		informArea.setPreferredSize(new Dimension(0, 0));
		
		return informArea;
	}

	//*************
	//editor������  *
	//���ܣ������ѯ    *
	//*************
	Box campusCardLayout() {
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
	
	Box shuidianLayout() {
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
		electric_bill.setText("20");
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

}
