package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.report.service.provider.book.InventoryBook;

public class InventoryBookQueryListRender extends PSIGoodsListPageRender {
	
	private LoginInfo loginInfo = null;
	
	

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(InventoryBookQueryListProcessor.ID_COMBOLIST_STORE);
		new Label(headerLeftArea).setText("  ");
		new SDatePicker(headerLeftArea).setID(InventoryBookQueryListProcessor.ID_COMBOLIST_TERM1);
		new Label(headerLeftArea).setText("��");
		new SDatePicker(headerLeftArea).setID(InventoryBookQueryListProcessor.ID_COMBOLIST_TERM2);
		Button doit = new Button(headerLeftArea, JWT.APPEARANCE3);
		doit.setText("ȷ��");
		doit.setID(InventoryBookQueryListProcessor.ID_COMBOLIST_TERM3);
		new Label(headerLeftArea).setText("  ��Ʒ������");
		Label label = new Label(headerLeftArea);
		label.setID(InventoryBookQueryListProcessor.ID_LABEL_INVENTORYBOOK_COUNT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);
		if (!hasAmountAuth()) {
			return;
		}
		Button b = new Button(this.footerLeftArea, JWT.APPEARANCE2);
		b.setText(" �����ɱ� ");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageControllerInstance pci = new PageControllerInstance("PSI_ChangeCostPage");
				WindowStyle ws = new WindowStyle(JWT.CLOSE);
				ws.setSize(600, 190);
				MsgRequest request = new MsgRequest(pci, "�����ɱ�", ws);
				((Situation) getContext()).bubbleMessage(request);
			}
		});
	}

	/**
	 * ��ȡ��
	 */
	public STableColumn[] getColumns(){
		int length = 13;
		if(!hasAmountAuth()){
			length = length - 4;
		}
		if(!hasCountAuth()){
			length = length - 4;
		}
		STableColumn[] columns = new STableColumn[length];
		int index = 0;
		// ��Ҫ��SheetId ��ȡ������ID
		columns[index] = new STableColumn("goodsCode", 100, JWT.LEFT, "��Ʒ����");
		columns[index].setSortable(true);
		columns[index++].setGrab(true);
		
		columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.goodsNo.name(), 100, JWT.LEFT, "��Ʒ����");
		columns[index].setSortable(true);
		columns[index++].setGrab(true);
		
		columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.goodsName.name(), 100, JWT.LEFT, "��Ʒ����");
		columns[index].setSortable(true);
		columns[index++].setGrab(true);
		columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.goodsAttr.name(), 100, JWT.LEFT, "��Ʒ���");
		columns[index].setSortable(true);
		columns[index++].setGrab(true);
		columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.goodsUnit.name(), 50, JWT.CENTER, "��λ");
		columns[index].setSortable(true);
		columns[index++].setGrab(true);
		if(hasCountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.count_begin.name(), 90, JWT.RIGHT, "�ڳ��������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasAmountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.amount_begin.name(), 90, JWT.RIGHT, "�ڳ������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasCountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.instoCount.name(), 70, JWT.RIGHT, "�������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasAmountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.instoAmount.name(), 70, JWT.RIGHT, "�����");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasCountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.outstoCount.name(), 70, JWT.RIGHT, "��������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasAmountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.outstoAmount.name(), 70, JWT.RIGHT, "������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasCountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.count_end.name(), 90, JWT.RIGHT, "��ĩ�������");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		if(hasAmountAuth()){
			columns[index] = new STableColumn(InventoryBookQueryProcessor.Columns.amount_end.name(), 90, JWT.RIGHT, "��ĩ�����");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
		}
		return columns;
	}

	/**
	 * ��Ԫ��ȡֵ
	 */
	public String getText(Object element, int columnIndex) {
		STableColumn[] columns = getColumns();
		String text = "";
		if (columnIndex >= 0 && columnIndex < columns.length) {
			String fieldName = columns[columnIndex].getName();
			InventoryBook book = (InventoryBook) element;
			if (fieldName.equals("goodsCode")) {
//				if (CheckIsNull.isEmpty(book.getGoodsCode())) {
//					MaterialsItem gi = this.getContext().find(MaterialsItem.class, book.getGoodsId());
//					if (null == gi) {
//						return null;
//					}
//					text = gi.getMaterialCode();
//				}else{
//					text = book.getGoodsCode();
//				}
				text = book.getGoodsCode();
			} else if (fieldName.equals("goodsNo")) { //����
				text = book.getGoodsNo();
			} else if (fieldName.equals("goodsName")) { // ��Ʒ����
				text = book.getGoodsName();
			} else if (fieldName.equals("goodsAttr")) { // ��Ʒ���
				text = book.getGoodsAttr();
			} else if (fieldName.equals("goodsUnit")) { // ��λ
				text = book.getGoodsUnit();
			} else if (fieldName.equals("count_begin")) { // �ڳ��������
				text = DoubleUtil.getRoundStr(book.getCount_begin());
			} else if (fieldName.equals("amount_begin")) { // �ڳ������
				text = DoubleUtil.getRoundStr(book.getAmount_begin());
			} else if (fieldName.equals("instoCount")) { // �������
				text = DoubleUtil.getRoundStr(book.getInstoCount());
			} else if (fieldName.equals("instoAmount")) { // �����
				text = DoubleUtil.getRoundStr(book.getInstoAmount());
			} else if (fieldName.equals("outstoCount")) { // ��������
				text = DoubleUtil.getRoundStr(book.getOutstoCount());
			} else if (fieldName.equals("outstoAmount")) { // ������
				text = DoubleUtil.getRoundStr(book.getOutstoAmount());
			} else if (fieldName.equals("count_end")) { // ��ĩ�������
				text = DoubleUtil.getRoundStr(book.getCount_end());
			} else if (fieldName.equals("amount_end")) { // ��ĩ�����
				text = DoubleUtil.getRoundStr(book.getAmount_end());
			}
		}
		return text;
	}

	/**
	 * ��þ���
	 */
	@Override
	public int getDecimal(Object element, int columnIndex) {
		STableColumn[] columns = getColumns();
		if (columnIndex >= 0 && columnIndex < columns.length) {
			String fieldName = columns[columnIndex].getName();
			if (fieldName.equals("count_begin")) { // �ڳ��������
				return 2;
			} else if (fieldName.equals("amount_begin")) { // �ڳ������
				return 2;
			} else if (fieldName.equals("instoCount")) { // �������
				return 2;
			} else if (fieldName.equals("instoAmount")) { // �����
				return 2;
			} else if (fieldName.equals("outstoCount")) { // ��������
				return 2;
			} else if (fieldName.equals("outstoAmount")) { // ������
				return 2;
			} else if (fieldName.equals("count_end")) { // ��ĩ�������
				return 2;
			} else if (fieldName.equals("amount_end")) { // ��ĩ�����
				return 2;
			}
		}
		return -1;
	}

	
	
	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	/**
	 * �ж��Ƿ��п����Ȩ��
	 * 
	 *@return
	 */
	private boolean hasAmountAuth() {
		return (loginInfo.hasAuth(Auth.SubFunction_InventoryQuery_Price));
//		GUID id = getContext().find(Login.class).getEmployeeId();
//		return getContext().find(Boolean.class, Auth.SubFunction_InventoryQuery_Price, id);
	}

	/**
	 * �ж��Ƿ��п������Ȩ��
	 * 
	 *@return
	 */
	private boolean hasCountAuth() {
		return (loginInfo.hasAuth(Auth.SubFunction_InventoryQuery_Count));
//		GUID id = getContext().find(Login.class).getEmployeeId();
//		return getContext().find(Boolean.class, Auth.SubFunction_InventoryQuery_Count, id);
	}

}
