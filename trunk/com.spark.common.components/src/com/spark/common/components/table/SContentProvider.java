package com.spark.common.components.table;

import com.jiuqi.dna.core.Context;

/**
 * ±Ì∏Ò Ù–‘
 */
public interface SContentProvider {

	public Object[] getElements(Context context, STableStatus tablestatus);

	public String getElementId(Object element);

	public boolean isSelected(Object element);

	public boolean isSelectable(Object element);

}
