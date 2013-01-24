package com.spark.common.components.controls.text;


import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class SText extends AbstractSText implements STextImp{

	
	public SText(Composite parent){
	    super(parent, false);
    }

	@Override
    protected void setCenterImage(Composite contextComposite){
		GridData layoutData = new GridData();
		layoutData.heightHint = 20;
		contextComposite.setLayoutData(layoutData);
		contextComposite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_center.png"));
    }

	@Override
    public void setLeftImage(Composite composite){
		GridData layoutData = new GridData();
		layoutData.heightHint = 20;
		layoutData.widthHint = 4;
		composite.setLayoutData(layoutData);
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_left.png"));
    }

	@Override
    protected void setRightImage(Composite composite){
		GridData layoutData = new GridData();
		layoutData.heightHint = 20;
		layoutData.widthHint = 4;
		composite.setLayoutData(layoutData);
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_right.png"));
    }

	@Override
	protected void addActive() {
		super.addActive();
		  this.getLeft_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
					"com.spark.common.components","img/text/saas_stext_h_left.png"));
		  this.getRight_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
					"com.spark.common.components","img/text/saas_stext_h_right.png"));
		  this.getContext_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
					"com.spark.common.components","img/text/saas_stext_h_center.png"));
	}
	
	@Override
	protected void lostFocus() {
		super.lostFocus();
		this.getLeft_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_left.png"));
	  this.getRight_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_right.png"));
	  this.getContext_composite().setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/text/saas_stext_n_center.png"));
	}

}
