/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.pay.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       向中秋        
 * ============================================================*/

package com.spark.psi.account.intf.key.pay;


/**
 * <p>付款记录查询工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 向中秋
 * @version 2011-11-11
 */

public class PayRecordKey{

	/********排序条件********/
//	private String sortType; //排序类型  倒叙或者顺序
	private String sortCloumName; //排序列名

	/********下拉框搜索条件********/
	//下拉列表时间段开始时间
	private Long compBeginTime;
	//下拉列表时间段结束时间
	private Long compEndTime;
	//普通搜索框文本內容
	private String searchText;

	public int totalCount;
	public double payTotal;

	/********高级搜索条件********/
	//是否高级查询
	private boolean isAdvance = false;
	//付款对象
	private String payObject;
	//付款开始时间
	private Long payDateBegin;
	//付款结束时间
	private Long payDateEnd;
	//付款人
	private String payer;
	//付款类型
	private String[] payType;
	//付款开始金额
	private Double payMoneyBegin;
	//付款结束金额
	private Double payMoneyEnd;
	//付款方式
	private String[] payWay;
	
	/**
	 * 查询偏移（从0开始）
	 */
	private int offset;

	/**
	 * 查询数量
	 */
	private int count;

	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	/**
	 * 排序方式
	 */
	private String sortType;
	

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(double payTotal) {
		this.payTotal = payTotal;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * 排序列名
	 */
	public String getSortCloumName(){
		return sortCloumName;
	}

	/**
	 * 排序列名
	 */
	public void setSortCloumName(String sortCloumName){
		this.sortCloumName = sortCloumName;
	}

	/**
	 * 付款对象
	 */
	public String getPayObject(){
		return payObject;
	}

	/**
	 * 付款对象
	 */
	public void setPayObject(String payObject){
		this.payObject = payObject;
	}

	/**
	 * 付款开始时间
	 */
	public Long getPayDateBegin(){
		return payDateBegin;
	}

	/**
	 * 付款开始时间
	 */
	public void setPayDateBegin(Long payDateBegin){
		this.payDateBegin = payDateBegin;
	}

	/**
	 * 付款结束时间
	 */
	public Long getPayDateEnd(){
		return payDateEnd;
	}

	/**
	 * 付款结束时间
	 */
	public void setPayDateEnd(Long payDateEnd){
		this.payDateEnd = payDateEnd;
	}

	/**
	 * 付款人
	 */
	public String getPayer(){
		return payer;
	}

	/**
	 * 付款人
	 */
	public void setPayer(String payer){
		this.payer = payer;
	}

	/**
	 * 付款类型
	 */
	public String[] getPayType(){
		return payType;
	}

	/**
	 * 付款类型
	 */
	public void setPayType(String[] payType){
		this.payType = payType;
	}

	/**
	 * 付款开始金额
	 */
	public Double getPayMoneyBegin(){
		return payMoneyBegin;
	}

	/**
	 * 付款开始金额
	 */
	public void setPayMoneyBegin(Double payMoneyBegin){
		this.payMoneyBegin = payMoneyBegin;
	}

	/**
	 * 付款结束金额
	 */
	public Double getPayMoneyEnd(){
		return payMoneyEnd;
	}

	/**
	 * 付款结束金额
	 */
	public void setPayMoneyEnd(Double payMoneyEnd){
		this.payMoneyEnd = payMoneyEnd;
	}

	/**
	 * 付款方式
	 */
	public String[] getPayWay(){
		return payWay;
	}

	/**
	 * 付款方式
	 */
	public void setPayWay(String[] payWay){
		this.payWay = payWay;
	}

	/**
	 * 是否高级查询
	 */
	public boolean isAdvance(){
		return isAdvance;
	}

	/**
	 * 是否高级查询
	 */
	public void setAdvance(boolean isAdvance){
		this.isAdvance = isAdvance;
	}

	/**
	 * 普通搜索框文本內容
	 */
	public String getSearchText(){
		return searchText;
	}

	/**
	 * 普通搜索框文本內容
	 */
	public void setSearchText(String searchText){
		this.searchText = searchText;
	}

	/**
	 * 下拉列表时间段开始时间
	 */
	public Long getCompBeginTime(){
		return compBeginTime;
	}

	/**
	 * 下拉列表时间段开始时间
	 */
	public void setCompBeginTime(Long compBeginTime){
		this.compBeginTime = compBeginTime;
	}

	/**
	 * 下拉列表时间段结束时间
	 */
	public Long getCompEndTime(){
		return compEndTime;
	}

	/**
	 * 下拉列表时间段结束时间
	 */
	public void setCompEndTime(Long compEndTime){
		this.compEndTime = compEndTime;
	}
}
