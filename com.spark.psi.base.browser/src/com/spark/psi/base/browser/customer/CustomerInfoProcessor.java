package com.spark.psi.base.browser.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;

public abstract class CustomerInfoProcessor extends PartnerInfoProcessor {

	public static final String ID_Text_PriceStrategy = "Text_PriceStrategy";
	public static final String ID_List_CustomerType = "List_CustomerType";
	public static final String ID_Text_CreditStrategy = "Text_CreditStrategy";
	public static final String ID_Text_TaxNumber = "Text_TaxNumber";
	protected LWComboList pricePolicyList, customerTypeList;
	protected Text taxNumberText;

	@Override
	public void process(Situation situation) {
		super.process(situation);

		this.taxNumberText = this.createTextControl(ID_Text_TaxNumber);
		final List<EnumEntity> priceEnums1 = getContext().getList(EnumEntity.class,
				new BaseDictionaryKey(BaseDictionaryEnum.CustomerPricePolicy));
		final List<EnumEntity> priceEnums2 = getContext().getList(EnumEntity.class,
				new BaseDictionaryKey(BaseDictionaryEnum.CustomerType));

		pricePolicyList = createControl(ID_Text_PriceStrategy, LWComboList.class);
		pricePolicyList.getList().setSource(new ListSourceAdapter() {

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
				for (EnumEntity ee : priceEnums1) {
					map.put(ee.getCode(), ee);
				}
				return priceEnums1.toArray();
			}
		});
		pricePolicyList.getList().setInput(null);
		pricePolicyList.setSelection("01");

		customerTypeList = createControl(ID_List_CustomerType, LWComboList.class);
		customerTypeList.getList().setSource(new ListSourceAdapter() {

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
				for (EnumEntity ee : priceEnums2) {
					map.put(ee.getCode(), ee);
				}
				return priceEnums2.toArray();
			}
		});
		customerTypeList.getList().setInput(null);
		customerTypeList.setSelection("01");
	}
}
