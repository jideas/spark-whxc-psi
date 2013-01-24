package com.spark.psi.account.browser.internal;

import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class AccountImages {
	public final static String pluginId = "com.spark.psi.account.browser";

	public static ImageDescriptor getImage(String path) {
		return FileImageDescriptor.createImageDescriptor(pluginId, path);
	}
}
