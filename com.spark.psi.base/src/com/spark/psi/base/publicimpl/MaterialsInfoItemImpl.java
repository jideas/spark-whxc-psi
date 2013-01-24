package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsInfoItem;

public class MaterialsInfoItemImpl implements MaterialsInfoItem {

	private GUID id;

	private String code;

	private String name;

	private double price;

	private boolean ref;

	private MaterialsStatus status;

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
	public MaterialsStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(MaterialsStatus status) {
		this.status = status;
	}

}
