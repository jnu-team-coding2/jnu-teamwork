package School_office;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import chrriis.common.Utils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class Plan {
	String[] string;
	static String title;
	public Plan(){
		
	}
	
	public static String[] pStrings(Document doc){
		
		
		Document plan_message=doc;
		
		Elements elements=doc.getElementsByClass("Gridview").select("td");
		String[] depart=new String[elements.size()];
		   for(int j=0;j<elements.size();j++){
			   title=elements.get(j).text();
			   depart[j]=title;
		   }
		
		/*for(int i=0;i<depart.length;i++){
			System.out.println(depart[i]);
		}*/
		return depart;
	}

	
}
