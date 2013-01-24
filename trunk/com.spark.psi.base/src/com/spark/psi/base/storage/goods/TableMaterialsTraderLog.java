package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

/**
 * 
 * <p>材料交易记录</p>
 *
 */
public class TableMaterialsTraderLog  extends TableDeclarator {
	
	public final static String Name = "SA_Materials_TRADERLOG";
	
	public final TableFieldDefine f_id;
	
	public final TableFieldDefine f_materialsItemId;
	
	public final TableFieldDefine f_partnerId;
	
	public final TableFieldDefine f_data;
	
	public final TableFieldDefine f_type;
	
	public final TableFieldDefine f_materialsId;
	
	
	public TableMaterialsTraderLog(){
		super(Name);
		this.f_id = table.f_RECID();
		this.f_data = table.newField("data", TypeFactory.TEXT);
		this.f_materialsItemId = table.newPrimaryField("materialsItemId", TypeFactory.GUID);
		this.f_partnerId = table.newPrimaryField("partnerId", TypeFactory.GUID);
		this.f_type = table.newField("type",TypeFactory.VARCHAR8);
		this.f_materialsId = table.newField("MaterialsId", TypeFactory.GUID);
		this.table.newIndex("Materials_TRADERLOG_INDEX", f_materialsId,f_type);
		this.table.newIndex("Materials_TRADERLOG_INDEX2",f_materialsItemId,f_partnerId);
    }
}
