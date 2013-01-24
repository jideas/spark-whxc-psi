package com.spark.psi.base.browser.station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.station.entity.StationManagerItem;
import com.spark.psi.publish.base.station.key.GetAllStationManagerKey;

public class StationManagerSelectProcessor extends PageProcessor implements SEditContentProvider {

	public final static String ID_Table = "EmployeeTable";
	public final static String ID_Button_Select = "ButtonSelect";
	public final static String ID_Button_Cancel = "ButtonCancel";

	protected SEditTable table;

	protected String[] employeeIds;

	protected GUID storeId;

	private Map<String, StationManagerItem> itemMap;

	/**
	 * 
	 */
	public final void init(Situation context) {
		Object argument = this.getArgument();
		if (argument != null && argument instanceof String) {
			employeeIds = StringUtils.split((String) argument, ";");
		}
		Object arg1 = this.getArgument2();
		if (arg1 != null && arg1 instanceof GUID) {
			storeId = (GUID) arg1;
		}
	}

	public final void process(Situation context) {
		table = this.createControl(ID_Table, SEditTable.class);
		table.setContentProvider(this);
		table.render();

		//
		Button selectButton = this.createControl(ID_Button_Select, Button.class);
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleConfirmSelect();
			}
		});
		Button cancelButton = this.createControl(ID_Button_Cancel, Button.class);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
	}

	protected String[] getSelections() {
		return table.getSelections();
	}

	/**
	 * 
	 */
	private void handleConfirmSelect() {
		String[] selections = getSelections();
		if (selections == null || selections.length == 0) {
			alert("请选择站点负责人！");
			return;
		}
		if (selections != null && selections.length > 0) {
			GUID id = GUID.valueOf(selections[0]);
			EmployeeInfo user = getContext().find(EmployeeInfo.class, id);
			StationManagerInfo info = new StationManagerInfo();
			info.setId(id);
			info.setIdNo(user.getIdNumber());
			info.setEmail(user.getEmail());
			info.setMobile(user.getMobileNo());
			info.setName(user.getName());
			getContext().bubbleMessage(new MsgResponse(true, info));
		}
	}

	protected class StationManagerInfo {
		private GUID id;
		private String name;
		private String mobile;
		private String idNo;
		private String email;

		public GUID getId() {
			return id;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getIdNo() {
			return idNo;
		}

		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}

	/**
	 * 根据id获取员工姓名
	 * 
	 * @param id
	 * @return
	 */
	protected final String getEmployeeNameById(String id) {
		return table.getExtraData(id, "name")[0];
	}

	public final String getElementId(Object element) {
		return ((StationManagerItem) element).getId().toString();
	}

	public String getValue(Object element, String columnName) {
		return null;
	}

	public String[] getActionIds(Object element) {
		return null;
	}

	public Object getNewElement() {
		return null;
	}

	public SNameValue[] getExtraData(Object element) {
		return new SNameValue[] { new SNameValue("name", ((StationManagerItem) element).getName() + "("
				+ ((StationManagerItem) element).getDeptName() + ")") };
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		List<StationManagerItem> list = context.getList(StationManagerItem.class, new GetAllStationManagerKey());
		itemMap = new HashMap<String, StationManagerItem>();
		for (StationManagerItem item : list) {
			itemMap.put(item.getId().toString(), item);
		}
		return list.toArray();
	}

	public boolean isSelectable(Object element) {
		return true;
	}

	public boolean isSelected(Object element) {
		String id = getElementId(element);
		if (employeeIds != null) {
			for (String salesMan : employeeIds) {
				if (salesMan.equals(id)) {
					return true;
				}
			}
		}
		return false;
	}

}
