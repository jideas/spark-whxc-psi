package com.spark.psi.inventory.browser.count;

import java.io.IOException;
import java.io.InputStream;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.excel.ExcelReadHelper;
import com.spark.common.utils.excel.ExcelReadHelper2007;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.StoreStatus;

/**
 * 
 */
public class InventoryCountTypeSelectProcessor extends BaseFormPageProcessor {

	public final static String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Button_Cancel = "Button_Cancel";
	public final static String ID_Radio_Goods = "Radio_Goods";
	public final static String ID_Radio_Kit = "Radio_Kit";
	public final static String ID_List_Store = "List_Store";
	public final static String ID_Text_Counter = "Text_Counter";

	private Button radioGoods;
	private Text counterText;
	private LWComboList storeList;

	public void init(Situation context) {

	}

	public void process(Situation context) {
		radioGoods = this.createControl(ID_Radio_Goods, Button.class);
		radioGoods.setSelection(true);
		counterText = this.createControl(ID_Text_Counter, Text.class);
		storeList = this.createControl(ID_List_Store, LWComboList.class);
		StoreStatus[] statuss = new StoreStatus[] { StoreStatus.ENABLE, StoreStatus.STOP };
		StoreSource storeSource = new StoreSource(statuss);
		storeList.getList().setSource(storeSource);
		storeList.getList().setInput(null);
		if (storeSource.getSize() == 1) {
			storeList.setSelection(storeSource.getFirstStoreId());
		}
		final FileChooser fc = this.createControl("FileChooser", FileChooser.class);
		final Button button = this.createControl(ID_Button_Confirm, Button.class);
		fc.setRelativeTarget(button, ActionListener.class);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateInput()) {
					return;
				}
				String storeId = storeList.getText();
				String counter = counterText.getText();
				InventoryCountType countType = radioGoods.getSelection() ? InventoryCountType.Materials
						: InventoryCountType.Kit;
				ExcelReadHelper read = null;
				if (radioGoods.getSelection()) {
					read = read(fc);
					if (null == read && CheckIsNull.isNotEmpty(fc.getFileName())) {
						return;
					}
				}
				getContext().bubbleMessage(
						new MsgResponse(true, GUID.valueOf(storeId), counter, countType, read));
			}
		});
		this.createControl(ID_Button_Cancel, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
		this.registerNotEmptyValidator(storeList, "盘点仓库");
		this.registerNotEmptyValidator(counterText, "盘点人");

		final Button radiokit = this.createControl(ID_Radio_Kit, Button.class);
		radiokit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (radiokit.getSelection()) {
					fc.setEnabled(false);
				}
			}
		});
		radioGoods.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (radiokit.getSelection()) {
					fc.setEnabled(true);
				}
			}
		});

	}

	private ExcelReadHelper read(FileChooser fc) {
		String text = fc.getFileName();
		if (CheckIsNull.isEmpty(text)) {
			return null;
		}
		if (!".xls".equals(text.substring(text.lastIndexOf(".")))&&!".xlsx".equals(text.substring(text.lastIndexOf(".")))) {
			alert("导入数据需要选择excel文件!");
			return null;
		}
		ExcelReadHelper read = null;
		if(".xls".equals(text.substring(text.lastIndexOf(".")))){
			read = new ExcelReadHelper();
		}else {
			read = new ExcelReadHelper2007();
		}
		if (CheckIsNull.isNotEmpty(text)) {
			InputStream input = null;
			try {
				input = fc.getInputStream(text);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				read.read(input, true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return read;
	}
}
