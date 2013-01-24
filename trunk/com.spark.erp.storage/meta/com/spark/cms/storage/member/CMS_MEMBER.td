<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER" ID="93042AF374377F3A2E0BF8507AEBCBA9" name-db="CMS_MEMBER" category="业务主体" title="登陆用户表" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER" name="username" name-db="username" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="登陆名" description="" ID="CEFFFC3532C337E75A173C5ED7D5236E" />
    <field dbtable="CMS_MEMBER" name="password" name-db="password" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="密码" description="" ID="CFFC38CE399FAEB5A39489145C7C0BDB" />
    <field dbtable="CMS_MEMBER" name="memberName" name-db="memberName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="会员名" description="" ID="7244BF20BCC356A3555686CE781BFFDB" />
    <field dbtable="CMS_MEMBER" name="mobile" name-db="mobile" type="nvarchar(15)" primary-key="false" keep-valid="false" author="" title="手机号" description="" ID="71ADD0288551A4104536183032F5DF21" />
    <field dbtable="CMS_MEMBER" name="email" name-db="email" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="邮箱" description="" ID="962ECD5AEDCD083890E9C96560A57218" />
    <field dbtable="CMS_MEMBER" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="41C73425AD4241C269DF085E83C8C446" />
    <field dbtable="CMS_MEMBER" name="invitationCode" name-db="invitationCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="邀请码" description="" ID="B73B358D52C3FDB16205B586B29A32A4" />
    <field dbtable="CMS_MEMBER" name="isOffical" name-db="isOffical" type="boolean" primary-key="false" keep-valid="false" author="" title="正式会员" description="" ID="720B8361FD78601435288FB4C9524ED7" />
    <field dbtable="CMS_MEMBER" name="code" name-db="code" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="1CBE719C0C545910946C816846C8A971" />
    <field dbtable="CMS_MEMBER" name="lastLoginDate" name-db="lastLoginDate" type="date" primary-key="false" keep-valid="false" author="" title="最后登录时间" description="" ID="AF1A7E5DFDB807AF5039AEE2C57C91B7" />
    <field dbtable="CMS_MEMBER" name="checkedMobile" name-db="checkedMobile" type="boolean" primary-key="false" keep-valid="false" author="" title="" description="" ID="37801FDC6EA677EAD01ADAF602B1A6E6" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER" name-db="CMS_MEMBER" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

