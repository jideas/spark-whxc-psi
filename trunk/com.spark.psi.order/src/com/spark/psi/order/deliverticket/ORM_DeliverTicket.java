package com.spark.psi.order.deliverticket;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_DeliverTicket extends ORMDeclarator<com.spark.deliverticket.intf.entity.DeliverTicketEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_disAmount;
	public final QueryColumnDefine c_memberId;
	public final QueryColumnDefine c_memberRealName;
	public final QueryColumnDefine c_mobilePhone;
	public final QueryColumnDefine c_onlineOrderId;
	public final QueryColumnDefine c_onlineOrderNo;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_sheetNo;
	public final QueryColumnDefine c_stationId;
	public final QueryColumnDefine c_stationName;
	public final QueryColumnDefine c_totalAmount;
	public final QueryColumnDefine c_deliverTicketType;

	public ORM_DeliverTicket() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_address = this.orm.getColumns().get(1);
		this.c_createDate = this.orm.getColumns().get(2);
		this.c_creator = this.orm.getColumns().get(3);
		this.c_creatorId = this.orm.getColumns().get(4);
		this.c_disAmount = this.orm.getColumns().get(5);
		this.c_memberId = this.orm.getColumns().get(6);
		this.c_memberRealName = this.orm.getColumns().get(7);
		this.c_mobilePhone = this.orm.getColumns().get(8);
		this.c_onlineOrderId = this.orm.getColumns().get(9);
		this.c_onlineOrderNo = this.orm.getColumns().get(10);
		this.c_remark = this.orm.getColumns().get(11);
		this.c_sheetNo = this.orm.getColumns().get(12);
		this.c_stationId = this.orm.getColumns().get(13);
		this.c_stationName = this.orm.getColumns().get(14);
		this.c_totalAmount = this.orm.getColumns().get(15);
		this.c_deliverTicketType = this.orm.getColumns().get(16);
	}
}
