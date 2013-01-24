<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Produce_Order" ID="77AC483390C61D02CFE39834C65C77B8" name-db="PSI_Produce_Order" category="" title="生产订单" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Produce_Order" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Produce_Order" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="订单编号" description="" ID="4D45DB4E5DF4CE002BA94E153D0A8C55" />
    <field dbtable="PSI_Produce_Order" name="planDate" name-db="planDate" type="date" primary-key="false" keep-valid="false" author="" title="计划完成日期" description="" ID="298CC4740A8158BE7023D78032C25B44" />
    <field dbtable="PSI_Produce_Order" name="goodsCount" name-db="goodsCount" type="int" primary-key="false" keep-valid="false" author="" title="商品数量" description="" ID="C6B73D065861B4AD6AB985116A783634" />
    <field dbtable="PSI_Produce_Order" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="CE5A6B4204C703EB4CB3548685A06D25" />
    <field dbtable="PSI_Produce_Order" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="785C1C0D5E58F7CC8365B9F790E56BF4" />
    <field dbtable="PSI_Produce_Order" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="457002EBE51B11B51BF932A08C7E437A" />
    <field dbtable="PSI_Produce_Order" name="finishDate" name-db="finishDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="5568F33414F19108640F4B34F9CC0E03" />
    <field dbtable="PSI_Produce_Order" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="CBBB6BF5B5B41C9AF307ABC768108E5F" />
    <field dbtable="PSI_Produce_Order" name="rejectReason" name-db="rejectReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="B4E7FD0CCD066007EA2C2F35FBA5072C" />
    <field dbtable="PSI_Produce_Order" name="approvePerson" name-db="approvePerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="审核人" description="" ID="14A1DBBB308DC281D5F4912E11F74206" />
    <field dbtable="PSI_Produce_Order" name="approveDate" name-db="approveDate" type="date" primary-key="false" keep-valid="false" author="" title="审核时间" description="" ID="3216FA408D88FDCC4EFA442D249A2D07" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Produce_Order" name-db="PSI_Produce_Order" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

