<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="ERP_Sales_OnlineOrder_Log" ID="D8A847EDF609DD53F1813365D8C18518" name-db="ERP_Sales_OnlineOrder_Log" category="业务主体" title="网上订单跟踪信息表" author="" description="" packagename="com.spark.erp.storage.sales" name="ERP_Sales_OnlineOrder_Log" isneedrebuild="false">
  <fields>
    <field dbtable="ERP_Sales_OnlineOrder_Log" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="99EC22A13A840D19B9ADA336D20BBEB1" />
    <field dbtable="ERP_Sales_OnlineOrder_Log" name="processingTime" name-db="processingTime" type="date" primary-key="false" keep-valid="false" author="" title="处理时间" description="" ID="8D7CD903238C4BA3F5E09105BE7BC4C0" />
    <field dbtable="ERP_Sales_OnlineOrder_Log" name="message" name-db="message" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="处理信息" description="" ID="0494C0B117C15AA9EE1F52378AC4427D" />
    <field dbtable="ERP_Sales_OnlineOrder_Log" name="operator" name-db="operator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="操作人" description="" ID="131140C13BBEFDA1BD3B26CC3528D36C" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="ERP_Sales_OnlineOrder_Log" name-db="ERP_Sales_OnlineOrder_Log" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

