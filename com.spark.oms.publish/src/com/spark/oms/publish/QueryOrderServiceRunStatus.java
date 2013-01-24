package com.spark.oms.publish;

public enum QueryOrderServiceRunStatus {

	Warning("7", "����Ԥ��"), Wait("4", "�ȴ�ִ��"), Enable("3", "�ȴ�����"), Protected(
			"6", "��������"), Run("2", "��������"), All("0", "ȫ��");

	private String code;

	private String name;

	private QueryOrderServiceRunStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static QueryOrderServiceRunStatus getQueryOrderServiceRunStatusByCode(
			String code) {
		for (QueryOrderServiceRunStatus item : QueryOrderServiceRunStatus
				.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}

		return null;
	}
}
