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
				// TODO �Զ����ɵķ������
				keyword=getkeyword(kyboard);
				search_result=search_db(keyword);
			}
        });
	}*/
	
	public Object[][] find_searchresult(String key)
	{
		Object[][] tableData_result =
			  {
			    new Object[]{"���ϴ�ѧ�����칫�ҹ���2019�������ڷżٵ�֪ͨ" , "�����칫��" , "2019-03-15"},
			    new Object[]{"���ڡ����ϴ�ѧ�������2019��Ŀ¼���������֪ͨ", "�����칫��" , "2019-04-01"},
			    new Object[]{"���ϴ�ѧ�����ص��з�ר�����������Ƹ���ط���", "������Դ���������" , "2019-03-29"},
			    new Object[]{"���ڿ�չ���ϴ�ѧ2019�����������������������Ŀѧ��ѡ�ɹ�����֪ͨ","ѧ����" , "2019-03-29"}
			  };
		/*String[] ky_result=new String[] {"���ϴ�ѧ��","���ϴ�ѧ����"};*/
		return tableData_result;
	}

}
