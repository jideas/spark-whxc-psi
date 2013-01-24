<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_NOTICE" ID="EA3D32CED65A86E89ADC6744D3796127" name-db="SA_NOTICE" category="" title="公告基础信息表" author="" description="" packagename="com.spark.psi.base.storage.notice" name="SA_NOTICE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_NOTICE" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="1B670F9F929EE82AB8F5B80AAC46B0EB" />
    <field dbtable="SA_NOTICE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="690421FED947D296A811C44C98896A95" />
    <field dbtable="SA_NOTICE" name="deptNameStr" name-db="deptNameStr" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="发布范围" description="" ID="3A5E7BC267199F5FBCDE860DF7ECF1C7" />
    <field dbtable="SA_NOTICE" name="createGuid" name-db="createGuid" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="9221863D6A078CAF09915A57F47D947B" />
    <field dbtable="SA_NOTICE" name="createPerson" name-db="createPerson" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="71D0F723EEE2B07BE0FB20C366FEC8B1" />
    <field dbtable="SA_NOTICE" name="noticeTitle" name-db="noticeTitle" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公告标题" description="" ID="EF4B4A51570CFFCAC7D3899B92AE068C" />
    <field dbtable="SA_NOTICE" name="noticeTitlePY" name-db="noticeTitlePY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公告标题拼音" description="" ID="A1C3315E9D78D4FE928BF6E82AE277BB" />
    <field dbtable="SA_NOTICE" name="createTime" name-db="createTime" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="1F7F44669504685E3918D21F1C36C401" />
    <field dbtable="SA_NOTICE" name="publishTime" name-db="publishTime" type="date" primary-key="false" keep-valid="false" author="" title="发布时间" description="" ID="6FC1B23FF30EEB7F59A581DB998C9B50" />
    <field dbtable="SA_NOTICE" name="cancelTime" name-db="cancelTime" type="date" primary-key="false" keep-valid="false" author="" title="撤销时间" description="" ID="3D86B1E2018EDF8C0E95D5BF68D1EE41" />
    <field dbtable="SA_NOTICE" name="isTop" name-db="isTop" type="boolean" primary-key="false" keep-valid="false" author="" title="是否置顶" description="" ID="0A850B1D38C3A643DE9A3676477078B1" />
    <field dbtable="SA_NOTICE" name="noticeState" name-db="noticeState" type="char(2)" primary-key="false" keep-valid="false" author="" title="公告状态" description="" ID="FB5717C6532F2125C11673D65712479A" />
    <field dbtable="SA_NOTICE" name="noticeContent" name-db="noticeContent" type="text" primary-key="false" keep-valid="false" author="" title="公告内容" description="" ID="116FC946E01122E7A052F7BF49CECB8A" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_NOTICE" name-db="SA_NOTICE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

