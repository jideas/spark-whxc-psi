<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_GOODS" ID="10FC21573F46B2A944A740ADDFA6B07C" name-db="CMS_GOODS" category="业务主体" title="网上商品" author="" description="" packagename="com.spark.cms.storage.goods" name="CMS_GOODS" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_GOODS" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="944BA915406009BB8A71699B214F74CD" />
    <field dbtable="CMS_GOODS" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="条码" description="" ID="91232DCEBD56A7E4B2514D7B8E19B192" />
    <field dbtable="CMS_GOODS" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="名称" description="" ID="30B772E3555158C36259FBE1D5E15104" />
    <field dbtable="CMS_GOODS" name="goodsSpec" name-db="goodsSpec" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="规格" description="" ID="E7A8B1B5E8A125CBB660FC0C72F33574" />
    <field dbtable="CMS_GOODS" name="goodsUnit" name-db="goodsUnit" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="D91CB1B9C567314251BAD7C4DFCBB5A7" />
    <field dbtable="CMS_GOODS" name="originalPrice" name-db="originalPrice" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="原价" description="" ID="B03E144D8C7CFA636570BCBC752319E1" />
    <field dbtable="CMS_GOODS" name="realPrice" name-db="realPrice" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="现价" description="" ID="A485A04A1E961CA4E1D79E016632D6F1" />
    <field dbtable="CMS_GOODS" name="vantagesType" name-db="vantagesType" type="char(2)" primary-key="false" keep-valid="false" author="" title="积分类别" description="" ID="EC496AEAFE61DA297C88B4223D9C02A3" />
    <field dbtable="CMS_GOODS" name="freeDelivery" name-db="freeDelivery" type="boolean" primary-key="false" keep-valid="false" author="" title="是否免费送货" description="" ID="9B2138DB0D1CCFD93D13053CB270501E" />
    <field dbtable="CMS_GOODS" name="categoryId1" name-db="categoryId1" type="guid" primary-key="false" keep-valid="false" author="" title="一级分类id" description="" ID="65CF43681F00894D50B06AA649FDE17F" />
    <field dbtable="CMS_GOODS" name="categoryId2" name-db="categoryId2" type="guid" primary-key="false" keep-valid="false" author="" title="二级分类id" description="" ID="BB8C2743B4276267C194649D48014669" />
    <field dbtable="CMS_GOODS" name="categoryId3" name-db="categoryId3" type="guid" primary-key="false" keep-valid="false" author="" title="三级分类id" description="" ID="B6F9FE88F0A5480D85EED3DE9978BAF7" />
    <field dbtable="CMS_GOODS" name="propertiy1" name-db="propertiy1" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="属性一" description="" ID="0DD83C9D0D2BD0636586E2E3D0A36D76" />
    <field dbtable="CMS_GOODS" name="propertiy2" name-db="propertiy2" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="属性二" description="" ID="0BD894F2A223522D48A1D4CDFCF9507A" />
    <field dbtable="CMS_GOODS" name="propertiy3" name-db="propertiy3" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="属性三" description="" ID="EA337601364102CFFFFEE1B573B88EBF" />
    <field dbtable="CMS_GOODS" name="propertiy4" name-db="propertiy4" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="属性四" description="" ID="44A181B7213E0444C1E20B6274AC30CF" />
    <field dbtable="CMS_GOODS" name="propertiy5" name-db="propertiy5" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="属性五" description="" ID="5D214BFC8F17197F9B851F5E1137BB32" />
    <field dbtable="CMS_GOODS" name="picturePath1" name-db="picturePath1" type="nvarchar(300)" primary-key="false" keep-valid="false" author="" title="图片地址1" description="" ID="DCE8E3E3B13F98C67233AA8DF1FE4DF4" />
    <field dbtable="CMS_GOODS" name="picturePath2" name-db="picturePath2" type="nvarchar(300)" primary-key="false" keep-valid="false" author="" title="图片地址2" description="" ID="08E55FAD96BF65881D878BCEDD39CCF8" />
    <field dbtable="CMS_GOODS" name="picturePath3" name-db="picturePath3" type="nvarchar(300)" primary-key="false" keep-valid="false" author="" title="图片地址3" description="" ID="07F85CAB919CB372C51C8059D9E9A0F8" />
    <field dbtable="CMS_GOODS" name="saledCount" name-db="saledCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="已销数量" description="" ID="6490F498FC6C09EE4B1DF356F6D7E6F0" />
    <field dbtable="CMS_GOODS" name="inventoryCount" name-db="inventoryCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="库存数量" description="" ID="534C1AE94DF229B1BEAC76CEA828E835" />
    <field dbtable="CMS_GOODS" name="isMostSales" name-db="isMostSales" type="boolean" primary-key="false" keep-valid="false" author="" title="是否热卖" description="" ID="AF2047CF88367C3C022101EBB07973D9" />
    <field dbtable="CMS_GOODS" name="isPopular" name-db="isPopular" type="boolean" primary-key="false" keep-valid="false" author="" title="是否人气" description="" ID="A5535AB404EC20664593E31E12157C89" />
    <field dbtable="CMS_GOODS" name="isPublished" name-db="isPublished" type="boolean" primary-key="false" keep-valid="false" author="" title="是否已发布" description="" ID="D367CAC5D624AC4375FC1EC51F75DB74">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="CMS_GOODS" name="isVantagesGoods" name-db="isVantagesGoods" type="boolean" primary-key="false" keep-valid="false" author="" title="是否积分商城商品" description="" ID="6212BB9359D6C78DFDB6E84E8B71C4EA">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="CMS_GOODS" name="vantagesCost" name-db="vantagesCost" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="积分价格" description="" ID="0AD4D82ECB48787944761B4E9922A1BC">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_GOODS" name="goodsType" name-db="goodsType" type="char(2)" primary-key="false" keep-valid="false" author="" title="普通，预订" description="" ID="9835CF53DB6521234A2C7DAC4C1F6EC2">
      <default>
        <const type="char(2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_GOODS" name="publishPersonId" name-db="publishPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="发布人ID" description="" ID="D523FD92B37237D34EBEC36D62844B99" />
    <field dbtable="CMS_GOODS" name="publishPersonName" name-db="publishPersonName" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="发布人名称" description="" ID="7C7EF56F4C4A54728A54B50FCE11DAAF" />
    <field dbtable="CMS_GOODS" name="publishDate" name-db="publishDate" type="date" primary-key="false" keep-valid="false" author="" title="发布日期" description="" ID="D2464062FF6B87EAAC64FC9E6DCB785A" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_GOODS" name-db="CMS_GOODS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

