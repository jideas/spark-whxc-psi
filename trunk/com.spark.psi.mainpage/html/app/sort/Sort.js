Sort = function() {
	
}

Sort.extend(MoveDesktop,{
	init:function() {
		var today = new Date();
		var c = $(window.parent.document.body);
		var father = c.find('#sortiframe');
		var width = father.css("width").substring(0,father.css("width").length-2);
		var height = father.css("height").substring(0,father.css("height").length-2);
		var name = father.attr("class");
		var alldiv = Sort.superclass.create.call(this,width,height,name);
		alldiv.children().remove();
		var div = $("<div></div>");
		div.css("padding-top","15px");
		div.css("padding-left","20px");
		alldiv.append(div);
		var img = $("<img></img>");
		img.attr("src","images/HengXiang_n.gif");
		img.css("cursor","pointer");
		img.hover(function(){
			$(this).attr(src,$(this).attr(src).replace("HengXiang_n.gif","HengXiang_h.gif"));
		},function(){
			$(this).attr(src,$(this).attr(src).replace("HengXiang_h.gif","HengXiang_n.gif"));
		});
		div.append(img);
		img.click(function() {
			window.top.orderImage("crosswise",8);
		});
		var img = $("<img></img>");
		img.attr("src","images/ZongXiang_n.gif");
		img.css("margin-left","20px");
		img.css("cursor","pointer");
		img.hover(function(){
			$(this).attr(src,$(this).attr(src).replace("ZongXiang_n.gif","ZongXiang_h.gif"));
		},function(){
			$(this).attr(src,$(this).attr(src).replace("ZongXiang_h.gif","ZongXiang_n.gif"));
		});
		img.click(function() {
			window.top.orderImage("lengthways",8);
		});
		div.append(img);
		
		var div = $("<div></div>");
		div.css("padding-top","10px");
		div.css("padding-left","23px");
		alldiv.append(div);
		var font = $("<font style='font-size:12px;color:#FFFFFF'>横向排列</font>");
		div.append(font);
		var font = $("<font style='font-size:12px;color:#FFFFFF'>纵向排列</font>");
		font.css("margin-left","28px");
		div.append(font);
	}
});