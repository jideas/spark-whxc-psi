package com.spark.common.components.text;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.ColorDialog;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Menu;
import com.jiuqi.dna.ui.wt.widgets.MenuItem;
import com.jiuqi.dna.ui.wt.widgets.RichText;
import com.jiuqi.dna.ui.wt.widgets.ToolBar;
import com.jiuqi.dna.ui.wt.widgets.ToolItem;
import com.spark.common.components.CommonImages;

/**
 * 
 * <p>富文本编辑器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-23
 */
public class HtmlEditor extends Composite{

	/**富文本编辑器*/
	private RichText richText;
	/**加粗*/
	private ToolItem boldItem;
	/**斜体*/
	private ToolItem italicItem;
	/**下划线*/
	private ToolItem underlineItem;
	/**字体*/
	private ToolItem fontNameItem;
	/**字号*/
	private ToolItem fontSizeItem;
	/**前景色*/
	private ToolItem foreGroundItem;
	/**背景色*/
	private ToolItem backGroundItem;
	/**段落样式*/
	private ToolItem formatBlockItem;
	/**左对齐*/
	private ToolItem justifyLeftItem;
	/**居中对齐*/
	private ToolItem justifyCenterItem;
	/**右对齐*/
	private ToolItem justifyRightItem;
	/**减少缩进*/
	private ToolItem outdentItem;
	/**增加缩进*/
	private ToolItem indentItem;
	/**项目编号*/
	private ToolItem insertOrderedlistItem;
	/**项目符号*/
	private ToolItem insertUnOrderedlistItem;
	/**水平线*/
	private ToolItem horizontalLineItem;
	/**图片*/
//	private ToolItem imageItem;

	private final static String[] BLOCK_VALUES =
	        {"NORMAL", "<H1>", "<H2>", "<H3>", "<H4>", "<H5>", "<H6>", "<PRE>", "<ADDRESS>"};
	private String[] BLOCK_NAMES = {"正文", "标题1", "标题2", "标题3", "标题4", "标题5", "标题6", "带格式的文本", "地址格式"};

	private final static int[] FONT_SIZES = {1, 2, 3, 4, 5, 6, 7};

	private final static String[] FONT_NAMES = {"宋体", "幼圆", "楷体_GB2312", "仿宋_GB2312", "黑体", "隶书"};

	/**
	 * 构造函数
	 */
	public HtmlEditor(Composite parent){
		super(parent);

		//初始化
		init();
	}

	/**
	 * 初始化
	 */
	private void init(){
		//创建界面组件
		createUIComponent();
		//置空
		this.richText.setContent("");
	}

	/**
	 * 设置编辑器内容
	 * 
	 *@param content 待设置的内容
	 */
	public void setContent(String content){
		this.richText.setContent(content);
	}

	/**
	 * 获取编辑器内容
	 * 
	 * @return 编辑器内容
	 */
	public String getContent(){
		if(this.richText != null){
			return this.richText.getContent();
		}
		return null;
	}
	
	/**
	 * 获取文本内容
	 */
	public String getText(){
		if(getContent() == null){
			return null;
		}
		String text = getContent();
		//去掉标签
		text = text.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
		//去掉空格
		text = text.replaceAll("&nbsp;", " ");
		return text;
	}

	/**
	 * 根据图片名称获得描述符
	 *
	 *@param name 图片文件名称
	 *@return 返回件型图片描述符
	 */
	private ImageDescriptor getImage(String name){
		return CommonImages.getImage("img/htmleditor/" + name);
	}

	/**
	 * 创建界面组件
	 */
	private void createUIComponent(){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		this.setLayout(gridLayout);
		//工具栏
		ToolBar toolBar = new ToolBar(this, JWT.HORIZONTAL | JWT.RIGHT);
		toolBar.setCssClassName(JWT.CSS_SYSTEM_STYLED_NOBACKGROUND);
		toolBar.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//加粗
		createBoldItem(toolBar);
		//斜体
		createItalicItem(toolBar);
		//下划线
		createUnderlineItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//字体
		createFontNameItem(toolBar);
		//字号
		createFontSizeItem(toolBar);
		//前景色
		createForeGroundItem(toolBar);
		//背景色
		createBackGroundItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//段落样式
		createFormatBlockMenu(toolBar);
		//左对齐
		createJustifyLeftItem(toolBar);
		//居中对齐
		createJustifyCenterItem(toolBar);
		//右对齐
		createJustifyRightItem(toolBar);
		//减少缩进
		createOutdentItem(toolBar);
		//增加缩进
		createIndentItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//项目编号
		createInsertOrderedlistItem(toolBar);
		//项目符号
		createInsertUnOrderedlistItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//水平线
		createHorizontalLineItem(toolBar);
		//图片
//		createImageItem(toolBar);
		//富文本
		richText = new RichText(HtmlEditor.this);
		richText.setLayoutData(GridData.INS_FILL_BOTH);
		new ToolItem(toolBar, JWT.SEPARATOR);
	}

	/**
	 *加粗
	 */
	private void createBoldItem(ToolBar toolBar){
		boldItem = new ToolItem(toolBar);
		boldItem.setToolTipText("黑体");
		boldItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('bold');");
			}
		});
		boldItem.setImage(getImage("bold.gif"));
	}

	/**
	 *斜体
	 */
	private void createItalicItem(ToolBar toolBar){
		italicItem = new ToolItem(toolBar);
		italicItem.setToolTipText("斜体");
		italicItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('italic');");
			}
		});
		italicItem.setImage(getImage("italic.gif"));
	}

	/**
	 * 下划线
	 */
	private void createUnderlineItem(ToolBar toolBar){
		underlineItem = new ToolItem(toolBar);
		underlineItem.setToolTipText("下划线");
		underlineItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('underline');");
			}
		});
		underlineItem.setImage(getImage("under.gif"));
	}

	/**
	 * 字体
	 */
	private void createFontNameItem(ToolBar toolBar){
		ActionListener fontnameListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuItem item = (MenuItem)e.widget;
				int index = item.getParentMenu().indexOf(item);
				richText.execScript("execCommand('fontname','" + FONT_NAMES[index] + "');");
			}
		};
		fontNameItem = new ToolItem(toolBar, JWT.DROP_DOWN);
		fontNameItem.setText("字体");
		Menu fontNameMenu = new Menu(toolBar);
		fontNameItem.setMenu(fontNameMenu);
		MenuItem[] fontNameItems = new MenuItem[FONT_NAMES.length];
		for(int i = 0; i < fontNameItems.length; i++){
			fontNameItems[i] = new MenuItem(fontNameMenu, JWT.PUSH);
			fontNameItems[i].setText(FONT_NAMES[i]);
			fontNameItems[i].addActionListener(fontnameListener);
		}
	}

	/**
	 * 字号
	 */
	private void createFontSizeItem(ToolBar toolBar){
		ActionListener fontSizeListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuItem item = (MenuItem)e.widget;
				int index = item.getParentMenu().indexOf(item);
				richText.execScript("execCommand('fontsize'," + FONT_SIZES[index] + ");");
			}
		};
		fontSizeItem = new ToolItem(toolBar, JWT.DROP_DOWN);
		fontSizeItem.setText("字号");
		Menu fontSizeMenu = new Menu(toolBar);
		fontSizeItem.setMenu(fontSizeMenu);
		MenuItem[] fontsizeItems = new MenuItem[FONT_SIZES.length];
		for(int i = 0; i < fontsizeItems.length; i++){
			fontsizeItems[i] = new MenuItem(fontSizeMenu, JWT.PUSH);
			fontsizeItems[i].setText("      " + FONT_SIZES[i] + "      ");
			fontsizeItems[i].addActionListener(fontSizeListener);
		}
	}

	/**
	 * 前景色
	 */
	private void createForeGroundItem(ToolBar toolBar){
		foreGroundItem = new ToolItem(toolBar);
		foreGroundItem.setToolTipText("前景色");
		foreGroundItem.setImage(getImage("fgcolor.gif"));
		foreGroundItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final ColorDialog dialog = new ColorDialog();
				dialog.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						if(dialog.getReturnCode() == JWT.OK && dialog.getColor() != null){
							richText.execScript("execCommand('ForeColor','#"
							        + Integer.toHexString(dialog.getColor().getColor()) + "');");
						}
					}
				});
			}
		});
	}

	/**
	 * 背景色
	 */
	private void createBackGroundItem(ToolBar toolBar){
		backGroundItem = new ToolItem(toolBar);
		backGroundItem.setToolTipText("背景色");
		backGroundItem.setImage(getImage("backcolor.gif"));
		backGroundItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final ColorDialog dialog = new ColorDialog();
				dialog.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						if(dialog.getReturnCode() == JWT.OK && dialog.getColor() != null){
							richText.execScript("execCommand('BackColor','#"
							        + Integer.toHexString(dialog.getColor().getColor()) + "');");
						}
					}
				});
			}
		});
	}

	/**
	 * 段落样式
	 */
	private void createFormatBlockMenu(ToolBar toolBar){
		ActionListener formatBlockListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuItem item = (MenuItem)e.widget;
				int index = item.getParentMenu().indexOf(item);
				richText.execScript("execCommand('formatBlock','" + BLOCK_VALUES[index] + "');");
			}
		};
		formatBlockItem = new ToolItem(toolBar, JWT.DROP_DOWN);
		Menu formatBlockMenu = new Menu(toolBar);
		formatBlockItem.setMenu(formatBlockMenu);
		formatBlockItem.setText("段落样式");
		MenuItem[] formatBlockItems = new MenuItem[BLOCK_NAMES.length];
		for(int i = 0; i < formatBlockItems.length; i++){
			formatBlockItems[i] = new MenuItem(formatBlockMenu, JWT.PUSH);
			formatBlockItems[i].setText(BLOCK_NAMES[i]);
			formatBlockItems[i].addActionListener(formatBlockListener);
		}
	}

	/**
	 * 左对齐
	 */
	private void createJustifyLeftItem(ToolBar toolBar){
		justifyLeftItem = new ToolItem(toolBar);
		justifyLeftItem.setToolTipText("左对齐");
		justifyLeftItem.setImage(getImage("left.gif"));
		justifyLeftItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifyleft');");
			}
		});
	}

	/**
	 * 居中对齐
	 */
	private void createJustifyCenterItem(ToolBar toolBar){
		justifyCenterItem = new ToolItem(toolBar);
		justifyCenterItem.setImage(getImage("center.gif"));
		justifyCenterItem.setToolTipText("居中");
		justifyCenterItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifycenter');");
			}
		});
	}

	/**
	 * 右对齐
	 */
	private void createJustifyRightItem(ToolBar toolBar){
		justifyRightItem = new ToolItem(toolBar);
		justifyRightItem.setToolTipText("右对齐");
		justifyRightItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifyright');");
			}
		});
		justifyRightItem.setImage(getImage("right.gif"));
	}

	/**
	 * 减少缩进
	 */
	private void createOutdentItem(ToolBar toolBar){
		outdentItem = new ToolItem(toolBar);
		outdentItem.setToolTipText("减少缩进");
		outdentItem.setImage(getImage("deindent.gif"));
		outdentItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('outdent');");
			}
		});
	}

	/**
	 * 增加缩进
	 */
	private void createIndentItem(ToolBar toolBar){
		indentItem = new ToolItem(toolBar);
		indentItem.setToolTipText("增加缩进");
		indentItem.setImage(getImage("inindent.gif"));
		indentItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('indent');");
			}
		});
	}

	/**
	 * 项目编号
	 */
	private void createInsertOrderedlistItem(ToolBar toolBar){
		insertOrderedlistItem = new ToolItem(toolBar);
		insertOrderedlistItem.setToolTipText("项目编号");
		insertOrderedlistItem.setImage(getImage("numlist.gif"));
		insertOrderedlistItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('insertorderedlist');");
			}
		});
	}

	/**
	 * 项目符号
	 */
	private void createInsertUnOrderedlistItem(ToolBar toolBar){
		insertUnOrderedlistItem = new ToolItem(toolBar);
		insertUnOrderedlistItem.setToolTipText("项目符号");
		insertUnOrderedlistItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('insertunorderedlist');");
			}
		});
		insertUnOrderedlistItem.setImage(getImage("bullist.gif"));
	}

	/**
	 * 水平线
	 */
	private void createHorizontalLineItem(ToolBar toolBar){
		horizontalLineItem = new ToolItem(toolBar);
		horizontalLineItem.setImage(getImage("hr.gif"));
		horizontalLineItem.setToolTipText("水平线");
		horizontalLineItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('InsertHorizontalRule');");
			}
		});
	}

	/**
	 * 图片
	 */
//	private void createImageItem(ToolBar toolBar){
//		imageItem = new ToolItem(toolBar);
//		imageItem.setToolTipText("图片");
//		imageItem.setImage(getImage("image.gif"));
//		imageItem.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				//边框与图片资源选择对话框
//				ResourceChooserDialog dialog =
//				        new ResourceChooserDialog(getParentWindowHandle(), HtmlEditor.this, Constants.CONST_1);
//				//选择图片返回后处理方法
//				final MessageListenerRegHandle<MsgChooseIPValue> handler =
//				        getContext().regMessageListener(MsgChooseIPValue.class, new MessageListener<MsgChooseIPValue>()
//				        {
//					        public void onMessage(Situation context, MsgChooseIPValue message,
//					                MessageTransmitter<MsgChooseIPValue> transmitter)
//					        {
//						        Resource ipvalue = message.getValue();
//						        ImageDescriptor image = (DataImageDescriptor)ipvalue.getResourceObject();
//						        String url = ServerUrl.getImageServiceUrlPrefix() + image.getImageId();
//						        richText.execScript("execCommand('InsertImage','" + url + "');");
//					        }
//				        });
//				//添加部件初始化和销毁事件监听器
//				dialog.addWidgetListener(new WidgetListener(){
//
//					public void widgetInited(WidgetEvent e){
//					}
//
//					public void widgetDisposed(WidgetEvent e){
//						handler.unRegister();
//					}
//				});
//			}
//		});
//	}
}
