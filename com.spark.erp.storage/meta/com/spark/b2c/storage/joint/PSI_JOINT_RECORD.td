<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_JOINT_RECORD" ID="300ECDE9B3080B40B6F4EB8A5B23557D" name-db="PSI_JOINT_RECORD" category="业务主体" title="联营商品销售记录" author="" description="" packagename="com.spark.b2c.storage.joint" name="PSI_JOINT_RECORD" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_JOINT_RECORD" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="6CA5ECA95FE82CB663A2D3E045131943" />
    <field dbtable="PSI_JOINT_RECORD" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="B509115608CB51D2029A3FDB23E5416C" />
    <field dbtable="PSI_JOINT_RECORD" name="supplierId" name-db="supplierId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="7031C7FE2FA68763F54B0184A6DDAD04" />
    <field dbtable="PSI_JOINT_RECORD" name="supplierName" name-db="supplierName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D14EE70187C9C88FC8F38AD18F75CFCF" />
    <field dbtable="PSI_JOINT_RECORD" name="shortName" name-db="shortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="7676F5C912829D90A0A24A02396D77B2" />
    <field dbtable="PSI_JOINT_RECORD" name="supplierNamePY" name-db="supplierNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="A0EB7F80E5CD3449E7786E16B266BCCC" />
    <field dbtable="PSI_JOINT_RECORD" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6FC0A5F38889766537BB24A21C653E4A" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="7703800C6F36201FF0BCE6747F28FA6A" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="4951668D88EF8621BDDE002D386AD95D" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="2CEE18BA45A342B7DFD321C27FD5C4C6" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsUnit" name-db="goodsUnit" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6063AF43FACE86AEC0F69FED20444DFB" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="F0F83871FCE5C77C5907D6788A127BA1" />
    <field dbtable="PSI_JOINT_RECORD" name="goodsPrice" name-db="goodsPrice" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="1D99959E67EC81D0F776B0377052CD08" />
    <field dbtable="PSI_JOINT_RECORD" name="salesCount" name-db="salesCount" type="numeric(15,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="25DE0CFEDED2763F98C63788E8BE73FD" />
    <field dbtable="PSI_JOINT_RECORD" name="amount" name-db="amount" type="numeric(15,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="292FEDE89A389EF22A079908FDA0000B" />
    <field dbtable="PSI_JOINT_RECORD" name="percentage" name-db="percentage" type="numeric(5,4)" primary-key="false" keep-valid="false" author="" title="" description="" ID="613CA6BA0698E335EF1C85D6B5EFF219" />
    <field dbtable="PSI_JOINT_RECORD" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="56773382BBB090642C17E802193AA61B" />
    <field dbtable="PSI_JOINT_RECORD" name="alreadySettlement" name-db="alreadySettlement" type="boolean" primary-key="false" keep-valid="false" author="" title="" description="" ID="9B872BFA043092212D56AC7FF78C0511">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_JOINT_RECORD" name-db="PSI_JOINT_RECORD" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

