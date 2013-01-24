<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Bom" ID="1B315C8A7522F23A7DA1445CED718441" name-db="PSI_Base_Bom" category="基础数据主体" title="bom表" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_Bom" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Bom" name="bomNo" name-db="bomNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom单号" description="" ID="73FBBC55CB88870AEA7A1B151C69413A" />
    <field dbtable="PSI_Base_Bom" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品条目id" description="" ID="F8C3BBC7C9AA1FEADBA4E9A93A36B89F" />
    <field dbtable="PSI_Base_Bom" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="834C4E8125788DA318A253B40C8068A9" />
    <field dbtable="PSI_Base_Bom" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="定制日期" description="" ID="A86A2B72FC14C222862079894A74DA4E" />
    <field dbtable="PSI_Base_Bom" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="ECB23C78FE6E88922CCD166B2BB3D620" />
    <field dbtable="PSI_Base_Bom" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="EB84167A4268ADC8A7EE1F89CC318097" />
    <field dbtable="PSI_Base_Bom" name="approvePerson" name-db="approvePerson" type="guid" primary-key="false" keep-valid="false" author="" title="审核人" description="" ID="9C2C61CDD2888EB5AEC07D2031C248A0" />
    <field dbtable="PSI_Base_Bom" name="approvePersonName" name-db="approvePersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="审核人姓名" description="" ID="93658D7EC87A11D602A0E436D9D3EEB5" />
    <field dbtable="PSI_Base_Bom" name="approveDate" name-db="approveDate" type="date" primary-key="false" keep-valid="false" author="" title="审核日期" description="" ID="63875D148B0324A859158EA747F376F8" />
    <field dbtable="PSI_Base_Bom" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="80BB4AE3F34AFA25071074ED5FC27DC1" />
    <field dbtable="PSI_Base_Bom" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="" description="" ID="E2D854A2C4B572FB480F44A928703FF2" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Bom" name-db="PSI_Base_Bom" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

