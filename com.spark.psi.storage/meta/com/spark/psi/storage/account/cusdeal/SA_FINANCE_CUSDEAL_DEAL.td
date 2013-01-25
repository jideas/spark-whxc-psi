<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_FINANCE_CUSDEAL_DEAL" ID="FEBB465A0F662A757DC2C9608C652AD7" name-db="SA_FINANCE_CUSDEAL_DEAL" category="业务主体" title="往来明细" author="" description="往来明细" packagename="com.spark.psi.storage.account.cusdeal" name="SA_FINANCE_CUSDEAL_DEAL" isneedrebuild="false">
  <fields>
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="092DB6E1210C93B47459689A938B2C16" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商GUID" description="" ID="495802D8AD2D0794F87080C860060877" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="billsGuid" name-db="billsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="单据GUID" description="" ID="4DFCDC57F402DC5D84210F364EF3A866" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="billsNo" name-db="billsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="AD15C1418C25A788696166AAA7371CD5" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="billsType" name-db="billsType" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="单据类型" description="" ID="E9605A0AB3323B86078DC000ABFD75A1" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="receiptType" name-db="receiptType" type="char(10)" primary-key="false" keep-valid="false" author="" title="收款方式" description="" ID="4A7BC1A3438A0862AAC6EF2260496918" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="planAmount" name-db="planAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付金额" description="" ID="3B03B4317B72EBE710EB7B6CD02BBD9F" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="realAmount" name-db="realAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="实收/实付金额" description="" ID="501503506878F97D0003DEC738E27D7E" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="balance" name-db="balance" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付余额" description="" ID="FB364570A900C78686B04D6C0CABABA3" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="日期" description="" ID="7D6B5DC21F77FF0FBAA1BB4D792C7D1B" />
    <field dbtable="SA_FINANCE_CUSDEAL_DEAL" name="remark" name-db="remark" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="03896F20E3EE5CC49FB8F23EB08EAC84" />
  </fields>
  <indexs>
    <index author="" name="index_finance_cusdel" name-db="index_finance_cusdel" title="index_finance_cusdel" unique="false" description="">
      <index-item fieldID="092DB6E1210C93B47459689A938B2C16" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="495802D8AD2D0794F87080C860060877" field="cusproGuid" field-author="" desc="false" />
      <index-item fieldID="7D6B5DC21F77FF0FBAA1BB4D792C7D1B" field="createDate" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_FINANCE_CUSDEAL_DEAL" name-db="SA_FINANCE_CUSDEAL_DEAL" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

