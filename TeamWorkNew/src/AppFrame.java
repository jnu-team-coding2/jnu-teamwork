import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class AppFrame extends JFrame{

	private Container container;
	private JPanel menu;
	private JPanel area;

	//*********************
	//editor:�¼���
	JTextField searchText;
	JButton searchButton;
	Object[][] tableData_result;
	//*******************

	void launch() {
		this.setLayout(new GridBagLayout());
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
		container.add(menu, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setAnchor(GBC.WEST));
	}

	//******************************
	//editor:zzf                   *
	//���ܣ�����ÿ��addΪ��߲˵�����ĸ�ѡ�� *
	//******************************
	void addSpace() {
		JPanel space = new JPanel();
		space.setBackground(Color.WHITE);
		menu.add(space, new GBC(GBC.REMAINDER, 2, 2).setFill(GBC.BOTH));
	}

	void addSearch() {
		JPanel search = new JPanel();
		search.setBackground(Color.WHITE);
		ImageIcon searchIcon = new ImageIcon("./images/search.png");
		searchButton = new JButton("����");
		searchButton.setSize(10,15);
		searchButton.setIcon(new IconSizeAdapt(searchIcon, searchButton).getAdaptIcon());
		searchButton.setContentAreaFilled(false);
		searchText = new JTextField(8);


		search.setLayout(new FlowLayout());
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
		menu.add(search, new GBC(GBC.REMAINDER, 3, 3).setFill(GBC.BOTH));
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
		area.setBackground(Color.GREEN);
//		infoLayout(area);
//		searchLayout(area);
		container.add(area, new GBC(1, 0, 10, 1).setFill(GBC.BOTH).setWeight(10, 1));
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

		  //������ռ��
		  table.getColumnModel().getColumn(0).setPreferredWidth(200);
		  table.getColumnModel().getColumn(1).setPreferredWidth(50);
		  table.getColumnModel().getColumn(2).setPreferredWidth(50);

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
	//editor���ż���                          *
	//���ܣ���ѯ�ɼ������ѡ�ν���  *
	//**********************
	JPanel gradesearchLayout() {
		JPanel grade_search_panel=new JPanel();
		GridBagLayout gridBagLayout=new GridBagLayout();
		grade_search_panel.setLayout(new GridBagLayout());
		//grade_search_panel.setSize(2200,2200);
	    JButton choose_subject=new JButton("��ѯ�˲���������");
	    JButton search_button=new JButton("��ѯ");
	    JLabel seamster_text=new JLabel("ѧ�� ");
		JTextField name=new JTextField("������     ");
		JTextField major=new JTextField("רҵ��     ");
		JTextField college=new JTextField("ѧԺ��     ");
		JLabel title=new JLabel("�ɼ���ѯ");
		//���ø�������е�����Ĵ�С
		Font font=new Font("����", Font.PLAIN, 50);
		title.setFont(font);
		name.setFont(new Font("����", Font.PLAIN, 25));
		major.setFont(new Font("����", Font.PLAIN, 25));
		college.setFont(new Font("����", Font.PLAIN, 25));
		seamster_text.setFont(new Font("����", Font.PLAIN, 25));
		search_button.setFont(new Font("����", Font.PLAIN, 25));
		choose_subject.setFont(new Font("����", Font.PLAIN, 25));
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
		Object[] columnTitle={"�γ̺�","�γ���","ѧ��" ,"ѧ��","�ɼ�","����"};
		choose_semaster.setFont(new Font("����", Font.PLAIN, 25));
		Object[][] tabledata= {
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""},
				new Object[] {"","","","","",""}
		};
		JTable grade=new JTable(tabledata,columnTitle);
		grade.setFont(new Font("����", Font.PLAIN, 25));
		grade.setRowHeight(50);
		grade.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		name.setBorder(new EmptyBorder(0,0,0,0));
		name.setBackground(Color.getColor("#708090"));
		major.setBorder(new EmptyBorder(0,0,0,0));
		major.setBackground(Color.getColor("#708090"));
		college.setBorder(new EmptyBorder(0,0,0,0));
		college.setBackground(Color.getColor("#708090"));
		grade_search_panel.add(title, new GBC(200, 0, 2, 1).setFill(GBC.WEST).setWeight(0, 0));
		grade_search_panel.add(name , new GBC(1200, 1, 2, 1).setFill(GBC.NONE).setWeight(0, 0));
		grade_search_panel.add(major, new GBC(1200, 10, 2, 1).setFill(GBC.NONE).setWeight(0, 0));
		grade_search_panel.add(college, new GBC(1200, 20, 2, 1).setFill(GBC.NONE).setWeight(0, 0));
		grade_search_panel.add(new JScrollPane(grade),new GBC(200, 200, 0, 18).setFill(GBC.BOTH).setWeight(8, 1));
		grade_search_panel.add(seamster_text,new GBC(200, 220, 1, 1).setFill(GBC.WEST).setWeight(1, 0));
		grade_search_panel.add(choose_semaster,new GBC(210, 220, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		grade_search_panel.add(search_button, new GBC(220, 220, 1, 1).setFill(GBC.EAST).setWeight(1, 0));
		grade_search_panel.add(choose_subject, new GBC(230, 220, 1, 1).setFill(GBC.EAST).setWeight(1, 0));
		return grade_search_panel;
	}

	JPanel chooseClassLayout() {
		JPanel choose_class_panel=new JPanel();

		choose_class_panel.setLayout(new GridBagLayout());
		JLabel title=new JLabel("ѡ��");
		title.setFont(new Font("����", Font.PLAIN, 50));
		choose_class_panel.add(title, new GBC(200, 0, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		JTextField name=new JTextField("������     ");
		JTextField major=new JTextField("רҵ��     ");
		JTextField college=new JTextField("ѧԺ��     ");
		name.setBorder(new EmptyBorder(0,0,0,0));
		name.setBackground(Color.getColor("#708090"));
		major.setBorder(new EmptyBorder(0,0,0,0));
		major.setBackground(Color.getColor("#708090"));
		college.setBorder(new EmptyBorder(0,0,0,0));
		college.setBackground(Color.getColor("#708090"));
		Object[] columnTitle={"���","�γ̱��","�γ�" ,"ѧ��","��ѧ","���","ʱ�䰲��","��ʦ","�Ͽεص�","��ע","ѡ��"};
		Object[][] tabledata= {
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""},
				new Object[] {"","","","","","","","","","",""}
		};
		JTable class_info=new JTable(tabledata,columnTitle);
		class_info.setFont(new Font("����", Font.PLAIN, 25));
		class_info.setRowHeight(50);
		JButton scramble_class=new JButton("�Զ�������Ϣ����");
		scramble_class.setFont(new Font("����", Font.PLAIN, 25));
		choose_class_panel.add(name , new GBC(300, 1, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(major, new GBC(300, 10, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(college, new GBC(300, 20, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(new JScrollPane(class_info),new GBC(200, 200, 0, 18).setFill(GBC.BOTH).setWeight(8, 1));
		choose_class_panel.add(scramble_class, new GBC(230, 220, 8, 1).setFill(GBC.EAST).setWeight(8, 1));
		name.setFont(new Font("����", Font.PLAIN, 25));
		major.setFont(new Font("����", Font.PLAIN, 25));
		college.setFont(new Font("����", Font.PLAIN, 25));
		return choose_class_panel;
	}

	//****************
	//editor��������           *
	//���ܣ���ҵ��Ϣ��ѯ     *
	//****************
	JPanel workinfoLayout() {
		JPanel workinfo = new JPanel();
		workinfo.setLayout(new GridBagLayout());
		JButton news=new JButton("���Ŷ�̬");
		JButton notice=new JButton("֪ͨ���");
		JButton recruit=new JButton("��Ƹ�ȵ�");
		JButton policy=new JButton("���߽��");
		int i=448;
		news.setPreferredSize(new Dimension(i, 50));
		notice.setPreferredSize(new Dimension(i, 50));
		recruit.setPreferredSize(new Dimension(i, 50));
		policy.setPreferredSize(new Dimension(i, 50));
		Object[] sort= {"����","ʱ��"};
		Object[][] context= {
				new Object[] {"��У2018�������Ƽ�ģ�����Դ���Բ����Ļ","2019-01-10"},
				new Object[] {"���ڷ��������ϴ�ѧ2018��ȱ�ҵ����ҵ�������桷�Ĺ���","2018-12-31"},
				new Object[] {"11��29��������Լ���ϴ�ѧ2018�������Ƽ�ģ�����Դ���","2018-11-21"},
				new Object[] {"�㶫ʡ2019���У��ҵ��������� �м�����Ƹר��","2018-11-15"},
				new Object[] {"",""},
				new Object[] {"",""},
				new Object[] {"",""},
				new Object[] {"",""}
		};
		DefaultTableModel model=new DefaultTableModel(context, sort);
		JTable work=new JTable(model);
		//������ռ��
		work.getColumnModel().getColumn(0).setPreferredWidth(200);
		work.getColumnModel().getColumn(1).setPreferredWidth(100);

		//�������־���
		DefaultTableCellRenderer t=new DefaultTableCellRenderer();
		t.setHorizontalAlignment(JLabel.CENTER);
		work.setDefaultRenderer(Object.class,t);

		//���ñ��,��
		work.setPreferredScrollableViewportSize(new Dimension(700, 300));

		//�����п�
		work.setRowHeight(50);
		//�������ִ�ѧ
		work.setFont(new Font("Menu.font", Font.PLAIN, 20));
		work.getTableHeader().setFont(new Font("Menu.font", Font.PLAIN, 30));
		workinfo.add(news,new GBC(100,9,1,2).setFill(GBC.EAST).setWeight(0, 0));
		workinfo.add(notice,new GBC(101,9,1,2).setFill(GBC.EAST).setWeight(0, 0));
		workinfo.add(recruit,new GBC(102,9,1,1).setFill(GBC.EAST).setWeight(0, 0));
		workinfo.add(policy,new GBC(103,9,1,1).setFill(GBC.EAST).setWeight(0, 0));

		JPanel total = new JPanel();
		total.setLayout(new BorderLayout());
		total.add(workinfo, BorderLayout.NORTH);
		total.add(new JScrollPane(work), BorderLayout.CENTER);

		return total;
	}

	//*************
	//editor������  *
	//���ܣ������ѯ    *
	//*************
	JPanel ShuidianLayout() {
		JPanel shuidian_recharge = new JPanel();
		shuidian_recharge.setLayout(new GridBagLayout());
		shuidian_recharge.setSize(100, 100);

		Font font = new Font("����", Font.PLAIN, 25);
		JLabel label1 = new JLabel("���ϴ�ѧ");
		JLabel label2 = new JLabel("����������");
		JLabel label3 = new JLabel("����ţ�6627");
		JLabel label4 = new JLabel("��ǰˮ����20");
		JLabel label5 = new JLabel("��ǰ�����30");
		JTextField shui_recharge = new JTextField("����������ˮ��                ");
		JTextField dian_recharge = new JTextField("������������                ");
		JButton b1 = new JButton("ˮ�ѳ�ֵ");
		JButton b2 = new JButton("��ѳ�ֵ");

		label1.setFont(font);
		label2.setFont(font);
		label3.setFont(font);
		label4.setFont(font);
		label5.setFont(font);
		b1.setFont(font);
		b2.setFont(font);

		shuidian_recharge.add(label1, new GBC(0, 0, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(label2, new GBC(0, 1, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(label3, new GBC(0, 2, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(label5, new GBC(0, 4, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(shui_recharge, new GBC(0, 5, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(dian_recharge, new GBC(0, 6, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(b1, new GBC(0, 7, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		shuidian_recharge.add(b2, new GBC(0,8, 8, 1).setFill(GBC.EAST).setWeight(8, 1));

		return shuidian_recharge;
	}

	//*************
	//editor��֣����  *
	//���ܣ��ĵ��༭    *
	//*************
	JPanel documentLayout() {

		TextField tx_stu_id,tx_stu_name,tx_date_year,tx_date_month,tx_date_day;
		tx_stu_id = new TextField(20);
		tx_stu_name = new TextField(20);

		tx_date_year=new TextField(4);
		tx_date_month=new TextField(4);
		tx_date_day=new TextField(4);

		JTextArea tx_reason;
		tx_reason=new JTextArea(10,20);

		JLabel lab_stu_id,lab_stu_name,lab_reason,lab_date,lab_year,lab_month,lab_day;
		lab_stu_id=new JLabel("ѧ��:	");
		lab_stu_name=new JLabel("����:	");
		lab_reason=new JLabel("���ԭ��:	");
		lab_date=new JLabel("�������:	");
		lab_year=new JLabel("��");
		lab_month=new JLabel("��");
		lab_day=new JLabel("��");

		final JButton jb_export;
		jb_export=new JButton("�����ĵ�");

		jb_export.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
                 JPopupMenu  pop  = new JPopupMenu();//��ĵ����˵�
                 JMenuItem item1 = new JMenuItem("WORD");
         		JMenuItem item2 = new JMenuItem("PDF");
         		pop.add(item1);
         	    pop.add(item2);

                pop.show(jb_export,e.getX(),e.getY());
              }
		});

		//JMenuBar jMenuBar = new JMenuBar();

		/*JMenu jMenu = new JMenu("�����ĵ�");
		JMenuItem item1 = new JMenuItem("WORD��ʽ");
        JMenuItem item2 = new JMenuItem("PDF��ʽ");
        jMenu.add(item1);
        jMenu.add(item2);*/
        //jMenuBar.add(jMenu);

		/*JPopupMenu editMenu = new JPopupMenu("Edit");
		JMenuItem item1 = new JMenuItem("WORD");
		JMenuItem item2 = new JMenuItem("PDF");
		editMenu.add(item1);
	    editMenu.add(item2);*/

		JPanel jp_stu_id,jp_stu_name,jp_reason,jp_date,jp_button;
		jp_stu_id=new JPanel();
		jp_stu_name=new JPanel();
		jp_reason=new JPanel();
		jp_date=new JPanel();
		jp_button=new JPanel();

		jp_stu_id.add(lab_stu_id);
		jp_stu_id.add(tx_stu_id);
		jp_stu_name.add(lab_stu_name);
		jp_stu_name.add(tx_stu_name);
		jp_reason.add(lab_reason);
		jp_reason.add(tx_reason);
		jp_date.add(lab_date);
		jp_date.add(tx_date_year);
		jp_date.add(lab_year);
		jp_date.add(tx_date_month);
		jp_date.add(lab_month);
		jp_date.add(tx_date_day);
		jp_date.add(lab_day);
		jp_button.add(jb_export);
		//editMenu.show(jp_button);


		JPanel jp_title=new JPanel();
		Font textsize = new Font("����",Font.PLAIN,35);
		JLabel title=new JLabel("�����");
		title.setFont(textsize);
		jp_title.add(title);

		Box box = Box.createVerticalBox();
		box.add(jp_title);
		box.add(jp_stu_id);
		box.add(jp_stu_name);
		box.add(jp_reason);
		box.add(jp_date);
		box.add(jp_button);

		JPanel docu=new JPanel();
		docu.add(box);

		return docu;
	}


	//********************
	//editor��zzf         *
	//���ܣ�getter&setter *
	//********************
	public JPanel getArea() {
		return area;
	}

}
