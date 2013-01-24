<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_TYPE_TONGJI" ID="5FD507D31C1E88295708396F8CAE3587" name-db="SA_GOODS_TYPE_TONGJI" category="" title="商品分类统计表(中间表) " author="" description="存放商品分类的统计信息" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_TYPE_TONGJI" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="6008FE79CFED812DD05D69E32D00D0B8" />
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="1FF1E6F26E3569E7B81C4BDD6F8BB517" />
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="parentGuid" name-db="parentGuid" type="guid" primary-key="false" keep-valid="false" author="" title="父分类GUID" description="" ID="63306E8C80A3014C2D62BC19668AC2FC" />
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="storeAmountUpper" name-db="storeAmountUpper" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="分类金额上限" description="" ID="756A1F86EE3E92CC29C992EF673E0121">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="recentStoreAmount" name-db="recentStoreAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="分类库存当前金额" description="" ID="7ABEE43D56AE11DA3576A3ED0E6A74BC">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="saleGoodsCount" name-db="saleGoodsCount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="该分类下在售商品数量" description="" ID="6A433EE2BE28BFBC8B760B5D194E8682">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE_TONGJI" name="stopGoodsCount" name-db="stopGoodsCount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="该分类下停售商品数量" description="" ID="4E3EE582E3D0C3BCFDE6BCF5302B82A4">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_TYPE_TONGJI1" name-db="SA_GOODS_TYPE_TONGJI1" title="SA_GOODS_TYPE_TONGJI1" unique="true" description="">
      <index-item fieldID="1FF1E6F26E3569E7B81C4BDD6F8BB517" field="goodsTypeGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_TYPE_TONGJI" name-db="SA_GOODS_TYPE_TONGJI" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

