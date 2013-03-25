/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnListEntity;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey.OnlineReturnTab;

/**
 * 待提交销售退货单列表处理器
 * 
 * 销售退货
 * 
 */
public class FinishedOnlineReturnSheetListProcessor extends OnlineReturnSheetListProcessor {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Search = "ID_Search";
	public static final String ID_Search_Advanced = "Search_Advanced";
	
	private Label countLabel    = null;
	private Label amountLabel    = null;
	private SSearchText2 search = null;
	private AdvanceSearchCondition advanceCondition = null;
	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = createLabelControl(ID_Label_Count);
		amountLabel = createLabelControl(ID_Label_Amount);
		search = createControl(ID_Search, SSearchText2.class);
		
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				advanceCondition = null;
				table.render();
			}
		});
		final Button advanceButton = createControl(ID_Search_Advanced, Button.class);
		advanceButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = OnlineReturnCommon.createAdvanceRequest();
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							advanceCondition = (AdvanceSearchCondition) returnValue;
							table.render();
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	@Override
	public String[] getTableActionIds() {
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
			return new String[] { Action.ReExecute.name()};
		}
		return null;
	}
	@Override
	protected String[] getElementActionIds(Object element) {
		OnlineReturnItem item = (OnlineReturnItem)element;
		if (OnlineReturnStatus.Stopped.equals(item.getStatus())) {
			return new String[] { Action.ReExecute.name()};
		}
		return null;
	}
	@Override
	public String getElementId(Object element) {
		OnlineReturnItem item = (OnlineReturnItem)element;
		return item.getId().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetOnlineReturnListKey key = new GetOnlineReturnListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true, OnlineReturnTab.Finished);
		key.setSearchText(search.getText());
		if(null!=advanceCondition)
		{
			key.setCreateDateBegin(advanceCondition.getCreateDateBegin());
			key.setCreateDateEnd(advanceCondition.getCreateDateEnd());
			key.setRealName(advanceCondition.getRealName());
			key.setStationName(advanceCondition.getStationName());
		}
		OnlineReturnListEntity listEntity = context.find(OnlineReturnListEntity.class, key);
		if (null == listEntity) return null;
//		int size = listEntity.getItemList().size();
//		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
//			String preSize = countLabel.getText();
//			if (StringHelper.isNotEmpty(preSize)) {
//				size += Integer.parseInt(preSize);
//			}
//		}
		countLabel.setText(String.valueOf(listEntity.getTotalCount()));
		amountLabel.setText(DoubleUtil.getRoundStr(listEntity.getTotalAmount()));
//		countLabel.setText("" + listEntity.getItemList().size());
		return listEntity.getItemList().toArray();
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)
				|| Action.ReExecute.name().equals(actionName)) {
			PageController pc = new PageController(OnlineReturnSheetDetailProcessor.class, OnlineReturnSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId), OnlineReturnSheetDetailProcessor.Type.Detail);
			MsgRequest request = new MsgRequest(pci, "网上退货单详情");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}
}