package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.BrowserActionEvent;
import com.jiuqi.dna.ui.wt.events.BrowserActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ImageBorderComposite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.SMessageAlertWindow;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.GoodsCommon;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.base.browser.mdcommon.CategoryListChangeMsg;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.goods.task.CreateGoodsCategoryTask;

public class GoodsCategoryListPage extends Page {

	//
	final static ImageBorder imageBorder = new ImageBorder(
			new ImageDescriptor[] {
					BaseImages.getImage("images/goods/window2_Lists_lt.png"),
					BaseImages.getImage("images/goods/window2_Lists_t.png"),
					BaseImages.getImage("images/goods/window2_Lists_rt.png"),
					BaseImages.getImage("images/goods/window2_Lists_r.png"),
					BaseImages.getImage("images/goods/window2_Lists_rb.png"),
					BaseImages.getImage("images/goods/window2_Lists_b.png"),
					BaseImages.getImage("images/goods/window2_Lists_lb.png"),
					BaseImages.getImage("images/goods/window2_Lists_l.png") });

	//
	private ImageBorderComposite imageBorderComposite; 
	private Composite headerArea;
	private Browser browser;

	private Text nameText = null;
	private Text codeText = null;

	//
	private GUID targetNodeId;

	//
	private int columnCount = 2;
	private static final int workCount = 6;
	private static final int MAX_CATEGORYNO_LENGTH = 2;
	private boolean canAdd;

	public GoodsCategoryListPage(Composite parent, final boolean canAdd) {
		super(parent);
		this.canAdd = canAdd;

		//
		this.setLayout(new FillLayout());
		imageBorderComposite = new ImageBorderComposite(this);
		imageBorderComposite.setImageBorder(imageBorder);
		imageBorderComposite.setLayout(new GridLayout());

		//
		this.getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						GoodsCategoryTree categoryTeee = context
								.find(GoodsCategoryTree.class);
						targetNodeId = message.getCategoryId();
						CategoryNode targetNode = null;
						if (targetNodeId != null) {
							targetNode = categoryTeee.getNodeById(targetNodeId);
						}
						if (targetNode == null) {
							reloadContent(categoryTeee.getRootNodes());
						} else if ((!GoodsCategoryListPage.this.canAdd && targetNode
								.getChildren().length > 0)
								|| canAdd) {
							reloadContent(targetNode.getChildren());
						}
					}
				});
		this.getContext().regMessageListener(CategoryListChangeMsg.class,
				new MessageListener<CategoryListChangeMsg>() {

					public void onMessage(
							Situation context,
							CategoryListChangeMsg message,
							MessageTransmitter<CategoryListChangeMsg> transmitter) {
						GoodsCategoryTree categoryTeee = context
								.find(GoodsCategoryTree.class);
						reloadContent(categoryTeee.getRootNodes());
						getContext().broadcastMessage(
								new GoodsCategorySelectionMsg(null));
						
//						targetNodeId = message.getCategoryId();
//						CategoryNode targetNode = null;
//						if (targetNodeId != null) {
//							targetNode = categoryTeee.getNodeById(targetNodeId);
//						}
//						if (targetNode == null) {
//							reloadContent(categoryTeee.getRootNodes());
//							getContext().broadcastMessage(
//									new GoodsCategorySelectionMsg(null));
//						} 
////						else if ((!GoodsCategoryListPage.this.canAdd && targetNode
////								.getChildren().length > 0)
////								|| canAdd) {
////							reloadContent(targetNode.getChildren());
////						}
//						else {
//							reloadContent(targetNode.getChildren());
//							getContext().broadcastMessage(
//									new GoodsCategorySelectionMsg(targetNodeId));
//						}
					}
				});
	}

	private void reloadContent(CategoryNode[] showNodes) {
		if (canAdd) {
			showAddControl();
		}
		if (null != browser)
			browser.dispose();
		if (null == showNodes)
			return;
		browser = new Browser(imageBorderComposite);
		browser.setLayoutData(GridData.INS_FILL_BOTH);
		browser.setHandlerName("nodeSelecting");
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head>");
		buffer
				.append("<style type=\"text/css\">.selected{color: '#803AC2'; font-weight: 'bold';} .unSelected, a{text-decoration: none; color: blue;}</style>");
		buffer.append("<script language=\"javascript\">");
		buffer.append("var lastSelectId;");
		buffer.append("function nodeClick(id) { nodeSelecting(id);");
		buffer.append("	var selectedElement = document.getElementById(id);");
		buffer.append("	if(selectedElement != null) {");
		buffer.append("		selectedElement.className = \"selected\";");
		buffer.append("	}");
		buffer.append("	if(lastSelectId != null) {");
		buffer
				.append("		var lastElement = document.getElementById(lastSelectId);");
		buffer.append("		lastElement.className = \"unSelected\";");
		buffer.append("	}");
		buffer.append("	lastSelectId = id;");
		buffer.append("}");
		buffer.append("</script>");
		buffer.append("</head><body style='color: white' leftmargin=0 rightmargin=0 topmargin='10' unselectable=\"on\" onselectstart=\"return false\" style=\"-moz-user-select:none;\" oncontextmenu=self.event.returnValue=false ><table style='width:240px'>");

		for (int rowIndex = 0; rowIndex < getRowCount(showNodes.length); rowIndex++) {
			buffer.append("<tr><td width='10px'></td>");
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				int nodeIndex = rowIndex * columnCount + columnIndex;			
				if (nodeIndex < showNodes.length) {
					String nodeName = showNodes[nodeIndex].getName();
					nodeName = nodeName.length()>workCount ? nodeName.substring(0,workCount)+"..." : nodeName;
					// buffer.append("<td><a style='{text-decoration: none;}' href=\"javascript:nodeSelecting('"
					// buffer.append("<td><a id='" +
					// showNodes[nodeIndex].getId().toString() +
					// "' href=\"javascript:nodeSelecting('"
					buffer.append("<td height='22px'><a title='" + showNodes[nodeIndex].getName() + "' id='"
							+ showNodes[nodeIndex].getId().toString()
							+ "' href=\"javascript:nodeClick('"
							+ showNodes[nodeIndex].getId().toString()
							+ "');\"><font size='2'>"
							+ nodeName
							+ "</font></a></td><td width='10px'></td>");
				}
			}
			buffer.append("</tr>");
		}
		buffer.append("</table></body></html>");
		browser.applyHTML(buffer.toString());
		browser.addActionListener(new BrowserActionListener() {
			public void actionPerformed(BrowserActionEvent e) {
				getContext().broadcastMessage(
						new GoodsCategorySelectionMsg(GUID
								.tryValueOf(e.actionValue)));
			}
		});
		this.layout();
	}

	/**
	 * ��ʾ��������ؼ�
	 */
	public void showAddControl() {
		if (null == headerArea) {
			headerArea = new Composite(imageBorderComposite);
		} else {
			headerArea.clear();
		}
		GridLayout gdHeader = new GridLayout();
		gdHeader.numColumns = 3;
		gdHeader.marginTop = 3;
		gdHeader.horizontalSpacing = 0;
		headerArea.setLayout(gdHeader);
		headerArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(headerArea).setText("��ţ�");
		Composite codeArea = new Composite(headerArea);
		GridData gdCodeArea = new GridData(GridData.FILL_BOTH);
		gdCodeArea.horizontalSpan = 2;
		gdCodeArea.horizontalIndent = -3;
		GridLayout glCodeArea = new GridLayout();
		glCodeArea.numColumns = 2;
		glCodeArea.marginLeft = 0;
		glCodeArea.horizontalSpacing = 3;
		
		codeArea.setLayout(glCodeArea);
		codeArea.setLayoutData(gdCodeArea);
		
		final Label parentCodeLabel = new Label(codeArea);
		codeText = new Text(codeArea, JWT.APPEARANCE3);
		GridData gdCode = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gdCode.widthHint = 40;
		codeText.setRegExp(TextRegexp.NUMBER);
		codeText.setMaximumLength(MAX_CATEGORYNO_LENGTH);
		codeText.setLayoutData(gdCode);
		
		new Label(headerArea).setText("���ƣ�");
		nameText = new Text(headerArea, JWT.APPEARANCE3);
		nameText.setLayoutData(new GridData(GridData.FILL_BOTH));
//		nameText.setRegExp(TextRegexp.ENGLISH_AND_NUMBER);
//		codeText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
//			"PSIBase.GoodsCategoryList.handleAddButtonKeyDown");
		String clientId = nameText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
				"PSIBase.GoodsCategoryList.handleAddButtonKeyDown");
		
		
		Button addButton = new Button(headerArea, JWT.APPEARANCE2);
		GridData gdButton = new GridData();
		gdButton.widthHint = 100;
		addButton.setText(" ���� ");
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				handleAddCategory(nameText.getText());
			}
		});

		addButton.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				handleAddCategory(nameText.getText());
			}
		});
		if(targetNodeId != null) { 
			GoodsCategoryTree categoryTeee = getContext().find(GoodsCategoryTree.class);
			CategoryNode node = categoryTeee.getNodeById(targetNodeId);
			if (node != null) {
				// ��ʾ��ǰ��ı��
				parentCodeLabel.setText(node.getCategoryNo());
				GridData gdParentCode = new GridData();
				gdParentCode.widthHint = node.getCategoryNo().length() * 10;
				parentCodeLabel.setLayoutData(gdParentCode);
				
				// Ҷ�ӷ��������������Ʒ�������ٽ�����
				if((node.getChildren() == null || node.getChildren().length < 1)
						&& GoodsCommon.isCategoryContainGoods(getContext(), targetNodeId)) {
					addButton.setEnabled(false);
					nameText.removeClientEventHandler(clientId);
				}
			}
			
		}
		this.layout();
	}

	private void handleAddCategory(String categoryName) {
		// ������������
		if (null == categoryName || "".equals(categoryName.trim())) {
			new SMessageAlertWindow(true, "�������Ʋ���Ϊ�գ�", null);
			return;
		}
		// ��֤��Ʒ���������Ƿ��ظ�
		if(GoodsCommon.isExistCategoryName(getContext(), targetNodeId, categoryName)) {
			new SMessageAlertWindow(true, "�������Ʋ����ظ���", null);
			return;
		}
		// ��֤��Ʒ������
		String categoryNo = codeText.getText();
		if (StringUtils.isEmpty(categoryNo)) {
			new SMessageAlertWindow(true, " ��Ų���Ϊ�գ�", null);
			return;
		}
		if (null != targetNodeId) {
			GoodsCategoryTree categoryTeee = getContext().find(GoodsCategoryTree.class);
			CategoryNode node = categoryTeee.getNodeById(targetNodeId);
			categoryNo = node.getCategoryNo() + categoryNo;
		}
		if (GoodsCommon.isExistCategoryCode(getContext(), targetNodeId, categoryNo.trim())) {
			new SMessageAlertWindow(true, " ��Ų����ظ���", null);
			return;
		}
		
		
		CreateGoodsCategoryTask task = new CreateGoodsCategoryTask(
				categoryName.trim(), categoryNo.trim(), targetNodeId);
		try{
	        getContext().handle(task);
        }
        catch(Throwable e){
        	new SMessageAlertWindow(true, "����ʧ�ܣ�\n"+e.getMessage(),null);
        	return ;
        }
		getContext().broadcastMessage(
				new GoodsCategorySelectionMsg(targetNodeId));
		getContext().bubbleMessage(new MsgResponse(false, targetNodeId));
		nameText.forceFocus();
	}

	/**
	 * ����������
	 * 
	 * @param nodeCount
	 * @return
	 */
	private int getRowCount(int nodeCount) {
		if (0 == nodeCount)
			return 0;
		if (nodeCount % columnCount == 0) {
			return nodeCount / columnCount;
		} else {
			return nodeCount / columnCount + 1;
		}
	}
}
