package Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveDoc {
	File file;
	String path;
	
	public String getPath() {
		return path;
	}
	public SaveDoc(DocExport export) {
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.doc","doc");
		JFileChooser fc=new JFileChooser();
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int result=fc.showSaveDialog(null);
		if (result==JFileChooser.APPROVE_OPTION) {
			file=fc.getSelectedFile();
			if (!file.getPath().endsWith(".doc")) {
				file=new File(file.getPath()+".doc");
			}
			System.out.println("file path="+file.getPath());
			/*FileOutputStream fos=null;
			try {
				if (!file.exists()) {//�ļ������� �򴴽�һ��
					file.createNewFile();
				}
				fos=new FileOutputStream(file);
				//fos.write("�ļ�����".getBytes());
				fos.flush();
			} catch (IOException e) {
				System.err.println("�ļ�����ʧ�ܣ�");
				e.printStackTrace();
			}finally{
				if (fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}*/
		}
		
		//export.setPath(file.getPath());
		path=file.getPath();
	}	
}
