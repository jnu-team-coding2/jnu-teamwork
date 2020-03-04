package Test;


import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import Packaging.JudgeFormat;
import Packaging.StuInfo;
import Packaging.StuInfoPreserve;

public class stuinfoTest {

	StuInfo stuinfo_test=new StuInfo();
	@Test
	public void readwrite_test() {
		//fail("Not yet implemented");
		
		StuInfoPreserve stuinfopre_test=new StuInfoPreserve();
		stuinfo_test.setStuid("2016052350");
		stuinfo_test.setStuname("³Â¼ÑÀ¼");
		stuinfo_test.setCardnum("252205");
		stuinfo_test.setDormnum("4531");
		stuinfo_test.setJnupasssword("713814Cjl");
		stuinfo_test.setJwcpassword("19970814");
		stuinfo_test.setUnipassword("713814Cjl");
		stuinfo_test.setForumaccount("1547444671@qq.com");
		stuinfo_test.setFornumpassword("12345");
		
		stuinfopre_test.writeObjectToFile(stuinfo_test);
		StuInfo readresult_test=(StuInfo)stuinfopre_test.readObjectFromFile();
		
		assertEquals(readresult_test.getCardnum(), "252205");
		assertEquals(readresult_test.getDormnum(), "4531");
		assertEquals(readresult_test.getJnupasssword(), "713814Cjl");
		assertEquals(readresult_test.getJwcpassword(), "19970814");
		assertEquals(readresult_test.getStuid(), "2016052350");
		assertEquals(readresult_test.getStuname(), "³Â¼ÑÀ¼");
		assertEquals(readresult_test.getUnipassword(), "713814Cjl");
		assertEquals(readresult_test.getForumaccount(), "1547444671@qq.com");
	}
	
	@Test
	public void judgeform_test() {
		
		stuinfo_test.setStuid("201605235");
		stuinfo_test.setStuname("");
		stuinfo_test.setCardnum("25220");
		stuinfo_test.setDormnum("453");
		stuinfo_test.setJnupasssword("713814Cjl");
		stuinfo_test.setJwcpassword("19970814");
		stuinfo_test.setUnipassword("713814Cjl");
		stuinfo_test.setForumaccount("1547444671qq.com");
		stuinfo_test.setFornumpassword("12345");
		
		
		JudgeFormat jinformat_test=new JudgeFormat();
		Map<String, Boolean> result_test=jinformat_test.JInfoFormat(stuinfo_test);
		
		assertEquals(result_test.get("stuid"), false);
		assertEquals(result_test.get("stuname"), false);
		assertEquals(result_test.get("upw"), true);
		assertEquals(result_test.get("jwcpw"), true);
		assertEquals(result_test.get("jnupw"), true);
		assertEquals(result_test.get("dormnum"), false);
		assertEquals(result_test.get("faccount"), false);
		
	}

}
