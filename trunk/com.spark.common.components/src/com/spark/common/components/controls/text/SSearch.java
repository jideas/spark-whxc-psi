package com.spark.common.components.controls.text;


import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * 搜索控件
 * @author durendong
 *
 */
public class SSearch extends AbstractSText implements STextImp{
	
	public SSearch(Composite parent, boolean isAdvanceSearchButton){
	    super(parent, isAdvanceSearchButton);
    }
	
	/**
	 * 返回查询的关键字
	 * 
	 * @return String
	 */
	public String getSearchText(){
		return this.getText().getText();
	}
	
	public SSearch(Composite parent,int width,boolean isAdvanceSearchButton){
		super(parent, width,isAdvanceSearchButton);
	}
	
	
	@Override
	public void setLeftImage(Composite composite){
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_bg_left.png"));
	}
	
	@Override
	protected void setCenterImage(Composite contextComposite){
		contextComposite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_bg_center.png"));

		
	}
	
	@Override
	protected void setRightImage(Composite composite){
		composite.setHoverBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_pic_h.png"));
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_pic_n.png"));
	}
	
	
}
