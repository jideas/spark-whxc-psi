package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.base.goods.entity.GoodsInfoItem;

public class GoodsInfoItemImpl implements GoodsInfoItem {

	private GUID id;

	private String code;

	private String name;

	private double price;

	private boolean ref;

	private GoodsStatus status;

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the refFlag
	 */
	public boolean isRef() {
		return ref;
	}

	/**
	 * @param refFlag
	 *            the refFlag to set
	 */
	public void setRef(boolean ref) {
		this.ref = ref;
	}

	/**
	 * @return the status
	 */
	public GoodsStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

}
