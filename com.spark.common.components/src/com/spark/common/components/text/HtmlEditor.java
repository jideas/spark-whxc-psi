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
 * <p>���ı��༭��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-23
 */
public class HtmlEditor extends Composite{

	/**���ı��༭��*/
	private RichText richText;
	/**�Ӵ�*/
	private ToolItem boldItem;
	/**б��*/
	private ToolItem italicItem;
	/**�»���*/
	private ToolItem underlineItem;
	/**����*/
	private ToolItem fontNameItem;
	/**�ֺ�*/
	private ToolItem fontSizeItem;
	/**ǰ��ɫ*/
	private ToolItem foreGroundItem;
	/**����ɫ*/
	private ToolItem backGroundItem;
	/**������ʽ*/
	private ToolItem formatBlockItem;
	/**�����*/
	private ToolItem justifyLeftItem;
	/**���ж���*/
	private ToolItem justifyCenterItem;
	/**�Ҷ���*/
	private ToolItem justifyRightItem;
	/**��������*/
	private ToolItem outdentItem;
	/**��������*/
	private ToolItem indentItem;
	/**��Ŀ���*/
	private ToolItem insertOrderedlistItem;
	/**��Ŀ����*/
	private ToolItem insertUnOrderedlistItem;
	/**ˮƽ��*/
	private ToolItem horizontalLineItem;
	/**ͼƬ*/
//	private ToolItem imageItem;

	private final static String[] BLOCK_VALUES =
	        {"NORMAL", "<H1>", "<H2>", "<H3>", "<H4>", "<H5>", "<H6>", "<PRE>", "<ADDRESS>"};
	private String[] BLOCK_NAMES = {"����", "����1", "����2", "����3", "����4", "����5", "����6", "����ʽ���ı�", "��ַ��ʽ"};

	private final static int[] FONT_SIZES = {1, 2, 3, 4, 5, 6, 7};

	private final static String[] FONT_NAMES = {"����", "��Բ", "����_GB2312", "����_GB2312", "����", "����"};

	/**
	 * ���캯��
	 */
	public HtmlEditor(Composite parent){
		super(parent);

		//��ʼ��
		init();
	}

	/**
	 * ��ʼ��
	 */
	private void init(){
		//�����������
		createUIComponent();
		//�ÿ�
		this.richText.setContent("");
	}

	/**
	 * ���ñ༭������
	 * 
	 *@param content �����õ�����
	 */
	public void setContent(String content){
		this.richText.setContent(content);
	}

	/**
	 * ��ȡ�༭������
	 * 
	 * @return �༭������
	 */
	public String getContent(){
		if(this.richText != null){
			return this.richText.getContent();
		}
		return null;
	}
	
	/**
	 * ��ȡ�ı�����
	 */
	public String getText(){
		if(getContent() == null){
			return null;
		}
		String text = getContent();
		//ȥ����ǩ
		text = text.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
		//ȥ���ո�
		text = text.replaceAll("&nbsp;", " ");
		return text;
	}

	/**
	 * ����ͼƬ���ƻ��������
	 *
	 *@param name ͼƬ�ļ�����
	 *@return ���ؼ���ͼƬ������
	 */
	private ImageDescriptor getImage(String name){
		return CommonImages.getImage("img/htmleditor/" + name);
	}

	/**
	 * �����������
	 */
	private void createUIComponent(){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		this.setLayout(gridLayout);
		//������
		ToolBar toolBar = new ToolBar(this, JWT.HORIZONTAL | JWT.RIGHT);
		toolBar.setCssClassName(JWT.CSS_SYSTEM_STYLED_NOBACKGROUND);
		toolBar.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�Ӵ�
		createBoldItem(toolBar);
		//б��
		createItalicItem(toolBar);
		//�»���
		createUnderlineItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//����
		createFontNameItem(toolBar);
		//�ֺ�
		createFontSizeItem(toolBar);
		//ǰ��ɫ
		createForeGroundItem(toolBar);
		//����ɫ
		createBackGroundItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//������ʽ
		createFormatBlockMenu(toolBar);
		//�����
		createJustifyLeftItem(toolBar);
		//���ж���
		createJustifyCenterItem(toolBar);
		//�Ҷ���
		createJustifyRightItem(toolBar);
		//��������
		createOutdentItem(toolBar);
		//��������
		createIndentItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//��Ŀ���
		createInsertOrderedlistItem(toolBar);
		//��Ŀ����
		createInsertUnOrderedlistItem(toolBar);
		new ToolItem(toolBar, JWT.SEPARATOR);
		//ˮƽ��
		createHorizontalLineItem(toolBar);
		//ͼƬ
//		createImageItem(toolBar);
		//���ı�
		richText = new RichText(HtmlEditor.this);
		richText.setLayoutData(GridData.INS_FILL_BOTH);
		new ToolItem(toolBar, JWT.SEPARATOR);
	}

	/**
	 *�Ӵ�
	 */
	private void createBoldItem(ToolBar toolBar){
		boldItem = new ToolItem(toolBar);
		boldItem.setToolTipText("����");
		boldItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('bold');");
			}
		});
		boldItem.setImage(getImage("bold.gif"));
	}

	/**
	 *б��
	 */
	private void createItalicItem(ToolBar toolBar){
		italicItem = new ToolItem(toolBar);
		italicItem.setToolTipText("б��");
		italicItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('italic');");
			}
		});
		italicItem.setImage(getImage("italic.gif"));
	}

	/**
	 * �»���
	 */
	private void createUnderlineItem(ToolBar toolBar){
		underlineItem = new ToolItem(toolBar);
		underlineItem.setToolTipText("�»���");
		underlineItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('underline');");
			}
		});
		underlineItem.setImage(getImage("under.gif"));
	}

	/**
	 * ����
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
		fontNameItem.setText("����");
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
	 * �ֺ�
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
		fontSizeItem.setText("�ֺ�");
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
	 * ǰ��ɫ
	 */
	private void createForeGroundItem(ToolBar toolBar){
		foreGroundItem = new ToolItem(toolBar);
		foreGroundItem.setToolTipText("ǰ��ɫ");
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
	 * ����ɫ
	 */
	private void createBackGroundItem(ToolBar toolBar){
		backGroundItem = new ToolItem(toolBar);
		backGroundItem.setToolTipText("����ɫ");
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
	 * ������ʽ
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
		formatBlockItem.setText("������ʽ");
		MenuItem[] formatBlockItems = new MenuItem[BLOCK_NAMES.length];
		for(int i = 0; i < formatBlockItems.length; i++){
			formatBlockItems[i] = new MenuItem(formatBlockMenu, JWT.PUSH);
			formatBlockItems[i].setText(BLOCK_NAMES[i]);
			formatBlockItems[i].addActionListener(formatBlockListener);
		}
	}

	/**
	 * �����
	 */
	private void createJustifyLeftItem(ToolBar toolBar){
		justifyLeftItem = new ToolItem(toolBar);
		justifyLeftItem.setToolTipText("�����");
		justifyLeftItem.setImage(getImage("left.gif"));
		justifyLeftItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifyleft');");
			}
		});
	}

	/**
	 * ���ж���
	 */
	private void createJustifyCenterItem(ToolBar toolBar){
		justifyCenterItem = new ToolItem(toolBar);
		justifyCenterItem.setImage(getImage("center.gif"));
		justifyCenterItem.setToolTipText("����");
		justifyCenterItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifycenter');");
			}
		});
	}

	/**
	 * �Ҷ���
	 */
	private void createJustifyRightItem(ToolBar toolBar){
		justifyRightItem = new ToolItem(toolBar);
		justifyRightItem.setToolTipText("�Ҷ���");
		justifyRightItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('justifyright');");
			}
		});
		justifyRightItem.setImage(getImage("right.gif"));
	}

	/**
	 * ��������
	 */
	private void createOutdentItem(ToolBar toolBar){
		outdentItem = new ToolItem(toolBar);
		outdentItem.setToolTipText("��������");
		outdentItem.setImage(getImage("deindent.gif"));
		outdentItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('outdent');");
			}
		});
	}

	/**
	 * ��������
	 */
	private void createIndentItem(ToolBar toolBar){
		indentItem = new ToolItem(toolBar);
		indentItem.setToolTipText("��������");
		indentItem.setImage(getImage("inindent.gif"));
		indentItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('indent');");
			}
		});
	}

	/**
	 * ��Ŀ���
	 */
	private void createInsertOrderedlistItem(ToolBar toolBar){
		insertOrderedlistItem = new ToolItem(toolBar);
		insertOrderedlistItem.setToolTipText("��Ŀ���");
		insertOrderedlistItem.setImage(getImage("numlist.gif"));
		insertOrderedlistItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('insertorderedlist');");
			}
		});
	}

	/**
	 * ��Ŀ����
	 */
	private void createInsertUnOrderedlistItem(ToolBar toolBar){
		insertUnOrderedlistItem = new ToolItem(toolBar);
		insertUnOrderedlistItem.setToolTipText("��Ŀ����");
		insertUnOrderedlistItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('insertunorderedlist');");
			}
		});
		insertUnOrderedlistItem.setImage(getImage("bullist.gif"));
	}

	/**
	 * ˮƽ��
	 */
	private void createHorizontalLineItem(ToolBar toolBar){
		horizontalLineItem = new ToolItem(toolBar);
		horizontalLineItem.setImage(getImage("hr.gif"));
		horizontalLineItem.setToolTipText("ˮƽ��");
		horizontalLineItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				richText.execScript("execCommand('InsertHorizontalRule');");
			}
		});
	}

	/**
	 * ͼƬ
	 */
//	private void createImageItem(ToolBar toolBar){
//		imageItem = new ToolItem(toolBar);
//		imageItem.setToolTipText("ͼƬ");
//		imageItem.setImage(getImage("image.gif"));
//		imageItem.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				//�߿���ͼƬ��Դѡ��Ի���
//				ResourceChooserDialog dialog =
//				        new ResourceChooserDialog(getParentWindowHandle(), HtmlEditor.this, Constants.CONST_1);
//				//ѡ��ͼƬ���غ�����
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
//				//��Ӳ�����ʼ���������¼�������
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
