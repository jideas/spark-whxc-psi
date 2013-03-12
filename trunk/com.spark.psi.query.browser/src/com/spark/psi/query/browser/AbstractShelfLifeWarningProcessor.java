package com.spark.psi.query.browser;

import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.key.BaseDictionaryKey;

public abstract class AbstractShelfLifeWarningProcessor<Item> extends PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Button_Reset = "Button_Reset";
	public static final String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	public static final String ID_Button_Export = "Button_Export";
	
	public static final String ID_Store = "Select_Store";
	public static final String ID_Status = "Select_Status";
	
	private Label countLabel  = null;
	protected LWComboList storeList; 
	protected LWComboList statusList;
	protected StoreSource storeSource;
	@Override
	public void process(Situation context) {
		super.process(context);
		countLabel = createLabelControl(ID_Label_Count);
		storeList = createLWComboListControl(ID_Store);
		storeSource = new StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
		storeSource.setShowAllStoreItem(true);
		storeList.getList().setSource(storeSource);
		storeList.getList().setInput(null);
		statusList = createLWComboListControl(ID_Status);
		StatusSource statusSource = new StatusSource();
		statusList.getList().setSource(statusSource);
		statusList.getList().setInput(null);
//		final Button advanceSearch = createButtonControl(ID_Button_AdvanceSearch);
//		final Button resetButton = createButtonControl(ID_Button_Reset);
//		final Button exportButton = createButtonControl(ID_Button_Export);
		
//		advanceSearch.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				doAdvanceSearch();
//			}
//		});
//		
//		resetButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				doReset();
//			}
//		});
		
		storeList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				doRefresh();
			}
		});
		
		statusList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				doRefresh();
			}
		});
		
		storeList.setSelection(storeSource.getFirstStoreId());
		statusList.setSelection("00");
		
//		exportButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				doExport();
//			}
//		});
	}
	
	protected void setRecordCount(boolean isFistPage, long count) {
//		int size = count;
//		if (!isFistPage) {
//			String preSize = countLabel.getText();
//			if (StringHelper.isNotEmpty(preSize)) {
//				size += Integer.parseInt(preSize);
//			}
//		}
		countLabel.setText(String.valueOf(count));
	}
	
	protected abstract void doAdvanceSearch();
	
	protected abstract void doReset();
	
	protected abstract void doRefresh();
	
private class StatusSource extends ListSourceAdapter {
		
//		private EnumEntity firstEntity = null;
		
		public String getElementId(Object element) {
			EnumEntity entity = (EnumEntity)element;
			return entity.getCode();
		}
		
		public Object getElementById(String id) {
			BaseDictionaryKey key = new BaseDictionaryKey(BaseDictionaryEnum.ShelfLifeWarningStatus, id);
			EnumEntity entity = getContext().find(EnumEntity.class, key);
			return entity;
		}
		
		public String getText(Object element) {
			EnumEntity entity = (EnumEntity)element;
			return entity.getName();
		}
		
		public Object[] getElements(Object inputElement) {
			List<EnumEntity> list = getContext().getList(EnumEntity.class,
					new BaseDictionaryKey(BaseDictionaryEnum.ShelfLifeWarningStatus));
//			if (list.size() > 0) {
//				firstEntity = list.get(0);
//			}
			return list.toArray();
		}
		
//		public EnumEntity getFirstEntity() {
//			return firstEntity;
//		}
		
	}
	
//	protected abstract void doExport();
}
