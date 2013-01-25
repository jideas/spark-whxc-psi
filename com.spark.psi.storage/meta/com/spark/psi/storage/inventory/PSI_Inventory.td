<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory" ID="AC88225BC220032C562679615F161573" name-db="PSI_Inventory" category="基础数据主体" title="库存表" author="" description="" packagename="com.spark.psi.storage.inventory" name="PSI_Inventory" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库标识" description="" ID="393A0E57BEB88E13186AC59F58E81362" />
    <field dbtable="PSI_Inventory" name="stockId" name-db="stockId" type="guid" primary-key="false" keep-valid="false" author="" title="存货标识" description="" ID="AAACE60453C854787B98ACD2E5B27817" />
    <field dbtable="PSI_Inventory" name="initCount" name-db="initCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="初始化数量" description="" ID="D923CD1AD51741827177A44347812C6A" />
    <field dbtable="PSI_Inventory" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化金额" description="" ID="22FA77E67321B309C0659CC2457B8E0B" />
    <field dbtable="PSI_Inventory" name="initCost" name-db="initCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化单位成本" description="" ID="2B87209DCE03C40DFE1E47285CA8A280" />
    <field dbtable="PSI_Inventory" name="name" name-db="name" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="存货名称" description="" ID="47ADFD0405AAEBD010913780610EEB45" />
    <field dbtable="PSI_Inventory" name="code" name-db="code" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="存货编码" description="" ID="EBBCD77794694B0BF86BC8D7A7FD3C79" />
    <field dbtable="PSI_Inventory" name="stockNo" name-db="stockNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="存货条码" description="" ID="8D756DEE1A8AB9A414AB44E4310B36C1" />
    <field dbtable="PSI_Inventory" name="properties" name-db="properties" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="属性" description="" ID="2238D5FA68ACBBD6D23B500954FFAFAB" />
    <field dbtable="PSI_Inventory" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="存货数量" description="" ID="D720EF9EE833F51D5CA69C5277C79CDD" />
    <field dbtable="PSI_Inventory" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="存货金额" description="" ID="409C0EC7E0ACBF9E9CC26DE1250FA962" />
    <field dbtable="PSI_Inventory" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="存货单位" description="" ID="A0BEE0A3E1BB677DC121EFF34E8C5E2F" />
    <field dbtable="PSI_Inventory" name="spec" name-db="spec" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="存货规格" description="" ID="6F1115326B59758C8B7CB4B999022A06" />
    <field dbtable="PSI_Inventory" name="onWayCount" name-db="onWayCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购在途数量" description="" ID="16592B2885FFA71216B417871CEF79A6" />
    <field dbtable="PSI_Inventory" name="toDeliverCount" name-db="toDeliverCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="发货需求" description="" ID="9F01D6F1D02E5C2C8CA699F009FD3AEF" />
    <field dbtable="PSI_Inventory" name="upperLimitCount" name-db="upperLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量上限" description="" ID="12882CFCA76CCB0E6A1C8EC299E0922B" />
    <field dbtable="PSI_Inventory" name="lowerLimitCount" name-db="lowerLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量下限" description="" ID="D8D6382E99C3C3EA3BF7D7DC98BE0A32" />
    <field dbtable="PSI_Inventory" name="upperLimitAmount" name-db="upperLimitAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存金额上限" description="" ID="B1E818E2E5A9E4322C4408D630E2E81C" />
    <field dbtable="PSI_Inventory" name="inventoryType" name-db="inventoryType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存类型" description="" ID="5D65F2C411AD52A1F8106287F8D0F215" />
    <field dbtable="PSI_Inventory" name="lockedCount" name-db="lockedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="锁定库存数量" description="" ID="329C8ABF01CA92E6DDEFD6A64AAB7FF6" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory" name-db="PSI_Inventory" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

