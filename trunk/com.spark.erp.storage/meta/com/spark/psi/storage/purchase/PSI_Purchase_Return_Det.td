<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Purchase_Return_Det" ID="2606693DC68EBEB93E26EA813C2D2749" name-db="PSI_Purchase_Return_Det" category="" title="采购退货明细" author="" description="" packagename="com.spark.psi.storage.purchase" name="PSI_Purchase_Return_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Purchase_Return_Det" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="退货单号" description="" ID="E1B431B53377D5678ABA3DAB61B1911B" />
    <field dbtable="PSI_Purchase_Return_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="F2AAAF7B12CE742A43A6759DB5BBF016" />
    <field dbtable="PSI_Purchase_Return_Det" name="goodsCode" name-db="goodsCode" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="1076BE8A4801F906829B922B9DB4D8FE" />
    <field dbtable="PSI_Purchase_Return_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="1112B234F336D57FB1DC133C7B1C0907" />
    <field dbtable="PSI_Purchase_Return_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="8296BFBDCE9353793E0CFA6D06AC3E21" />
    <field dbtable="PSI_Purchase_Return_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="8ECBEF9DF924164F39A0C2DCAE1F4AB5" />
    <field dbtable="PSI_Purchase_Return_Det" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="32D8211FA785A90CC7263767BC93BF23" />
    <field dbtable="PSI_Purchase_Return_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="A2509CE53E9567C204605114D6E31A7C" />
    <field dbtable="PSI_Purchase_Return_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="BE260F696C87A19B55373D851248D230" />
    <field dbtable="PSI_Purchase_Return_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="D467585314A408EC5225F9A0AF9AB6F6" />
    <field dbtable="PSI_Purchase_Return_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="3645A3B70D4C867F8600B9085FC09236" />
    <field dbtable="PSI_Purchase_Return_Det" name="creator" name-db="creator" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="C3B1814AAEEB0B0D74C0B39252EB7E79" />
    <field dbtable="PSI_Purchase_Return_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="8C314129390174E626B4869CCB257343" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Purchase_Return_Det" name-db="PSI_Purchase_Return_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

