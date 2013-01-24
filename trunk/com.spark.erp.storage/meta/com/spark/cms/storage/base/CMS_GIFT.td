<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_GIFT" ID="846220EEC93B1C7104B45582E8F8A817" name-db="CMS_GIFT" category="业务主体" title="赠品登记" author="" description="" packagename="com.spark.cms.storage.base" name="CMS_GIFT" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_GIFT" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="2681D72DD5A027626F532B8D7840E916" />
    <field dbtable="CMS_GIFT" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="3A89C1AB3D6EB62AFBDFF18B598149C7" />
    <field dbtable="CMS_GIFT" name="goodsCount" name-db="goodsCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="商品数量" description="" ID="C15C37F08BD07832745E6D8C42CD7C96" />
    <field dbtable="CMS_GIFT" name="reason" name-db="reason" type="nvarchar(300)" primary-key="false" keep-valid="false" author="" title="赠送原因" description="" ID="00D2EAD4616202AE253CB5FDF9609B8B" />
    <field dbtable="CMS_GIFT" name="lastDate" name-db="lastDate" type="date" primary-key="false" keep-valid="false" author="" title="截止日期" description="" ID="FF9880A4FA3A69D15EEFFC7FA848B092" />
    <field dbtable="CMS_GIFT" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="4507B5596FE44A0CB582F5BC7CCA7569" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_GIFT" name-db="CMS_GIFT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

