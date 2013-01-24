package com.spark.psi.base.browser.goods;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.SMessageAlertWindow;
import com.spark.common.components.pages.SMessageConfirmWindow;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.GoodsCommon;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.goods.task.ChangeGoodsCategoryNameTask;
import com.spark.psi.publish.base.goods.task.DeleteGoodsCategoryTask;

public class GoodsCategoryBarPage extends Page {

	boolean isShowEdit = false;
	private Text editText;

	private static GridData gdHeadLabelControl;
	private static GridData gdHeadButtonControl;
	static {
		gdHeadLabelControl = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER);

		gdHeadButtonControl = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gdHeadButtonControl.heightHint = 23;
	}

	public GoodsCategoryBarPage(Composite parent, boolean isShowEdit) {
		super(parent);
		this.isShowEdit = isShowEdit;

		//
		this.getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						GoodsCategoryTree categoryTeee = context
								.find(GoodsCategoryTree.class);
						reloadBar(categoryTeee.getNodeById(message
								.getCategoryId()), false);
					}
				});
//		this.getContext().regMessageListener(CategoryListChangeMsg.class,
//				new MessageListener<CategoryListChangeMsg>() {
//
//					public void onMessage(
//							Situation context,
//							CategoryListChangeMsg message,
//							MessageTransmitter<CategoryListChangeMsg> transmitter) {
//						GoodsCategoryTree categoryTeee = context
//								.find(GoodsCategoryTree.class);
//						
//						CategoryNode node = categoryTeee.getNodeById(
//								message.getCategoryId());
//						reloadBar(node == null ? null : node.getParent(), false);
//					}
//				});
	}

	private void reloadBar(final CategoryNode node, boolean isEdit) {
		this.clear();
		GridLayout gl = new GridLayout();

		List<CategoryNode> nodeParentsList = new ArrayList<CategoryNode>();
		if (node != null) {
			nodeParentsList.add(node);
			CategoryNode parentNaode = node.getParent();
			while (parentNaode != null) {
				nodeParentsList.add(parentNaode);
				parentNaode = parentNaode.getParent();
			}
		}
		gl.numColumns = nodeParentsList.size() * 2 + 3;
		gl.horizontalSpacing = 5;
		this.setLayout(gl);

		final Label rootLabel = new Label(this);
		rootLabel.setForeground(Color.COLOR_BLUE);
		rootLabel.setCursor(Cursor.HAND);
		rootLabel.setText("全部");

		rootLabel.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent arg0) {
				getContext().broadcastMessage(
						new GoodsCategorySelectionMsg(null));
			}
		});

		for (int index = nodeParentsList.size() - 1; index >= 0; index--) {
			final CategoryNode pathNode = nodeParentsList.get(index);

			if (!isShowEdit && index == 0 && pathNode.getChildren().length < 1)
				break;

			Label signLabel = new Label(this);
			signLabel.setText("->");
			signLabel.setForeground(Color.COLOR_BLUE);

			if (isEdit && index == 0) {
				editText = new Text(this, JWT.APPEARANCE3);
				GridData gdText = new GridData();
				gdText.widthHint = 120;
				editText.setLayoutData(gdText);
				editText.setText(node.getName());
				break;
			}

			final Label textLabel = new Label(this);
			textLabel.setText(pathNode.getName());
			textLabel.setForeground(Color.COLOR_BLUE);
			textLabel.setCursor(Cursor.HAND);
			textLabel.addMouseClickListener(new MouseClickListener() {

				public void click(MouseEvent event) {
					getContext().broadcastMessage(
							new GoodsCategorySelectionMsg(pathNode.getId()));
					reloadBar(pathNode, false);
				}
			});

		}

		if (isEdit) {
			Button editButton = new Button(this, JWT.APPEARANCE2);
			editButton.setText(" 保存 ");
			editButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					if (null == editText)
						return;
					if (null == editText.getText()
							|| "".equals(editText.getText().trim())) {
						MessageDialog.alert("分类名称不能为空！");
						return;
					}
					// 验证商品分类名称是否重复
					if(GoodsCommon.isExistCategoryName(getContext(), node.getParent() == null ? null : node.getParent().getId(), editText.getText(), node.getId())) {
						new SMessageAlertWindow(true, "分类名称不能重复！", null);
						return;
					}
					ChangeGoodsCategoryNameTask task = new ChangeGoodsCategoryNameTask(
							node.getId(), editText.getText().trim());
					getContext().handle(task);
					GoodsCategoryTree dataTree = getContext().find(
							GoodsCategoryTree.class);
					CategoryNode updatedNode = dataTree.getNodeById(node
							.getId());
					reloadBar(updatedNode, false);
					getContext().broadcastMessage(
							new GoodsCategorySelectionMsg(node.getId()));
//					reloadBar(node, true);
					getContext().bubbleMessage(new MsgResponse(false, updatedNode.getId()));
				}
			});
		}

		if (isShowEdit && node != null) {
			if (!isEdit) {
				Button editButton = new Button(this, JWT.APPEARANCE2);
				editButton.setText(" 修改 ");
				editButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						if(GoodsCommon.isCategoryContainGoods(getContext(), node.getId())) {
							new SMessageAlertWindow(true, "所选商品分类下已存在商品,不能被修改!", null);
							return;
						}
						reloadBar(node, true);
					}
				});
			}
			Button delButton = new Button(this, JWT.APPEARANCE2);
			delButton.setText(" 删除 ");
			delButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(GoodsCommon.isCategoryContainGoods(getContext(), node.getId())) {
						new SMessageAlertWindow(true, "所选商品分类下已存在商品,不能被删除!", null);
						return;
					}
					new SMessageConfirmWindow("确认删除所选商品分类？", new Runnable(){
						
						public void run(){
							DeleteGoodsCategoryTask delTask = new DeleteGoodsCategoryTask(
									node.getId());
							getContext().handle(delTask);
							reloadBar(node.getParent(), false);
							getContext().broadcastMessage(
									new GoodsCategorySelectionMsg(
											node.getParent() == null ? null : node
													.getParent().getId()));
							getContext().bubbleMessage(new MsgResponse(false, node.getParent() == null ? null : node.getParent().getId()));
						}
					});
//					
				}
			});
		}

		Control[] headerControls = this.getChildren();
		for (Control control : headerControls) {
			if (control instanceof Label) {
				control.setLayoutData(gdHeadLabelControl);
			} else if (control instanceof Button) {
				control.setLayoutData(gdHeadButtonControl);
			}
		}

		this.layout();

	}
}
