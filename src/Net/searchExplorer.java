package Net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Crawling.CrawMain;
import Frame.AppFrame;
import Packaging.Backbutton;
import Packaging.searchBackbutton;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
public class searchExplorer extends JPanel{

	JPanel area;
	Net net;
	AppFrame appFrame;
	
    class requestforum extends JPanel{
        //内部类继承JPanel，进行浏览器嵌入
        public  requestforum(String url){
            super.setLayout(new BorderLayout());
            UIUtils.setPreferredLookAndFeel();
            NativeInterface.open();
            JPanel webBrowserPanel = new JPanel();
            webBrowserPanel.setLayout(new BorderLayout(0,0));
            JWebBrowser webBrowser = new JWebBrowser();
            webBrowser.navigate(url);
            webBrowser.setButtonBarVisible(false);
            webBrowser.setMenuBarVisible(false);
            webBrowser.setBarsVisible(false);
            webBrowser.setStatusBarVisible(false);
            webBrowserPanel.add(webBrowser);
            add(webBrowserPanel,BorderLayout.CENTER);
        }
    }
    
    public searchExplorer(AppFrame appFrame, String url,Object[][] tableData) {
    	area = appFrame.getArea();
    	this.appFrame = appFrame;
    	switch (url) {
		case "http://bbs.jnlts.com":
			SwingUtilities.invokeLater(new Runnable() {
	        	 @Override
	            public void run() {
	        		area.removeAll();
	                area.add(new requestforum(url),BorderLayout.CENTER);
	                area.revalidate();
	            }
	        });
			break;
		default:
			SwingUtilities.invokeLater(new Runnable() {
	        	 @Override
	            public void run() {
	        		JButton back = new JButton("返回");
	        		back.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new searchBackbutton(appFrame,tableData);
						}
					});
					area.removeAll();
					area.add(new requestforum(url),BorderLayout.CENTER);
					area.add(back, BorderLayout.SOUTH); area.revalidate();
			
	            }
	        });
			break;
		}
    }
}
