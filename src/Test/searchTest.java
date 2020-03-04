package Test;



import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.awt.BorderLayout;

import org.junit.Before;
import org.junit.Test;

import Crawling.CrawMain;
import Packaging.*;
import Frame.AppFrame;
import Net.Net;
public class searchTest {
	 AppFrame testframe=new AppFrame();
	public search searchtest=new search();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void searchtest() {
		String testKyword="Ùﬂƒœ¥Û—ß";
		Object[][] search_result=searchtest.find_searchresult(testKyword, testframe);
		for(int i=0;i<search_result.length;i++)
		{
			String result=search_result[i][0].toString();
			assertThat(result,containsString(testKyword));
		}
	}

}
