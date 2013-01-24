package com.spark.psi.base.browser.config;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.controls.text.SAAS;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;

public class DepartmentTreePage extends Page {
	
	
	private Composite mainCmp,canterCmp,topCmp;	
	
	private Situation context;

	public DepartmentTreePage(Composite parent) {
		
		super(parent);
		context = getContext();
		init();
	}
	
	private void init() {
		initCmp();//����
		initControl();//��̬���ɿؼ�
	}

	private void initControl() {
		
		Label label = new Label(topCmp);
		label.setText("��ʾ��������Ϣ��ҵ��������������޸ģ�����ϵҵ�����");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_END | GridData.GRAB_VERTICAL | GridData.GRAB_HORIZONTAL));
		label.setForeground(Color.COLOR_RED);
			
		//������ canterCmp
		final DepartmentTree departmentTree = context.find(DepartmentTree.class);
		new BigTree(canterCmp,departmentTree.getRoot());
	}

	/**
	 * 
	 * mainCmp�����������
	 * 
	 */
	private void initCmp() {
		
		FillLayout layout = new FillLayout();
		this.setLayout(layout);
		
//		ScrolledPanel panel = new ScrolledPanel(this, JWT.V_SCROLL);	panel.getComposite();//	
		mainCmp = new Composite(this);
		
		GridLayout gridLayout = new GridLayout(1);
		gridLayout.verticalSpacing = 8;
		
		mainCmp.setLayout(gridLayout);
		
		initTopCmp();
		initCanterCmp();
	}

	//top ������ʾlabel��ʾ
	private void initTopCmp(){
		
		topCmp = new Composite(mainCmp);
		topCmp.setLayout(new GridLayout(1));
		
		GridData gridData =  new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL);
		gridData.heightHint = 22;
		
		topCmp.setLayoutData(gridData);
	}

	//��Ҫ���� ������ʾ
	private void initCanterCmp(){
		
		ScrolledPanel panel = new ScrolledPanel(mainCmp, JWT.V_SCROLL);		
		panel.setLayoutData(GridData.INS_FILL_BOTH);
		canterCmp = panel.getComposite();//new Composite(this);
		
//		canterCmp = new Composite(mainCmp);
		
		canterCmp.setLayoutData(GridData.INS_FILL_BOTH);//�ؼ�ˮƽ��ֱ����������
		
		canterCmp.setLayout(SAAS.Layout.fillLayoutIns);
	}
}