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
	
	private String partnerType;// 客户类型
	
	private String name;// 客户供应商全称

	private String shortName;// 客户供应商简称

	protected String province;// 省份

	protected String city;// 城市
	
	protected String district; //区县

	protected String address;// 详细地址

	protected String postCode;// 邮编

	private String workPhoneNumber;// 工作电话

	private String faxNumber;// 传真
	
	private String contactPersonName;// 联系人姓名
	
	private String contactPersonSex;// 联系人性别
	
	private String contactPersonTellphone;// 联系人手机
	
	private String contactPersonFixTellphone;// 固话
	
	private String contactPersonEmail;// 电子邮箱

	private String bankAccountNumber;// 银行账号

	private String bankName;// 银行

	private String bankAccountName;// 开户名称

	private double creditAmount;// 信用额度

	private int creditDay;// 账期

	private int warnningDay;// 预警天数

	private String website;// 网址

	private String taxationNumber; // 增值税号

	private String developType;// 客户供应商来源

	private String industyType;// 所属行业类型

	private String scaleType;// 企业规模类型

	private String memo;// 备注
	
	private Double openCredit;// 往来款
	
	private GUID tenantId; //租户id
	
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
	 * 根据编号进行匹配设置客户信息并检测是否有效
	 */
	public void checkPartnerInfoByIndex(int index,String strValue,Map<String, ErrType> map)
	{
		switch (index) 
		{
			case ONE:											/** 客户简称 */
					this.shortName = strValue.trim();
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						map.put("简称", ErrType.IsNull);
					}
					else if(isConSpeCharacters(strValue.trim()))
					{
						map.put("简称", ErrType.ValueIsVoid);
					}
					else if(strValue.trim().length() > 6)
					{
						map.put("简称", ErrType.LengthIsError);
					}
					else if(this.partnerType.equals("客户"))
					{
						if(customerShortNameIsExist(strValue.trim()))
						{
							map.put("客户简称", ErrType.IsExist);
						}
					}
					else
					{
						if(supplierShortNameIsExist(strValue.trim()))
						{
							map.put("供应商简称", ErrType.IsExist);
						}
					}
					break;
			case TWO:											/**客户全称 */
					this.name = strValue.trim();
					if(CheckIsNull.isEmpty(strValue.trim()))
					{
						map.put("全称", ErrType.IsNull);
					}
					else if(isConSpeCharacters(strValue.trim()))
					{
						map.put("全称", ErrType.ValueIsVoid);
					}
					else if(strValue.trim().length() < 2)
					{
						map.put("全称", ErrType.LengthIsError);
					}
					else if(this.partnerType.equals("客户"))
					{
						if(customerNameIsExist(strValue.trim()))
						{
							map.put("客户全称", ErrType.IsExist);
						}
					}
					else
					{
						if(supplierNameIsExist(strValue.trim()))
						{
							map.put("供应商全称", ErrType.IsExist);
						}
					}
					break;
			case THREE:											/**省份 */
					this.province = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("省份", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("省份", ErrType.LengthIsError);
						}
					}
					break;
			case FOUR:											/**城市 */
					this.city = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("城市", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("城市", ErrType.LengthIsError);
						}
					}
					break;
			case FIVE:											/**区县 */
					this.district = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("区县", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 20)
						{
							map.put("区县", ErrType.LengthIsError);
						}
					}
					break;
			case SIX:											/**详细地址 */
					this.address = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 200)
						{
							map.put("详细地址", ErrType.LengthIsError);
						}
					}
					break;
			case SEVEN:											/**邮政编码 */
					this.postCode = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!checkStrIsNumber(strValue.trim()))
						{
							map.put("邮编", ErrType.FormatError);
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
										map.put("邮编", ErrType.FormatError);
									}
								}
							} 
							catch (Exception e) 
							{
								map.put("邮编", ErrType.ValueIsVoid);
							}
						}
					}
					break;
			case EIGHT:											/**工作电话 */
					this.workPhoneNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFixPhoneNumber(strValue.trim()))
						{
							map.put("工作电话", ErrType.FormatError);
						}
					}
					break;
			case NINE:											/**传真 */
					this.faxNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFaxNumber(strValue.trim()))
						{
							map.put("传真", ErrType.FormatError);
						}
					}
					break;
			case TEN:											/**联系人姓名 */
					this.contactPersonName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() < 2)
						{
							map.put("联系人姓名", ErrType.FormatError);
						}
					}
					break;
			case ELEVEN:										/**联系人性别 */
					this.contactPersonSex = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("性别", ErrType.ValueIsVoid);
						}
						if(!(strValue.trim().equals("男") || strValue.trim().equals("女")))
						{
							map.put("性别", ErrType.ValueIsVoid);
						}
					}
					break;
			case TWELVE:										/**联系人手机号码*/
					this.contactPersonTellphone = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().indexOf(".") < 0)
						{
							this.contactPersonTellphone = strValue.trim();
							if(!checkStrIsNumber(strValue.trim()))
							{
								map.put("联系人手机号码", ErrType.NotIsNumber);
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
										map.put("联系人手机号码", ErrType.LengthIsError);
									}
									else if(!isPhoneNumber(this.contactPersonTellphone))
									{
										map.put("联系人手机号码", ErrType.FormatError);
									}
								}
							} 
							catch (Exception e)
							{
								map.put("联系人手机号码", ErrType.ValueIsVoid);
							}
						}
					}	
					break;
			case THIRTEEN:										/**联系人固话 */
					this.contactPersonFixTellphone = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isFixPhoneNumber(strValue.trim()))
						{
							map.put("联系人固话", ErrType.FormatError);
						}
					}
					break;
			case FOURTEEN:										/**联系人邮箱*/
					this.contactPersonEmail = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isEmail(strValue.trim()))
						{
							map.put("联系人邮箱", ErrType.FormatError);
						}
					}
					break;
					
			case FIFITEEN:										/**开户银行 */
					this.bankName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("开户银行", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() < 6 || strValue.trim().length() > 100)
						{
							map.put("开户银行", ErrType.LengthIsError);
						}
					}
					break;
			case SIXTEEN:										/**开户名称 */
					this.bankAccountName = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("开户名称", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 50 || strValue.trim().length() < 2)
						{
							map.put("开户名称", ErrType.LengthIsError);
						}
					}
					break;
			case SEVENTEEN:										/**银行帐号 */
					this.bankAccountNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!strIsNumber(this.bankAccountNumber))
						{
							map.put("银行帐号", ErrType.NotIsNumber);
						}
						if(this.bankAccountNumber.indexOf(".") >= 0)
						{
							map.put("银行帐号", ErrType.NotIsText);
						}
						else if(strValue.trim().length() > 30)
						{
							map.put("银行账号", ErrType.LengthIsError);
						}
					}
				break;
			case EIGHTEEN:										/**增值税号 */
					this.taxationNumber = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!strIsNumber(this.taxationNumber))
						{
							map.put("增值税号", ErrType.NotIsNumber);
						}
						if(this.taxationNumber.indexOf(".") >= 0)
						{
							map.put("增值税号", ErrType.NotIsText);
						}
						else if(strValue.trim().length() > 100)
						{
							map.put("增值税号", ErrType.LengthIsError);
						}
					}
				break;
			case NINETEEN:										/**信用额度 */
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
								map.put("信用额度", ErrType.NotIsNumber);
							}
							else if(strValue.trim().length() > 17)
							{
								map.put("信用额度", ErrType.LengthIsError);
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
									map.put("信用额度", ErrType.LengthIsError);
								}
								else
								{
									this.creditAmount = Double.parseDouble(tempString);
								}
							} 
							catch (Exception e)
							{
								map.put("信用额度", ErrType.ValueIsVoid);
							}
							
						}
					}
					break;
			case TWENTY:										/**账期天数 */
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
								map.put("账期天数", ErrType.NotIsNumber);
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
									map.put("账期天数", ErrType.ValueIsVoid);
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
								map.put("账期天数", ErrType.ValueIsVoid);
							}
							
						}
					}
					break;
			case TWENTY_ONE:									/**预警天数 */
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
								map.put("预警天数", ErrType.NotIsNumber);
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
									map.put("预警天数", ErrType.ValueIsVoid);
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
								map.put("预警天数", ErrType.ValueIsVoid);
							}
						}
					}
					break;
			case TWENTY_TWO:									/**客户来源 */
					this.developType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("客户来源", ErrType.ValueIsVoid);
						}
					}
					break;
			case TWENTY_THREE:									/**公司网站 */
					this.website = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(!isHomepage(strValue.trim()))
						{
							map.put("企业网址", ErrType.FormatError);
						}
					}
					break;
			case TWENTY_FOUR:									/**所属行业 */
					this.industyType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(isConSpeCharacters(strValue.trim()))
						{
							map.put("所属行业", ErrType.ValueIsVoid);
						}
						else if(strValue.trim().length() > 2)
						{
							map.put("所属行业", ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_FIVE:									/**企业规模 */
					this.scaleType = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 10)
						{
							map.put("企业规模",  ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_SIX:									/**备注 */
					this.memo = strValue.trim();
					if(CheckIsNull.isNotEmpty(strValue.trim()))
					{
						if(strValue.trim().length() > 1000)
						{
							map.put("备注",  ErrType.LengthIsError);
						}
					}
					break;
			case TWENTY_SEVEN:									/** 往来款 */
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
								map.put("往来款", ErrType.NotIsNumber);
							}
							else if(strValue.trim().length() > 17 )
							{
								map.put("往来款", ErrType.LengthIsError);
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
									map.put("往来款", ErrType.LengthIsError);
								}
								else
								{
									this.openCredit = Double.parseDouble(tempString);
								}
							}
							catch (Exception e) 
							{
								map.put("往来款", ErrType.ValueIsVoid);
							}
						}
					}
					break;
						
			default:
					break; 
		}
	}
	
	/**
	 * 检测字符串是否含有特殊字符
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
	 * 检测字符串是否为数字
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
	 * 检测客户简称是否唯一
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
	 * 检测客户全称是否唯一
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
	 * 检测供应商简称是否唯一
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
	 * 检测供应商全称是否唯一
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
	 * 验证是否是固定电话
	 * @param str
	 * @return
	 */
	public boolean isFixPhoneNumber(String str)
	{
		String regex ="^\\(?\\d{3,4}[-\\)]?\\d{7,8}$";
		return match(regex, str);
	}
	
	/**
	 * 验证是否是传真号码
	 * @param str
	 * @return
	 */
	public boolean isFaxNumber(String str)
	{
		String regex ="^\\(?\\d{3,4}[-\\)]?\\d{7,8}$";
		return match(regex, str);
	}
	
	/**
	 * 验证是否是正确的手机号码
	 * @param str
	 * @return
	 */
	public boolean isPhoneNumber(String str)
	{
		String regex ="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return match(regex, str);
	}
	
	/**
	 * 验证是否是正确的邮编
	 * @param str
	 * @return
	 */
	public boolean isPostCode(String str)
	{
		String regex ="^[1-9][0-9]{5}";
		return match(regex, str);
	}
	
	/**
	 * 验证是否符合邮箱格式
	 * @param str
	 * @return
	 */
	public boolean isEmail(String str) 
	{
	    String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	    return match(regex, str);
	}
	
	/**
	 * 验证是否符合网址格式
	 * @param str
	 * @return
	 */
	public boolean isHomepage(String str)
	{
	    String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
	    return match(regex,str);
	}
	
	/**
	 * 匹配    
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
	 * 检测联系人姓名是否能为空
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
				map.put("联系人姓名", ErrType.IsNull);
			}
		}
	}
	
	/**
	 * 检测Excel中的数字是否有效
	 * @param str
	 * @return
	 */
	public boolean checkStrIsNumber(String str)
	{
		/** 整数部分大于等于1，但是首字符不能为'0' */
		Pattern pattern1 = Pattern.compile("[1-9]\\d*\\.?\\d+");
		/** 零点几 */
		Pattern pattern2 = Pattern.compile("0{1}\\.\\d+");     
		/** 字符串为0 */
		Pattern pattern3 = Pattern.compile("0{1}");
		
		/** 进行字符串匹配 */
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
	 * 数据编码转换
	 *************************/
	
	/**
	 * 将某些数据转换成对应的编码
	 */
	public void setCode(Map<String, ErrType> map)
	{
		/** 检测联系人姓名是否能为空 */
		checkContactInfo(map);
		setSexCode();
		setAreaCode(map);
		setIndustyTypeCode(map);
		setPartnerDevelopTypeCode(map);
		setCompanyScaleTypeCode(map);
	}
	
	/**
	 * 设置性别编码
	 */
	public void setSexCode(){
		
		if(CheckIsNull.isNotEmpty(this.contactPersonSex))
		{
			if(this.contactPersonSex.equals("男"))
			{
				this.contactPersonSex = "01";
			}
			else {
				this.contactPersonSex = "02";
			}
		}
	}
	
	/**
	 * 设置客户供应商来源编码
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
				map.put("客户供应商来源", ErrType.ValueIsVoid);
			}
		}
	}
	
	/**
	 * 设置企业规模类型编码
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
				map.put("企业规模", ErrType.ValueIsVoid);
			}
		}
	}
	
	/**
	 * 设置行业类型编码
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
				map.put("所属行业", ErrType.ValueIsVoid);
			}
		}
	}
	
	
	/**
	 * 设置省市区县的编码
	 */
	public void setAreaCode(Map<String, ErrType> map)
	{
		if(CheckIsNull.isNotEmpty(this.province) && CheckIsNull.isNotEmpty(this.city) && CheckIsNull.isNotEmpty(this.district))
		{
			/**获取省市区县的Key */
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
				map.put("省市区县", ErrType.NotMatch);
			}
		}
		else if(!(CheckIsNull.isEmpty(this.province) && CheckIsNull.isEmpty(this.city) && CheckIsNull.isEmpty(this.district)))
		{
			map.put("省市区县", ErrType.NotFull);
		}
	}
	
	
	/**
	 * 将科学计数的数字转换成long型数字字符串
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
	 * 将科学计数的数字转换成double型数字字符串
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

