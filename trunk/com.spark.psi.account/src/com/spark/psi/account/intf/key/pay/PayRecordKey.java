/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.pay.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       ������        
 * ============================================================*/

package com.spark.psi.account.intf.key.pay;


/**
 * <p>�����¼��ѯ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-11
 */

public class PayRecordKey{

	/********��������********/
//	private String sortType; //��������  �������˳��
	private String sortCloumName; //��������

	/********��������������********/
	//�����б�ʱ��ο�ʼʱ��
	private Long compBeginTime;
	//�����б�ʱ��ν���ʱ��
	private Long compEndTime;
	//��ͨ�������ı�����
	private String searchText;

	public int totalCount;
	public double payTotal;

	/********�߼���������********/
	//�Ƿ�߼���ѯ
	private boolean isAdvance = false;
	//�������
	private String payObject;
	//���ʼʱ��
	private Long payDateBegin;
	//�������ʱ��
	private Long payDateEnd;
	//������
	private String payer;
	//��������
	private String[] payType;
	//���ʼ���
	private Double payMoneyBegin;
	//����������
	private Double payMoneyEnd;
	//���ʽ
	private String[] payWay;
	
	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	private int offset;

	/**
	 * ��ѯ����
	 */
	private int count;

	/**
	 * �Ƿ��ѯ����
	 */
	private boolean queryTotal;

	/**
	 * ����ʽ
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
	 * ��������
	 */
	public String getSortCloumName(){
		return sortCloumName;
	}

	/**
	 * ��������
	 */
	public void setSortCloumName(String sortCloumName){
		this.sortCloumName = sortCloumName;
	}

	/**
	 * �������
	 */
	public String getPayObject(){
		return payObject;
	}

	/**
	 * �������
	 */
	public void setPayObject(String payObject){
		this.payObject = payObject;
	}

	/**
	 * ���ʼʱ��
	 */
	public Long getPayDateBegin(){
		return payDateBegin;
	}

	/**
	 * ���ʼʱ��
	 */
	public void setPayDateBegin(Long payDateBegin){
		this.payDateBegin = payDateBegin;
	}

	/**
	 * �������ʱ��
	 */
	public Long getPayDateEnd(){
		return payDateEnd;
	}

	/**
	 * �������ʱ��
	 */
	public void setPayDateEnd(Long payDateEnd){
		this.payDateEnd = payDateEnd;
	}

	/**
	 * ������
	 */
	public String getPayer(){
		return payer;
	}

	/**
	 * ������
	 */
	public void setPayer(String payer){
		this.payer = payer;
	}

	/**
	 * ��������
	 */
	public String[] getPayType(){
		return payType;
	}

	/**
	 * ��������
	 */
	public void setPayType(String[] payType){
		this.payType = payType;
	}

	/**
	 * ���ʼ���
	 */
	public Double getPayMoneyBegin(){
		return payMoneyBegin;
	}

	/**
	 * ���ʼ���
	 */
	public void setPayMoneyBegin(Double payMoneyBegin){
		this.payMoneyBegin = payMoneyBegin;
	}

	/**
	 * ����������
	 */
	public Double getPayMoneyEnd(){
		return payMoneyEnd;
	}

	/**
	 * ����������
	 */
	public void setPayMoneyEnd(Double payMoneyEnd){
		this.payMoneyEnd = payMoneyEnd;
	}

	/**
	 * ���ʽ
	 */
	public String[] getPayWay(){
		return payWay;
	}

	/**
	 * ���ʽ
	 */
	public void setPayWay(String[] payWay){
		this.payWay = payWay;
	}

	/**
	 * �Ƿ�߼���ѯ
	 */
	public boolean isAdvance(){
		return isAdvance;
	}

	/**
	 * �Ƿ�߼���ѯ
	 */
	public void setAdvance(boolean isAdvance){
		this.isAdvance = isAdvance;
	}

	/**
	 * ��ͨ�������ı�����
	 */
	public String getSearchText(){
		return searchText;
	}

	/**
	 * ��ͨ�������ı�����
	 */
	public void setSearchText(String searchText){
		this.searchText = searchText;
	}

	/**
	 * �����б�ʱ��ο�ʼʱ��
	 */
	public Long getCompBeginTime(){
		return compBeginTime;
	}

	/**
	 * �����б�ʱ��ο�ʼʱ��
	 */
	public void setCompBeginTime(Long compBeginTime){
		this.compBeginTime = compBeginTime;
	}

	/**
	 * �����б�ʱ��ν���ʱ��
	 */
	public Long getCompEndTime(){
		return compEndTime;
	}

	/**
	 * �����б�ʱ��ν���ʱ��
	 */
	public void setCompEndTime(Long compEndTime){
		this.compEndTime = compEndTime;
	}
}
