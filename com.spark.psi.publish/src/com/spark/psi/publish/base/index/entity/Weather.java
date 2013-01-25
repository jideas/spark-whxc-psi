package com.spark.psi.publish.base.index.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>用户设置的城市列表</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Administrator
 * @version 2011-8-1
 */
public interface Weather{
	public GUID getRecid();
	public String getCity_name();
	public String getCity_order();
}
