/**============================================================
  * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.service.sale
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-12       汤成国        
 * ============================================================*/

package com.spark.psi.report.service.screen;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.report.constant.OldReportEnums.FenXiType;
import com.spark.psi.report.constant.OldReportEnums.RreportConstant;
import com.spark.psi.report.constant.OldReportEnums.ScreenType;
import com.spark.psi.report.entity.ReportScreen;
import com.spark.psi.report.key.GetScereenDataKey;
import com.spark.psi.report.key.GetScreenDataByDateAndTenantsGuidKey;
import com.spark.psi.report.task.ReportScreenTask;

/**
 * <p>滚动屏服务类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-12-12
 */

public class ReportScreenService extends Service{

	protected ReportScreenService(){
	    super("ReportScreenService");
    }
	/**
	 * <p>增加滚动屏数据，此数据位实时数据，只有插入操作</p>
	 * 
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-9
	 */
	@Publish
	protected class AddScreenData extends SimpleTaskMethodHandler<ReportScreenTask> {
		@Override
        protected void handle(Context context, ReportScreenTask task)
                throws Throwable
        {
			ReportScreen screen = task.getReportScreen();
			
			StringBuffer sql = new StringBuffer("define insert addScreenData(@RECID guid,@tenantId guid," +
					"@orderType string,@orderAmount double,@orderPerson guid,@orderDate date,@deptGuid guid,@viewOrder int)");
			sql.append(" begin");
			sql.append(" insert into SA_REPORT_SCREEN");
			sql.append("(RECID,tenantId,orderType,orderAmount,orderPerson,orderDate,deptGuid,viewOrder)");
			sql.append(" values");
			sql.append("(@RECID,@tenantId,@orderType,@orderAmount,@orderPerson,@orderDate,@deptGuid,@viewOrder)");
			sql.append(" end");
			
			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, context.newRECID());
			dbc.setArgumentValue(1, screen.getTenantsGuid());
			dbc.setArgumentValue(2, screen.getOrderType().getCode());
			dbc.setArgumentValue(3, screen.getOrderAmount());
			dbc.setArgumentValue(4, screen.getOrderPerson());
			dbc.setArgumentValue(5, task.getDateTime());
			dbc.setArgumentValue(6, screen.getDeptGuid());
			
			GetScreenDataByDateAndTenantsGuidKey key =
		        new GetScreenDataByDateAndTenantsGuidKey(screen
		                .getTenantsGuid(), new Date().getTime());
			ReportScreen reportScreen = context.find(ReportScreen.class, key);
		
			if(null == reportScreen) {
				dbc.setArgumentValue(7, 1);
			} else {
				dbc.setArgumentValue(7, reportScreen.getViewOrder() + 1);
			}
			
			dbc.executeUpdate();
        }
	} 
	
	@Publish
	protected class GetScreenDataByDateAndTenantsGuid extends OneKeyResultProvider<ReportScreen, GetScreenDataByDateAndTenantsGuidKey> {

		@Override
        protected ReportScreen provide(Context context,
                GetScreenDataByDateAndTenantsGuidKey key) throws Throwable
        {
			StringBuffer sql1 = new StringBuffer("define query getScreenData(@tenantId guid, @orderDate date)");
			sql1.append(" begin");
			sql1.append(" select a.RECID as recid,a.tenantId as tenantId,");
			sql1.append(" a.orderType as orderType,a.orderAmount as orderAmount,");
			sql1.append(" max(a.viewOrder) as viewOrder,a.orderPerson as orderPerson,");
			sql1.append(" max(a.orderDate) as orderDate,a.deptGuid as deptGuid");
			sql1.append(" from SA_REPORT_SCREEN as a");
			sql1.append(" where 1=1");
			sql1.append(" and a.tenantId=@tenantId");
			sql1.append(" and truncday(a.orderDate)=truncday(@orderDate)");
//			sql1.append(" group by a.tenantId,truncday(a.orderDate)");
			sql1.append(" end");
			
			DBCommand dbc1 = context.prepareStatement(sql1);
			dbc1.setArgumentValue(0, key.getTenantsGuid());
			dbc1.setArgumentValue(1, key.getCurrentDate());
			
			RecordSet rs = dbc1.executeQueryLimit(0, 1);
			ReportScreen screen = null;
			while(rs.next()) {
				screen = new ReportScreen();
				screen.setRecid(rs.getFields().get(0).getGUID());
				screen.setTenantsGuid(rs.getFields().get(1).getGUID());
				screen.setOrderType(ScreenType.getScreenTypeByCode(rs.getFields().get(2).getString()));
				screen.setOrderAmount(rs.getFields().get(3).getDouble());
				screen.setViewOrder(rs.getFields().get(4).getInt());
				screen.setOrderPerson(rs.getFields().get(5).getGUID());
				screen.setOrderDate(rs.getFields().get(6).getDate());
				screen.setDeptGuid(rs.getFields().get(7).getGUID());
			}
	        return screen;
        }
		
	}
	
	/**
	 * <p>滚动屏显示数据，系统当天在流动屏中出现的单据按次序显示</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author tangchengguo
	 * @version 2011-11-9
	 */
	@Publish
	  protected class GetScereenData extends OneKeyResultListProvider<ReportScreen, GetScereenDataKey>{

		@Override
	    protected void provide(Context context, GetScereenDataKey key, List<ReportScreen> list)
	            throws Throwable
	    {
			if(FenXiType.GONG_SI == key.getFenXiType()) {
				// 公司
				StringBuffer sql = new StringBuffer("define query getScereenData(@tenantId guid, @orderDate date)");
				sql.append(" begin");
				sql.append(" select a.tenantId as tenantId,a.orderType as orderType,");
				sql.append(" a.orderAmount as orderAmount,a.orderPerson as orderPerson,");
				sql.append(" a.orderDate as orderDate,a.deptGuid as deptGuid, a.viewOrder as viewOrder");
				sql.append(" from SA_REPORT_SCREEN as a");
				sql.append(" where 1=1");
				sql.append(" and a.tenantId=@tenantId");
				sql.append(" and truncday(a.orderDate)=truncday(@orderDate)");
				sql.append(" order by a.orderDate desc");
				sql.append(" end");
				
				DBCommand dbc = context.prepareStatement(sql);
				dbc.setArgumentValue(0, key.getTenantsGuid());
				dbc.setArgumentValue(1, new Date().getTime());
				
				RecordSet rs = dbc.executeQuery();
				ReportScreen reportScreen = null;
				while(rs.next()) {
					reportScreen = new ReportScreen();
					reportScreen.setTenantsGuid(rs.getFields().get(0).getGUID());
					reportScreen.setOrderType(ScreenType.getScreenTypeByCode(rs.getFields().get(1).getString()));
					reportScreen.setOrderAmount(rs.getFields().get(2).getDouble());
					reportScreen.setOrderPerson(rs.getFields().get(3).getGUID());
					reportScreen.setOrderDate(rs.getFields().get(4).getDate());
					reportScreen.setDeptGuid(rs.getFields().get(5).getGUID());
					reportScreen.setViewOrder(rs.getFields().get(6).getInt());
					list.add(reportScreen);
				}
			} else if(FenXiType.BU_MEN == key.getFenXiType()) {
				// 部门
				StringBuffer sql = new StringBuffer("define query getScereenData(@tenantId guid, @orderDate date," +
						"@deptGuid guid)");
				sql.append(" begin");
				sql.append(" select a.tenantId as tenantId,a.orderType as orderType,");
				sql.append(" a.orderAmount as orderAmount,a.orderPerson as orderPerson,");
				sql.append(" a.orderDate as orderDate,a.deptGuid as deptGuid, a.viewOrder as viewOrder");
				sql.append(" from SA_REPORT_SCREEN as a");
				sql.append(RreportConstant.DEPT_ZISUN_JIEDAIN_AND_SELF);
				sql.append(" where 1=1");
				sql.append(" and a.tenantId=@tenantId");
				sql.append(" and truncday(a.orderDate)=truncday(@orderDate)");
				sql.append(" order by a.orderDate desc");
				sql.append(" end");
				
				DBCommand dbc = context.prepareStatement(sql);
				dbc.setArgumentValue(0, key.getTenantsGuid());
				dbc.setArgumentValue(1, new Date().getTime());
				dbc.setArgumentValue(2, key.getDeptGuid());
				
				RecordSet rs = dbc.executeQuery();
				ReportScreen reportScreen = null;
				while(rs.next()) {
					reportScreen = new ReportScreen();
					reportScreen.setTenantsGuid(rs.getFields().get(0).getGUID());
					reportScreen.setOrderType(ScreenType.getScreenTypeByCode(rs.getFields().get(1).getString()));
					reportScreen.setOrderAmount(rs.getFields().get(2).getDouble());
					reportScreen.setOrderPerson(rs.getFields().get(3).getGUID());
					reportScreen.setOrderDate(rs.getFields().get(4).getDate());
					reportScreen.setDeptGuid(rs.getFields().get(5).getGUID());
					reportScreen.setViewOrder(rs.getFields().get(6).getInt());
					list.add(reportScreen);
				}
			} else if(FenXiType.YUAN_GONG == key.getFenXiType()) {
				// 员工
				StringBuffer sql = new StringBuffer("define query getScereenData(@tenantId guid, @orderDate date," +
						"@deptGuid guid,@orderPerson guid)");
				sql.append(" begin");
				sql.append(" select a.tenantId as tenantId,a.orderType as orderType,");
				sql.append(" a.orderAmount as orderAmount,a.orderPerson as orderPerson,");
				sql.append(" a.orderDate as orderDate,a.deptGuid as deptGuid, a.viewOrder as viewOrder");
				sql.append(" from SA_REPORT_SCREEN as a");
				sql.append(" where 1=1");
				sql.append(" and a.tenantId=@tenantId");
				sql.append(" and truncday(a.orderDate)=truncday(@orderDate)");
				sql.append(" and a.deptGuid=@deptGuid");
				sql.append(" and a.orderPerson=@orderPerson");
				sql.append(" order by a.orderDate desc");
				sql.append(" end");
				
				DBCommand dbc = context.prepareStatement(sql);
				dbc.setArgumentValue(0, key.getTenantsGuid());
				dbc.setArgumentValue(1, new Date().getTime());
				dbc.setArgumentValue(2, key.getDeptGuid());
				dbc.setArgumentValue(3, key.getEmpGuid());
				
				RecordSet rs = dbc.executeQuery();
				ReportScreen reportScreen = null;
				while(rs.next()) {
					reportScreen = new ReportScreen();
					reportScreen.setTenantsGuid(rs.getFields().get(0).getGUID());
					reportScreen.setOrderType(ScreenType.getScreenTypeByCode(rs.getFields().get(1).getString()));
					reportScreen.setOrderAmount(rs.getFields().get(2).getDouble());
					reportScreen.setOrderPerson(rs.getFields().get(3).getGUID());
					reportScreen.setOrderDate(rs.getFields().get(4).getDate());
					reportScreen.setDeptGuid(rs.getFields().get(5).getGUID());
					reportScreen.setViewOrder(rs.getFields().get(6).getInt());
					list.add(reportScreen);
				}
			}
	    }
	  }
}
