<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Purchase_Det" ID="AA8E868A33F91854DECF563E4EC4EA41" name-db="PSI_Purchase_Det" category="" title="采购明细" author="" description="" packagename="com.spark.psi.storage.purchase" name="PSI_Purchase_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Purchase_Det" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="采购订单单据编号" description="" ID="047EC87CDC49581DFEEF09658B5BA526" />
    <field dbtable="PSI_Purchase_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="42321435147045731F3DCBAD7ECCD3F8" />
    <field dbtable="PSI_Purchase_Det" name="goodsCode" name-db="goodsCode" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="65C1AB66DA456793CA4D65D448357D2C" />
    <field dbtable="PSI_Purchase_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="B2FD1FC1808078E87DE63972D5705C85" />
    <field dbtable="PSI_Purchase_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="A23B3EBEBF5FD2215CB5AA3CDA07F298" />
    <field dbtable="PSI_Purchase_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="06839191BAF8CA023B19C650E09009AF" />
    <field dbtable="PSI_Purchase_Det" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="DC87D43C3C169F767EC2D689AC97AE17" />
    <field dbtable="PSI_Purchase_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="13357EDA4A72A3865FF4E2992A24D234" />
    <field dbtable="PSI_Purchase_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="C999C49C3E338E3536341FB59E4D7AAB" />
    <field dbtable="PSI_Purchase_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="C022D919F9C9F6E3B50921522815F440" />
    <field dbtable="PSI_Purchase_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="E31982B56AE9571B178D9BB1E8FDAD7B" />
    <field dbtable="PSI_Purchase_Det" name="reason" name-db="reason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="采购原因" description="" ID="D2C56C0E1900DA8882DFD65694E3E179" />
    <field dbtable="PSI_Purchase_Det" name="creator" name-db="creator" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="34239991B674C03D98DDF774665195E0" />
    <field dbtable="PSI_Purchase_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="984EDCDD6CDB33395605F6D38463BC66" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Purchase_Det" name-db="PSI_Purchase_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

