package com.spark.common.components.controls.text;


import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;

public interface STextImp{
	public Text getText();
	public void setSearchDescription(String name);
	public void setAdvanceSearchButtonName(String name);
	public void addSearchAction(MouseClickListener mouseClickListener);
	public void addAdvanceSearchButtonAction(ActionListener actionListener);
	public Button getAdvanceSearchButton();
}
