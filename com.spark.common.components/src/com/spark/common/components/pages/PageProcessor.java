package com.spark.common.components.pages;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.custom.hotkey.HotkeyDefine;
import com.jiuqi.dna.ui.custom.popup.SecondsWindow;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.portal.browser.SFocusInfoWindow;
import com.spark.portal.browser.SMenuWindow;

/**
 * 页面处理器基类<br>
 * 主要负责以下工作：<br>
 * (1)获取控件 <br>
 * (2)控件初始化<br>
 * (3)响应控件动作<br>
 * 不负责以下工作：<br>
 * (1)控件实际创建过程<br>
 * (2)控件外观<br>
 * (3)页面布局<br>
 */
public abstract class PageProcessor {

	/**
	 * 
	 */
	static {
		SecondsWindow.setImageBorder(SMenuWindow.imageBorder);
		SecondsWindow.setContentBackimage(SMenuWindow.backImage);

	}

	/**
	 * 传递的参数
	 */
	private Object argument;

	protected Situation context;
	/**
	 * 传递的参数
	 */
	private Object argument2;

	/**
	 * 传递的参数
	 */
	private Object argument3;

	/**
	 * 传递的参数
	 */
	private Object argument4;

	/**
	 * 对应的渲染器
	 */
	private PageRender render;

	/**
	 * 
	 */
	@SuppressWarnings( { "unchecked" })
	private List<InputValidator> validatorList;

	/**
	 * 
	 */
	private SFocusInfoWindow focusInfoWindow;

	/**
	 * 
	 * @param render
	 */
	protected final void setRender(PageRender render) {
		this.render = render;
	}

	/**
	 * 页面初始化之前
	 * 
	 * @param situation
	 */
	public void init(Situation context) {

	}

	/**
	 * 页面初始化
	 * 
	 * @param situation
	 */
	public abstract void process(Situation context);

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context) {

	}

	/**
	 * 处理销毁
	 * 
	 * @param context
	 */
	public void postDisposed(Situation context) {

	}

	/**
	 * 提供给子类用来创建或获取相应控件的方法
	 * 
	 * @param clazz
	 * @param controlId
	 * @param style
	 * @return
	 */
	protected final <T extends Control> T createControl(String controlId, Class<T> clazz, int style) {
		return render.createControl(controlId, clazz, style);
	}

	/**
	 * 提供给子类用来创建或获取相应控件的方法
	 * 
	 * @param clazz
	 * @param controlId
	 * @return
	 */
	protected final <T extends Control> T createControl(String controlId, Class<T> clazz) {
		return createControl(controlId, clazz, JWT.NONE);
	}

	protected Text createTextControl(String controlId) {
		return createControl(controlId, Text.class);
	}

	protected Label createLabelControl(String controlId) {
		return createControl(controlId, Label.class);
	}

	protected LWComboList createLWComboListControl(String controlId) {
		return createControl(controlId, LWComboList.class);
	}

	protected SDatePicker createSDatePickerControl(String controlId) {
		return createControl(controlId, SDatePicker.class);
	}
	protected Button createButtonControl(String controlId) {
		return createControl(controlId, Button.class);
	}
	/**
	 * 
	 * @return
	 */
	protected final Situation getContext() {
		this.context = this.render.getContext();
		return context;
	}

	/**
	 * @return the argument
	 */
	public Object getArgument() {
		return argument;
	}

	/**
	 * @param arguments
	 *            the argument to set
	 */
	public void setArgument(Object argument) {
		this.argument = argument;
	}

	/**
	 * @return the argument2
	 */
	public Object getArgument2() {
		return argument2;
	}

	/**
	 * @param argument2
	 *            the argument2 to set
	 */
	public void setArgument2(Object argument2) {
		this.argument2 = argument2;
	}

	/**
	 * @return the argument3
	 */
	public Object getArgument3() {
		return argument3;
	}

	/**
	 * @param argument3
	 *            the argument3 to set
	 */
	public void setArgument3(Object argument3) {
		this.argument3 = argument3;
	}

	/**
	 * @return the argument4
	 */
	public Object getArgument4() {
		return argument4;
	}

	/**
	 * @param argument4
	 *            the argument4 to set
	 */
	public void setArgument4(Object argument4) {
		this.argument4 = argument4;
	}

	/**
	 * 得到快捷键列表
	 * 
	 * @return
	 */
	public HotkeyDefine[] getHotkeyDefines() {
		return null;
	}

	/**
	 * 
	 * @param hotkeyDefine
	 */
	public void hotkeyPressed(HotkeyDefine hotkeyDefine) {
	}

	/**
	 * 弹出一个选择确认对话框
	 * 
	 * @param message
	 * @param runnable
	 */
	protected final void confirm(String message, Runnable runnable) {
		new SMessageConfirmWindow(message, runnable);
	}
	
	protected final void confirm(String message, Runnable runnable, Runnable cancelRunnable) {
		new SMessageConfirmWindow(message, runnable, cancelRunnable);
	}

	/**
	 * 
	 * @param message
	 */
	protected final void alert(String message) {
		this.alert(message, null);
	}

	/**
	 * 
	 * @param message
	 */
	protected final void hint(String message) {
		// SecondsWindow.showMessage(message, 1);
		hint(message, null);
	}

	/**
	 * 
	 * @param message
	 * @param runnable
	 */
	protected final void hint(String message, Runnable runnable) {
		new SMessageAlertWindow(false, message, runnable);
	}

	/**
	 * 
	 * @param message
	 */
	protected final void alert(String message, final Runnable runnable) {
		// PageController pc = new PageController(AlertPageProcessor.class,
		// AlertPageRender.class);
		// PageControllerInstance pci = new PageControllerInstance(pc, message);
		// WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		// windowStyle.setSize(400, 220);
		// MsgRequest request = new MsgRequest(pci, " 提示信息", windowStyle);
		// request.setIcon(CommonImages.getImage("img/dialog/dialog.png"));
		// if (runnable != null) {
		// request.setCancelHandler(new CancelHandler() {
		// public void handle() {
		// runnable.run();
		// }
		// });
		// }
		// getContext().bubbleMessage(request);
		new SMessageAlertWindow(true, message, runnable);
	}

	/**
	 * 
	 */
	protected final void resetDataChangedstatus() {
		try {
			getContext().bubbleMessage(new MsgResetDataStatus());
		} catch (Throwable t) {
		}
	}

	/**
	 * 
	 */
	protected final void markDataChanged() {
		try {
			getContext().bubbleMessage(new MsgMarkDataChanged());
		} catch (Throwable t) {
		}
	}

	/**
	 * 
	 * @param text
	 * @param title
	 */
	protected final void registerNotEmptyValidator(Text text, String title) {
		registerInputValidator(new TextInputNotEmptyValidator(text, title));
	}

	/**
	 * 
	 * @param validator
	 */
	@SuppressWarnings("unchecked")
	protected final void registerInputValidator(InputValidator validator) {
		if (validatorList == null) {
			validatorList = new ArrayList<InputValidator>();
		}
		validatorList.add(validator);
		String validatorIds = (String) validator.control.getData("validatorIds");
		if (validatorIds == null) {
			validatorIds = validator.id;
		} else {
			validatorIds = validatorIds + ";" + validator.id;
		}
		validator.control.setData("validatorIds", validatorIds); // 记录对应控件的所有校验器Id
	}

	/**
	 * 校验所有输入
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	protected final boolean validateInput() {
		Control firstControl = null;
		if (validatorList != null) {
			List<InputValidator> disposedList = new ArrayList<InputValidator>();
			for (InputValidator validator : validatorList) {
				if (validator.control.isDisposed()) {
					disposedList.add(validator);
					continue;
				}
				if (!validator.validate(validator.control)) {
					if (firstControl == null) {
						firstControl = validator.control;
					}
				}
			}
			for (InputValidator validator : disposedList) {
				validatorList.remove(validator);
			}
		}
		if (firstControl != null) {
			firstControl.forceFocus();
			return false;
		} else { 
			return true;
		}
	}

	protected final boolean validateInput(Control control) {
		return validateInput(control, null);
	}

	@SuppressWarnings( { "unchecked" })
	protected final boolean validateInput(Control control, Object scope) {
		boolean failed = false;
		if (validatorList != null && validatorList.size() > 0) {
			for (InputValidator validator : validatorList) {
				if (validator.control == control) {
					if (validator instanceof InputValidator2) {
						if (!((InputValidator2) validator).validate(control, scope)) {
							failed = true;
						}
					} else {
						if (!validator.validate(control)) {
							failed = true;
						}
					}
				}
			}
		}
		if (failed) {
			control.forceFocus();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param control
	 * @param title
	 */
	private void bindFocusInfoWindow(Control control, String title) {
		if (focusInfoWindow == null) {
			focusInfoWindow = new SFocusInfoWindow(render.contentArea);
			// focusInfoWindow.addClientEventHandler(
			// PopupWindow.CLIENT_EVENT_BeforeShow,
			// "SaasNavigator.handleFocusInfoWindowBeforeShow");
		}
		focusInfoWindow.bindTargetControl(control, title);
	}

	/**
	 * 
	 * @param control
	 */
	private void unbindFocusInfoWindow(Control control) {
		if (focusInfoWindow != null) {
			focusInfoWindow.unbindTargetControl(control);
		}
	}

	/**
	 * 输入校验器
	 */
	protected abstract class InputValidator<TControl extends Control> {
		protected TControl control;
		protected String id;

		/**
		 * 
		 * @param control
		 */
		public InputValidator(TControl control) {
			this.id = String.valueOf(GUID.randomLong());
			this.control = control;
		}

		/**
		 * 
		 * @param control
		 * @return
		 */
		protected abstract boolean validate(TControl control);
	}

	/**
	 * 输入校验器
	 */
	protected abstract class InputValidator2<TControl extends Control, TScope> extends InputValidator<TControl> {

		/**
		 * 
		 * @param control
		 */
		public InputValidator2(TControl control) {
			super(control);
		}

		/**
		 * 
		 * @param control
		 * @return
		 */
		protected final boolean validate(TControl control) {
			return validate(control, null);
		}

		/**
		 * 
		 * @param control
		 * @return
		 */
		protected abstract boolean validate(TControl control, TScope scope);
	}

	/**
	 * 文本输入框校验器
	 */
	protected abstract class TextInputValidator extends InputValidator<Text> {

		protected String hint;

		/**
		 * 
		 * @param control
		 */
		public TextInputValidator(Text control, String hint) {
			super(control);
			this.hint = hint;
		}

		/**
		 * 
		 * @param control
		 * @return
		 */
		protected boolean validate(Text control) {
			String info = null;
			boolean failed = false;
			if (!validateText(control.getText())) {
				info = hint;
				failed = true;
			}
			String[] ids = StringUtils.split((String) control.getData("validatorIds"), ";");
			if (ids.length > 1) { // 如果控件不止一个校验器，则需要看所有校验结果
				control.setData(id, info);
				StringBuffer buffer = new StringBuffer();
				for (String id : ids) {
					String s = (String) control.getData(id);
					if (!StringUtils.isEmpty(s)) {
						if (buffer.length() > 0) {
							buffer.append("\n");
						}
						buffer.append(s);
					}
				}
				info = buffer.toString();
			}
			//
			if (!StringUtils.isEmpty(info)) {
				bindFocusInfoWindow(control, info);
				control.applyMode(JWT.MODE_ERROR);
			} else {
				unbindFocusInfoWindow(control);
				control.removeMode(JWT.MODE_ERROR);
			}
			return !failed;
		}

		/**
		 * 
		 * @param control
		 * @return
		 */
		protected abstract boolean validateText(String text);
	}

	/**
	 * 输入非空校验器
	 */
	protected final class TextInputNotEmptyValidator extends TextInputValidator {

		public TextInputNotEmptyValidator(Text control, String title) {
			super(control, title + "不可为空");
		}

		@Override
		protected boolean validateText(String text) {
			return !StringUtils.isEmpty(text);
		}

	}

	protected abstract class TableDataValidator extends InputValidator2<SEditTable, String[]> {

		public TableDataValidator(SEditTable table) {
			super(table);
		}

		@Override
		protected final boolean validate(SEditTable table, String[] rowIds) {
			String info = null;
			if (rowIds == null) {
				rowIds = table.getAllRowId();
				info = validateRowCount(rowIds == null ? 0 : rowIds.length);
				if (!StringUtils.isEmpty(info)) {
					alert(info);
					return false;
				}
			}
			SEditColumn[] columns = table.getEditColumns();
			if (rowIds != null && rowIds.length > 0) {
				for (String rowId : rowIds) {
					for (SEditColumn column : columns) {
						info = validateCell(rowId, column.getName());
						// XXX：暂时简单实现，应该定位到表格
						if (!StringUtils.isEmpty(info)) {
							alert(info);
							return false;
						}
						// table.markEditCellstatus(rowId, column.getName(),
						// );
					}
				}
			}
			return true;
		}

		protected abstract String validateRowCount(int rowCount);

		protected abstract String validateCell(String rowId, String columnName);

	}
}
