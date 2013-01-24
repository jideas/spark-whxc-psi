<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_NOTICE_DEPT" ID="3FBAE11D79C3F8D27E9C49F27078720E" name-db="SA_NOTICE_DEPT" category="" title="公告与部门中间表" author="" description="用于存储公告发布范围" packagename="com.spark.psi.base.storage.notice" name="SA_NOTICE_DEPT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_NOTICE_DEPT" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="8F1F5B10AFDD82AF34C1BA3AB3B0AB3B" />
    <field dbtable="SA_NOTICE_DEPT" name="noticeGuid" name-db="noticeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="公告GUID" description="" ID="4BAA402E9E1FE436BFF39329A6456026" />
    <field dbtable="SA_NOTICE_DEPT" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="98EF2DAFA03D99E3D2C888E982CDA9AD" />
    <field dbtable="SA_NOTICE_DEPT" name="deptName" name-db="deptName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="部门名称" description="" ID="444D29C3E588307E790F5D5C2AC8EAA8" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_NOTICE_DEPT" name-db="SA_NOTICE_DEPT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

