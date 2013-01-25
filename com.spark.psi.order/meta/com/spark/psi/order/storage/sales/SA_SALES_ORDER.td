<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALES_ORDER" ID="EE71F61C114EAD0A2728693C284CDF49" name-db="SA_SALES_ORDER" category="" title="存放销售订单信息" author="" description="" packagename="com.spark.psi.order.storage.sales" name="SA_SALES_ORDER" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALES_ORDER" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="true" author="" title="租户GUID" description="" ID="0948233EFECCF757CDEA9B92459F6D69" />
    <field dbtable="SA_SALES_ORDER" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="FB0763E601AB3AAE7B1D6A0F7FADDC99" />
    <field dbtable="SA_SALES_ORDER" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="14AC813BBB5E48483B936C1A11AA9B4C" />
    <field dbtable="SA_SALES_ORDER" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="3F7283E28D60AE25A0F7400527E9BC07" />
    <field dbtable="SA_SALES_ORDER" name="orderNumber" name-db="orderNumber" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="销售订单编号" description="" ID="AEA124C33D8FB690907FC6A2DFB72739" />
    <field dbtable="SA_SALES_ORDER" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户GUID" description="" ID="3D2FB8C20F2A2D451F5484C70315FEEF" />
    <field dbtable="SA_SALES_ORDER" name="partnerShortName" name-db="partnerShortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户名称" description="" ID="21A223556D046159719BCC44B92FF3CC" />
    <field dbtable="SA_SALES_ORDER" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户全称" description="" ID="D5C609FBB0A6239D2A3C2691BC16BDC9" />
    <field dbtable="SA_SALES_ORDER" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户拼音" description="" ID="AF6DA45EF44278C6A7DAE5EF9F9C81F4" />
    <field dbtable="SA_SALES_ORDER" name="partnerFax" name-db="partnerFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户传真" description="" ID="43306CC30A33C1F9F059050129FC0E80" />
    <field dbtable="SA_SALES_ORDER" name="contactId" name-db="contactId" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="60A4857BAD531BE403D9CA00794C1EF3" />
    <field dbtable="SA_SALES_ORDER" name="contactName" name-db="contactName" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="A57ECAB91D1C78E30983BAEC70528874" />
    <field dbtable="SA_SALES_ORDER" name="contactTel" name-db="contactTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="37F70F1EFE21A03E08CDD52D150C3E98" />
    <field dbtable="SA_SALES_ORDER" name="contactPhone" name-db="contactPhone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="044BF9CBA98EAF0D607C30D89A367EDF" />
    <field dbtable="SA_SALES_ORDER" name="returnCause" name-db="returnCause" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回/中止原因" description="" ID="569F4BCB3097F540BB050EB59604E09D" />
    <field dbtable="SA_SALES_ORDER" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="5E0A5A6687D8A53F17AC1CE76983D0C6" />
    <field dbtable="SA_SALES_ORDER" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="9C550E555E1B5C463FF74A5F994AE5E5" />
    <field dbtable="SA_SALES_ORDER" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="FA7102B7B9260EC5324C592428F1EE4D" />
    <field dbtable="SA_SALES_ORDER" name="state" name-db="state" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="C43C30AEBD96327D78172F068B9C5BDE" />
    <field dbtable="SA_SALES_ORDER" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门部门GUID" description="" ID="ECF1F1BD5490BDDA581F4EEDF98B69E3" />
    <field dbtable="SA_SALES_ORDER" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="是否中止状态" description="" ID="08EB31F70E15DA6A08FA54421CB9CD4E" />
    <field dbtable="SA_SALES_ORDER" name="examin" name-db="examin" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="D9A78BF38CC5DB4402E1F8AF7E7A2A25" />
    <field dbtable="SA_SALES_ORDER" name="effectiveDate" name-db="effectiveDate" type="date" primary-key="false" keep-valid="false" author="" title="订单生效时间" description="" ID="DA56D0FAF49FEE85D5AC8E06F1C72988" />
    <field dbtable="SA_SALES_ORDER" name="field_25" name-db="field_25" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="351EF06480CBB28166FC2D5A2297C5FA" />
    <field dbtable="SA_SALES_ORDER" name="consigneeId" name-db="consigneeId" type="guid" primary-key="false" keep-valid="false" author="" title="收货人Guid" description="" ID="9D82DC68011E808998910C1E4E039107" />
    <field dbtable="SA_SALES_ORDER" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="CC2BDF99987336ED2EE710AC96367157" />
    <field dbtable="SA_SALES_ORDER" name="consigneeTel" name-db="consigneeTel" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="收货人电话" description="" ID="9F3F88F43C83918D2923A0DEB362E5CC" />
    <field dbtable="SA_SALES_ORDER" name="consigneePhone" name-db="consigneePhone" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="收货人手机" description="" ID="57EEE9DC35C7A184FAE1E8380F98B55D" />
    <field dbtable="SA_SALES_ORDER" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="CF7E6A838C3B24E13EBDBD5C422038A1" />
    <field dbtable="SA_SALES_ORDER" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="BB0C3F5DA86108A2CBBEEEBFC7A03706" />
    <field dbtable="SA_SALES_ORDER" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="整单折扣" description="" ID="E30B5A8E416AA49EC6169AA798D44267" />
    <field dbtable="SA_SALES_ORDER" name="examDeptId" name-db="examDeptId" type="guid" primary-key="false" keep-valid="false" author="" title="审核部门" description="" ID="F35BDC7FC4FF16314A72ADED95A46B6A" />
    <field dbtable="SA_SALES_ORDER" name="isAllot" name-db="isAllot" type="boolean" primary-key="false" keep-valid="false" author="" title="是否分配" description="" ID="9C927492F8D5882C230F1ED5CE9A6687" />
    <field dbtable="SA_SALES_ORDER" name="purchaseOrderId" name-db="purchaseOrderId" type="guid" primary-key="false" keep-valid="false" author="" title="在线下单采购订单GUID" description="" ID="65155614F11B7A65EF026F5EE46C33B3" />
    <field dbtable="SA_SALES_ORDER" name="tenantsIdB" name-db="tenantsIdB" type="guid" primary-key="false" keep-valid="false" author="" title="远程租户GUID" description="" ID="9B473A258429A571B2E2C566C2CF1181" />
    <field dbtable="SA_SALES_ORDER" name="planOutDate" name-db="planOutDate" type="date" primary-key="false" keep-valid="false" author="" title="预计出库日期" description="" ID="BC119AABDB52E9D7EF13CA81F2D0D565" />
    <field dbtable="SA_SALES_ORDER" name="field_38" name-db="field_38" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="2F6F43B428476BFE95760040E6A9E92B" />
    <field dbtable="SA_SALES_ORDER" name="defOne" name-db="defOne" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段1" description="" ID="837CD87D0063C73A9EF7D887FCB90979" />
    <field dbtable="SA_SALES_ORDER" name="defTwo" name-db="defTwo" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段2" description="" ID="A9A877D1B0452A5F36340DEBCCF3EF81" />
    <field dbtable="SA_SALES_ORDER" name="defThree" name-db="defThree" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段3" description="" ID="BF922998179B2075DA535D6D68D78DE7" />
    <field dbtable="SA_SALES_ORDER" name="defFour" name-db="defFour" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段4" description="" ID="F57E3D3B5178D0DC5B4658CE602D3CA1" />
    <field dbtable="SA_SALES_ORDER" name="defFive" name-db="defFive" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段5" description="" ID="525175B927A7AA8EFCF8E6E2AAEF2A63" />
    <field dbtable="SA_SALES_ORDER" name="defSix" name-db="defSix" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段6" description="" ID="0E59185BAD7CB84AB068DFD1D0091B46" />
    <field dbtable="SA_SALES_ORDER" name="defSeven" name-db="defSeven" type="date" primary-key="false" keep-valid="false" author="" title="用户自定义字段7" description="" ID="040176B327DBFCD873749D9EB3FEEBF5" />
    <field dbtable="SA_SALES_ORDER" name="defEight" name-db="defEight" type="date" primary-key="false" keep-valid="false" author="" title="用户自定义字段8" description="" ID="F637762CAA8DE8B85E2CAE5C370011BC" />
    <field dbtable="SA_SALES_ORDER" name="defNine" name-db="defNine" type="char(2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段9" description="" ID="10666F8D95DA7F30C8BDB1D7805D050E" />
    <field dbtable="SA_SALES_ORDER" name="defTen" name-db="defTen" type="char(2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段10" description="" ID="ABA21751D8053BF333EB0039DD9704AB" />
  </fields>
  <indexs>
    <index author="" name="SA_SALES_ORDER_1" name-db="SA_SALES_ORDER_1" title="SA_SALES_ORDER_1" unique="false" description="">
      <index-item fieldID="0948233EFECCF757CDEA9B92459F6D69" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="C43C30AEBD96327D78172F068B9C5BDE" field="state" field-author="" desc="false" />
      <index-item fieldID="ECF1F1BD5490BDDA581F4EEDF98B69E3" field="deptId" field-author="" desc="false" />
    </index>
    <index author="" name="SA_SALES_ORDER_2" name-db="SA_SALES_ORDER_2" title="SA_SALES_ORDER_2" unique="false" description="">
      <index-item fieldID="0948233EFECCF757CDEA9B92459F6D69" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="FB0763E601AB3AAE7B1D6A0F7FADDC99" field="creatorId" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALES_ORDER" name-db="SA_SALES_ORDER" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

