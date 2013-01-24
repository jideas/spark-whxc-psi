/*! 表头使用的JS文件  */

function selall() {
	if($("[name=checkbox1]:checked").length > 0) {
		handleHeaderEvent("selall:no");
	}else {
		handleHeaderEvent("selall:yes");
	}
}

function checksel(issel) {
	if(issel == "true") {
		$("[name=checkbox1]").attr("checked","true");
	}else {
		$("[name=checkbox1]").removeAttr("checked");
	}
}


function setColumnWidths(columnWidths,overWidth,isScrollbar) {
	if(isScrollbar) {
		//滚动条的出现的时候，需要给每一行的最后一列加上右边框，并且还要新增一列填满滚动条的空间
		if($("#hidetd").length == 0) {
			$("table").find("tr").append("<td id='hidetd' style='width:"+overWidth+";border-width:0px 0px 0px 0px;'>&nbsp;</td>");
		}
	}else {
		if($("#hidetd").length > 0) {
			$("#hidetd").remove();
		}
	}
	
	if($("table").find("tr").length > 1) {
		//这里处理多重表头的情况
		var index = 0;
		var number = 0;
		$("table").find("tr").eq(0).find("td").each(function() {
			if($(this).attr("colspan") == null) {
				//单纯表头
				$(this).css("width", columnWidths[index]);
				index++;
			} else {
				//多重表头
				var parent_width = 0;
				for(var i = number; i < (number + $(this).attr("colspan")); i++) {
					var td = $("table").find("tr").eq(1).find("td").eq(i);
					td.css("width", columnWidths[index]);
					parent_width += columnWidths[index];
					if(index == columnWidths.length-1) {
						td.css("border-right-width", 0);
					}
					index++;
				}
				$(this).css("width",parent_width);
				if(index == columnWidths.length) {
					$(this).css("border-right-width", 0);
				}
				number += $(this).attr("colspan");
			}
		});
	} else {
		$("table").find("tr").each(function() {
			$(this).find("td").each(function(index) {
				$(this).css("width", columnWidths[index]);
				if(index == columnWidths.length-1) {
					$(this).css("border-right-width", 0);
				}
			});
			
		});
	}
	
	
	$("[name=checkbox1]").removeAttr("checked");
	
}

function sort(td) {
	if($(td).children("img").length > 0 && $(td).children("img").attr("src").indexOf("headarrow-down") != -1) {
		$("td").children("img").remove();
		$(td).append("<img id='up_img' src='/saas_grid/img/headarrow-up.png'></img>");
		handleHeaderEvent("sort" + ":" + $(td).attr("id") + ":" +"ASC");
	}else {
		$("td").children("img").remove();
		$(td).append("<img id='down_img' src='/saas_grid/img/headarrow-down.png'></img>");
		handleHeaderEvent("sort" + ":" + $(td).attr("id") + ":" +"DESC");
	}
}


function table_title_Click(event) {
	var event = event?event:window.event;
	var obj = event.srcElement?event.srcElement:event.target;
	
	if(obj.nodeName.toLowerCase() == "input") {
		if($(obj).attr("type").toLowerCase() == "checkbox") {
			selall();
		}
		return;
	}
	if(obj.nodeName.toLowerCase() == "td") {
		initSort(obj);
	}else if($(obj).parent("td").length > 0) {
		initSort($(obj).parent("td").eq(0));
	}
}

function initSort(td) {
	var $td = $(td);
	if($td.css("cursor") == "pointer") {
		sort($td);
	}
}

function loaded() {
}
