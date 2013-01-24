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

	private Button advanceSearchButton;//�߼�����
	private final int TEXT_WIDTH;
	private Composite left_composite,right_composite,context_composite;
//	private String defaultValue;
	
	

	private Text text;
	/**
	 * 
	 * @param parent
	 * @param isAdvanceSearchButton ����true�������߼�������ť��false��û�и߼�����
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
	 * @param isAdvanceSearchButton ����true�������߼�������ť��false��û�и߼�����
	 * @param width text�ı���ĳ���
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
	 * ���text
	 */
	private void addText() {
		addLeft();
		addContext();
		addRight();
	}
	
	/**
	 * ������ʽ
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
	 * ���text��ߵĲ���
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
	 * ���text�м�Ĳ��֣���һ��text��ȥ
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
		text.setDescription("������������"); 
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
			text.setDescription("������������"); 
		}
	}

	protected abstract void setCenterImage(Composite context_composite);

	
	
	/**
	 * ���text�ұߵĲ��֣���һ������ͼ���ȥ
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
	 * �߼�����
	 *  void
	 */
	private void addAdvanceSearchButton(){
		//grid��ʽ����
		GridData gridData = new GridData();
		gridData.verticalAlignment = JWT.TOP;
		gridData.verticalIndent = -1;
		gridData.heightHint = 25;
		gridData.horizontalIndent = 3;
		gridData.widthHint = 67;
		advanceSearchButton = new Button(this);
		advanceSearchButton.setText("�߼�����");
		advanceSearchButton.setID(null);
		advanceSearchButton.setData(null);
		advanceSearchButton.setLayoutData(gridData);
	}
	
	

	/**
	 * �õ��߼�����button
	 */
	public Button getAdvanceSearchButton(){
		return advanceSearchButton;
	}
	
	/**
	 * �߼���������¼�
	 * 
	 * @param actionListener void
	 */
	public void addAdvanceSearchButtonAction(ActionListener actionListener){
		if(null!=advanceSearchButton) {
			advanceSearchButton.addActionListener(actionListener);
		}
		
	} 
	
	/**
	 * ��������¼�
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
	 * �޸ĸ߼�������text
	 * @param name
	 */
	public void setAdvanceSearchButtonName(String name) {
		this.advanceSearchButton.setText(name);
	}
	
	/**
	 * �޸��ı����������Ϣ
	 * @param name
	 */
	public void setSearchDescription(String name) {
		this.text.setHint(name);
	}
	
	/**
	 * �õ���ǰ�ı������
	 * @return
	 */
	public Text getText() {
		return text;
	}
	
}
