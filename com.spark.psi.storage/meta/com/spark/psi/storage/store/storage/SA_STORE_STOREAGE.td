<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_STOREAGE" ID="911CD91CD1127AF12C2B913B2678F9F9" name-db="SA_STORE_STOREAGE" category="" title="" author="" description="" packagename="com.spark.psi.storage.store.storage" name="SA_STORE_STOREAGE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_STOREAGE" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="A869CC83A6482604BA6A0BEE78B9B89D" />
    <field dbtable="SA_STORE_STOREAGE" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="5927000447DC4EBFB0B115A531136064" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="66145586734761BBF92B5887707A2C81" />
    <field dbtable="SA_STORE_STOREAGE" name="initCount" name-db="initCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="初始化数量" description="" ID="55F4A6985852A45117170BA1C7B7C5CF" />
    <field dbtable="SA_STORE_STOREAGE" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化金额" description="" ID="8B0E25E5BF6CD00F3D14C0E18A0CBF9E" />
    <field dbtable="SA_STORE_STOREAGE" name="initUnitCost" name-db="initUnitCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化商品库存平均成本" description="" ID="8155AD3FF03DC9B16DDEA4BA25C7926A" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="库存商品（物品）名称" description="" ID="11AAC478B63D2D4496642F8A7D486E3B" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsNo" name-db="goodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="库存商品（物品）编号" description="" ID="E49BBF3B0FA59D39C64D664796855EA9" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsProperty" name-db="goodsProperty" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="" description="" ID="E552469A77F14844981F4BAD909A0CAC" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsUnit" name-db="goodsUnit" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="" description="" ID="082229386D94EAAE328CEEE94625E424" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsCount" name-db="goodsCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="商品库存数量" description="" ID="5A8A2401DD18A5866465FFFD48C2810F" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsUnitCost" name-db="goodsUnitCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品库存单位成本" description="" ID="741585135F8BCA26CB6AF0606EA592EB" />
    <field dbtable="SA_STORE_STOREAGE" name="goodsStoreAmount" name-db="goodsStoreAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品库存金额" description="" ID="AF6FA18FF1C40AB95945D8C7659AC556" />
    <field dbtable="SA_STORE_STOREAGE" name="onWayCount" name-db="onWayCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购在途数量" description="" ID="B8A16CBFF717562472A9BB1236DDBE1A" />
    <field dbtable="SA_STORE_STOREAGE" name="toDeliverCount" name-db="toDeliverCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="发货需求" description="" ID="0C291623C067FC8AC2E0490253268850" />
    <field dbtable="SA_STORE_STOREAGE" name="storeUpper" name-db="storeUpper" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="商品库存上限" description="" ID="EDD433D597C66BC16E6D836C7AD17C9D" />
    <field dbtable="SA_STORE_STOREAGE" name="storeFloor" name-db="storeFloor" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="商品库存下限" description="" ID="B545B05E2A8706D437EC0FAE3A9BA50C" />
    <field dbtable="SA_STORE_STOREAGE" name="storeAmountUpper" name-db="storeAmountUpper" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存金额上限" description="" ID="13B2D7D294A3D561F16B34B385E32030" />
    <field dbtable="SA_STORE_STOREAGE" name="storeType" name-db="storeType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存类型" description="" ID="AF065AD1F2E6A3B95346397AC440D6C4" />
    <field dbtable="SA_STORE_STOREAGE" name="isInit" name-db="isInit" type="boolean" primary-key="false" keep-valid="false" author="" title="是否初始化" description="" ID="A5D8113189D23EB6CABDD5D7F3CD9789" />
    <field dbtable="SA_STORE_STOREAGE" name="lockedCount" name-db="lockedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="锁定数量" description="" ID="481567D320409E94C80DFA79193E7C3E" />
  </fields>
  <indexs>
    <index author="" name="index_1" name-db="index_1" title="index_1" unique="false" description="">
      <index-item fieldID="A869CC83A6482604BA6A0BEE78B9B89D" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="5927000447DC4EBFB0B115A531136064" field="storeGuid" field-author="" desc="false" />
      <index-item fieldID="66145586734761BBF92B5887707A2C81" field="goodsGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_STOREAGE" name-db="SA_STORE_STOREAGE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

