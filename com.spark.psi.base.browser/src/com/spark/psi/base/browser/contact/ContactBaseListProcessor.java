package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.base.contact.task.DeleteContactInfoTask;

/**
 * <p>通讯录基类列表处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-9
 */

public abstract class ContactBaseListProcessor<Item> extends PSIListPageProcessor<Item>{

	/**
	 * 控件ID
	 */
	public final static String ID_PHONETICE_NAVIGATOR_BAR = "Phonetic_Navigator_Bar";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	
	
	/**拼音搜索导航条*/
	protected ContactPersonPhoneticNavigatorBar phoneticNavigatorBar;
	
	/**
	 * 控件
	 */
	protected Text searchNoticeText;
	
	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//拼音导航条
		phoneticNavigatorBar = this.createControl(ID_PHONETICE_NAVIGATOR_BAR, ContactPersonPhoneticNavigatorBar.class, JWT.NONE);
		//搜索文本
		searchNoticeText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		searchNoticeText.addActionListener(new SearchButtonListenter());
	}
	
	/**
	 * 页面初始化完毕（加载数据）
	 */
	@Override
	public void postProcess(Situation context) {
		table.render();
		//侦听消息
		getContext().regMessageListener(String.class, new MessageListener<String>(){

			public void onMessage(Situation context, String message, MessageTransmitter<String> transmitter){
	            table.render();
            }
		});
	}
	
	/**
	 * 搜索按钮侦听器
	 */
	private class SearchButtonListenter implements ActionListener{

		public void actionPerformed(ActionEvent e){
			table.render();
		}
	}
	
	/**
	 * 判断是否选择联系人
	 */
	protected boolean isSelectedPerson(){
		if(null == table.getSelections() || table.getSelections().length == 0){
			alert("您未选择任何联系人！");
			return false;
		}
		return true;
	}

	/**
	 * 发送短信按钮侦听器
	 */
	protected class SendMsgButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(!isSelectedPerson()){
				return;
			}
			//
		}

	}

	/**
	 * 发送Email侦听器
	 */
	protected class SendEmailButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(!isSelectedPerson()){
				return;
			}
			// 
		}

	}
	
	
}
