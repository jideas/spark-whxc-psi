<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_AUTHORIZE_GOODS" ID="B94129914FDD930C0F7C6287FF02F787" name-db="SA_AUTHORIZE_GOODS" category="基础数据主体" title="存放供应商授权的商品信息" author="" description="商品关联表" packagename="com.spark.psi.base.storage.goods" name="SA_AUTHORIZE_GOODS" isneedrebuild="false">
  <fields>
    <field dbtable="SA_AUTHORIZE_GOODS" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="本地租户GUID" description="" ID="2F84F3B0F6E503ECD5C65F82FBDBB717" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proTenantsGuid" name-db="proTenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="授权供应商租户GUID" description="" ID="2494258BEFDF5C7146D986B82E0989AC" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="providerGuid" name-db="providerGuid" type="guid" primary-key="false" keep-valid="false" author="" title="授权供应商GUID" description="" ID="67B7B67796259E1BA92290C62C57E0AE" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proGoodsPropertyGuid" name-db="proGoodsPropertyGuid" type="guid" primary-key="false" keep-valid="false" author="" title="授权商品属性GUID" description="" ID="9047B43FC59DE1766783BDC87CB1AAC8" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proGoodsProperty" name-db="proGoodsProperty" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="授权商品属性1，包括单位" description="" ID="8AD8735CCFB48C6F238B8D885EDAEAE0" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proGoodsName" name-db="proGoodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="授权商品名称" description="" ID="0C619997EF7D8AC6ADE3F4DB336BF7E9" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proSalePrice" name-db="proSalePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="授权商品价格" description="" ID="89CD5AA99DDF2334403E5F731C01B7CC">
      <default>
        <const type="numeric(17,2)" value="0.00" />
      </default>
    </field>
    <field dbtable="SA_AUTHORIZE_GOODS" name="priceA" name-db="priceA" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="预留价格，用于促销价格" description="" ID="8B0D0542A7928198E7941D726B7C5A85">
      <default>
        <const type="numeric(17,2)" value="0.00" />
      </default>
    </field>
    <field dbtable="SA_AUTHORIZE_GOODS" name="priceB" name-db="priceB" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="预留价格，用于其他" description="" ID="5989114D9BCCE2DFE7A190B71928C1EE">
      <default>
        <const type="numeric(17,2)" value="0.00" />
      </default>
    </field>
    <field dbtable="SA_AUTHORIZE_GOODS" name="proGoodsState" name-db="proGoodsState" type="char(2)" primary-key="false" keep-valid="false" author="" title="授权商品状态" description="" ID="3287AB96AF027CDE39B0470246B8ABAC">
      <default>
        <const type="char(2)" value="02" />
      </default>
    </field>
    <field dbtable="SA_AUTHORIZE_GOODS" name="goodsPropertyGuid" name-db="goodsPropertyGuid" type="guid" primary-key="false" keep-valid="false" author="" title="本地商品属性GUID" description="" ID="31ADBCB6C01C7D31DCC28D12BA4A2243" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="7F3A4D004B3460FA30FB70ACC736C947" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="87C91798AC7C4102CB46239FAC6E49F9" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="goodsCountDigit" name-db="goodsCountDigit" type="int" primary-key="false" keep-valid="false" author="" title="商品显示位数" description="" ID="3FB4FDEC5B3D0C91EC7483086BB18CED" />
    <field dbtable="SA_AUTHORIZE_GOODS" name="proGoodsNo" name-db="proGoodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="授权商品编号" description="" ID="2060BBC4A21B337D2CA9CAE60565669F" />
  </fields>
  <indexs>
    <index author="" name="INDEX_AUTHORIZE_GOODS" name-db="INDEX_AUTHORIZE_GOODS" title="INDEX_AUTHORIZE_GOODS" unique="false" description="">
      <index-item fieldID="2F84F3B0F6E503ECD5C65F82FBDBB717" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="67B7B67796259E1BA92290C62C57E0AE" field="providerGuid" field-author="" desc="false" />
      <index-item fieldID="31ADBCB6C01C7D31DCC28D12BA4A2243" field="goodsPropertyGuid" field-author="" desc="false" />
    </index>
    <index author="" name="SA_AUTHORIZE_GOODS_ONE" name-db="SA_AUTHORIZE_GOODS_ONE" title="SA_AUTHORIZE_GOODS_ONE" unique="false" description="">
      <index-item fieldID="2F84F3B0F6E503ECD5C65F82FBDBB717" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="3287AB96AF027CDE39B0470246B8ABAC" field="proGoodsState" field-author="" desc="false" />
      <index-item fieldID="67B7B67796259E1BA92290C62C57E0AE" field="providerGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_AUTHORIZE_GOODS" name-db="SA_AUTHORIZE_GOODS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

