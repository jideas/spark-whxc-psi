<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_MaterialItem" ID="2007E80B575233619F401AFBCC151EC8" name-db="PSI_Base_MaterialItem" category="基础数据主体" title="材料条目" author="" description="" packagename="com.spark.psi.storage.base.material" name="PSI_Base_MaterialItem" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_MaterialItem" name="materialCode" name-db="materialCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="975AC6E28E59767273B84C4C70AF4486" />
    <field dbtable="PSI_Base_MaterialItem" name="materialNo" name-db="materialNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="D4694D0101B10FF3712F176593DE47DA" />
    <field dbtable="PSI_Base_MaterialItem" name="materialName" name-db="materialName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="AE6A4669CA16CF42087D66DC9F090A31" />
    <field dbtable="PSI_Base_MaterialItem" name="namePY" name-db="namePY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="8DB56C51452250DA00C210CDF5B85C44" />
    <field dbtable="PSI_Base_MaterialItem" name="categoryId" name-db="categoryId" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="BF87EA609E30106E8FA1FAA3AEDE8FDA" />
    <field dbtable="PSI_Base_MaterialItem" name="spec" name-db="spec" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="规格" description="" ID="2BEC23FD5F2623AB3AF2DA5EDE8BA6C2" />
    <field dbtable="PSI_Base_MaterialItem" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="精度" description="" ID="0D1A53006AA90738010B9CD29C007819" />
    <field dbtable="PSI_Base_MaterialItem" name="inventoryStrategy" name-db="inventoryStrategy" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存策略" description="" ID="F0C7FECDE3D0F5B61A3CB965C3321133" />
    <field dbtable="PSI_Base_MaterialItem" name="materialUnit" name-db="materialUnit" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="341CD134B41BE48B4ED4DE72043F08DC" />
    <field dbtable="PSI_Base_MaterialItem" name="avgBuyPrice" name-db="avgBuyPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="平均采购价格" description="" ID="0AC3338BD4747C8A4A7A044DF539C794" />
    <field dbtable="PSI_Base_MaterialItem" name="totalStoreUpper" name-db="totalStoreUpper" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="总库存上限数量" description="" ID="0445FDE4D827E3F624288090DF5480A1" />
    <field dbtable="PSI_Base_MaterialItem" name="totalStoreFlore" name-db="totalStoreFlore" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="总库存下限数量" description="" ID="F348DBE4AA296780C8E6D49BC8CF8E21" />
    <field dbtable="PSI_Base_MaterialItem" name="totalStoreAmount" name-db="totalStoreAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总库存上限金额" description="" ID="5A0045DB24B46E28E5B96BEC5F5F00B2" />
    <field dbtable="PSI_Base_MaterialItem" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="93425DE0F1B0DDD3F51A285B3EEC553E" />
    <field dbtable="PSI_Base_MaterialItem" name="warningDay" name-db="warningDay" type="int" primary-key="false" keep-valid="false" author="" title="预警天数" description="" ID="BFE520B3A8C7D7E6A4A350E5E994DADB" />
    <field dbtable="PSI_Base_MaterialItem" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售价格" description="" ID="7202C4F63F2BCE8584CA3AFB450127FC" />
    <field dbtable="PSI_Base_MaterialItem" name="standardPrice" name-db="standardPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="标准价格" description="" ID="937CB6486F7B0E4BD516BF695D583782" />
    <field dbtable="PSI_Base_MaterialItem" name="planPrice" name-db="planPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="计划价格" description="" ID="02E9A8E202D97D5B944B86A70B36D354" />
    <field dbtable="PSI_Base_MaterialItem" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="" ID="CF06EA74E064E29124044E025FEA870B" />
    <field dbtable="PSI_Base_MaterialItem" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="BD2A6E0746E4E9CEC9227DEE0F3DFA8E" />
    <field dbtable="PSI_Base_MaterialItem" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="406021FD27B57A6508948FBEF7135AB0" />
    <field dbtable="PSI_Base_MaterialItem" name="refFlag" name-db="refFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="" ID="FCD4D3F6CDBCCC494CF881828F4036BB" />
    <field dbtable="PSI_Base_MaterialItem" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="1BB08E8CC8FCCCFC6545F93CEB817A53" />
    <field dbtable="PSI_Base_MaterialItem" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="EF0BE834977C9DEA3142F782434292E7" />
    <field dbtable="PSI_Base_MaterialItem" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="5AA26F0C1CDD70B659094410EB75ADD8" />
    <field dbtable="PSI_Base_MaterialItem" name="warningType" name-db="warningType" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品预警类型" description="" ID="00714DE8842B513EC85A936CAE62DD10" />
    <field dbtable="PSI_Base_MaterialItem" name="materialProperties" name-db="materialProperties" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="分类属性值" description="" ID="C861D24442E3F7854A7F31298E601991" />
    <field dbtable="PSI_Base_MaterialItem" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="9DCB39B766B949E62356E1347CFAC628" />
    <field dbtable="PSI_Base_MaterialItem" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="D36690F45CB7901C5C5DF8DB8626B33A" />
    <field dbtable="PSI_Base_MaterialItem" name="materialId" name-db="materialId" type="guid" primary-key="false" keep-valid="false" author="" title="材料ID" description="" ID="2E7C7CF68FB3CD4E3019F27F8613311B" />
    <field dbtable="PSI_Base_MaterialItem" name="lossRate" name-db="lossRate" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="损耗率" description="" ID="79BC006EAAA7750BD9B63EBD6C51A4CB" />
    <field dbtable="PSI_Base_MaterialItem" name="recentPurchasePrice" name-db="recentPurchasePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="最近采购价格" description="" ID="984C67B623BDC792C69FFADBD7FDC5CE" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_MaterialItem" name-db="PSI_Base_MaterialItem" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

