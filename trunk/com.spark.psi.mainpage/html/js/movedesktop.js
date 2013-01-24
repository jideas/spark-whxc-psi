MoveDesktop = {
		create : function(w,h,id) {
			this.moveid = id;
			$("#"+id).remove();
			alldiv = $("<div id='"+id+"' style='white-space:nowrap;overflow:hidden;'></div>");
			$("body").append(alldiv);
			alldiv.css("position","absolute");
			alldiv.css("left",200);
			alldiv.css("top",300);
			alldiv.css("z-index","4");
			alldiv.css("width",w);
			alldiv.css("height",h);
			//分2层,这里是第一层
			//第1层
			var div = $("<div class='cct_bgWindow_l' style='float:left'></div>");
			div.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgWindow_l.png)");
			div.css("width",8);
			div.css("height",h - 30);
			alldiv.append(div);
			var leftname  = this.moveid + "leftcadiv";
			var div = $("<div class='cct_bgWindow_c' style='float:left' id='"+leftname+"'></div>");
			div.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgWindow_c.png)");
			div.css("width",w-16);
			div.css("height",h - 30);
			alldiv.append(div);
			var div = $("<div class='cct_bgWindow_r'  style='float:left'></div>");
			div.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgWindow_r.png)");
			div.css("width",8);
			div.css("height",h - 30);
			alldiv.append(div);
			//第er层
			var buttom = $("<div></div>");
			buttom.css("width",w);
			buttom.css("height",10);
			alldiv.append(buttom);
			var buttomdiv = $("<div class='cct_bgFolder_l' style='float:left'></div>");
			buttomdiv.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgFolder_l.png)");
			buttomdiv.css("width",8);
			buttomdiv.css("height",10);
			buttom.append(buttomdiv);
			var buttomdiv = $("<div class='cct_bgFolder_c' style='float:left'></div>");
			buttomdiv.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgFolder_c.png)");
			buttomdiv.css("width",w-16);
			buttomdiv.css("height",10);
			buttom.append(buttomdiv);
			var buttomdiv = $("<div class='cct_bgFolder_r' style='float:left'></div>");
			buttomdiv.css("background-image","url(app/theme/common/images/level1/toolface/cct_bgFolder_r.png)");
			buttomdiv.css("width",8);
			buttomdiv.css("height",10);
			buttom.append(buttomdiv);
			var titlename = this.moveid+"toptitle";
			//alldiv.setHandler(''+titlename+'');
			return $("#"+leftname);
		},
		remove: function(child) {
				if($("#"+this.moveid).css("display") == 'none'){
					$("#"+this.moveid).css("display","block");
				}else {
					$("#"+this.moveid).css("display","none");
				}
			},
		isOpen:function() {
			if($("#"+this.moveid).length > 0) {
				return true;
			}else {
				return false;
			}
		},
		isClose:function() {
			
		},
		moveid : ""
}