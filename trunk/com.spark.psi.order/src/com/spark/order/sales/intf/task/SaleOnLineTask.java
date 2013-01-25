package com.spark.order.sales.intf.task;

import java.util.List;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;

/**
 * <p>�����������۶�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-30
 */
@StructClass
public class SaleOnLineTask extends SimpleTask{
	@StructField
	public SaleOrderInfo entity;
	@StructField
	public List<SaleOrderItem> dets;
}
