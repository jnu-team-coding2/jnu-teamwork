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

//此类实现填写文档导出word功能
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
	
	
	//获取appframe中填写的数据
	public Map<String,Object> SaveData(Map<String,Object> map,AppFrame af) {
            //编号
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
	
	//获取保存路径
	public String GetFilePath() {
		String path;
		SaveDoc save= new SaveDoc(this);
        path=save.getPath();
		return path;
	}
	
	//向模板填充数据&生成文件
	public File InputData2Template() throws IOException, TemplateException {
		File file = null;
		try {
		//Configuration 用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
       
        File file1 =new File(System.getProperty("user.dir") + "/template");//相对路径，找到模板
        configuration.setDirectoryForTemplateLoading(file1);
      //输出文档路径及名称
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
