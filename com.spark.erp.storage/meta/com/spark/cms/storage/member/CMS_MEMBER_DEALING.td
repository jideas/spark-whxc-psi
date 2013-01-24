<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_DEALING" ID="1E1320DD38A744A43A56182FBC8F884A" name-db="CMS_MEMBER_DEALING" category="业务主体" title="往来记录" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_DEALING" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_DEALING" name="memberID" name-db="memberID" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="C0362D616BB39ACE3D32FDB77ADBBC15" />
    <field dbtable="CMS_MEMBER_DEALING" name="dealType" name-db="dealType" type="char(2)" primary-key="false" keep-valid="false" author="" title="往来类型" description="" ID="37A12302BDDC9D45D11301D4CDBDA8F1" />
    <field dbtable="CMS_MEMBER_DEALING" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="面值卡、订单id" description="" ID="A173893C1CA85A685CD19024B37296F0" />
    <field dbtable="CMS_MEMBER_DEALING" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单号" description="" ID="E36351C3F6E40635FBE510C17586450F" />
    <field dbtable="CMS_MEMBER_DEALING" name="amount" name-db="amount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="AB4E8C13AAAFB88A8CCBEFCDD13E64B3" />
    <field dbtable="CMS_MEMBER_DEALING" name="occurDate" name-db="occurDate" type="date" primary-key="false" keep-valid="false" author="" title="发生日期" description="" ID="2205801796FDAC25830442F506164355" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_DEALING" name-db="CMS_MEMBER_DEALING" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

