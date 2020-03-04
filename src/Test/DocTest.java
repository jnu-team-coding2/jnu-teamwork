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
	String Savepath=null;//保存路径
	@Before
	public void setUp() throws Exception {
		testframe=new AppFrame();
		
		//设置填写内容
		testframe.setStuNum("2016052464");
		testframe.setName("郑凯帆");
		testframe.setMajor("软件工程");
		testframe.setCollege("智能科学与工程学院");
		testframe.setReasonStr("发烧");
		testframe.setYear1("2019");
		testframe.setMonth1("05");
		testframe.setDay1("22");
		testframe.setYear2("2019");
		testframe.setMonth2("05");
		testframe.setDay2("24");
		
		//testframe.launch();
		
		Savepath="C:\\Users\\即客\\Desktop\\123.doc";
		
	}
	//测试导出文档是否存在及内容
	@Test
	public void test_Export() throws IOException, TemplateException {
		
		doc_exp=new DocExport(testframe);
		File testfile=doc_exp.getOutFile();
		//测试路径
		assertEquals(testfile.getPath(),Savepath);
		//将导出文档转换为String
		FileInputStream inputStream = new FileInputStream(testfile);
	    int length = inputStream.available();
	    byte bytes[] = new byte[length];
	    inputStream.read(bytes);
	    inputStream.close();
	    String teststr =new String(bytes, StandardCharsets.UTF_8);
	    
	    //测试导出文档中内容是否包含所填的信息
	    assertEquals(teststr.contains("2016052464"),true);
	    assertEquals(teststr.contains("郑凯帆"),true);
	    assertEquals(teststr.contains("智能科学与工程学院"),true);
	    assertEquals(teststr.contains("软件工程"),true);
	    assertEquals(teststr.contains("2019"),true);
	    assertEquals(teststr.contains("05"),true);
	    assertEquals(teststr.contains("22"),true);
        
        
	}
	//测试预览html文件是否存在
	@Test
	public void test_Preview() throws IOException {
		
		doc_pre=new DocPreview(testframe);
		File testhtml=doc_pre.getPreviewFile();
		assertEquals(testhtml.getPath(),System.getProperty("user.dir")+"\\template\\Preview.html");
	}
	//测试保存路径
	@Test
	public void test_Save() throws IOException, TemplateException {
		
		doc_save=new SaveDoc(doc_exp);
		String path=doc_save.getPath();
		assertEquals(path,Savepath);
		
	}

}
