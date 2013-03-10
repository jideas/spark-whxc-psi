package com.spark.psi.order.onlineorder;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_OnlineOrder extends ORMDeclarator<com.spark.onlineorder.intf.entity.OnlineOrderEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_arrivedConfirm;
	public final QueryColumnDefine c_arrivedConfirmDate;
	public final QueryColumnDefine c_arrivedConfirmId;
	public final QueryColumnDefine c_billsNo;
	public final QueryColumnDefine c_consignedDate;
	public final QueryColumnDefine c_consignee;
	public final QueryColumnDefine c_consigneeTel;
	public final QueryColumnDefine c_consignor;
	public final QueryColumnDefine c_consignorId;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_deliverDate;
	public final QueryColumnDefine c_deliverPerson;
	public final QueryColumnDefine c_deliverPersonId;
	public final QueryColumnDefine c_deliveryeDate;
	public final QueryColumnDefine c_disAmount;
	public final QueryColumnDefine c_memberId;
	public final QueryColumnDefine c_noVerificationReason;
	public final QueryColumnDefine c_realName;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_stationId;
	public final QueryColumnDefine c_stationName;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_totalAmount;
	public final QueryColumnDefine c_type;
	public final QueryColumnDefine c_verificationCode;
	public final QueryColumnDefine c_returnFlag;
	public final QueryColumnDefine c_isToDoor;
	public final QueryColumnDefine c_vantages;
	public final QueryColumnDefine c_deliveryCost;
	public final QueryColumnDefine c_vantagesCost;
	public final QueryColumnDefine c_payType;

	public ORM_OnlineOrder() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_address = this.orm.getColumns().get(1);
		this.c_arrivedConfirm = this.orm.getColumns().get(2);
		this.c_arrivedConfirmDate = this.orm.getColumns().get(3);
		this.c_arrivedConfirmId = this.orm.getColumns().get(4);
		this.c_billsNo = this.orm.getColumns().get(5);
		this.c_consignedDate = this.orm.getColumns().get(6);
		this.c_consignee = this.orm.getColumns().get(7);
		this.c_consigneeTel = this.orm.getColumns().get(8);
		this.c_consignor = this.orm.getColumns().get(9);
		this.c_consignorId = this.orm.getColumns().get(10);
		this.c_createDate = this.orm.getColumns().get(11);
		this.c_deliverDate = this.orm.getColumns().get(12);
		this.c_deliverPerson = this.orm.getColumns().get(13);
		this.c_deliverPersonId = this.orm.getColumns().get(14);
		this.c_deliveryeDate = this.orm.getColumns().get(15);
		this.c_disAmount = this.orm.getColumns().get(16);
		this.c_memberId = this.orm.getColumns().get(17);
		this.c_noVerificationReason = this.orm.getColumns().get(18);
		this.c_realName = this.orm.getColumns().get(19);
		this.c_remark = this.orm.getColumns().get(20);
		this.c_stationId = this.orm.getColumns().get(21);
		this.c_stationName = this.orm.getColumns().get(22);
		this.c_status = this.orm.getColumns().get(23);
		this.c_totalAmount = this.orm.getColumns().get(24);
		this.c_type = this.orm.getColumns().get(25);
		this.c_verificationCode = this.orm.getColumns().get(26);
		this.c_returnFlag = this.orm.getColumns().get(27);
		this.c_isToDoor = this.orm.getColumns().get(28);
		this.c_vantages = this.orm.getColumns().get(29);
		this.c_deliveryCost = this.orm.getColumns().get(30);
		this.c_vantagesCost = this.orm.getColumns().get(31);
		this.c_payType = this.orm.getColumns().get(32);
	}
}
