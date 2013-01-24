package com.spark.common.components.table.search;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;

/**
 * 定位
 *
 */
public class StableSearch{

	private SSearchText2 search;
	private Composite textComposite;
	
	/**
	 * 三个参数必须传，不能为空
	 * @param searchComposite 查询控件的容器
	 * @param textcomposite 定位，上一个，下一个的容器
	 * @param parent 顶层容器
	 */
	public StableSearch(final Composite searchComposite,
			Composite textcomposite, final Composite parent) {
		this.textComposite = textcomposite;
		textComposite.setID("textComposite");
		textComposite.setVisible(false);
		textComposite.setLayout(new GridLayout(3));
		parent.addClientMessageHandler("find", "Search.getMessage");
		parent.addClientMessageHandler("stableMessage", "Search.getStableMessage");
		parent.addClientMessageHandler("changColor", "Search.changColor");
		search = new SSearchText2(searchComposite);
		search.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY,
				"Search.handleServerNotify");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String text = search.getText();
				if (text == null || text.length() == 0) {
					textComposite.clear();
				}
				
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("name", text);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				search.setCustomObject("text", jsonObject);
				search.notifyClientAction();	            
            }
		});
		
		//
		Label labelName = new Label(textComposite);
		labelName.setID("labelName");
		Label labelBefore = new Label(textComposite);
		labelBefore.setText("上一个");
		labelBefore.setEnabled(false);
		labelBefore.setHoverCursor(Cursor.HAND);
		labelBefore.setHoverForeground(new Color(0x0059af));
		labelBefore.setID("labelBefore");
		labelBefore.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "Search.findBefore");
		Label labelNext = new Label(textComposite);
		labelNext.setText("下一个");
		labelNext.setHoverCursor(Cursor.HAND);
		labelNext.setHoverForeground(new Color(0x0059af));
		labelNext.setID("labelNext");
		labelNext.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "Search.findNext");
	}
	
}
