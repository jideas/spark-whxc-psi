<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Deliver_Det" ID="E9CBDF36071DDD40496D313567115D75" name-db="PSI_Deliver_Det" category="" title="配送单明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Deliver_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Deliver_Det" name="deliverSheetId" name-db="deliverSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="配送单ID" description="" ID="7045CEE3DA74AB6B6BAB23D051F8D1B6" />
    <field dbtable="PSI_Deliver_Det" name="onlineOrderId" name-db="onlineOrderId" type="guid" primary-key="false" keep-valid="false" author="" title="网上订单Id" description="" ID="64D23536558C7D583DB67456602C8433" />
    <field dbtable="PSI_Deliver_Det" name="onlineOrderNo" name-db="onlineOrderNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="订单编号" description="" ID="0B73228704ED3825A5DB6AB10C9DBB4B" />
    <field dbtable="PSI_Deliver_Det" name="memberRealName" name-db="memberRealName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="会员名称" description="" ID="7938B4837B5266D8CA214B988730AFA1" />
    <field dbtable="PSI_Deliver_Det" name="orderAmount" name-db="orderAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="F426223DD8A6BFF5E97F11EAF44FD3AD" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Deliver_Det" name-db="PSI_Deliver_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

