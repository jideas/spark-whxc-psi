package com.spark.psi.inventory.intf.key.instorage;

/**
 * 
 * <p>
 * 查询逾期没到货金额Key
 * </p>
 * 
 *<p>
 * 数值统计范围：
 * 交货日期早于当前系统日期（不含当天），处理状态为“未入库”的采购订单上的订单金额的累计值+处理状态为“部分入库”的采购订单上的未入库金额（订单金额
 * -已入库金额）的累计值。 不同角色的统计范围： 总经理、采购经理：公司内的所有采购订单。
 * </p>
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 */
public class GetExceedTimeLimitNotDeliveredAmountKey {

}
