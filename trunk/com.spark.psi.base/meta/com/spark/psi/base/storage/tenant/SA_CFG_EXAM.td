<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_CFG_EXAM" ID="EFE5BD20C47177156183C3977A6C6D2C" name-db="SA_CFG_EXAM" category="" title="审核流程配置表" author="" description="" packagename="com.spark.psi.base.storage.tenant" name="SA_CFG_EXAM" isneedrebuild="false">
  <fields>
    <field dbtable="SA_CFG_EXAM" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="true" keep-valid="true" author="" title="租户编号1" description="" ID="9571E7DDAE9AF5BAB3893251F8322543" />
    <field dbtable="SA_CFG_EXAM" name="modeid" name-db="modeid" type="char(2)" primary-key="true" keep-valid="true" author="" title="模块id" description="" ID="5A45ECF14FEC3476CD4FF344D9A30A60" />
    <field dbtable="SA_CFG_EXAM" name="isOpenExam" name-db="isOpenExam" type="boolean" primary-key="false" keep-valid="false" author="" title="是否开启审核" description="" ID="BF98CDF961778E32E6F645BC4A8A422C" />
    <field dbtable="SA_CFG_EXAM" name="maxAmount" name-db="maxAmount" type="double" primary-key="false" keep-valid="false" author="" title="金额上限" description="" ID="771A8E083C0B30961A37CC566D2A3940" />
    <field dbtable="SA_CFG_EXAM" name="updatePerson" name-db="updatePerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="最后修改人" description="" ID="B1F94FC9739BE698047439C20045B9A0" />
    <field dbtable="SA_CFG_EXAM" name="updateDate" name-db="updateDate" type="date" primary-key="false" keep-valid="false" author="" title="修改时间" description="" ID="B4659A502008410C33FC6CA50976EC35" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_CFG_EXAM" name-db="SA_CFG_EXAM" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

