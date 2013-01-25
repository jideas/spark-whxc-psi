<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALES_RETURN_ITEM" ID="9E1D111A0785E40610A36B5207A8102D" name-db="SA_SALES_RETURN_ITEM" category="" title="存放退货单明细信息" author="" description="" packagename="com.spark.psi.order.storage.sales" name="SA_SALES_RETURN_ITEM" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALES_RETURN_ITEM" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="AAC57E94FF74AC63FBAFE60AE6A0DD8E" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="1995F2FF73440FF027807342107F2BC2" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="E5435590F63375802C6346C668811BAF" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="52CCEF00E145E0180C45F84CD4C6D9CA" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="06ADF5F5B8C9B71090269CF5070DE60E" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="num" name-db="num" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="E1F3FDA53EF7196A244A9237F09396D5" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="D35911AF4433DA6EFE9BF884A4372782" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="orderId" name-db="orderId" type="guid" primary-key="false" keep-valid="false" author="" title="退货单号" description="" ID="12807F20988D3440FAD97BD4D82054CB" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="C06A0261156C062039A9A892EFCD1153" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="goodsCode" name-db="goodsCode" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="8B1A3035539C5EA6B88C8472E2B0FA93" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="4A49F2A685DE51FFD047ED3529B75702" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="goodsProperties" name-db="goodsProperties" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="10208BCB82D3C7FA2AD3B78C9D2D11BE" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="goodsUnit" name-db="goodsUnit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="C8E56099027F117B114183F0C0EBBCBE" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="9729B607E9BE40AD2C06FAB703995E3F" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="7C97D61DED4E63E5AA325CBD83D81775" />
    <field dbtable="SA_SALES_RETURN_ITEM" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="2D5F9964427EDB5014CC486BD4447E11" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALES_RETURN_ITEM" name-db="SA_SALES_RETURN_ITEM" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

