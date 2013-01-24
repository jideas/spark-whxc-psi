package com.spark.psi.base.browser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.common.components.controls.text.PSIConstant;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

/**
 * 
 */
public class PSIProcessorUtils {

	/**
	 * 初始化行政区划列表
	 * 
	 * @param provinceList
	 * @param cityList
	 * @param districtList
	 */
	public final static void initAreaSource(final LWComboList provinceList,
			final LWComboList cityList, final LWComboList districtList) {
		final EnumEntitySource source = new EnumEntitySource(EnumType.Area);
		provinceList.getList().setSource(source);
		provinceList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				String province = provinceList.getText();
				String input = StringHelper.isEmpty(province) ? null : province;
				Object[] elements = source.getElements(input);
				cityList.getList().setInput(input);
				cityList.setSelection(elements != null && elements.length > 0 ? elements[0] : null);
				cityList.pack();
			}
		});
		cityList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				String city = cityList.getText();
				String input = StringHelper.isEmpty(city) ? null : city;
				Object[] elements = source.getElements(input);
				districtList.getList().setInput(input);
				districtList.setSelection(elements != null && elements.length > 0 ? elements[0]
						: null);
				districtList.pack();
			}
		});
		cityList.getList().setSource(source);
		districtList.getList().setSource(source);
		provinceList.getList().setInput("");
		cityList.getList().setInput(null);
		districtList.getList().setInput(null);
		provinceList.setHint("省（直辖市）");
		cityList.setHint("市（区）");
		districtList.setHint("区（县）");
		provinceList.setSelection(PSIConstant.DefArea.provinceDefaut);
		cityList.setSelection(PSIConstant.DefArea.cityDefaut);
	}

	/**
	 * 
	 * @param queryTermList
	 */
	public final static void initQueryTermSource(final LWComboList queryTermList) {
		QueryTermSource source = new QueryTermSource();
		queryTermList.getList().setSource(source);
		queryTermList.getList().setInput(null);
		queryTermList.setSelection(source.getFirstStoreId());
	}

	public final static QueryScopeSource initQueryScopeSource(
			final LWComboList queryList, String myItemName, String allItemName,
			Auth... auths) {
		QueryScopeSource source = new QueryScopeSource(myItemName, allItemName,
				auths);
		queryList.getList().setSource(source);
		queryList.getList().setInput(null);
		queryList.setVisibleItemCount(8);
		queryList.setSelection(source.getFirst());
		return source;
	}

	public final static QueryScopeSource initQueryScopeSource(
			final LWComboList queryList, String myItemName, Auth... auths) {
		QueryScopeSource source = new QueryScopeSource(myItemName, auths);
		queryList.getList().setSource(source);
		queryList.getList().setInput(null);
		queryList.setVisibleItemCount(8);
		queryList.setSelection(source.getFirst());
		return source;

	}

	public final static QueryScopeSource initQueryScopeSource(
			final LWComboList queryList, String myItemName,String allItemName,boolean isShowAll) {
		QueryScopeSource source = new QueryScopeSource(myItemName, allItemName,isShowAll);
		queryList.getList().setSource(source);
		queryList.getList().setInput(null);
		queryList.setVisibleItemCount(8);
		queryList.setSelection(source.getFirst());
		return source;
	}
	
	public final static ListSourceAdapter initDeparmentQueryScopeSource(
			final LWComboList queryList){
		final Context context =  Display.getCurrent().getSituation();
		final DepartmentTree tree =  context.find(DepartmentTree.class);
		final Map<GUID,QueryScope> map = new LinkedHashMap<GUID,QueryScope>();
		ListSourceAdapter source = new ListSourceAdapter(){
			
						
			public String getElementId(Object element){
				return ((QueryScope)element).getDepartmentId().toString();
			}
			
			public Object getElementById(String id){
				return map.get(GUID.tryValueOf(id));
			}
			
			public String getText(Object element){
				return ((QueryScope)element).getName();
			}
			
			public Object[] getElements(Object inputElement){
				DepartmentNode root = tree.getRoot();
				map.put(root.getId(),new QueryScope("公司",root.getId()));
				map.put(GUID.emptyID,new QueryScope("公司直属"));
				fullChildren(root);
				return map.values().toArray();
			}

			private void fullChildren(DepartmentNode root)
            {
	            if(root.getChildren()==null)return ;
	            for(DepartmentNode departmentNode : root.getChildren()){
	                map.put(departmentNode.getId(),new QueryScope(departmentNode.getFillPathName(), departmentNode.getId()));
	                fullChildren(departmentNode);
                }
            }
		}; 
		queryList.getList().setSource(source);
		queryList.getList().setInput(null);
		queryList.setVisibleItemCount(8);
		queryList.setSelection(map.get(tree.getRoot().getId()));
		return source;
		
	}
}
