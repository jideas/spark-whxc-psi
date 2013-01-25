package com.spark.order.promotion.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectKey.ScopeEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.promotion.intf.SelectPromotionKey;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.psi.base.Department;

/**
 * <p>促销单查询sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
class SelectPromotionSql extends DnaSql_query{
	static final String promotion_Table = "SA_GOODS_Promotion";
	private final SelectPromotionKey key;

	public SelectPromotionSql(Context context, SelectPromotionKey key) {
		super(context);
		this.key = key;
	}
	
	/**
	 * 获得返回结果集。
	 * @return List<Promotion2>
	 */
	public List<Promotion2> getList(RecordSet rs){
		List<Promotion2> list = new ArrayList<Promotion2>();
		Promotion2 p;
		while(rs.next()){
			int index = 0;
			p = new Promotion2();
//			sql.append(" t.'approvalDate' as 'approvalDate', ");
//			sql.append(" t.'beginDate' as 'beginDate', ");
//			sql.append(" t.'countDecimal' as 'countDecimal', ");
//			sql.append(" t.'deptId' as 'deptId', ");
//			sql.append(" t.'endDate' as 'endDate', ");
			p.setApprovalDate(rs.getFields().get(index++).getDate());
			p.setBeginDate(rs.getFields().get(index++).getDate());
			p.setCountDecimal(rs.getFields().get(index++).getInt());
			p.setDeptId(rs.getFields().get(index++).getGUID());
			p.setEndDate(rs.getFields().get(index++).getDate());
//			sql.append(" t.'goodsItemId' as 'goodsItemId', ");
//			sql.append(" t.'goodsName' as 'goodsName', ");
//			sql.append(" t.'goodsProperties' as 'goodsProperties', ");
//			sql.append(" t.'price_goods' as 'price_goods', ");
//			sql.append(" t.'price_promotion' as 'price_promotion', ");
			p.setGoodsItemId(rs.getFields().get(index++).getGUID());
			p.setGoodsName(rs.getFields().get(index++).getString());
			p.setGoodsProperties(rs.getFields().get(index++).getString());
			p.setPrice_goods(rs.getFields().get(index++).getDouble());
			p.setPrice_promotion(rs.getFields().get(index++).getDouble());
//			sql.append(" t.'promotionCause' as 'promotionCause', ");
//			sql.append(" t.'promotionCount' as 'promotionCount', ");
//			sql.append(" t.'returnCause' as 'returnCause', ");
//			sql.append(" t.'saledCount' as 'saledCount', ");
//			sql.append(" t.'status' as 'status', ");
			p.setPromotionCause(rs.getFields().get(index++).getString());
			p.setPromotionCount(rs.getFields().get(index++).getDouble());
			p.setReturnCause(rs.getFields().get(index++).getString());
			p.setSaledCount(rs.getFields().get(index++).getDouble());
			p.setStatus(rs.getFields().get(index++).getString());
//			sql.append(" t.'createDate' as 'createDate', ");
//			sql.append(" t.'creator' as 'creator', ");
//			sql.append(" t.'creatorId' as 'creatorId', ");
//			sql.append(" t.'RECID' as 'recid', ");
//			sql.append(" t.'tenantsId' as 'tenantsId' ");
			p.setCreateDate(rs.getFields().get(index++).getDate());
			p.setCreator(rs.getFields().get(index++).getString());
			p.setCreatorId(rs.getFields().get(index++).getGUID());
			p.setRecid(rs.getFields().get(index++).getGUID());
			p.setTenantsId(rs.getFields().get(index++).getGUID());
			list.add(p);
		}
		return list;
	}

	@Override
	protected String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" t.approvalDate as approvalDate, ");
		sql.append(" t.beginDate as beginDate, ");
		sql.append(" t.countDecimal as countDecimal, ");
		sql.append(" t.deptId as deptId, ");
		sql.append(" t.endDate as endDate, ");
		sql.append(" t.goodsItemId as goodsItemId, ");
		sql.append(" t.goodsName as goodsName, ");
		sql.append(" t.goodsProperties as goodsProperties, ");
		sql.append(" t.price_goods as price_goods, ");
		sql.append(" t.price_promotion as price_promotion, ");
		sql.append(" t.promotionCause as promotionCause, ");
		sql.append(" t.promotionCount as promotionCount, ");
		sql.append(" t.returnCause as returnCause, ");
		sql.append(" t.saledCount as saledCount, ");
		sql.append(" t.status as status, ");
		sql.append(" t.createDate as createDate, ");
		sql.append(" t.creator as creator, ");
		sql.append(" t.creatorId as creatorId, ");
		sql.append(" t.RECID as recid, ");
		sql.append(" t.tenantsId as tenantsId ");
		sql.append(" from ");
		sql.append(promotion_Table);
		sql.append(" as t ");
		sql.append(" where ");
		sql.append(" t.tenantsId = @tenantsId ");
		this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
		//状态
		if(CheckIsNull.isNotEmpty(key.getStatus())){
			sql.append(" and( ");
			for(int i=0; i<key.getStatus().length; i++){
				String code = "@status"+i;
				sql.append(" t.status = ");
				sql.append(code);
				this.addParam(code+" string", key.getStatus()[i].getCode());
				sql.append(" or ");
			}
			sql.append(" 1=0 ");
			sql.append(" ) ");
		}
		//部门或创建人
		if(ScopeEnum.Main == key.getScopeEnum()){
			sql.append(" and t.creatorId = @creatorId ");
			this.addParam("@creatorId guid", BillsConstant.getUserGuid(context));
		}
		else if(ScopeEnum.Dept == key.getScopeEnum()){
			List<Department> list = getPromotionDept(key.getSelectDeptId());
			if(CheckIsNull.isNotEmpty(list)){
				sql.append(" and( ");
				for(int i=0; i<list.size(); i++){
					Department dept = list.get(i);
					sql.append(" t.deptId = @dept"+i);
					this.addParam("@dept"+i+" guid", dept.getId());
					sql.append(" or ");
				}
				sql.append(" t.deptId = @tenantsId ");
				sql.append(" ) ");
			}
		}
		//查询时效
		if(null != key.getTime() && null != key.getTime().getName()){
			sql.append(" and ");
			sql.append(" t.beginDate >= @beginDate ");
			this.addParam("@beginDate date", key.getTime().getBeginTime());
			if(null != key.getTime().getEndTime()){
				sql.append(" and ");
				sql.append(" t.beginDate < @endDate ");
				this.addParam("@endDate date", key.getTime().getEndTime());
			}
		}
		//搜索
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			sql.append(" and( ");
			this.addParam("@searchText string", "%"+key.getSearchText()+"%");
			List<String> list = PromotionStatus2.searchstatus(key.getSearchText());
			for(int i=0; i<list.size(); i++){
				sql.append(" t.status = @searchstatus"+i);
				sql.append(" or ");
				this.addParam("@searchstatus"+i+" string", list.get(i));
			}
			if(0 == list.size()){
				sql.append(" 1=0 or ");
			}
			sql.append(" t.goodsName like @searchText or ");
			sql.append(" t.goodsNamePY like @searchText or ");
			sql.append(" t.goodsProperties like @searchText ");
			sql.append(" ) ");
		}
		//排序
		sql.append(" order by ");
		sql.append(key.getSortField());
		sql.append("  ");
		sql.append(key.getSortType());
		return sql.toString();
	}
	
	/**
	 * 获得促销所需部门，所有下级和直线上级（不含顶级部门租户）
	 * @return List<?>
	 */
	private List<Department> getPromotionDept(GUID deptId){
		Department dept = context.find(Department.class, deptId);
		if(null == dept.getParent()){
			return null;
		}
		List<Department> list = new ArrayList<Department>();
		int maxDeptTier = BillsConstant.deptTierMax;
		switch (BillsConstant.getUserAuth(context, OrderEnum.Sales_Promotion)) {
		case BOSS:
			list.addAll(Arrays.asList(dept.getDescendants(context)));
			break;
		case MANGER:
			list.addAll(Arrays.asList(dept.getDescendants(context)));
			while (null != dept.getParent() && 0 != maxDeptTier--) {
				list.add(dept);
				dept = dept.getParent(context);
			}
			break;
		case EMPLOYEE:
			while (null != dept.getParent() && 0 != maxDeptTier--) {
				list.add(dept);
				dept = dept.getParent(context);
			}
			break;
		}
		return list;
	}

}
