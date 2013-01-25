package com.spark.psi.order.browser.internal;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class OrderImages {
	public final static String pluginId = "com.spark.psi.order.browser";

	public static ImageDescriptor getImage(String path) {
		return FileImageDescriptor.createImageDescriptor(pluginId, path);
	}
}
