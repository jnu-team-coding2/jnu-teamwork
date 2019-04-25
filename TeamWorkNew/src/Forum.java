import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
public class Forum extends JPanel{

    class requestforum extends JPanel{
        //内部类继承JPanel，进行浏览器嵌入
        public  requestforum(){
            super.setLayout(new BorderLayout());
            UIUtils.setPreferredLookAndFeel();
            NativeInterface.open();
            JPanel webBrowserPanel = new JPanel();
            webBrowserPanel.setLayout(new BorderLayout(0,0));
            JWebBrowser webBrowser = new JWebBrowser();
            String url = "http://bbs.jnlts.com/";
            webBrowser.navigate(url);
            webBrowser.setButtonBarVisible(false);
            webBrowser.setMenuBarVisible(false);
            webBrowser.setBarsVisible(false);
            webBrowser.setStatusBarVisible(false);
            webBrowserPanel.add(webBrowser);
            add(webBrowserPanel,BorderLayout.CENTER);
        }
    }
    public Forum(JPanel area) {
        System.out.println("forum");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                area.add(new requestforum(),BorderLayout.CENTER);
            }
        });
    }
}