/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.common
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-15       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 模拟的其他物品类
 * @author zhongxin
 *
 */
public class OtherGoods {
	private GUID tenantsId;
	private GUID recid;
	private String name;
	private String description;
	private String unit;
	private double number;
	private boolean init;
	
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setInit(boolean init) {
		this.init = init;
	}
	public boolean isInit() {
		return init;
	}
	
}
