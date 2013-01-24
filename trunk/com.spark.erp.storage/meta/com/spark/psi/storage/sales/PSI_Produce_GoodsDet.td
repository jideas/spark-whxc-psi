<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Produce_GoodsDet" ID="FD9C9AB47CB071F6D05D58C69DC61912" name-db="PSI_Produce_GoodsDet" category="" title="生产订单商品明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Produce_GoodsDet" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Produce_GoodsDet" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="EC82D07491F547BB0548C5F472D19478" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="BDBD9E5C25A6E06527C6D3EFFE51ABC2" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="788C0BD54B93D0C96781E5CAF8405E7E" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="994C0F341754049BCE0847F51F2C043F" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="9D0DD7BBE7D57D650719E0BD8D35E20A" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="C1C39E571BF75221F2A4F85195C72AAA" />
    <field dbtable="PSI_Produce_GoodsDet" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="018D35F159028FA859531DAF441B028E" />
    <field dbtable="PSI_Produce_GoodsDet" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="D766F7AB3C08C15D9D2D47592C0ABFC3" />
    <field dbtable="PSI_Produce_GoodsDet" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="D85624DAAE4A04412E994A4B8E3D0BFD" />
    <field dbtable="PSI_Produce_GoodsDet" name="bomId" name-db="bomId" type="guid" primary-key="false" keep-valid="false" author="" title="bom表Id" description="" ID="5C0FB77169FE6E44CAA679721F70BF38" />
    <field dbtable="PSI_Produce_GoodsDet" name="finishedCount" name-db="finishedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="已完成数量" description="" ID="662EDDE12F3B90A546E0051C0CB25301">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Produce_GoodsDet" name-db="PSI_Produce_GoodsDet" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

