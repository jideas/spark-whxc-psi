package com.spark.order.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.key.GetOrderListBusKey;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.sales.intf.entity.SaleOrder;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;

/**
 * <p>����ģ���ڲ�����Service</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public class OrderModuleService extends Service{

	protected OrderModuleService() {
		super("com.spark.order.internal.service.OrderModuleService");
	}

	/**
	 * <p>�����ۡ�����������ѯ������Ϣ(ҵ�������)</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-21
	 */
	@Publish
	class GetSalesOrderListBusProvider extends OneKeyResultListProvider<SaleOrder, GetOrderListBusKey>{

		@Override
		protected void provide(Context context, GetOrderListBusKey key,
				List<SaleOrder> resultList) throws Throwable {
			throw new Throwable("δʵ��");
		}
	}
	
	/**
	 * <p>���ɹ�������������ѯ������Ϣ(ҵ�������)</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-21
	 */
	@Publish
	class GetPurchaseOrderListBusProvider extends OneKeyResultListProvider<PurchaseOrder, GetOrderListBusKey>{

		@Override
		protected void provide(Context context, GetOrderListBusKey key,
				List<PurchaseOrder> resultList) throws Throwable {
			throw new Throwable("δʵ��");
		}
	}
	/**
	 * <p>�ɹ���������������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-29
	 */
	@Publish
	class GetPurchaseOrderGoodsCountByGoodsIdProvider extends OneKeyResultProvider<Double, GetPurchaseOrderGoodsCountByGoodsIdKey>{

		@Override
		protected Double provide(Context context,
				GetPurchaseOrderGoodsCountByGoodsIdKey key) throws Throwable {
			IDnaSql sql = new GetPurchaseOrderGoodsCountByGoodsIdSql(context, key);
			RecordSet rs = sql.executeQuery();
			double count = 0;
			if(rs.next()){
				count = rs.getFields().get(0).getDouble();
			}
			return count;
		}
	}
	

	// /**
	// * <p>�ͻ���Ӧ�̣����󶩵��������ݡ��������״��������׽�����������ڣ������������ڣ���ͳ����Ч������</p>
	// *
	// * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	//	
	// *
	// * @author modi
	// * @version 2012-3-30
	// */
	// @Publish
	// class GetPartnerTradeSummaryByPartnerIdProvider extends
	// OneKeyResultProvider<PartnerTradeSummary,
	// GetPartnerTradeSummaryByPartnerIdKey>{
	//
	// @Override
	// protected PartnerTradeSummary provide(Context context,
	// GetPartnerTradeSummaryByPartnerIdKey key) throws Throwable {
	// GetPartnerTradeSummaryByPartnerIdSql sql = new
	// GetPartnerTradeSummaryByPartnerIdSql(context, key);
	// DBCommand db = sql.getDB(null);
	// //���ύ������ˡ����˻�״̬
	// // return
	// "@tenants guid, @status1 string, @status2 string, @status3 string";
	// db.setArgumentValues(BillsConstant.getTenantsGuid(context),
	// StatusEnum.Submit.getKey(), StatusEnum.Approve.getKey(),
	// StatusEnum.Return.getKey()
	// ,key.getPartnerId());
	// RecordSet rs = db.executeQuery();
	// return sql.getPartnerTradeSummary(rs);
	// }
	// }
}
