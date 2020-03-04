package Crawling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.Doc;
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;

import Document.Recruit;
import Frame.AppFrame;

public class CrawMain {
		Object[][] content;
		File file;
		int listNum;
		AppFrame appFrame;
		String fileName;
		
		private static Logger CrawMain_log=Logger.getLogger(CrawMain.class);
	
	public CrawMain(String fileName, AppFrame appFrame, boolean net) {
		content = appFrame.getContent();
		listNum = appFrame.getListNum();
		this.fileName = fileName;
		this.appFrame = appFrame;
		String pathName = "./data/" + fileName + "_page" + listNum + ".xml";
		file = new File(pathName);
		
		if(net) {
			crawNet(fileName);
		}else {
			crawDoc(pathName);
		}
	}
	
	private void crawNet(String fileName) {
		int row = 0;
		Document document = null;
		String htmPath;
		Element liElement;
		Element emElement;
		switch (fileName) {
		case "infor_sch":
			CrawMain_log.info("��ȡѧУ������վԴ��");
			//System.out.println("infor_sch");
			try {
				htmPath = "https://www.jnu.edu.cn/xxgg/list" + listNum + ".htm";
				document = Jsoup.connect(htmPath).get();
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, "������ȡѧУ����ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡѧУ����ʧ��");
				
				e.printStackTrace();
			}
			preserveDoc(document);
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element divElement = document.selectFirst("div.content");
			Elements links = divElement.select("p");
			for(Element link : links) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;

		case "infor_schIn":
			CrawMain_log.info("��ȡУ��֪ͨ��վԴ��");
			//System.out.println("infor_shcIn");
			try {
				htmPath = "https://www.jnu.edu.cn/tz/list" + listNum + ".psp";
				document = Jsoup.connect(htmPath).get();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡУ��֪ͨʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				
				CrawMain_log.error("��ȡУ��֪ͨʧ��");
				e.printStackTrace();
				
			}
			preserveDoc(document);
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element tableElement = document.selectFirst("div");
//			Element tbodyElement = tableElement.selectFirst("tbody");
			Elements links_schIn = tableElement.select("table").select("tbody").select("tr");
			for(Element link: links_schIn) {
				Element aElement = link.selectFirst("a");
				Element timeElement = link.selectFirst("td.time");
				content[row][0] = aElement.text();
				content[row][1] = timeElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "infor_meeting":
			CrawMain_log.info("��ȡÿ�ܻ�����վԴ��");
			//System.out.println("infor_meeting");
			try {
				htmPath = "https://www.jnu.edu.cn/gg/list" + listNum + ".psp";
				document = Jsoup.connect(htmPath).get();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡÿ�ܻ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡÿ�ܻ���ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element divElement_meeting = document.selectFirst("div.content");
			Elements links_meeting = divElement_meeting.select("p");
			for(Element link : links_meeting) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "infor_lec":
			CrawMain_log.info("��ȡѧ��������վԴ��");
			//System.out.println("infor_meeting");
			try {
				htmPath = "https://www.jnu.edu.cn/xsjz/list" + listNum + ".htm";
				document = Jsoup.connect(htmPath).get();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡѧ������ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡѧ������ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			
			Element divElement_lec = document.selectFirst("div.content");
			Elements links_lec = divElement_lec.select("p");
			for(Element link : links_lec) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "infor_office":
			CrawMain_log.info("��ȡ������վԴ��");
			//System.out.println("infor_meeting");
			try {
				htmPath = "https://jwc.jnu.edu.cn/SmallClass_index.asp?BigClassName=%BD%CC%CE%F1%B4%A6&SmallClassName=%CD%A8%D6%AA&page=" + listNum;
				document = Jsoup.connect(htmPath).get();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ����ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			
			liElement = document.selectFirst("tbody");
			String num = liElement.getElementsByIndexEquals(21).text();
			int startNum = num.indexOf("/") + 1;
			int endNum = num.indexOf("ҳ") - 1;
			num = num.substring(startNum, endNum);
			appFrame.setListNumMax(Integer.parseInt(num));
			
//			emElement = liElement.selectFirst("em.all_pages");
//			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			int count = 0;
			Elements iElements = liElement.getElementsByTag("tbody");
			liElement = iElements.last();
			Elements links_office = liElement.select("tr");
			for(Element link : links_office) {
				if(count > 0 && count < 21) {
					
					Element tdElement = link.selectFirst("td");
					Element tdElement2 = link.selectFirst("td").nextElementSibling();
					Element aElement = tdElement.selectFirst("a");
//					System.out.println(aElement.attr("abs:href"));
//					System.out.println(tdElement.text());
//					System.out.println(tdElement2.text());
//					System.out.println("-------------");
					content[row][0] = tdElement.text();
					content[row][1] = tdElement2.text();
					content[row][2] = aElement.attr("abs:href");
					row++;
				}
				count++;	
			}
			break;
			
			
		case "work_new":
			CrawMain_log.info("��ȡ��̬����");
			//System.out.println("work_new");
			try {
				htmPath = "https://career.jnu.edu.cn/eweb/jygl/index.so?modcode=jyw_xwgg&subsyscode=jyw&type=searchNews&newsType=xwdt&type=goPager&requestPager=pager&pageMethod=next&currentPage="+(listNum-1);
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ���Ŷ�̬ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ���Ŷ�̬ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			//��ȡ���ҳ��
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_new = document.selectFirst("ul.sy_inf");
			Elements links_new = divElement_new.select("li");
			for(Element link : links_new) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "work_ad":
			CrawMain_log.info("��ȡ֪ͨ���");
			//System.out.println("work_ad");
			try {
				htmPath = "https://career.jnu.edu.cn/eweb/jygl/index.so?modcode=jyw_tzgg&subsyscode=jyw&type=searchNews&newsType=tzgg&type=goPager&requestPager=pager&pageMethod=next&currentPage="+(listNum-1);
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ֪ͨ���ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_ad = document.selectFirst("ul.sy_inf");
			Elements links_ad = divElement_ad.select("li");
			for(Element link : links_ad) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "work_hotpoint":
			CrawMain_log.info("��ȡ��Ƹ�ȵ�");
			//System.out.println("work_hotpoint");
			try {
				htmPath = "https://career.jnu.edu.cn/eweb/jygl/zpfw.so?modcode=jygl_scfwzpxx&subsyscode=zpfw&type=searchZprd&sysType=TPZPFW&zpxxType=new&type=goPager&requestPager=pager&pageMethod=next&currentPage="+(listNum-1);
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ��Ƹ�ȵ�ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ��Ƹ�ȵ�ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_hotpoint = document.selectFirst("div.z_newsl").selectFirst("ul");
			Elements links_hotpoint = divElement_hotpoint.select("li");
			boolean first = true;
			for(Element link : links_hotpoint) {
				if (first) {
					first = false;
					continue;
				}
				Elements aElement = link.select("div");
				content[row][0] = aElement.get(0).text();
				content[row][1] = aElement.get(1).text();
				content[row][2] = "https://career.jnu.edu.cn/eweb/jygl/zpfw.so?modcode=jygl_zpfwzpgg&subsyscode=zpfw&type=view&id="+aElement.get(0).selectFirst("a").attr("abs:onclick").substring(46, 68);//��ȡ����
				row++;
			}
			break;
			
		case "work_policy":
			CrawMain_log.info("��ȡ����");
			//System.out.println("work_policy");
			try {
				htmPath = "https://career.jnu.edu.cn/eweb/jygl/index.so?modcode=jyw_xwgg&subsyscode=jyw&type=searchNews&newsType=dfzc&type=goPager&requestPager=pager&pageMethod=next&currentPage="+(listNum-1);
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ���߽��ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ���߽��ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_policy = document.selectFirst("ul.sy_inf");
			Elements links_policy = divElement_policy.select("li");
			for(Element link : links_policy) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "calendar":
			CrawMain_log.info("��ȡ������Ϣ");
			//System.out.println("work_policy");
			try {
				htmPath = "https://career.jnu.edu.cn/eweb/jygl/zpfw.so?modcode=jygl_xjhxxck&subsyscode=zpfw&type=searchXjhxx&xjhType=all&type=goPager&requestPager=pager&pageMethod=next&currentPage=0";
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ������Ϣʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ������Ϣʧ��");
				e.printStackTrace();
			}
			/*
			preserveDoc(document);
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			*/
			HashMap<String, ArrayList<Recruit>> temp=appFrame.getdatacalendar();
			Element divElement_calendar = document.selectFirst("div.z_newsl").selectFirst("ul");
			Elements links_calendar = divElement_calendar.select("li");
			
			boolean first_1 = true;
			for(Element link : links_calendar) {
				if (first_1) {
					first_1 = false;
					continue;
				}
				Elements aElement = link.select("div");
				//���������ĵ�λ���ƣ��ص㣬ʱ�������ӣ�Ҫ�����½������飨��APPFrame���½����ɣ������ﷵ�������
				Recruit info = new Recruit();
				info.setCompany(aElement.get(0).text());//��λ����
				info.setPlace(aElement.get(2).text());//�ص�
				info.setTime(aElement.get(4).text());//����ʱ��
				info.setUrl("https://career.jnu.edu.cn/eweb/jygl/zpfw.so?modcode=jygl_xjhxxck&subsyscode=zpfw&type=viewXjhxx&id="+aElement.get(0).selectFirst("a").attr("abs:onclick").substring(47, 69));
				if(temp.get(aElement.get(3).text())!=null)
					temp.get(aElement.get(3).text()).add(info);//���
				else {
					ArrayList<Recruit> t=new ArrayList<>();
					t.add(info);
					temp.put(aElement.get(3).text(), t);
				}
			}
			break;
			//lql
			//lql
			//��ȡ����Ժ֪ͨ
		case "college_business":
			CrawMain_log.info("��ȡ����Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				htmPath = "https://gjsxy.jnu.edu.cn/tzgg/list"+listNum+".htm";
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			//��ȡ���ҳ��
			Elements t1 = document.getElementsByClass("all_pages");
			liElement = t1.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			
			Element divElement_business = document.selectFirst("ul.common-list");
			Elements links_business = divElement_business.select("li");
			for(Element link : links_business) {
				Element aElement = link.selectFirst("a");
				Element time = link.selectFirst("span");
				//System.out.println(aElement.text()+time.text()+aElement.attr("abs:href"));
				content[row][0] = aElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			//��ȡ����Ժ֪ͨ
		case "college_translation":
			CrawMain_log.info("��ȡ����Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				htmPath = "https://translation.jnu.edu.cn/7832/list"+listNum+".htm";
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			//��ȡ���ҳ��
			Elements t2 = document.getElementsByClass("all_pages");
			liElement = t2.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			//System.out.println(liElement.text());
			
			Element divElement_translation = document.selectFirst("ul.xwlb");
			Elements links_translation = divElement_translation.select("li");
			for(Element link : links_translation) {
				Element aElement = link.selectFirst("a");
				Element time = link.selectFirst("span");
				//System.out.println(aElement.text()+time.text()+aElement.attr("abs:href"));
				content[row][0] = aElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			//��ȡ����Ժ֪ͨ
		case "college_Humanities":
			CrawMain_log.info("������Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				htmPath = "https://rwxy.jnu.edu.cn/11063/list"+listNum+".htm";
				document = Jsoup.connect(htmPath).get();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}
			preserveDoc(document);
			//��ȡ���ҳ��
			Elements t3 = document.getElementsByClass("all_pages");
			liElement = t3.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			//System.out.println(liElement.text());
			
			Element divElement_Humanities = document.selectFirst("ul.news_list");
			Elements links_Humanities = divElement_Humanities.select("li");
			for(Element link : links_Humanities) {
				Element aElement = link.selectFirst("span");
			    Element tElement = aElement.selectFirst("a");
			    Elements times = link.getElementsByClass("news_meta");
			    Element time = times.get(0);
			    //System.out.println(tElement.text()+time.text()+tElement.attr("abs:href"));
			    content[row][0] = tElement.text();
			    content[row][1] = time.text();
			    content[row][2] = tElement.attr("abs:href");
				row++;
			}
			break;
			
			
		}
		
	}
	
	private void preserveDoc(Document document) {
//		System.out.println(document.toString());
		if(!file.exists()) {
			try {
				CrawMain_log.info("createNewFile:" + fileName + "_page" + listNum + ".xml");
				//System.out.println("createNewFile:" + fileName + "_page" + listNum + ".xml");
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "�����ļ������ڣ�����ʧ��", "�����ļ�����", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("�����ļ������ڣ�����ʧ��");
				e.printStackTrace();
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(document.toString());
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void crawDoc(String pathName) {
		int row = 0;
		Document document = null;
		File file = null;
		Element liElement;
		Element emElement;
		switch (fileName) {
		case "infor_sch":
			CrawMain_log.debug("����ѧУ�������");
			//System.out.println("infor_sch");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GBK");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ�ѧУ����ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ�ѧУ����ʧ��");
				e.printStackTrace();
			}
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element divElement = document.selectFirst("div.content");
			Elements links = divElement.select("p");
			for(Element link : links) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;

		case "infor_schIn":
			CrawMain_log.debug("����У��֪ͨ����");
			//System.out.println("infor_shcIn");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GBK");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ�У��֪ͨʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ�У��֪ͨʧ��");
				e.printStackTrace();
			}
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element tableElement = document.selectFirst("div");
//			Element tbodyElement = tableElement.selectFirst("tbody");
			Elements links_schIn = tableElement.select("table").select("tbody").select("tr");
			for(Element link: links_schIn) {
				Element aElement = link.selectFirst("a");
				Element timeElement = link.selectFirst("td.time");
				content[row][0] = aElement.text();
				content[row][1] = timeElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "infor_meeting":
			CrawMain_log.debug("����ÿ�ܻ������");
			//System.out.println("infor_meeting");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GBK");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ�ѧУ����ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ�ѧУ����ʧ��");
				e.printStackTrace();
			}
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element divElement_meeting = document.selectFirst("div.content");
			Elements links_meeting = divElement_meeting.select("p");
			for(Element link : links_meeting) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "infor_lec":
			CrawMain_log.debug("����ѧ����������");
			//System.out.println("infor_meeting");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GBK");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ�ѧУ����ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ�ѧУ����ʧ��");
				e.printStackTrace();
			}
			liElement = document.selectFirst("li.page_jump");
			emElement = liElement.selectFirst("em.all_pages");
			appFrame.setListNumMax(Integer.parseInt(emElement.text()));
			
			Element divElement_lec = document.selectFirst("div.content");
			Elements links_lec = divElement_lec.select("p");
			for(Element link : links_lec) {
				Element aElement = link.selectFirst("a");
				Element spanElement = link.selectFirst("span");
				content[row][0] = aElement.text();
				content[row][1] = spanElement.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "work_new":
			CrawMain_log.info("��ȡ�����ļ����Ŷ�̬");
			//System.out.println("work_new");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ����Ŷ�̬ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ����Ŷ�̬ʧ��");
				e.printStackTrace();
			}
			
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			//��ȡ���ҳ��
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_new = document.selectFirst("ul.sy_inf");
			Elements links_new = divElement_new.select("li");
			for(Element link : links_new) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "work_ad":
			CrawMain_log.info("��ȡ�����ļ�֪ͨ���");
			//System.out.println("work_ad");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ�֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ�֪ͨ���ʧ��");
				e.printStackTrace();
			}
			
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_ad = document.selectFirst("ul.sy_inf");
			Elements links_ad = divElement_ad.select("li");
			for(Element link : links_ad) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "work_hotpoint":
			CrawMain_log.info("��ȡ�����ļ���Ƹ�ȵ�");
			//System.out.println("work_hotpoint");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ���Ƹ�ȵ�ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ���Ƹ�ȵ�ʧ��");
				e.printStackTrace();
			}
			
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_hotpoint = document.selectFirst("div.z_newsl").selectFirst("ul");
			Elements links_hotpoint = divElement_hotpoint.select("li");
			boolean first = true;
			for(Element link : links_hotpoint) {
				if (first) {
					first = false;
					continue;
				}
				Elements aElement = link.select("div");
				content[row][0] = aElement.get(0).text();
				content[row][1] = aElement.get(1).text();
				content[row][2] = "https://career.jnu.edu.cn/eweb/jygl/zpfw.so?modcode=jygl_zpfwzpgg&subsyscode=zpfw&type=view&id="+aElement.get(0).selectFirst("a").attr("abs:onclick").substring(46, 68);//��ȡ����
				row++;
			}
			break;
			
		case "work_policy":
			CrawMain_log.info("��ȡ�����ļ�����");
			//System.out.println("work_policy");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "��ȡ�����ļ����߽��ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("��ȡ�����ļ����߽��ʧ��");
				e.printStackTrace();
			}
			
			//��ȡ���ҳ��
			liElement = document.getElementById("pageForm");
			emElement = liElement.selectFirst("span");
			appFrame.setListNumMax(Integer.parseInt(emElement.text().substring(emElement.text().indexOf('/')+1, emElement.text().indexOf('ҳ')).trim()));
			
			Element divElement_policy = document.selectFirst("ul.sy_inf");
			Elements links_policy = divElement_policy.select("li");
			for(Element link : links_policy) {
				Element aElement = link.selectFirst("a");
				Element spanElement = aElement.selectFirst("span");
				Element time = link.selectFirst("span.gg_year");

				content[row][0] = spanElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			
		case "college_business":
			CrawMain_log.info("��ȡ����Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}

			//��ȡ���ҳ��
			Elements t1 = document.getElementsByClass("all_pages");
			liElement = t1.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			
			Element divElement_business = document.selectFirst("ul.common-list");
			Elements links_business = divElement_business.select("li");
			for(Element link : links_business) {
				Element aElement = link.selectFirst("a");
				Element time = link.selectFirst("span");
				//System.out.println(aElement.text()+time.text()+aElement.attr("abs:href"));
				content[row][0] = aElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			//��ȡ����Ժ֪ͨ
		case "college_translation":
			CrawMain_log.info("��ȡ����Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}

			//��ȡ���ҳ��
			Elements t2 = document.getElementsByClass("all_pages");
			liElement = t2.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			//System.out.println(liElement.text());
			
			Element divElement_translation = document.selectFirst("ul.xwlb");
			Elements links_translation = divElement_translation.select("li");
			for(Element link : links_translation) {
				Element aElement = link.selectFirst("a");
				Element time = link.selectFirst("span");
				//System.out.println(aElement.text()+time.text()+aElement.attr("abs:href"));
				content[row][0] = aElement.text();
				content[row][1] = time.text();
				content[row][2] = aElement.attr("abs:href");
				row++;
			}
			break;
			//��ȡ����Ժ֪ͨ
		case "college_Humanities":
			CrawMain_log.info("������Ժ֪ͨ");
			//System.out.println("work_policy");
			try {
				file = new File(pathName);
				document = Jsoup.parse(file, "GB2312");
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, "������ȡ����Ժ֪ͨ���ʧ��", "��ȡ����ʧ��", JOptionPane.ERROR_MESSAGE);
				CrawMain_log.error("������ȡ����Ժ֪ͨ���ʧ��");
				e.printStackTrace();
			}

			//��ȡ���ҳ��
			Elements t3 = document.getElementsByClass("all_pages");
			liElement = t3.get(0);
			appFrame.setListNumMax(Integer.parseInt(liElement.text()));
			//System.out.println(liElement.text());
			
			Element divElement_Humanities = document.selectFirst("ul.news_list");
			Elements links_Humanities = divElement_Humanities.select("li");
			for(Element link : links_Humanities) {
				Element aElement = link.selectFirst("span");
			    Element tElement = aElement.selectFirst("a");
			    Elements times = link.getElementsByClass("news_meta");
			    Element time = times.get(0);
			    //System.out.println(tElement.text()+time.text()+tElement.attr("abs:href"));
			    content[row][0] = tElement.text();
			    content[row][1] = time.text();
			    content[row][2] = tElement.attr("abs:href");
				row++;
			}
			break;

		}
	}
}
