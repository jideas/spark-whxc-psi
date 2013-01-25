/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;

/**
 * 待提交销售退货单列表处理器
 * 
 * 销售退货
 * 
 */
public abstract class OnlineReturnSheetListProcessor extends PSIListPageProcessor<OnlineReturnItem> {


	public enum OlReturnColumns {
		CreateDate("退货日期"), Customer("会员"), billsNo("退货单号"), OnlineBillsNo("相关单据"), Creator("创建人"), Status(
				"处理状态"), Amount("金额")// , Station("退货站点")
		;
		private String title;

		private OlReturnColumns(String title) {
			this.title = title;
		}

		public String title() {
			return title;
		}
	}
	
	protected LoginInfo loginInfo  = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	protected String getExportFileTitle() {
		return "网上退货";
	}

//	protected void setBillsCount(int count) {
//		labelCount.setText(DoubleUtil.getRoundStr(DoubleUtil.strToDouble(count + ""), 0));
//	}
//
//	protected String getSearchText() {
//		return this.textSearch.getText();
//	}

}