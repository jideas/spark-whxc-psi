<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Produce_MaterialDet" ID="AA18658B87368679563EA48A603D4373" name-db="PSI_Produce_MaterialDet" category="" title="生产订单材料明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Produce_MaterialDet" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Produce_MaterialDet" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="65874231BF3F418B52B4FE7FEC489BB2" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialId" name-db="MaterialId" type="guid" primary-key="false" keep-valid="false" author="" title="商品Guid" description="" ID="0525565FBA6A6AF83C714F2FDAEB86C6" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialCode" name-db="MaterialCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="021CD405730002F96AA142B22E7410E8" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialNo" name-db="MaterialNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="16DA90D358B6DD98A74461EF1F9E0670" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialName" name-db="MaterialName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="7B57E1487958564B87DBF964C49A4F53" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialSpec" name-db="MaterialSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="32FCB734D74012581CADFA109A2E4874" />
    <field dbtable="PSI_Produce_MaterialDet" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="86A8D2D33259CE61C9929EC87D46AE0F" />
    <field dbtable="PSI_Produce_MaterialDet" name="MaterialScale" name-db="MaterialScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="95A1CA2AB3043B3BD28F0B60D52EB5A4" />
    <field dbtable="PSI_Produce_MaterialDet" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="F9DBEE07CB8A0B42F801145EC3C39729" />
    <field dbtable="PSI_Produce_MaterialDet" name="receivedCount" name-db="receivedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="已领数量" description="" ID="5CB4D3DB0AEAFBED9107FCC46634C28B">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Produce_MaterialDet" name="returnedCount" name-db="returnedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="已退数量" description="" ID="0A2CB7AA4F2B752309F6191EB569CC4C">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Produce_MaterialDet" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库ID" description="" ID="00C732C941CA06678D649396000BB6D2" />
    <field dbtable="PSI_Produce_MaterialDet" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="3DB5D78758B119AE4769E8A2B6830AF3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Produce_MaterialDet" name-db="PSI_Produce_MaterialDet" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

