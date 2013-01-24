<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_MEMBER_DELIVERY" ID="9E7885F7C930C1B0221DEA136288796A" name-db="CMS_MEMBER_DELIVERY" category="业务主体" title="送货上门数据" author="" description="" packagename="com.spark.cms.storage.member" name="CMS_MEMBER_DELIVERY" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_MEMBER_DELIVERY" name="member" name-db="member" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="39C88B566A7CE77604B9BF15E72A767A" />
    <field dbtable="CMS_MEMBER_DELIVERY" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="开始日期" description="" ID="4CCAC7E734F4B925F1F97593003723C3" />
    <field dbtable="CMS_MEMBER_DELIVERY" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="结束日期" description="" ID="6F56E695F55B47ABE33051ED55EC06A2" />
    <field dbtable="CMS_MEMBER_DELIVERY" name="dcount" name-db="dcount" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="次数" description="" ID="639FC5920CE68BE01B470695A49B99AC" />
    <field dbtable="CMS_MEMBER_DELIVERY" name="counted" name-db="counted" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="已用次数" description="" ID="F6C38302741173189175C1B880A1F3FB" />
    <field dbtable="CMS_MEMBER_DELIVERY" name="lockedDeliveryBalance" name-db="lockedDeliveryBalance" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="锁定上门送货包月次数" description="" ID="8A9E24C63A969B710BC23889158BEB12">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_MEMBER_DELIVERY" name-db="CMS_MEMBER_DELIVERY" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

