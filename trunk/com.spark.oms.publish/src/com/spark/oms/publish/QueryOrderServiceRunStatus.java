package com.spark.oms.publish;

public enum QueryOrderServiceRunStatus {

	Warning("7", "运行预警"), Wait("4", "等待执行"), Enable("3", "等待启用"), Protected(
			"6", "保号运行"), Run("2", "正常运行"), All("0", "全部");

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
