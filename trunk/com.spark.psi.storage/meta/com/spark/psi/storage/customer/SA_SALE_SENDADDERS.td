<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_SENDADDERS" ID="9E51533C0C1D210B0FF983966EBF877F" name-db="SA_SALE_SENDADDERS" category="基础数据主体" title="送货/收货地址表" author="" description="" packagename="com.spark.psi.storage.customer" name="SA_SALE_SENDADDERS" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_SENDADDERS" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="true" author="" title="租户编号" description="" ID="1A101841F342B83C8BA6EC37C8C0D79B" />
    <field dbtable="SA_SALE_SENDADDERS" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商编号" description="" ID="6F57F8CA9C896D07FAE41292751F84E5" />
    <field dbtable="SA_SALE_SENDADDERS" name="cusproType" name-db="cusproType" type="char(2)" primary-key="false" keep-valid="false" author="" title="客户供应商类型" description="" ID="1EC620B090BA9C5B838C7EF0251E626F" />
    <field dbtable="SA_SALE_SENDADDERS" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="1D5F7B5D7D7D70E40340F97F9FB94D70" />
    <field dbtable="SA_SALE_SENDADDERS" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="B171AA27E9B93F5AEA765C4957717EE5" />
    <field dbtable="SA_SALE_SENDADDERS" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="8F87E1A39678FD81834467559B9063A6" />
    <field dbtable="SA_SALE_SENDADDERS" name="addressDetail" name-db="addressDetail" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="0DD25CFB738E6EAEA4061172936A7D2C" />
    <field dbtable="SA_SALE_SENDADDERS" name="linkman" name-db="linkman" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="E0438054244B4B8C15439EE08ACD74ED" />
    <field dbtable="SA_SALE_SENDADDERS" name="mobile" name-db="mobile" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="手机" description="" ID="B4663640701120471B74AA6FC4034D64" />
    <field dbtable="SA_SALE_SENDADDERS" name="telephone" name-db="telephone" type="varchar(15)" primary-key="false" keep-valid="false" author="" title="固话" description="" ID="CAF5B8DB36DA1F1D345858AB2FD48339" />
    <field dbtable="SA_SALE_SENDADDERS" name="postCode" name-db="postCode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="725842F50EC997E54B2082668647A590" />
    <field dbtable="SA_SALE_SENDADDERS" name="lastDate" name-db="lastDate" type="date" primary-key="false" keep-valid="false" author="" title="最近使用日期" description="" ID="022489B3E5D5BC77402556A57D0D2371" />
    <field dbtable="SA_SALE_SENDADDERS" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="0041F2BC3FDE10004FA04ABA0FB87330" />
    <field dbtable="SA_SALE_SENDADDERS" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="B92B45BCBBC592AE4E5D3CA03FE7B8E3" />
  </fields>
  <indexs>
    <index author="" name="INDEX_SALE_SENDADDERS" name-db="INDEX_SALE_SENDADDERS" title="INDEX_SALE_SENDADDERS" unique="false" description="">
      <index-item fieldID="1A101841F342B83C8BA6EC37C8C0D79B" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="6F57F8CA9C896D07FAE41292751F84E5" field="cusproGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_SENDADDERS" name-db="SA_SALE_SENDADDERS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

