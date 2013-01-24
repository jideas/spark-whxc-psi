<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_CREDIT" ID="B52D48894E23EAE8EA3F2692A03A5232" name-db="SA_SALE_CREDIT" category="" title=" " author="" description="销售授权" packagename="com.spark.psi.base.storage.tenant" name="SA_SALE_CREDIT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_CREDIT" name="tenantsGUID" name-db="tenantsGUID" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="CF43EA5509C5C353700C973BCBF2C2AE" />
    <field dbtable="SA_SALE_CREDIT" name="customerCreditLimit" name-db="customerCreditLimit" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="客户信用额度" description="" ID="AB31F690C80345D8A13FAD1C5BF9BB6C" />
    <field dbtable="SA_SALE_CREDIT" name="availableTotalCreditLimit" name-db="availableTotalCreditLimit" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="累计可设置信用额度" description="" ID="425E20F83961E9D109892DDB23E3E816" />
    <field dbtable="SA_SALE_CREDIT" name="customerCountUsed" name-db="customerCountUsed" type="int" primary-key="false" keep-valid="false" author="" title="已设置信用额度客户数" description="" ID="F9045421E544A7FCAF7B9B2D8EEF9176" />
    <field dbtable="SA_SALE_CREDIT" name="customerCreditUsed" name-db="customerCreditUsed" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已分配信用额度" description="" ID="6F111D838F184F39A5D34F3D5FD82C11" />
    <field dbtable="SA_SALE_CREDIT" name="customerCreditDayLimit" name-db="customerCreditDayLimit" type="int" primary-key="false" keep-valid="false" author="" title="客户最长账期" description="" ID="E3790226FA98CAC92E8D7F05D5D758BC" />
    <field dbtable="SA_SALE_CREDIT" name="orderApprovalLimit" name-db="orderApprovalLimit" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单审批限额" description="" ID="F8F532151F1C6CB64E1F9E0E9DDF5151" />
  </fields>
  <indexs>
    <index author="" name="INDEX_CREDIT" name-db="INDEX_CREDIT" title="INDEX_CREDIT" unique="false" description="">
      <index-item fieldID="CF43EA5509C5C353700C973BCBF2C2AE" field="tenantsGUID" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_CREDIT" name-db="SA_SALE_CREDIT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

