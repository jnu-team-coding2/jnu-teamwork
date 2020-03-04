package Packaging;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import Crawling.CrawMain;
import Frame.AppFrame;
import Net.Net;

public class searchBackbutton {
	
	public static Logger Backbutton_log=Logger.getLogger(searchBackbutton.class);
	
	public searchBackbutton(AppFrame appFrame,Object[][] tableData) {
		JPanel area = appFrame.getArea();
		Backbutton_log.debug("返回搜索界面");
		//System.out.println("返回");
		area.removeAll();
		Net net = new Net("http://www.baidu.com");
		if(net.getConnect()) {
			new CrawMain(appFrame.getListType(), appFrame, true);
		}else {
			new CrawMain(appFrame.getListType(), appFrame, false);
		}
		area.add(appFrame.searchLayout(tableData), BorderLayout.CENTER);
		//System.out.println(type);
		area.revalidate();
		System.out.println("--------");
	}
}
