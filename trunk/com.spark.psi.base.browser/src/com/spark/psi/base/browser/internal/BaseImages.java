package com.spark.psi.base.browser.internal;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class BaseImages {

	public final static String pluginId = "com.spark.psi.base.browser";

	public static ImageDescriptor getImage(String path) {
		return FileImageDescriptor.createImageDescriptor(pluginId, path);
	}
}
