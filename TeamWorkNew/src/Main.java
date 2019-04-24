import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import org.apache.log4j.Logger;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		
		new AppFrame().launch();
		log.debug("╪сть©Р╪э");
		NativeInterface.runEventPump();

	}
}
