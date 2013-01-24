package com.spark.psi.mainpage;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

/**
 * Õº∆¨π§æﬂ¿‡
 * @author yanglin
 *
 */
public final class ImageUtil {
	public static ImageDescriptor crateImage(String imageName) {
		return FileImageDescriptor.createImageDescriptor(
				"com.spark.psi.mainpage","/images/tip/"+imageName);
	}

	public static ImageDescriptor getMessageImage(String path) {
		return FileImageDescriptor.createImageDescriptor("com.spark.psi.mainpage", "/html/app/theme/common/window/"+path);
	}
	
	public static ImageDescriptor getMessageTitleImage() {
		return FileImageDescriptor.createImageDescriptor("com.spark.psi.mainpage", "html/app/theme/common/images/level1/footer/message-n.png");
	}
}
