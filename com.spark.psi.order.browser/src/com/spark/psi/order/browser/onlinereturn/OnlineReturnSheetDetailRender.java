package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.order.browser.onlinereturn.OnlineReturnSheetDetailProcessor.Type;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnDet;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnInfo;

public class OnlineReturnSheetDetailRender extends SimpleSheetPageRender {
	private OnlineReturnInfo returnInfo = null;
	private LoginInfo loginInfo         = null;
	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
		GUID id = (GUID)getArgument();
		Type viewType = (Type)getArgument2();
		if (Type.Detail == viewType) {
			returnInfo = getContext().find(OnlineReturnInfo.class, id);
		}
	}
	
	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0) {
			if (column == 0) {
				GridData gdLabel = new GridData();
				gdLabel.widthHint = 100;
				
				Label label = new Label(baseInfoArea);
				label.setText("客户：");
				label = new Label(baseInfoArea);
				label.setID(OnlineReturnSheetDetailProcessor.ID_Label_CustomerName);
				label.setLayoutData(gdLabel);
				
//				new Label(baseInfoArea).setText("    ");
				
				label = new Label(baseInfoArea);
				label.setText("手机号码：");
				label = new Label(baseInfoArea);
				label.setID(OnlineReturnSheetDetailProcessor.ID_Label_MobileNo);
				label.setLayoutData(gdLabel);
				
//				new Label(baseInfoArea).setText("    ");
				
				label = new Label(baseInfoArea);
				label.setText("下单日期：");
				label = new Label(baseInfoArea);
				label.setID(OnlineReturnSheetDetailProcessor.ID_Label_BookingDate);
				label.setLayoutData(gdLabel);
				
//				new Label(baseInfoArea).setText("    ");
				
				label = new Label(baseInfoArea);
				label.setText("订单金额：");
				label = new Label(baseInfoArea);
				label.setID(OnlineReturnSheetDetailProcessor.ID_Label_TotalAmount);
				label.setLayoutData(gdLabel);
				
				label = new Label(baseInfoArea);
				label.setText("扣除积分：");
				Text text = new Text(baseInfoArea,JWT.APPEARANCE3);
				text.setID(OnlineReturnSheetDetailProcessor.ID_Text_Vantages);
				text.setLayoutData(gdLabel);
				text.setRegExp(TextRegexp.ALLNUMBER);
			} else if (column == 1) {
				new Label(baseInfoArea).setText("单据状态：");
				new Label(baseInfoArea).setID(OnlineReturnSheetDetailProcessor.ID_Label_Status);
			}
		}

	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		new Label(dataInfoArea).setText("退货总金额：");
		new Text(dataInfoArea, JWT.APPEARANCE3).setID(OnlineReturnSheetDetailProcessor.ID_Text_TotalReturnAmount);
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = null;
		if (null == returnInfo || OnlineReturnStatus.Submitting.equals(returnInfo.getStatus())
				|| OnlineReturnStatus.Rejected.equals(returnInfo.getStatus())) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 保 存 ");
			button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Save);
			
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 提 交 ");
			button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Submit);
		} else {
			switch(returnInfo.getStatus()) {
			case Approving:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
					button = new Button(sheetButtonArea, JWT.APPEARANCE3);
					button.setText(" 批 准 ");
					button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Approval);
					
					button = new Button(sheetButtonArea, JWT.APPEARANCE3);
					button.setText(" 退 回 ");
					button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Reject);
				}
				break;
			case Processing:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
					button = new Button(sheetButtonArea, JWT.APPEARANCE3);
					button.setText(" 确认完成 ");
					button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Finish);
					
					button = new Button(sheetButtonArea, JWT.APPEARANCE3);
					button.setText(" 中 止 ");
					button.setID(OnlineReturnSheetDetailProcessor.ID_Button_Stop);
				}
				break;
			case Stopped:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
					button = new Button(sheetButtonArea, JWT.APPEARANCE3);
					button.setText(" 重新执行");
					button.setID(OnlineReturnSheetDetailProcessor.ID_Button_ReExceute);
				}
				break;
			}
		}
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		// TODO Auto-generated method stub

	}
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[9];
		
		columns[0] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.goodsCode.name(), 120, 
				JWT.LEFT, OnlineReturnSheetDetailProcessor.ColumnName.goodsCode.getTitle());
		columns[1] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.goodsNo.name(), 150, 
				JWT.LEFT, OnlineReturnSheetDetailProcessor.ColumnName.goodsNo.getTitle());
		columns[2] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.goodsName.name(), 200, 
				JWT.LEFT, OnlineReturnSheetDetailProcessor.ColumnName.goodsName.getTitle());
		columns[3] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.goodsSpec.name(), 100, 
				JWT.LEFT, OnlineReturnSheetDetailProcessor.ColumnName.goodsSpec.getTitle());
		columns[4] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.unit.name(), 100, 
				JWT.CENTER, OnlineReturnSheetDetailProcessor.ColumnName.unit.getTitle());
		columns[5] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.price.name(), 100, 
				JWT.RIGHT, OnlineReturnSheetDetailProcessor.ColumnName.price.getTitle());
		columns[6] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.count.name(), 100, 
				JWT.RIGHT, OnlineReturnSheetDetailProcessor.ColumnName.count.getTitle());
		columns[7] = new SNumberEditColumn(OnlineReturnSheetDetailProcessor.ColumnName.returnCount.name(), 100, 
				JWT.RIGHT, OnlineReturnSheetDetailProcessor.ColumnName.returnCount.getTitle());
		columns[8] = new STableColumn(OnlineReturnSheetDetailProcessor.ColumnName.returnAmount.name(), 100, 
				JWT.RIGHT, OnlineReturnSheetDetailProcessor.ColumnName.returnAmount.getTitle());
		((SNumberEditColumn)columns[7]).setDecimal(2);
		columns[2].setGrab(true);
	
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof OnlineOrderInfoItem) {
			OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
			switch(columnIndex) {
			case 0:
				return item.getGoodsCode();
			case 1:
				return item.getGoodsNo();
			case 2:
				return item.getGoodsName();
			case 3:
				return item.getGoodsSpec();
			case 4:
				return item.getUnit();
			case 5:
				return DoubleUtil.getRoundStr(item.getPrice());
			case 6:
				return DoubleUtil.getRoundStr(item.getCount());
			}
		} else if (element instanceof OnlineReturnDet) {
			OnlineReturnDet item = (OnlineReturnDet)element;
			switch(columnIndex) {
			case 0:
				return item.getGoodsCode();
			case 1:
				return item.getGoodsNo();
			case 2:
				return item.getGoodsName();
			case 3:
				return item.getGoodsSpec();
			case 4:
				return item.getGoodsUnit();
			case 5:
				return DoubleUtil.getRoundStr(item.getPrice());
			case 6:
				return DoubleUtil.getRoundStr(item.getBillsCount());
			case 7:
				return DoubleUtil.getRoundStr(item.getCount());
			case 8:
				return DoubleUtil.getRoundStr(item.getAmount());
			}
		}
		
		return null;
	}

}
