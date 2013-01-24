package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * <p>���ۻ�ó�����Ʒ�۸�</p>
 * <result>Double</result>
 *
 *����һ�� 
1���ܾ�����ƷA�����������۸�Ϊ200 
     ���۾������ƷAҲ�����������۸���180 
2���ܾ���������ƷA�����������Ʒ�������Ʒ�ļ۸�Ϊ200 

�������� 
1��ֻ�����۾������ƷB���д����������۸�200 
2���ܾ���������ƷB�����������Ʒ�������Ʒ�ļ۸�Ϊ"���ۼ۸�" 

��������
1���ܾ������ƷA�����������۸�Ϊ200�� 
   ���۾������ƷAҲ�����������۸���180 
2������Ա�����۸���Ʒ�����������Ʒ������ʾ�ļ۸�Ӧ��Ϊ180 



����Ʒ�������κδ����۸�ʱ����ʾ���ۼ۸�
����Ʒ������һ�������۸�ʱ����ʾ�ô����۸�
����Ʒ�Ĺ�˾�����۸��������ϼ����ž������趨�Ĵ����۸���������2��ʱ���������������ŵ���֯��������ʾ������ĩһ�����ž������趨�Ĵ����۸�
�ܾ����ڴ������۶���ʱ��������Ʒ���ڹ�˾�����۸�����ʾ�ô����۸�������Ʒ�����ڹ�˾�����۸�ʱ������ʾ�����ۼ۸�
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-7-9
 */
public class GetPromotionPriceByRetailKey{
	private final GUID goodsItemId;

	/**
	 * @param goodsItemId
	 */
	public GetPromotionPriceByRetailKey(GUID goodsItemId) {
		super();
		this.goodsItemId = goodsItemId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}
	
}
