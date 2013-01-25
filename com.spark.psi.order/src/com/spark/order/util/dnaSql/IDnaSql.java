package com.spark.order.util.dnaSql;

import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

/**
 * <p>DNA SQL接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public interface IDnaSql {
	/**
	 * 获得DNA数据库命令接口
	 * @return DBCommand
	 */
	DBCommand getDBCommand();
	/**
	 * 获得返回数据集接口
	 * @return RecordSet
	 */
	RecordSet executeQuery();
	/**
	 * 获得返回数据集接口
	 * @return RecordSet
	 */
	RecordSet executeQuery(long offset, long rowCount);
	/**
	 * 执行更新SQL，返回相应条数
	 * @return int
	 */
	int executeUpdate();
	/**
	 * 获得总数量
	 * @return long
	 */
	int rowCountOf();
	/**
	 * 获得参数集合
	 * @return List<?>
	 */
	Object[] getParams();
	/**
	 * 获得DNA数据库命令接口(所有参数一传入完成)
	 * @return DBCommand
	 */
	DBCommand getDBCommand_FinishedParams();
}
