package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>查询拥有指定职能的员工列表</p>
 *


 *
 
 * @version 2012-3-14
 */
public class GetEmployeeListByAuthKey {
	
	private String searchText = "";
	
	/**
	 * 
	 * <p>层级深度</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-20
	 */
	public enum Level {
		One(1),
		Max(16);
		
		final int level;
		
		public int getLevel(){
        	return level;
        }
		
		Level(int level){
			this.level = level;
		}
	}
	
	private final Auth[] auths;
	
	private GUID tenantId;

	
	public GetEmployeeListByAuthKey(final Auth... auths){
		this.auths = auths;
	}

	public GetEmployeeListByAuthKey(Auth[] auths2, String searchText2){
	    this.auths = auths2;
	    this.searchText = searchText2;
    }

	public Auth[] getAuths(){
    	return auths;
    }

	public String getSearchText(){
    	return StringUtils.isEmpty(searchText)?"":searchText;
    }

	public void setSearchText(String searchText){
    	this.searchText = searchText;
    }

	/**
     * @return the tenantId
     */
    public GUID getTenantId(){
    	return tenantId;
    }

	/**
     * @param tenantId the tenantId to set
     */
    public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	
	
}
