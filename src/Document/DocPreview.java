package Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;

import Frame.AppFrame;

public class DocPreview {
	String content = null;
	File previewFile=null;
	
	public DocPreview(AppFrame appFrame) throws IOException {
		//将html模板转换为String流
		File templateFile = new File(System.getProperty("user.dir")+ "/template/LeaveApply.html");
		System.out.println(templateFile);
		content = FileUtils.readFileToString(templateFile, "GBK");
		//替换模板中的数据
		ReplaceData(appFrame);
		
		//生成预览的html文件
		previewFile = CreateHtmlFile(System.getProperty("user.dir")+ "/template/Preview.html");
		
	}
	//替换模板中的数据
	public void ReplaceData(AppFrame af) {
		content = content.replaceAll("\\$\\{stu_id\\}",af.getStuNum());
		content = content.replaceAll("\\$\\{stu_name\\}",af.getName());
		content = content.replaceAll("\\$\\{stu_college\\}",af.getCollege());
		content = content.replaceAll("\\$\\{stu_major\\}",af.getMajor());
		content = content.replaceAll("\\$\\{reason\\}",af.getReasonStr());
		content = content.replaceAll("\\$\\{start_year\\}",af.getYear1());
		content = content.replaceAll("\\$\\{start_month\\}",af.getMonth1());
		content = content.replaceAll("\\$\\{start_day\\}",af.getDay1());
		content = content.replaceAll("\\$\\{end_year\\}",af.getYear2());
		content = content.replaceAll("\\$\\{end_month\\}",af.getMonth2());
		content = content.replaceAll("\\$\\{end_day\\}",af.getDay2());
	}
	
	//生成预览的html文件
	public File CreateHtmlFile(String path) throws UnsupportedEncodingException, IOException {
		
		
		File file=new File(path);
		OutputStream fos = new FileOutputStream(file);
		fos.write(content.getBytes("GBK"));
		fos.flush();
		fos.close();
		return file;
	}
	public File getPreviewFile() {
		return previewFile;
	}
	
}
