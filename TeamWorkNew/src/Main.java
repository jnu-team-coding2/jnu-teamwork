import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();//电费
		NativeInterface.open();
		//饭卡查询
		new AppFrame().launch();

		//修复bug
		NativeInterface.runEventPump();

		//论坛的功能的优化。
		//添加教务处，教务处功能更新
	}
}
