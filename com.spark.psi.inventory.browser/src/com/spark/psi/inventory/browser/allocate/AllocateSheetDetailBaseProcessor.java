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
 * <p>���ε�������ദ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-23
 */

public class AllocateSheetDetailBaseProcessor extends
        SimpleSheetPageProcessor<AllocateSheetDetailBaseProcessor.AllocateGoodsItem>
{

	/**
	 * ���ID
	 */
	public final static String ID_Label_Out = "Label_Out";
	public final static String ID_Label_In = "Label_In";
	public final static String ID_Label_SubmitDate = "Label_SubmitDate";

	/**
	 * ���� 
	 */
	public static enum Columns{
		code, number, name, spec, unit,	availableCount,	allocateCount
	}

	//
	protected String sheetId = null;
	protected InventoryAllocateSheetInfo info = null;

	/**
	 * ҳ���ʼ��
	 * 
	 * @param context ������
	 */
	public void process(Situation context){
		super.process(context);
		//�����ֿ�
		Label outStore = this.createControl(ID_Label_Out, Label.class, JWT.NONE);
		outStore.setText(info.getSourceStoreName());
		//����ֿ�
		Label inStore = this.createControl(ID_Label_In, Label.class, JWT.NONE);
		inStore.setText(info.getDestinationStoreName());
		//����ʱ��
		Label submitDate = this.createControl(ID_Label_SubmitDate, Label.class, JWT.NONE);
		submitDate.setText(DateUtil.dateFromat(info.getCreateDate()));
		//��ע���ɱ༭
		this.createMemoText().setEditable(false);
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		if(CheckIsNull.isNotEmpty(info)){
			//��ʼ����ע
			this.createMemoText().setText(info.getCause());
		}
	}

	/**
	 * ��õ��ݻ�����Ϣ
	 */
	@Override
	protected String[] getBaseInfoCellContent(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * �������ݣ�����Ϣ
	 */
	@Override
	protected SNameValue[] getDataInfoContent(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ��ȡ��ע����
	 */
	@Override
	protected String getRemark(){
		if(null == info){
			return null;
		}
		return info.getCause();
	}

	/**
	 * ��ȡ�����Ϣ(ҳ����Label=�Ƶ���Ϣ+�����Ϣ+������Ϣ)
	 */
	@Override
	protected String getSheetApprovalInfo(){
		if(info == null){
			return "";
		}
		String approvalInfo = "";
		//������
		if(StringHelper.isNotEmpty(info.getApprovePerson())){
			approvalInfo += "���������ˣ�" + info.getApprovePerson();
			approvalInfo += "(" + DateUtil.dateFromat(info.getApproveDate()) + ")";
		}
		return approvalInfo;
	}

	/**
	 * ��ȡ�Ƶ���Ϣ(ҳ����Label=�Ƶ���Ϣ+�����Ϣ+������Ϣ)
	 */
	@Override
	protected String getSheetCreateInfo(){
		String createInfo = "";
		if(info != null){
			createInfo = "�Ƶ���" + info.getCreatorName();
			createInfo += "(" + DateUtil.dateFromat(info.getCreateDate()) + ")";

		}
		return createInfo;
	}

	/**
	 * ������Ϣ(ҳ����Label=�Ƶ���Ϣ+�����Ϣ+������Ϣ)
	 */
	@Override
	protected String[] getSheetExtraInfo(){
		if(info == null){
			return null;
		}
		List<String> list = new ArrayList<String>();
		//����ȷ��
		if(StringHelper.isNotEmpty(info.getAllocateOutName())){
			list.add("����ȷ�ϣ�" + info.getAllocateOutName() + "(" + DateUtil.dateFromat(info.getAllocateOutDate()) + ")");
		}
		//����ȷ��
		if(StringHelper.isNotEmpty(info.getAllocateInName())){
			list.add("����ȷ�ϣ�" + info.getAllocateInName() + "(" + DateUtil.dateFromat(info.getAllocateInDate()) + ")");
		}
		//״̬��Ϣ
		list.add("״̬��" + info.getStatus().getName());
		return list.toArray(new String[list.size()]);
	}

	/**
	 * ��ȡ���ݱ��(��ʾ�����Ͻ�)
	 */
	@Override
	protected String getSheetNumber(){
		return info != null ? info.getSheetNumber() : "";
	}

	/**
	 * ��õ��ݱ���(��ʾ�����ݱ���)
	 */
	@Override
	protected String getSheetTitle(){
		return "���ε�";
	}

	/**
	 * ��ȡ��������
	 */
	@Override
	protected String[] getSheetType(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ��ȡ��ֹԭ��
	 */
	@Override
	protected String getStopCause(){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ҳ�����֮ǰ��ʼ������������
	 */
	@Override
	protected void initSheetData(){
		if(CheckIsNull.isNotEmpty(this.getArgument())){
			sheetId = (String)this.getArgument();
			info = getContext().find(InventoryAllocateSheetInfo.class, GUID.valueOf(sheetId));
		}
	}

	/**
	 * ��ȡָ�������ID
	 */
	@Override
	public String getElementId(Object element){
		if(element instanceof AllocateShowItem){
			return ((AllocateShowItem)element).getStockItemId().toString();
		}
		return null;
	}

	/**
	 * ���������б�
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
	 * ���ݵ��β��ϻ�ò���
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
	 * ���ĳ�����ڲֿ�Ŀ�������
	 */
	private Double getAvailableCount(GUID storeId, GUID stockId) {
		// ��òֿ�ID
//		GUID allocateOutStoreGuid = GUID.valueOf(allocateOutStoreList.getText());
		GetAvailableCountKey key = new GetAvailableCountKey(storeId, stockId);
		Double availableCountD = getContext().find(Double.class, key);
		double availableCount = availableCountD == null ? 0.0 : availableCountD.doubleValue();
		return availableCount > 0 ? availableCount : 0;
	}
	
	/**
	 * ˢ���Ƶ��˼�״̬��Ϣ
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
	 * ����������Ŀ��Ϣ
	 */
	public static interface AllocateGoodsItem{

		/**
		 * ��ȡ������ĿID
		 * 
		 * @return
		 */
		public GUID getGoodsItemId();

		/**
		 * ��ȡ������Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemCode();

		/**
		 * ��ȡ������Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemName();

		/**
		 * ��ȡ������Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemProperties();

		/**
		 * ��ȡ������Ŀ��λ
		 * 
		 * @return
		 */
		public String getGoodsItemUnit();

		/**
		 * ��ȡ��������С��λ
		 * 
		 * @return
		 */
		public int getScale();

		/**
		 * ��ȡ���ÿ��
		 * 
		 * @return
		 */
		public double getAvailableCount();

		/**
		 * ��ȡ��������
		 * 
		 * @return
		 */
		public double getAllocateCount();

	}

}
