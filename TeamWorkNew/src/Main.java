import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();//电费
		NativeInterface.open();
		//饭卡查询
		new AppFrame().launch();//career

		//修复bug
		NativeInterface.runEventPump();

		//论坛网址炸了
		//添加教务处，教务处功能更新
	}
}
