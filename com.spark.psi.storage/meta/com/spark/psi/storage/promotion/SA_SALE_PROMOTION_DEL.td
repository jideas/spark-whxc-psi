<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_PROMOTION_DEL" ID="B35BFFE15A2C30D5A45602993015C187" name-db="SA_SALE_PROMOTION_DEL" category="基础数据主体" title="促销记录删除历史" author="" description="促销记录删除历史" packagename="com.spark.psi.storage.promotion" name="SA_SALE_PROMOTION_DEL" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_PROMOTION_DEL" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="883D22C37B062A226EA9BCCA2F5A9769" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="AF2E9DCA155266380B0E2107C9046149" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="53711046BA323E247C4195C52837E1A9" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="goodsNamePY" name-db="goodsNamePY" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="957F265A43366B78EFA58C61709E5BED" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="0012E635003B3A761B5E09DCA4A6C66A" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="createPersonGuid" name-db="createPersonGuid" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="E7715D9411230422A9EFFD04D8311388" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="cpName" name-db="cpName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="102591E48026DF21A78A3755A322884C" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="4A927552E2B3E09EA8DCF1BF2E7D3322" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="promotionCount" name-db="promotionCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="促销数量" description="" ID="3838CA66FDC01B45BD10062A48EED37F" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="saleCount" name-db="saleCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="销售数量" description="" ID="6B4CADBDC34F1D90A005BCA054F73CE3" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="原价" description="" ID="65019A743AE861CF4D1FE3816B91721C" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="promotionPrice" name-db="promotionPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="促销单价" description="" ID="788CB2C1151EA7C74E2DAB9C5A0476D5" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="开始日期" description="" ID="4FB6E633B8A7671A1C4B66645CD2924C" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="结束日期" description="" ID="7F33CEF79907DF8DBD699F198D3DF4E0" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="E288DF2775010D49DB6D509CE399686B" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="2B0B7A275AEFFDB8F9A19AF81188214F" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="promotionCause" name-db="promotionCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="促销原因" description="" ID="FF70DD3D45121ECC4C574F5A5070D47F" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="returnCause" name-db="returnCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="53A296540B1C910BC48E76C03B3EFDDE" />
    <field dbtable="SA_SALE_PROMOTION_DEL" name="checkDate" name-db="checkDate" type="date" primary-key="false" keep-valid="false" author="" title="审核时间" description="" ID="A49729DED1E89A5A730479CD8C40C2D3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_PROMOTION_DEL" name-db="SA_SALE_PROMOTION_DEL" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

