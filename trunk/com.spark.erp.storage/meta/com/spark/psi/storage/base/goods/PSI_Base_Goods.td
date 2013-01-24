<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Goods" ID="966B1BF3644C0C07BAF80C0B74A023F9" name-db="PSI_Base_Goods" category="基础数据主体" title="商品表" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_Goods" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Goods" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="3804F7238A0A3CF314F6861CE854509B" />
    <field dbtable="PSI_Base_Goods" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="DE907E7F16143FB5BE54697B696FB677" />
    <field dbtable="PSI_Base_Goods" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="1D023DCD69ACAFDC4BC7789DB955B651" />
    <field dbtable="PSI_Base_Goods" name="categoryId" name-db="categoryId" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="E04AAA4AF43F6B39046E8BCD97173C17" />
    <field dbtable="PSI_Base_Goods" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="统一销售价格" description="" ID="46A153E99A0BB55F8342B37EE37D56D6" />
    <field dbtable="PSI_Base_Goods" name="isJointVenture" name-db="isJointVenture" type="boolean" primary-key="false" keep-valid="false" author="" title="是否联营商品" description="" ID="411DDC595128BA0E06290B6E9D418D09" />
    <field dbtable="PSI_Base_Goods" name="supplierId" name-db="supplierId" type="guid" primary-key="false" keep-valid="false" author="" title="联营供应商" description="" ID="1795A7231F1F83D892CEABC3D594AAB6" />
    <field dbtable="PSI_Base_Goods" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="209CB4BF1E19FCE466C633AE5752D14B" />
    <field dbtable="PSI_Base_Goods" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="41AC4A60BAFABD5F409FD14A11072A30" />
    <field dbtable="PSI_Base_Goods" name="warningDay" name-db="warningDay" type="int" primary-key="false" keep-valid="false" author="" title="预警天数" description="" ID="7AF5553EB1AC307EBC3212E5B3FCE00C" />
    <field dbtable="PSI_Base_Goods" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="8C8354D0A8B753795BA54C0C7EAF62E5" />
    <field dbtable="PSI_Base_Goods" name="refFlag" name-db="refFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否关联" description="" ID="EC691AE10C99BDFF4ABA064A7D4104A4" />
    <field dbtable="PSI_Base_Goods" name="inventoryWarningType" name-db="inventoryWarningType" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品库存预警类型" description="" ID="FBC3FEB5ECDAFDACDA5C1763B29BBEC2" />
    <field dbtable="PSI_Base_Goods" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="C4E8343FD3A5BA088D52143EACCCF884" />
    <field dbtable="PSI_Base_Goods" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="B45A7706D56D38FBBA1344A5B889A0F0" />
    <field dbtable="PSI_Base_Goods" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="6507992302C0F932A820567769D03B8B" />
    <field dbtable="PSI_Base_Goods" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="3A70866FD2F80998BD898CCD12E84B60" />
    <field dbtable="PSI_Base_Goods" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="A39D2A9B844571AA3A0BB4C370E2111F" />
    <field dbtable="PSI_Base_Goods" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="" ID="33E3568A807CDA32D6E37861CD7B2986" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Goods" name-db="PSI_Base_Goods" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

