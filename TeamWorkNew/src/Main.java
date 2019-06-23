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
        //通知的需求更改
		//论坛的功能的优化
		//添加教务处，教务处功能更新，识别率过低
	}
}
