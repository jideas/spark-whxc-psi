<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_PURCHASE_GOODS" ID="7D8ED7DB194DD6FB02AFBBFE80252032" name-db="SA_PURCHASE_GOODS" category="" title="存放采购订单的商品信息" author="" description="" packagename="com.spark.psi.order.storage.purchase" name="SA_PURCHASE_GOODS" isneedrebuild="false">
  <fields>
    <field dbtable="SA_PURCHASE_GOODS" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="true" author="" title="租户GUID" description="" ID="F154FF18DFEDCB43FF7439FB3EB6B5DA" />
    <field dbtable="SA_PURCHASE_GOODS" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="true" author="" title="创建人GUID" description="" ID="41948F5C76EFE8AE975065CFBEAD354A" />
    <field dbtable="SA_PURCHASE_GOODS" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="5E52DABD7396A546466E0EE8DCB91934" />
    <field dbtable="SA_PURCHASE_GOODS" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="38D8D1975EAEB7131C59A982DB59915D" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品规格GUID" description="" ID="D2E8B4B288FADC658BCC8AEFC4F51144" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsCode" name-db="goodsCode" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="19BF3DCF5E343E2CB62E138C7F234ECB" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="B5D8663DB7F75F37EC718E13FAC47A2B" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="DE35D75A2F8212711A7DCB24EA19E107" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsProperties" name-db="goodsProperties" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="D53F0F9D7A9717B7178E7936FBA9624E" />
    <field dbtable="SA_PURCHASE_GOODS" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="590BA19DA2E1F1CBF3D7FB3F475AF55C" />
    <field dbtable="SA_PURCHASE_GOODS" name="goodsUnit" name-db="goodsUnit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="25728ABB519E34D865D68732AA00C237" />
    <field dbtable="SA_PURCHASE_GOODS" name="purchaseCount" name-db="purchaseCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购数量" description="" ID="6B8837852CD3AF8BC19A376BEDD438D0" />
    <field dbtable="SA_PURCHASE_GOODS" name="sourceId" name-db="sourceId" type="guid" primary-key="false" keep-valid="false" author="" title="对应仓库编号" description="" ID="8028487EFD8A58294E7542507AA815AB" />
    <field dbtable="SA_PURCHASE_GOODS" name="sourceName" name-db="sourceName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="1F0690B61B4579989CFD86347D8CCBC0" />
    <field dbtable="SA_PURCHASE_GOODS" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="870349A89A193E94B5C9E5D0D2C21B98" />
    <field dbtable="SA_PURCHASE_GOODS" name="partnerShortName" name-db="partnerShortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="A7FE8EB2D571CB31DBAD3CF818DAA4CC" />
    <field dbtable="SA_PURCHASE_GOODS" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="C62F188CD7A92E1FD6C4E814831E1366" />
    <field dbtable="SA_PURCHASE_GOODS" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="供应商全称拼音" description="" ID="DA0EBF06F1CC8C024531CCFEC9904AD2" />
    <field dbtable="SA_PURCHASE_GOODS" name="partnerFax" name-db="partnerFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商传真" description="" ID="4300CBEDCC9D208CFB3D411488D9C5ED" />
    <field dbtable="SA_PURCHASE_GOODS" name="contactId" name-db="contactId" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="388272B585232475A240A0D7C21ED20B" />
    <field dbtable="SA_PURCHASE_GOODS" name="contactName" name-db="contactName" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="A5D007038548C6556CC406E7B9BA6199" />
    <field dbtable="SA_PURCHASE_GOODS" name="contactTel" name-db="contactTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="6B9865D762CB66366D621FAA0DF539A4" />
    <field dbtable="SA_PURCHASE_GOODS" name="contactPhone" name-db="contactPhone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="17CC7C166706F435314FD7F453E869BF" />
    <field dbtable="SA_PURCHASE_GOODS" name="price_purchase" name-db="price_purchase" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购单价" description="" ID="45301B0CB7936C81009D7D9BCE874392" />
    <field dbtable="SA_PURCHASE_GOODS" name="price_lastPurchase" name-db="price_lastPurchase" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="上次采购单价" description="" ID="C77D7723518F0592ACE11BE50C1127DF" />
    <field dbtable="SA_PURCHASE_GOODS" name="purchaseCause" name-db="purchaseCause" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="采购原因" description="" ID="3EF209B354106EC656D77CC3728EBFA9" />
  </fields>
  <indexs>
    <index author="" name="SA_PURCHASE_GOODS_1" name-db="SA_PURCHASE_GOODS_1" title="SA_PURCHASE_GOODS_1" unique="false" description="">
      <index-item fieldID="F154FF18DFEDCB43FF7439FB3EB6B5DA" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="41948F5C76EFE8AE975065CFBEAD354A" field="creatorId" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_PURCHASE_GOODS" name-db="SA_PURCHASE_GOODS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

