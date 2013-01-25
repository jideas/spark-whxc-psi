package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant.BillsWithout;
import com.spark.order.intf.type.BillsEnum;

public class SelectCuspMainKey extends SelectMainKey{
	private String type = BillsWithout.FINISH_NO;//�������ͣ�Ĭ��Ϊδ��ɵ��ݣ�
	private final GUID cusProGuid;

	public GUID getCusProGuid() {
		return cusProGuid;
	}


	public SelectCuspMainKey(BillsEnum billsEnum, String type, GUID cusProGuid) {
		super(billsEnum, null);
		if(null != type){
			this.type = type;
		}
		this.cusProGuid = cusProGuid;
		initSort();
	}
	
	@Override
	protected void initSort() {
		this.setSortField("t.createDate");
		if(BillsWithout.FINISH_NO.equals(type)){
			this.setSortType(SortEnum.Asc.getValue());
		}
		else{
			this.setSortType(SortEnum.Desc.getValue());
		}
	}
	
	/**
	 * �������� 
	 * @return String
	 */
	public String getType() {
		return type;
	}
}
