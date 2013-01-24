package com.spark.oms.publish;


/**
 * 运营系统使用内部系统编号规则枚举
 * @author Administrator
 *
 */
public enum SystemNumber {

	orderNum("DD",4,1,"111"),//"订单编号",DD + YYYYMMDD + 4位流水
	userNum("YH",6,1,"000"),//,"职能用户编号"职能用户 YH+6位流水
	saleUserNum("SS",6,1,"000"),//"销售用户编号",销售用户 SS+6位流水
	roleNum("JS",3,1,"000"),//"角色编号",JS+3位流水
	tenantNum("ZH",4,1,"111"),//"租户编号",ZH + YYYYMMDD + 4位流水
	fishNum("京A",5,1,"000");//"鱼儿多号",京A + 5位流水
		
	private String prefix;//前缀
	private int scale;//位数
	private int firstSerial;//起始值
	private String ymd;//年月日  1用 0不用
	
	private SystemNumber(String prefix,int scale, int firstSerial,String ymd) {
		this.prefix = prefix;
		this.scale = scale;
		this.firstSerial = firstSerial;
		this.ymd = ymd;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public int getScale() {
		return scale;
	}

	public int getFirstSerial() {
		return firstSerial;
	}

	public String getYmd() {
		return ymd;
	}
}