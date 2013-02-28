package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;

/**
 * <p>���ε����������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-23
 */

public class AllocateSheetDetailBaseRender extends SimpleSheetPageRender{

	/**
	 * ��ť������
	 */
	protected static GridData buttonGridData;
	
	static {
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
			new Label(baseInfoArea).setText("�����ֿ⣺");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_Out);
			//
			new Label(baseInfoArea).setText("����");
			//����ֿ�
			new Label(baseInfoArea).setText("����ֿ⣺");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_In);
			//
			new Label(baseInfoArea).setText("����");
			//����ʱ��
			new Label(baseInfoArea).setText("�������ڣ�");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_SubmitDate);
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
		// TODO Auto-generated method stub

	}

	/**
	 * ���ײ���ť����Ĭ�ϲ���Ϊ5�е�DataGrid
	 */
	@Override
	protected void renderTableButtonArea(Composite tableButtonArea){
		// TODO Auto-generated method stub

	}

	/**
	 * �����
	 */
	@Override
	public STableColumn[] getColumns(){
		//������
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.code.name(), 150, JWT.LEFT, "���");
		columns[1] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.number.name(), 100, JWT.LEFT, "����");
		columns[2] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.name.name(), 100, JWT.LEFT, "��������");
		columns[3] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.spec.name(), 100, JWT.LEFT, "���");
		columns[4] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.unit.name(), 120, JWT.CENTER, "��λ");
		columns[5] = new SNumberEditColumn(AllocateSheetDetailBaseProcessor.Columns.allocateCount.name(), 150, JWT.RIGHT, "��������");
		//����Ӧ
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		((SNumberEditColumn)columns[5]).setDecimal(2);
		return columns;
	}
	
	/**
	 * ��þ���
	 */
	public int getDecimal(Object element, int columnIndex) {
		if(element instanceof AllocateShowItem){
			switch(columnIndex){
				case 5:
					return 2;
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
					return DoubleUtil.getRoundStr(item.getAllocateCount());
//				case 6:
//					return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return "";
	}
	
	/**
	 * ��ȡ�����
	 */
	public STableStyle getTableStyle(){
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

}
