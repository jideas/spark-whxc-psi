package com.spark.oms.publish.config;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.YesOrNo;
import com.spark.oms.publish.common.DateUtils;
import com.spark.oms.publish.config.ParamConfig.BillNoRule;
import com.spark.oms.publish.config.ParamConfig.Orgnization;
import com.spark.uac.publish.TenantConfigFormat;

public class GenConfigXmlUtil {
	
	private  List<Orgnization> list;

	private  StringBuffer sb = new StringBuffer();
	
	public  String genConfigXml(TenantsConfig tc){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<").append(TenantConfigFormat.Root)
			.append(" ").append(TenantConfigFormat.A.TenantId).append("=\"").append(tc.getTenantsId()).append("\"")
			.append(" ").append(TenantConfigFormat.A.TenantName).append("=\"").append(tc.getTenantsName()).append("\"")
			.append(" ").append(TenantConfigFormat.A.TenantShortName).append("=\"").append(tc.getTenantsShortName()).append("\"")
			.append(" ").append(TenantConfigFormat.A.BossName).append("=\"").append(tc.getBossName()).append("\"")
			.append(" ").append(TenantConfigFormat.A.BossMobile).append("=\"").append(tc.getBossMoblile()).append("\"")
			.append(" ").append(TenantConfigFormat.A.FishNumber).append("=\"").append(tc.getTenantsCode()).append("\"")
			.append(">");
		
		sb.append("<").append(TenantConfigFormat.Services.Name).append(">");
		//TODO:遍历服务
		List<ServiceConfig> services = tc.getServices();
		for(ServiceConfig service:services){
			
			//start service
			sb.append("<").append(TenantConfigFormat.Services.Service.Name)
				.append(" ").append(TenantConfigFormat.Services.Service.A.ProductCode).append("=\"").append(service.getProductCode()).append("\"")
				.append(" ").append(TenantConfigFormat.Services.Service.A.ProductSerial).append("=\"").append(service.getProductSerial()).append("\"")
				.append(" ").append(TenantConfigFormat.Services.Service.A.UserCount).append("=\"").append(service.getUserNum()).append("\"")
				.append(" ").append(TenantConfigFormat.Services.Service.A.ServerStartDate).append("=\"").append(service.getServerStartDate()).append("\"") //DateUtils.formatDateToShortStr(service.getServerStartDate())
				.append(" ").append(TenantConfigFormat.Services.Service.A.ServerEndDate).append("=\"").append(service.getServerEndDate()).append("\"") //DateUtils.formatDateToShortStr(service.getServerEndDate())
				.append(" ").append(TenantConfigFormat.Services.Service.A.ServiceHostId).append("=\"").append(service.getServerHostId()).append("\"")//service.getServerHostId()
				.append(" ").append(TenantConfigFormat.Services.Service.A.SysName).append("=\"").append(service.getSysName()).append("\"")
				.append(">");
			
			//start ParamConfig
			ParamConfig paramConfig = service.getParamConfig();
			sb.append("<").append(TenantConfigFormat.Services.Service.ParamConfig.Name)
				.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.A.DirectProviderFlag).append("=\"").append((null!=paramConfig.getDirectProviderFlag()&&paramConfig.getDirectProviderFlag().equals(YesOrNo.Yes.getCode()))?"true":"false").append("\"")
				.append(">");
				//TODO:遍历单据编号配置
					sb.append("<").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.Name).append(">");
					List<BillNoRule> billNoRules = paramConfig.getBillNoRules();
					for(BillNoRule billNoRule:billNoRules){
						sb.append("<").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.Name)
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.BillCode).append("=\"").append(billNoRule.getBillCode()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.Prefix).append("=\"").append(billNoRule.getPrefix()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.Scale).append("=\"").append(billNoRule.getScale()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.FirstSerial).append("=\"").append(billNoRule.getFirstSerial()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.YearFlag).append("=\"").append(billNoRule.getYearFlag()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.MonthFlag).append("=\"").append(billNoRule.getMonthFlag()).append("\"")
						.append(" ").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.BillNoRule.A.DayFlag).append("=\"").append(billNoRule.getDayFlag()).append("\"")
						.append("/>");
					}
					sb.append("</").append(TenantConfigFormat.Services.Service.ParamConfig.BillNoRules.Name).append(">");
									
					//TODO:遍历机构
					sb.append("<").append(TenantConfigFormat.Departments.Name).append(">");
					list = paramConfig.getOrgnizations();
					sb.append(OrgnizationtoString());
					//TODO:遍历
					sb.append("</").append(TenantConfigFormat.Departments.Name).append(">");
					
					
				sb.append("</").append(TenantConfigFormat.Services.Service.ParamConfig.Name).append(">");
			//end	 ParamConfig
				
			sb.append("</").append(TenantConfigFormat.Services.Service.Name).append(">");
			//end service
		}
		sb.append("</").append(TenantConfigFormat.Services.Name).append(">");
		//End services
		
//		//TODO:遍历机构
//		sb.append("<").append(TenantConfigFormat.Departments.Name).append(">");
//		//TODO:遍历
//		sb.append("</").append(TenantConfigFormat.Departments.Name).append(">");
		
		sb.append("</").append(TenantConfigFormat.Root).append(">");
		
		return sb.toString();
	}
	
	/**
	 * 获取组织机构树的XML
	 * @return
	 */
	private  String OrgnizationtoString() {		
		
		if(list==null || list.size()==0){
			return ""; 
		}
		
		if(list.size()==1){
			return fillItem(list.get(0));
		}
		
		ParamConfig pc = new ParamConfig();
		ParamConfig.Orgnization tree =  pc. new Orgnization();
		tree.setOrganizationId(null);
		
		makeTree(tree,getChildren(tree.getOrganizationId()));
		//必须删除最后一个结尾标记?Bug
		String tag = "</" + TenantConfigFormat.Departments.Department.Name + ">";
		return sb.delete(sb.length()-tag.length(), sb.length()).toString();
	}

	/**
	 * 递归遍历树
	 * @param root
	 * @param list
	 */
	private  void makeTree(Orgnization root, List<Orgnization> list) {
		if (null == list || null == root) {
			
		} else {
			int i = 0;
			for (Orgnization item:list) {				
				List<Orgnization> result = getChildren(item.getOrganizationId());
				if (null != result && result.size() > 0) {
					sb.append(fillItemParent(item));
					makeTree(item,result);
				} else {
					sb.append(fillItem(item));
				}
				i++;				
				if(i==list.size()){
					sb.append("</").append(TenantConfigFormat.Departments.Department.Name).append(">");	
				}
			}
		}
	}
	
	/**
	 * 获取子节点集
	 * @param parent
	 * @return
	 */
	private  List<Orgnization> getChildren(GUID parent){
		List<Orgnization> result = new ArrayList<Orgnization>();
		for(Orgnization item:list){			
			if(null==parent){
				if(null==item.getOrganizationParent()){
					result.add(item);
				}				
			} else {			
				if(null==item.getOrganizationParent()){
					
				} else if( item.getOrganizationParent().equals(parent)){
					result.add(item);
				}
			}
		}
		return result;
	}
	
	/**
	 * 分支结点字符串的构造
	 * @param curr
	 * @return
	 */
	private  String fillItemParent(Orgnization curr) {
		StringBuffer sb = new StringBuffer();		
		sb.append("<").append(TenantConfigFormat.Departments.Department.Name)
			.append(" ").append(TenantConfigFormat.Departments.Department.A.id).append("=\"").append(curr.getOrganizationId().toString()).append("\"")
			.append(" ").append(TenantConfigFormat.Departments.Department.A.Title).append("=\"").append(curr.getOrganizationName()).append("\"")
			.append(" ").append(TenantConfigFormat.Departments.Department.A.RemoveFlag).append("=\"").append(curr.getRemoveFlag()).append("\"")
			.append(">");
		return sb.toString();
	}
	
	/**
	 * 叶子字符串的构造
	 * @param curr
	 * @return
	 */
	private  String fillItem(Orgnization curr) {		
		StringBuffer sb = new StringBuffer();		
		sb.append("<").append(TenantConfigFormat.Departments.Department.Name)
			.append(" ").append(TenantConfigFormat.Departments.Department.A.id).append("=\"").append(curr.getOrganizationId().toString()).append("\"")
			.append(" ").append(TenantConfigFormat.Departments.Department.A.Title).append("=\"").append(curr.getOrganizationName()).append("\"")
			.append(" ").append(TenantConfigFormat.Departments.Department.A.RemoveFlag).append("=\"").append(curr.getRemoveFlag()).append("\"") 
			.append(">");		
		sb.append("</").append(TenantConfigFormat.Departments.Department.Name).append(">");		
		return sb.toString();
	}
}