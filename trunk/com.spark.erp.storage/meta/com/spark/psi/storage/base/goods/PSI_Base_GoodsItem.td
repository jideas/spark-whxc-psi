<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_GoodsItem" ID="C24FC0B6FD8B3BFF24A4481FAA6807EC" name-db="PSI_Base_GoodsItem" category="基础数据主体" title="商品条目" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_GoodsItem" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_GoodsItem" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条目编码" description="" ID="BCDB65DD3AB639BA6762BF284C026087" />
    <field dbtable="PSI_Base_GoodsItem" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条目条码" description="" ID="170DE3D781692DC219F9F49BDDA2C1FA" />
    <field dbtable="PSI_Base_GoodsItem" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品条目名称" description="" ID="2C7ABCDD1A9AB21827D29E517AD10BFE" />
    <field dbtable="PSI_Base_GoodsItem" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="33FF2300EB3150B230C4A2DE14E49425" />
    <field dbtable="PSI_Base_GoodsItem" name="categoryId" name-db="categoryId" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="EEBFD74C100F34F80573B9F271D78C11" />
    <field dbtable="PSI_Base_GoodsItem" name="spec" name-db="spec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="规格" description="" ID="49B8215CCB44E23D5861AA717BE9B1D0" />
    <field dbtable="PSI_Base_GoodsItem" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="精度" description="" ID="55F42B15B2296EDE529DC46BF62ED5C9" />
    <field dbtable="PSI_Base_GoodsItem" name="needProduce" name-db="needProduce" type="boolean" primary-key="false" keep-valid="false" author="" title="是否需要生产" description="" ID="4B570E711BCCBEAE7E8B0D71A28E5B00" />
    <field dbtable="PSI_Base_GoodsItem" name="inventoryStrategy" name-db="inventoryStrategy" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存策略" description="" ID="FA6F10A4F8E7EC9B693932E40117FE18" />
    <field dbtable="PSI_Base_GoodsItem" name="goodsUnit" name-db="goodsUnit" type="nvarchar(5)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="F1AAA156F069FB63B849F8E0CFA5B256" />
    <field dbtable="PSI_Base_GoodsItem" name="avgCost" name-db="avgCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="实际成本" description="" ID="914296E43DA5C13DD727DF37FCFBE71A" />
    <field dbtable="PSI_Base_GoodsItem" name="standardCost" name-db="standardCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="标准成本" description="" ID="8E7BE3EF8ECAAA799445015EA0D0C49F" />
    <field dbtable="PSI_Base_GoodsItem" name="assessCost" name-db="assessCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="考核成本" description="" ID="671BC2C6455944EFFA3D2EBF16711B87" />
    <field dbtable="PSI_Base_GoodsItem" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="4A11B5F6A31DBB1E086B25BE1A3662A1" />
    <field dbtable="PSI_Base_GoodsItem" name="warningDay" name-db="warningDay" type="int" primary-key="false" keep-valid="false" author="" title="预警天数" description="" ID="44DEF2CE52D33D13260CECFF77574893" />
    <field dbtable="PSI_Base_GoodsItem" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售价格" description="" ID="A09AA61B93FA8614AD48664C95B470B3" />
    <field dbtable="PSI_Base_GoodsItem" name="originalPrice" name-db="originalPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="原价" description="" ID="6A196A4E7E60B368C099BB90770590C0" />
    <field dbtable="PSI_Base_GoodsItem" name="lossRate" name-db="lossRate" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="损耗率" description="" ID="0097317AEA91E204747BBD77BAC0FCBC" />
    <field dbtable="PSI_Base_GoodsItem" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="" ID="5FEB3FB24A756191BC312D6F404F8B27" />
    <field dbtable="PSI_Base_GoodsItem" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="5CFA214D1BBFD143486667252B900DC5" />
    <field dbtable="PSI_Base_GoodsItem" name="refFlag" name-db="refFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="" ID="47553B04E2E3D85DEA57444BD2C28A59" />
    <field dbtable="PSI_Base_GoodsItem" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="65F20089D5EA0FF30F5F2609B8311546" />
    <field dbtable="PSI_Base_GoodsItem" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="5A5EB16F6AE55D2B1E7AEB8B3421D1DA" />
    <field dbtable="PSI_Base_GoodsItem" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="1A25D376F6CB9D8E7BE2D338D20EB677" />
    <field dbtable="PSI_Base_GoodsItem" name="goodsProperties" name-db="goodsProperties" type="text" primary-key="false" keep-valid="false" author="" title="分类属性值" description="" ID="9F7F65CE165AB3BE4B7E8A77D43A0C0C" />
    <field dbtable="PSI_Base_GoodsItem" name="bomId" name-db="bomId" type="guid" primary-key="false" keep-valid="false" author="" title="bom标志" description="" ID="0D32A3D6A7381730F26FB9C861D19F80" />
    <field dbtable="PSI_Base_GoodsItem" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="32AE7226CEDDFD9778B49BF43A16E6C3" />
    <field dbtable="PSI_Base_GoodsItem" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="93D9A417642A241FE0D3B3D0C01E1340" />
    <field dbtable="PSI_Base_GoodsItem" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="40F2620841A1C3ECA7ABB65492C31095" />
    <field dbtable="PSI_Base_GoodsItem" name="serialNumber" name-db="serialNumber" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="序号" description="" ID="1936FDF277E4789EBFCAC5FD084E7E02" />
    <field dbtable="PSI_Base_GoodsItem" name="halfkgPrice" name-db="halfkgPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="价格/斤" description="" ID="9009DAB56AA68BD2D10CA0BC9D76DEA7" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_GoodsItem" name-db="PSI_Base_GoodsItem" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

