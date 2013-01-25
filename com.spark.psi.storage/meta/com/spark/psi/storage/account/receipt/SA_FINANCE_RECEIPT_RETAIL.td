<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_FINANCE_RECEIPT_RETAIL" ID="45583BF6A775A292513951D3E636B232" name-db="SA_FINANCE_RECEIPT_RETAIL" category="业务主体" title="零售收款记录表" author="" description="" packagename="com.spark.psi.storage.account.receipt" name="SA_FINANCE_RECEIPT_RETAIL" isneedrebuild="false">
  <fields>
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="EFCBF35FCECD8C141EA0EAF349F9A3CB" />
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="saleEmpGuid" name-db="saleEmpGuid" type="guid" primary-key="false" keep-valid="false" author="" title="销售员工编号" description="" ID="4647F36B5D23D7FE25663ADB88E77E19" />
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="saleEmpName" name-db="saleEmpName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="销售员工名称" description="" ID="0730DACC5CFF5B610E0676A755ACCF40" />
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="receiptDate" name-db="receiptDate" type="date" primary-key="false" keep-valid="false" author="" title="交款期间" description="" ID="926F6990332C47A7CCCD989B4C7DB5A1" />
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="shouldMoney" name-db="shouldMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应交现金" description="" ID="0E8223C5AD3833FD0EC644C4697DA726">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="shouldCardCount" name-db="shouldCardCount" type="int" primary-key="false" keep-valid="false" author="" title="应交刷卡底单（数量）" description="" ID="73B507D37A7BD73BBDE4419D644DD523">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="shouldCardMoney" name-db="shouldCardMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应交刷卡底单（金额）" description="" ID="17AB0D2D9894A30DF8B5E60F5BB1D846">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="DE49AA2E06780D89165DA675C7D46C3F" />
    <field dbtable="SA_FINANCE_RECEIPT_RETAIL" name="retailGuid" name-db="retailGuid" type="guid" primary-key="false" keep-valid="false" author="" title="零售单据编号" description="" ID="6DDAFA4E411856E3ABF62607AD7AA89D" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_FINANCE_RECEIPT_RETAIL" name-db="SA_FINANCE_RECEIPT_RETAIL" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

