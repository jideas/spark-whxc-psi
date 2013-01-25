/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Department;

/**
 *
 */
public class ReportUtils{
	
	public static Condition findCondition(String columnName, Condition[] cons){
		if(null == cons){
			return null;
		}
		for(Condition con : cons){
			if(columnName.toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				return con;
			}
		}
		return null;
	}
	
	public static Condition findCondition(String columnName, List<Condition> cons){
		if(null == cons){
			return null;
		}
		for(Condition con : cons){
			if(columnName.toUpperCase().equals(con.getConditionColumn().toUpperCase())){
				return con;
			}
		}
		return null;
	}
	/**
	 * ���ݲ���id��øò��ŵ������Ӳ���id�����������ţ�
	 * 
	 * @param context
	 * @param deptId
	 * @return
	 */
	public static List<GUID> getChildrenDeptList(Context context, GUID deptId){
		Department dept = context.find(Department.class, deptId);
		List<GUID> list = new ArrayList<GUID>();
//		list.add(dept.getId());
		for(Department d : dept.getDescendants(context)){
			list.add(d.getId());
		}
		return list;
	}

	/**
	 * ����ͳ��ͼ����������̶�
	 * 
	 */
	public static double computeMaxAxis(double max){
		if(max <= 0){
			return 100.00;
		}
		else if(max > 0){
			int index = (int)((max / 100) + 1);
			return 100 * index;
		}
		return 0.00;
	}

	/**
	 * ���㻷��
	 * 
	 * @param benqi ����
	 * @param shangqi ����
	 * @return double
	 */
	public static double calcHuanBi(double benqi, double shangqi){
		if(benqi == 0 || shangqi == 0) return 0;
		double f = DoubleUtil.div(benqi, shangqi,4);
		f = f - 1;
		f = f * 100;
		return f;
	}
}
