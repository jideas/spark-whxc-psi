package com.spark.order.util.checking;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.order.util.checking.ServiceCheckImpl.UntreatedException;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Store;

/**
 * <p>ҵ��У��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-22
 */
public interface IServiceCheck{
	/**
	 * <p>���ʹ����쳣</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	@SuppressWarnings("serial")
	public class ErrorEnumException extends Throwable{
		public ErrorEnumException(){
			super("ҵ��У�����ʵ����ʱ�������쳣ö�ٴ���");
		}
	}
	/**
	 * <p>�����ڵ�ǰҳ��У��������ʵ��������չ...</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	@SuppressWarnings("serial")
	public class ServiceCheckInitException extends Throwable{
		public ServiceCheckInitException(){
			super("�����ڵ�ǰҳ��У��������ʵ��������չ...");
		}
	}
	/**
	 * <p>У���������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	public class CheckParam{
		private GUID id;
		private GUID id2;
		private double count;//����
		private double money;//���
		private final CheckEnum checkEnum;//�������
		
		/**
		 * ����Ԥ�Ʒ�������(ֻ��һ�����òֿ�У��)��
		 * @param checkEnum
		 */
		public CheckParam(CheckEnum checkEnum){
			this.checkEnum = checkEnum;
		}
		/**
		 * ��������ޡ�����������ޡ��Ƿ�����Ԥ�����У��
		 * @param goodsItemId
		 * @param storeId
		 * @param d
		 * @param checkEnum
		 * @throws ServiceCheckInitException
		 */
		public CheckParam(GUID goodsItemId, GUID storeId, double d, CheckEnum checkEnum) throws ServiceCheckInitException {
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case inventory_amount_upper:
				this.money = d;
				break;
			case inventory_count_upper:
				this.count = d;
				break;
			case purchase_warning_lack:
				this.count = d;
				break;
			default:
				throw new ServiceCheckInitException();
			}
			this.id = goodsItemId;
			this.id2 = storeId;
		}
		
		/**
		 * ��Ʒ�ɹ���������(0)
		 * @param d
		 * @param checkEnum
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(double d, CheckEnum checkEnum) throws ServiceCheckInitException{
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case goods_count_zero:
				this.count = d;
				break;
			default:
				throw new ServiceCheckInitException();
			}
		}
		
		/**
		 * ����У��
		 * @param id
		 * @param count
		 * @param price
		 * @param checkEnum
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(GUID id, double count, double price, CheckEnum checkEnum) throws ServiceCheckInitException{
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case promotion:
				this.money = price;
				this.count = count;
				break;
			default:
				throw new ServiceCheckInitException();
			}
			this.id = id;
		}
		
		/**
		 * ��Ʒͣ�ۡ��ɹ�ֱ����Ʒ�Ѳɹ����ֿ�ͣ��
		 * @param storeId
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(GUID id, CheckEnum checkEnum) throws ServiceCheckInitException {
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case goods_stop:
				this.id = id;
				break;
			case direct_goods:
				this.id = id;
				break;
			case store_stop:
				this.id = id;
				break;
			default:
				throw new ServiceCheckInitException();
			}
		}

		
		//===========get����=============
		/**
		 * @return the id
		 */
		public GUID getId() {
			return id;
		}
		
		/**
		 * 
		 * @return GUID
		 */
		public GUID getId2() {
			return id2;
		}
		
		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}
		
		/**
		 * @return the money
		 */
		public double getMoney() {
			return money;
		}
		
		/**
		 * @return the checkEnum
		 */
		public CheckEnum getCheckEnum() {
			return checkEnum;
		}
		
	}
	/**
	 * ���У������
	 * @return CheckType
	 */
	CheckEnum getCheckEnum();
	/**
	 * �Ƿ�����д���true
	 * @return boolean
	 * @throws UntreatedException 
	 */
	boolean isError() throws UntreatedException;
	/**
	 * ִ����֤�������Ƿ�����д���true
	 * @param param
	 * @return boolean
	 */
	boolean doError();
	/**
	 * ��ô��������޷���null
	 * @return Promotion
	 */
	Promotion2 getPromotion();
	/**
	 * ��ö����������ۡ������˻����ɹ����ɹ��˻������޷���null
	 * @return OrderInfo
	 */
	OrderInfo getOrderInfo();
	/**
	 * �����Ʒ��Ŀ�����޷���null
	 * @return GoodsItem
	 */
	GoodsItem getGoodsItem();
	/**
	 * ��òֿ�����޷���null
	 * @return Store
	 */
	Store getStore();
	/**
	 * �����Ʒ�����Ϣ���޷���null
	 * @return GoodsInventory
	 */
	Inventory getGoodsInventory();
	/**
	 * �����������
	 *  void
	 */
	void setCheckParam(CheckParam param);
	/**
	 * ��õ�ǰ����
	 * @return int
	 */
	int getIndex();
	/**
	 * ���뵱ǰ����
	 *  void
	 */
	void setIndex(int index);
}
