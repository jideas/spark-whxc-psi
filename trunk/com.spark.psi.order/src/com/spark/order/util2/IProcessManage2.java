package com.spark.order.util2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.EntityFather;

/**
 * <p>���̹���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public interface IProcessManage2<status> {
	/**
	 * ��һ�����̣�����һ�����̷���null
	 * @param status������ǰ״̬
	 * @return status
	 */
	status next(status status);
	/**
	 * ��һ�����̣�����һ�����̷���null
	 * @param status������ǰ״̬
	 * @return status
	 */
	status prov(status status);
	/**
	 * ��ö����仯��Ĳ��ţ�����ޱ仯����null
	 * @return GUID
	 */
	GUID getOrderDepartment();
	/**
	 * ��ö����仯�����˲��ţ�����ޱ仯����null
	 * @return GUID
	 */
	GUID getOrderExamDept();
	/**
	 * �ⲿ���ж��������붩�����������²�ѯ
	 * @param order void
	 */
	void setEntity(EntityFather entity);
}
