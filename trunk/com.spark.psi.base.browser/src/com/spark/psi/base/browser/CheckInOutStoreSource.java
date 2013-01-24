package com.spark.psi.base.browser;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;

/**
 * 仓库列表源
 * 
 */
public class CheckInOutStoreSource extends ListSourceAdapter {

	private String firstStoreId; 

	private GetStoreListKey key;
	
	private int size;//下拉数据项大小

	
	/**
	 * 获取下拉数据项的长度
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 查询指定状态的仓库 并加入权限过滤
	 * 
	 * @param statuss
	 */
	public CheckInOutStoreSource(StoreStatus... statuss) {
		key = new GetStoreListKey(statuss);
	} 

	public Object[] getElements(Object inputElement) {
		Context context = Display.getCurrent().getSituation(); 
		List<Object> storeList = new ArrayList<Object>();

		if(context.find(LoginInfo.class).hasAuth(Auth.Boss)){
			key.setAllStore(true);
		}
		@SuppressWarnings("unchecked")
		ListEntity<StoreItem> result = context.find(ListEntity.class, key);
		if (result != null) {
			List<StoreItem> itemList = result.getItemList();
			if (itemList.size() > 0) {
				firstStoreId = itemList.get(0).getId().toString();
			}
			storeList.addAll(itemList);
		} 
		
		if(storeList!=null &&!storeList.isEmpty())
			this.size = storeList.size();
		else
			this.size = 0;
		
		return storeList.toArray();
	}

	public String getFirstStoreId() {
		return this.firstStoreId;
	}

	public String getText(Object element) {
		if (element instanceof String) {
			return (String) element;
		}
		return ((StoreItem) element).getName();
	}

	public String getElementId(Object element) {
		if (element instanceof String) {
			return GUID.emptyID.toString();
		}
		return ((StoreItem) element).getId().toString();
	}

	public Object getElementById(String id) {
		if (id.equals(GUID.emptyID.toString())) {
			return "全部仓库";
		}
		return Display.getCurrent().getSituation()
				.find(StoreItem.class, GUID.tryValueOf(id));
	}

}
