<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkin" ID="3627F1DFFE83C28E52501199DB2FEE95" name-db="PSI_Inventory_Checkin" category="基础数据主体" title="入库单" author="" description="" packagename="com.spark.psi.storage.inventory.checkin" name="PSI_Inventory_Checkin" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkin" name="sheetNo" name-db="sheetNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="入库单号" description="" ID="40553F6C62F7DC04FEC6AEC059133394" />
    <field dbtable="PSI_Inventory_Checkin" name="sheetType" name-db="sheetType" type="char(2)" primary-key="false" keep-valid="false" author="" title="入库类型" description="" ID="6291E63125932099FC1CBA93CC0143A3" />
    <field dbtable="PSI_Inventory_Checkin" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商/客户recid" description="" ID="96E4E1B0EFC82E01CBD1043B0F49BBD9" />
    <field dbtable="PSI_Inventory_Checkin" name="partnerName" name-db="partnerName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="供应商/客户名称" description="" ID="6E469AF900022705C986CA6DEC43AF82" />
    <field dbtable="PSI_Inventory_Checkin" name="partnerCode" name-db="partnerCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="6ECA4B6F2E071F73987B5A9D94763157" />
    <field dbtable="PSI_Inventory_Checkin" name="namePY" name-db="namePY" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="B47BD0015D9A5DFA68112E85ECA521D3" />
    <field dbtable="PSI_Inventory_Checkin" name="partnerShortName" name-db="partnerShortName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="DE8053A7F99D6F39D556EE1DD5E0F918" />
    <field dbtable="PSI_Inventory_Checkin" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据recid" description="" ID="477DED8EE91A9F696898BCA678A96A80" />
    <field dbtable="PSI_Inventory_Checkin" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="相关单据编号" description="" ID="3C5FC6894863F045B9176C99E566D51A" />
    <field dbtable="PSI_Inventory_Checkin" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库recid" description="" ID="30F2E6ABF0851B0875BB850C9C9E6466" />
    <field dbtable="PSI_Inventory_Checkin" name="storeName" name-db="storeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="C10CB7EF4E093EBFBB6448185C84E7F8" />
    <field dbtable="PSI_Inventory_Checkin" name="storeNamePY" name-db="storeNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="159F200AE409F934D7D354F552BB42A8" />
    <field dbtable="PSI_Inventory_Checkin" name="goodsFrom" name-db="goodsFrom" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="物品来源" description="" ID="869080F8CE6882EFEC0D74A59C874651" />
    <field dbtable="PSI_Inventory_Checkin" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="C0B165877AF5E66D3C5451294CE3BB30" />
    <field dbtable="PSI_Inventory_Checkin" name="buyPerson" name-db="buyPerson" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="采购人" description="" ID="E27BCE519BEACD930176D5DA3986B593" />
    <field dbtable="PSI_Inventory_Checkin" name="buyDate" name-db="buyDate" type="date" primary-key="false" keep-valid="false" author="" title="采购日期" description="" ID="266DC09D62D291754826FC4B8EDA2D21" />
    <field dbtable="PSI_Inventory_Checkin" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="6B3B79BEA245BC8412C2CAC36CA40F1B" />
    <field dbtable="PSI_Inventory_Checkin" name="askedAmount" name-db="askedAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已申请金额" description="" ID="E8E481D886086C9C3A15A727D8142281" />
    <field dbtable="PSI_Inventory_Checkin" name="paidAmount" name-db="paidAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已付款金额" description="" ID="D29044AEDC6C4847814E20A0BEB4E1AF" />
    <field dbtable="PSI_Inventory_Checkin" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣金额" description="" ID="D4D9CFF6FF16B444AB1CFF3869073848" />
    <field dbtable="PSI_Inventory_Checkin" name="checkinDate" name-db="checkinDate" type="date" primary-key="false" keep-valid="false" author="" title="入库日期" description="" ID="FAFA65F0AF86B624C2663017FDB42B5A" />
    <field dbtable="PSI_Inventory_Checkin" name="checkinPerson" name-db="checkinPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认入库人" description="" ID="6BE27DFF515167B72BEEFF66113D394F" />
    <field dbtable="PSI_Inventory_Checkin" name="checkinPersonName" name-db="checkinPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认入库人" description="" ID="F2D5866CCCFDCDC15CEC1AEE2116F624" />
    <field dbtable="PSI_Inventory_Checkin" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门recid" description="" ID="2B3B6D5EB15D11B3BE7410ABDE115759" />
    <field dbtable="PSI_Inventory_Checkin" name="deptName" name-db="deptName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="部门名称" description="" ID="DEB128129BDFF216BF782C30996F4DD7" />
    <field dbtable="PSI_Inventory_Checkin" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="EDE07EFD638061CD5D82E343FEFF2AC3" />
    <field dbtable="PSI_Inventory_Checkin" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="E85A603DFBF70BA27009F98BEA241564" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkin" name-db="PSI_Inventory_Checkin" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

