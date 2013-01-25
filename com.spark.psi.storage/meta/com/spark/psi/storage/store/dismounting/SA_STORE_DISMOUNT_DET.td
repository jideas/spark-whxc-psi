<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_DISMOUNT_DET" ID="734188BF99DDF2FFA911ADFE0A5E6BD9" name-db="SA_STORE_DISMOUNT_DET" category="" title="库存拆装明细" author="" description="库存拆装明细" packagename="com.spark.psi.storage.store.dismounting" name="SA_STORE_DISMOUNT_DET" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_DISMOUNT_DET" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="A041906B4E5B8A7D52AD567085219B97" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="dismFlag" name-db="dismFlag" type="char(2)" primary-key="false" keep-valid="false" author="" title="拆装前后标识" description="" ID="80A305BA19E1B33C6EED8FD614767BFA" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="dismGuid" name-db="dismGuid" type="guid" primary-key="false" keep-valid="false" author="" title="拆装单编号" description="" ID="DCAD98E6450F359CFC02714B777059B9" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="dismCount" name-db="dismCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="拆装数量" description="" ID="F739CECD387FD35CE95F03F2908A9BC9" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="storeCost" name-db="storeCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="拆装成本" description="" ID="9152136E4F51EB1CCC56860FB7FF30CE" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="B0BF5543EDE90ADC9CF70A014B8D187C" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="createPerson" name-db="createPerson" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="FD58B0AC7A059871CA56925B69817A62" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="4763CF77672E6DD023D45161DF774D2A" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="goodsName" name-db="goodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="3DA3E8587F6FAD89AEF73523F4F0BF09" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="419CE1BC5887DB9DA6DC5952A99C6EB0" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="unit" name-db="unit" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="6C401049D4FC1C0EFD6A2CC734CF29CF" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="goodsNo" name-db="goodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品号" description="" ID="FB35FAD774A4EB678A83D9C13942B845" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="money" name-db="money" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="368B61A379FB7AA6F95CE6E3B35379E5" />
    <field dbtable="SA_STORE_DISMOUNT_DET" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="FDD6A8EF698578FFE84FB55AE9847E23" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_DISMOUNT_DET" name-db="SA_STORE_DISMOUNT_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

