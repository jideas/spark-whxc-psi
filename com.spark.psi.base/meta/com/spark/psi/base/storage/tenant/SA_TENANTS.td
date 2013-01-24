<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_TENANTS" ID="B954B66D6ACA695BA454C1674C6BC029" name-db="SA_TENANTS" category="" title="租户基础信息表" author="" description="" packagename="com.spark.psi.base.storage.tenant" name="SA_TENANTS" isneedrebuild="false">
  <fields>
    <field dbtable="SA_TENANTS" name="code" name-db="code" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="8715912D74B00BEC18568604CC641FD8" />
    <field dbtable="SA_TENANTS" name="name" name-db="name" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="名称" description="" ID="FB9D2CD1CA007EEFA4C8749CA7C6C2E7" />
    <field dbtable="SA_TENANTS" name="state" name-db="state" type="nvarchar(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="52D98EAA1A28D965F39071CD0B53FD7A" />
    <field dbtable="SA_TENANTS" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="174661FC54FD0A2507B0AF2C864A5BF7" />
    <field dbtable="SA_TENANTS" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="752B6231C4145972A0A4B08EA9143CE5" />
    <field dbtable="SA_TENANTS" name="updatePerson" name-db="updatePerson" type="guid" primary-key="false" keep-valid="false" author="" title="最后修改人" description="" ID="FA428DE785E3C0290E4139015B696634" />
    <field dbtable="SA_TENANTS" name="updateDate" name-db="updateDate" type="date" primary-key="false" keep-valid="false" author="" title="最后修改日期" description="" ID="F1CF92655E17EC025F369229EC3A629D" />
    <field dbtable="SA_TENANTS" name="examPerson" name-db="examPerson" type="guid" primary-key="false" keep-valid="false" author="" title="审核人" description="" ID="45D893C4D1E4BC60348D1C9BA450F1A6" />
    <field dbtable="SA_TENANTS" name="examDate" name-db="examDate" type="date" primary-key="false" keep-valid="false" author="" title="审核日期" description="" ID="C2B41A26715ED3D8D2B0A4E98B47B7D1" />
    <field dbtable="SA_TENANTS" name="examState" name-db="examState" type="varchar(1)" primary-key="false" keep-valid="false" author="" title="审核状态1" description="" ID="E1D01B147D3405789B9031DD9314160A" />
    <field dbtable="SA_TENANTS" name="examStateFunrecid" name-db="examStateFunrecid" type="guid" primary-key="false" keep-valid="false" author="" title="审核状态标识" description="" ID="781A83598B02FC25ECE209D2FD3F95D1" />
    <field dbtable="SA_TENANTS" name="namePy" name-db="namePy" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="租户名称拼音简写" description="" ID="D7C24F742B5BC5C1D867E950A06271B4" />
    <field dbtable="SA_TENANTS" name="backreason" name-db="backreason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="驳回原因" description="" ID="FDB7448EE79F51863BFA0C91B4BF3919" />
    <field dbtable="SA_TENANTS" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="E919590A1E954C8E74B89BDF13C331EE" />
    <field dbtable="SA_TENANTS" name="tenantsType" name-db="tenantsType" type="nvarchar(2)" primary-key="false" keep-valid="true" author="" title="租户类型" description="" ID="8E40934791472384341CBDFEC4EED381" />
    <field dbtable="SA_TENANTS" name="configState" name-db="configState" type="guid" primary-key="false" keep-valid="false" author="" title="配置流程状态" description="" ID="620BCC80ACA2E6150F2D134C997962D4" />
  </fields>
  <indexs>
    <index author="" name="INDEX_TENANTS_NAME" name-db="INDEX_TENANTS_NAME" title="INDEX_TENANTS_NAME" unique="true" description="">
      <index-item fieldID="FB9D2CD1CA007EEFA4C8749CA7C6C2E7" field="name" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_TENANTS" name-db="SA_TENANTS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

