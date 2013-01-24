package com.spark.psi.base.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;

public class EnumEntitySource extends ListSourceAdapter {

	private EnumType enumType;

	public EnumEntitySource(EnumType enumType) {
		this.enumType = enumType;
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement == null) {
			return null;
		}
		Context context = Display.getCurrent().getSituation();
		if (inputElement instanceof String) {
			return context.getList(EnumEntity.class, enumType,
					(String) inputElement).toArray();
		}
		throw new IllegalArgumentException();
	}

	public String getText(Object element) {
		return ((EnumEntity) element).getName();
	}

	public String getElementId(Object element) {
		return ((EnumEntity) element).getCode();
	}

	public Object getElementById(String id) {
		return Display.getCurrent().getSituation()
				.find(EnumEntity.class, enumType, id);
	}
}
