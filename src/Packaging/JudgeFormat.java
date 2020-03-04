package Packaging;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeFormat {
	
	public Map<String, Boolean> JInfoFormat(StuInfo stuinfo)
	{
		
		Map<String, Boolean> jubgemap = new HashMap<String, Boolean>();
		//TextField stu_id,stu_name,uni_password,jwc_password,jnu_password,dorm_num,card_num,forum_account,forum_password;
		Pattern pattern;
	    Matcher matcher;
		
		
		String stuid_pattern="[0-9]{10}";
		String stuname_pattern="\\S+";
		String upw_pattern="\\S+";
		String jwcpw_pattern="\\S+";
		String jnupw_pattern="\\S+";
		String dormnum_pattern="[0-9]{4}";
		String cardnum_pattern="[0-9]{6}";
		String faccount_pattern="[0-9]+@\\S+.\\S*";
		String fpw_pattern="\\S+";
		
		pattern = Pattern.compile(stuid_pattern);
		matcher = pattern.matcher(stuinfo.getStuid());
		if(!matcher.matches())
		{
			jubgemap.put("stuid", false);
		}
		else {
			jubgemap.put("stuid", true);
		}
		
		pattern = Pattern.compile(stuname_pattern);
		matcher = pattern.matcher(stuinfo.getStuname());
		if(!matcher.matches())
		{
			jubgemap.put("stuname", false);
		}
		else {
			jubgemap.put("stuname", true);
		}
		
		pattern = Pattern.compile(upw_pattern);
		matcher = pattern.matcher(stuinfo.getUnipassword());
		if(!matcher.matches())
		{
			jubgemap.put("upw", false);
		}
		else {
			jubgemap.put("upw", true);
		}
		pattern = Pattern.compile(jwcpw_pattern);
		matcher = pattern.matcher(stuinfo.getJwcpassword());
		if(!matcher.matches())
		{
			jubgemap.put("jwcpw", false);
		}
		else {
			jubgemap.put("jwcpw", true);
		}
		
		pattern = Pattern.compile(jnupw_pattern);
		matcher = pattern.matcher(stuinfo.getJnupasssword());
		if(!matcher.matches())
		{
			jubgemap.put("jnupw", false);
		}
		else {
			jubgemap.put("jnupw", true);
		}
		
		pattern = Pattern.compile(dormnum_pattern);
		matcher = pattern.matcher(stuinfo.getDormnum());
		if(!matcher.matches())
		{
			jubgemap.put("dormnum", false);
		}
		else {
			jubgemap.put("dormnum", true);
		}
		
		pattern = Pattern.compile(cardnum_pattern);
		matcher = pattern.matcher(stuinfo.getCardnum());
		if(!matcher.matches())
		{
			jubgemap.put("cardnum", false);
		}
		else {
			jubgemap.put("cardnum", true);
		}
		
		pattern = Pattern.compile(faccount_pattern);
		matcher = pattern.matcher(stuinfo.getForumaccount());
		if(!matcher.matches())
		{
			jubgemap.put("faccount", false);
		}
		else {
			jubgemap.put("faccount", true);
		}
		
		pattern = Pattern.compile(fpw_pattern);
		matcher = pattern.matcher(stuinfo.getFornumpassword());
		if(!matcher.matches())
		{
			jubgemap.put("fpw", false);
		}
		else {
			jubgemap.put("fpw", true);
		}
	
		
		
		return jubgemap;
	}

}
