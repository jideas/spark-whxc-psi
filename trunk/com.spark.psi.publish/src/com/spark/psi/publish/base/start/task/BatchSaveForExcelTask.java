/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.start.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-9    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.start.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-9    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.start.task;

import java.io.InputStream;
import java.util.Map;

import com.jiuqi.dna.core.invoke.Task;

/**
 * <p>通过excel批量保存数据</p>
 *
  */

public class BatchSaveForExcelTask extends Task<com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model>{

	public enum Model{
		Customer,
		Supplier,
		Goods,
		Materials
	}
	
	/**
	 * 
	 * <p>输入值错误类型</p>
	 *
	 */
	public enum ErrType {
		/** 不能为空 */
		IsNull("不能为空！"),
		/** 格式错误 */
		FormatError("格式错误！"),
		/** 不是数字 */
		NotIsNumber("不是数字！"),
		/** 长度不对 */
		LengthIsError("数据长度不在规定范围！"),
		/** 已被占用 */
		IsExist("已被占用！"),
		/** 省市区县XML文件不存在*/
		AreaXmlNotExist("省市区县XML文件不存在，无法进行匹配！"),
		/** 省市区县输入值不完整 */
		NotFull("省市区县输入值不完整"),
		/** 省市区县不匹配 */
		NotMatch("省市区县不匹配"),
		/** 输入值无效 */
		ValueIsVoid("输入值无效！"),
		/** 不是文本格式数据 */
		NotIsText("Excel表中该列数据请以文本格式填写！");
		
		String title;
		
		private ErrType(String title){
			this.title = title;
        }

		public String getTitle(){
        	return title;
        }
		
		
	}
	
	/**
	 * 执行日志信息
	 *  
	 * @return Map<String,ErrType> 如果为空则为保存成功
	 */
	private Map<Integer,Map<String,ErrType>> excuteLog;
	
	private InputStream excel;
	
	public BatchSaveForExcelTask(InputStream excel){
	    this.excel = excel;
    }

	public InputStream getExcel(){
    	return excel;
    }
	
	public Integer[] getExcutesNumber(){
		return excuteLog.keySet().toArray(new Integer[0]);
	}
	
	public Map<String,ErrType> getErr(Integer rowNum){
		Map<String,ErrType> err = excuteLog.get(rowNum);
//		if(err==null)throw new IllegalArgumentException(rowNum+"行没有错误");
		return err;
	}

	public void setExceutlog(Map<Integer,Map<String,ErrType>> log){
    	this.excuteLog = log;
    }
	
	
}
