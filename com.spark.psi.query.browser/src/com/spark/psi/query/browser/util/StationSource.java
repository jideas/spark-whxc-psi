package com.spark.psi.query.browser.util;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;

/**
 * 站点列表源
 * 
 */
public class StationSource extends ListSourceAdapter {

	private String firstStationId;

	private GetStationListKey key;
	
	private int size;//下拉数据项大小
	
	
	/**
	 * 获取下拉数据项的长度
	 * @return
	 */
	public int getSize() {
		return size;
	}

	public StationSource() {
		key = new GetStationListKey();
		key.setQueryAll(true);
	}

	public Object[] getElements(Object inputElement) {
		Context context = Display.getCurrent().getSituation();


		List<Object> stationList = new ArrayList<Object>();
		stationList.add(0, "全部站点");
		firstStationId = GUID.emptyID.toString();

		List<StationItem> list = context.getList(StationItem.class, key);
		if(CheckIsNull.isNotEmpty(list))
		{
			stationList.addAll(list);
		}
			
		
		
		if(stationList!=null &&!stationList.isEmpty())
			this.size = stationList.size();
		else
			this.size = 0;
		
		return stationList.toArray();
	}

	public String getFirstSationId() {
		return this.firstStationId;
	}

	public String getText(Object element) {
		if (element instanceof String) {
			return (String) element;
		}
		return ((StationItem) element).getShortName();
	}

	public String getElementId(Object element) {
		if (element instanceof String) {
			return GUID.emptyID.toString();
		}
		return ((StationItem) element).getId().toString();
	}

	public Object getElementById(String id) {
		if (id.equals(GUID.emptyID.toString())) {
			return "全部站点";
		}
		return Display.getCurrent().getSituation()
				.find(StationItem.class, GUID.tryValueOf(id));
	}

}
