<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_DELIVER_LOG" ID="DB31CF09F09F5FBB9164690426FC5A99" name-db="CMS_MEMBER_DELIVER_LOG" category="业务主体" title="送货上门记录" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_DELIVER_LOG" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="B0B861B1CD211318FF080D72491C56D3" />
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="deliveryId" name-db="deliveryId" type="guid" primary-key="false" keep-valid="false" author="" title="deliveryId" description="" ID="D1D027C622A3F4B1C03126E2A11EAB9D" />
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据id" description="" ID="ABF51BC4A58DDCFDE4B06C40BC3E3EBC" />
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据号" description="" ID="CB47D0EE0A3BED3FBC5C8E6DBFD49B16" />
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="count" name-db="count" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="次数" description="" ID="FA73C63082E76915191A8A2BA06CA2A3" />
    <field dbtable="CMS_MEMBER_DELIVER_LOG" name="occurDate" name-db="occurDate" type="date" primary-key="false" keep-valid="false" author="" title="发生日期" description="" ID="E58D8DB316A846ADC87C4BC8CD88EE2D" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_DELIVER_LOG" name-db="CMS_MEMBER_DELIVER_LOG" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

