<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Sales_Return_Det" ID="66FF4208087405D684D13BB9ECBDA905" name-db="PSI_Sales_Return_Det" category="" title="销售退货明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Sales_Return_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Sales_Return_Det" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="退货单号" description="" ID="3B76776DE1F10C1C1F08E2A211C0DE88" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="2EFA5FFB9E09C1122F01EE2F77C13242" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="404A019E5432AC448B1DE910ECD87E06" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="9BA33F2EA650923CE2A409816232E905" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="D294019915C5E0DFB7A4343C62CBF351" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="22E9BEB8DAAB9F82EBAD8B123CB88C54" />
    <field dbtable="PSI_Sales_Return_Det" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="8F64EEEB9A37E8BBF208D0005E5FDEBA" />
    <field dbtable="PSI_Sales_Return_Det" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="08AACDA56F75E14C39FFBA5CDBD59C44" />
    <field dbtable="PSI_Sales_Return_Det" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="25A25B2413B8313C22C5567E06172F56" />
    <field dbtable="PSI_Sales_Return_Det" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="B232426BE210EA0E5C3F4DB88FE6C61F" />
    <field dbtable="PSI_Sales_Return_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="ABE458337BB457F76E8802D37A0454D9" />
    <field dbtable="PSI_Sales_Return_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="23FDB642D67F47DB6436C8D40DF28F8A" />
    <field dbtable="PSI_Sales_Return_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="CB51A5324F86565B7B481E56014F5AE3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Sales_Return_Det" name-db="PSI_Sales_Return_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

