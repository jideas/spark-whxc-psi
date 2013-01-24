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
 * <p>ͨѶ¼�����б�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-9
 */

public abstract class ContactBaseListProcessor<Item> extends PSIListPageProcessor<Item>{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_PHONETICE_NAVIGATOR_BAR = "Phonetic_Navigator_Bar";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	
	
	/**ƴ������������*/
	protected ContactPersonPhoneticNavigatorBar phoneticNavigatorBar;
	
	/**
	 * �ؼ�
	 */
	protected Text searchNoticeText;
	
	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//ƴ��������
		phoneticNavigatorBar = this.createControl(ID_PHONETICE_NAVIGATOR_BAR, ContactPersonPhoneticNavigatorBar.class, JWT.NONE);
		//�����ı�
		searchNoticeText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		searchNoticeText.addActionListener(new SearchButtonListenter());
	}
	
	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 */
	@Override
	public void postProcess(Situation context) {
		table.render();
		//������Ϣ
		getContext().regMessageListener(String.class, new MessageListener<String>(){

			public void onMessage(Situation context, String message, MessageTransmitter<String> transmitter){
	            table.render();
            }
		});
	}
	
	/**
	 * ������ť������
	 */
	private class SearchButtonListenter implements ActionListener{

		public void actionPerformed(ActionEvent e){
			table.render();
		}
	}
	
	/**
	 * �ж��Ƿ�ѡ����ϵ��
	 */
	protected boolean isSelectedPerson(){
		if(null == table.getSelections() || table.getSelections().length == 0){
			alert("��δѡ���κ���ϵ�ˣ�");
			return false;
		}
		return true;
	}

	/**
	 * ���Ͷ��Ű�ť������
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
	 * ����Email������
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
