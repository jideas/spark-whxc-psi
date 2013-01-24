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
	 * 图片目录
	 */
	private final static String PATH = "images/bigtree/";	
	
	/**
	 * 连接线的宽度
	 */
	private final static int LINE_WIDTH = 30;//grop_line_cline.png	
	/**
	 * 连接线的高度
	 */
	private final static int LINE_HEIGHT = 32;
	
//	/**
//	 * 节点的宽度
//	 */
//	private final static int NODE_WIDTH = 145;	
//	/**
//	 * 节点的高度
//	 */
//	private final static int NODE_HEIGHT = 32;
	
	/**
	 * 连接线的布局参数
	 */
	private final static GridData LINE_GRIDDATA = new GridData(LINE_WIDTH, LINE_HEIGHT);
	
	/**
	 * 节点的布局参数
	 */
	private final static GridData NODE_GRIDDATA = new GridData();
	
	/**
	 * 顶部连接线
	 */
	private final static ImageDescriptor TOP_LINE_IMG = BaseImages.getImage(PATH + "grop_line_top.png");
	//	|---
	//	|
	/**
	 * 中间的连接线
	 */
	private final static ImageDescriptor CENTER_LINE_IMG = BaseImages.getImage(PATH + "grop_line_center.png");
	//	|
	//	|---
	//	|
	/**
	 * 底部连接线
	 */
	private final static ImageDescriptor BOTTOM_LINE_IMG = BaseImages.getImage(PATH + "grop_line_bottom.png");
	//	|
	//	|---
	
	/**
	 * 垂直连接线
	 */
	private final static ImageDescriptor VERTICAL_LINE_IMG = BaseImages.getImage(PATH + "grop_line_Lline.png");
	//	|
	/**
	 * 横向连接线
	 */
	private final static ImageDescriptor HORIZONTAL_LINE_IMG = BaseImages.getImage(PATH + "grop_line_cline.png");
	//	---
	
	/**
	 * 节点左边图片
	 */
	private final static ImageDescriptor NODE_LFET_IMG = BaseImages.getImage(PATH + "grop_btn2_left.png");//20*32
	/**
	 * 节点中间图片
	 */
	private final static ImageDescriptor NODE_CENTER_IMG = BaseImages.getImage(PATH + "grop_btn2_center.png");//1*32
	/**
	 * 节点右边图片
	 */
	private final static ImageDescriptor NODE_RIGHT_IMG = BaseImages.getImage(PATH + "grop_btn2_right.png");//20*32
	
	/**
	 * 公司名称上部图片
	 */
	private final static ImageDescriptor ROOT_TOP_IMG = BaseImages.getImage(PATH + "grop_btn_top.png");//56*30
	/**
	 * 公司名称中间图片
	 */
	private final static ImageDescriptor ROOT_MIDDLE_IMG = BaseImages.getImage(PATH + "grop_btn_center.png");//56*600
	/**
	 * 公司名称下边图片
	 */
	private final static ImageDescriptor ROOT_BOTTOM_IMG = BaseImages.getImage(PATH + "grop_btn_bottom.png");//56*30
	
	/**
	 * 标签显示字体
	 */
	private final static Font TEXT_FONT = new Font(11, JWT.FONT_STYLE_NAME_BOLD, JWT.FONT_STYLE_BOLD);
	
	/**
	 * 树节点的根节点对象数据
	 */
	private DepartmentNode ROOT;
	
	private Composite mainCmp;
	
	/**
	 * <p>连接线枚举</p>
	 * 分为第一枚叶子节点（顶部），中间叶子节点（中间区域），最后一枚叶子节点（底部），只有一枚叶子节点<br>等4种情况
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
		initCmp();//布局
	    createRootCmp();//根节点布局		
    }
	
	private void createRootCmp(){
	    createRoot(mainCmp);
	    createNodes(mainCmp,ROOT);
    }
	
	private void initCmp(){
	    this.setLayout(getGridLayout(1));//主容器为一列式Grid布局
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
	 * 一个容器，里面装有3个容器(上，中(装了一个标签)，下) 创建父节点
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
	 * 创建子节点(所有的非父节点)
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
	 * 与createNodeCmp方法一起递归处理子节点的展示
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
				
				if(children.length==1){ //如果叶子节点数为1，则连接线为一根横线
					line = Line.LINE;
				}else if(index==1){ //如果叶子节点数大于1，切当前是第一个叶子节点
					line = Line.TOP;
				}else if(index==children.length){ //如果叶子节点大于1，切当前是最后一个叶子节点
					line = Line.BOTTOM;
				}
				
				createNodeCmp(nodeCmp, iBigTreeData,line);
				
				index++;

		}
		
    }

	//创建子节点
	private void createNodeCmp(Composite nodeCmp, DepartmentNode node,Line line){
	    line.lineBuilder.buildLine(this, nodeCmp);
	    createNode(nodeCmp, node.getName());
		Composite childCmp = new Composite(nodeCmp);
		childCmp.setLayout(getGridLayout(2));
		childCmp.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		createNodes(childCmp,node);	 
	    
    }

	
	
	/**
	 * 创建第一个叶子节点的连接线
	 * 注：必须有1个以上叶子节点才能使用此连接线
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
	 * 创建一个非第一和最后一个叶子节点的连接线
	 * 注：必须有2个以上叶子节点才能使用此连接线
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
	 * 创建最后一个叶子节点的连接线
	 * 注：必须有1个以上的叶子节点才能使用此连接线
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
	 * 只有一个叶子节点时使用的连接线
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
