<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SAAS_REPORT_QUEUE" ID="5A4E0BEEECA5D7C7721E34222AE32313" name-db="SA_SAAS_REPORT_QUEUE" category="" title="报表事件处理队列" author="" description="" packagename="com.spark.psi.report.storage.queue" name="SA_SAAS_REPORT_QUEUE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SAAS_REPORT_QUEUE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="B9FE3848E70990C7E1A331A97252DBCE" />
    <field dbtable="SA_SAAS_REPORT_QUEUE" name="eventClass" name-db="eventClass" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="事件类型" description="" ID="00C3C1466EA3FC685809F1C4EA359B4E" />
    <field dbtable="SA_SAAS_REPORT_QUEUE" name="attributeXml" name-db="attributeXml" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="参数xml" description="" ID="C5D44DA684739FCB82B620FAA17DBF33" />
    <field dbtable="SA_SAAS_REPORT_QUEUE" name="currTime" name-db="currTime" type="long" primary-key="false" keep-valid="false" author="" title="发生时间点" description="" ID="75831D64847B8485BA3F2A3CB8FCEA21" />
    <field dbtable="SA_SAAS_REPORT_QUEUE" name="userId" name-db="userId" type="guid" primary-key="false" keep-valid="false" author="" title="触发事件用户id" description="" ID="CD9DC80A07EAC82CFD2CC024FA5099D0" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SAAS_REPORT_QUEUE" name-db="SA_SAAS_REPORT_QUEUE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

