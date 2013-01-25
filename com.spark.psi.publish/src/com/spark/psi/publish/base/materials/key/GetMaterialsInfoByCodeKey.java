/**
 * 
 */
package com.spark.psi.publish.base.materials.key;

/**
 * 
 *
 */
public class GetMaterialsInfoByCodeKey {
	private String materialsCode;
	
	public GetMaterialsInfoByCodeKey(String materialsCode) {
		this.materialsCode = materialsCode;
	}
	
	public String getMaterialsCode() {
		return this.materialsCode;
	}
}
