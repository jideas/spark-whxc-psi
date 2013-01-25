package com.spark.psi.publish.phonemessage.entity;

import com.jiuqi.dna.core.type.GUID;

public interface PhoneMessageConfig {
	public GUID getId();

	public boolean isActiving();

	public String getBalanceUrl();

	public String getSubmitUrl();

	public String getUserNameKey();

	public String getPasswordKey();

	public String getPhoneNumberKey();

	public String getMsgContentKey();

	public String getUserName();

	public String getPassword();

	public String getSecretkey();

	public String getCompAccountKey();

	public String getCompAccount();
}
