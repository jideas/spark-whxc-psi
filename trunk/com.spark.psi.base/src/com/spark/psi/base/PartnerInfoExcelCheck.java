package com.spark.psi.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.task.GetEnumEntityListByNameKey;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.base.partner.task.ValidatePartnerNameTask;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.ErrType;
public class PartnerInfoExcelCheck {

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private static final int TEN = 10;
	private static final int ELEVEN = 11;
	private static final int TWELVE = 12;
	private static final int THIRTEEN = 13;
	private static final int FOURTEEN = 14;
	private static final int FIFITEEN = 15;
	private static final int SIXTEEN = 16;
	private static final int SEVENTEEN = 17;
	private static final int EIGHTEEN = 18;
	private static final int NINETEEN = 19;
	private static final int TWENTY = 20;
	private static final int TWENTY_ONE = 21;
	private static final int TWENTY_TWO = 22;
	private static final int TWENTY_THREE = 23;
	private static final int TWENTY_FOUR = 24;
	private static final int TWENTY_FIVE = 25;
	private static final int TWENTY_SIX = 26;
	private static final int TWENTY_SEVEN = 27;
	
	private String partnerType;// �ͻ�����
	
	private String name;// �ͻ���Ӧ��ȫ��

	private String shortName;// �ͻ���Ӧ�̼��

	protected String province;// ʡ��

	protected String city;// ����
	
	protected String district; //����

	protected String address;// ��ϸ��ַ

	protected String postCode;// �ʱ�

	private String workPhoneNumber;// �����绰

	private String faxNumber;// ����
	
	private String contactPersonName;// ��ϵ������
	
	private String contactPersonSex;// ��ϵ���Ա�
	
	private String contactPersonTellphone;// ��ϵ���ֻ�
	
	private String contactPersonFixTellphone;// �̻�
	
	private String contactPersonEmail;// ��������

	private String bankAccountNumber;// �����˺�

	private String bankName;// ����

	private String bankAccountName;// ��������

	private double creditAmount;// ���ö��

	private int creditDay;// ����

	private int warnningDay;// Ԥ������

	private String website;// ��ַ

	private String taxationNumber; // ��ֵ˰��

	private String developType;// �ͻ���Ӧ����Դ

	private String industyType;// ������ҵ����

	private String scaleType;// ��ҵ��ģ����

	private String memo;// ��ע
	
	private Double openCredit;// ������
	
	private GUID tenantId; //�⻧id
	
	private Context context = null;
	
	//
	protected GUID id = GUID.emptyID;
	public PartnerInfoExcelCheck(Context context)
	{
		this.context = context;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonSex() {
		return contactPersonSex;
	}

	public void setContactPersonSex(String contactPersonSex) {
		this.contactPersonSex = contactPersonSex;
	}

	public String getContactPersonTellphone() {
		return contactPersonTellphone;
	}

	public void setContactPersonTellphone(String contactPersonTellphone) {
		this.contactPersonTellphone = contactPersonTellphone;
	}

	public String getContactPersonFixTellphone() {
		return contactPersonFixTellphone;
	}

	public void setContactPersonFixTellphone(String contactPersonFixTellphone) {
		this.contactPersonFixTellphone = contactPersonFixTellphone;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getAccountPeriod() {
		return creditDay;
	}

	public void setCreditDay(int creditDay) {
		this.creditDay = creditDay;
	}

	public int getAccountRemind() {
		return warnningDay;
	}

	public void setWarnningDay(int warnningDay) {
		this.warnningDay = warnningDay;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTaxationNumber() {
		return taxationNumber;
	}

	public void setTaxationNumber(String taxationNumber) {
		this.taxationNumber = taxationNumber;
	}

	public String getDevelopType() {
		return developType;
	}

	public void setDevelopType(String developType) {
		this.developType = developType;
	}

	public String getIndustyType() {
		return industyType;
	}

	public void setIndustyType(String industyType) {
		this.industyType = industyType;
	}

	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public GUID getTenantId() {
		return tenantId;
	}

	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	
	public Double getOpenCredit() {
		return openCredit;
	}

	public void setOpenCredit(Double openCredit) {
		this.openCredit = openCredit;
	}

	/**
	 * ���ݱ�Ž���ƥ�����ÿͻ���Ϣ������Ƿ���Ч
	 */
	public void checkPartnerInfoByIndex(int index,String strValue,Map<String, ErrType> map)
	{
		switch (index) 
		{
			case ONE:											/** �ͻ���� */
					this.shortName = strValue.trim();
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						map.put("���", ErrType.IsNull);
					}
					else if(isConSpeCharacters(strValue.trim()))
					{
						map.put("���", ErrType.ValueIsVoid);
					}
					else if(strValue.trim().length() > 6)
					{
						map.put("���", ErrType.LengthIsError);
					}
					else if(this.partnerType.equals("�ͻ�"))
					{
						if(customerShortNameIsExist(strValue.trim()))
						{
							map.put("�ͻ����", ErrType.IsExist);
						}
					}
					else
					{
						if(supplierShortNameIsExist(strValue.trim()))
						{
							map.put("��Ӧ�̼��", ErrType.IsExist);
						}
					}
					break;
			case TWO:											/**�ͻ�ȫ�� */
					this.name = strValue.trim();
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						map.put("ȫ��", ErrType.IsNull);
					}
					else if(isConSpeCharacters(strValue.trim()))
					{
						map.put("ȫ��", ErrType.ValueIsVoid);
					}
					else if(strValue.trim().length() < 2)
					{
						map.put("ȫ��", ErrType.LengthIsError);
					}
					else if(this.partnerType.equals("�ͻ�"))
					{
						if(customerNameIsExist(strValue.trim()))
						{
							map.put("�ͻ�ȫ��", ErrType.IsExist);
						}
					}
					else
					{
						if(supplierNameIsExist(strValue.trim()))
						{
							map.put("��Ӧ��ȫ��", ErrType.IsExist);
						}
					}
					break;
			case THREE:											/**ʡ�� */
					this.province = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("ʡ��", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("ʡ��", ErrType.LengthIsError);
						}
					}
					break;
			case FOUR:											/**���� */
					this.city = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("����", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("����", ErrType.LengthIsError);
						}
					}
					break;
			case FIVE:											/**���� */
					this.district = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("����", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("����", ErrType.LengthIsError);
						}
					}
					break;
			case SIX:											/**��ϸ��ַ */
					this.address = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 200)
						{
							map.put("��ϸ��ַ", ErrType.LengthIsError);
						}
					}
					break;
			case SEVEN:											/**�������� */
					this.postCode = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!checkStrIsNumber(strValue.trim()))
						{
							map.put("�ʱ�", ErrType.FormatError);
						}
						else
						{
							try 
							{
								if(strValue.indexOf(".") < 0)
								{
									this.postCode = strValue.trim();
								}
								else
								{
									this.postCode = convertScientificNumberToLongString(strValue.trim());
								}
								if(CheckIsNull.isNotEmpty(this.postCode))
								{
									if(!isPostCode(this.postCode))
									{
										map.put("�ʱ�", ErrType.FormatError);
									}
								}
							} 
							catch (Exception e) 
							{
								map.put("�ʱ�", ErrType.ValueIsVoid);
							}
						}
					}
					break;
			case EIGHT:											/**�����绰 */
					this.workPhoneNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFixPhoneNumber(strValue.trim()))
						{
							map.put("�����绰", ErrType.FormatError);
						}
					}
					break;
			case NINE:											/**���� */
					this.faxNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFaxNumber(strValue.trim()))
						{
							map.put("����", ErrType.FormatError);
						}
					}
					break;
			case TEN:											/**��ϵ������ */
					this.contactPersonName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() < 2)
						{
							map.put("��ϵ������", ErrType.FormatError);
						}
					}
					break;
			case ELEVEN:										/**��ϵ���Ա� */
					this.contactPersonSex = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("�Ա�", ErrType.ValueIsVoid);
						}
						if(!(strValue.trim().equals("��") || strValue.trim().equals("Ů")))
						{
							map.put("�Ա�", ErrType.ValueIsVoid);
						}
					}
					break;
			case TWELVE:										/**��ϵ���ֻ�����*/
					this.contactPersonTellphone = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().indexOf(".") < 0)
						{
							this.contactPersonTellphone = strValue.trim();
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("��ϵ���ֻ�����", ErrType.NotIsNumber);
							}
						}
						else
						{
							try 
							{
								this.contactPersonTellphone = convertScientificNumberToLongString(strValue);
								if(CheckIsNull.isNotEmpty(this.contactPersonTellphone))
								{
									if(this.contactPersonTellphone.length() != 11)
									{
										map.put("��ϵ���ֻ�����", ErrType.LengthIsError);
									}
									else if(!isPhoneNumber(this.contactPersonTellphone))
									{
										map.put("��ϵ���ֻ�����", ErrType.FormatError);
									}
								}
							} 
							catch (Exception e)
							{
								map.put("��ϵ���ֻ�����", ErrType.ValueIsVoid);
							}
						}
					}	
					break;
			case THIRTEEN:										/**��ϵ�˹̻� */
					this.contactPersonFixTellphone = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFixPhoneNumber(strValue.trim()))
						{
							map.put("��ϵ�˹̻�", ErrType.FormatError);
						}
					}
					break;
			case FOURTEEN:										/**��ϵ������*/
					this.contactPersonEmail = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isEmail(strValue.trim()))
						{
							map.put("��ϵ������", ErrType.FormatError);
						}
					}
					break;
					
			case FIFITEEN:										/**�������� */
					this.bankName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("��������", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() < 6 || strValue.trim().length() > 100)
						{
							map.put("��������", ErrType.LengthIsError);
						}
					}
					break;
			case SIXTEEN:										/**�������� */
					this.bankAccountName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("��������", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 50 || strValue.trim().length() < 2)
						{
							map.put("��������", ErrType.LengthIsError);
						}
					}
					break;
			case SEVENTEEN:										/**�����ʺ� */
					this.bankAccountNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!strIsNumber(this.bankAccountNumber))
						{
							map.put("�����ʺ�", ErrType.NotIsNumber);
						}
						if(this.bankAccountNumber.indexOf(".") >= 0)
						{
							map.put("�����ʺ�", ErrType.NotIsText);
						}
						else if(strValue.trim().length() > 30)
						{
							map.put("�����˺�", ErrType.LengthIsError);
						}
					}
				break;
			case EIGHTEEN:										/**��ֵ˰�� */
					this.taxationNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!strIsNumber(this.taxationNumber))
						{
							map.put("��ֵ˰��", ErrType.NotIsNumber);
						}
						if(this.taxationNumber.indexOf(".") >= 0)
						{
							map.put("��ֵ˰��", ErrType.NotIsText);
						}
						else if(strValue.trim().length() > 100)
						{
							map.put("��ֵ˰��", ErrType.LengthIsError);
						}
					}
				break;
			case NINETEEN:										/**���ö�� */
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						this.creditAmount = 0.0;
					}
					else
					{
						if(strValue.indexOf(".") < 0)
						{
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("���ö��", ErrType.NotIsNumber);
							}
							else if(strValue.trim().length() > 17)
							{
								map.put("���ö��", ErrType.LengthIsError);
							}
							else
							{
								this.creditAmount = Double.parseDouble(strValue.trim());
							}
						}
						else 
						{
							try 
							{
								String tempString  = convertScientificNumberToDoubleString(strValue);
								if(tempString.length() > 17)
								{
									map.put("���ö��", ErrType.LengthIsError);
								}
								else
								{
									this.creditAmount = Double.parseDouble(tempString);
								}
							} 
							catch (Exception e)
							{
								map.put("���ö��", ErrType.ValueIsVoid);
							}
							
						}
					}
					break;
			case TWENTY:										/**�������� */
					String creditday = "";
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						this.creditDay = 0;
					}
					else
					{
						if(strValue.trim().indexOf(".") < 0)
						{
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("��������", ErrType.NotIsNumber);
							}
							else
							{
								try 
								{
									creditday = strValue.trim();
									this.creditDay = Integer.parseInt(creditday);
								} 
								catch (Exception e) 
								{
									map.put("��������", ErrType.ValueIsVoid);
								}
							}
						}
						else 
						{
							try 
							{
								creditday = convertScientificNumberToLongString(strValue.trim());
								this.creditDay = Integer.parseInt(creditday);
							}
							catch (Exception e) 
							{
								map.put("��������", ErrType.ValueIsVoid);
							}
							
						}
					}
					break;
			case TWENTY_ONE:									/**Ԥ������ */
					String warnningday = "";
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						this.warnningDay = 0;
					}
					else 
					{
						if(strValue.trim().indexOf(".") < 0)
						{
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("Ԥ������", ErrType.NotIsNumber);
							}
							else 
							{
								try 
								{
									warnningday = strValue.trim();
									this.warnningDay = Integer.parseInt(warnningday);
								} 
								catch (Exception e)
								{
									map.put("Ԥ������", ErrType.ValueIsVoid);
								}
							}
						}
						else 
						{
							try
							{
								warnningday = convertScientificNumberToLongString(strValue.trim());
								this.warnningDay = Integer.parseInt(warnningday);
							} 
							catch (Exception e) {
								map.put("Ԥ������", ErrType.ValueIsVoid);
							}
						}
					}
					break;
			case TWENTY_TWO:									/**�ͻ���Դ */
					this.developType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("�ͻ���Դ", ErrType.ValueIsVoid);
						}
					}
					break;
			case TWENTY_THREE:									/**��˾��վ */
					this.website = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isHomepage(strValue.trim()))
						{
							map.put("��ҵ��ַ", ErrType.FormatError);
						}
					}
					break;
			case TWENTY_FOUR:									/**������ҵ */
					this.industyType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("������ҵ", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 2)
						{
							map.put("������ҵ", ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_FIVE:									/**��ҵ��ģ */
					this.scaleType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 10)
						{
							map.put("��ҵ��ģ",  ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_SIX:									/**��ע */
					this.memo = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 1000)
						{
							map.put("��ע",  ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_SEVEN:									/** ������ */
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						this.openCredit = 0.0;
					}
					else 
					{
						if(strValue.indexOf(".") < 0)
						{
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("������", ErrType.NotIsNumber);
							}
							else if(strValue.trim().length() > 17 )
							{
								map.put("������", ErrType.LengthIsError);
							}
							else
							{
								this.openCredit = Double.parseDouble(strValue.trim());
							}
						}
						else 
						{
							try 
							{
								String tempString = convertScientificNumberToDoubleString(strValue);
								if(tempString.length() > 17)
								{
									map.put("������", ErrType.LengthIsError);
								}
								else
								{
									this.openCredit = Double.parseDouble(tempString);
								}
							}
							catch (Exception e) 
							{
								map.put("������", ErrType.ValueIsVoid);
							}
						}
					}
					break;
						
			default:
					break; 
		}
	}
	
	/**
	 * ����ַ����Ƿ��������ַ�
	 */
	public boolean isConSpeCharacters(String string) 
	{
		  if(string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0)
		  {
			  return  false;
		  }
		  return true;
	}
	
	/**
	 * ����ַ����Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public Boolean strIsNumber(String str)
	{
		for(int i = 0; i < str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ���ͻ�����Ƿ�Ψһ
	 * @param shortName
	 * @return
	 */
	public boolean customerShortNameIsExist(String shortName)
	{
		ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(this.id, shortName);
		this.context.handle(validateTask, ValidatePartnerNameTask.Method.CustomerShortName);
		return validateTask.isExist();
	}
	
	/**
	 * ���ͻ�ȫ���Ƿ�Ψһ
	 * @param Name
	 * @return
	 */
	public boolean customerNameIsExist(String name)
	{
		ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(this.id, name);
		this.context.handle(validateTask, ValidatePartnerNameTask.Method.CustomerName);
		return validateTask.isExist();
	}
	
	/**
	 * ��⹩Ӧ�̼���Ƿ�Ψһ
	 * @param shortName
	 * @return
	 */
	public boolean supplierShortNameIsExist(String shortName)
	{
		ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(this.id, shortName);
		this.context.handle(validateTask, ValidatePartnerNameTask.Method.SupplierShortName);
		return validateTask.isExist();
	}
	
	/**
	 * ��⹩Ӧ��ȫ���Ƿ�Ψһ
	 * @param Name
	 * @return
	 */
	public boolean supplierNameIsExist(String name)
	{
		ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(this.id, name);
		this.context.handle(validateTask, ValidatePartnerNameTask.Method.SupplierName);
		return validateTask.isExist();
	}
	
	/**
	 * ��֤�Ƿ��ǹ̶��绰
	 * @param str
	 * @return
	 */
	public boolean isFixPhoneNumber(String str)
	{
		String regex ="^\\(?\\d{3,4}[-\\)]?\\d{7,8}$";
		return match(regex, str);
	}
	
	/**
	 * ��֤�Ƿ��Ǵ������
	 * @param str
	 * @return
	 */
	public boolean isFaxNumber(String str)
	{
		String regex ="^\\(?\\d{3,4}[-\\)]?\\d{7,8}$";
		return match(regex, str);
	}
	
	/**
	 * ��֤�Ƿ�����ȷ���ֻ�����
	 * @param str
	 * @return
	 */
	public boolean isPhoneNumber(String str)
	{
		String regex ="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return match(regex, str);
	}
	
	/**
	 * ��֤�Ƿ�����ȷ���ʱ�
	 * @param str
	 * @return
	 */
	public boolean isPostCode(String str)
	{
		String regex ="^[1-9][0-9]{5}";
		return match(regex, str);
	}
	
	/**
	 * ��֤�Ƿ���������ʽ
	 * @param str
	 * @return
	 */
	public boolean isEmail(String str) 
	{
	    String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	    return match(regex, str);
	}
	
	/**
	 * ��֤�Ƿ������ַ��ʽ
	 * @param str
	 * @return
	 */
	public boolean isHomepage(String str)
	{
	    String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
	    return match(regex,str);
	}
	
	/**
	 * ƥ��    
	 * @param regex
	 * @param str
	 * @return
	 */
	private boolean match(String regex,String str)
	{
	     Pattern pattern = Pattern.compile(regex);
	     Matcher  matcher = pattern.matcher(str);
	     return matcher.matches();
	}
	
	/**
	 * �����ϵ�������Ƿ���Ϊ��
	 * @param map
	 */
	public void checkContactInfo(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.contactPersonSex) 
			|| CheckIsNull.isNotEmpty(this.contactPersonTellphone) 
			|| CheckIsNull.isNotEmpty(this.contactPersonFixTellphone) 
			|| CheckIsNull.isNotEmpty(this.contactPersonEmail))
		{
			if(CheckIsNull.isEmpty(this.contactPersonName))
			{
				map.put("��ϵ������", ErrType.IsNull);
			}
		}
	}
	
	/**
	 * ���Excel�е������Ƿ���Ч
	 * @param str
	 * @return
	 */
	public boolean checkStrIsNumber(String str)
	{
		/** �������ִ��ڵ���1���������ַ�����Ϊ'0' */
		Pattern pattern1 = Pattern.compile("[1-9]\\d*\\.?\\d+");
		/** ��㼸 */
		Pattern pattern2 = Pattern.compile("0{1}\\.\\d+");     
		/** �ַ���Ϊ0 */
		Pattern pattern3 = Pattern.compile("0{1}");
		
		/** �����ַ���ƥ�� */
		Matcher matcher1 = pattern1.matcher(str);
		Matcher matcher2 = pattern2.matcher(str);
		Matcher matcher3 = pattern3.matcher(str);
		if(matcher1.matches() || matcher2.matches() || matcher3.matches())
		{
			return true;
		}
		else {
			return false;
		}  
	}
	
	/*************************
	 * ���ݱ���ת��
	 *************************/
	
	/**
	 * ��ĳЩ����ת���ɶ�Ӧ�ı���
	 */
	public void setCode(Map<String, ErrType> map)
	{
		/** �����ϵ�������Ƿ���Ϊ�� */
		checkContactInfo(map);
		setSexCode();
		setAreaCode(map);
		setIndustyTypeCode(map);
		setPartnerDevelopTypeCode(map);
		setCompanyScaleTypeCode(map);
	}
	
	/**
	 * �����Ա����
	 */
	public void setSexCode(){
		
		if(CheckIsNull.isNotEmpty(this.contactPersonSex))
		{
			if(this.contactPersonSex.equals("��"))
			{
				this.contactPersonSex = "01";
			}
			else {
				this.contactPersonSex = "02";
			}
		}
	}
	
	/**
	 * ���ÿͻ���Ӧ����Դ����
	 */
	public void setPartnerDevelopTypeCode(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.developType))
		{
			GetEnumEntityListByNameKey partnerDevelopTypeKey = new GetEnumEntityListByNameKey(EnumType.CustomerDevelopeType, this.developType);
			List<EnumEntity> partnerDevelopTypeList = context.getList(EnumEntity.class,partnerDevelopTypeKey);
			if(partnerDevelopTypeList != null && partnerDevelopTypeList.size() > 0)
			{
				this.developType = partnerDevelopTypeList.get(0).getCode();
			}
			else
			{
				map.put("�ͻ���Ӧ����Դ", ErrType.ValueIsVoid);
			}
		}
	}
	
	/**
	 * ������ҵ��ģ���ͱ���
	 */
	public void setCompanyScaleTypeCode(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.scaleType))
		{
			GetEnumEntityListByNameKey scaleTypeKey = new GetEnumEntityListByNameKey(EnumType.ScaleType, this.scaleType);
			List<EnumEntity> scaleTypeList = context.getList(EnumEntity.class, scaleTypeKey);
			if(scaleTypeList != null && scaleTypeList.size() > 0)
			{
				this.scaleType = scaleTypeList.get(0).getCode();
			}
			else {
				map.put("��ҵ��ģ", ErrType.ValueIsVoid);
			}
		}
	}
	
	/**
	 * ������ҵ���ͱ���
	 * @param map
	 */
	public void setIndustyTypeCode(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.industyType))
		{
			GetEnumEntityListByNameKey industyTypeKey = new GetEnumEntityListByNameKey(EnumType.IndustyType, this.industyType);
			List<EnumEntity> industyTypeList = context.getList(EnumEntity.class, industyTypeKey);
			int l = industyTypeList.size();
			System.out.println(l);
			if(industyTypeList != null && industyTypeList.size() > 0)
			{
				this.industyType = industyTypeList.get(0).getCode();
			}
			else {
				map.put("������ҵ", ErrType.ValueIsVoid);
			}
		}
	}
	
	
	/**
	 * ����ʡ�����صı���
	 */
	public void setAreaCode(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.province) && CheckIsNull.isNotEmpty(this.city) && CheckIsNull.isNotEmpty(this.district))
		{
			/**��ȡʡ�����ص�Key */
			GetEnumEntityListByNameKey provinceKey = new GetEnumEntityListByNameKey(EnumType.Area, this.province);
			List<EnumEntity> provinceList = context.getList(EnumEntity.class, provinceKey);
			boolean areaIsMatch = false;
			if(provinceList != null && provinceList.size() > 0)
			{
				this.province = provinceList.get(0).getCode();
				List<EnumEntity> cityList = context.getList(EnumEntity.class, EnumType.Area,this.province);
				if(cityList != null && cityList.size() > 0)
				{
					for(int i = 0; i < cityList.size(); i++)
					{
						EnumEntity cityEntity = cityList.get(i);
						if(cityEntity.getName().equals(this.city))
						{
							this.city = cityEntity.getCode();
							List<EnumEntity> districtList = context.getList(EnumEntity.class,EnumType.Area, this.city);
							if(districtList != null && districtList.size() > 0)
							{
								for(int j = 0 ; j < districtList.size(); j++)
								{
									EnumEntity districtEntity = districtList.get(j);
									if(districtEntity.getName().equals(this.district))
									{
										this.district = districtEntity.getCode();
										areaIsMatch = true;
										break;
									}
								}
							}
							break;
						}
					}
				}
			}
			if(!areaIsMatch){
				map.put("ʡ������", ErrType.NotMatch);
			}
		}
		else if(!(CheckIsNull.isEmpty(this.province) && CheckIsNull.isEmpty(this.city) && CheckIsNull.isEmpty(this.district)))
		{
			map.put("ʡ������", ErrType.NotFull);
		}
	}
	
	
	/**
	 * ����ѧ����������ת����long�������ַ���
	 * @param string
	 * @return
	 */
	public String convertScientificNumberToLongString(String string)
	{
		BigDecimal bDecimal = new BigDecimal(string.trim());
		long a = bDecimal.longValue();
		String rString  = String.valueOf(a);
		return rString;
	}
	
	/**
	 * ����ѧ����������ת����double�������ַ���
	 * @param string
	 * @return
	 */
	public String convertScientificNumberToDoubleString(String string)
	{
		BigDecimal bDecimal = new BigDecimal(string.trim());
		double a = bDecimal.doubleValue();
		String rString  = String.valueOf(a);
		return rString;
	}
}

