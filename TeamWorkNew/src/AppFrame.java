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
	//editor:陈佳兰
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
	//editor：张泽锋             *
	//功能：添加左边菜单栏   *
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
	//功能：以下每个add为左边菜单栏里的各选项 *
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
		searchButton = new JButton("搜索");
		searchButton.setSize(10,15);
		searchButton.setIcon(new IconSizeAdapt(searchIcon, searchButton).getAdaptIcon());
		searchButton.setContentAreaFilled(false);
		searchText = new JTextField(8);


		search.setLayout(new FlowLayout());
		searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//进行搜索,editor:陈佳兰
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
		gradeRoot = new DefaultMutableTreeNode("成绩查询");
		gradeCheck = new DefaultMutableTreeNode("查询成绩");
		lessonSelect = new DefaultMutableTreeNode("选课");
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
		documentRoot = new DefaultMutableTreeNode("文档编辑");
		documentWrite = new DefaultMutableTreeNode("文档填写");
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
		messageRoot = new DefaultMutableTreeNode("通知");
		messageCheck = new DefaultMutableTreeNode("查看通知");
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
		jobRoot = new DefaultMutableTreeNode("就业");
		jobCheck = new DefaultMutableTreeNode("就业信息查询");
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
		lifeRoot = new DefaultMutableTreeNode("生活");
		card = new DefaultMutableTreeNode("校园卡");
		elec = new DefaultMutableTreeNode("水电");
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
		forumRoot = new DefaultMutableTreeNode("论坛");
		forumCheck = new DefaultMutableTreeNode("浏览论坛");
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
		informationRoot = new DefaultMutableTreeNode("个人信息");
		informationManage = new DefaultMutableTreeNode("个人信息管理");
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

	//各组员在此area的JPanel中添加你的布局，注意，不要直接添加在area，自己再建一个容器，布局添加在自己新建的容器，将容器再添加至area
	//****************
	//editor：张泽锋             *
	//功能：添加右边显示区    *
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
	//editor：陈佳兰                                    *
	//功能：个人信息界面和搜索结果界面    *
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
		t_stu_id=new JLabel("学号        ");
		t_name=new JLabel("姓名        ");
		t_uni_password=new JLabel("统一密码    ");
		t_jwc_password=new JLabel("教务处密码  ");
		t_jnu_password=new JLabel("数字暨大密码");
		t_dorm_num=new JLabel("宿舍号      ");
		t_card_num=new JLabel("学生卡卡号  ");
		t_forum_account=new JLabel("论坛账号    ");
		t_forum_password=new JLabel("论坛密码    ");

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
		JButton ensure_btn=new JButton("确定");
		JButton cancel_btn=new JButton("取消");
		confirm_btns.add(ensure_btn);
		confirm_btns.add(cancel_btn);


		JPanel jp_title=new JPanel();
		Font textsize = new Font("宋体",Font.PLAIN,35);
		JLabel title=new JLabel("个人信息");
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
		  //定义二维数组作为表格数据
		  //定义一维数据作为列标题
		  Object[] columnTitle = {"标题" , "发布单位" , "发布日期"};
		    //以二维数组和一维数组来创建一个JTable对象
		  model = new DefaultTableModel(tableData, columnTitle);
		  table = new JTable(model);

		  //设置列占比
		  table.getColumnModel().getColumn(0).setPreferredWidth(200);
		  table.getColumnModel().getColumn(1).setPreferredWidth(50);
		  table.getColumnModel().getColumn(2).setPreferredWidth(50);

		  //设置字居中显示
		  DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		  r.setHorizontalAlignment(JLabel.CENTER);
		  table.setDefaultRenderer(Object.class, r);

		  table.setPreferredScrollableViewportSize(new Dimension(700,300));//设置表格的长宽
		  table.setRowHeight(30);//设置行宽


		  JScrollPane JSP= new JScrollPane(table);

		  return JSP;

	}


	//**********************
	//editor：张家骐                          *
	//功能：查询成绩界面和选课界面  *
	//**********************
	JPanel gradesearchLayout() {
		JPanel grade_search_panel=new JPanel();
		GridBagLayout gridBagLayout=new GridBagLayout();
		grade_search_panel.setLayout(new GridBagLayout());
		//grade_search_panel.setSize(2200,2200);
	    JButton choose_subject=new JButton("查询人才培养方案");
	    JButton search_button=new JButton("查询");
	    JLabel seamster_text=new JLabel("学期 ");
		JTextField name=new JTextField("姓名：     ");
		JTextField major=new JTextField("专业：     ");
		JTextField college=new JTextField("学院：     ");
		JLabel title=new JLabel("成绩查询");
		//设置各个组件中的字体的大小
		Font font=new Font("宋体", Font.PLAIN, 50);
		title.setFont(font);
		name.setFont(new Font("宋体", Font.PLAIN, 25));
		major.setFont(new Font("宋体", Font.PLAIN, 25));
		college.setFont(new Font("宋体", Font.PLAIN, 25));
		seamster_text.setFont(new Font("宋体", Font.PLAIN, 25));
		search_button.setFont(new Font("宋体", Font.PLAIN, 25));
		choose_subject.setFont(new Font("宋体", Font.PLAIN, 25));
		JComboBox choose_semaster=new JComboBox();
		choose_semaster.addItem("  ");
		choose_semaster.addItem("大一上");
		choose_semaster.addItem("大一下");
		choose_semaster.addItem("大二上");
		choose_semaster.addItem("大二下");
		choose_semaster.addItem("大三上");
		choose_semaster.addItem("大三下");
		choose_semaster.addItem("大四上");
		choose_semaster.addItem("大四下");
		Object[] columnTitle={"课程号","课程名","学期" ,"学分","成绩","绩点"};
		choose_semaster.setFont(new Font("宋体", Font.PLAIN, 25));
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
		grade.setFont(new Font("宋体", Font.PLAIN, 25));
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
		JLabel title=new JLabel("选课");
		title.setFont(new Font("宋体", Font.PLAIN, 50));
		choose_class_panel.add(title, new GBC(200, 0, 8, 1).setFill(GBC.WEST).setWeight(8, 1));
		JTextField name=new JTextField("姓名：     ");
		JTextField major=new JTextField("专业：     ");
		JTextField college=new JTextField("学院：     ");
		name.setBorder(new EmptyBorder(0,0,0,0));
		name.setBackground(Color.getColor("#708090"));
		major.setBorder(new EmptyBorder(0,0,0,0));
		major.setBackground(Color.getColor("#708090"));
		college.setBorder(new EmptyBorder(0,0,0,0));
		college.setBackground(Color.getColor("#708090"));
		Object[] columnTitle={"班号","课程编号","课程" ,"学分","修学","类别","时间安排","教师","上课地点","备注","选课"};
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
		class_info.setFont(new Font("宋体", Font.PLAIN, 25));
		class_info.setRowHeight(50);
		JButton scramble_class=new JButton("自动抢课信息设置");
		scramble_class.setFont(new Font("宋体", Font.PLAIN, 25));
		choose_class_panel.add(name , new GBC(300, 1, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(major, new GBC(300, 10, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(college, new GBC(300, 20, 8, 1).setFill(GBC.EAST).setWeight(0, 0));
		choose_class_panel.add(new JScrollPane(class_info),new GBC(200, 200, 0, 18).setFill(GBC.BOTH).setWeight(8, 1));
		choose_class_panel.add(scramble_class, new GBC(230, 220, 8, 1).setFill(GBC.EAST).setWeight(8, 1));
		name.setFont(new Font("宋体", Font.PLAIN, 25));
		major.setFont(new Font("宋体", Font.PLAIN, 25));
		college.setFont(new Font("宋体", Font.PLAIN, 25));
		return choose_class_panel;
	}

	//****************
	//editor：雷清林           *
	//功能：就业信息查询     *
	//****************
	JPanel workinfoLayout() {
		JPanel workinfo = new JPanel();
		workinfo.setLayout(new GridBagLayout());
		JButton news=new JButton("新闻动态");
		JButton notice=new JButton("通知广告");
		JButton recruit=new JButton("招聘热点");
		JButton policy=new JButton("政策解读");
		int i=448;
		news.setPreferredSize(new Dimension(i, 50));
		notice.setPreferredSize(new Dimension(i, 50));
		recruit.setPreferredSize(new Dimension(i, 50));
		policy.setPreferredSize(new Dimension(i, 50));
		Object[] sort= {"标题","时间"};
		Object[][] context= {
				new Object[] {"我校2018年简历设计及模拟面试大赛圆满落幕","2019-01-10"},
				new Object[] {"关于发布《暨南大学2018年度毕业生就业质量报告》的公告","2018-12-31"},
				new Object[] {"11月29日与你相约暨南大学2018年简历设计及模拟面试大赛","2018-11-21"},
				new Object[] {"广东省2019届高校毕业生供需见面活动 残疾人招聘专场","2018-11-15"},
				new Object[] {"",""},
				new Object[] {"",""},
				new Object[] {"",""},
				new Object[] {"",""}
		};
		DefaultTableModel model=new DefaultTableModel(context, sort);
		JTable work=new JTable(model);
		//设置列占比
		work.getColumnModel().getColumn(0).setPreferredWidth(200);
		work.getColumnModel().getColumn(1).setPreferredWidth(100);

		//设置文字居中
		DefaultTableCellRenderer t=new DefaultTableCellRenderer();
		t.setHorizontalAlignment(JLabel.CENTER);
		work.setDefaultRenderer(Object.class,t);

		//设置表格长,宽
		work.setPreferredScrollableViewportSize(new Dimension(700, 300));

		//设置行宽
		work.setRowHeight(50);
		//设置文字大学
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
	//editor：刘睿睿  *
	//功能：生活查询    *
	//*************
	JPanel ShuidianLayout() {
		JPanel shuidian_recharge = new JPanel();
		shuidian_recharge.setLayout(new GridBagLayout());
		shuidian_recharge.setSize(100, 100);

		Font font = new Font("宋体", Font.PLAIN, 25);
		JLabel label1 = new JLabel("暨南大学");
		JLabel label2 = new JLabel("姓名：刘睿睿");
		JLabel label3 = new JLabel("宿舍号：6627");
		JLabel label4 = new JLabel("当前水费余额：20");
		JLabel label5 = new JLabel("当前电费余额：30");
		JTextField shui_recharge = new JTextField("请输入所需水费                ");
		JTextField dian_recharge = new JTextField("请输入所需电费                ");
		JButton b1 = new JButton("水费充值");
		JButton b2 = new JButton("电费充值");

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
	//editor：郑凯帆  *
	//功能：文档编辑    *
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
		lab_stu_id=new JLabel("学号:	");
		lab_stu_name=new JLabel("姓名:	");
		lab_reason=new JLabel("请假原因:	");
		lab_date=new JLabel("请假日期:	");
		lab_year=new JLabel("年");
		lab_month=new JLabel("月");
		lab_day=new JLabel("日");

		final JButton jb_export;
		jb_export=new JButton("导出文档");

		jb_export.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
                 JPopupMenu  pop  = new JPopupMenu();//你的弹出菜单
                 JMenuItem item1 = new JMenuItem("WORD");
         		JMenuItem item2 = new JMenuItem("PDF");
         		pop.add(item1);
         	    pop.add(item2);

                pop.show(jb_export,e.getX(),e.getY());
              }
		});

		//JMenuBar jMenuBar = new JMenuBar();

		/*JMenu jMenu = new JMenu("导出文档");
		JMenuItem item1 = new JMenuItem("WORD格式");
        JMenuItem item2 = new JMenuItem("PDF格式");
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
		Font textsize = new Font("宋体",Font.PLAIN,35);
		JLabel title=new JLabel("请假条");
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
	//editor：zzf         *
	//功能：getter&setter *
	//********************
	public JPanel getArea() {
		return area;
	}

}
