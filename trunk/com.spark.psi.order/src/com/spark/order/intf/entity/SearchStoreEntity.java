/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       Ī��        
 * ============================================================*/

package com.spark.order.intf.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ�ѳ������Ʒʵ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-11
 */

public class SearchStoreEntity {

	/**
	 * �ͻ���Ӧ��GUID
	 */
	private GUID cusproGuid;
	private String cusproName;
	/**
	 * �ѳ������Ʒʵ��LIST
	 */
	private List<SearchStoreGoods> goodsList;
	
	public GUID getCusproGuid() {
		return cusproGuid;
	}
	public void setCusproGuid(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}
	public List<SearchStoreGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<SearchStoreGoods> goodsList) {
		this.goodsList = goodsList;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	public String getCusproName() {
		return cusproName;
	}
}
