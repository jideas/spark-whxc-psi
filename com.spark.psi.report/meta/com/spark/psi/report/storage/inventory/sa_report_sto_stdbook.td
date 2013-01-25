<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="sa_report_sto_stdbook" ID="000228631B4187F031E7D9BEBAAF106F" name-db="sa_report_sto_stdbook" category="" title="库存台账" author="" description="" packagename="com.spark.psi.report.storage.inventory" name="sa_report_sto_stdbook" isneedrebuild="false">
  <fields>
    <field dbtable="sa_report_sto_stdbook" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="31D013DE7289C3C4B9ECCEE43FE3C054" />
    <field dbtable="sa_report_sto_stdbook" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="77FBE97B90D53F437C6E487D38C03BE0" />
    <field dbtable="sa_report_sto_stdbook" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="2510BD3226587FC6D4309A9B1603F6D9" />
    <field dbtable="sa_report_sto_stdbook" name="inventoryType" name-db="inventoryType" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="DF50DC1F508EF16933FA05DC564E43F5" />
    <field dbtable="sa_report_sto_stdbook" name="beginStoreCount" name-db="beginStoreCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="期初库存数量" description="" ID="F0F3748717F8C543C8EC04D59C9FF3EC" />
    <field dbtable="sa_report_sto_stdbook" name="beginStoreMoney" name-db="beginStoreMoney" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="期初库存金额" description="" ID="EDB8807983672BD23E0A8373A74AF861" />
    <field dbtable="sa_report_sto_stdbook" name="instoCount" name-db="instoCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="入库数量" description="" ID="0E2A983F94E25D23E905867646279FEF" />
    <field dbtable="sa_report_sto_stdbook" name="instoAmount" name-db="instoAmount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="026A923DE79527DAD7210182672F378D" />
    <field dbtable="sa_report_sto_stdbook" name="outstoCount" name-db="outstoCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="出库数量" description="" ID="7DFA54779E8C725646FB71B5F1D6187B" />
    <field dbtable="sa_report_sto_stdbook" name="outstoAmount" name-db="outstoAmount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="FE25F636919DFE1D35939D3D72F695C3" />
    <field dbtable="sa_report_sto_stdbook" name="endStoreCount" name-db="endStoreCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="期末库存数量" description="" ID="A2D19B75776FE8695F9F9E62C63EA901" />
    <field dbtable="sa_report_sto_stdbook" name="endStoreMoney" name-db="endStoreMoney" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="期末库存金额" description="" ID="BCC30DAA7E3A29D8DADC6D53F9C8F333" />
    <field dbtable="sa_report_sto_stdbook" name="createdDate" name-db="createdDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="470C32A6913D3E380FE6A9AE65CDD988" />
    <field dbtable="sa_report_sto_stdbook" name="goodsName" name-db="goodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="2BC21EF13D5E4FAF5C6F045BE89CDA49" />
    <field dbtable="sa_report_sto_stdbook" name="goodsAttr" name-db="goodsAttr" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="E5A8F7815F4DAC21E267A3755FCC1B79" />
    <field dbtable="sa_report_sto_stdbook" name="goodsUnit" name-db="goodsUnit" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="B9E1CCA454087DCCC24C527B9D5B1A32" />
    <field dbtable="sa_report_sto_stdbook" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="53AAE1F46A96D013D6FE5D4A3094A169" />
    <field dbtable="sa_report_sto_stdbook" name="goodsNo" name-db="goodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="6238530D533FDD1766AF9092C93CE450" />
    <field dbtable="sa_report_sto_stdbook" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="49C604EDA1F10A9BB8D7B4E4E58FE13D" />
    <field dbtable="sa_report_sto_stdbook" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="天号" description="" ID="50AB4CB3928DB2637D627B1921E1CF6C" />
    <field dbtable="sa_report_sto_stdbook" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="月号" description="" ID="723E4F92BDA206C56C4C5BC225E397E1" />
    <field dbtable="sa_report_sto_stdbook" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季号" description="" ID="4097DBEDA081A912EE4B87AA0761B27A" />
    <field dbtable="sa_report_sto_stdbook" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年号" description="" ID="53BA241C187926B6B8C34F1DC2ACA71D" />
  </fields>
  <indexs>
    <index author="" name="index_orderNo" name-db="index_orderNo" title="index_orderNo" unique="false" description="">
      <index-item fieldID="6238530D533FDD1766AF9092C93CE450" field="goodsNo" field-author="" desc="false" />
      <index-item fieldID="2BC21EF13D5E4FAF5C6F045BE89CDA49" field="goodsName" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="sa_report_sto_stdbook" name-db="sa_report_sto_stdbook" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

