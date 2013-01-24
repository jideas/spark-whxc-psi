package com.spark.psi.base.browser.material;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.spark.common.components.CommonImages;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;

public class MaterialCategoryTreePage extends Page {
	private Composite contentArea;
	private Composite footerArea;
//	private Text categoryCodeText;
	
	public MaterialCategoryTreePage(Composite parent, CategoryNode node) {
		this(parent, node, true);
	}
	
	public MaterialCategoryTreePage(Composite parent, CategoryNode node, boolean hasFooter) {
		super(parent);
		parent.setLayout(new FillLayout());
		GridLayout glThis = new GridLayout();
		glThis.verticalSpacing = 0;
		this.setLayout(glThis);

		if (null != node) {
			GridLayout glHeader = new GridLayout();
			glHeader.numColumns = 4;
			GridData gdHeader = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gdHeader.heightHint = 25;
			
			Composite headerArea = new Composite(this);
			headerArea.setLayoutData(gdHeader);
			headerArea.setLayout(glHeader);
			
//			GoodsCategoryInfo categoryInfo = getContext().find(GoodsCategoryInfo.class, node.getId());

			new Label(headerArea).setText("分类名称：" + node.getName());
			new Label(headerArea).setText("    ");
			new Label(headerArea).setText("分类编号：" + node.getCategoryNo());
//			categoryCodeText = new Text(headerArea, JWT.APPEARANCE3);
//			categoryCodeText.setText(categoryInfo.);
//			amountLimitText.setText(categoryInfo.getInventoryUpperLimit() > 0 ? DoubleUtil.getRoundStr(categoryInfo.getInventoryUpperLimit()) : null);
			GridData gdText = new GridData();
			gdText.widthHint = 120;
//			categoryCodeText.setLayoutData(gdText);
			headerArea.layout();
			
		}
		contentArea = new Composite(this);
		contentArea.setLayout(new FillLayout());
		contentArea.setLayoutData(GridData.INS_FILL_BOTH);
		contentArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));

		if(hasFooter){
			footerArea = new Composite(this);
			GridData gdFooter = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gdFooter.heightHint = 33;
			footerArea.setLayout(new GridLayout());
			footerArea.setLayoutData(gdFooter);
			footerArea.setBackimage(CommonImages
					.getImage("img/page/MTabsarea_bottom.png"));
			footerArea.layout();
		}

		showContent(node);

		contentArea.layout();
		this.layout();
	}

	private void showContent(final CategoryNode node) {
		MaterialCategoryShowTree tree = new MaterialCategoryShowTree(contentArea);
		tree.setInputNodeId(node == null ? null : node.getId());

//		if (node != null) {
//			Button saveButton = new Button(footerArea, JWT.APPEARANCE3);
//			saveButton.setText(" 保 存 ");
//
//			GridData gdButton = new GridData(GridData.GRAB_VERTICAL
//					| GridData.VERTICAL_ALIGN_END | GridData.GRAB_HORIZONTAL
//					| GridData.HORIZONTAL_ALIGN_END);
//			saveButton.setLayoutData(gdButton);
//			saveButton.addActionListener(new ActionListener() {
//				
//				public void actionPerformed(ActionEvent e) {
////					if(null == categoryCodeText) return;
////					String amountStr = categoryCodeText.getText();
////					if(amountStr == null || "".equals(amountStr.trim())) {
//////						MessageDialog.alert("库存金额上限来能为空！");
//////						return;
////						amountStr = "0";
////					}
//					MaterialsCategoryInfo categoryInfo = getContext().find(MaterialsCategoryInfo.class, node.getId());
//					UpdateMaterialsCategoryTask task = new UpdateMaterialsCategoryTask();
//					task.setScale(categoryInfo.getScale());
//					task.setId(node.getId());
//					
////					task.setCategoryCode(categoryCodeText.getText());
//					task.setName(categoryInfo.getName());
//					task.setCategoryNo(node.getCategoryNo());
//					task.setPropertyDefines(categoryInfo.getPropertyDefines());
//					try {
//						getContext().handle(task);
//						
//						getContext().broadcastMessage(
//								new MaterialCategorySelectionMsg(categoryInfo.getId()));
//						new SMessageAlertWindow(false, "保存成功。", null);
//					} catch (Exception exception) {
//						exception.printStackTrace();
//						new SMessageAlertWindow(true, "保存失败!", null);
//					}
//				}
//			});
//		}
	}

}
