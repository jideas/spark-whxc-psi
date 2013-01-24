package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;

/**
 * 
 * <p>ͨѸ¼(������)�б���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class AllContactListRender extends ContactBaseListRender {
	@Override
	protected void afterFooterRender(){
		super.afterFooterRender(); 
	}

	/**
	 * ��ȡ��
	 */
	public STableColumn[] getColumns(){
		STableColumn[] columns = new STableColumn[6];
		//����
		columns[0] = new STableColumn("name", nameColumnWidth, JWT.LEFT, "����");
		columns[0].setSortable(true);
		//��λ(����)
		columns[1] = new STableColumn("department", unitColumnWidth, JWT.LEFT, "��λ(����)");
		columns[1].setGrab(true);
		columns[1].setSortable(true);
		//ְλ
		columns[2] = new STableColumn("job", positionColumnWidth, JWT.CENTER, "ְλ");
		columns[2].setSortable(true);
		//�̻�
		columns[3] = new STableColumn("phone", phoneNumberColumnWidth, JWT.CENTER, "�̻�");
		columns[3].setSortable(true);
		//�ֻ�
		columns[4] = new STableColumn("mobile", cellPhoneNumberColumnWidth, JWT.CENTER, "�ֻ�");
		columns[4].setSortable(true);
		//����
		columns[5] = new STableColumn("email", emailColumnWidth, JWT.LEFT, "����");
		columns[5].setSortable(true);
		columns[5].setGrab(true);
		return columns;
	}


	/**
	 * ��Ԫ��ȡֵ
	 */
	public String getText(Object element, int columnIndex){
		ContactPersonItem contactPersonItem = (ContactPersonItem)element;
		String text = "";
		switch(columnIndex){
			case 0:
				if(ContactPersonItem.COLLEAGUE.equals(contactPersonItem.getType())){ //ͬ��
					text = contactPersonItem.getName();
				}else{ //��ϵ��
					text = StableUtil.toLink(AllContactListProcessor.ID_ACTION_VIEW, "", contactPersonItem.getName());
				}
				break;
			case 1:
				text = contactPersonItem.getDepartment();
				break;
			case 2:
				text = contactPersonItem.getJob();
				break;
			case 3:
				text = contactPersonItem.getPhone();
				break;
			case 4:
				text = contactPersonItem.getMobile();
				break;
			case 5:
				text = contactPersonItem.getEmail();
				break;
		}
		return text;
	}
}
