package com.spark.psi.base.browser.contact;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.contact.entity.ContactItem;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;
import com.spark.psi.publish.base.contact.key.FindColleagueContactListKey;

/**
 * 
 * <p>通迅录(同事)列表处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-4
 */
public class ColleagueContactListProcessor extends ContactBaseListProcessor<ContactItem>{
 
	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
	}

	/**
	 * 获得通讯录(同事)列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindColleagueContactListKey key = new FindColleagueContactListKey();
		//拼音过滤
		key.setPhonetics(phoneticNavigatorBar.getText());
		//搜索字符串
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("输入搜索内容")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		//排序
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element){
		return ((ContactPersonItem)element).getId().toString();
	}
	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
	}

	@Override
	protected String getExportFileTitle() {
		return "联系人-同事";
	}

}
