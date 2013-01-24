/**
 * 
 */
package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.table.edit.SNameValue;

/**
 * 
 *
 */
public abstract class SimpleSheetPageProcessor<TItem> extends
		PSIListPageProcessor<TItem> {
	public final static String ID_SheetTitleComposite = "SheetTitleComposite";
	public final static String ID_SheetNumberComposite = "SheetNumberComposite";
	public final static String ID_Button_Export_Form = "Button_Export_Form";
	public final static String ID_Button_Print = "Button_Print";
	public final static String ID_Label_Label_ExtraInfo = "Label_ExtraInfo";
	public final static String ID_Text_Memo = "Text_Memo";
	public final static String ID_Area_TableButton = "Area_TableButton";
	public final static String ID_Label_StopCause = "Label_StopCause";

	private SheetTitleComposite sheetTitleComposite;
	private SheetNumberComposite sheetNumberComposite;

	
	@Override
	public void init(Situation context) {
		super.init(context);
		initSheetData();
	}

	
	@Override
	protected String getExportFileTitle() {
		return null;
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);

		

		sheetTitleComposite = this.createControl(ID_SheetTitleComposite,
				SheetTitleComposite.class);
		sheetNumberComposite = this.createControl(ID_SheetNumberComposite,
				SheetNumberComposite.class);
		setSheetTitle();
		setSheetNumber();

		StringBuffer extraInfos = new StringBuffer();
		extraInfos.append(isEmptyString(getSheetCreateInfo()) ? ""
				: getSheetCreateInfo() + "  ");
		extraInfos.append(isEmptyString(getSheetApprovalInfo()) ? ""
				: getSheetApprovalInfo() + "  ");
		String[] extraInfo = getSheetExtraInfo();
		if (extraInfo != null && extraInfo.length > 0) {
			for (String info : extraInfo) {
				extraInfos.append(isEmptyString(info) ? "" : info + "  ");
			}
		}
		//
		Label extraInfoLabel = createControl(ID_Label_Label_ExtraInfo,
				Label.class);
		extraInfoLabel.setText(extraInfos.toString());

		//
		Label causeLabel = createControl(ID_Label_StopCause,
				Label.class);
		String cause = this.getStopCause();
		if(StringUtils.isEmpty(cause)) {
			causeLabel.dispose();
		} else {
			causeLabel.setText(cause);
		}
		//
		
		Button printBtn = createControl(ID_Button_Print, Button.class);
		if (isNeedPrint()) {
			printBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
//					 // ��ӡ����
//					 Display.getCurrent().print(PageConfigure.A4Configure, new
//						 PrinterWithContext[]{new PrinterWithContext() {
//							
//						 public void run(Context context, PrintWriter printWriter)
//						 throws IOException {
//						 // TODO Auto-generated method stub
//											
//						 }
//					 })
//					 }
					printAction();
				}
			});
			
		} else {
			printBtn.dispose();
		}
		Button exportBtn = createButtonControl(ID_Button_Export_Form);
		if (isNeedExport()) {
			exportBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exportAction();
				}
			});
		} else {
			exportBtn.dispose();
		}

	}
	
	protected void exportAction() {
		
	}
	
	protected void printAction() {
		
	}
	
	protected boolean isNeedPrint() {
		return false;
	}
	
	protected boolean isNeedExport() {
		return false;
	}
	protected final void setSheetTitle() {
		String title = getSheetTitle();
		sheetTitleComposite.setTitleValue(title);
	}

	protected final void setSheetNumber() {
		String sheetNumber = getSheetNumber();
		if (sheetNumber == null || "".equals(sheetNumber))
			return;
		sheetNumberComposite.setSheetNumber(sheetNumber);
	}
	
	protected void removeTableButton() {
		Composite tableButtonArea = this.createControl(ID_Area_TableButton, Composite.class);
		Composite tableButtonParent = tableButtonArea.getParent();
		tableButtonArea.dispose();
		tableButtonParent.layout();
	}
	
	private boolean isEmptyString(String sourceString) {
		if (null == sourceString || "".equals(sourceString)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * ��ȡ��עText�ؼ�
	 * @return
	 */
	protected final Text createMemoText() {
		return createControl(ID_Text_Memo, Text.class, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP);
	}
	
	protected abstract void initSheetData();

	/**
	 * ��ȡ��ע����
	 * 
	 * @return
	 */
	protected abstract String getRemark();

	/**
	 * ��ȡ��ֹԭ��
	 * 
	 * @return
	 */
	protected abstract String getStopCause();

	/**
	 * ��ȡ�Ƶ���Ϣ
	 * 
	 * @return
	 */
	protected abstract String getSheetCreateInfo();

	/**
	 * ��ȡ�����Ϣ
	 * 
	 * @return
	 */
	protected abstract String getSheetApprovalInfo();

	/**
	 * ������Ϣ
	 * 
	 * @return
	 */
	protected abstract String[] getSheetExtraInfo();

	/**
	 * ��õ�������
	 * 
	 * @return
	 */
	protected abstract String getSheetTitle();

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	protected abstract String[] getSheetType();

	/**
	 * ��ȡ���ݱ��
	 * 
	 * @return
	 */
	protected abstract String getSheetNumber();

	/**
	 * ��õ��ݻ�����Ϣ
	 * 
	 * @return
	 */
	protected abstract String[] getBaseInfoCellContent();

	/**
	 * �������ݣ�����Ϣ
	 * 
	 * @return
	 */
	protected abstract SNameValue[] getDataInfoContent();

}
