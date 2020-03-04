package Net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import Frame.AppFrame;

public class ItemPreserve {
	private static Logger ItemPreserve_log=Logger.getLogger(ItemPreserve.class);
	
	public ItemPreserve(String fileName, AppFrame appFrame, int clickedRow) {
		int listNum = appFrame.getListNum();
		String parentPathName = "./data/" + fileName + "_page" + listNum;
		String pathName = "./data/" + fileName + "_page" + listNum + "/" + "page_" + listNum + "_" + (clickedRow+1) + ".html";
		File parentFile = new File(parentPathName);
		File file = new File(pathName);
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if(!file.exists()) {
			try {
				ItemPreserve_log.info("createNewFile:" + "./data/" + fileName + "_page" + listNum + "/" + "page_" + listNum + "_" + (clickedRow+1) + ".xml");
				//System.out.println("createNewFile:" + "./data/" + fileName + "_page" + listNum + "/" + "page_" + listNum + "_" + (clickedRow+1) + ".xml");
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "数据文件不存在，创建失败", "数据文件错误", JOptionPane.ERROR_MESSAGE);
				ItemPreserve_log.error("数据文件不存在，创建失败");
				e.printStackTrace();
			}
		}
		
		
		FileWriter fileWriter = null;
		try {
			Document document = Jsoup.connect((String) (appFrame.getContent())[clickedRow][2]).get();
			//fileWriter = new FileWriter(file);
			//fileWriter.write(document.toString());
			//fileWriter.close();
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			writer.write(document.toString());
			writer.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
