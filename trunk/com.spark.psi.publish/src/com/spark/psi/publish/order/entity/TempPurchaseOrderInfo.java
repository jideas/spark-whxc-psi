package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;

/**
 * 
 * <p>�����ɵ���ʱ�ɹ�����</p>
 * 
 * ���ɲɹ��������� List<TempPurchaseOrderInfo> + GetTempPurchaseOrderInfoKey
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2012-2-22
 */
public interface TempPurchaseOrderInfo{

//	private SupplierInfo supplierInfo; //��Ӧ��
//
//	private StoreInfo storeInfo; //�ֿ�
//
//	private TempPurchaseGoodsItem[] purchaseGoodsItems;

	public SupplierInfo getSupplierInfo();

	public StoreInfo getStoreInfo();

	public TempPurchaseGoodsItem[] getPurchaseGoodsItems();

	public static final class TempPurchaseGoodsItem{

		private GUID goodsId; //��Ʒ��Ŀid

		private String goodsName; //��Ʒ����

		private double price = -1; //����	NUM(17,2)

		private double num;//	����	NUM(10,2)

		public GUID getGoodsId(){
			return goodsId;
		}

		public void setGoodsId(GUID goodsId){
			this.goodsId = goodsId;
		}

		public String getGoodsName(){
			return goodsName;
		}

		public void setGoodsName(String goodsName){
			this.goodsName = goodsName;
		}

		public double getPrice(){
			return price;
		}

		public void setPrice(double price){
			this.price = price;
		}

		public double getNum(){
			return num;
		}

		public void setNum(double num){
			this.num = num;
		}

	}

}
