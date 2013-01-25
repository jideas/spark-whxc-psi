<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_CHECK_DET" ID="26F2BEF9CA8B9258C9BA0A8268E1996D" name-db="SA_STORE_CHECK_DET" category="" title="库存盘点明细" author="" description="库存盘点明细" packagename="com.spark.psi.storage.store.check" name="SA_STORE_CHECK_DET" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_CHECK_DET" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="BC6C37E44272A91E6F8C985038751DB9" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="B22490977C0C01759DEC6E5F0257B4AA" />
    <field dbtable="SA_STORE_CHECK_DET" name="remark" name-db="remark" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="说明" description="" ID="9F60C7CC68C8298171DDDD6F44FE5E39" />
    <field dbtable="SA_STORE_CHECK_DET" name="checkOrdGuid" name-db="checkOrdGuid" type="guid" primary-key="false" keep-valid="false" author="" title="盘点单编号" description="" ID="02A28255D9951222897E87A3049CE273" />
    <field dbtable="SA_STORE_CHECK_DET" name="carryCount" name-db="carryCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="账面数量" description="" ID="C403E815EB015735B76C26A7C40E24C2" />
    <field dbtable="SA_STORE_CHECK_DET" name="realCount" name-db="realCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="实盘数量" description="" ID="AC12F5A829A0497433BFDEC5E0D164D5" />
    <field dbtable="SA_STORE_CHECK_DET" name="createPerson" name-db="createPerson" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="43B6895E6F4195DC504806343E31690D" />
    <field dbtable="SA_STORE_CHECK_DET" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="4868AFEFA602833DFB6C284095A1EC7F" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsName" name-db="goodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="7D9C64E022E425728433800F20E77C16" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="7484691141C1D01921467B366E7822D4" />
    <field dbtable="SA_STORE_CHECK_DET" name="unit" name-db="unit" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="4CBCC4A9101B2E2B9D7FE2D8F4389762" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsItemNo" name-db="goodsItemNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="88F7A1891BB17393CDFA8C47D324D26A" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsItemCode" name-db="goodsItemCode" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="136418517E9262A7117C16B1BFF4CEF8" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="455CDC8C547A18C44CBC96EE8ED06725" />
    <field dbtable="SA_STORE_CHECK_DET" name="newGoods" name-db="newGoods" type="char(2)" primary-key="false" keep-valid="false" author="" title="是否为新增商品" description="" ID="2F4901FE949D8F3BDD1005DE1181DB33" />
    <field dbtable="SA_STORE_CHECK_DET" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="505A291F4D834CD1103C5220E2C77AAC" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_CHECK_DET" name-db="SA_STORE_CHECK_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

