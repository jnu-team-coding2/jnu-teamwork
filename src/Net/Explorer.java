package Net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Crawling.CrawMain;
import Frame.AppFrame;
import Packaging.Backbutton;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
public class Explorer extends JPanel{

	JPanel area;
	Net net;
	AppFrame appFrame;
	
    class requestforum extends JPanel{
        //�ڲ���̳�JPanel�����������Ƕ��
        public  requestforum(String url){
            super.setLayout(new BorderLayout());
            UIUtils.setPreferredLookAndFeel();
            NativeInterface.open();
            JPanel webBrowserPanel = new JPanel();
            webBrowserPanel.setLayout(new BorderLayout(0,0));
            JWebBrowser webBrowser = new JWebBrowser();
//            String url = "http://www.baidu.com/";
            webBrowser.navigate(url);
            webBrowser.setButtonBarVisible(false);
            webBrowser.setMenuBarVisible(false);
            webBrowser.setBarsVisible(false);
            webBrowser.setStatusBarVisible(false);
            webBrowserPanel.add(webBrowser);
            add(webBrowserPanel,BorderLayout.CENTER);
        }
    }
    
    public Explorer(AppFrame appFrame, String url) {
//        System.out.println("forum");
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
	        		JButton back = new JButton("����");
	        		back.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new Backbutton(appFrame);
						}
					});
	        		area.removeAll();
	                area.add(new requestforum(url),BorderLayout.CENTER);
	                area.add(back, BorderLayout.SOUTH);
	                area.revalidate();
	            }
	        });
			break;
		}
    }
}