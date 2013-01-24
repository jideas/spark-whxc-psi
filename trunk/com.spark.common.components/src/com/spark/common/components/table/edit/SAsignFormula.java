package com.spark.common.components.table.edit;

/**
 * ��ֵ��ʽ��ִ��expressScript����ֵ����ָ����<br>
 * ��ʽ��ʽ��{1}*{2}-{3}������1/2/3Ϊ�����
 * 
 */
public class SAsignFormula extends SFormula {

	/**
	 * ��ֵ��Ŀ��
	 */
	private String target;

	/**
	 * 
	 * @param expressScript
	 */
	public SAsignFormula(String expressScript, String target) {
		super(expressScript);
		this.target = target;
	}

	/**
	 * 
	 * @return
	 */
	public String getTarget() {
		return target;
	}

}
