package School_office;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.lowagie.text.Document;

import chrriis.common.Utils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Office {
	static String title,title2;
	public Office(){
		//String[] output=grade_message();
	}
	public static String[] grade_message(String grade_time,org.jsoup.nodes.Document doc){
		
		   org.jsoup.nodes.Document document = doc;
		  // System.out.println(doc.body());
			//document = Jsoup.parse(htmlf,"UTF-8");
			// TODO Auto-generated catch block
		  // System.out.println(document.title());
		   //System.out.println("选择的学期是："+grade_time);
		   //Elements element=document.getElementsByAttributeValue("class","Gridview" );
		   //Elements element2=document.getElementsByAttributeValue("class", "DGAlternatingItemStyle");
		   Elements element=document.getElementsByClass("Gridview").select("td");
		   String[] depart=new String[element.size()];
		   for(int j=0;j<element.size();j++){
			   title=element.get(j).text();
			   //System.out.println(title);
			   depart[j]=title;
		   }
		  
		   
		   String[] type=new String[200];
		   int begin=0,end=0;
		   //System.out.println(depart[2]);
		   if(grade_time.equals("大一上")){
			  int i=0,j=0;
			  while(depart[i].equals("2016-2017")==false)
				  i++;
				   begin=i;
			  while((depart[j].equals("2016-2017")&&depart[j+1].equals("下"))==false)
				  j++;
			  end=j;
		   }
		   if(grade_time.equals("大一下")){
				  int i=0,j=0;
				  while((depart[i].equals("2016-2017")&&depart[i+1].equals("下"))==false)
					  i++;
					   begin=i;
				  while((depart[j].equals("2017-2018")&&depart[j+1].equals("上"))==false)
					  j++;
				  end=j;
			   }
		   if(grade_time.equals("大二上")){
				  int i=0,j=0;
				  while((depart[i].equals("2017-2018")&&depart[i+1].equals("上"))==false)
					  i++;
					   begin=i;
				  while((depart[j].equals("2017-2018")&&depart[j+1].equals("下"))==false)
					  j++;
				  end=j;
			   }
		   if(grade_time.equals("大二下")){
				  int i=0,j=0;
				  while((depart[i].equals("2017-2018")&&depart[i+1].equals("下"))==false)
					  i++;
					   begin=i;
				  while((depart[j].equals("2018-2019")&&depart[j+1].equals("上"))==false)
					  j++;
				  end=j;
			   }
		   if(grade_time.equals("大三上")){
				  int i=0,j=0;
				  while((depart[i].equals("2018-2019")&&depart[i+1].equals("上"))==false)
					  i++;
					   begin=i;
				  while((depart[j].equals("2018-2019")&&depart[j+1].equals("下"))==false){
					  j++;
					  if(j==depart.length)
						  break;
				  }
				  end=j;
			   }
		   /*if(grade_time.equals("大三下"))
			   type=null;
		   if(grade_time.equals("大四上"))
			   type=null;*/
		   //System.out.println("begin="+begin);
		   
		   
		   System.arraycopy(depart, begin, type, 0, end-begin);
		   
		   
		   
		   return type;
	}
	
	
}	