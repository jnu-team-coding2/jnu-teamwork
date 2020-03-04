package Net;

import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class Net {
	private boolean connect;
	
	private static Logger Net_log=Logger.getLogger(Net.class);
	
	public Net(String net) {
		URL url = null;
		try {
			url = new URL(net);
			InputStream inputStream = url.openStream();
			Net_log.info("�����ɹ�");
			//System.out.println("Connect successful");
			connect = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "δ�������У԰�����Ѵ����������Ӧ�ĵ���ȡ��Ϣ", "��������", JOptionPane.WARNING_MESSAGE);
			Net_log.warn("����ʧ��");
			//System.out.println("Connect failed");
			connect = false;
		}
	}
	
	public boolean getConnect() {
		return connect;
	}
}
