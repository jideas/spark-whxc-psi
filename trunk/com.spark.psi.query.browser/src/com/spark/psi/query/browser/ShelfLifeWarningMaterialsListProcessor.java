package com.spark.psi.query.browser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.ExcelWriteHelperPoi;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ShelfLifeWarningType;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;
import com.spark.psi.publish.inventory.key.GetShelfLifeWarningMaterialsKey;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInListEntity;
import com.spark.psi.query.intf.publish.key.GetMaterialsCheckInListKey;

public class ShelfLifeWarningMaterialsListProcessor<Item> extends
		AbstractShelfLifeWarningProcessor<Item> {

	public static enum ColumnName {
		storeName("仓库"), materialName("材料名称"), materialCode("材料编号"), materailNo(
				"材料条码"), spec("规格"), unit("单位"), count("数量"), shelfLifeWarningType(
				"状态"), produceDate("生产日期"), shelfLife("保质期"), warningDay("预警天数"), shelfNo(
				"货位"), tiersNo("层号");

		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	// private MaterialCheckInSearchCondition searchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		ShelfLifeWarningMaterialsItem item = (ShelfLifeWarningMaterialsItem) element;
		return item.getId().toString();
	}

	GetShelfLifeWarningMaterialsKey key;

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetShelfLifeWarningMaterialsKey(tablestatus.getBeginIndex(),
				tablestatus.getPageSize(), true);
		GUID storeId = null;
		if (null != storeList.getText()
				&& null != storeSource.getFirstStoreId()&&!storeSource.getFirstStoreId().equals(storeList.getText())) {
			storeId = GUID.valueOf(storeList.getText());
		}
		key.setStoreId(storeId);
		ShelfLifeWarningType shelfLifeWarningType = null;
		if (null != statusList.getText() && !"00".equals(statusList.getText())) {
			shelfLifeWarningType = ShelfLifeWarningType
					.getShelfLifeWarningType(statusList.getText());
		}
		key.setShelfLifeWarningType(shelfLifeWarningType);
		ListEntity<ShelfLifeWarningMaterialsItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE,
				listEntity.getTotalCount());
		return listEntity.getItemList().toArray(
				new ShelfLifeWarningMaterialsItem[0]);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(
				MaterialCheckinAdvanceSearchProcessor.class,
				MaterialCheckinAdvanceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				// searchCondition = (MaterialCheckInSearchCondition)
				// returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private ListEntity<ShelfLifeWarningMaterialsItem> getListEntity() {
		// if (null != searchCondition) {
		// key.setSheetNo(searchCondition.getSheetNo());
		// key.setGoodsCode(searchCondition.getMaterialNo());
		// key.setGoodsName(searchCondition.getMaterialName());
		// key.setPurchaseSheetNo(searchCondition.getPurchaseSheetNo());
		// key.setCheckinDateBegin(searchCondition.getCheckinDateBegin());
		// key.setCheckinDateEnd(searchCondition.getCheckinDateEnd());
		// key.setCheckingInType(searchCondition.getCheckingInType());
		// key.setPartnerName(searchCondition.getPartnerName());
		// key.setPartnerNo(searchCondition.getPartnerNo());
		// }
		return getContext().find(ListEntity.class, key);
	}

	@Override
	protected void doExport() {
		final List<Object> datalist = table.getAllRows();
		Display.getCurrent().exportFile(
				getExportFileTitle() + QueryDataUtil.getDateNo() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream)
							throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(
								datalist) {
							@Override
							protected String getText(Object element,
									int columnIndex) {
								ShelfLifeWarningMaterialsItem item = (ShelfLifeWarningMaterialsItem) element;
								switch (columnIndex) {
								case 0:
									return item.getStoreName();
								case 1:
									return item.getShelfNo() + "";
								case 2:
									return item.getTiersNo() + "";
								case 3:
									return item.getMaterialName();
								case 4:
									return item.getMaterialCode();
								case 5:
									return item.getMaterialNo();
								case 6:
									return item.getMaterialSpec();
								case 7:
									return item.getMaterialUnit();
								case 8:
									return DoubleUtil.getRoundStr(item
											.getCount());
								case 9:
									return item.getShelfLifeWarningType()
											.getName();
								case 10:
									return DateUtil.dateFromat(item
											.getProduceDate());
								case 11:
									return item.getShelfLife() + "";
								case 12:
									return item.getWarningDay() + "";

								}
								return null;
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[13];
								columns[0] = ShelfLifeWarningMaterialsListProcessor.ColumnName.storeName
										.getTitle();
								columns[1] = ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfNo
										.getTitle();
								columns[2] = ShelfLifeWarningMaterialsListProcessor.ColumnName.tiersNo
										.getTitle();
								columns[3] = ShelfLifeWarningMaterialsListProcessor.ColumnName.materialName
										.getTitle();
								columns[4] = ShelfLifeWarningMaterialsListProcessor.ColumnName.materialCode
										.getTitle();
								columns[5] = ShelfLifeWarningMaterialsListProcessor.ColumnName.materailNo
										.getTitle();
								columns[6] = ShelfLifeWarningMaterialsListProcessor.ColumnName.spec
										.getTitle();
								columns[7] = ShelfLifeWarningMaterialsListProcessor.ColumnName.unit
										.getTitle();
								columns[8] = ShelfLifeWarningMaterialsListProcessor.ColumnName.count
										.getTitle();
								columns[9] = ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLifeWarningType
										.getTitle();
								columns[10] = ShelfLifeWarningMaterialsListProcessor.ColumnName.produceDate
										.getTitle();
								columns[11] = ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLife
										.getTitle();
								columns[12] = ShelfLifeWarningMaterialsListProcessor.ColumnName.warningDay
										.getTitle();

								return columns;
							}
						};
						writer.writeNormal(outputStream, getExportFileTitle());
					}
				});

	}

	@Override
	protected void doReset() {
		// searchCondition = null;
		table.render();
	}

	@Override
	protected String getExportFileTitle() {
		return "保质期预警材料";
	}

	@Override
	protected void doRefresh() {
		table.render();
	}

}
