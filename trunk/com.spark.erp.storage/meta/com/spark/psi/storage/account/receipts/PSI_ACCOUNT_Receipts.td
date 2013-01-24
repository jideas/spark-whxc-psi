<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Receipts" ID="350913428035530EE99B2BFAB26043EF" name-db="PSI_ACCOUNT_Receipts" category="基础数据主体" title="收款通知单" author="" description="" packagename="com.spark.psi.storage.account.receipts" name="PSI_ACCOUNT_Receipts" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Receipts" name="receiptsNo" name-db="receiptsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="D74E579B965CB53272D304C78875CF1C" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="收款对象" description="" ID="3447034B1C8F808CFD1EF4062ED029D4" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="partnerType" name-db="partnerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="对象类型" description="" ID="E7ACB6F8C67BC050C8BB9CA2112899E3" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="partnerName" name-db="partnerName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="对象名称" description="" ID="FC72BD349B2C473663B8524538ABF044" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="pNamePy" name-db="pNamePy" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="对象名拼音" description="" ID="9FFE22DC480689096F6B8A8324D8A445" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="partnerShortName" name-db="partnerShortName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="对象简称" description="" ID="5353E51AE177728302C49A4FDF689C01" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="receiptMode" name-db="receiptMode" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款方式" description="" ID="0C61A3863FE03ECA464601D19D7CAA88" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="receiptType" name-db="receiptType" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款类型" description="" ID="0ED926C36C8A8A4B9DC8457F417E3049" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="reason" name-db="reason" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款原因" description="" ID="FF0F7ABD3BE301D6BE188AB71FB92B3D" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="receiptDate" name-db="receiptDate" type="date" primary-key="false" keep-valid="false" author="" title="收款日期" description="" ID="C6DE8114AF84F35FCF724A8BE921C6EA" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="3733241FFCDBACCDE7BEFC1771960481" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="B0E5BE10C4522735B2568768F6D583A7" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="receiptedAmount" name-db="receiptedAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已收金额" description="" ID="C3D693E9AFB52FC8C96859D0C62C82C9">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_ACCOUNT_Receipts" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="7AAF82AA1ABC6174FFF036E582BE1EB9" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="F3A3E456CD5FE06A6E9FDF7FBCFDEFE9" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="B2AED5168D8D9C3D3FA0DF35A1A02B73" />
    <field dbtable="PSI_ACCOUNT_Receipts" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="B9BC46BF4626E9C2E473B636E9BFB578" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Receipts" name-db="PSI_ACCOUNT_Receipts" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

