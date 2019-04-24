import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class search {
	
	/*TextField kyboard;
	String keyword;
	String[] search_result;
	
	public String getkeyword(TextField board)
	{
		String keyword=board.getText();
		return keyword;

	}
	*/
	
	/*public void setButtonClick(JButton searchbtn)
	{
		searchbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				keyword=getkeyword(kyboard);
				search_result=search_db(keyword);
			}
        });
	}*/
	
	public Object[][] find_searchresult(String key)
	{
		Object[][] tableData_result =
			  {
			    new Object[]{"暨南大学党政办公室关于2019年清明节放假的通知" , "党政办公室" , "2019-03-15"},
			    new Object[]{"关于《暨南大学年鉴》（2019）目录征求意见的通知", "党政办公室" , "2019-04-01"},
			    new Object[]{"暨南大学国家重点研发专项科研助理招聘（重发）", "人力资源开发与管理处" , "2019-03-29"},
			    new Object[]{"关于开展暨南大学2019年出国（境）本科生交换项目学生选派工作的通知","学生处" , "2019-03-29"}
			  };
		/*String[] ky_result=new String[] {"暨南大学好","暨南大学教务处"};*/
		return tableData_result;
	}

}
