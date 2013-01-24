<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="B2C_Base_Member" ID="F23C3805CB73AB55539CC9198E629F2C" name-db="B2C_Base_Member" category="基础数据主体" title="会员信息表" author="" description="" packagename="com.spark.b2c.storage.base.member" name="B2C_Base_Member" isneedrebuild="false">
  <fields>
    <field dbtable="B2C_Base_Member" name="userId" name-db="userId" type="guid" primary-key="false" keep-valid="false" author="" title="用户id" description="" ID="B314067CC225A2A2666FCEF710720A55" />
    <field dbtable="B2C_Base_Member" name="realName" name-db="realName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="真实姓名" description="" ID="D83E97C7D0F7C0D6F6BE02C27C1913A5" />
    <field dbtable="B2C_Base_Member" name="username" name-db="username" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="用户名" description="" ID="31CD3677572054B1B88F2313340A5F6D" />
    <field dbtable="B2C_Base_Member" name="mobile" name-db="mobile" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="手机号码" description="" ID="AD7F34BA175E5544B2BD9A472ED5F100" />
    <field dbtable="B2C_Base_Member" name="email" name-db="email" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="邮箱地址" description="" ID="B315668E80153B4C52738885557A9D8C" />
    <field dbtable="B2C_Base_Member" name="extendCode" name-db="extendCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="推广码" description="" ID="DBDF9F457B2239972E05AFBD29DCA302" />
    <field dbtable="B2C_Base_Member" name="level" name-db="level" type="char(2)" primary-key="false" keep-valid="false" author="" title="会员等级" description="" ID="DCDF10A3195D4E56E1EB805F54B694ED" />
    <field dbtable="B2C_Base_Member" name="sex" name-db="sex" type="boolean" primary-key="false" keep-valid="false" author="" title="性别" description="" ID="D8F48B1DB4AF30B97E20C58AF8E6D276" />
    <field dbtable="B2C_Base_Member" name="birthday" name-db="birthday" type="date" primary-key="false" keep-valid="false" author="" title="生日" description="" ID="DF1136A4ECE1D7A8A739B5CD1F6270DA" />
    <field dbtable="B2C_Base_Member" name="subscribeFlag" name-db="subscribeFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否订阅邮件" description="" ID="9F0481B16A17D4C90243F169DC5DC5C8" />
    <field dbtable="B2C_Base_Member" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="92E28566E114BB91A72564ECB034B7ED" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="B2C_Base_Member" name-db="B2C_Base_Member" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

