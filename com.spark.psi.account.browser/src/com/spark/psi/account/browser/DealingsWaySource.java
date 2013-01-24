/**
 * 
 */
package com.spark.psi.account.browser;


import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.psi.publish.DealingsWay;

/**
 * 
 *
 */
public class DealingsWaySource extends ListSourceAdapter {

	public Object[] getElements(Object inputElement) {
		return new DealingsWay[] {DealingsWay.Cash, DealingsWay.ChargePay, DealingsWay.Account, DealingsWay.Check};
	}

	public String getText(Object element) {
		DealingsWay enumEntity = (DealingsWay)element;
		return enumEntity.getName();
	}

	public Object getElementById(String id) {
		return DealingsWay.getDealingsWay(id);
	}
	public String getElementId(Object element) {
		DealingsWay enumEntity = (DealingsWay)element;
		return enumEntity.getCode();
	}
	
	
	

}
