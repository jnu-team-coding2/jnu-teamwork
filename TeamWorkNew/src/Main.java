import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		//饭卡查询
		new AppFrame().launch();

		NativeInterface.runEventPump();
		//添加教务处
	}
}
