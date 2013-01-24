/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.service.common
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-16       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.util.inventory;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryDeliveringTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryOnWayTask;
import com.spark.psi.publish.InventoryType;

/**
 * @author zhongxin
 * 
 */
public class SqlBuildHelper {
	private StringBuffer scriptBuffer = new StringBuffer();
	private StringBuffer paramterStr = new StringBuffer();// 存放SQL中的参数
	private List<Object> paramterList = new ArrayList<Object>();// 参数值

	public SqlBuildHelper() {

	}

	/**
	 * 取得DNA SQL
	 * 
	 * @return
	 */
	public String getQuerySql() {
		return this.scriptBuffer.toString();
	}

	/**
	 * 取得SQL中的参数值
	 * 
	 * @return
	 */
	public List<Object> getParameterList() {
		return paramterList;
	}

	/**
	 * 构建修改库存业务数据的SQL
	 * 
	 * @param task
	 */
	public void buildUpdateStorageBusSql(InventoryBusTask task) {
		paramterStr.append("@storeId guid, @stockId guid,");
		paramterList.add(task.getStoreId());
		paramterList.add(task.getStockId());

		List<String> setColumnList = new ArrayList<String>();
		if (null != task.getChangeAmount()) {
			paramterStr.append("@amount double,");
			paramterList.add(task.getChangeAmount());
			setColumnList.add(" amount = case when (t.amount + @amount) > 0 then (t.amount + @amount) else 0 end ");
		}
		if (null != task.getChangeCount()) {
			paramterStr.append("@count double,");
			paramterList.add(task.getChangeCount());
			setColumnList.add(" \"count\" = case when (t.\"count\" + @count) > 0 then (t.\"count\" + @count) else 0 end ");
			if(InventoryType.Goods.equals(task.getInventoryType()))
			{
				setColumnList.add(" \"lockedCount\" = case when (t.\"lockedCount\" + @count) > 0 then (t.\"lockedCount\" + @count) else 0 end ");
			}
		}
		if (null != task.getNewCost()) {
			paramterStr.append("@unitCost double,");
			paramterList.add(task.getNewCost());
			StringBuilder newCostColum = new StringBuilder();
			newCostColum.append(" amount = @unitCost * ");
			if (null != task.getChangeCount()) {
				newCostColum.append("(t.\"count\"+@count)");
			} else {
				newCostColum.append("t.\"count\"");
			}
			newCostColum.append(" ");
			setColumnList.add(newCostColum.toString());
		}

		scriptBuffer.append("define update up_storage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0, paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(")\n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(" update ");
		scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
		scriptBuffer.append(" as t set \n");
		if (setColumnList.size() < 1) {
			scriptBuffer.append("\"count\" = t.\"count\"");
		} else {
			for (int setIndex = 0; setIndex < setColumnList.size(); setIndex++) {
				scriptBuffer.append(setColumnList.get(setIndex));
				if (setIndex != setColumnList.size() - 1) {
					scriptBuffer.append(",");
				}
			}
		}
		scriptBuffer.append(" where t.storeId = @storeId and t.stockId = @stockId ");
		if (null != task.getChangeCount() && task.getChangeCount() < 0 && !task.isRetail()) {

			double changeCount = DoubleUtil.abs(task.getChangeCount());
			scriptBuffer.append(" and (t.\"count\">").append(changeCount).append(" or t.\"count\"=").append(changeCount).append(") ");
		}
		scriptBuffer.append(" end");
	}

	/**
	 * 采购在途数量Sql
	 * 
	 * @param task
	 *            void
	 */
	public void buildUpdateStorageOnWaySql(InventoryOnWayTask task) {
		paramterStr.append("@storeId guid, @stockId guid,");
		paramterList.add(task.getStoreId());
		paramterList.add(task.getStockId());

		List<String> setColumnList = new ArrayList<String>();

		if (null != task.getOnWayCount()) {
			paramterStr.append("@onWayCount double,");
			paramterList.add(task.getOnWayCount());
			setColumnList.add(" onWayCount = (t.onWayCount + @onWayCount)");
		}

		scriptBuffer.append("define update up_storage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0, paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(")\n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(" update ");
		scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
		scriptBuffer.append(" as t set \n");
		if (setColumnList.size() < 1) {
			scriptBuffer.append("count = t.count");
		} else {
			for (int setIndex = 0; setIndex < setColumnList.size(); setIndex++) {
				scriptBuffer.append(setColumnList.get(setIndex));
				if (setIndex != setColumnList.size() - 1) {
					scriptBuffer.append(",");
				}
			}
		}
		scriptBuffer.append(" where t.storeId = @storeId and t.stockId = @stockId ");
		scriptBuffer.append(" end");
	}

	/**
	 * 交货需求数量Sql
	 * 
	 * @param task
	 *            void
	 */
	public void buildUpdateStorageDeliveringSql(InventoryDeliveringTask task) {
		paramterStr.append("@storeId guid, @stockId guid,");
		paramterList.add(task.getStoreId());
		paramterList.add(task.getStockId());

		List<String> setColumnList = new ArrayList<String>();

		if (null != task.getToDeliverCount()) {
			paramterStr.append("@toDeliverCount double,");
			paramterList.add(task.getToDeliverCount());
			setColumnList.add(" toDeliverCount = (t.toDeliverCount + @toDeliverCount)");
		}

		scriptBuffer.append("define update up_storage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0, paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(")\n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(" update ");
		scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
		scriptBuffer.append(" as t set \n");
		if (setColumnList.size() < 1) {
			scriptBuffer.append("count = t.count");
		} else {
			for (int setIndex = 0; setIndex < setColumnList.size(); setIndex++) {
				scriptBuffer.append(setColumnList.get(setIndex));
				if (setIndex != setColumnList.size() - 1) {
					scriptBuffer.append(",");
				}
			}
		}
		scriptBuffer.append(" where t.storeId = @storeId and t.stockId = @stockId ");
		scriptBuffer.append(" end");
	}

	/**
	 * 锁定数量Sql
	 * 
	 * @param task
	 *            void
	 */
	public void buildUpdateStorageLockSql(InventoryLockTask task) {
		paramterStr.append("@storeId guid, @stockId guid,");
		paramterList.add(task.getStorId());
		paramterList.add(task.getStockId());

		List<String> setColumnList = new ArrayList<String>();

		if (null != task.getLockedCount()) {
			paramterStr.append("@lockedCount double,");
			paramterList.add(task.getLockedCount());
			setColumnList.add(" lockedCount = (t.lockedCount + @lockedCount)");
		}
		scriptBuffer.append("define update up_storage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0, paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(")\n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(" update ");
		scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
		scriptBuffer.append(" as t set \n");
		if (setColumnList.size() < 1) {
			scriptBuffer.append("\"count\" = t.\"count\"");
		} else {
			for (int setIndex = 0; setIndex < setColumnList.size(); setIndex++) {
				scriptBuffer.append(setColumnList.get(setIndex));
				if (setIndex != setColumnList.size() - 1) {
					scriptBuffer.append(",");
				}
			}
		}
		scriptBuffer.append(" where t.storeId = @storeId and t.stockId = @stockId ");
		if (InventoryType.Goods.equals(task.getInventoryType())) {
			scriptBuffer.append(" and ((t.\"count\" - (t.\"count\"+t.lockedCount))>@lockedCount").append(
					" or (t.\"count\" - (t.\"count\"+t.lockedCount))=@lockedCount)\n");
			scriptBuffer.append(" and ((t.lockedCount+@lockedCount)>0 ").append("or (t.lockedCount+@lockedCount)=0)\n");
		}
		scriptBuffer.append(" end");
	}
}
