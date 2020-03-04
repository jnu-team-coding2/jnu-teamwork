import org.apache.log4j.Logger;

import Frame.AppFrame;


import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	private static Logger log=Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		log.debug("����ʼ����");
		
		
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		
		new AppFrame().launch();
		
		NativeInterface.runEventPump();
	}
}
