package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.b2b.entity.AuthorizingGoodsItem;

/**
 * 
 * 客户授权明细界面处理器
 */
public class CustomerAuthorizingDetailProcessor extends
		PSIListPageProcessor<AuthorizingGoodsItem> {
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_Label_Find = "Label_Find";
	public final static String ID_List_AuthorizedPerson = "List_AuthorizedPerson";
	public final static String ID_Label_NewContactImg = "Label_NewContactImg";
	public final static String ID_Label_EditContactImg = "Label_EditContactImg";
	public final static String ID_Label_SalerInfo = "Label_SalerInfo";
	public final static String ID_Label_Mobile = "Label_Mobile";
	public final static String ID_Label_LandPhoneNumber = "Label_LandPhoneNumber";
	public final static String ID_Label_ModelGoods = "Label_ModelGoods";
	public final static String ID_Label_SaveModelGoods = "Label_SaveModelGoods";
	public final static String ID_Button_AddGoods = "Button_AddGodos";
	public final static String ID_Button_Save = "Button_Save";
	@Override
	public void process(Situation situation) {
		super.process(situation);
		
		Label nameLabel = this.createControl(ID_Label_Name, Label.class, JWT.NONE);
		nameLabel.setText("重庆");
		
		Label findLabel = this.createControl(ID_Label_Find, Label.class, JWT.NONE);
		findLabel.addMouseClickListener(new MouseClickListener() {
			
			public void click(MouseEvent arg0) {
				// TODO  选择客户
				
			}
		});
		
		
		// TODO 被授权人列表
//		LWComboList list = this.createControl(controlId, clazz, style)
		
		Label newContactLabel = this.createControl(ID_Label_NewContactImg, Label.class, JWT.NONE);
		newContactLabel.addMouseClickListener(new MouseClickListener() {
			
			public void click(MouseEvent arg0) {
				// TODO  新增联系人
				
			}
		});

		Label editContactLabel = this.createControl(ID_Label_EditContactImg, Label.class, JWT.NONE);
		editContactLabel.addMouseClickListener(new MouseClickListener() {
			
			public void click(MouseEvent arg0) {
				// TODO  编辑联系人
				
			}
		});
		
		Label salerLabel = this.createControl(ID_Label_SalerInfo, Label.class, JWT.NONE);
		salerLabel.setText("销售中心(销售员)");
		
		Label mobileLabel = this.createControl(ID_Label_Mobile, Label.class, JWT.NONE);
		mobileLabel.setText("13888888888");
		
		Label landNumberLabel = this.createControl(ID_Label_LandPhoneNumber, Label.class, JWT.NONE);
		landNumberLabel.setText("023888888");
		
		Label modelGoodsLabel = this.createControl(ID_Label_ModelGoods, Label.class, JWT.NONE);
		modelGoodsLabel.addMouseClickListener(new MouseClickListener() {
			
			public void click(MouseEvent arg0) {
				// TODO 使用模板商品
				
			}
		});
		
		Label saveModelGoodsLabel = this.createControl(ID_Label_SaveModelGoods, Label.class, JWT.NONE);
		saveModelGoodsLabel.addMouseClickListener(new MouseClickListener() {
			
			public void click(MouseEvent arg0) {
				// TODO 保存商品模板
				
			}
		});
		
		Button addGoodsButton = this.createControl(ID_Button_AddGoods, Button.class, JWT.NONE);
		addGoodsButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 添加商品
				
			}
		});
		
		Button saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 保存
				
			}
		});
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return null;
	}

	public String getElementId(Object element) {
		return "";
	}

	public String getValue(Object element, int columnIndex) {
//		AuthorizingGoodsItem item = (AuthorizingGoodsItem) element;
//		switch (columnIndex) {
//		case 0:
//			return item.getSalesmanName();
//		case 1:
//			return item.getDepartmentInfo();
//		case 2:
//			return String.valueOf(item.getCustomerCreditLimit());
//		case 3:
//			return String.valueOf(item.getAvailableTotalCreditLimit());
//		case 4:
//			return String.valueOf(item.getCustomerCountUsed());
//		case 5:
//			return String.valueOf(item.getCustomerCreditUsed());
//		case 6:
//			return String.valueOf(item.getCustomerCreditDayLimit());
//		case 7:
//			return String.valueOf(item.getOrderApprovalLimit());
//		default:
//			return null;
//		}
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Clear.name() };
	}

	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Clear.name() };
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Clear.name())) {
			// TODO：清空
		} 
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
