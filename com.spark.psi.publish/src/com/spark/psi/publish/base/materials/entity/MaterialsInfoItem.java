package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;

/**
 * ������Ϣ�б���Ŀ
 * 
 */
public interface MaterialsInfoItem {

	public GUID getId();

	public String getCode();

	public String getName();

	public boolean isRef();

	public double getPrice();

	public MaterialsStatus getStatus();

}
