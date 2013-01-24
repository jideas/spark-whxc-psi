<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_CARD_DISTRIBUTE_LOG" ID="DBC06B9E6427397549FC69AAAA9017F5" name-db="CMS_CARD_DISTRIBUTE_LOG" category="业务主体" title="面值卡派发记录" author="" description="" packagename="com.spark.cms.storage.card" name="CMS_CARD_DISTRIBUTE_LOG" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="cardId" name-db="cardId" type="guid" primary-key="false" keep-valid="false" author="" title="卡id" description="" ID="07166F4218640005FA07C49A958D5FD2" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="distributePersonId" name-db="distributePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="派发人" description="" ID="10CA6A3E4B5FCCEB3B078505FCCD1DCC" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="distributePerson" name-db="distributePerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="派发人姓名" description="" ID="6978578FFF736FADA9CE3F29FAF4C963" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="responsiblePersonId" name-db="responsiblePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="新责任人" description="" ID="8EA7CE9A304E92D95630344D688144D8" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="responsiblePerson" name-db="responsiblePerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="新责任人姓名" description="" ID="17828D269FC310623CE9B713AA8CCC58" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="distributeDate" name-db="distributeDate" type="date" primary-key="false" keep-valid="false" author="" title="变更日期" description="" ID="E87D1741EABF792C5FC2811DCE8D192F" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="lastPersonId" name-db="lastPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="前责任人" description="" ID="B30B029A473AF19B4C7DDBA5896CF59A" />
    <field dbtable="CMS_CARD_DISTRIBUTE_LOG" name="lastPerson" name-db="lastPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="前责任人姓名" description="" ID="BE4C6DB75F4B2354BEC1D2C9CC6CF22E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_CARD_DISTRIBUTE_LOG" name-db="CMS_CARD_DISTRIBUTE_LOG" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

