package com.spark.common.components.pages;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;

/**
 * 基础的表单页面视图<br>
 * 
 */
public abstract class BaseFormPageRender extends AbstractBoxPageRender {

	private final static GridData gd1;
	private final static GridData gd2;
	private final static GridData gdLabel;
	private final static GridData gdHidden;

	private final static GridData gdButton1;
	private final static GridData gdButton2;

	static {
		gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 4;

		gd2 = new GridData(GridData.FILL_BOTH);
		gd2.horizontalSpan = 2;

		gdLabel = new GridData();
		gdLabel.widthHint = 120;

		gdHidden = new GridData();
		gdHidden.exclude = true;

		//
		gdButton1 = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gdButton1.heightHint = 28;
		gdButton2 = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gdButton2.heightHint = 28;

	}

	private Composite formArea;

	private Composite footerArea;

	private Composite contentArea;

	/**
	 * 是否自定义Form布局
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout() {
		return false;
	}

	/**
	 * 是否自定义按钮布局
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout() {
		return false;
	}

	protected final void beforeFooterRender() {
		this.contentArea = super.contentArea;
		formArea = new Composite(contentArea);
		formArea.setLayoutData(GridData.INS_FILL_BOTH);
		if (!customizeFormLayout()) {
			GridLayout gl = new GridLayout();
			gl.numColumns = 4;
			formArea.setLayout(gl);
		}
		renderFormArea(formArea);
	}

	protected final void afterFooterRender() {
		this.footerArea = super.footerArea;
		renderButton(footerArea);
		if (!customizeButtonLayout()) {
			GridLayout gl = new GridLayout();
			gl.numColumns = footerArea.getChildrenCount();
			this.footerArea.setLayout(gl);
			Control[] children = this.footerArea.getChildren();
			if (children != null) {
				for (int i = 0; i < children.length; i++) {
					if (i == 0) {
						children[0].setLayoutData(gdButton1);
					} else {
						children[i].setLayoutData(gdButton2);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param formArea
	 */
	protected abstract void renderFormArea(Composite formArea);

	/**
	 * 
	 * @param buttonArea
	 */
	protected abstract void renderButton(Composite buttonArea);

	@Override
	protected final void afterRender() {
		super.afterRender();
		if (!customizeFormLayout()) {
			Control[] children = this.formArea.getChildren();
			for (Control control : children) {
				if (control instanceof Text) {
					String id = control.getID();
					if (id == null) {
						id = "Text";
					}
					Label label = new Label(formArea);
					label.setText(id + "：");
					label.setLayoutData(gdLabel);
					control.setLayoutData(GridData.INS_FILL_HORIZONTAL);
					label.moveBefore(control);
					// } else if (control instanceof Composite) {
					// String id = control.getID();
					// if (id != null) {
					// Label label = new Label(formArea);
					// label.setText(id);
					// label.setLayoutData(gd1);
					// label.moveBefore(control);
					// }
					// control.setLayoutData(gd2);
				} else if (control instanceof Label
						&& ((control.getStyle() & JWT.SEPARATOR) != 0)) {
					control.setLayoutData(gdHidden);
					control.setVisible(false);
				} else {
					control.setLayoutData(gd1);
				}
			}
		}
	}
}
