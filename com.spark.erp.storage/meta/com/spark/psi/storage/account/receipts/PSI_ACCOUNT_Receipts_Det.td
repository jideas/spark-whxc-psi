<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Receipts_Det" ID="1B6E6AA48D16409C49A66B6C2A1012DD" name-db="PSI_ACCOUNT_Receipts_Det" category="基础数据主体" title="收款明细" author="" description="" packagename="com.spark.psi.storage.account.receipts" name="PSI_ACCOUNT_Receipts_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="receiptsId" name-db="receiptsId" type="guid" primary-key="false" keep-valid="false" author="" title="申请单id" description="" ID="B0FC8EF412E65B5671FC01CC716FBEB4" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="checkoutSheetId" name-db="checkoutSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="出库单id" description="" ID="8D116552E6E2A8F55FF41A11B6D5CACE" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="出库单号" description="" ID="B3E0441786CBE04BFEC258B92CE6E437" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="relevantBillId" name-db="relevantBillId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据Id" description="" ID="3F8CC20883A512F116B60DCC391E4A37" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="relevantBillNo" name-db="relevantBillNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据" description="" ID="CBEFD3D09FCFDCC6F2925EAA24804C8F" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="checkoutDate" name-db="checkoutDate" type="date" primary-key="false" keep-valid="false" author="" title="出库日期" description="" ID="E1D0E3BD2840006BC7000314DC25AE9B" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="EBD12C3D0C794AA77D68333FD03488B4" />
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="receiptedAmount" name-db="receiptedAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已收金额" description="" ID="A2C93BC5D9FA599AE63E30A8A4074178">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_ACCOUNT_Receipts_Det" name="molingAmount" name-db="molingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抹零金额" description="" ID="F553FB3A51CED810D09540DC76D08FC9" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Receipts_Det" name-db="PSI_ACCOUNT_Receipts_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

