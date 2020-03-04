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
			Net_log.info("联网成功");
			//System.out.println("Connect successful");
			connect = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "未联网或非校园网，已从最近联网对应文档读取信息", "联网错误", JOptionPane.WARNING_MESSAGE);
			Net_log.warn("联网失败");
			//System.out.println("Connect failed");
			connect = false;
		}
	}
	
	public boolean getConnect() {
		return connect;
	}
}
