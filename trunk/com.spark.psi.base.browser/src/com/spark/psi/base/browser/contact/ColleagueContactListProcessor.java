package com.spark.psi.base.browser.contact;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.contact.entity.ContactItem;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;
import com.spark.psi.publish.base.contact.key.FindColleagueContactListKey;

/**
 * 
 * <p>ͨѸ¼(ͬ��)�б�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class ColleagueContactListProcessor extends ContactBaseListProcessor<ContactItem>{
 
	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
	}

	/**
	 * ���ͨѶ¼(ͬ��)�б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindColleagueContactListKey key = new FindColleagueContactListKey();
		//ƴ������
		key.setPhonetics(phoneticNavigatorBar.getText());
		//�����ַ���
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("������������")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		//����
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element){
		return ((ContactPersonItem)element).getId().toString();
	}
	/**
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
	}

	@Override
	protected String getExportFileTitle() {
		return "��ϵ��-ͬ��";
	}

}
