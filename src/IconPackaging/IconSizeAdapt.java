package IconPackaging;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

//***********************
//editor：zzf            *
//功能：添加Icon自适应组件大小  *
//***********************
public class IconSizeAdapt {
	ImageIcon imageIcon;
	JComponent jComponent;
	public IconSizeAdapt(ImageIcon imageIcon, JComponent jComponent) {
		this.imageIcon = imageIcon;
		this.jComponent = jComponent;
	}
	
	public ImageIcon getAdaptIcon() {
		Image image= imageIcon.getImage().getScaledInstance(jComponent.getHeight(), jComponent.getHeight(), imageIcon.getImage().SCALE_DEFAULT);
//		System.out.println(jComponent.getFont().getSize());
		imageIcon = new ImageIcon(image);
		return imageIcon;
	}
}
