package Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import Document.DocExport;
import School_office.GetScore;
import School_office.Office;
import School_office.Plan;
import junit.framework.Assert;

public class grade_searchTest {
	Office office=new Office();	
	String xml=new String("<table class=\"Gridview\" > <td>2016-2017</td><td>��</td><td>�й����ִ�ʷ��Ҫ</td><td>2.00</td><td>91.0</td><td>8.20</td><td>2017-1-16</td><td>��ĩ    </td><td>���޿�</td><td>����  </td><td>False</td> <td>2016-2017</td><td>��</td><td>�����еĻ���ʱ���ֵ</td><td>2.00</td><td>68.0</td><td>3.60</td><td>2017-7-7</td><td>��ĩ    </td><td>����ѡ�޿�</td><td>����  </td><td>False</td></table>");
	Document document=Jsoup.parse(xml);
	@SuppressWarnings("deprecation")
	@Test
	public void grade_test() {
		int i=0;
		String[] result=office.grade_message("��һ��", document);
		for(i=0;i<result.length;i++){
			if(result[i].equals("�й����ִ�ʷ��Ҫ"))
				break;
		}
		Assert.assertEquals("�й����ִ�ʷ��Ҫ", result[i]);
		
	}
}
