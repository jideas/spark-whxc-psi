package com.spark.order.service.dao.sql;

import java.util.List;

import com.jiuqi.dna.core.da.RecordSet;

/**
 * <p>结果集接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-2
 */
public interface IResultSetBySql {
	public <T> List<T> getList(RecordSet rs);
	public Object getResult(RecordSet rs);
}
