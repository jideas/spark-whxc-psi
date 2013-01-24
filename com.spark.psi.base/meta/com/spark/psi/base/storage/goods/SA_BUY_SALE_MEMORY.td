<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_BUY_SALE_MEMORY" ID="28B02214E96B1DA37D2EB0D291B87F53" name-db="SA_BUY_SALE_MEMORY" category="" title="采购/销售情况表（中间表） " author="" description="存放针对某一商品的采购和销售情况" packagename="com.spark.psi.base.storage.goods" name="SA_BUY_SALE_MEMORY" isneedrebuild="false">
  <fields>
    <field dbtable="SA_BUY_SALE_MEMORY" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="80857568DFE7CB3DF9DA42A62FED89FE" />
    <field dbtable="SA_BUY_SALE_MEMORY" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="供应商/客户GUID" description="" ID="1A7FD7C935EE07D40307DD424FA6A6BC" />
    <field dbtable="SA_BUY_SALE_MEMORY" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="69ADA9D3754A0537BA1995A9E5DA6716" />
    <field dbtable="SA_BUY_SALE_MEMORY" name="goodsPropertyGuid" name-db="goodsPropertyGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品属性GUID" description="" ID="23974487887D4A48D58975621E60AA2A" />
    <field dbtable="SA_BUY_SALE_MEMORY" name="buySaleTime" name-db="buySaleTime" type="int" primary-key="false" keep-valid="false" author="" title="采购/销售次数" description="" ID="7BC0ABF864F8F6949D58E65B566C802C">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_BUY_SALE_MEMORY" name="buySaleCount" name-db="buySaleCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="累计采购/销售数量" description="" ID="89E1FDCE254CC3469DF3DD7CC142E952">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="SA_BUY_SALE_MEMORY" name="recentBuySalePrice" name-db="recentBuySalePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="最近采购/销售价格" description="" ID="26029AD5ACC0CC2EDBEC7FF9842317AF">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_BUY_SALE_MEMORY" name="recentBuySaleCount" name-db="recentBuySaleCount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="最近采购/销售数量" description="" ID="0B2526D29E35EC718F36AE3AE8DC8791">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_BUY_SALE_MEMORY" name="recentBuySaleDate" name-db="recentBuySaleDate" type="date" primary-key="false" keep-valid="false" author="" title="最近采购/销售日期" description="" ID="67B3FA7E333349CCFBA66F06A5F04EBA" />
    <field dbtable="SA_BUY_SALE_MEMORY" name="cusproType" name-db="cusproType" type="char(2)" primary-key="false" keep-valid="false" author="" title="供应商/客户类型标识" description="枚举（01供应商、02客户）" ID="5E6A83D8B1F649556E99FD1516CFB1D8" />
  </fields>
  <indexs>
    <index author="" name="SA_BUY_SALE_MEMORY_ONE" name-db="SA_BUY_SALE_MEMORY_ONE" title="SA_BUY_SALE_MEMORY_ONE" unique="false" description="">
      <index-item fieldID="80857568DFE7CB3DF9DA42A62FED89FE" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="5E6A83D8B1F649556E99FD1516CFB1D8" field="cusproType" field-author="" desc="false" />
    </index>
    <index author="" name="SA_BUY_SALE_MEMORY_TWO" name-db="SA_BUY_SALE_MEMORY_TWO" title="SA_BUY_SALE_MEMORY_TWO" unique="false" description="">
      <index-item fieldID="80857568DFE7CB3DF9DA42A62FED89FE" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="23974487887D4A48D58975621E60AA2A" field="goodsPropertyGuid" field-author="" desc="false" />
      <index-item fieldID="5E6A83D8B1F649556E99FD1516CFB1D8" field="cusproType" field-author="" desc="false" />
      <index-item fieldID="1A7FD7C935EE07D40307DD424FA6A6BC" field="cusproGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_BUY_SALE_MEMORY" name-db="SA_BUY_SALE_MEMORY" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

