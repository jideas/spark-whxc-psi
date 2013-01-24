package com.spark.psi.base.browser.contact;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * 
 * <p>通讯录拼音搜索</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-9
 */
public class ContactPersonPhoneticNavigatorBar extends Composite{

	private ImageDescriptor img_abc_hover;
	private ImageDescriptor img_abc_normal;
	private ImageDescriptor img_abc_selected;
	private ImageDescriptor img_all_hover;
	private ImageDescriptor img_all_normal;
	private ImageDescriptor img_all_selected;
	private ImageDescriptor img_bg_hover;
	private ImageDescriptor img_bg_normal;
	private ImageDescriptor img_def_hover;
	private ImageDescriptor img_def_normal;
	private ImageDescriptor img_def_selected;
	private ImageDescriptor img_ghi_hover;
	private ImageDescriptor img_ghi_normal;
	private ImageDescriptor img_ghi_selected;
	private ImageDescriptor img_jkl_hover;
	private ImageDescriptor img_jkl_normal;
	private ImageDescriptor img_jkl_selected;
	private ImageDescriptor img_mno_hover;
	private ImageDescriptor img_mno_normal;
	private ImageDescriptor img_mno_selected;
	private ImageDescriptor img_pqrs_hover;
	private ImageDescriptor img_pqrs_normal;
	private ImageDescriptor img_pqrs_selected;
	private ImageDescriptor img_tuv_hover;
	private ImageDescriptor img_tuv_normal;
	private ImageDescriptor img_tuv_selected;
	private ImageDescriptor img_wxyz_hover;
	private ImageDescriptor img_wxyz_normal;
	private ImageDescriptor img_wxyz_selected;

	private List<NavigatorButton> navBtnList = new ArrayList<NavigatorButton>();

	private String text = "";

	public String getText(){
		return text;
	}

	/**
	 * 
	 *构造方法
	 *@param parent 父容器
	 */
	ContactPersonPhoneticNavigatorBar(Composite parent){
		super(parent);
		initControls();
	}

	/**
	 * 初始化控件
	 */
	private void initControls(){
		initImage();
		initButton();
	}

	/**
	 * 初始化按钮
	 */
	private void initButton(){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		this.setLayout(gridLayout);
		navBtnList.add(new NavigatorButton(this, img_all_normal, img_all_selected, "", true));
		navBtnList.add(new NavigatorButton(this, img_abc_normal, img_abc_selected, "abc"));
		navBtnList.add(new NavigatorButton(this, img_def_normal, img_def_selected, "def"));
		navBtnList.add(new NavigatorButton(this, img_ghi_normal, img_ghi_selected, "ghi"));
		navBtnList.add(new NavigatorButton(this, img_jkl_normal, img_jkl_selected, "jkl"));
		navBtnList.add(new NavigatorButton(this, img_mno_normal, img_mno_selected, "mno"));
		navBtnList.add(new NavigatorButton(this, img_pqrs_normal, img_pqrs_selected, "pqrs"));
		navBtnList.add(new NavigatorButton(this, img_tuv_normal, img_tuv_selected, "tuv"));
		navBtnList.add(new NavigatorButton(this, img_wxyz_normal, img_wxyz_selected, "wxyz"));
		this.layout();
	}

	/**
	 * 初始化图片
	 */
	private void initImage(){
		img_bg_normal = BaseImages.getImage("images/contact/saas_adress_bg.png");
		img_bg_hover = BaseImages.getImage("images/contact/saas_adress_bg_h.png");

		img_all_normal = BaseImages.getImage("images/contact/saas_adress_all_n.png");
		img_all_hover = BaseImages.getImage("images/contact/saas_adress_all_n.png");
		img_all_selected = BaseImages.getImage("images/contact/saas_adress_all_s_n.png");

		img_abc_hover = BaseImages.getImage("images/contact/saas_adress_abc_h.png");
		img_abc_normal = BaseImages.getImage("images/contact/saas_adress_abc_n.png");
		img_abc_selected = BaseImages.getImage("images/contact/saas_adress_abc_s_n.png");

		img_def_hover = BaseImages.getImage("images/contact/saas_adress_def_h.png");
		img_def_normal = BaseImages.getImage("images/contact/saas_adress_def_n.png");
		img_def_selected = BaseImages.getImage("images/contact/saas_adress_def_s_n.png");

		img_ghi_hover = BaseImages.getImage("images/contact/saas_adress_ghi_h.png");
		img_ghi_normal = BaseImages.getImage("images/contact/saas_adress_ghi_n.png");
		img_ghi_selected = BaseImages.getImage("images/contact/saas_adress_ghi_s_n.png");

		img_jkl_hover = BaseImages.getImage("images/contact/saas_adress_jkl_h.png");
		img_jkl_normal = BaseImages.getImage("images/contact/saas_adress_jkl_n.png");
		img_jkl_selected = BaseImages.getImage("images/contact/saas_adress_jkl_s_n.png");

		img_mno_hover = BaseImages.getImage("images/contact/saas_adress_mno_h.png");
		img_mno_normal = BaseImages.getImage("images/contact/saas_adress_mno_n.png");
		img_mno_selected = BaseImages.getImage("images/contact/saas_adress_mno_s_n.png");

		img_pqrs_hover = BaseImages.getImage("images/contact/saas_adress_pqrs_h.png");
		img_pqrs_normal = BaseImages.getImage("images/contact/saas_adress_pqrs_n.png");
		img_pqrs_selected = BaseImages.getImage("images/contact/saas_adress_pqrs_s_n.png");

		img_tuv_hover = BaseImages.getImage("images/contact/saas_adress_tuv_h.png");
		img_tuv_normal = BaseImages.getImage("images/contact/saas_adress_tuv_n.png");
		img_tuv_selected = BaseImages.getImage("images/contact/saas_adress_tuv_s_n.png");

		img_wxyz_hover = BaseImages.getImage("/images/contact/saas_adress_wxyz_h.png");
		img_wxyz_normal = BaseImages.getImage("images/contact/saas_adress_wxyz_n.png");
		img_wxyz_selected = BaseImages.getImage("images/contact/saas_adress_wxyz_s_n.png");
	}

	/**
	 * 拼音导航按钮
	 */
	protected class NavigatorButton{

		/**
		 * 组件
		 */
		private Composite container;
		private Label labelBtn;
		private ImageDescriptor imgNormal, imgSelected;
		private String text;

		/**
		 *  创建导航按钮(默认不选中)
		 */
		public NavigatorButton(Composite parent, ImageDescriptor imgNormal, ImageDescriptor imgSelected, String text){
			this(parent, imgNormal, imgSelected, text, false);
		}

		/**
		 *  创建导航按钮
		 */
		public NavigatorButton(Composite parent, ImageDescriptor imgNormal, ImageDescriptor imgSelected, String text,
		        boolean selected)
		{
			this.imgNormal = imgNormal;
			this.imgSelected = imgSelected;
			this.text = text;
			//容器
			container = new Composite(parent);
			container.setLayout(new GridLayout());
			container.setLayoutData(new GridData(60, 30));
			container.setCursor(Cursor.HAND);
			//按钮
			labelBtn = new Label(container);
			GridData labelBtnGridData = new GridData();
			labelBtnGridData.horizontalAlignment = JWT.CENTER;
			labelBtnGridData.verticalAlignment = JWT.CENTER;
			labelBtnGridData.grabExcessHorizontalSpace = true;
			labelBtnGridData.grabExcessVerticalSpace = true;
			labelBtn.setLayoutData(labelBtnGridData);
			labelBtn.setCursor(Cursor.HAND);
			if(selected){
				container.setBackimage(img_bg_hover);
				labelBtn.setImage(imgSelected);
			}
			else{
				container.setBackimage(img_bg_normal);
				labelBtn.setImage(imgNormal);
			}
			regBtnListener();
		}

		/**
		 * 注册单击事件
		 */
		private void regBtnListener(){
			container.addMouseClickListener(new MouseClick(text));
			labelBtn.addMouseClickListener(new MouseClick(text));
		}

		/**
		 * 重新使外观恢复到默认状态
		 */
		private void reset(){
			container.setBackimage(img_bg_normal);
			labelBtn.setImage(imgNormal);
		}

		/**
		 * 单击事件侦听器
		 */
		private class MouseClick implements MouseClickListener{

			private String text;

			/** 
			 *构造方法
			 *@param text
			 */
			public MouseClick(String text){
				this.text = text;
			}

			public void click(MouseEvent event){
				for(NavigatorButton button : navBtnList){
					button.reset();
				}
				container.setBackimage(img_bg_hover);
				labelBtn.setImage(imgSelected);
				ContactPersonPhoneticNavigatorBar.this.text = text;
				getContext().getParent().broadcastMessage(text);
			}
		}

	}
}
