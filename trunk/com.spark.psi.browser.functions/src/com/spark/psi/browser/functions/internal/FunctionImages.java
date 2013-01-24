package com.spark.psi.browser.functions.internal;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class FunctionImages {

	public final static String pluginId = "com.spark.psi.browser.functions";

	public static ImageDescriptor getImage(String path) {
		return FileImageDescriptor.createImageDescriptor(pluginId, path);
	}
}
