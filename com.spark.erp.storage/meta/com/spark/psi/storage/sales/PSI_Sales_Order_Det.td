<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Sales_Order_Det" ID="59EEB73DC77E61AFB668774BCA8C77DA" name-db="PSI_Sales_Order_Det" category="" title="销售订单明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Sales_Order_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Sales_Order_Det" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="销售订单GUID" description="" ID="6C13CD3F1FA57B1D057518A3CD766DA6" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品ID" description="" ID="66A7C6F0B574D9DDE30776BDE4D3CFCF" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="69EB4BAE43ECEFC72530B1A259DFD70E" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="4CBFC4BFF5F3B7ACBBC666D6DE340EF9" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="5B0A2E6A36F0604A4AEA7A53D4E9B840" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="7BA849961103BB994560DC9AE883EEAC" />
    <field dbtable="PSI_Sales_Order_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="30E073F39106A16167BE4BD02D5C51D6" />
    <field dbtable="PSI_Sales_Order_Det" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="080E37D6FCB3E04D59E238C380C57FDD" />
    <field dbtable="PSI_Sales_Order_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="049CADA96A0FD0C348D3C9E356D76B21" />
    <field dbtable="PSI_Sales_Order_Det" name="planPrice" name-db="planPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="1F6532FBA5D79038092D44221B02E082" />
    <field dbtable="PSI_Sales_Order_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="013B180A5B73B69F0AD9787DB44368A6" />
    <field dbtable="PSI_Sales_Order_Det" name="disRate" name-db="disRate" type="numeric(6,0)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="F009A1AD070D31AC529983E6468DB5E7" />
    <field dbtable="PSI_Sales_Order_Det" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣额" description="" ID="9C9F6580D63FDBFB708636793C5DB7DC" />
    <field dbtable="PSI_Sales_Order_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="FE83993396349BC08ABFDE39B8D1DD71" />
    <field dbtable="PSI_Sales_Order_Det" name="promotionId" name-db="promotionId" type="guid" primary-key="false" keep-valid="false" author="" title="促销商品Guid" description="" ID="D7A76412BC0E3C7FF7393CC84158AEC3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Sales_Order_Det" name-db="PSI_Sales_Order_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

