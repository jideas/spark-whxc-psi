<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Store" ID="C715757D11AC301DE9A87200A2316149" name-db="PSI_Base_Store" category="基础数据主体" title="仓库主体" author="" description="" packagename="com.spark.psi.storage.base.store" name="PSI_Base_Store" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Store" name="storeNo" name-db="storeNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="仓库编号" description="" ID="4347895910019C5BEBCD46D44192F418" />
    <field dbtable="PSI_Base_Store" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="7830FE7DB84E7417FC5ADD79053F2BFC" />
    <field dbtable="PSI_Base_Store" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="51A51084171E4B68E4A0516FE35BA312" />
    <field dbtable="PSI_Base_Store" name="consignee" name-db="consignee" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="313948016AE266F59B022D2549C4D376" />
    <field dbtable="PSI_Base_Store" name="mobileNo" name-db="mobileNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="手机号" description="" ID="9C6EE6494DB90DC66118DB940CD43025" />
    <field dbtable="PSI_Base_Store" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="442D01431459C966FCAD7A18E4C8E993" />
    <field dbtable="PSI_Base_Store" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="6D7B26B52CEBAA7BC0C7EBDCEEC9C344" />
    <field dbtable="PSI_Base_Store" name="town" name-db="town" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="区县" description="" ID="70B3D259476FE993E41413EDE9C9B39D" />
    <field dbtable="PSI_Base_Store" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="2FBF02B50AAEC5D40455D79DA7A9A227" />
    <field dbtable="PSI_Base_Store" name="postCode" name-db="postCode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="764AF2E3451EF82A5C1AAAA367D16226" />
    <field dbtable="PSI_Base_Store" name="shelfCount" name-db="shelfCount" type="int" primary-key="false" keep-valid="false" author="" title="货位数量" description="" ID="F424E5AF5F94CD8546F9E2320215D5FD" />
    <field dbtable="PSI_Base_Store" name="defaultTiersCount" name-db="defaultTiersCount" type="int" primary-key="false" keep-valid="false" author="" title="默认层数" description="" ID="C2A7E7479C2A66477BC6A266D0212F04" />
    <field dbtable="PSI_Base_Store" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="仓库状态" description="" ID="FE5E7E629CB7C9A7113CCA705D23D253" />
    <field dbtable="PSI_Base_Store" name="storeType" name-db="storeType" type="char(2)" primary-key="false" keep-valid="false" author="" title="仓库类型" description="" ID="CF4990A13AD3FE66C02FDF3811EDE90A" />
    <field dbtable="PSI_Base_Store" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="F19D42D00D5FE93959263948C3FF322D" />
    <field dbtable="PSI_Base_Store" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="FE2037CDB5FA93D4FE79FF0038CB0B3A" />
    <field dbtable="PSI_Base_Store" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="226CFF3B7A320C73E96E3CE2E71B7C13" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Store" name-db="PSI_Base_Store" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

