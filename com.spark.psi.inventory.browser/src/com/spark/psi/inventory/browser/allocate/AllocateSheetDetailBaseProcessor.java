package com.spark.psi.inventory.browser.allocate;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;
import com.spark.psi.publish.inventory.key.GetAvailableCountKey;

/**
 * <p>调拔单详情基类处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-23
 */

public class AllocateSheetDetailBaseProcessor extends
        SimpleSheetPageProcessor<AllocateSheetDetailBaseProcessor.AllocateGoodsItem>
{

	/**
	 * 组件ID
	 */
	public final static String ID_Label_Out = "Label_Out";
	public final static String ID_Label_In = "Label_In";
	public final static String ID_Label_SubmitDate = "Label_SubmitDate";

	/**
	 * 列名 
	 */
	public static enum Columns{
		code, number, name, spec, unit,	availableCount,	allocateCount
	}

	//
	protected String sheetId = null;
	protected InventoryAllocateSheetInfo info = null;

	/**
	 * 页面初始化
	 * 
	 * @param context 上下文
	 */
	public void process(Situation context){
		super.process(context);
		//调出仓库
		Label outStore = this.createControl(ID_Label_Out, Label.class, JWT.NONE);
		outStore.setText(info.getSourceStoreName());
		//调入仓库
		Label inStore = this.createControl(ID_Label_In, Label.class, JWT.NONE);
		inStore.setText(info.getDestinationStoreName());
		//申请时间
		Label submitDate = this.createControl(ID_Label_SubmitDate, Label.class, JWT.NONE);
		submitDate.setText(DateUtil.dateFromat(info.getCreateDate()));
		//备注不可编辑
		this.createMemoText().setEditable(false);
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		if(CheckIsNull.isNotEmpty(info)){
			//初始化备注
			this.createMemoText().setText(info.getCause());
		}
	}

	/**
	 * 获得单据基本信息
	 */
	@Override
	protected String[] getBaseInfoCellContent(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 单据数据（金额）信息
	 */
	@Override
	protected SNameValue[] getDataInfoContent(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取备注内容
	 */
	@Override
	protected String getRemark(){
		if(null == info){
			return null;
		}
		return info.getCause();
	}

	/**
	 * 获取审核信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String getSheetApprovalInfo(){
		if(info == null){
			return "";
		}
		String approvalInfo = "";
		//审批人
		if(StringHelper.isNotEmpty(info.getApprovePerson())){
			approvalInfo += "调出审批人：" + info.getApprovePerson();
			approvalInfo += "(" + DateUtil.dateFromat(info.getApproveDate()) + ")";
		}
		return approvalInfo;
	}

	/**
	 * 获取制单信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String getSheetCreateInfo(){
		String createInfo = "";
		if(info != null){
			createInfo = "制单：" + info.getCreatorName();
			createInfo += "(" + DateUtil.dateFromat(info.getCreateDate()) + ")";

		}
		return createInfo;
	}

	/**
	 * 其他信息(页面左部Label=制单信息+审核信息+其他信息)
	 */
	@Override
	protected String[] getSheetExtraInfo(){
		if(info == null){
			return null;
		}
		List<String> list = new ArrayList<String>();
		//调出确认
		if(StringHelper.isNotEmpty(info.getAllocateOutName())){
			list.add("调出确认：" + info.getAllocateOutName() + "(" + DateUtil.dateFromat(info.getAllocateOutDate()) + ")");
		}
		//调入确认
		if(StringHelper.isNotEmpty(info.getAllocateInName())){
			list.add("调入确认：" + info.getAllocateInName() + "(" + DateUtil.dateFromat(info.getAllocateInDate()) + ")");
		}
		//状态信息
		list.add("状态：" + info.getStatus().getName());
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 获取单据编号(显示在右上角)
	 */
	@Override
	protected String getSheetNumber(){
		return info != null ? info.getSheetNumber() : "";
	}

	/**
	 * 获得单据标题(显示本单据标题)
	 */
	@Override
	protected String getSheetTitle(){
		return "调拔单";
	}

	/**
	 * 获取单据类型
	 */
	@Override
	protected String[] getSheetType(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取中止原因
	 */
	@Override
	protected String getStopCause(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 页面加载之前初始化本单据数据
	 */
	@Override
	protected void initSheetData(){
		if(CheckIsNull.isNotEmpty(this.getArgument())){
			sheetId = (String)this.getArgument();
			info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		}
	}

	/**
	 * 获取指定对象的ID
	 */
	@Override
	public String getElementId(Object element){
		if(element instanceof AllocateShowItem){
			return ((AllocateShowItem)element).getStockItemId().toString();
		}
		return null;
	}

	/**
	 * 获格的数据列表
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		if(CheckIsNull.isEmpty(sheetId)){
			return null;
		}
		info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		if(CheckIsNull.isEmpty(info)){
			return null;
		}
		List<AllocateShowItem> itemList = new ArrayList<AllocateShowItem>();
		com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo.AllocateGoodsItem[] goodsItem =
		        info.getItems();
		for(int i = 0; i < goodsItem.length; i++){
			itemList.add(changeAllocateGoodsItemToItem(goodsItem[i]));
		}
		return itemList.toArray();
	}

	/**
	 * 根据调拔材料获得材料
	 *
	 *@param item
	 *@return
	 */
	private AllocateShowItem changeAllocateGoodsItemToItem(
	        com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo.AllocateGoodsItem goodItem)
	{
		AllocateShowItem item = new AllocateShowItem();
		if(CheckIsNull.isNotEmpty(goodItem)){
			item.setAllocateCount(goodItem.getAllocateCount());
			item.setAvailableCount(getAvailableCount(info.getSourceStoreId(), goodItem.getGoodsItemId()));
			item.setStockItemCode(goodItem.getGoodsItemCode());
			item.setStockItemId(goodItem.getGoodsItemId());
			item.setStockItemName(goodItem.getGoodsItemName());
			item.setScale(goodItem.getScale());
			item.setStockSpec(goodItem.getGoodsItemProperties());
			item.setStockItemUnit(goodItem.getGoodsItemUnit());
			item.setStockItemNumber(goodItem.getStockNumber());
		}
		return item;
	}

	
	/**
	 * 获得某材料在仓库的可用数量
	 */
	private Double getAvailableCount(GUID storeId, GUID stockId) {
		// 获得仓库ID
//		GUID allocateOutStoreGuid = GUID.valueOf(allocateOutStoreList.getText());
		GetAvailableCountKey key = new GetAvailableCountKey(storeId, stockId);
		Double availableCountD = getContext().find(Double.class, key);
		double availableCount = availableCountD == null ? 0.0 : availableCountD.doubleValue();
		return availableCount > 0 ? availableCount : 0;
	}
	
	/**
	 * 刷新制单人及状态信息
	 */
	protected void refreshstatusInfo(){
		info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		StringBuffer extraInfos = new StringBuffer();
		extraInfos.append(StringHelper.isEmpty(getSheetCreateInfo()) ? ""
				: getSheetCreateInfo() + "  ");
		extraInfos.append(StringHelper.isEmpty(getSheetApprovalInfo()) ? ""
				: getSheetApprovalInfo() + "  ");
		String[] extraInfo = getSheetExtraInfo();
		if (extraInfo != null && extraInfo.length > 0) {
			for (String info : extraInfo) {
				extraInfos.append(StringHelper.isEmpty(info) ? "" : info + "  ");
			}
		}
		Label extraInfoLabel = createControl(ID_Label_Label_ExtraInfo,
				Label.class);
		extraInfoLabel.setText(extraInfos.toString());
		extraInfoLabel.getParent().getParent().layout();
	}

	/**
	 * 调拨材料条目信息
	 */
	public static interface AllocateGoodsItem{

		/**
		 * 获取材料条目ID
		 * 
		 * @return
		 */
		public GUID getGoodsItemId();

		/**
		 * 获取材料条目编码
		 * 
		 * @return
		 */
		public String getGoodsItemCode();

		/**
		 * 获取材料条目名称
		 * 
		 * @return
		 */
		public String getGoodsItemName();

		/**
		 * 获取材料条目属性
		 * 
		 * @return
		 */
		public String getGoodsItemProperties();

		/**
		 * 获取材料条目单位
		 * 
		 * @return
		 */
		public String getGoodsItemUnit();

		/**
		 * 获取材料数量小数位
		 * 
		 * @return
		 */
		public int getScale();

		/**
		 * 获取可用库存
		 * 
		 * @return
		 */
		public double getAvailableCount();

		/**
		 * 获取调拨数量
		 * 
		 * @return
		 */
		public double getAllocateCount();

	}

}
