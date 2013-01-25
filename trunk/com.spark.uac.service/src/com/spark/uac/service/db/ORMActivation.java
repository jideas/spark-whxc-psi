package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.uac.service.impl.ActivationInfoImpl;

public class ORMActivation extends ORMDeclarator<ActivationInfoImpl> {

	public final static String NAME = "ORM_UAC_Activation";

	protected QuRelationRefDeclare tableReference;

	public ORMActivation(TableActivation tbActivation) {
		super(NAME);
		tableReference = this.orm.newReference(tbActivation);
		this.orm.newColumn(tbActivation.f_id, "id");
		this.orm.newColumn(tbActivation.f_targetId, "sourceId");
		this.orm.newColumn(tbActivation.f_sourceId, "targetId");
		this.orm.newColumn(tbActivation.f_mobile, "mobileNumber");
		this.orm.newColumn(tbActivation.f_userId, "userId");
		this.orm.newColumn(tbActivation.f_userTitle, "title");
		this.orm.newColumn(tbActivation.f_activeCode, "activeCode");
		this.orm.newColumn(tbActivation.f_codeCreate, "activeCodeCreateTime");
		this.orm.setCondition(tableReference.expOf(tbActivation.f_mobile).xEq(
				this.orm.newArgument(tbActivation.f_mobile)));
	}

}
