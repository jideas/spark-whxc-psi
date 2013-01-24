package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Notice extends ORMDeclarator<com.spark.psi.base.internal.entity.NoticeImpl> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_cancelTime;
	public final QueryColumnDefine c_createGuid;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_createTime;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_deptNameStr;
	public final QueryColumnDefine c_isTop;
	public final QueryColumnDefine c_noticeContent;
	public final QueryColumnDefine c_noticeStatus;
	public final QueryColumnDefine c_noticeTitle;
	public final QueryColumnDefine c_noticeTitlePY;
	public final QueryColumnDefine c_publishTime;
	public final QueryColumnDefine c_tenantsGuid;

	public Orm_Notice() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_cancelTime = this.orm.getColumns().get(1);
		this.c_createGuid = this.orm.getColumns().get(2);
		this.c_createPerson = this.orm.getColumns().get(3);
		this.c_createTime = this.orm.getColumns().get(4);
		this.c_deptGuid = this.orm.getColumns().get(5);
		this.c_deptNameStr = this.orm.getColumns().get(6);
		this.c_isTop = this.orm.getColumns().get(7);
		this.c_noticeContent = this.orm.getColumns().get(8);
		this.c_noticeStatus = this.orm.getColumns().get(9);
		this.c_noticeTitle = this.orm.getColumns().get(10);
		this.c_noticeTitlePY = this.orm.getColumns().get(11);
		this.c_publishTime = this.orm.getColumns().get(12);
		this.c_tenantsGuid = this.orm.getColumns().get(13);
	}
}
