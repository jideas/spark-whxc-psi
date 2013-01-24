<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_ADDRESS" ID="3CE587DC5BF0E310F374014BE15F0314" name-db="CMS_MEMBER_ADDRESS" category="业务主体" title="收货地址管理" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_ADDRESS" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_ADDRESS" name="memberid" name-db="memberid" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="EB87295BE610F96D855FFCBF0B4F99DD" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="consignee" name-db="consignee" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="406887824BBDCDECC870999DF5F4534A" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省" description="" ID="A950D2C25FC519C7BFFEB25C64A84DAD" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="市" description="" ID="58A9CA00534CD4C9D177310B911AB778" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="town" name-db="town" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="区" description="" ID="1BA5883B246BAD042B0A6198672BAC92" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点id" description="" ID="7EE29E11C852BDD784A00C485E65DF97" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="9682BE79B4550511478439F2B8C45660" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="postcode" name-db="postcode" type="nvarchar(6)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="1BACA134FA1281370F8052A7B3208E4B" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="mobile" name-db="mobile" type="nvarchar(15)" primary-key="false" keep-valid="false" author="" title="手机号码" description="" ID="4B1BAE56DB58B57EBE80C9B60C19DEF0" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="telephone" name-db="telephone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="固定电话" description="" ID="0A70600F742C2997ADFC5D80F00A527C" />
    <field dbtable="CMS_MEMBER_ADDRESS" name="isDefault" name-db="isDefault" type="boolean" primary-key="false" keep-valid="false" author="" title="是否默认" description="" ID="766628E1AD80E19E0E3EF576DFD5137B" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_ADDRESS" name-db="CMS_MEMBER_ADDRESS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

