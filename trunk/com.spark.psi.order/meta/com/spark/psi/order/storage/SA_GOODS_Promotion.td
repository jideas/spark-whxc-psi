<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_Promotion" ID="BCDBE48E706BFF9380B1181D62EA0538" name-db="SA_GOODS_Promotion" category="" title="商品促销管理2" author="" description="" packagename="com.spark.psi.order.storage" name="SA_GOODS_Promotion" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_Promotion" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="true" author="" title="租户GUID" description="" ID="12F2BA824AA1457695D6624E15BC32EE" />
    <field dbtable="SA_GOODS_Promotion" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="true" author="" title="创建人GUID" description="" ID="2AED4A772EB2B59CA4CC62EC071A245A" />
    <field dbtable="SA_GOODS_Promotion" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="0E363691BA2A9DD750FCC5DE2D8DF186" />
    <field dbtable="SA_GOODS_Promotion" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建日期" description="" ID="0365FC1C7590F6383868C74A79EC864F" />
    <field dbtable="SA_GOODS_Promotion" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="true" author="" title="商品GUID" description="" ID="C98C7C3416F6D448545A280929AC99E1" />
    <field dbtable="SA_GOODS_Promotion" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="34CB907AE71A448B04CA58FEADB498E8" />
    <field dbtable="SA_GOODS_Promotion" name="goodsNamePY" name-db="goodsNamePY" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="0769D108C757CDA66FE45FD6192CCB09" />
    <field dbtable="SA_GOODS_Promotion" name="goodsProperties" name-db="goodsProperties" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="39C04F39973DE0B6ED84691D0198AB30" />
    <field dbtable="SA_GOODS_Promotion" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="127C719D40DDD3533C005A3F215EA509" />
    <field dbtable="SA_GOODS_Promotion" name="promotionCount" name-db="promotionCount" type="numeric(17,5)" primary-key="false" keep-valid="true" author="" title="促销数量" description="" ID="24DC161F34B23553F9C86FE6BDFFCB17" />
    <field dbtable="SA_GOODS_Promotion" name="saledCount" name-db="saledCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="销售数量" description="" ID="20F202F18247DBABA3768511266F4052">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_Promotion" name="price_goods" name-db="price_goods" type="numeric(17,2)" primary-key="false" keep-valid="true" author="" title="原价" description="" ID="B73E49BDB656D7DB1EAA23B7747FB892" />
    <field dbtable="SA_GOODS_Promotion" name="price_promotion" name-db="price_promotion" type="numeric(17,2)" primary-key="false" keep-valid="true" author="" title="促销单价" description="" ID="C621E5F064D8B06D3DC38FF8D9EBDB6E" />
    <field dbtable="SA_GOODS_Promotion" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="true" author="" title="开始日期" description="" ID="074E426DB99F783E867A971024590E37" />
    <field dbtable="SA_GOODS_Promotion" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="true" author="" title="结束日期" description="" ID="3FA3F369151DF7227220603A883C91DF" />
    <field dbtable="SA_GOODS_Promotion" name="state" name-db="state" type="char(2)" primary-key="false" keep-valid="true" author="" title="状态" description="" ID="79081720A4C3DF5C9FBE1FFF961A3622" />
    <field dbtable="SA_GOODS_Promotion" name="promotionCause" name-db="promotionCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="促销原因" description="" ID="9660FCF8230E97B97F088EBB37915A8F" />
    <field dbtable="SA_GOODS_Promotion" name="returnCause" name-db="returnCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="73F87984D37A8F82EF1201F99858A633" />
    <field dbtable="SA_GOODS_Promotion" name="approvalDate" name-db="approvalDate" type="date" primary-key="false" keep-valid="false" author="" title="审核时间" description="" ID="854BDCB87FDAC2963E612A1DC066B26F" />
    <field dbtable="SA_GOODS_Promotion" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="true" author="" title="部门GUID" description="" ID="298EA4DC7F0BF03BB4D4B317E4358A65" />
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_PROMOTION_1" name-db="SA_GOODS_PROMOTION_1" title="SA_GOODS_PROMOTION_1" unique="false" description="">
      <index-item fieldID="12F2BA824AA1457695D6624E15BC32EE" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="79081720A4C3DF5C9FBE1FFF961A3622" field="state" field-author="" desc="false" />
      <index-item fieldID="298EA4DC7F0BF03BB4D4B317E4358A65" field="deptId" field-author="" desc="false" />
    </index>
    <index author="" name="SA_GOODS_PROMOTION_2" name-db="SA_GOODS_PROMOTION_2" title="SA_GOODS_PROMOTION_2" unique="false" description="">
      <index-item fieldID="12F2BA824AA1457695D6624E15BC32EE" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="C98C7C3416F6D448545A280929AC99E1" field="goodsItemId" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_Promotion" name-db="SA_GOODS_Promotion" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

