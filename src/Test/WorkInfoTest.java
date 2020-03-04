package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Crawling.CrawMain;
import Document.Recruit;
import Frame.AppFrame;

public class WorkInfoTest {
		AppFrame appFrame ;
	
	@Before
	public void setUp() throws Exception {
		appFrame = new AppFrame();
	}
	//lql-������ȡ��ҵ��Ϣ-���Ŷ�̬
	@Test
	public void test_worknew() {
		appFrame.iniContent();
		appFrame.setListNum(1);
		appFrame.setListType("work_new");
		new CrawMain(appFrame.getListType(), appFrame, true);
		assertEquals(3, appFrame.getListNumMax());
	}
	//lql-������ȡ��ҵ��Ϣ-֪ͨ���
	@Test
	public void test_workad() {
		appFrame.iniContent();
		appFrame.setListNum(1);
		appFrame.setListType("work_ad");
		new CrawMain(appFrame.getListType(), appFrame, true);
		assertEquals(14, appFrame.getListNumMax());
	}
	
	//lql-������ȡ��ҵ��Ϣ-��Ƹ�ȵ�
	@Test
	public void test_workhotpoint() {
		appFrame.iniContent50();
		appFrame.setListNum(1);
		appFrame.setListType("work_hotpoint");
		new CrawMain(appFrame.getListType(), appFrame, true);
		assertEquals(7, appFrame.getListNumMax());
	}
	
	//lql-������ȡ��ҵ��Ϣ-���߽��
	@Test
	public void test_workpolicy() {
		appFrame.iniContent();
		appFrame.setListNum(1);
		appFrame.setListType("work_policy");
		new CrawMain(appFrame.getListType(), appFrame, true);
		assertEquals(3, appFrame.getListNumMax());
	}
	
	//lql-������ȡ������Ϣ
	@Test
	public void test_data() {
		new CrawMain("calendar", appFrame, true);
		HashMap<String, ArrayList<Recruit>> temp = appFrame.getdatacalendar();
		assertEquals(2, temp.size());
	}
	

}
