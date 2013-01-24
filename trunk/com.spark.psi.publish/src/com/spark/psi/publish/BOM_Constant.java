package com.spark.psi.publish;

public abstract class BOM_Constant {

	/**
	 * ״̬
	 */
	public enum BOM_STATUS {
		Submit("01", "���ύ"), Approveing("02", "������"), Rejected("03", "���˻�"), InEffect("04", "δ����");

		private String code;
		private String title;

		private BOM_STATUS(String code, String title) {
			this.code = code;
			this.title = title;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public static BOM_STATUS getStatus(String code) {
			if ("01".equals(code)) {
				return Submit;
			} else if ("02".equals(code)) {
				return Approveing;
			} else if ("03".equals(code)) {
				return Rejected;
			} else if ("04".equals(code)) {
				return InEffect;
			}
			return null;
		}
	}
}
