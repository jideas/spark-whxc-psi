<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_ACCOUNT" ID="0549982927C21A302C2F9F68499E3103" name-db="CMS_MEMBER_ACCOUNT" category="业务主体" title="财务账户" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_ACCOUNT" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_ACCOUNT" name="moneyBalance" name-db="moneyBalance" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="账户余额" description="" ID="4EFCE575EF17798ED0947E8410608FF2">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_MEMBER_ACCOUNT" name="vantages" name-db="vantages" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="积分" description="" ID="FFBC6B5FA168370B256A6CC62E60EFDA">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_MEMBER_ACCOUNT" name="payPassword" name-db="payPassword" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="支付密码" description="" ID="6BE4DD4B235E8BE9AB0F921DF7BCF9BC" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_ACCOUNT" name-db="CMS_MEMBER_ACCOUNT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

