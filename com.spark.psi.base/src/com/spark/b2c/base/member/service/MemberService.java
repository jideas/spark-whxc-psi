package com.spark.b2c.base.member.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.base.member.Orm_MemberAccount;
import com.spark.b2c.base.member.entity.MemberAccountInfoImpl;
import com.spark.b2c.publish.base.member.entity.MemberAccountInfo;
import com.spark.b2c.publish.base.member.entity.MemberInfo;
import com.spark.b2c.publish.base.member.key.GetAllMembersKey;
import com.spark.b2c.publish.base.member.key.GetMembersByBirthdayKey;
import com.spark.b2c.publish.base.member.task.LogoffMemberTask;
import com.spark.b2c.publish.base.member.task.MemberInfoTask;
import com.spark.common.utils.dnasql.QuerySqlBuilder;

public class MemberService extends Service {
	
	private Orm_MemberAccount orm_memeberAccount;

	protected MemberService(Orm_MemberAccount orm_memeberAccount) {
		super("com.spark.b2c.base.member.service.MemberService");
		this.orm_memeberAccount = orm_memeberAccount;
	}

	@Publish
	protected class MembersListProvider extends
			OneKeyResultListProvider<MemberInfo, GetAllMembersKey> {

		@Override
		protected void provide(Context context, GetAllMembersKey key,
				List<MemberInfo> resultList) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.setFrom("");

		}

	}

	@Publish
	protected class MemberProvider extends
			OneKeyResultProvider<MemberInfo, GUID> {

		@Override
		protected MemberInfo provide(Context context, GUID key)
				throws Throwable {
			return null;
		}

	}

	@Publish
	protected class MembersListByBirthdayProvider extends
			OneKeyResultListProvider<MemberInfo, GetMembersByBirthdayKey> {

		@Override
		protected void provide(Context context, GetMembersByBirthdayKey key,
				List<MemberInfo> resultList) throws Throwable {

		}
	}

	@Publish
	protected class SaveOrUpdateMemberHandle extends
			SimpleTaskMethodHandler<MemberInfoTask> {

		@Override
		protected void handle(Context context, MemberInfoTask task)
				throws Throwable {
		}

	}

	@Publish
	protected class LogoffMemberHandle extends
			SimpleTaskMethodHandler<LogoffMemberTask> {

		@Override
		protected void handle(Context context, LogoffMemberTask task)
				throws Throwable {
		}

	}
	
	@Publish
	protected class GetMemberAccountInfo extends OneKeyResultProvider<MemberAccountInfo, GUID> {

		@Override
		protected MemberAccountInfo provide(Context context, GUID key)
				throws Throwable {
			ORMAccessor<MemberAccountInfoImpl> accessor = context.newORMAccessor(orm_memeberAccount);
			MemberAccountInfo accountInfo = null;
			try {
				accountInfo = accessor.findByRECID(key);
			} finally {
				accessor.unuse();
			}
			return accountInfo;
		}
		
	}
}
