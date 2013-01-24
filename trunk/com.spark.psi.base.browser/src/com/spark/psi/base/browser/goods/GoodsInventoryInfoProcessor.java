/**
 * 
 */
package com.spark.psi.base.browser.goods;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.pages.PageProcessor;
import com.spark.psi.base.browser.GoodsCommon.GoodsInventoryScope;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.ThresholdControlType;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;

/**
 * 
 *
 */
public class GoodsInventoryInfoProcessor extends PageProcessor {
	public final static String ID_Label_Code = "Label_Code";
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_List_Scope = "List_Scope";
	public final static String ID_List_Type = "List_Type";
	public final static String ID_Area_Content = "Area_Content";
	public final static String ID_Button_Save = "Button_Save";

	private GoodsInfo goodsInfo;
	
	private Composite contentArea;
	private LWComboList listScope;
	private LWComboList listType;
	
	private List<GoodsItemInventoryInfoPage> itemInventoryList = new ArrayList<GoodsItemInventoryInfoPage>();
	
	private boolean isShowOnly = true;
	
	@Override
	public void init(Situation context) {
		if(getArgument() != null && getArgument() instanceof GUID) {
			GUID goodsId = (GUID)getArgument();
			goodsInfo = getContext().find(GoodsInfo.class, goodsId);
		}
		if(getArgument2() != null && getArgument2() instanceof Boolean) {
			isShowOnly = (Boolean) getArgument2();
		}
	}
	
	@Override
	public void process(Situation context) {
		if(goodsInfo == null) return;
		final Label codeLabel = createControl(ID_Label_Code, Label.class);
		final Label nameLabel = createControl(ID_Label_Name, Label.class);
		
		codeLabel.setText(goodsInfo.getCode());
		nameLabel.setText(goodsInfo.getName());
		
		Composite contentCmp = createControl(ID_Area_Content, Composite.class);
		ScrolledPanel panel = new ScrolledPanel(contentCmp , JWT.V_SCROLL);
		contentArea = panel.getComposite();
		GridLayout glContent = new GridLayout();
		glContent.verticalSpacing = 20;
		contentArea.setLayout(glContent);
//		contentArea.setBorder(CBorder.BORDER_NORMAL);
		
//		goodsInfo.
		listScope = createControl(ID_List_Scope, LWComboList.class);
		listScope.getList().setSource(new ListSourceAdapter() {
			
			public String getElementId(Object element) {
				GoodsInventoryScope scope = (GoodsInventoryScope)element;
				return scope.name();
			}
			
			public Object getElementById(String id) {
				return GoodsInventoryScope.getScopeByName(id);
			}
			
			public String getText(Object element) {
				GoodsInventoryScope scope = (GoodsInventoryScope)element;
				return scope.getTitle();
			}
			
			public Object[] getElements(Object inputElement) {
				return new GoodsInventoryScope[] {GoodsInventoryScope.Total, GoodsInventoryScope.EachStore};
			}
		});
		listType = createControl(ID_List_Type, LWComboList.class);
		listType.getList().setSource(new ListSourceAdapter() {
			
			public String getElementId(Object element) {
				ThresholdControlType type = (ThresholdControlType)element;
				return type.name();
			}
			
			public Object getElementById(String id) {
				return ThresholdControlType.valueOf(id);
			}
			
			public String getText(Object element) {
				ThresholdControlType type = (ThresholdControlType)element;
				return type.getName();
			}
			
			public Object[] getElements(Object inputElement) {
				return new ThresholdControlType[] {ThresholdControlType.COUNT, ThresholdControlType.AMOUNT};
			}
		});
		listScope.getList().setInput(null);
		listType.getList().setInput(null);
		
		listScope.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				if(listScope.getList().getSeleted().equals(GoodsInventoryScope.Total.name())) {
					listType.setSelection(ThresholdControlType.AMOUNT.name());
					listType.setEnabled(false);
				} else {
					listType.setEnabled(true);
				}
				showItemPage();
			}
		});
		
		listType.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				showItemPage();
			}
		});
		GoodsInventoryScope viewScope = null;
		ThresholdControlType viewType = null;
		if (InventoryWarningType.ALL_Amount.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.Total;
			viewType = ThresholdControlType.AMOUNT;
		} else if (InventoryWarningType.ALL_Count.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.Total;
			viewType = ThresholdControlType.COUNT;
		} else if (InventoryWarningType.Store_Amount.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.EachStore;
			viewType = ThresholdControlType.AMOUNT;
		} else if (InventoryWarningType.Store_Count.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.EachStore;
			viewType = ThresholdControlType.COUNT;
		}
//		try {
		listType.setSelection(viewType == null ? ThresholdControlType.AMOUNT : viewType);
		listScope.setSelection(viewScope == null ? GoodsInventoryScope.Total : viewScope);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		final Button saveBtn = createControl(ID_Button_Save, Button.class);
		saveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
//				getContext().broadcastMessage(new GoodsItemLimitMsg());
				boolean isSaveSuccess = true;
				for(GoodsItemInventoryInfoPage itemPage : itemInventoryList) {
					isSaveSuccess = itemPage.doSave();
					if(!isSaveSuccess) {
						break;
					}
				}
				if(isSaveSuccess) {
					hint("±£´æ³É¹¦¡£");
				}
			}
		});
		if(isShowOnly) {
//			saveBtn.dispose();
			saveBtn.getParent().dispose();
			listType.getPrev().dispose();
			listScope.getPrev().dispose();
			listType.setVisible(false);
			listScope.setVisible(false);
		}
	}
	
	private void showItemPage() {
		contentArea.clear();
		GoodsInventoryScope viewScope = null;
		ThresholdControlType viewType = null;
		if (InventoryWarningType.ALL_Amount.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.Total;
			viewType = ThresholdControlType.AMOUNT;
		} else if (InventoryWarningType.ALL_Count.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.Total;
			viewType = ThresholdControlType.COUNT;
		} else if (InventoryWarningType.Store_Amount.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.EachStore;
			viewType = ThresholdControlType.AMOUNT;
		} else if (InventoryWarningType.Store_Count.equals(goodsInfo.getGoodsWarnningType())) {
			viewScope = GoodsInventoryScope.EachStore;
			viewType = ThresholdControlType.COUNT;
		}
//		listType.setSelection(viewType == null ? ThresholdControlType.AMOUNT.name() : viewType);
//		listScope.setSelection(viewScope == null ? GoodsInventoryScope.Total.name() : viewScope);
		if (!isShowOnly) {
			if(listScope.getList().getSeleted() != null) {
				viewScope = GoodsInventoryScope.valueOf(listScope.getList().getSeleted());
			} else {
				viewScope = GoodsInventoryScope.Total;
			}
			if(listType.getList().getSeleted() != null) {
				viewType = ThresholdControlType.valueOf(listType.getList().getSeleted());
			} else {
				viewType = ThresholdControlType.AMOUNT;
			}
		}
		itemInventoryList.clear();
		for(GoodsItemData goodsItem : goodsInfo.getItems()) {
			GoodsItemInventoryInfoPage itemInventoryPage = new GoodsItemInventoryInfoPage(contentArea, goodsItem, viewScope, viewType, isShowOnly);
			itemInventoryPage.setLayoutData(GridData.INS_FILL_HORIZONTAL);
			itemInventoryList.add(itemInventoryPage);
		}
		contentArea.layout();
	}

}


