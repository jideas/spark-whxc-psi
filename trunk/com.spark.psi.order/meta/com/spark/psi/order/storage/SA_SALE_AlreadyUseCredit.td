<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_AlreadyUseCredit" ID="141EC04350989B157B2C13B350C404ED" name-db="SA_SALE_AlreadyUseCredit" category="业务主体" title="已用信用额度维护表" author="" description="" packagename="com.spark.psi.order.storage" name="SA_SALE_AlreadyUseCredit" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_AlreadyUseCredit" name="customerId" name-db="customerId" type="guid" primary-key="true" keep-valid="true" author="" title="客户id" description="" ID="2325C507CAA79DDF82A77950E7664DED" />
    <field dbtable="SA_SALE_AlreadyUseCredit" name="aleardyUseCredit" name-db="aleardyUseCredit" type="numeric(17,2)" primary-key="false" keep-valid="true" author="" title="已用信用额度" description="" ID="856A6BFCE265DA344F98B114CAEFC81A">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_AlreadyUseCredit" name-db="SA_SALE_AlreadyUseCredit" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

