package com.spark.psi.mainpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.spark.psi.mainpage.utils.SMessageShowUtil;
import com.spark.psi.publish.smessage.entity.SMessageItem;
import com.spark.psi.publish.smessage.entity.SMessageTypeItem;
import com.spark.psi.publish.smessage.task.SMessageBuddleTask;

public class MessageTipMain extends Page {

	private Composite main_composite;
	private Map<String,Composite> contentMap = new HashMap<String, Composite>();
	private Map<String,List<Label>> nameLabelMap = new HashMap<String, List<Label>>();
	//当前展开项
	private String thisExpansion = null;			
	
	
	public MessageTipMain(Composite parent) {
		super(parent);
		doInit();
	}

	private void doInit() {
		//初始化菜单，以及每个菜单的设置与数量
		this.addClientNotifyListener(new ClientNotifyListener() {
			
			public void notified(ClientNotifyEvent e) {
				JSONObject open = MessageTipMain.this.getCustomObject("open");
				if(null != open) {
					try {
						String typeStr = open.getString("type");
						thisExpansion = null;
						if(null != typeStr && typeStr.toString() != "null") {
							thisExpansion = typeStr;
						}
						setPage();
						initControls();
					} catch (JSONException e1) {
						e1.printStackTrace();
					} finally {
						MessageTipMain.this.setCustomObject("open",null);
					}
				}
			}
		});
		
		this.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY,
		"MainPageDna.MessageNotify");
	}

	private void setPage() {
		this.clear();
		this.setLayout(new FillLayout());
		this.layout();
		
		main_composite = Scroll.setScroll(this);
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		gridLayout.marginBottom = gridLayout.marginLeft = gridLayout.marginTop = gridLayout.marginRight =0;
//		gridLayout.marginRight = 10;
		main_composite.setLayout(gridLayout);
		
		GridData layoutData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_FILL|
				GridData.VERTICAL_ALIGN_FILL|GridData.GRAB_VERTICAL);
		main_composite.setLayoutData(layoutData);
		main_composite.setBackground(new Color(0x252f47));
		main_composite.layout();
	}
	
	private void initControls() {
		boolean isFirst = true;
		List<SMessageTypeItem> mainList = getContext().getList(SMessageTypeItem.class);
		for (final SMessageTypeItem sMessageTypeItem : mainList) {
			final Composite composite_content = new Composite(main_composite);
			GridData layoutData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_BEGINNING);
			composite_content.setLayoutData(layoutData);
			GridLayout gridLayout = new GridLayout();
			gridLayout.verticalSpacing = 0;
			composite_content.setLayout(gridLayout);
			composite_content.layout();
			
			Composite composite = new Composite(composite_content);
			composite.setLayout(new GridLayout(3));
			composite.layout();
			
			layoutData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_CENTER);
			layoutData.heightHint = 32;
			composite.setLayoutData(layoutData);
			
			composite.setBackimage(ImageUtil.crateImage("inf_category_bg_n.png"));
			
			Composite composite2 = new Composite(composite);
			layoutData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_FILL|GridData.GRAB_VERTICAL);
			composite2.setLayoutData(layoutData);
			GridLayout layout = new GridLayout(2);
			layout.marginLeft = 8;
			composite2.setLayout(layout);
			composite2.layout();
			
			List<Label> labelList = new ArrayList<Label>();
			Label label = new Label(composite2);
			label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER|GridData.GRAB_VERTICAL));
			label.setImage(ImageUtil.crateImage(sMessageTypeItem.getCode()+"_n.png"));
			labelList.add(label);
			
			Label label1 = new Label(composite2);
			GridData gridData = new GridData(GridData.VERTICAL_ALIGN_CENTER|GridData.GRAB_VERTICAL);
			gridData.verticalIndent = 3;
			label1.setLayoutData(gridData);
			label1.setText(sMessageTypeItem.getTitle()+"（"+getContext().find(Integer.class,sMessageTypeItem.getType())+"）");
			setPrefixLabelDataDefault(label1);
			labelList.add(label1);
			
			Composite composite3 = new Composite(composite);
			layoutData = new GridData();
			composite3.setLayoutData(layoutData);
			composite3.setLayout(new GridLayout());
			composite3.layout();
			
			final Label label2 = new Label(composite3);
			if(sMessageTypeItem.isBuddling()) {
				label2.setImage(ImageUtil.crateImage("inf_category_n_open.png"));
				label2.setToolTipText("关闭浮出提示");
			} else {
				label2.setImage(ImageUtil.crateImage("inf_category_n_close.png"));
				label2.setToolTipText("开启浮出提示");
			}
			label2.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent arg0) {
					boolean isFloatTip = sMessageTypeItem.isBuddling();
					if(sMessageTypeItem.getCode().equals(thisExpansion)) {
						if(!isFloatTip) {
							label2.setImage(ImageUtil.crateImage("inf_category_h_open.png"));
							label2.setToolTipText("关闭浮出提示");
						} else {
							label2.setImage(ImageUtil.crateImage("inf_category_h_close.png"));
							label2.setToolTipText("开启浮出提示");
						}
					} else {
						if(!isFloatTip) {
							label2.setImage(ImageUtil.crateImage("inf_category_n_open.png"));
							label2.setToolTipText("关闭浮出提示");
						} else {
							label2.setImage(ImageUtil.crateImage("inf_category_n_close.png"));
							label2.setToolTipText("开启浮出提示");
						}
					}
					SMessageBuddleTask buddleTask = new SMessageBuddleTask(sMessageTypeItem.getType(), !isFloatTip);
					getContext().handle(buddleTask);
					sMessageTypeItem.setBuddling(!isFloatTip);
				}
			});
			label2.setData(sMessageTypeItem);
			labelList.add(label2);
			
			//展开按钮
			Composite composite5 = new Composite(composite);
			layout = new GridLayout();
			layout.marginRight = 6;
			composite5.setLayout(layout);
			composite5.layout();
			
			final Label label3 = new Label(composite5);
			label3.setImage(ImageUtil.crateImage("inf_category_n_minus.png"));	
			label3.setToolTipText("展开");
			
			label3.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent arg0) {
					Object o = label3.getData();
					changeData(label3, o , sMessageTypeItem.getCode(), sMessageTypeItem.isBuddling(), composite_content,sMessageTypeItem);
				}
			});
			labelList.add(label3);
			
			label.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent arg0) {
					Object o = label3.getData();
					changeData(label3, o , sMessageTypeItem.getCode(),  sMessageTypeItem.isBuddling(), composite_content,sMessageTypeItem);
				}
			});
			label1.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent arg0) {
					Object o = label3.getData();
					changeData(label3, o , sMessageTypeItem.getCode(),  sMessageTypeItem.isBuddling(), composite_content,sMessageTypeItem);
				}
			});
			nameLabelMap.put(sMessageTypeItem.getCode(), labelList);
			if(isFirst) {
				if(thisExpansion == null || thisExpansion.equals(sMessageTypeItem.getCode())) {
					changeData(label3, null , sMessageTypeItem.getCode(), sMessageTypeItem.isBuddling(), composite_content,sMessageTypeItem);
					isFirst = false;
				}
			}
		}
	}
	
	/**
	 * 设置字体颜色
	 * @param label
	 */
	private static void setPrefixLabelData(Label label) {
		label.setForeground(new Color(0xF7F7F7));
	}
	
	/**
	 * 设置字体颜色
	 * @param label
	 */
	private static void setPrefixLabelDataDefault(Label label) {
		Font font = new Font();
		font.setSize(10);
		label.setFont(font);
		label.setForeground(new Color(0x7386A8));
	}
	
	private void changeData(final Label label3, Object o, String messageTipType, boolean isFloat, Composite compositeContent,SMessageTypeItem sMessageTypeItem) {
		if(o == null || o.equals("true")) {
			if(thisExpansion != null) {
				away();
			}
			thisExpansion = messageTipType;
			expansion(messageTipType,compositeContent,isFloat,sMessageTypeItem);
		} else {
			away();
			label3.setData("true");
			label3.setImage(ImageUtil.crateImage("inf_category_n_minus.png"));
			label3.setToolTipText("展开");
			thisExpansion = null;
		}
	}

	/**
	 * 收起
	 * @param parent 
	 */
	private void away() {
		Composite composite = contentMap.get(thisExpansion);
		if(composite != null) {
			composite.dispose();
			contentMap.remove(thisExpansion);
		}
		List<Label> labels = nameLabelMap.get(thisExpansion);
		if(labels == null) {
			return;
		}
		setPrefixLabelDataDefault(labels.get(1));
		labels.get(0).setImage(ImageUtil.crateImage(thisExpansion+"_n.png"));
		Composite parentCmp = labels.get(0).getParent().getParent();
		parentCmp.setBackimage(ImageUtil.crateImage("inf_category_bg_n.png"));
		
		Label label = labels.get(3);
		label.setData(null);
		label.setImage(ImageUtil.crateImage("inf_category_n_minus.png"));
		
		Label label2 = labels.get(2);
		SMessageTypeItem sMessageTypeItem = (SMessageTypeItem) label2.getData();
		if(sMessageTypeItem.isBuddling()) {
			label2.setImage(ImageUtil.crateImage("inf_category_n_open.png"));
		} else {
			label2.setImage(ImageUtil.crateImage("inf_category_n_close.png"));
		}
		main_composite.layout();
	}
	
	/**
	 * 展开
	 * @param messageTipType
	 * @param parent 
	 * @param parent
	 * @param isFloat 
	 */
	private void expansion(String messageTipType, Composite parent, boolean isFloat,SMessageTypeItem sMessageTypeItem) {
		Composite composite = contentMap.get(messageTipType);
		if(composite != null && !composite.isDisposed()) {
			composite.setVisible(true);
		} else {
			composite =	new Composite(parent);
			GridData layoutData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_FILL|GridData.GRAB_VERTICAL);
			composite.setLayoutData(layoutData);
			GridLayout gridLayout = new GridLayout();
			gridLayout.marginTop = 12;
			gridLayout.marginBottom = 10;
			composite.setLayout(gridLayout);
			composite.layout();
			
			composite.setBackground(new Color(0x252F47));
			
			int newCount = fillData(sMessageTypeItem, composite);
			
			contentMap.put(messageTipType, composite);
			
			List<Label> labels = nameLabelMap.get(thisExpansion);
			
			Composite parentCmp = labels.get(0).getParent().getParent();
			parentCmp.setBackimage(ImageUtil.crateImage("inf_category_bg_h.png"));
			
			//展开按钮
			Label label3 = labels.get(3);
			label3.setData("false");
			label3.setImage(ImageUtil.crateImage("inf_category_h_plus.png"));
			label3.setToolTipText("收起");
			
			//提示按钮
			Label label2 = labels.get(2);
			if(isFloat) {
				label2.setImage(ImageUtil.crateImage("inf_category_h_open.png"));
			} else {
				label2.setImage(ImageUtil.crateImage("inf_category_h_close.png"));
			}
			//图标
			labels.get(0).setImage(ImageUtil.crateImage(thisExpansion+"_h.png"));
			Label label = labels.get(1);
			String name = label.getText();
			name = getNewCount(name,newCount);
			label.setText(name);
			setPrefixLabelData(label);
		}
		main_composite.layout();
		main_composite.getParent().layout();
		this.layout();
	}

	private String getNewCount(String name, int newCount) {
		return name.replaceAll("\\（[0-9]{0,6}\\）", "（"+newCount+"）");
	}
	
	private int fillData(SMessageTypeItem sMessageTypeItem, Composite composite) {
		List<SMessageItem> messageItems = getContext().getList(SMessageItem.class,sMessageTypeItem.getType());
		int size = messageItems.size();
		if(size == 0) {
			GridLayout gridLayout = new GridLayout(4);
			gridLayout.marginLeft = 16;
			Composite composite1 = new Composite(composite);
			
			GridData gd1 = new GridData();
			gd1.grabExcessHorizontalSpace = true;
			gd1.horizontalAlignment = JWT.FILL;
			gd1.heightHint = 20;
			gd1.horizontalIndent = 11;
			composite1.setLayoutData(gd1);
			composite1.setLayout(gridLayout);
			
			Label label = new Label(composite1);
			label.setText("当前分类下，记录数为 0。");
			label.setForeground(new Color(0xc6c6c6));
		} else {
			for(SMessageItem item : messageItems) {
				SMessageShowUtil.show(composite, item);
			}
		}
		return size;
	}

}