package Packaging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import Frame.AppFrame;

public class ElecRead {
	private static Map<String, String> cookies = null;
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Date date = new Date();
	AppFrame appFrame;


	public ElecRead(AppFrame appFrame) throws IOException{
		this.appFrame = appFrame;
		String url = "http://202.116.25.12/default.aspx";
		Map<String, String> data = new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();
		
		login();
		Connection connection = Jsoup.connect(url);
		
		connection
		.cookies(cookies)
		.headers(header)
		.data(data);
		Response response = connection.ignoreContentType(true).method(Method.POST).execute();
		org.jsoup.nodes.Document document= Jsoup.parse(response.body());
		
		read( document);
	}

	public void login() throws IOException {
		String url = "http://202.116.25.12/Login.aspx";
		Map<String, String> data = new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.9");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "keep-alive");
		header.put("Content-Length", "298");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Host", "202.116.25.12");
		header.put("Origin", "http://202.116.25.12");
		header.put("Referer", "http://202.116.25.12/Login.aspx?ReturnUrl=%2fdefault.aspx");
		header.put("Upgrade-Insecure-Requests", "1");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
		
		data.put("__LASTFOCUS", "");
		data.put("__VIEWSTATE", "/wEPDwULLTE5ODQ5MTY3NDlkZM4DISokA1JscbtlCdiUVQMwykIc");
		data.put("__VIEWSTATEGENERATOR", "C2EE9ABB");
		data.put("__EVENTTARGET", "");
		data.put("__EVENTARGUMENT", "");
		data.put("__EVENTVALIDATION", "/wEWBQLz+M6SBQK4tY3uAgLEhISACwKd+7q4BwKiwImNC7oxDnFDxrZR6l5WlUJDrpGZXrmN");
		data.put("hidtime", simpleDateFormat.format(date));
		data.put("txtname", "3311");
		data.put("txtpwd", "");
		data.put("ctl01", "");
		
		Connection connection = Jsoup.connect(url);
		connection
		.headers(header)
		.data(data);
		Response response = connection.ignoreContentType(true).method(Method.POST).execute();
		if(response.statusCode() == 200) {
			cookies = response.cookies();
		}else {
			System.out.println("connect fail");
		}
	}
	private static String getValue(String value){
	        return  value.substring(value.indexOf("value")+7,value.indexOf(">")-1);
	}
	 
	public void read(org.jsoup.nodes.Document doc) throws IOException {
		String url = "http://202.116.25.12/default.aspx";
	    String __VIEWSTATE=doc.getElementById("__VIEWSTATE").toString();
	    String __EVENTVALIDATION = doc.getElementById("__EVENTVALIDATION").toString();
		Map<String, String> data = new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("Accept", "*/*");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.9");
		header.put("Connection", "keep-alive");
		header.put("Content-Length", "7147");
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.put("Host", "202.116.25.12");
		header.put("Origin", "http://202.116.25.12");
		header.put("Referer", "http://202.116.25.12/default.aspx");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
		header.put("X-Requested-With", "XMLHttpRequest");
		
		data.put("__EVENTTARGET", "RegionPanel1$Region2$GroupPanel1$ContentPanel1$DDL_监控项目");
		data.put("__EVENTARGUMENT", "");
		data.put("__LASTFOCUS", "");
		data.put("__VIEWSTATE", getValue(__VIEWSTATE));
		data.put("__VIEWSTATEGENERATOR", "CA0B0334");
		data.put("__EVENTVALIDATION", getValue(__EVENTVALIDATION));
		data.put("tqid", "");
		data.put("tqsort", "");
		data.put("PandValue", "10");
		data.put("hidpageCurrentSize", "1");
		data.put("hidpageSum", "1");
		data.put("hidpageCurrentSize2", "1");
		data.put("hidpageSum2", "4");
		data.put("hidpageCurrentSize3", "1");
		data.put("hidpageSum3", "40");
		data.put("RegionPanel1$Region3$ContentPanel3$txtstarOld", "2019-4-30");
		data.put("RegionPanel1$Region3$ContentPanel3$txtstar", "2019-5-30");
		data.put("__12_value", "[电表]|000001214311");
		data.put("RegionPanel1$Region3$ContentPanel3$tb_ammeterNumb", "[电表]000001214311");
		data.put("__41_value", "00900200");
		data.put("RegionPanel1$Region1$GroupPanel2$Grid3$Toolbar2$pagesize3", "1");
		data.put("RegionPanel1$Region1$GroupPanel2$Grid2$Toolbar3$pagesize2", "1");
		data.put("RegionPanel1$Region1$GroupPanel2$Grid1$Toolbar1$pagesize", "1");
		data.put("__box_page_state_changed", "false");
		data.put("__12_last_value", "[电表]|000001214311");
		data.put("__12_disable_select_row_indexs", "");
		data.put("__41_last_value", "00900200");
		data.put("__41_disable_select_row_indexs", "");
		data.put("__43_selectedRows", "");
		data.put("__44_selectedRows", "");
		data.put("__45_selectedRows", "");
		data.put("__box_ajax_mark", "true");
		Connection connection = Jsoup.connect(url);
		connection
		.headers(header)
		.data(data)
		.cookies(cookies);
//		.timeout(10000);
		Response response = connection.ignoreContentType(true).method(Method.POST).execute();
//		System.out.println(response.body());
		int num = response.body().indexOf("box.__27.setValue(\"") + 19;
		int num_end = response.body().indexOf("\")", num);
		String elec = response.body().substring(num, num_end);
//		System.out.println(elec);
		appFrame.setElec(elec);
	}
}
