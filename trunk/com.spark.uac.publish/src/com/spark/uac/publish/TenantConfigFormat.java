package com.spark.uac.publish;

/**
 * 
 * <p>租户配置文件格式常量</p>
 *
 *
 *	<Tenant tenantsId="" tenantsName="" tenantsCode="" bossMoblile="" bossName="">
 *	 *TODO:遍历services list
 *	<Services>
 *		<Service serverHostId=\"2\"   productSerial=""  productCode="" userNum="" serverStartDate="" serverEndDate="" SysName="">
 *		
 *			<ParamConfig directProviderFlag="">
 *				 *TODO:遍历 BillNoRules list
 *				<BillNoRules>
 *					<BillNoRule billCode="" prefix="" scale="" firstSerial="" yearFlag="" monthFlag="" dayFlag=""></BillNoRule>
 *				</BillNoRules>
 *			</ParamConfig>
 *		</Service>
 *	</Services>
 *</Tenant>	


 *
 
 * @version 2012-6-8
 */
public final class TenantConfigFormat{
	
	public static final String Encoding = "utf-8";
	
	public static final String Root = "tenant";
	
	/*
	 * Attribute 属性
	 */
	public final static class A {
		public static final String TenantId = "id";
		public static final String TenantName = "tenant-name";
		public static final String FishNumber = "fish-number";
		public static final String BossMobile = "boss-mobile";
		public static final String BossName = "boss-name";	
		public static final String TenantShortName = "Tenant-ShortName";
	}
	/*
	 * Services 子元素
	 */
	public final static class Services {
			
			public static final String Name = "services";
			
			public final static class Service {
				
				public static final String Name = "service";
				
				public final static class A {
					public static final String ServiceHostId = "Service-host-id";
					public static final String ProductSerial = "product-serial";
					public static final String ProductCode = "product-code";
					public static final String UserCount = "user-count";
					public static final String ServerStartDate = "service-start-date";
					public static final String ServerEndDate = "service-end-date";
					public static final String SysName = "sys-name";
				}
				
				public final static class ParamConfig {
					public static final String Name = "param-config";
					public static class A {
						public static final String DirectProviderFlag = "direct-provider-flag";
					}
					public final static class BillNoRules {
						public static final String Name = "bill-no-rules";
						public static class BillNoRule {
							public static final String Name = "bill-no-rule";
							public static class A {
								public static final String BillCode = "bill-code";
								public static final String Prefix = "prefix";
								public static final String Scale = "scale";
								public static final String FirstSerial = "first-serial";
								public static final String YearFlag = "year-flag";
								public static final String MonthFlag = "month-flag";
								public static final String DayFlag = "day-flag";
								
							}
						}
					}
				}
				
			}
	}
	
	public final static class Departments {
		public static final String Name = "departments";
		public final static class Department {
			public static final String Name = "department";
			public static final class A {
				public static final String Title = "title";
				public static final String Code = "code";
				public static final String RemoveFlag = "remove-flag";
				public static final String id = "id";
			}
		}
	}
}
