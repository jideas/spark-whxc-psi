package com.spark.uac.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.StyledPanel;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.uac.publish.entity.UserInfo;

public class SelectTenantWindow extends Window{

	UserInfo[] users;
	
//	private Button button;
	
	private SelectTenantListener selectTenantListener;
	
	private final static ImageBorder imageBorder = new ImageBorder(new ImageDescriptor[] {
			LoginImage.getImage("titleLeft.png"),
			LoginImage.getImage("titleCenter.png"),
			LoginImage.getImage("titleRight.png"),
			LoginImage.getImage("centerRight.png"),
			LoginImage.getImage("bottomRight.png"),
			LoginImage.getImage("bottomCenter.png"),
			LoginImage.getImage("bottomLeft.png"),
			LoginImage.getImage("centerLeft.png")
	});
	
	private UserInfo actionUser = null;
	
	public interface SelectTenantListener {
		public void onSelection(UserInfo user);
	}
	
	public SelectTenantWindow(UserInfo[] users){
	    super(JWT.CLOSE | JWT.RESIZE | JWT.NO_TRIM);
	    this.users = users;
	    initContorls();
    }
	/**
	 * ��ʼ������
	 */
	private void initContorls() {
		//����ҳ�沼��
		this.setLayout(new FillLayout());
		this.layout();
		this.setHeight(210);
		this.setWidth(330);
		StyledPanel styledPanel = new StyledPanel(this);
		styledPanel.setImageBorder(imageBorder);
		//����һ����������������ά��
		Composite contentArea = styledPanel.getContentArea();
		contentArea.setLayout(new GridLayout());
		contentArea.setBackimage(LoginImage.getImage("centerCenter.png"));
		Label titleLabel = new Label(contentArea);
		titleLabel.setText("��½��ҵѡ��");
		GridData dataLogin = new GridData();
		dataLogin.grabExcessHorizontalSpace = true;
		dataLogin.horizontalAlignment = JWT.CENTER;
		dataLogin.heightHint = 20;
		dataLogin.verticalIndent = 10;
		titleLabel.setLayoutData(dataLogin);
		Font titleFont = new Font();
		titleFont.setSize(14);
		titleFont.setStyle(JWT.FONT_STYLE_BOLD);
		titleLabel.setForeground(new Color(0x909090));
		titleLabel.setFont(titleFont);
		
		
		
		Composite root = new Composite(contentArea);
		/*GridLayout loginLayout = new GridLayout(2);
		root.setLayout(loginLayout);*/
		dataLogin = new GridData();
		dataLogin.horizontalAlignment = JWT.FILL;
		dataLogin.verticalAlignment = JWT.FILL;
		dataLogin.grabExcessHorizontalSpace = true;
		dataLogin.grabExcessVerticalSpace = true;
		dataLogin.widthHint = 330;
		dataLogin.verticalIndent = 10;
		root.setLayoutData(dataLogin);
		root.setLayout(new FillLayout());
		root.setBackground(Color.COLOR_WHITE);
		root.setBorder(new CBorder(1,JWT.BORDER_STYLE_SOLID, 0xCBCBCB));
		
		ScrolledPanel scrolledPanel = new ScrolledPanel(root,JWT.V_SCROLL);
		
		Composite scrollComposite = scrolledPanel.getComposite();
		
		GridLayout scrollgridLayout = new GridLayout();
		scrollComposite.setLayout(scrollgridLayout);
		
		
		//����һ�����radio������
		Composite radioComposite = new Composite(scrollComposite);
		GridData compostieData = new GridData();
		compostieData.grabExcessVerticalSpace = true;
		compostieData.verticalAlignment = JWT.FILL;
		compostieData.grabExcessHorizontalSpace = true;
		compostieData.horizontalAlignment = JWT.FILL;
		compostieData.horizontalIndent = 5;
		compostieData.verticalIndent = 5;
		radioComposite.setLayoutData(compostieData);
		radioComposite.setLayout(new GridLayout());
		radioComposite.layout();
		
		for (int i = 0 ;i <users.length;i++) {
			UserInfo user = users[i];
			final Button buttonRadio = new Button(radioComposite,JWT.RADIO);
			buttonRadio.setData(user);
			if(i==0) {
				buttonRadio.setSelection(true);
				actionUser = (UserInfo)buttonRadio.getData();
			}
			buttonRadio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					actionUser = (UserInfo)buttonRadio.getData();
				}
			});
			buttonRadio.setText(user.getTenantName());
		}
		
		
		//����һ���ײ���ť����
		Composite buttonComposite = new Composite(contentArea);
		compostieData = new GridData();
		compostieData.grabExcessHorizontalSpace = true;
		compostieData.horizontalAlignment = JWT.CENTER;
		compostieData.heightHint = 35;
		compostieData.verticalIndent = 5;
		buttonComposite.setLayoutData(compostieData);
		buttonComposite.setLayout(new GridLayout(2));
		buttonComposite.layout();
		Button button = new Button(buttonComposite,JWT.APPEARANCE2);
		button.setText("ȷ��");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectTenantListener!=null && actionUser != null){
					selectTenantListener.onSelection(actionUser);
				}	
			}
		});
		GridData buttonData = new GridData();
		buttonData.heightHint = 30;
		buttonData.widthHint = 65;
		button.setLayoutData(buttonData);
		Button button2 = new Button(buttonComposite,JWT.APPEARANCE1);
		button2.setText("ȡ��");
		button2.setLayoutData(buttonData);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		scrollComposite.layout();
		root.layout();
	}

	public void addSelectTenantListener(SelectTenantListener l){
		this.selectTenantListener = l;
	}
}
