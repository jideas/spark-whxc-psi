<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Deliver_Sheet" ID="7844B8D9C8FB0C96727E2B7680B4360B" name-db="PSI_Deliver_Sheet" category="" title="配送单" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Deliver_Sheet" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Deliver_Sheet" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="78CDFCEE79C62CF750B46B042770CBD8" />
    <field dbtable="PSI_Deliver_Sheet" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="2CD5CB85F635F99A3FAC2AB43B0BC2EF" />
    <field dbtable="PSI_Deliver_Sheet" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="发货人Id" description="" ID="14DF5BAD148A14ABE88B1079490E6854" />
    <field dbtable="PSI_Deliver_Sheet" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="发货人" description="" ID="03E9B58362DC40431F4D96964E7C54C0" />
    <field dbtable="PSI_Deliver_Sheet" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="发货日期" description="" ID="E85200BD753B2AEF2D26FDBF76332466" />
    <field dbtable="PSI_Deliver_Sheet" name="finishDate" name-db="finishDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="FD14F13956CA07EE1700F418B5BD0220" />
    <field dbtable="PSI_Deliver_Sheet" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点ID" description="" ID="4CD2DB19A5C5F93F5D65BE77FBDB8894" />
    <field dbtable="PSI_Deliver_Sheet" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="89272404F51CA7D3DB0F6C6160929932" />
    <field dbtable="PSI_Deliver_Sheet" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="AEAC68B68A189E44FED22AB6ED6C5BF6" />
    <field dbtable="PSI_Deliver_Sheet" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="4FCB7F7EF09C2BEBD9145C21F61502AF" />
    <field dbtable="PSI_Deliver_Sheet" name="deliverPersonId" name-db="deliverPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="配送人Id" description="" ID="399ACC08D4E5792F7E0F0A94ACE282A3" />
    <field dbtable="PSI_Deliver_Sheet" name="deliverPerson" name-db="deliverPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="配送人" description="" ID="E7385FCCB8450FC42ABCD13FF02E07EE" />
    <field dbtable="PSI_Deliver_Sheet" name="planDate" name-db="planDate" type="date" primary-key="false" keep-valid="false" author="" title="预计到货日期" description="" ID="907EF71248F56FD7723FB143B9F5B07F" />
    <field dbtable="PSI_Deliver_Sheet" name="deliverDate" name-db="deliverDate" type="date" primary-key="false" keep-valid="false" author="" title="配送日期" description="" ID="56B76FCF33A6C0EB81D46DFAE97873E2" />
    <field dbtable="PSI_Deliver_Sheet" name="consigneeId" name-db="consigneeId" type="guid" primary-key="false" keep-valid="false" author="" title="收货人Id" description="" ID="C5F997816323DE4B7B99F8F2979405DB" />
    <field dbtable="PSI_Deliver_Sheet" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="8F395E466F339B8FA73818021AC13D35" />
    <field dbtable="PSI_Deliver_Sheet" name="consigneeDate" name-db="consigneeDate" type="date" primary-key="false" keep-valid="false" author="" title="收货日期" description="" ID="5FB5E91C52DC0287445F1CCFF2276896" />
    <field dbtable="PSI_Deliver_Sheet" name="deliveredPackageCount" name-db="deliveredPackageCount" type="int" primary-key="false" keep-valid="false" author="" title="发货箱数" description="" ID="345041216D123A0DA9E78687A6766188" />
    <field dbtable="PSI_Deliver_Sheet" name="receivedPackageCount" name-db="receivedPackageCount" type="int" primary-key="false" keep-valid="false" author="" title="收货箱数" description="" ID="B581BE44CE048CF4438A16B75C8A63D8" />
    <field dbtable="PSI_Deliver_Sheet" name="description" name-db="description" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="异常情况" description="" ID="7C4269D70175D0FF8E5BB0A1EE9EA6EE" />
    <field dbtable="PSI_Deliver_Sheet" name="formula" name-db="formula" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="处理方案" description="" ID="E046E07E21045553F2AA367890B51619" />
    <field dbtable="PSI_Deliver_Sheet" name="handlerId" name-db="handlerId" type="guid" primary-key="false" keep-valid="false" author="" title="处理人ID" description="" ID="44A6ADEB28D6E5C4CFECE7C5316A0DF5" />
    <field dbtable="PSI_Deliver_Sheet" name="handler" name-db="handler" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="处理人" description="" ID="3398905C66EB7B08373D107A38A4BC17" />
    <field dbtable="PSI_Deliver_Sheet" name="handleDate" name-db="handleDate" type="date" primary-key="false" keep-valid="false" author="" title="处理时间" description="" ID="621E1A738474E738785CF625170FB1CD" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Deliver_Sheet" name-db="PSI_Deliver_Sheet" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

