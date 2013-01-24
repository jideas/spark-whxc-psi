<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_VANTAGES" ID="EA2256F09EC4808E36DA116A30037A53" name-db="CMS_MEMBER_VANTAGES" category="业务主体" title="积分记录" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_VANTAGES" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_VANTAGES" name="memberID" name-db="memberID" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="7903A1CCA03CA487636AA6C9E76A1B77" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="vType" name-db="vType" type="char(2)" primary-key="false" keep-valid="false" author="" title="积分类型" description="" ID="2B9E4FF228EE1C0F6B3200FBB2E64281" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="面值卡、订单id" description="" ID="0D732C6F2F52E761612D8CC50DD4B2E5" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单号" description="" ID="925C4E740143766546EE612789A7522B" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="vantages" name-db="vantages" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="积分" description="" ID="A22089CFEEE69390A25EA0F32580D0D8" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="occurDate" name-db="occurDate" type="date" primary-key="false" keep-valid="false" author="" title="发生日期" description="" ID="5FBBDB1C58172AE6171BEE0B7B7088EF" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="modifyPerson" name-db="modifyPerson" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="051892A73BFB9025570787B281EA2B9B" />
    <field dbtable="CMS_MEMBER_VANTAGES" name="modifyPersonId" name-db="modifyPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="ECDB266ED9F5AF54395964D543D9F451" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_VANTAGES" name-db="CMS_MEMBER_VANTAGES" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

