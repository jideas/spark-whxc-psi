<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_GOODS_PROMOTION" ID="914DCE4BDABCE2BCA1846EB5E7CFE7A1" name-db="CMS_GOODS_PROMOTION" category="业务主体" title="商品促销" author="" description="" packagename="com.spark.cms.storage.promotion" name="CMS_GOODS_PROMOTION" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_GOODS_PROMOTION" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="goodsId" description="" ID="B2E97376C99DE182EB340EA0F266BB10" />
    <field dbtable="CMS_GOODS_PROMOTION" name="beginTime" name-db="beginTime" type="long" primary-key="false" keep-valid="false" author="" title="开始时间" description="" ID="9479CCCFE582CEFFF23EEDCB69FDA954" />
    <field dbtable="CMS_GOODS_PROMOTION" name="endTime" name-db="endTime" type="long" primary-key="false" keep-valid="false" author="" title="结束时间" description="" ID="FBC7BE8B8047CDE037F5401EBDE97BA3" />
    <field dbtable="CMS_GOODS_PROMOTION" name="disRate" name-db="disRate" type="numeric(6,4)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="9F2BAD19F87A002DEE7ABF6EE5BEE72F" />
    <field dbtable="CMS_GOODS_PROMOTION" name="pcount" name-db="pcount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="促销数量" description="" ID="FD1A44201C6ACBBFE9AB8F9B206088B1" />
    <field dbtable="CMS_GOODS_PROMOTION" name="isActiving" name-db="isActiving" type="boolean" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="98BD879AE00E8BECC9885BA99F94A9D6" />
    <field dbtable="CMS_GOODS_PROMOTION" name="saledCount" name-db="saledCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="已销售数量" description="" ID="0BF77791E4DC752BE43C1F8BB40ABE3D" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_GOODS_PROMOTION" name-db="CMS_GOODS_PROMOTION" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

