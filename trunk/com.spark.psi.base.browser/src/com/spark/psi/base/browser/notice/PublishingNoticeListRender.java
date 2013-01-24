package com.spark.psi.base.browser.notice;

import java.util.ArrayList;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.notice.entity.NoticeItem;

/**
 * 
 * <p>δ���������б���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */
public class PublishingNoticeListRender extends PSIListPageRender{
	
	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setID(PublishingNoticeListProcessor.ID_LABEL_COUNT);
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setText("��");
		new SSearchText2(headerRightArea).setID(PublishingNoticeListProcessor.ID_TEXT_SEARCHTEXT);
		//�½�����
		Button addButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		addButton.setID(PublishingNoticeListProcessor.ID_BUTTON_ADDNOTICE);
		addButton.setText("�½�����");
		GridData addButtonGridData = new GridData();
		addButtonGridData.widthHint = 80;
		addButtonGridData.heightHint = 30;
		addButton.setLayoutData(addButtonGridData);
	}

	/**
	 * ��ȡ��
	 */
	public STableColumn[] getColumns(){
		ArrayList<STableColumn> columnList = new ArrayList<STableColumn>();
		//�������
		STableColumn titleColumn = new STableColumn("noticeTitle", 200, JWT.LEFT, "�������");
		titleColumn.setGrab(true);
		titleColumn.setSortable(true);
		columnList.add(titleColumn);
		//��������
		STableColumn publishTimeColumn = new STableColumn("publishTime", 150, JWT.CENTER, "��������");
		publishTimeColumn.setSortable(true);
		columnList.add(publishTimeColumn);
		//��������
		STableColumn cancelTimeColumn = new STableColumn("cancelTime", 150, JWT.CENTER, "��������");
		cancelTimeColumn.setSortable(true);
		columnList.add(cancelTimeColumn);
		//ֻ���ܾ�����ʾ����
		if(getContext().find(LoginInfo.class).hasAuth(Auth.Boss)){
			STableColumn createPersonColumn = new STableColumn("createPerson", 150, JWT.CENTER, "������");
			createPersonColumn.setSortable(true);
			columnList.add(createPersonColumn);
		}
		//������Χ
		STableColumn deptNameStrColumn = new STableColumn("deptNameStr", 300, JWT.CENTER, "������Χ");
		deptNameStrColumn.setSortable(true);
		deptNameStrColumn.setGrab(true);
		columnList.add(deptNameStrColumn);
		//�ö�
		STableColumn isTopColumn = new STableColumn("isTop", 50, JWT.CENTER, "�ö�");
		isTopColumn.setSortable(true);
		columnList.add(isTopColumn);
		return columnList.toArray(new STableColumn[columnList.size()]);
	}
	
	/**
	 * ��ȡ�����
	 */
	public STableStyle getTableStyle(){
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	/**
	 * ��Ԫ��ȡֵ
	 */
	public String getText(Object element, int columnIndex){
		STableColumn[] columns = getColumns();
		String text = "";
		if(columnIndex >= 0 && columnIndex < columns.length){
			String fieldName = columns[columnIndex].getName();
			NoticeItem noticeItem = (NoticeItem)element;
			if(fieldName.equals("noticeTitle")){ //�������
				text = StableUtil.toLink(PublishingNoticeListProcessor.ID_ACTION_EDIT, "", noticeItem.getNoticeTitle());
			}else if(fieldName.equals("publishTime")){ //��������
				text = DateUtil.dateFromat(noticeItem.getPublishTime());
			}else if(fieldName.equals("cancelTime")){ //��������
				text = DateUtil.dateFromat(noticeItem.getCancelTime());
			}else if(fieldName.equals("createPerson")){ //������
				text = noticeItem.getCreatePerson();
			}else if(fieldName.equals("deptNameStr")){ //������Χ
				text = noticeItem.getDeptNameStr();
			}else if(fieldName.equals("isTop")){ //�Ƿ��ö�
				text = noticeItem.getIsTop() ? "�ö�" : "����";
			}
		}
		return text;
	}
}
