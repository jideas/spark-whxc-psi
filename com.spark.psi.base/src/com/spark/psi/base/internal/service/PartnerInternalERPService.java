package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.BeanCopy;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.Partner;
import com.spark.psi.base.internal.entity.PartnerImpl;
import com.spark.psi.base.publicimpl.CustomerInfoImpl;
import com.spark.psi.base.publicimpl.PartnerInfoImpl;
import com.spark.psi.base.publicimpl.SupplierInfoImpl;
import com.spark.psi.base.task.resource.UpdatePartnerResourceTask;
import com.spark.psi.base.utils.QueryTableColumnUtil;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

public class PartnerInternalERPService extends ResourceService<Partner, PartnerImpl, PartnerImpl> {

	protected PartnerInternalERPService() {
		super("com.spark.psi.base.internal.service.PartnerInternalERPService");
	}

	@Override
	protected void init(Context context) {
		context.getList(Partner.class);
	}

	@Override
	protected void initResources(Context context, ResourceInserter<Partner, PartnerImpl, PartnerImpl> initializer) {
		List<PartnerImpl> list = GetAllPartnerList(context);
		for (PartnerImpl partnerImpl : list) {
			initializer.putResource(partnerImpl);
		}
		PartnerImpl retailCustomer = new PartnerImpl(null);
		retailCustomer.setId(Partner.RetailCustomerId);
		retailCustomer.setName(Partner.RetailCustomerName);
		retailCustomer.setShortName(Partner.RetailCustomerName);
		retailCustomer.setPartnerType(PartnerType.Customer);
		initializer.putResource(retailCustomer);
		createPartner(context, retailCustomer, true);
		System.currentTimeMillis();
		PartnerImpl retailPurchaseSupplier = new PartnerImpl(null);
		retailPurchaseSupplier.setId(Partner.RetailPurchaseSupplierId);
		retailPurchaseSupplier.setName(Partner.RetailPurchaseSupplierName);
		retailPurchaseSupplier.setShortName(Partner.RetailPurchaseSupplierName);
		retailPurchaseSupplier.setPartnerType(PartnerType.Supplier);
		initializer.putResource(retailPurchaseSupplier);
		createPartner(context, retailPurchaseSupplier, false);
		list.clear();
		list = null;
	}

	/**
	 * 保存零售客户及零星采购
	 */
	private void createPartner(Context context, PartnerImpl r, boolean isCustomer) {
		// if (isCustomer) {
		// UpdateCustomerTask task = new UpdateCustomerTask();
		// task.setId(r.getId());
		// task.setName(r.getName());
		// task.setShortName(r.getShortName());
		// task.setAddress(r.getAddress());
		// context.handle(task, UpdatePartnerTask.Method.CREATE);
		// } else {
		// UpdateSupplierTask task = new UpdateSupplierTask();
		// task.setId(r.getId());
		// task.setName(r.getName());
		// task.setShortName(r.getShortName());
		// task.setAddress(r.getAddress());
		// context.handle(task, UpdatePartnerTask.Method.CREATE);
		// }
		// System.out.println("com.spark.psi.base.internal.service.PartnerInternalERPService.createPartner并未向数据库中插入零售客户和零星采购！");
	}

	/**
	 * 通过RECID查询一条记录
	 */
	@Publish
	protected class GetPartnerResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(PartnerImpl keysHolder) {
			return keysHolder.getId();
		}

	}

	/**
	 * 得到所有合作伙伴
	 */
	private List<PartnerImpl> GetAllPartnerList(Context context) {
		List<PartnerImpl> result = new ArrayList<PartnerImpl>();
		QuerySqlBuilder qbc = new QuerySqlBuilder(context);
		qbc.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
		QueryTableColumnUtil.setCustomerInfoColumn(qbc);
		RecordSet rsc = qbc.getRecord();
		while (rsc.next()) {
			CustomerInfoImpl info = QueryTableColumnUtil.getCustomerInfo(context, rsc);
			info.setPartnerType(PartnerType.Customer);
			try {
				result.add(info.copyToPartner());
			} catch (Throwable e) {
				System.err.println("初始化客户失败 id:" + rsc.getFields().get(0).getGUID());
				e.printStackTrace();
				continue;
			}
		}
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
		QueryTableColumnUtil.setSupplierInfoColumn(qb);
		RecordSet rs = qb.getRecord();
		while (rs.next()) {
			SupplierInfoImpl info = QueryTableColumnUtil.getSupplierInfo(context, rs);
			info.setPartnerType(PartnerType.Supplier);
			try {
				result.add(info.copyToPartner());
			} catch (Throwable e) {
				System.err.println("初始化供应商失败 id:" + rs.getFields().get(0).getGUID());
				e.printStackTrace();
				continue;
			}
		}
		return result;
	}

	/**
	 * 得到具体合作伙伴
	 */
	PartnerImpl getPartnerById(Context context, GUID id) {
		PartnerImpl impl = null;
		CustomerInfoImpl info = (CustomerInfoImpl) context.find(CustomerInfo.class, id);
		if (info == null) {
			SupplierInfoImpl ii = (SupplierInfoImpl) context.find(SupplierInfo.class, id);
			ii.setPartnerType(PartnerType.Supplier);
			impl = ii.copyToPartner();
		} else {
			info.setPartnerType(PartnerType.Customer);
			impl = info.copyToPartner();
		}
		return impl;
	}

	@Publish
	protected final class PutPartnerResourceHandler extends
			TaskMethodHandler<UpdatePartnerResourceTask, UpdatePartnerResourceTask.Method> {

		protected PutPartnerResourceHandler() {
			super(UpdatePartnerResourceTask.Method.Put);
		}

		@Override
		protected void handle(ResourceContext<Partner, PartnerImpl, PartnerImpl> context, UpdatePartnerResourceTask task)
				throws Throwable {
			PartnerImpl partner = getPartnerById(context, task.id);
			context.putResource(partner);
		}
	}

	@Publish
	protected final class ModifyPartnerResourceHandler extends
			TaskMethodHandler<UpdatePartnerResourceTask, UpdatePartnerResourceTask.Method> {

		protected ModifyPartnerResourceHandler() {
			super(UpdatePartnerResourceTask.Method.Modify);
		}

		@Override
		protected void handle(ResourceContext<Partner, PartnerImpl, PartnerImpl> context, UpdatePartnerResourceTask task)
				throws Throwable {
			PartnerImpl old = context.modifyResource(task.id);
			PartnerImpl _new = getPartnerById(context, task.id);
			BeanCopy.copy(_new, old);
			context.postModifiedResource(old);
		}
	}

	@Publish
	protected final class RemovePartnerResourceHandler extends
			TaskMethodHandler<UpdatePartnerResourceTask, UpdatePartnerResourceTask.Method> {

		protected RemovePartnerResourceHandler() {
			super(UpdatePartnerResourceTask.Method.Remove);
		}

		@Override
		protected void handle(ResourceContext<Partner, PartnerImpl, PartnerImpl> context, UpdatePartnerResourceTask task)
				throws Throwable {
			context.removeResource(task.id);
		}
	}

	@Publish
	protected class PartnerInfoProvider extends OneKeyResultProvider<PartnerInfo, GUID> {

		@Override
		protected PartnerInfo provide(ResourceContext<Partner, PartnerImpl, PartnerImpl> context, GUID key)
				throws Throwable {
			Partner p = null;
			if (Partner.RetailCustomerId.equals(key)) {
				PartnerImpl retailCustomer = new PartnerImpl(null);
				retailCustomer.setId(Partner.RetailCustomerId);
				retailCustomer.setName(Partner.RetailCustomerName);
				retailCustomer.setShortName(Partner.RetailCustomerName);
				retailCustomer.setPartnerType(PartnerType.Customer);
				p = retailCustomer;
			} else if (Partner.RetailPurchaseSupplierId.equals(key)) {
				PartnerImpl retailPurchaseSupplier = new PartnerImpl(null);
				retailPurchaseSupplier.setId(Partner.RetailPurchaseSupplierId);
				retailPurchaseSupplier.setName(Partner.RetailPurchaseSupplierName);
				retailPurchaseSupplier.setShortName(Partner.RetailPurchaseSupplierName);
				retailPurchaseSupplier.setPartnerType(PartnerType.Supplier);
				p = retailPurchaseSupplier;
			} else {
				p = context.find(Partner.class, key);
			}
			PartnerInfoImpl info = new PartnerInfoImpl();
			info.setId(p.getId());
			info.setName(p.getName());
			info.setPartnerType(p.getPartnerType());
			info.setShortName(p.getShortName());
			info.setAccountPeriod(p.getAccountPeriod());
			info.setAccountRemind(p.getAccountRemind());
			info.setAddress(p.getAddress());
			info.setCreditAmount(p.getCreditAmount());
			info.setBusinessPerson(p.getBusinessPerson());
			return info;
		}

	}
}
