package Packaging;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import Crawling.CrawMain;
import Frame.AppFrame;
import Net.Net;

public class Backbutton {
	
	public static Logger Backbutton_log=Logger.getLogger(Backbutton.class);
	
	public Backbutton(AppFrame appFrame) {
		JPanel area = appFrame.getArea();
		Backbutton_log.debug("返回通知界面");
		//System.out.println("返回");
		area.removeAll();
		Net net = new Net("http://www.baidu.com");
		if(net.getConnect()) {
			new CrawMain(appFrame.getListType(), appFrame, true);
		}else {
			new CrawMain(appFrame.getListType(), appFrame, false);
		}
		String type = appFrame.getListType().substring(0, 5);
		
		Backbutton_log.debug("返回"+type);
		
		//System.out.println(type);
		switch (type) {
		case "infor":
			Backbutton_log.debug("添加信息界面布局");
			//System.out.println("add informlayout");
			area.add(appFrame.informLayout(appFrame.getContent()), BorderLayout.CENTER);
			break;
		
		case "work_":
			Backbutton_log.debug("添加工作界面布局");
			//System.err.println("add workinfolayout");
			area.add(appFrame.workinfoLayout(), BorderLayout.CENTER);
			break;

		case "":
			Backbutton_log.debug("添加选课界面布局");
			//System.out.println("add chooseclasslayout");
			area.add(appFrame.chooseClassLayout(), BorderLayout.CENTER);
			break;
			
		case "searc":
			Backbutton_log.debug("添加信息界面布局");
			//System.out.println("add informlayout");
			search searching=new search();
			Object[][] tableData_result=searching.find_searchresult(appFrame.key,appFrame);
			area.add(appFrame.informLayout(tableData_result), BorderLayout.CENTER);
			break;
		}
		area.revalidate();
		System.out.println("--------");
	}
}
