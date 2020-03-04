package Packaging;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import Crawling.CrawMain;
import Frame.AppFrame;
import Net.Net;

public class search {
	
	
	ArrayList<Object[]> resultList=new ArrayList<Object[]>();
	private static Logger search_log=Logger.getLogger(search.class);
	public Object[][] find_searchresult(String key,AppFrame appframe)
	{
		/*Object[][] datacontent;
		if(appframe.getContent()==null)
		{
			appframe.iniContent();
			appframe.setListNum(1); 
			String listType = "infor_sch";
			new CrawMain(listType, appframe, false);
			datacontent=appframe.getContent();
		}
		else 
			datacontent=appframe.getContent();*/
		
		/////////////
		//int allnum=0;
		ArrayList<Object[]> allnewsList=new ArrayList<Object[]>();
		appframe.iniContent();
		appframe.setListNum(1); 
		String listType = "infor_sch";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		appframe.iniContent();
		appframe.setListNum(1);
		listType = "infor_schIn";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		
		appframe.iniContent();
		appframe.setListNum(1);
		listType = "infor_meeting";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		appframe.iniContent();
		appframe.setListNum(1);
		listType = "infor_lec";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		
		appframe.iniContent();
		appframe.setListNum(1);
		listType = "infor_lec";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		appframe.iniContent();
		appframe.setListNum(1);
		listType = "infor_office";
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		
		appframe.iniContent();
		appframe.setListNum(1);
		switch (appframe.Item) {
		case "国际商学院":
			listType = "college_business";
			break;

		case "翻译学院":
			listType = "college_translation";
			break;
			
		case "人文学院":
			listType = "college_Humanities";
			break;
		}
		new CrawMain(listType, appframe, true);
		for(int i=0;i<appframe.getContent().length;i++)
		{
			if(appframe.getContent()[i][0]!=null)
			{
				allnewsList.add(appframe.getContent()[i]);
				//allnum++;
			}
		}
		
		ListTocontent LtoC=new ListTocontent();
		Object[][] datacontent=LtoC.change(allnewsList);
		
		
		
		
		String s=String.valueOf(datacontent[0][2]);
		search_log.info(s);
		int accout=0;
		for(int i=0;i<datacontent.length;i++)
		{
			if(datacontent[i][0]!=null)
				accout++;
		}
		
		for(int i=0;i<accout;i++)
		{
			String result=datacontent[i][0].toString();
			if(result.contains(key))
			{
				resultList.add(datacontent[i]);
			}
			
			
		}
		
		Object[][] search_result=new Object[resultList.size()][3];
		
		for(int i=0;i<resultList.size();i++)
		{
			search_result[i]=resultList.get(i);
		}
		
		return search_result;
	}

}
