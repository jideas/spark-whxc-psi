package com.spark.oms.publish;


/**
 * ��Ӫϵͳʹ���ڲ�ϵͳ��Ź���ö��
 * @author Administrator
 *
 */
public enum SystemNumber {

	orderNum("DD",4,1,"111"),//"�������",DD + YYYYMMDD + 4λ��ˮ
	userNum("YH",6,1,"000"),//,"ְ���û����"ְ���û� YH+6λ��ˮ
	saleUserNum("SS",6,1,"000"),//"�����û����",�����û� SS+6λ��ˮ
	roleNum("JS",3,1,"000"),//"��ɫ���",JS+3λ��ˮ
	tenantNum("ZH",4,1,"111"),//"�⻧���",ZH + YYYYMMDD + 4λ��ˮ
	fishNum("��A",5,1,"000");//"������",��A + 5λ��ˮ
		
	private String prefix;//ǰ׺
	private int scale;//λ��
	private int firstSerial;//��ʼֵ
	private String ymd;//������  1�� 0����
	
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