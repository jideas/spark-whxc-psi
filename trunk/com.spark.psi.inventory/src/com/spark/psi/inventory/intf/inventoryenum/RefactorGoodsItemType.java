/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.inventoryenum
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-23       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>��Ʒ��װ��ϸ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-23
 */

public enum RefactorGoodsItemType {

	Source("1","Դ"),
	Destination("2","Ŀ��");
	
	private String code;
	private String name;
	
	private RefactorGoodsItemType(String code,String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public RefactorGoodsItemType getEnum(String code)
	{
		RefactorGoodsItemType typeEnum = null;
		if(RefactorGoodsItemType.Source.getCode().equals(code))
		{
			typeEnum = RefactorGoodsItemType.Source;
		}
		else if(RefactorGoodsItemType.Destination.getCode().equals(code))
		{
			typeEnum = RefactorGoodsItemType.Destination;
		}
		return typeEnum;
	}
}
