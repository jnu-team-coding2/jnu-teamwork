package Document;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import Frame.AppFrame;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

//����ʵ����д�ĵ�����word����
public class DocExport {
	
	public String SavePath;
	public Map<String,Object> dataMap;
	public File outFile=null;
	
	public DocExport(AppFrame appFrame) throws IOException, TemplateException {
		dataMap = new HashMap<String, Object>();
		
		dataMap=SaveData(dataMap, appFrame);
            
        SavePath=GetFilePath();
        
        outFile=InputData2Template();
        
	}
	
	
	//��ȡappframe����д������
	public Map<String,Object> SaveData(Map<String,Object> map,AppFrame af) {
            //���
            dataMap.put("stu_id",af.getStuNum() );
            dataMap.put("stu_name",af.getName() );
            dataMap.put("stu_college",af.getCollege());
            dataMap.put("stu_major", af.getMajor());
            dataMap.put("reason", af.getReasonStr());
            
            dataMap.put("start_year", af.getYear1());
            dataMap.put("start_month", af.getMonth1());
            dataMap.put("start_day", af.getDay1());
            
            dataMap.put("end_year", af.getYear2());
            dataMap.put("end_month", af.getMonth2());
            dataMap.put("end_day", af.getDay2());
		return map;
		
	}
	
	//��ȡ����·��
	public String GetFilePath() {
		String path;
		SaveDoc save= new SaveDoc(this);
        path=save.getPath();
		return path;
	}
	
	//��ģ���������&�����ļ�
	public File InputData2Template() throws IOException, TemplateException {
		File file = null;
		try {
		//Configuration ���ڶ�ȡftl�ļ�
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
       
        File file1 =new File(System.getProperty("user.dir") + "/template");//���·�����ҵ�ģ��
        configuration.setDirectoryForTemplateLoading(file1);
      //����ĵ�·��������
        file = new File(SavePath);
        
        Template template = configuration.getTemplate("LeaveApply.ftl", "utf-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"), 10240);
        template.process(dataMap, out);
        out.close();
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
		return file;
        
	}


	public File getOutFile() {
		return outFile;
	}


	public void setSavePath(String savePath) {
		SavePath = savePath;
	}
	
}
