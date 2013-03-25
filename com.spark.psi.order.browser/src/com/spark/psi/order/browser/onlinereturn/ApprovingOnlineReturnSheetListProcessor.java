/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnListEntity;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey.OnlineReturnTab;

/**
 * 
 * 
 */
public class ApprovingOnlineReturnSheetListProcessor extends OnlineReturnSheetListProcessor {
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "ID_Search";
	
	private Label countLabel    = null;
	private SSearchText2 search = null;
	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	@Override
	public String[] getTableActionIds() {
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
			return new String[] { Action.Approval.name() };
		}
		return null;
	}
	@Override
	protected String[] getElementActionIds(Object element) {
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
			return new String[] { Action.Approval.name() };
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
		GetOnlineReturnListKey key = new GetOnlineReturnListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true, OnlineReturnTab.Approving);
		key.setSearchText(search.getText());
		OnlineReturnListEntity listEntity = context.find(OnlineReturnListEntity.class, key);
		if (null == listEntity) return null;
//		int size = listEntity.getItemList().size();
//		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
//			String preSize = countLabel.getText();
//			if (StringHelper.isNotEmpty(preSize)) {
//				size += Integer.parseInt(preSize);
//			}
//		}
		countLabel.setText(listEntity.getTotalCount()+"");
		return listEntity.getItemList().toArray();
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)
				|| Action.Approval.name().equals(actionName)) {
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