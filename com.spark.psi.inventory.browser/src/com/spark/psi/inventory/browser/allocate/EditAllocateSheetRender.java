package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;

/**
 * <p>������༭���ε���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-23
 */

public class EditAllocateSheetRender extends SimpleSheetPageRender{

	/**
	 * ��ť������
	 */
	protected static GridData buttonGridData;

	static{
		buttonGridData = new GridData();
		buttonGridData.heightHint = 28;
		buttonGridData.widthHint = 80;
	}

	/**
	 * ��ָ������������(����λ�ڱ���ͱ��֮��)
	 * 
	 * @param baseInfoArea ����
	 * @param row �ڼ���
	 * @param column���ڼ���(ֻ֧�����У�0��Ϊ��߲��ݣ�1���ұ߲���)
	 */
	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column){
		if(column == 0){ //������
			//�����ֿ�
			new Label(baseInfoArea).setText("ѡ������ֿ⣺");
			LWComboList allocateOutStoreList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			allocateOutStoreList.setID(EditAllocateSheetProcesser.ID_ComboList_Out);
			allocateOutStoreList.setHint("��ѡ��");
			//
			new Label(baseInfoArea).setText("����");
			//����ֿ�
			new Label(baseInfoArea).setText("ѡ�����ֿ⣺");
			LWComboList allocateInStoreList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			allocateInStoreList.setID(EditAllocateSheetProcesser.ID_ComboList_In);
			allocateInStoreList.setHint("��ѡ��");
		}
	}

	/**
	 * ���ײ�����һ�У���ע�ı�������������
	 */
	@Override
	protected void fillDataInfoControl(Composite dataInfoArea){
		// TODO Auto-generated method stub

	}

	/**
	 * ���ײ����ڶ���
	 */
	@Override
	protected void fillStopCauseControl(Composite stopCauseArea){
		// TODO Auto-generated method stub

	}

	/**
	 * ����ͱ��֮��Ĳ�������
	 */
	@Override
	protected int getBaseInfoAreaRowCount(){
		return 1;
	}

	/**
	 * ҳ��ײ��ұ߰�ť��
	 */
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea){
		//�ύ����
		Button submitButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		submitButton.setID(EditAllocateSheetProcesser.ID_Button_Submit);
		submitButton.setText("�ύ����");
		submitButton.setLayoutData(buttonGridData);
		//����
		Button saveButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		saveButton.setID(EditAllocateSheetProcesser.ID_Button_Save);
		saveButton.setText("����");
		saveButton.setLayoutData(buttonGridData);
	}

	/**
	 * ���ײ���ť����Ĭ�ϲ���Ϊ5�е�DataGrid���ڱ�ע֮ǰ
	 */
	@Override
	protected void renderTableButtonArea(Composite tableButtonArea){
		//��Ӳ���
		Button addGoodsButton = new Button(tableButtonArea, JWT.APPEARANCE2);
		addGoodsButton.setID(EditAllocateSheetProcesser.ID_Button_AddGoods);
		addGoodsButton.setText("��Ӳ���");
		addGoodsButton.setLayoutData(buttonGridData);
	}
	
	/**
	 * �����
	 */
	@Override
	public STableColumn[] getColumns(){
		//������
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(EditAllocateSheetProcesser.Columns.code.name(), 150, JWT.LEFT, "���");
		columns[1] = new STableColumn(EditAllocateSheetProcesser.Columns.number.name(), 150, JWT.LEFT, "����");
		columns[2] = new STableColumn(EditAllocateSheetProcesser.Columns.name.name(), 100, JWT.LEFT, "��������");
		columns[3] = new STableColumn(EditAllocateSheetProcesser.Columns.spec.name(), 100, JWT.LEFT, "���Ϲ��");
		columns[4] = new STableColumn(EditAllocateSheetProcesser.Columns.unit.name(), 120, JWT.CENTER, "��λ");
		columns[5] =
		        new STableColumn(EditAllocateSheetProcesser.Columns.availableCount.name(), 150, JWT.RIGHT, "���ÿ��");
		columns[6] =
		        new SNumberEditColumn(EditAllocateSheetProcesser.Columns.allocateCount.name(), 150, JWT.RIGHT, "��������");
		//����Ӧ
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}

	/**
	 * ��þ���
	 */
	public int getDecimal(Object element, int columnIndex) {
		if(element instanceof AllocateShowItem){
			AllocateShowItem item = (AllocateShowItem)element;
			switch(columnIndex){
				case 5:
				case 6:
					return item.getScale();
			}
		}
		return -1;
	}
	
	/**
	 * ��Ԫ���ı�ֵ
	 */
	@Override
	public String getText(Object element, int columnIndex){
		if(element instanceof AllocateShowItem){
			AllocateShowItem item = (AllocateShowItem)element;
			switch(columnIndex){
				case 0:
					return item.getStockItemCode();
				case 1:
					return item.getStockItemNumber();
				case 2:
					return item.getStockItemName();
				case 3:
					return item.getStockSpec();
				case 4:
					return item.getStockItemUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getAvailableCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return "";
	}
	
	/**
	 * ��ȡ�����
	 */
	public STableStyle getTableStyle(){
		return new STableStyle();
	}

}
