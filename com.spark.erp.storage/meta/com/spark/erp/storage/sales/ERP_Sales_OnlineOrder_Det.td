<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="ERP_Sales_OnlineOrder_Det" ID="1B9AB98B3B4FC4D677584E02BE0FD894" name-db="ERP_Sales_OnlineOrder_Det" category="" title="网上订单明细" author="" description="" packagename="com.spark.erp.storage.sales" name="ERP_Sales_OnlineOrder_Det" isneedrebuild="false">
  <fields>
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="8D378D0CB049ACDF1B36A0133E34E04B" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品ID" description="" ID="E5F89B6418FA914F9A2A2D2A259C75B6" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="22911D989E87D2FE881D3AD212541EAA" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="5EF74E651B9C5EB51C98590116C1D393" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="FF33F9F0D710981900F261731D710A08" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="CC2FB5ADA3305135253CF26C3274E961" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="2C825F0CCE68D0FDD39BDB803C990B62" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="A1FA9AAD57CBD11A7DB1C9ACB6F709B4" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="A428C70798E281A81C3D8B3DBCB524CB" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="706DD64544BE1281D9DFCB2332204DF8" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="discount" name-db="discount" type="numeric(6,5)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="6FE45C1B5E12EBD428AD5D82A51C8D99" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣额" description="" ID="B3A9E3252EF7230B33740D24B39B120A" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="96E4416619B2719B143CB4B53233D3C5" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="promotionId" name-db="promotionId" type="guid" primary-key="false" keep-valid="false" author="" title="促销商品Guid" description="" ID="FDED39D3C20729A46BB2CCDD10F31DB1" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="vantages" name-db="vantages" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="送积分" description="" ID="84BBE12DAD0AE39C11C54B161F292574">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="vantagesType" name-db="vantagesType" type="char(1)" primary-key="false" keep-valid="false" author="" title="积分类型（0,1,2倍积分）" description="" ID="031B3D020DABDAC3943AC8A454EBFA43" />
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="vantagesCost" name-db="vantagesCost" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="消耗积分" description="" ID="9ABDB19605B502265C4EEC5D388AD90C">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder_Det" name="vantagesGoods" name-db="vantagesGoods" type="boolean" primary-key="false" keep-valid="false" author="" title="是否积分商城商品" description="" ID="FDA1DCF30F442569BB01E80A7FE6D276">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="ERP_Sales_OnlineOrder_Det" name-db="ERP_Sales_OnlineOrder_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

