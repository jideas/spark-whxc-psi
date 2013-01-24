package com.spark.psi.browser.functions.internal;

import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.spark.common.components.pages.MainFunction;

public abstract class PSIFunction implements MainFunction {
	
	protected abstract String getIconName();
		
	public boolean isPutMain(){
	    return false;
	}

	public ImageDescriptor getTitleIcon() {
		return FunctionImages.getImage("images/icons/22/" + getIconName() + "_22.png");
	}

	public ImageDescriptor getSmallNormalIcon() {
		return FunctionImages.getImage("images/icons/32/" + getIconName() + "_32_n.png");
	}

	public ImageDescriptor getSmallHoverIcon() {
		return FunctionImages.getImage("images/icons/32/" + getIconName() + "_32_h.png");
	}

	public ImageDescriptor getMiddleNormalIcon() {
		return FunctionImages.getImage("images/icons/38/" + getIconName() + "_38_n.png");
	}

	public ImageDescriptor getMiddleHoverIcon() {
		return FunctionImages.getImage("images/icons/38/" + getIconName() + "_38_h.png");
	}

	public ImageDescriptor getLargeNormalIcon() {
		return FunctionImages.getImage("images/icons/48/" + getIconName() + "_48_n.png");
	}

	public ImageDescriptor getLargeHoverIcon() {
		return FunctionImages.getImage("images/icons/48/" + getIconName() + "_48_h.png");
	}

}
