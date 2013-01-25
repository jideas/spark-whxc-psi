package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.type.GUID;

public class ItemOfSupplier {
		private GUID id;
		private String bankName, accountHolder, account, remark;

		public GUID getId() {
			return id;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getAccountHolder() {
			return accountHolder;
		}

		public void setAccountHolder(String accountHolder) {
			this.accountHolder = accountHolder;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

	}