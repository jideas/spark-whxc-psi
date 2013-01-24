<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Material" ID="87E669CE073D0E6B8275A035D7BB04C4" name-db="PSI_Base_Material" category="基础数据主体" title="材料主体" author="" description="" packagename="com.spark.psi.storage.base.material" name="PSI_Base_Material" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Material" name="materialCode" name-db="materialCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="1ACA9765E9BCA35EDCE95F9F1594B574" />
    <field dbtable="PSI_Base_Material" name="materialName" name-db="materialName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="6C20D1DA3F5C54F41645FBB32740592C" />
    <field dbtable="PSI_Base_Material" name="namePY" name-db="namePY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="C462A05FE3A823734736F8A1F6B97D19" />
    <field dbtable="PSI_Base_Material" name="categoryId" name-db="categoryId" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="8873C74ABF35DD88A6B81BEB063CAED2" />
    <field dbtable="PSI_Base_Material" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="9267745B39612E38463C21A7E901C995" />
    <field dbtable="PSI_Base_Material" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="45304C328F9015EE867F6D98101808C6" />
    <field dbtable="PSI_Base_Material" name="refFlag" name-db="refFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="" ID="21260847EDD198F3E81773FC115B3C55" />
    <field dbtable="PSI_Base_Material" name="inventoryWarningType" name-db="inventoryWarningType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存预警类型" description="" ID="C77F27954ACCA5CDA229306D95F85ED3" />
    <field dbtable="PSI_Base_Material" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="6ED2DE2F51E711CADF8F1611F0ACC023" />
    <field dbtable="PSI_Base_Material" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="F9F61EB94E734CB22E69ECCB9BFF3579" />
    <field dbtable="PSI_Base_Material" name="isJointVenture" name-db="isJointVenture" type="boolean" primary-key="false" keep-valid="false" author="" title="是否联营商品" description="" ID="D87FBCD459E545BC6EDC4DF91708F13F" />
    <field dbtable="PSI_Base_Material" name="supplierId" name-db="supplierId" type="guid" primary-key="false" keep-valid="false" author="" title="联营供应商" description="" ID="172CAEA890E3DFEF81CC4D9FA1AAB7A8" />
    <field dbtable="PSI_Base_Material" name="percentage" name-db="percentage" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="提成" description="" ID="809D9EF59591F8001348D82788B471EC" />
    <field dbtable="PSI_Base_Material" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="75D1994F3B41F9039A442FDE85CECD91" />
    <field dbtable="PSI_Base_Material" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="9E38FE20D1C214AF0BB39E50C3A8B65A" />
    <field dbtable="PSI_Base_Material" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="F0FBF028F83689946C6098DB3DD5A9ED" />
    <field dbtable="PSI_Base_Material" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="" ID="76B708691B8537B700BDF49B098FF379" />
    <field dbtable="PSI_Base_Material" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="36B8B428BFBEE934F780FD782548AA54" />
    <field dbtable="PSI_Base_Material" name="warningDay" name-db="warningDay" type="int" primary-key="false" keep-valid="false" author="" title="预警天数" description="" ID="E1BCE13BEF0C590443B430613E111F14" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Material" name-db="PSI_Base_Material" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

