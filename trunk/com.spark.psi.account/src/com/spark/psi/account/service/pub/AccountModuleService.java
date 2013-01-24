package com.spark.psi.account.service.pub;

import java.util.Date;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.task.dealing.DealingItemTask;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.account.intf.util.AccountUtil;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.PartnerType;

/**
 * 模块间服务
 * 
 */
public class AccountModuleService extends Service {

	public AccountModuleService() {
		super("CusdealService");
	}

	/**
	 * 
	 * <p>
	 * 往来
	 */
	@Publish
	protected class SaveCusdealHanlder extends SimpleTaskMethodHandler<CusdealTask> {
		@Override
		protected void handle(Context context, CusdealTask task) throws Throwable {
			String type = task.getCusdealType().getCode();

			if (!valiData(context, task)) {
				throw new Throwable("参数错误！");
			}

			DealingItemTask iTask = new DealingItemTask();
			DealingItem item = new DealingItem(type);
			item.setPlanAmount(task.getPlanAmount());
			item.setBillsId(task.getBillsGuid());
			item.setBillsNo(task.getBillsNo());
			if (CheckIsNull.isEmpty(task.getPubdate()) || 0 == task.getPubdate()) {
				item.setCreateDate(new Date().getTime());
			} else {
				item.setCreateDate(task.getPubdate());
			}
			item.setPartnerId(task.getPartnerId());
			item.setRealAmount(task.getRealAmount());
			item.setReceiptType(task.getReceiptType());
			item.setRemark(task.getRemark());
			item.setId(context.newRECID());
			item.setAccountBillsId(task.getAccountBillsId());
			item.setAccountBillsNo(task.getAccountBillsNo());
			iTask.setItem(item);

			context.handle(iTask);

		}
	}

	/**
	 * 校验数据是否正确
	 * 
	 * @param context
	 * @param task
	 * @return boolean
	 */
	public boolean valiData(Context context, CusdealTask task) {
		if (CheckIsNull.isEmpty(task.getCusdealType()) || CheckIsNull.isEmpty(task.getPartnerId())) {
			// TODO 类型或客户、供应商不能为空，异常待实现
			return false;
		}
		Partner partner = context.find(Partner.class, task.getPartnerId());
		if (null == partner) {
			// TODO 客户/供应商没找到，异常待实现
			return false;
		}
		if (!valiDealingsType(task, partner)) {
			// TODO 往来类型错误，异常待实现
			return false;
		}

		return true;
	}

	/**
	 * 校验往来类型
	 * 
	 * @param task
	 * @param partner
	 * @return boolean
	 */
	private boolean valiDealingsType(CusdealTask task, Partner partner) {
		if (PartnerType.Customer.equals(partner.getPartnerType())) {
			return DealingsType.CUS_TZYS.equals(task.getCusdealType()) ||

			DealingsType.CUS_XSCK.equals(task.getCusdealType()) ||

			DealingsType.CUS_THRK.equals(task.getCusdealType()) ||

			DealingsType.CUS_XSSK.equals(task.getCusdealType()) ||

			DealingsType.CUS_XSTK.equals(task.getCusdealType()) ||

			DealingsType.CUS_LSCK.equals(task.getCusdealType()) ||

			DealingsType.CUS_LSSK.equals(task.getCusdealType()) ||

			DealingsType.CUS_LSTH.equals(task.getCusdealType()) ||

			DealingsType.CUS_LSTK.equals(task.getCusdealType()) ||

			DealingsType.CUS_INIT.equals(task.getCusdealType());
		} else {
			return DealingsType.CUS_TZYS.equals(task.getCusdealType()) ||

			DealingsType.PRO_TZYF.equals(task.getCusdealType()) ||

			DealingsType.PRO_CGRK.equals(task.getCusdealType()) ||

			DealingsType.PRO_THCK.equals(task.getCusdealType()) ||

			DealingsType.PRO_CGFK.equals(task.getCusdealType()) ||

			DealingsType.PRO_CGTK.equals(task.getCusdealType()) ||

			DealingsType.PRO_LXCG.equals(task.getCusdealType()) ||

			DealingsType.PRO_INIT.equals(task.getCusdealType()) ||

			DealingsType.PRO_LCFK.equals(task.getCusdealType());
		}
	}

}
