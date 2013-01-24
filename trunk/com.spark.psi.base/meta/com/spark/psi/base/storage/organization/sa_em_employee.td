<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="sa_em_employee" ID="048F1E478E9175997CFDA0FC20766B20" name-db="sa_em_employee" category="" title="员工基础信息表" author="" description="" packagename="com.spark.psi.base.storage.organization" name="sa_em_employee" isneedrebuild="false">
  <fields>
    <field dbtable="sa_em_employee" name="empname" name-db="empname" type="nvarchar(20)" primary-key="false" keep-valid="true" author="" title="员工姓名" description="" ID="AE9216235029FFCC1C1633D6D9EBD661" />
    <field dbtable="sa_em_employee" name="idnum" name-db="idnum" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="证件号码" description="" ID="33A3485AA90230824E49CF6226F88F80" />
    <field dbtable="sa_em_employee" name="birthday" name-db="birthday" type="date" primary-key="false" keep-valid="false" author="" title="出生日期" description="" ID="79DD6CB475D2E40AECB36641561A66FA" />
    <field dbtable="sa_em_employee" name="departmentid" name-db="departmentid" type="guid" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="796CA8839D4DDF089DE5E89B2EDD3C21" />
    <field dbtable="sa_em_employee" name="rolestatue" name-db="rolestatue" type="nvarchar(2)" primary-key="false" keep-valid="false" author="" title="员工状态" description="" ID="81B49C4EA68183007275BB8FF38BC6E5" />
    <field dbtable="sa_em_employee" name="terminaldate" name-db="terminaldate" type="date" primary-key="false" keep-valid="false" author="" title="离职日期" description="" ID="00BECB0B30BAB26F904939EB8D03133F" />
    <field dbtable="sa_em_employee" name="mobilephone" name-db="mobilephone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="手机" description="" ID="BC3D2E459D9A3E33D5A161F1D968C2B8" />
    <field dbtable="sa_em_employee" name="email" name-db="email" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="电子邮件" description="" ID="3522168C6529AE3A5B607CC5F3C36EC4" />
    <field dbtable="sa_em_employee" name="phone" name-db="phone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="工作电话" description="" ID="B513681C640CB14539340C2A2C6CEC22" />
    <field dbtable="sa_em_employee" name="createperson" name-db="createperson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="2587F94854D02B5AF84C2A0EE53B934E" />
    <field dbtable="sa_em_employee" name="createdate" name-db="createdate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期1" description="" ID="972FCFCC925654CD28AA684DC6E26760" />
    <field dbtable="sa_em_employee" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="9FEDE10412E7778A8810DFEF539764DA" />
    <field dbtable="sa_em_employee" name="empimg" name-db="empimg" type="guid" primary-key="false" keep-valid="false" author="" title="头像" description="" ID="07A18B16DA05B99D40930CA0979E7109" />
    <field dbtable="sa_em_employee" name="issysuser" name-db="issysuser" type="char(2)" primary-key="false" keep-valid="false" author="" title="是否系统用户" description="" ID="209AE455717B8919189F4051C39D1411" />
    <field dbtable="sa_em_employee" name="duty" name-db="duty" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="岗位" description="" ID="694E15B7B0A5957DB86FF948011E52D1" />
    <field dbtable="sa_em_employee" name="pyempname" name-db="pyempname" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="姓名拼音" description="" ID="59EF25D409459065752B1BA5C60598D1" />
    <field dbtable="sa_em_employee" name="pyduty" name-db="pyduty" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="岗位拼音" description="" ID="6D6F4D2AF9ED033D2D5C729C3FA758AB" />
    <field dbtable="sa_em_employee" name="roles" name-db="roles" type="int" primary-key="false" keep-valid="false" author="" title="角色" description="" ID="3742C6E84801C64F833F1DAD59CA94BA" />
    <field dbtable="sa_em_employee" name="style" name-db="style" type="nvarchar(255)" primary-key="false" keep-valid="false" author="" title="界面风格" description="" ID="587F7CC98CF2216D65E827B428460C27" />
  </fields>
  <indexs>
    <index author="" name="INDEX_EMPLOYEE_TENANTS" name-db="INDEX_EMPLOYEE_TENANTS" title="INDEX_EMPLOYEE_TENANTS" unique="false" description="">
      <index-item fieldID="9FEDE10412E7778A8810DFEF539764DA" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="796CA8839D4DDF089DE5E89B2EDD3C21" field="departmentid" field-author="" desc="false" />
    </index>
    <index author="" name="INDEX_EMPLOYEE_MOBILE" name-db="INDEX_EMPLOYEE_MOBILE" title="INDEX_EMPLOYEE_MOBILE" unique="true" description="">
      <index-item fieldID="9FEDE10412E7778A8810DFEF539764DA" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="BC3D2E459D9A3E33D5A161F1D968C2B8" field="mobilephone" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="sa_em_employee" name-db="sa_em_employee" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

