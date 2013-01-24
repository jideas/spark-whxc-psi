package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryItem;

/**
 * 商品库存列表数据<br>
 * 查询方法：<br>
 * 根据GetInventoryItemKey查询InventoryItem列表<br>
 */

public class InventoryItemImpl implements InventoryItem{

	private GUID stockId;
	private String code;
	private String number;
	private String name;
	private int shelfLife;
	private String spec;
	private String properties;
	private String unit;
	private double count;
	/**
	 * 数量小数位
	 */
	private int scale;

	public GUID getStockId(){
		return stockId;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

	public String getProperties(){
		return properties;
	}

	public String getUnit(){
		return unit;
	}

	public double getCount(){
		return count;
	}

	public void setStockId(GUID stockId){
		this.stockId = stockId;
	}

	public void setCode(String code){
		this.code = code;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setProperties(String properties){
		this.properties = properties;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public void setCount(double count){
		this.count = count;
	}

	public int getScale(){
		return scale;
	}

	public void setScale(int scale){
		this.scale = scale;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	

}
