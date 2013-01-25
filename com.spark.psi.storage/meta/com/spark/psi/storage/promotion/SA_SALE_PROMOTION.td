<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_PROMOTION" ID="7649A0D304E9456C0518C6184995F74A" name-db="SA_SALE_PROMOTION" category="基础数据主体" title="促销商品" author="" description="促销商品" packagename="com.spark.psi.storage.promotion" name="SA_SALE_PROMOTION" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_PROMOTION" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="585C933715B2656087831AD65921FF99" />
    <field dbtable="SA_SALE_PROMOTION" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="9406C3DE2BC5BC9C467E5FEF8F7CEC15" />
    <field dbtable="SA_SALE_PROMOTION" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="2E001A6EFDB1047977FCF068166AC24F" />
    <field dbtable="SA_SALE_PROMOTION" name="goodsNamePY" name-db="goodsNamePY" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="F287C4F84C22893D5CDCB35575FD1C17" />
    <field dbtable="SA_SALE_PROMOTION" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="18A385AD29D28CC0B92C0AC26B29DCA3" />
    <field dbtable="SA_SALE_PROMOTION" name="createPersonGuid" name-db="createPersonGuid" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="9C0521A98F2DAE2F8C3F68F6E3FA57A5" />
    <field dbtable="SA_SALE_PROMOTION" name="cpName" name-db="cpName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="213E464A09B53FF1B1C6D0B2B1C914A7" />
    <field dbtable="SA_SALE_PROMOTION" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="3D0E29AB84AD5ECE5CED716170EAD664" />
    <field dbtable="SA_SALE_PROMOTION" name="promotionCount" name-db="promotionCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="促销数量" description="" ID="D363790947A2E116FCC82AE44B27B503" />
    <field dbtable="SA_SALE_PROMOTION" name="saleCount" name-db="saleCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="销售数量" description="" ID="6B6B0E00BC95BDFE0E621DB626BCF487" />
    <field dbtable="SA_SALE_PROMOTION" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="原价" description="" ID="22BBA435498838EF86E0A8AC9786056C" />
    <field dbtable="SA_SALE_PROMOTION" name="promotionPrice" name-db="promotionPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="促销单价" description="" ID="3A42AF5EB916C0DD306C67E9244DE202" />
    <field dbtable="SA_SALE_PROMOTION" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="开始日期" description="" ID="AA88066D0B7E3E80571E14CBE19BB9B9" />
    <field dbtable="SA_SALE_PROMOTION" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="结束日期" description="" ID="3E691396A538786A83F075F0544A9493" />
    <field dbtable="SA_SALE_PROMOTION" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="24B106EE3ED0490D3687EF7C825C88EE" />
    <field dbtable="SA_SALE_PROMOTION" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="B174DA0C3B44323E4F5E64DBB6020223" />
    <field dbtable="SA_SALE_PROMOTION" name="promotionCause" name-db="promotionCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="促销原因" description="" ID="A3BEAC7F5C1060E20F8E296F0E1B9149" />
    <field dbtable="SA_SALE_PROMOTION" name="returnCause" name-db="returnCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="4A64079DA52DBF5DC0E23B8075458211" />
    <field dbtable="SA_SALE_PROMOTION" name="checkDate" name-db="checkDate" type="date" primary-key="false" keep-valid="false" author="" title="审核时间" description="" ID="EE4B5A65E1F0BD57D06A2C9564545540" />
  </fields>
  <indexs>
    <index author="" name="index_promotion_1" name-db="index_promotion_1" title="index_promotion_1" unique="false" description="">
      <index-item fieldID="585C933715B2656087831AD65921FF99" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="B174DA0C3B44323E4F5E64DBB6020223" field="status" field-author="" desc="false" />
      <index-item fieldID="3D0E29AB84AD5ECE5CED716170EAD664" field="deptGuid" field-author="" desc="false" />
    </index>
    <index author="" name="index_promotion_2" name-db="index_promotion_2" title="index_promotion_2" unique="false" description="">
      <index-item fieldID="585C933715B2656087831AD65921FF99" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="9406C3DE2BC5BC9C467E5FEF8F7CEC15" field="goodsGuid" field-author="" desc="false" />
      <index-item fieldID="B174DA0C3B44323E4F5E64DBB6020223" field="status" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_PROMOTION" name-db="SA_SALE_PROMOTION" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

