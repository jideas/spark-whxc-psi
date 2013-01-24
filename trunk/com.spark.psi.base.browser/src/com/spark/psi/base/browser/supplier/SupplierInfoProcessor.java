package com.spark.psi.base.browser.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;

public abstract class SupplierInfoProcessor extends PartnerInfoProcessor {

	public static final String ID_List_Type = "List_Type";
	public static final String ID_List_Way = "List_Way";
	public static final String ID_Text_TaxNumber = "Text_TextNumber";
	public static final String ID_Text_NoticeAddress = "Text_NoticeAddress";
	public static final String ID_Text_NoticePost = "Text_NoticePost";
	public static final String ID_Table_Acount = "Table_Acount";

	/**
	 * 默认银行账户个数
	 */
	public static final int DEFAULT_ACOUNT_COUNT = 5;

	public static enum AcountTableName {
		bank, acountName, acountNumber, remark
	}

	protected LWComboList typeList;
	protected LWComboList wayList;
	protected Text noticeAddress;
	protected Text noticePost;
	protected SEditTable accountTable;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		final List<EnumEntity> typeEnums1 = getContext().getList(EnumEntity.class,
				new BaseDictionaryKey(BaseDictionaryEnum.SupplierType));
		final List<EnumEntity> typeEnums2 = getContext().getList(EnumEntity.class,
				new BaseDictionaryKey(BaseDictionaryEnum.SupplierCooperation));
		typeList = createControl(ID_List_Type, LWComboList.class);
		this.noticeAddress = createControl(ID_Text_NoticeAddress, Text.class);
		this.noticePost = createControl(ID_Text_NoticePost, Text.class);
		typeList.getList().setSource(new ListSourceAdapter() {

			private Map<String, EnumEntity> map;

			public String getElementId(Object element) {
				EnumEntity ee = (EnumEntity) element;
				return ee.getCode();
			}

			public Object getElementById(String id) {
				return map.get(id);
			}

			public String getText(Object element) {
				EnumEntity ee = (EnumEntity) element;
				return ee.getName();
			}

			public Object[] getElements(Object inputElement) {
				map = new HashMap<String, EnumEntity>();
				for (EnumEntity ee : typeEnums1) {
					map.put(ee.getCode(), ee);
				}
				return typeEnums1.toArray();
			}
		});
		typeList.getList().setInput(null);
		typeList.setSelection("01");
		wayList = createControl(ID_List_Way, LWComboList.class);
		wayList.getList().setSource(new ListSourceAdapter() {

			private Map<String, EnumEntity> map;

			public String getElementId(Object element) {
				EnumEntity ee = (EnumEntity) element;
				return ee.getCode();
			}

			public Object getElementById(String id) {
				return map.get(id);
			}

			public String getText(Object element) {
				EnumEntity ee = (EnumEntity) element;
				return ee.getName();
			}

			public Object[] getElements(Object inputElement) {
				map = new HashMap<String, EnumEntity>();
				for (EnumEntity ee : typeEnums2) {
					map.put(ee.getCode(), ee);
				}
				return typeEnums2.toArray();
			}
		});
		wayList.getList().setInput(null);
		wayList.setSelection("01");

		accountTable = createControl(ID_Table_Acount, SEditTable.class);
		accountTable.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (Action.Delete.name().equals(actionName)) {
					accountTable.removeRow(rowId);
					if (accountTable.getAllRowId().length == 1) {
						accountTable.addRow(GUID.randomID().toString());
					}
					accountTable.renderUpate();
				}
			}
		});
	}

}
