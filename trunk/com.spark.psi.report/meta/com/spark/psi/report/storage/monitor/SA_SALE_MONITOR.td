<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALE_MONITOR" ID="086A326F81EA165952D2A36B22589DBD" name-db="SA_SALE_MONITOR" category="" title="销售监控信息表" author="" description="" packagename="com.spark.psi.report.storage.monitor" name="SA_SALE_MONITOR" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALE_MONITOR" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="true" author="" title="租户编号" description="" ID="30C008B2F18016451CDCA4A325AD4143" />
    <field dbtable="SA_SALE_MONITOR" name="monitorYear" name-db="monitorYear" type="nvarchar(4)" primary-key="false" keep-valid="false" author="" title="监控年度" description="" ID="6BC0D8A0F6D861D4AF4EEA224A2C2534" />
    <field dbtable="SA_SALE_MONITOR" name="monitorType" name-db="monitorType" type="char(2)" primary-key="false" keep-valid="false" author="" title="监控类别" description="" ID="6F936D5136EF9982EB045FC7DBD0D5BA" />
    <field dbtable="SA_SALE_MONITOR" name="monitorItem" name-db="monitorItem" type="char(2)" primary-key="false" keep-valid="false" author="" title="监控项" description="" ID="EF59C12D5749CD1BDB506DB144FC06FD" />
    <field dbtable="SA_SALE_MONITOR" name="monitorObjGuid" name-db="monitorObjGuid" type="guid" primary-key="false" keep-valid="false" author="" title="监控对象GUID" description="" ID="06B5256955EB30EEB950F37CD7B1327B" />
    <field dbtable="SA_SALE_MONITOR" name="monitorObjType" name-db="monitorObjType" type="char(2)" primary-key="false" keep-valid="false" author="" title="监控对象类型" description="" ID="B03109AA3B863BF1B7CCD0453C6D37DD" />
    <field dbtable="SA_SALE_MONITOR" name="janAmount" name-db="janAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="一月" description="" ID="89A584D2A8575F0591D68D73713BF88A" />
    <field dbtable="SA_SALE_MONITOR" name="febAmount" name-db="febAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="二月" description="" ID="B6E579F810565E046E050808C47F2AE4" />
    <field dbtable="SA_SALE_MONITOR" name="marAmount" name-db="marAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="三月" description="" ID="FDEDFF49BFCAF7F961A8D403CC451B05" />
    <field dbtable="SA_SALE_MONITOR" name="aprilAmount" name-db="aprilAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="四月" description="" ID="2BB611A94D751C2012856D37EFF68E4D" />
    <field dbtable="SA_SALE_MONITOR" name="mayAmount" name-db="mayAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="五月" description="" ID="30717F75145FFDA90C37683AABD209D4" />
    <field dbtable="SA_SALE_MONITOR" name="junAmount" name-db="junAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="六月" description="" ID="9B7458B744271F1732A21ED3EB43ADFA" />
    <field dbtable="SA_SALE_MONITOR" name="julyAmount" name-db="julyAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="七月" description="" ID="CC7A324604BD129F7F93EC46A56D299B" />
    <field dbtable="SA_SALE_MONITOR" name="augAmount" name-db="augAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="八月" description="" ID="A7DEF01D62719C5A9CAAB0E724F3A0AE" />
    <field dbtable="SA_SALE_MONITOR" name="sepAmount" name-db="sepAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="九月" description="" ID="810518E23CFC2E6EC60EFEF507ACC854" />
    <field dbtable="SA_SALE_MONITOR" name="octAmount" name-db="octAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="十月" description="" ID="02C04ACA10423D10A7DEB9E35547D24C" />
    <field dbtable="SA_SALE_MONITOR" name="novAmount" name-db="novAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="十一月" description="" ID="1708358F9CB9D8438831F17D6227C614" />
    <field dbtable="SA_SALE_MONITOR" name="decAmount" name-db="decAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="十二月" description="" ID="E772ECF7521C820CD4DF2501C818D292" />
    <field dbtable="SA_SALE_MONITOR" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="BE388C09CF7174AB3297A6BB064915E9" />
    <field dbtable="SA_SALE_MONITOR" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="495B065313C264F3273356776A0A7E2E" />
    <field dbtable="SA_SALE_MONITOR" name="field_1" name-db="field_1" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="3917FD7DB0367F0DDAA20EEF8F8C0DB0" />
    <field dbtable="SA_SALE_MONITOR" name="field_2" name-db="field_2" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="DA7A04FEEE9B2035639C808E2C4C998C" />
  </fields>
  <indexs>
    <index author="" name="INDEX_SALE_MONITOR" name-db="INDEX_SALE_MONITOR" title="INDEX_SALE_MONITOR" unique="false" description="">
      <index-item fieldID="30C008B2F18016451CDCA4A325AD4143" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="6F936D5136EF9982EB045FC7DBD0D5BA" field="monitorType" field-author="" desc="false" />
      <index-item fieldID="6BC0D8A0F6D861D4AF4EEA224A2C2534" field="monitorYear" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALE_MONITOR" name-db="SA_SALE_MONITOR" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

