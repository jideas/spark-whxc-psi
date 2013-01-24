package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

public class BigTree extends Composite{
	
	/**
	 * ͼƬĿ¼
	 */
	private final static String PATH = "images/bigtree/";	
	
	/**
	 * �����ߵĿ��
	 */
	private final static int LINE_WIDTH = 30;//grop_line_cline.png	
	/**
	 * �����ߵĸ߶�
	 */
	private final static int LINE_HEIGHT = 32;
	
//	/**
//	 * �ڵ�Ŀ��
//	 */
//	private final static int NODE_WIDTH = 145;	
//	/**
//	 * �ڵ�ĸ߶�
//	 */
//	private final static int NODE_HEIGHT = 32;
	
	/**
	 * �����ߵĲ��ֲ���
	 */
	private final static GridData LINE_GRIDDATA = new GridData(LINE_WIDTH, LINE_HEIGHT);
	
	/**
	 * �ڵ�Ĳ��ֲ���
	 */
	private final static GridData NODE_GRIDDATA = new GridData();
	
	/**
	 * ����������
	 */
	private final static ImageDescriptor TOP_LINE_IMG = BaseImages.getImage(PATH + "grop_line_top.png");
	//	|---
	//	|
	/**
	 * �м��������
	 */
	private final static ImageDescriptor CENTER_LINE_IMG = BaseImages.getImage(PATH + "grop_line_center.png");
	//	|
	//	|---
	//	|
	/**
	 * �ײ�������
	 */
	private final static ImageDescriptor BOTTOM_LINE_IMG = BaseImages.getImage(PATH + "grop_line_bottom.png");
	//	|
	//	|---
	
	/**
	 * ��ֱ������
	 */
	private final static ImageDescriptor VERTICAL_LINE_IMG = BaseImages.getImage(PATH + "grop_line_Lline.png");
	//	|
	/**
	 * ����������
	 */
	private final static ImageDescriptor HORIZONTAL_LINE_IMG = BaseImages.getImage(PATH + "grop_line_cline.png");
	//	---
	
	/**
	 * �ڵ����ͼƬ
	 */
	private final static ImageDescriptor NODE_LFET_IMG = BaseImages.getImage(PATH + "grop_btn2_left.png");//20*32
	/**
	 * �ڵ��м�ͼƬ
	 */
	private final static ImageDescriptor NODE_CENTER_IMG = BaseImages.getImage(PATH + "grop_btn2_center.png");//1*32
	/**
	 * �ڵ��ұ�ͼƬ
	 */
	private final static ImageDescriptor NODE_RIGHT_IMG = BaseImages.getImage(PATH + "grop_btn2_right.png");//20*32
	
	/**
	 * ��˾�����ϲ�ͼƬ
	 */
	private final static ImageDescriptor ROOT_TOP_IMG = BaseImages.getImage(PATH + "grop_btn_top.png");//56*30
	/**
	 * ��˾�����м�ͼƬ
	 */
	private final static ImageDescriptor ROOT_MIDDLE_IMG = BaseImages.getImage(PATH + "grop_btn_center.png");//56*600
	/**
	 * ��˾�����±�ͼƬ
	 */
	private final static ImageDescriptor ROOT_BOTTOM_IMG = BaseImages.getImage(PATH + "grop_btn_bottom.png");//56*30
	
	/**
	 * ��ǩ��ʾ����
	 */
	private final static Font TEXT_FONT = new Font(11, JWT.FONT_STYLE_NAME_BOLD, JWT.FONT_STYLE_BOLD);
	
	/**
	 * ���ڵ�ĸ��ڵ��������
	 */
	private DepartmentNode ROOT;
	
	private Composite mainCmp;
	
	/**
	 * <p>������ö��</p>
	 * ��Ϊ��һöҶ�ӽڵ㣨���������м�Ҷ�ӽڵ㣨�м����򣩣����һöҶ�ӽڵ㣨�ײ�����ֻ��һöҶ�ӽڵ�<br>��4�����
	 */
	public enum Line {
		TOP(new LineBuilder(){
			public void buildLine(BigTree bigTree, Composite parent){
	            bigTree.createTopLine(parent);
            }
		}),
		CENTER(new LineBuilder(){
			public void buildLine(BigTree bigTree, Composite parent){
	            bigTree.createCenterLine(parent);
            }
		}),
		BOTTOM(new LineBuilder(){
			public void buildLine(BigTree bigTree, Composite parent){
	            bigTree.createBottomLine(parent);
            }
		}),
		LINE(new LineBuilder(){
			public void buildLine(BigTree bigTree, Composite parent){
	            bigTree.createHorizontalLine(parent);
            }
		});
		
		public interface LineBuilder{
			public void buildLine(BigTree bigTree,Composite parent);
		}
		
		private LineBuilder lineBuilder;
		
		Line(LineBuilder lineBuilder){
			this.lineBuilder = lineBuilder;
		}
	}
	
	static{
		NODE_GRIDDATA.grabExcessVerticalSpace = true;
	}
	
	public BigTree(Composite parent,DepartmentNode root) {		
		super(parent);
		ROOT = root;
		init();
	}
	
	private void init(){
		initCmp();//����
	    createRootCmp();//���ڵ㲼��		
    }
	
	private void createRootCmp(){
	    createRoot(mainCmp);
	    createNodes(mainCmp,ROOT);
    }
	
	private void initCmp(){
	    this.setLayout(getGridLayout(1));//������Ϊһ��ʽGrid����
	    mainCmp = new Composite(this);
	    mainCmp.setLayout(getGridLayout(3));
	    mainCmp.setLayoutData(GridData.INS_CENTER_BOTH);
    }
	
	private GridLayout getGridLayout(int size){
		GridLayout layout = new GridLayout(size);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		return layout;
	}	
	/**
	 * һ������������װ��3������(�ϣ���(װ��һ����ǩ)����) �������ڵ�
	 */
	private void createRoot(Composite parent){
		
	    Composite cmp = new Composite(parent);	    
	    cmp.setLayout(getGridLayout(1));
	    GridData gridData = new GridData(GridData.VERTICAL_ALIGN_CENTER|GridData.GRAB_VERTICAL);
	    gridData.widthHint = ROOT_MIDDLE_IMG.getWidth();
	    cmp.setLayoutData(gridData);
	    
	    Composite topCmp = new Composite(cmp);
	    topCmp.setLayoutData(new GridData(ROOT_TOP_IMG.getWidth(), ROOT_TOP_IMG.getHeight()));
	    topCmp.setBackimage(ROOT_TOP_IMG);
	    
	    Composite middleCmp = new Composite(cmp);
	    middleCmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
	    middleCmp.setBackimage(ROOT_MIDDLE_IMG);
	    middleCmp.setLayout(getGridLayout(1));
	    createRootText(middleCmp);
	    
	    Composite bottomCmp = new Composite(cmp);
	    bottomCmp.setLayoutData(new GridData(ROOT_BOTTOM_IMG.getWidth(), ROOT_BOTTOM_IMG.getHeight()));
	    bottomCmp.setBackimage(ROOT_BOTTOM_IMG);
    }
	
	private void createRootText(Composite middleCmp){
	    Label label = new Label(middleCmp,JWT.WRAP);
	    label.setText(ROOT.getName());
	    GridData gridData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_CENTER|GridData.GRAB_VERTICAL|GridData.VERTICAL_ALIGN_CENTER);
	    gridData.widthHint = 16;
	    label.setFont(TEXT_FONT);
	    label.setLayoutData(gridData);
    }
	
	/**
	 * �����ӽڵ�(���еķǸ��ڵ�)
	 */
	private Composite createNode(Composite parent,String text){
		
		Composite cmp = new Composite(parent);
		cmp.setLayoutData(NODE_GRIDDATA);
		cmp.setLayout(getGridLayout(3));
		
		Composite leftCmp = new Composite(cmp);
		leftCmp.setLayoutData(new GridData(NODE_LFET_IMG.getWidth(), NODE_LFET_IMG.getHeight()));
		leftCmp.setBackimage(NODE_LFET_IMG);
		
		Composite centerCmp = new Composite(cmp);
		centerCmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		centerCmp.setBackimage(NODE_CENTER_IMG);
		centerCmp.setLayout(getGridLayout(1));
		createNodeText(centerCmp,text);
		
		Composite rightCmp = new Composite(cmp);
		rightCmp.setLayoutData(new GridData(NODE_RIGHT_IMG.getWidth(), NODE_RIGHT_IMG.getHeight()));
		rightCmp.setBackimage(NODE_RIGHT_IMG);
		
		cmp.layout();
		return cmp;
	}
	
	private void createNodeText(Composite centerCmp, String text){
	    Label label = new Label(centerCmp);
	    label.setText(text);
	    GridData gridData = new GridData(GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_CENTER|GridData.GRAB_VERTICAL|GridData.VERTICAL_ALIGN_CENTER);
	    gridData.heightHint = 14;
	    label.setFont(TEXT_FONT);
	    label.setLayoutData(gridData);	    
    }
	
	/**
	 * ��createNodeCmp����һ��ݹ鴦���ӽڵ��չʾ
	 */
	private void createNodes(Composite parent, DepartmentNode node){
		
		DepartmentNode[] children = node.getChildren();
		
		if(children==null||children.length==0)
			return;
		
		createHorizontalLine(parent);
		
		Composite nodesCmp = new Composite(parent);
	    nodesCmp.setLayout(getGridLayout(1));
	    nodesCmp.setLayoutData(new GridData(GridData.GRAB_VERTICAL|GridData.VERTICAL_ALIGN_CENTER));
	    
	    int index = 1;
	    
		for(int i=0;i<children.length;i++){
			
			DepartmentNode iBigTreeData = children[i];
				
				Composite nodeCmp = new Composite(nodesCmp);
				nodeCmp.setLayout(getGridLayout(3));
				
				if(iBigTreeData.getChildren().length>0){
					nodeCmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
				}else{
					GridData gridData = new GridData(GridData.GRAB_VERTICAL|GridData.VERTICAL_ALIGN_CENTER);
					gridData.heightHint = 40;
					nodeCmp.setLayoutData(gridData);
				}
				
				Line line = Line.CENTER;
				
				if(children.length==1){ //���Ҷ�ӽڵ���Ϊ1����������Ϊһ������
					line = Line.LINE;
				}else if(index==1){ //���Ҷ�ӽڵ�������1���е�ǰ�ǵ�һ��Ҷ�ӽڵ�
					line = Line.TOP;
				}else if(index==children.length){ //���Ҷ�ӽڵ����1���е�ǰ�����һ��Ҷ�ӽڵ�
					line = Line.BOTTOM;
				}
				
				createNodeCmp(nodeCmp, iBigTreeData,line);
				
				index++;

		}
		
    }

	//�����ӽڵ�
	private void createNodeCmp(Composite nodeCmp, DepartmentNode node,Line line){
	    line.lineBuilder.buildLine(this, nodeCmp);
	    createNode(nodeCmp, node.getName());
		Composite childCmp = new Composite(nodeCmp);
		childCmp.setLayout(getGridLayout(2));
		childCmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		createNodes(childCmp,node);	 
	    
    }

	
	
	/**
	 * ������һ��Ҷ�ӽڵ��������
	 * ע��������1������Ҷ�ӽڵ����ʹ�ô�������
	 * 
	 * @param parent
	 * @return Composite
	 */
	private Composite createTopLine(Composite parent){
		Composite cmp = createLineCmp(parent);
		createLineNullCmp(cmp);
		createLineTopCmp(cmp);
		createLineVerticalCmp(cmp);
		return cmp;
	}
	
	/**
	 * ����һ���ǵ�һ�����һ��Ҷ�ӽڵ��������
	 * ע��������2������Ҷ�ӽڵ����ʹ�ô�������
	 * 
	 * @param parent
	 * @return Composite
	 */
	private Composite createCenterLine(Composite parent){
		Composite cmp = createLineCmp(parent);
		createLineVerticalCmp(cmp);
		createLineCenterCmp(cmp);
		createLineVerticalCmp(cmp);
		return cmp;
	}
	
	/**
	 * �������һ��Ҷ�ӽڵ��������
	 * ע��������1�����ϵ�Ҷ�ӽڵ����ʹ�ô�������
	 * 
	 * @param parent
	 * @return Composite
	 */
	private Composite createBottomLine(Composite parent){
		Composite cmp = createLineCmp(parent);
		createLineVerticalCmp(cmp);
		createLineBottomCmp(cmp);
		createLineNullCmp(cmp);
		return cmp;
	}
	
	/**
	 * ֻ��һ��Ҷ�ӽڵ�ʱʹ�õ�������
	 * 
	 * @param parent
	 * @return Composite
	 */
	private Composite createHorizontalLine(Composite parent){
		Composite cmp = createLineCmp(parent);
		createLineNullCmp(cmp);
		createLineHorizontalCmp(cmp);
		createLineNullCmp(cmp);
		return cmp;
	}
	
	private void createLineNullCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
    }
	
	private void createLineCenterCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(LINE_GRIDDATA);
	    cmp.setBackimage(CENTER_LINE_IMG);
	}
	
	private void createLineBottomCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(LINE_GRIDDATA);
	    cmp.setBackimage(BOTTOM_LINE_IMG);
	}
	
	private void createLineTopCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(LINE_GRIDDATA);
	    cmp.setBackimage(TOP_LINE_IMG);
    }

	private void createLineVerticalCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
	    cmp.setBackimage(VERTICAL_LINE_IMG);
    }
	
	private void createLineHorizontalCmp(Composite parent){
	    Composite cmp = new Composite(parent);
	    cmp.setLayoutData(LINE_GRIDDATA);
	    cmp.setBackimage(HORIZONTAL_LINE_IMG);
	}
	
	private Composite createLineCmp(Composite parent){
		Composite cmp = new Composite(parent);
		cmp.setLayout(getGridLayout(1));
		GridData gridData = new GridData(GridData.GRAB_VERTICAL|GridData.VERTICAL_ALIGN_FILL);
		gridData.widthHint = LINE_WIDTH;
		cmp.setLayoutData(gridData);
		return cmp;
	}
}
