package IconPackaging;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

//***********************
//editor��zzf            *
//���ܣ����Icon����Ӧ�����С  *
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
