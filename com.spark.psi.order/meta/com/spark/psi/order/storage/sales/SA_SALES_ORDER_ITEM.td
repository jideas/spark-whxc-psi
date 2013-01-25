<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALES_ORDER_ITEM" ID="CF40D7954DC900182431F28EF63441D7" name-db="SA_SALES_ORDER_ITEM" category="" title="存放销售订单明细信息" author="" description="" packagename="com.spark.psi.order.storage.sales" name="SA_SALES_ORDER_ITEM" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALES_ORDER_ITEM" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="C86F451DE10C79E2799F54EB77C6A913" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="18552CFEE80E8A6379FF130E74E40349" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="98A49A834EA5CC712F6AB33C1807653D" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="CFA7FEC92849C3D6C7B96C897FBFF82A" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="orderId" name-db="orderId" type="guid" primary-key="false" keep-valid="false" author="" title="销售订单GUID" description="" ID="E9F8652CD93F8C39E0FBE0EEBD40CAA6" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品规格GUID" description="" ID="F67809E9C7D69EF79D9CE1621FE73420" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="goodsCode" name-db="goodsCode" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="9037E98FD6A6E0544F90D945EA77FD80" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="F7ACC658EF2F7A6AB6B7D219EE871472" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="goodsProperties" name-db="goodsProperties" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="08356D689D292256A56007D1A917CD2C" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="goodsUnit" name-db="goodsUnit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="9FDEA7BF0B66A36FB908A11B01F92E31" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="0AA6935657E91CA11E06BB1E9310D275" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="AC42761FA63A423FDB5E1A3978E154FC" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="num" name-db="num" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="3C1888ADECFF8C80BC1477D660E5FBC2" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="72FBB5A134688AD9A580EEB419298226" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="discount" name-db="discount" type="numeric(6,5)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="A4DAC253EBF8F40203F23987A4CD119D" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣额" description="" ID="78808C67F5BA63ACFB0041FBE14DC6C9" />
    <field dbtable="SA_SALES_ORDER_ITEM" name="promotionId" name-db="promotionId" type="guid" primary-key="false" keep-valid="false" author="" title="促销商品Guid" description="" ID="E16DEFFE114ABECF46FB220581FC37C8" />
  </fields>
  <indexs>
    <index author="" name="SA_SALES_ORDER_ITEM_1" name-db="SA_SALES_ORDER_ITEM_1" title="SA_SALES_ORDER_ITEM_1" unique="false" description="">
      <index-item fieldID="C86F451DE10C79E2799F54EB77C6A913" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="E9F8652CD93F8C39E0FBE0EEBD40CAA6" field="orderId" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALES_ORDER_ITEM" name-db="SA_SALES_ORDER_ITEM" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

