package com.spark.oms.publish.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 创建短信通道
 * @author mengyongfeng
 * 
 *
 */
public class CreateSMSChannelTask extends SimpleTask {
	
	//id
	private GUID id;
	//名称
	private String name;
	//编码
	private String code;
	//计价方式
	private String chargeType;
	//价格
	private double price;
//	//支持移动
//	private String mobileAccess;
//	//支持联通
//	private String unicomAccess;
//	//支持电信
//	private String telecomAccess;
//	//支持网通
//	private String netcomAccess;
	//支持批量发送
	private String isBatchSend;
	//免费短信条数
	private String freeSMSNumber;
	//超出部分计价方式
	private double overtakePrice;
	//描述
	private String remark;
	//支持网络
	private String supportNetWork;
	
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 错误信息
	 */
	private String errors;
	/**
	 * 短信通道参数配置列表
	 */
	private ChannelConfig[] items;
	
	public final static class ChannelConfig{
		
		private GUID id;
		private String account;
		private String password;
		private int mobilePhoneMax;
		private int PHSMax;		
		private String connectType;
		private String serviceType;
		private String ip;
		private String port;
		private String longPort;
		private int massSMSMax;
		private int multipleSMSMax;
		private int priority;
		private GUID msgchannel;
		
		public ChannelConfig(){
			
		}
		
		public ChannelConfig(String account,String password){
			this.account = account;
			this.password = password;
		}

		public GUID getId() {
			return id;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getMobilePhoneMax() {
			return mobilePhoneMax;
		}

		public void setMobilePhoneMax(int mobilePhoneMax) {
			this.mobilePhoneMax = mobilePhoneMax;
		}

		public int getPHSMax() {
			return PHSMax;
		}

		public void setPHSMax(int pHSMax) {
			PHSMax = pHSMax;
		}

		public String getConnectType() {
			return connectType;
		}

		public void setConnectType(String connectType) {
			this.connectType = connectType;
		}

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getLongPort() {
			return longPort;
		}

		public void setLongPort(String longPort) {
			this.longPort = longPort;
		}

		public int getMassSMSMax() {
			return massSMSMax;
		}

		public void setMassSMSMax(int massSMSMax) {
			this.massSMSMax = massSMSMax;
		}

		public int getMultipleSMSMax() {
			return multipleSMSMax;
		}

		public void setMultipleSMSMax(int multipleSMSMax) {
			this.multipleSMSMax = multipleSMSMax;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public GUID getMsgchannel() {
			return msgchannel;
		}

		public void setMsgchannel(GUID msgchannel) {
			this.msgchannel = msgchannel;
		}		
	}
		
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public ChannelConfig[] getItems() {
		return items;
	}
	public void setItems(ChannelConfig[] items) {
		this.items = items;
	}
	public GUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getChargeType() {
		return chargeType;
	}
	public double getPrice() {
		return price;
	}
//	public String getMobileAccess() {
//		return mobileAccess;
//	}
//	public String getUnicomAccess() {
//		return unicomAccess;
//	}
//	public String getTelecomAccess() {
//		return telecomAccess;
//	}
//	public String getNetcomAccess() {
//		return netcomAccess;
//	}
	public String getIsBatchSend() {
		return isBatchSend;
	}
	public String getFreeSMSNumber() {
		return freeSMSNumber;
	}
	public Double getOvertakePrice() {
		return overtakePrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	public void setMobileAccess(String mobileAccess) {
//		this.mobileAccess = mobileAccess;
//	}
//	public void setUnicomAccess(String unicomAccess) {
//		this.unicomAccess = unicomAccess;
//	}
//	public void setTelecomAccess(String telecomAccess) {
//		this.telecomAccess = telecomAccess;
//	}
//	public void setNetcomAccess(String netcomAccess) {
//		this.netcomAccess = netcomAccess;
//	}
	public void setIsBatchSend(String isBatchSend) {
		this.isBatchSend = isBatchSend;
	}
	public void setFreeSMSNumber(String freeSMSNumber) {
		this.freeSMSNumber = freeSMSNumber;
	}
	public void setOvertakePrice(Double overtakePrice) {
		this.overtakePrice = overtakePrice;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupportNetWork() {
		return supportNetWork;
	}
	public void setSupportNetWork(String supportNetWork) {
		this.supportNetWork = supportNetWork;
	}
}