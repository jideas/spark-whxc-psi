<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Station" ID="71554C67B707A1CCA703685E84FDCF43" name-db="PSI_Base_Station" category="基础数据主体" title="站点表" author="" description="" packagename="com.spark.psi.storage.base.station" name="PSI_Base_Station" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Station" name="stationNo" name-db="stationNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="站点编号" description="" ID="66CC2446A6C11321244F7FE8E46C7319" />
    <field dbtable="PSI_Base_Station" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="3D7353090987F57A96B979BB33F1A0AC" />
    <field dbtable="PSI_Base_Station" name="namePY" name-db="namePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="1586C8E294CEC825EE858F00436961B4" />
    <field dbtable="PSI_Base_Station" name="shortName" name-db="shortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="站点简称" description="" ID="F5CF128EDDCFED884912D1E497F7E7AD" />
    <field dbtable="PSI_Base_Station" name="telephone" name-db="telephone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="电话" description="" ID="4B90C40A3F5DCA4B7A32B1069336CC20" />
    <field dbtable="PSI_Base_Station" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="传真" description="" ID="8624FE247C31167C90F2E1E19594BC08" />
    <field dbtable="PSI_Base_Station" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="63B91BC26A52A647A431B5AA9C308FCD" />
    <field dbtable="PSI_Base_Station" name="town" name-db="town" type="char(20)" primary-key="false" keep-valid="false" author="" title="地区" description="" ID="6A107230339F8DAA03F099615F41D9B1" />
    <field dbtable="PSI_Base_Station" name="province" name-db="province" type="char(20)" primary-key="false" keep-valid="false" author="" title="省" description="" ID="1EAECC7324520250123ED9FCD1C597E6" />
    <field dbtable="PSI_Base_Station" name="city" name-db="city" type="char(20)" primary-key="false" keep-valid="false" author="" title="市" description="" ID="C76DE8D38ADA931D2BAA49D33B993D51" />
    <field dbtable="PSI_Base_Station" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="F90D6A6459E080916DC8236220FB724E" />
    <field dbtable="PSI_Base_Station" name="managerId" name-db="managerId" type="guid" primary-key="false" keep-valid="false" author="" title="负责人id" description="" ID="091D90AAD68400F2CD368AF34D63783A" />
    <field dbtable="PSI_Base_Station" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="CB5655A26A35C0BA8FA5C238371A48A8" />
    <field dbtable="PSI_Base_Station" name="manager" name-db="manager" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="负责人" description="" ID="790F4D216E5BB50B1DDF40E64030B204" />
    <field dbtable="PSI_Base_Station" name="managerPersonId" name-db="managerPersonId" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="负责人身份证号" description="" ID="40901FDF38153757B751B56E6B83797D" />
    <field dbtable="PSI_Base_Station" name="managerPhone" name-db="managerPhone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="负责人电话" description="" ID="2E804D3EEB5F0AD4D69293C1558A9D97" />
    <field dbtable="PSI_Base_Station" name="managerEmail" name-db="managerEmail" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="负责人邮箱" description="" ID="83478B6736454CC1D8E8AD43030235A7" />
    <field dbtable="PSI_Base_Station" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="69476AB0B98B03CAE5914A77BA25FE9F" />
    <field dbtable="PSI_Base_Station" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="92FA0BEFABE395D99601DFCFE79768A4" />
    <field dbtable="PSI_Base_Station" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="88E668FFB394B53A6C7ED4C3D8265585" />
    <field dbtable="PSI_Base_Station" name="isStop" name-db="isStop" type="boolean" primary-key="false" keep-valid="true" author="" title="" description="" ID="6B78F4CFC45BCF7758C0376F1400510A">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Station" name-db="PSI_Base_Station" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

