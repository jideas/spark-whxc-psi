package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

/**
 * 
 * <p>商品交易记录</p>
 *


 *
 
 * @version 2012-5-3
 */
public class TableGoodsTraderLog  extends TableDeclarator {
	
	public final static String Name = "SA_GOODS_TRADERLOG";
	
	public final TableFieldDefine f_id;
	
	public final TableFieldDefine f_goodsItemId;
	
	public final TableFieldDefine f_partnerId;
	
	public final TableFieldDefine f_data;
	
	public final TableFieldDefine f_type;
	
	public final TableFieldDefine f_goodsId;
	
	
	public TableGoodsTraderLog(){
		super(Name);
		this.f_id = table.f_RECID();
		this.f_data = table.newField("data", TypeFactory.TEXT);
		this.f_goodsItemId = table.newPrimaryField("goodsItemId", TypeFactory.GUID);
		this.f_partnerId = table.newPrimaryField("partnerId", TypeFactory.GUID);
		this.f_type = table.newField("type",TypeFactory.VARCHAR8);
		this.f_goodsId = table.newField("goodsId", TypeFactory.GUID);
		this.table.newIndex("GOODS_TRADERLOG_INDEX", f_goodsId,f_type);
		this.table.newIndex("GOODS_TRADERLOG_INDEX2",f_goodsItemId,f_partnerId);
    }
}
