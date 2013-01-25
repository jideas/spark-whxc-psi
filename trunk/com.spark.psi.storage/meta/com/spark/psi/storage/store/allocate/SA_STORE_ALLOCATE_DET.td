<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_ALLOCATE_DET" ID="3AF417DB6ACA8B71730C959F06D8B543" name-db="SA_STORE_ALLOCATE_DET" category="" title="" author="" description="" packagename="com.spark.psi.storage.store.allocate" name="SA_STORE_ALLOCATE_DET" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_ALLOCATE_DET" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="6A1AC2E3A9FA8A7905462CA34D5084C9" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="allocateOrdGuid" name-db="allocateOrdGuid" type="guid" primary-key="false" keep-valid="false" author="" title="调拨单号" description="" ID="59220691DFD2C3736FD9B5B3A67B4D82" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="2DAE46B0034839CABEDE410D559D757D" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="goodsName" name-db="goodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="DC40B6E02C2A9D92D3EA93B4DDFE0CB0" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="goodsNo" name-db="goodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="93EBE7D9E7238DA133385C78EB07F4CD" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="E2F4CE9A5C4DCB891F4DEEB6648EFF4A" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="unit" name-db="unit" type="varchar(4)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="00EBBB07AB10A2A566638D86C320354D" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="ableCount" name-db="ableCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="可用库存" description="" ID="D4C2A1DFB0D2D5E237B824F1B8410754" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="allocateCount" name-db="allocateCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="实际调拨数量" description="" ID="C31B9638BD489A6005244842A07249BA" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="8A4022785BA9DE6242D941D0B6DD0A1D" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="B731E1648DD107A4A3E58B9151708CC7" />
    <field dbtable="SA_STORE_ALLOCATE_DET" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="精度" description="" ID="956454F617C3260DEF7B732606434881" />
  </fields>
  <indexs>
    <index author="" name="index_1" name-db="index_1" title="index_1" unique="false" description="">
      <index-item fieldID="6A1AC2E3A9FA8A7905462CA34D5084C9" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="59220691DFD2C3736FD9B5B3A67B4D82" field="allocateOrdGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_ALLOCATE_DET" name-db="SA_STORE_ALLOCATE_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

