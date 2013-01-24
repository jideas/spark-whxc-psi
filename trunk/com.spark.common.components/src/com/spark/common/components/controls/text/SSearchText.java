package com.spark.common.components.controls.text;

/**
 * 搜索框
 */
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.FocusEvent;
import com.jiuqi.dna.ui.wt.events.FocusListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;

public class SSearchText extends Composite {

	private final int TEXT_WIDTH;
	
	private String defaultValue;
	private Composite main_composite,left_composite,right_composite,context_composite;

	private Text text;
	
	protected Composite button_composite;
	
	/**
	 * 
	 * @param parent
	 */
	public SSearchText(Composite parent) {
		super(parent);
		TEXT_WIDTH = 120;
		setStyle();
		addText();
	} 
	
	/**
	 * 
	 * @param parent
	 * @param width text文本框的长度
	 */
	public SSearchText(Composite parent,int width) {
		super(parent);
		this.TEXT_WIDTH = width;
		setStyle();
		addText();
	} 
	
	protected void initButton() {
		button_composite = new Composite(main_composite);
		GridData gridData = new GridData();
		gridData.heightHint = 24;
		gridData.horizontalAlignment = JWT.FILL;
		gridData.verticalAlignment = JWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		button_composite.setLayoutData(gridData);
		GridLayout gridLayout = new GridLayout(2);
		gridLayout.marginLeft = 5;
		button_composite.setLayout(gridLayout);
		button_composite.layout();
	}

	public Composite getLeft_composite(){
    	return left_composite;
    }

	public Composite getRight_composite(){
    	return right_composite;
    }

	public Composite getContext_composite(){
    	return context_composite;
    }
	
	/**
	 * 添加text
	 */
	private void addText() {
		addLeft();
		addContext();
		addRight();
	}
	
	/**
	 * 设置样式
	 */
	private void setStyle() {
		this.setLayout(new FillLayout());
		GridData data = new GridData();
		data.horizontalAlignment = JWT.FILL;
		data.verticalAlignment = JWT.TOP;
		data.heightHint = 24;
		data.grabExcessHorizontalSpace = true;
		this.setLayoutData(data);
		this.layout();
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = 0;
		
		main_composite = new Composite(this);
		data = new GridData();
		data.horizontalAlignment = JWT.FILL;
		data.verticalAlignment = JWT.CENTER;
		data.grabExcessHorizontalSpace = true;
		main_composite.setLayoutData(data);
		main_composite.setLayout(gridLayout);
		main_composite.layout();
		
		defaultValue = SAAS.SEARCH_DEFAULT_KEY;
	}
	
	/**
	 * 添加text左边的布局
	 */
	private void addLeft() {
		left_composite = new Composite(main_composite);
		GridData gridData = new GridData();
		gridData.widthHint = 6;
		gridData.heightHint = 22;
		gridData.verticalAlignment = JWT.CENTER;
		left_composite.setLayoutData(gridData);
		setLeftImage(left_composite);
	}

	/**
	 * 添加text中间的布局，放一个text进去
	 */
	private void addContext() {
		context_composite = new Composite(main_composite);
		GridLayout fillLayout = new GridLayout();
		context_composite.setLayout(fillLayout);
		GridData gridData = new GridData();
		gridData.widthHint = this.TEXT_WIDTH;
		gridData.heightHint = 22;
		gridData.horizontalAlignment = JWT.FILL;
		gridData.verticalAlignment = JWT.CENTER;
		context_composite.setLayoutData(gridData);
		setCenterImage(context_composite);
		Composite composite = new Composite(context_composite);
		composite.setLayout(new FillLayout());
		GridData gridData2 = new GridData();
		gridData2.heightHint = 15;
		gridData2.verticalAlignment = JWT.CENTER;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalIndent = 4;
		composite.setLayoutData(gridData2);
		text = new Text(composite,JWT.APPEARANCE2|JWT.MIDDLE);
		text.setBackground(new Color(0xe1ecf5));
		text.setActiveBackground(new Color(0xe1ecf5));
		text.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				lostFocus();
			}
			public void focusGained(FocusEvent arg0) {
				addActive();
			}
		});
		text.setText(defaultValue); 
		Font font = new Font();
		font.setSize(9);
		text.setFont(font);
		CBorder border = new CBorder();
		border.setThick(0);
		text.setBorder(border);
	}
	
	private void addRight() {
		right_composite = new Composite(main_composite);
		GridData gridData = new GridData();
		gridData.widthHint = 6;
		gridData.heightHint = 22;
		gridData.verticalAlignment = JWT.CENTER;
		gridData.horizontalAlignment = JWT.FILL;
		right_composite.setLayoutData(gridData);
		setRightImage(right_composite);
	}

	protected void addActive(){
		if(defaultValue.equals(text.getText())) {
			text.setText(SAAS.EMPTY_KEY);  
		}
	}
	
	protected void lostFocus() {
		if(defaultValue.equals(text.getText()) || SAAS.EMPTY_KEY.equals(text.getText())) {
			text.setText(defaultValue); 
		}
	}
	
	public void setTipValue(String tipValue) {
		this.defaultValue = tipValue;
	}

	/**
	 * 修改文本框的描述信息
	 * @param name
	 */
	public void setSearchDescription(String name) {
		this.text.setHint(name);
	}
	
	/**
	 * 得到当前文本框内容
	 * @return
	 */
	public String getText() {
		String value = text.getText();
		if(defaultValue.equals(value)) {
			value = SAAS.EMPTY_KEY;
		}
		return value;
	}
	
	public void setLeftImage(Composite composite){
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_bg_left.png"));
	}
	
	protected void setCenterImage(Composite contextComposite){
		contextComposite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_bg_center.png"));

		
	}
	
	protected void setRightImage(Composite composite){
		composite.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.spark.common.components","img/search/search_bg_right2.png"));
	}
}
