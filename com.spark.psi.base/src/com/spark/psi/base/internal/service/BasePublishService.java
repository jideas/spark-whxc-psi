package com.spark.psi.base.internal.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
*
*/
public class BasePublishService extends Service {

	private Calendar lastQueryTermsGenerateDate;

	private Map<String, QueryTerm> queryTerms = new LinkedHashMap<String, QueryTerm>();

	/**
	 * 
	 */
	protected BasePublishService() {
		super("基础模块公开服务");
	}

	/**
	 * 系统内置查询时期范围列表
	 */
	@Publish
	protected class QueryTermListQuery extends ResultListProvider<QueryTerm> {

		@Override
		protected void provide(Context context, List<QueryTerm> resultList)
				throws Throwable {
			Calendar now = Calendar.getInstance();
			now.setFirstDayOfWeek(Calendar.MONDAY);
			// 如果没有生成，或者上次生成的时间已经过期
			if (lastQueryTermsGenerateDate == null
					|| lastQueryTermsGenerateDate.get(Calendar.DAY_OF_YEAR) != now
							.get(Calendar.DAY_OF_YEAR)
					|| lastQueryTermsGenerateDate.get(Calendar.YEAR) != now
							.get(Calendar.YEAR)) {
				synchronized (this) {
					resultList.clear();

					//
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek(Calendar.MONDAY);
					long start = 0;
					long end = 0;

					// 本周
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					calendar.set(Calendar.WEEK_OF_YEAR,
							now.get(Calendar.WEEK_OF_YEAR));
					start = calendar.getTimeInMillis();
					calendar.add(Calendar.WEEK_OF_YEAR, 1);
					end = calendar.getTimeInMillis() - 1;
					addTerm(new QueryTerm(start, end, QueryTerm.ID_WEEK));

					// 上周
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					calendar.set(Calendar.WEEK_OF_YEAR,
							now.get(Calendar.WEEK_OF_YEAR));
					end = calendar.getTimeInMillis() - 1;
					calendar.add(Calendar.WEEK_OF_YEAR, -1);
					start = calendar.getTimeInMillis();
					addTerm(new QueryTerm(start, end, QueryTerm.ID_LAST_WEEK));

					// 本月
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
					start = calendar.getTimeInMillis();
					calendar.add(Calendar.MONTH, 1);
					end = calendar.getTimeInMillis() - 1;
					addTerm(new QueryTerm(start, end, QueryTerm.ID_MONTH));

					// 上月
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
					end = calendar.getTimeInMillis() - 1;
					calendar.add(Calendar.MONTH, -1);
					start = calendar.getTimeInMillis();
					addTerm(new QueryTerm(start, end, QueryTerm.ID_LAST_MONTH));

					// // 本季
					calendar.clear();
					int month = now.get(Calendar.MONTH);
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					if (month < 3) {
						calendar.set(Calendar.MONTH, 0);
						start = calendar.getTimeInMillis();
						calendar.set(Calendar.MONTH, 3);
						end = calendar.getTimeInMillis() - 1;
					} else if (month < 6) {
						calendar.set(Calendar.MONTH, 3);
						start = calendar.getTimeInMillis();
						calendar.set(Calendar.MONTH, 6);
						end = calendar.getTimeInMillis() - 1;
					} else if (month < 9) {
						calendar.set(Calendar.MONTH, 6);
						start = calendar.getTimeInMillis();
						calendar.set(Calendar.MONTH, 9);
						end = calendar.getTimeInMillis() - 1;
					} else {
						calendar.set(Calendar.MONTH, 9);
						start = calendar.getTimeInMillis();
						calendar.set(Calendar.YEAR, now.get(Calendar.YEAR) + 1);
						calendar.set(Calendar.MONTH, 0);
						end = calendar.getTimeInMillis() - 1;
					}
					addTerm(new QueryTerm(start, end, QueryTerm.ID_QUARTER));

					// 本年
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
					start = calendar.getTimeInMillis();
					calendar.add(Calendar.YEAR, 1);
					end = calendar.getTimeInMillis() - 1;
					addTerm(new QueryTerm(start, end, QueryTerm.ID_YEAR));

					// 上年
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR) - 1);
					start = calendar.getTimeInMillis();
					calendar.add(Calendar.YEAR, 1);
					end = calendar.getTimeInMillis() - 1;
					addTerm(new QueryTerm(start, end, String.valueOf(now
							.get(Calendar.YEAR) - 1)));

					// 前年
					calendar.clear();
					calendar.set(Calendar.YEAR, now.get(Calendar.YEAR) - 2);
					start = calendar.getTimeInMillis();
					calendar.add(Calendar.YEAR, 1);
					end = calendar.getTimeInMillis() - 1;
					addTerm(new QueryTerm(start, end, String.valueOf(now
							.get(Calendar.YEAR) - 2)));

					//
					lastQueryTermsGenerateDate = now;
				}
			}
			resultList.addAll(queryTerms.values());
		}

		private void addTerm(QueryTerm queryTerm) {
			queryTerms.put(queryTerm.getName(), queryTerm);
		}
	}

	@Publish
	protected class QueryTermQuery extends
			OneKeyResultProvider<QueryTerm, String> {

		@Override
		protected QueryTerm provide(Context context, String key)
				throws Throwable {
			return queryTerms.get(key);
		}
	}

	@Publish
	protected class LoginInfoProvider extends ResultProvider<LoginInfo> {

		@Override
		protected LoginInfo provide(Context context) throws Throwable {
			Login login = context.find(Login.class);
			LoginInfoImpl loginInfo = new LoginInfoImpl(context.find(
					EmployeeInfo.class, login.getEmployeeId()), login.getAcls());
			loginInfo.setTenantId(context.find(Login.class).getTenantId());
			return loginInfo;
		}

		private final class LoginInfoImpl implements LoginInfo {

			private EmployeeInfo employeeInfo;

			private Set<Auth> acls;
			
			private GUID tenantId;

			private LoginInfoImpl(EmployeeInfo employeeInfo, Auth[] acls) {
				super();
				this.employeeInfo = employeeInfo;
				this.acls = new HashSet<Auth>();
				for (Auth auth : acls) {
					this.acls.add(auth);
				}
			}

			/**
			 * 
			 */
			public boolean hasAuth(Auth... auths) {
				for (Auth auth : auths) {
					if(acls.contains(auth))return true;
//					if (acls == null || !acls.contains(auth)) {
//						return false;
//					}
				}
				return false;
			}
			

			public EmployeeInfo getEmployeeInfo() {
				return employeeInfo;
			}

			public GUID getTenantId(){
            	return tenantId;
            }

			public void setTenantId(GUID tenantId){
            	this.tenantId = tenantId;
            }

			
			
		}
	}

	public final static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		System.out.println(now.get(Calendar.MONTH));
	}
}
