import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		
		new AppFrame().launch();
		
		NativeInterface.runEventPump();

		//论坛的功能的优化
	}
}
