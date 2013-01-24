package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;

/**
 * 
 * <p>通迅录(供应商)列表视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-4
 */
public class SupplierContactListRender extends ContactBaseListRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		//发送短信
		Button sendMsgButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		sendMsgButton.setID(SupplierContactListProcessor.ID_BUTTON_SENDMSG);
		sendMsgButton.setText("发送短信");
		//发送邮件
		Button sendEmailButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		sendEmailButton.setID(SupplierContactListProcessor.ID_BUTTON_SENDMAIL);
		sendEmailButton.setText("发送邮件");
	}

	/**
	 * 获取列
	 */
	public STableColumn[] getColumns(){
		STableColumn[] columns = new STableColumn[6];
		//姓名
		columns[0] = new STableColumn("name", nameColumnWidth, JWT.LEFT, "姓名");
		columns[0].setSortable(true);
		//单位(部门)
		columns[1] = new STableColumn("department", unitColumnWidth, JWT.LEFT, "单位(部门)");
		columns[1].setGrab(true);
		columns[1].setSortable(true);
		//职位
		columns[2] = new STableColumn("job", positionColumnWidth, JWT.CENTER, "职位");
		columns[2].setSortable(true);
		//固话
		columns[3] = new STableColumn("phone", phoneNumberColumnWidth, JWT.CENTER, "固话");
		columns[3].setSortable(true);
		//手机
		columns[4] = new STableColumn("mobile", cellPhoneNumberColumnWidth, JWT.CENTER, "手机");
		columns[4].setSortable(true);
		//邮箱
		columns[5] = new STableColumn("email", emailColumnWidth, JWT.LEFT, "邮箱");
		columns[5].setSortable(true);
		columns[5].setGrab(true);
		return columns;
	}

	/**
	 * 单元格取值
	 */
	public String getText(Object element, int columnIndex){
		ContactPersonItem contactPersonItem = (ContactPersonItem)element;
		String text = "";
		switch(columnIndex){
	        case 0:
	        	text = StableUtil.toLink(SupplierContactListProcessor.ID_ACTION_VIEW, "", contactPersonItem.getName());
		        break;
	        case 1:
	        	text = contactPersonItem.getDepartment();
	        	break;
	        case 2:
	        	text = contactPersonItem.getJob();
	        	break;
	        case 3:
	        	text = contactPersonItem.getPhone();
	        	break;
	        case 4:
	        	text = contactPersonItem.getMobile();
	        	break;
	        case 5:
	        	text = contactPersonItem.getEmail();
	        	break;
        }
		return text;
	}
}
