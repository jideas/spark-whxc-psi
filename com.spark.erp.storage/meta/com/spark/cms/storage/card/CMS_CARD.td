<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_CARD" ID="28AD49AE6C40095B3149E35261E17306" name-db="CMS_CARD" category="业务主体" title="" author="" description="" packagename="com.spark.cms.storage.card" name="CMS_CARD" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_CARD" name="ordinal" name-db="ordinal" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="顺序号" description="" ID="AB118BD9D49C0CB985FB5CACC4AC5D20" />
    <field dbtable="CMS_CARD" name="cardNo" name-db="cardNo" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="卡号" description="" ID="AA903D612422A235E434535A3C3CD11A" />
    <field dbtable="CMS_CARD" name="password" name-db="password" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="密码" description="" ID="C79668F36B19FD24D36B9B2E577A813B" />
    <field dbtable="CMS_CARD" name="amount" name-db="amount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="面值" description="" ID="95C3E4D18C6C89E6E95D2D478316F321" />
    <field dbtable="CMS_CARD" name="secretKey" name-db="secretKey" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="秘钥" description="" ID="35A42E5F84A4A1E5FDA8FCEF64B1246B" />
    <field dbtable="CMS_CARD" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="F10C069FB941C63906A9091E8CA8E3C0" />
    <field dbtable="CMS_CARD" name="creator" name-db="creator" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="创建姓名" description="" ID="480AD322580860E51E82CBFCA46FB477" />
    <field dbtable="CMS_CARD" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="CE639EAC44669BB096EFC1A3E338157F" />
    <field dbtable="CMS_CARD" name="activePersonId" name-db="activePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="启用人" description="" ID="332B226215ADD12436436F4CACDCA493" />
    <field dbtable="CMS_CARD" name="activePerson" name-db="activePerson" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="启用人姓名" description="" ID="F38EA7D63678BF12E05F76669BD9DF43" />
    <field dbtable="CMS_CARD" name="activeDate" name-db="activeDate" type="date" primary-key="false" keep-valid="false" author="" title="启用日期" description="" ID="E500B0E7910671533951998742B0EF34" />
    <field dbtable="CMS_CARD" name="distributePersonId" name-db="distributePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="派发人" description="" ID="263071E321D935BC2A6211611CC984D7" />
    <field dbtable="CMS_CARD" name="distributePerson" name-db="distributePerson" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="派发人姓名" description="" ID="08109D1D86C7AD066654D4633794AB60" />
    <field dbtable="CMS_CARD" name="responsiblePersonId" name-db="responsiblePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="责任人" description="" ID="F7CF8C81CF4A543EF8F20A535A605C5B" />
    <field dbtable="CMS_CARD" name="responsiblePerson" name-db="responsiblePerson" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="责任人姓名" description="" ID="14FEBDFDA138ADF51F4D5D044CBA4C3B" />
    <field dbtable="CMS_CARD" name="distributeDate" name-db="distributeDate" type="date" primary-key="false" keep-valid="false" author="" title="分发日期" description="" ID="1B27A458C3C5E97B4632B01376F17623" />
    <field dbtable="CMS_CARD" name="usedPersonId" name-db="usedPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="使用人" description="" ID="FE2AB28A196152F2FEDAD53CF35E0ECF" />
    <field dbtable="CMS_CARD" name="usedPerson" name-db="usedPerson" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="使用人名称" description="" ID="CE7B51EBC8C84A0A25DE33E159FEF0A1" />
    <field dbtable="CMS_CARD" name="usedDate" name-db="usedDate" type="date" primary-key="false" keep-valid="false" author="" title="使用日期" description="" ID="20A78576427F5F52E1492D4D8B5E894A" />
    <field dbtable="CMS_CARD" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点id" description="" ID="25D17756A3FD8FC91C4A2A25F9EAA705" />
    <field dbtable="CMS_CARD" name="stationName" name-db="stationName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="E8574AE6D0B0AA8E3BAC51BC22136301" />
    <field dbtable="CMS_CARD" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="7CF664972C74F8CE2051BA9994097117" />
    <field dbtable="CMS_CARD" name="stopDate" name-db="stopDate" type="date" primary-key="false" keep-valid="false" author="" title="截止日期" description="" ID="F60E72DE34B4CE0A5658AD2507ADF7C5" />
    <field dbtable="CMS_CARD" name="lastDate" name-db="lastDate" type="long" primary-key="false" keep-valid="false" author="" title="有效期至" description="" ID="28BCB2E5C1A723D46473D02853CEC747" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_CARD" name-db="CMS_CARD" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

