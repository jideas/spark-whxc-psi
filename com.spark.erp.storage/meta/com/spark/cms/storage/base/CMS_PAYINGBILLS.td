<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_PAYINGBILLS" ID="781076581CC09DB04372AD0BFD9491DB" name-db="CMS_PAYINGBILLS" category="业务主体" title="付款中间表" author="" description="" packagename="com.spark.cms.storage.base" name="CMS_PAYINGBILLS" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_PAYINGBILLS" name="payType" name-db="payType" type="char(2)" primary-key="false" keep-valid="false" author="" title="支付类型" description="" ID="430C6C8591B022A18D1ECFCE1B9A8C03" />
    <field dbtable="CMS_PAYINGBILLS" name="amount" name-db="amount" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="金额字符串" description="" ID="E9A94524B2EF4B982C2A65C4E4BD7427" />
    <field dbtable="CMS_PAYINGBILLS" name="createTime" name-db="createTime" type="long" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="47EA77E803B94FA640EEE9A162C9D80F" />
    <field dbtable="CMS_PAYINGBILLS" name="orderId" name-db="orderId" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="支付单号" description="" ID="621B8D1F2DA2AD00038138F8E0A6F3F9" />
    <field dbtable="CMS_PAYINGBILLS" name="transDate" name-db="transDate" type="nvarchar(8)" primary-key="false" keep-valid="false" author="" title="支付日期" description="" ID="275B31B8E1A41A087858DFA68066A6C8" />
    <field dbtable="CMS_PAYINGBILLS" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理状态" description="" ID="F4697642290187802334492AE351C80B" />
    <field dbtable="CMS_PAYINGBILLS" name="relaBillsId" name-db="relaBillsId" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="" description="" ID="2C85526E46D31DF33FF70EC4D6A4DB79" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_PAYINGBILLS" name-db="CMS_PAYINGBILLS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

