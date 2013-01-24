package com.spark.psi.inventory.browser.checkout;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageProcessor;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;

/**
 * ������Ʒ���ⵥ��ϸ
 * 
 * @author Administrator
 * 
 */
public class KitCheckOutSheetDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckOutBaseInfoItem> {// SimpleSheetPageProcessor

	public final static String ID_Label_Store = "Label_Store";
	public final static String ID_Label_Source = "Label_Source";

	public final static String ID_Text_DeliveryPerson = "Text_DeliveryPerson";
	public final static String ID_Text_DeliveryUnit = "Text_DeliveryUnit";
	public final static String ID_Text_VoucherNumber = "Text_VoucherNumber";
	String sheetId;
	CheckOutBaseInfo info;

	static enum Columns {
		kitName, kitDescription, unit, count
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);

		this.createControl(ID_Label_Store, Label.class).setText("����ֿ⣺" + nvl(info.getStoreName()));
		this.createControl(ID_Label_Source, Label.class).setText("  ��Ʒ��Դ��" + nvl(info.getGoodsFrom()));
		createMemoText().setText(info.getRemark());
		createMemoText().setEnabled(false);

		// ������ش�����Ϣ
		this.lblProcessingStatusValue.setText(CheckingOutStatus.Finish.getName());

		Label deliveryPersonText = this.createLabelControl(ID_Text_DeliveryPerson);
		Label deliveryUnitText = createLabelControl(ID_Text_DeliveryUnit);
		Label voucherNumberText = createLabelControl(ID_Text_VoucherNumber);

		deliveryPersonText.setText(info.getTakePerson());
		deliveryUnitText.setText(info.getTakeUnit());
		voucherNumberText.setText(info.getVouchersNo());
	}

	private String nvl(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	@Override
	protected void initSheetData() {
		if (null != this.getArgument()) {
			sheetId = (String) this.getArgument();
			info = getContext().get(CheckOutBaseInfo.class, GUID.valueOf(sheetId));
		}
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof String) {
			return (String) element;
		} else {
			CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) element;
			return item.getGoodsName().concat(item.getGoodsSpec()).concat(item.getUnit());
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (CheckIsNull.isNotEmpty(info.getItems())) {
			return info.getItems().toArray();
		}
		return null;
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		return null;
	}

	@Override
	protected String getRemark() {
		if (info != null)
			return info.getRemark();
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		// if(info!=null)
		// return info.get
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		if (info != null)
			return "";// info.getSheetNumber();
		return null;
	}

	@Override
	protected String getSheetTitle() {
		return "�������ⵥ";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getSheetNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("���ⵥ��", info.getSheetNo());
						bw.addLabel("����ֿ�", info.getStoreName());
						bw.addLabel("��Ʒ��Դ", info.getGoodsFrom());
						bw.addLabel("��Ʒ��;", info.getGoodsUse());
						bw.addLabel("ȷ�ϳ���", info.getCheckoutPersonName() + "("
								+ DateUtil.dateFromat(info.getCheckoutDate()) + ")");
						if (CheckIsNull.isNotEmpty(info.getTakePerson())) {
							bw.addLabel("�����", info.getTakePerson());
						}
						if (CheckIsNull.isNotEmpty(info.getTakeUnit())) {
							bw.addLabel("�����λ", info.getTakeUnit());
						}
						if (CheckIsNull.isNotEmpty(info.getVouchersNo())) {
							bw.addLabel("ƾ֤��", info.getVouchersNo());
						}
						bw.addLabel("��ע", info.getRemark());
						String[] head = new String[] { "��Ʒ����", "��Ʒ����", "��λ", "��������" };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) obj;
							list.add(new String[] { item.getGoodsName(), item.getGoodsSpec(), item.getUnit(),
									DoubleUtil.getRoundStr(item.getRealCount()) });
						}
						bw.setTable(head, list);
						try {
							bw.write(info.getSheetNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	@Override
	protected boolean isNeedExport() {
		return true;
	}
}