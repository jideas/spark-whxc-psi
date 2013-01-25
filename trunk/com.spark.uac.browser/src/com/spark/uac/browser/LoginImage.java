package com.spark.uac.browser;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class LoginImage {
	public final static String pluginId = "com.spark.uac.browser";

	public static ImageDescriptor getImage(String path) {
		return FileImageDescriptor.createImageDescriptor(pluginId, "images/window/"+path);
	}
}
