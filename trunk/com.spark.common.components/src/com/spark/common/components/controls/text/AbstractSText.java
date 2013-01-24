package com.spark.common.components.controls.text;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.FocusEvent;
import com.jiuqi.dna.ui.wt.events.FocusListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;

public abstract class AbstractSText extends Composite implements STextImp{

	private Button advanceSearchButton;//高级搜索
	private final int TEXT_WIDTH;
	private Composite left_composite,right_composite,context_composite;
//	private String defaultValue;
	
	

	private Text text;
	/**
	 * 
	 * @param parent
	 * @param isAdvanceSearchButton 传入true，会加入高级搜索按钮，false就没有高级搜索
	 */
	public AbstractSText(Composite parent,boolean isAdvanceSearchButton) {
		super(parent);
		TEXT_WIDTH = 120;
		setStyle();
		addText();
		if(isAdvanceSearchButton) {
			addAdvanceSearchButton();
		}
		
	} 
	
	/**
	 * 
	 * @param parent
	 * @param isAdvanceSearchButton 传入true，会加入高级搜索按钮，false就没有高级搜索
	 * @param width text文本框的长度
	 */
	public AbstractSText(Composite parent,int width,boolean isAdvanceSearchButton) {
		super(parent);
		this.TEXT_WIDTH = width;
		setStyle();
		addText();
		if(isAdvanceSearchButton) {
			addAdvanceSearchButton();
		}
		
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
		GridLayout gridLayout = new GridLayout(this.getChildrenCount());
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = 0;
		GridData data = new GridData();
		data.horizontalAlignment = JWT.FILL;
		data.verticalAlignment = JWT.FILL;
		data.heightHint = 22;
		data.grabExcessHorizontalSpace = true;
		this.setLayoutData(data);
		this.setLayout(gridLayout);
		
	}
	
	/**
	 * 添加text左边的布局
	 */
	private void addLeft() {
		left_composite = new Composite(this);
		GridData gridData = new GridData();
		gridData.widthHint = 6;
		gridData.heightHint = 19;
		gridData.verticalAlignment = JWT.FILL;
		left_composite.setLayoutData(gridData);
		setLeftImage(left_composite);
	}

	public abstract void setLeftImage(Composite composite);
	
	/**
	 * 添加text中间的布局，放一个text进去
	 */
	private void addContext() {
		context_composite = new Composite(this);
		GridLayout fillLayout = new GridLayout();
		context_composite.setLayout(fillLayout);
		GridData gridData = new GridData();
		gridData.widthHint = this.TEXT_WIDTH;
		gridData.horizontalAlignment = JWT.FILL;
		gridData.verticalAlignment = JWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		context_composite.setLayoutData(gridData);
		setCenterImage(context_composite);
		Composite composite = new Composite(context_composite);
		composite.setLayout(new FillLayout());
		GridData gridData2 = new GridData();
		gridData2.heightHint = 15;
		gridData2.verticalAlignment = JWT.CENTER;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalIndent = 3;
		composite.setLayoutData(gridData2);
		text = new Text(composite,JWT.APPEARANCE2);
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
		text.setDescription("输入搜索内容"); 
		Font font = new Font();
		font.setSize(9);
		text.setFont(font);
		CBorder border = new CBorder();
		border.setThick(0);
		text.setBorder(border);
	}
	

	protected void addActive(){
	}
	
	protected void lostFocus() {
		if(null != text.getText() && !"".equals(text.getText().trim())) {
			text.setDescription("");
		}else {
			text.setDescription("输入搜索内容"); 
		}
	}

	protected abstract void setCenterImage(Composite context_composite);

	
	
	/**
	 * 添加text右边的布局，放一个搜索图标进去
	 */
	private void addRight() {
		right_composite = new Composite(this);
		GridData gridData = new GridData();
		gridData.widthHint = 26;
		gridData.heightHint = 22;
		gridData.verticalAlignment = JWT.TOP;
		gridData.horizontalAlignment = JWT.FILL;
		right_composite.setLayoutData(gridData);
		setRightImage(right_composite);
	}

	protected abstract void setRightImage(Composite composite);
	
	/**
	 * 高级搜索
	 *  void
	 */
	private void addAdvanceSearchButton(){
		//grid格式布局
		GridData gridData = new GridData();
		gridData.verticalAlignment = JWT.TOP;
		gridData.verticalIndent = -1;
		gridData.heightHint = 25;
		gridData.horizontalIndent = 3;
		gridData.widthHint = 67;
		advanceSearchButton = new Button(this);
		advanceSearchButton.setText("高级搜索");
		advanceSearchButton.setID(null);
		advanceSearchButton.setData(null);
		advanceSearchButton.setLayoutData(gridData);
	}
	
	

	/**
	 * 得到高级搜索button
	 */
	public Button getAdvanceSearchButton(){
		return advanceSearchButton;
	}
	
	/**
	 * 高级搜索点击事件
	 * 
	 * @param actionListener void
	 */
	public void addAdvanceSearchButtonAction(ActionListener actionListener){
		if(null!=advanceSearchButton) {
			advanceSearchButton.addActionListener(actionListener);
		}
		
	} 
	
	/**
	 * 搜索点击事件
	 * 
	 * @param actionListener void
	 */
	public void addSearchAction(final MouseClickListener mouseClickListener){
		if(null!=right_composite) {
			text.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					mouseClickListener.click(null);
				}
			});
			right_composite.addMouseClickListener(mouseClickListener);
		}
			
	} 
	
	
	/**
	 * 修改高级搜索的text
	 * @param name
	 */
	public void setAdvanceSearchButtonName(String name) {
		this.advanceSearchButton.setText(name);
	}
	
	/**
	 * 修改文本框的描述信息
	 * @param name
	 */
	public void setSearchDescription(String name) {
		this.text.setHint(name);
	}
	
	/**
	 * 得到当前文本框对象
	 * @return
	 */
	public Text getText() {
		return text;
	}
	
}
