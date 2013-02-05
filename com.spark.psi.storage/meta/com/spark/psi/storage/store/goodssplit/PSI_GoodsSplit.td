<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_GoodsSplit" ID="D6D1D0DA256DFF37A2030B45C4965417" name-db="PSI_GoodsSplit" category="业务主体" title="商品拆分主表" author="" description="" packagename="com.spark.psi.storage.store.goodssplit" name="PSI_GoodsSplit" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_GoodsSplit" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="商品仓库GUID" description="" ID="7D4B4231B702DE0E373F371662881544" />
    <field dbtable="PSI_GoodsSplit" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="B6E6876C3CF69B954D99CC2BC3523F89" />
    <field dbtable="PSI_GoodsSplit" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Id" description="" ID="9111A784DF57894A58C121D88EEBB86B" />
    <field dbtable="PSI_GoodsSplit" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人名称" description="" ID="78F36391702404A5E86557A2A5984C0E" />
    <field dbtable="PSI_GoodsSplit" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="1892E178BF565AF48DC29070F6C9CF8F" />
    <field dbtable="PSI_GoodsSplit" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="7B84D83EAE0AB06A220C7191F39BE918" />
    <field dbtable="PSI_GoodsSplit" name="approvalPersonId" name-db="approvalPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="审批人标识" description="" ID="AAE9227EA27B395C94CA75FD9A09672B" />
    <field dbtable="PSI_GoodsSplit" name="approvalPersonName" name-db="approvalPersonName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="审批人名称" description="" ID="E2EC02F708C07800A995E8AD32564531" />
    <field dbtable="PSI_GoodsSplit" name="approvalDate" name-db="approvalDate" type="date" primary-key="false" keep-valid="false" author="" title="审批日期" description="" ID="69CEAE1C30F39196C06CC4D6FE705546" />
    <field dbtable="PSI_GoodsSplit" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="驳回原因" description="" ID="547A4A386D245E1C2C84DB336E307044" />
    <field dbtable="PSI_GoodsSplit" name="distributPersonId" name-db="distributPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="分配人标识" description="" ID="FBCE828E102776B7ABBD68E60651DCC8" />
    <field dbtable="PSI_GoodsSplit" name="distributPersonName" name-db="distributPersonName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="分配人名称" description="" ID="C62CF61CA1B0A3DEB4EC5500CD5C5093" />
    <field dbtable="PSI_GoodsSplit" name="distributDate" name-db="distributDate" type="date" primary-key="false" keep-valid="false" author="" title="分配日期" description="" ID="9A7507214D82187E7F9373D418973002" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_GoodsSplit" name-db="PSI_GoodsSplit" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

