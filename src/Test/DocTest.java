package Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;


import Document.DocExport;
import Document.DocPreview;
import Document.SaveDoc;
import Frame.AppFrame;
import freemarker.template.TemplateException;

public class DocTest {
	AppFrame testframe;
	DocExport doc_exp;
	DocPreview doc_pre;
	SaveDoc doc_save;
	String Savepath=null;//����·��
	@Before
	public void setUp() throws Exception {
		testframe=new AppFrame();
		
		//������д����
		testframe.setStuNum("2016052464");
		testframe.setName("֣����");
		testframe.setMajor("�������");
		testframe.setCollege("���ܿ�ѧ�빤��ѧԺ");
		testframe.setReasonStr("����");
		testframe.setYear1("2019");
		testframe.setMonth1("05");
		testframe.setDay1("22");
		testframe.setYear2("2019");
		testframe.setMonth2("05");
		testframe.setDay2("24");
		
		//testframe.launch();
		
		Savepath="C:\\Users\\����\\Desktop\\123.doc";
		
	}
	//���Ե����ĵ��Ƿ���ڼ�����
	@Test
	public void test_Export() throws IOException, TemplateException {
		
		doc_exp=new DocExport(testframe);
		File testfile=doc_exp.getOutFile();
		//����·��
		assertEquals(testfile.getPath(),Savepath);
		//�������ĵ�ת��ΪString
		FileInputStream inputStream = new FileInputStream(testfile);
	    int length = inputStream.available();
	    byte bytes[] = new byte[length];
	    inputStream.read(bytes);
	    inputStream.close();
	    String teststr =new String(bytes, StandardCharsets.UTF_8);
	    
	    //���Ե����ĵ��������Ƿ�����������Ϣ
	    assertEquals(teststr.contains("2016052464"),true);
	    assertEquals(teststr.contains("֣����"),true);
	    assertEquals(teststr.contains("���ܿ�ѧ�빤��ѧԺ"),true);
	    assertEquals(teststr.contains("�������"),true);
	    assertEquals(teststr.contains("2019"),true);
	    assertEquals(teststr.contains("05"),true);
	    assertEquals(teststr.contains("22"),true);
        
        
	}
	//����Ԥ��html�ļ��Ƿ����
	@Test
	public void test_Preview() throws IOException {
		
		doc_pre=new DocPreview(testframe);
		File testhtml=doc_pre.getPreviewFile();
		assertEquals(testhtml.getPath(),System.getProperty("user.dir")+"\\template\\Preview.html");
	}
	//���Ա���·��
	@Test
	public void test_Save() throws IOException, TemplateException {
		
		doc_save=new SaveDoc(doc_exp);
		String path=doc_save.getPath();
		assertEquals(path,Savepath);
		
	}

}
