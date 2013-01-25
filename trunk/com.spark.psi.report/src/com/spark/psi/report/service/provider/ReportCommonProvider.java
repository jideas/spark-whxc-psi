package com.spark.psi.report.service.provider;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.report.constant.SubjectEnum;
import com.spark.psi.report.entity.ReportChartDatas;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.ReportTargetUtil;

public class ReportCommonProvider extends Service{

	protected ReportCommonProvider(){
		super("ReportCommonProvider");
	}

	/**
	 * ͨ�ò�ѯ�������ݽ���ṩ��
	 */
	@Publish
	protected class ReportDataCommonProvider extends OneKeyResultListProvider<ReportResult, ReportCommonKey>{
		@Override
		protected void provide(Context context, ReportCommonKey key, List<ReportResult> list) throws Throwable{
			SubjectEnum subject = key.getSubject();
			if(null == subject){
				return;
			}
			if(CheckIsNull.isEmpty(key.getTargets())){
				return;
			}
			switch(subject){
				case GoodsItem:
					GoodsSubjectProvider.provide(context, key, list);
					break;
				case DateTime:
					DateTimeProvider.provide(context, key, list);
					break;
				/**
				 * �ͻ�
				 */
				case Customer:
					CustomerSubjectProvider.provide(context, key, list);
					break;
				/**
				 * �ͻ���������Ա
				 */
				case Customer_Employee:
					Customer_EmployeeSubjectProvider.provide(context, key, list);
					break;
				/**
				 * ��Ӧ��
				 */
				case Supplier:
					SupplierSubjectProvider.provide(context, key, list);
					break;
				/**
				 * Ա��
				 */
				case Employee:
					EmployeeSubjectProvider.provide(context, key, list);
					break;
				/**
				 * ����
				 */
				case Department:
					DepartmentSubjectProvider.provide(context, key, list);
					break;
				/**
				 * ����
				 */
				case Area:
					AreaSubjectProvider.provide(context, key, list);
					break;
				/**
				 * ���̨��
				 */
				case InventoryBook:
					InventorySubjectProvider.provide(context, key, list);
					break;
				/**
				 * ��˾
				 */
				case Company:
					TenantSubjectProvider.provide(context, key, list);
					break;
			}
		}
	}

	@Publish
	protected class ChartDatasProvider extends OneKeyResultProvider<ReportChartDatas, ReportCommonKey>{

		@SuppressWarnings("unchecked")
		@Override
		protected ReportChartDatas provide(Context context, ReportCommonKey key) throws Throwable{
			List<ReportResult> list = context.getList(ReportResult.class, key);
			if(CheckIsNull.isEmpty(key.getOrderTarget())){
				return null;
			}
			Enum target = null;
			SubjectEnum subject = null;
			if(key.isDateColumn()){
				subject = SubjectEnum.DateTime;
				target = ReportTargetUtil.getTarget(subject, key.getOrderTarget());
			}
			else{
				subject = key.getSubject();
				target = ReportTargetUtil.getTarget(subject, "Id");
			}
			ReportChartDatas datas = new ReportChartDatas(list, target);
			return datas;
		}

	}

}
