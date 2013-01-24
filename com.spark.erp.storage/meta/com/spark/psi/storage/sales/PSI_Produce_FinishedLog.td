<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Produce_FinishedLog" ID="9CCC7615E9637BA9406C555BA016E189" name-db="PSI_Produce_FinishedLog" category="" title="生产订单完成商品记录" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Produce_FinishedLog" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Produce_FinishedLog" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="DEC04D50C6B68C33DE3643D3918D2663" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="10B9F04D6EA88E537A1BA2A7CC0A7AC8" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="9413EDFB51BBF0F0B12915DC37216703" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="9F0C5CEF838785AEB35426C443EA266B" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="2298D9E4AEA3E2A195DEF6F5342977D1" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="62E58BA7B408DB6E07AE1E4DD5BE7790" />
    <field dbtable="PSI_Produce_FinishedLog" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="9584DAA27C47E32C8C942A1A8C3CBDCC" />
    <field dbtable="PSI_Produce_FinishedLog" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="B1EDE322317771FC6651A2DD608C14D5" />
    <field dbtable="PSI_Produce_FinishedLog" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="C0A6FD58B6B8AA9BAF4D4520BF3168D1" />
    <field dbtable="PSI_Produce_FinishedLog" name="bomId" name-db="bomId" type="guid" primary-key="false" keep-valid="false" author="" title="bom表Id" description="" ID="C2FF246B9E43E5A580DA4B9A34057968" />
    <field dbtable="PSI_Produce_FinishedLog" name="thistimeCount" name-db="thistimeCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="本次完成数量" description="" ID="0F7446C0F073D08BD04C307622C92C5C" />
    <field dbtable="PSI_Produce_FinishedLog" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="经手人" description="" ID="68CDD30E0232A5CF2804A8C4F377B9FD" />
    <field dbtable="PSI_Produce_FinishedLog" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="经手人姓名" description="" ID="54939570B7F10E7CFBEE0746B8CCCB0F" />
    <field dbtable="PSI_Produce_FinishedLog" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="日期" description="" ID="3FA0FE80133C10E77F57E49001D5AD2B" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Produce_FinishedLog" name-db="PSI_Produce_FinishedLog" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

