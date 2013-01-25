package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.PromotionStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

/**
 * 
 * <p>获得员工未确认促销列表</p>
 *


 *
 
 * @version 2012-3-6
 */
public class GetPromotionListKey extends LimitKey{
	
	private PromotionStatus[] promotionstatuss; //促销状态

	private QueryTerm queryTerm;  
	
	private QueryScope queryScope;
	
	private SortField sortField;
	
	
	public QueryTerm getQueryTerm(){
    	return queryTerm;
    }

	public void setQueryTerm(QueryTerm queryTerm){
    	this.queryTerm = queryTerm;
    }

	public QueryScope getQueryScope(){
    	return queryScope;
    }

	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }

	public SortField getSortField(){
    	return sortField;
    }

	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }

	public void setPromotionStatus(PromotionStatus... promotionstatus){
    	this.promotionstatuss = promotionstatus;
    }

	public GetPromotionListKey(int offset, int count,
            PromotionStatus... promotionstatus)
    {
	    super(offset, count, true);
	    this.promotionstatuss = promotionstatus;
    }

	public GetPromotionListKey(int offset, int count)
    {
	    super(offset, count, true);
    }

	public PromotionStatus[] getPromotionStatus(){
    	return promotionstatuss;
    }


	public static enum SortField{
//		//商品名称排序
//		sortColumn = "1";
//		//商品属性排序
//		sortColumn = "2";
//		//开始日期排序
//		sortColumn = "3";
//		//结束日期排序
//		sortColumn = "4";
//		//状态/发起人排序
//		sortColumn = "5";
//		//促销数量
//		sortColumn = "6";
//		//已售数量
//		sortColumn = "7";
//		//原价
//		sortColumn = "8";
//		//促销单价
//		sortColumn = "9";
		/** 商品名称 */
		goodsName("t.goodsName"),
		/** 商品属性 */
		goodsProperty("t.goodsProperties"),
		/** 促销商品数量 */
		promotionCount("t.promotionCount"),
		/** 原始单价 */
		originalPrice("t.price_goods"),
		/** 促销单价 */
		promotionPrice("t.price_promotion"),
		/** 开始日期 */
		startDate("t.beginDate"),
		/** 结束日期 */
		endDate("t.endDate"),
		/** 已售数量 */
		saleCount("t.saledCount"),
		/** 状态*/
		PromotionStatus("t.status"),
		/** 发起人*/
		creator("t.creator"),		
		/** 不排序 */
		None("t.goodsName");

		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }

	}
	
}
