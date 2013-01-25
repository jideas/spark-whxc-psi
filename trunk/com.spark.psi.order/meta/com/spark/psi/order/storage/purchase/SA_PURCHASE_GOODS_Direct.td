<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_PURCHASE_GOODS_Direct" ID="0F3126D620CB7861D5525C87BEAEEF25" name-db="SA_PURCHASE_GOODS_Direct" category="" title="存放采购订单的商品信息(直供)" author="" description="" packagename="com.spark.psi.order.storage.purchase" name="SA_PURCHASE_GOODS_Direct" isneedrebuild="false">
  <fields>
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="true" author="" title="租户GUID" description="" ID="5214065061B7CA3F6A3F33313838CF6B" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="true" author="" title="创建人GUID" description="" ID="231B4A6E235554598C5CD949A317CC2E" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="6FBC7E31ADCEA00EBCD1DA016133E976" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="26AF9724CB9CF7A394CE610E46165227" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品规格GUID" description="" ID="DDE799F6F6DCE56C5A474C75E826E9CA" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="goodsCode" name-db="goodsCode" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="13B82A7BC676907969C7A7C2EBF710A0" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="858C009CA6E6A524B4DEB0E4553B5610" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="goodsProperties" name-db="goodsProperties" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="310BE8DFE79BBC26A0C025446EDC1336" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="E3EEEC40FACFE3DAC79425D104875A85" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="goodsUnit" name-db="goodsUnit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="E15AF42290CDCC564EA8B73B78C04762" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="purchaseCount" name-db="purchaseCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购数量" description="" ID="5494A474D978DE57613A756049FFBC70" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="sourceId" name-db="sourceId" type="guid" primary-key="false" keep-valid="false" author="" title="对应仓库编号" description="" ID="F5969699247568F43F8CD4A1C38F456F" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="sourceName" name-db="sourceName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="DFA1311A9C7B2846F1FD32609979D0B0" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="D3438FB3BC477DE3ADF743E2E98F2C00" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="partnerShortName" name-db="partnerShortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="ADD799551CD2F0AFC69A91B034E70792" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="215E55C1D0A05B4E0C030615BF9B2192" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="供应商全称拼音" description="" ID="3B8575D028DCCBEE289B35188EF70E92" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="partnerFax" name-db="partnerFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商传真" description="" ID="465DCD81400C175A88595523D23F6605" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="contactId" name-db="contactId" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="A3AD2088915F8FD8DD5BCB51D4D05192" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="contactName" name-db="contactName" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="DEEAFA7ADCAACDCB80FA73A58223E3E8" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="contactTel" name-db="contactTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="5EAE5C2DE472D979D12E5CF319B6E10D" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="contactPhone" name-db="contactPhone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="A85CFC1DEF35871CB0A7362EC24E9763" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="price_purchase" name-db="price_purchase" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购单价" description="" ID="0D22C3E2340B52122DF98932ADD2FF05" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="price_lastPurchase" name-db="price_lastPurchase" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="上次采购单价" description="" ID="06596BAE065873EAE3FE2B2AB088854C" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="purchaseCause" name-db="purchaseCause" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="采购原因" description="" ID="D81BD90B0C41F9E1EB8C44DB2D2E73AC" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="sourceSaleItemId" name-db="sourceSaleItemId" type="guid" primary-key="false" keep-valid="false" author="" title="来源销售明细ID" description="" ID="A9C9FA86CB4EBA738366B4D6DB58269D" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="sourceSaleId" name-db="sourceSaleId" type="guid" primary-key="false" keep-valid="false" author="" title="来源销售订单ID" description="" ID="A0E9187613E18C3E06E28FBFC92E31B7" />
    <field dbtable="SA_PURCHASE_GOODS_Direct" name="deleteFlag" name-db="deleteFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="删除标志" description="" ID="494CE5FD216E6AAC218A9F99284BFDB3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_PURCHASE_GOODS_Direct" name-db="SA_PURCHASE_GOODS_Direct" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

