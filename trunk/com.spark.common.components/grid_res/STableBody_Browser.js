/*! ����ʹ�õ�JS�ļ�  */

/**
 * ��������ӵ�ʱ���DNA����
 */
function notifyGrid(actionName, actionValues,event) {
	event = event ? event : window.event;
	var obj = event.srcElement ? event.srcElement : event.target;
	var id = $(obj).parents("tr").attr("id");
	handleEvent("links" + ":" + actionName + ":" + actionValues + ":" + id + ":" + event.x + ":" + event.y);
}

/**
 * checkboxȫѡ
 */
function childrenSel(issel) {
	if(issel == 'no') {
		$("[name=checkbox1]").each(function() {
			if($(this).attr("disabled") == null || $(this).attr("disabled") == "true") {
				$(this).attr("checked", "true");
			}
		});
	}else {
		$("[name=checkbox1]").each(function() {
			if($(this).attr("disabled") == null || $(this).attr("disabled") == "true") {
				$(this).removeAttr("checked");
			}
		});
	}
	checkbox_selected(null);
}

/**
 * checkbox��ѡ
 */
function selectedOne(box) {
	
	if($("[name=checkbox1]:checked").length == $("tr").length) {
		//ȫѡ�ñ����ϵ�ȫѡ��������
		checkbox_selected(true);
	}else {
		//��ȫѡ�ñ����ϵ�ȫѡ������Ҫ����
		checkbox_selected(false);
	}
	
}

/**
 * �����ѡ���¼�
 */
function attchRowClickSelection() {
	$("body").click(function(event) {
		if(event.target.nodeName.toLowerCase() == "td") {
			rowClickSelection(event.target);
		} else if($(event.target).parents("td").length > 0) {
			rowClickSelection($(event.target).parents("td").eq(0));
		}
	});
}

function rowClickSelection(td) {
	var id = $(td).parent("tr").attr("id");
	$("tr").css("backgroundColor", "#FFFFFF");
	$(td).parent("tr").css("backgroundColor", "#C3DFE9");
	handleEvent("tableSelection" + ":" + id);
}

/**
 * ��ѡ��
 */
function rowSelected(rowId) {
	$("tr").css("backgroundColor", "#FFFFFF");
	setRowClickSelection(rowId);
}

/**
 * �ṩ���ⲿ���õĿͻ��˷������ô����и���
 * @param rowId
 */
function setRowClickSelection(rowId) {
	$("#" + rowId).css("backgroundColor", "#C3DFE9");
	handleEvent("tableSelection" + ":" + rowId);
}

/**
 * radioѡ��
 */
function selectRadio(radio) {
	var id = $(radio).parents("tr").attr("id");
	handleEvent("tableSelection" + ":" + id);
}

/**
 * ���ĳһ�е�ʱ���DNA����
 */
function selClick(rowIndex, cellIndex, td) {
	handleEvent("selClick" + ":" + rowIndex + ":" + cellIndex + ":" + $(td).parent().attr("id"));
}

/**
 * ����ĳһ��һ�е����ݣ���DNA����
 */
function setTableText(row, cell, text) {
	setDivText($("tr").eq(row).find("td").eq(cell).find("div"),text);
}

/**
 * �ı�����ɫ��ͬʱ��DNA����
 */
function getChangeTd(event) {
	var obj = getObj(event);
	if(obj.nodeName.toLowerCase() == "td") {
		return obj;
	} else if($(obj).parent("td").length > 0) {
		return $(obj).parent("td").eq(0);
	}
}

function changColor(tr,color){
	if(tr.css("background-color") != "#c3dfe9") {
		//����Ѿ���û�б�ѡ�У���ô�ı���е���ɫ
		tr.css("background-color", color);
	}
}

/**
 * ����ƶ���ȥ
 */
function table_content_Move(event) {
	var td = getChangeTd(event);
	// var brothers = table_getBrothers($(td).parents("tr"));
	var brothers = table_getBrothers($(td).parent());
	$(brothers).each(function(){
		changColor($(this),"#d6f3fc");
	});
	/*
	$.each(brothers,function(key,val){
		changColor(val,"#d6f3fc");
	});
	*/
	// changColor($(td).parents("tr"),"#d6f3fc");
	var handleValue = "table_mouseover:" + $(td).parent().index() + ":" + $(td).index();
	handleEvent(handleValue);
}

function table_getBrothers(tr) {
	var brothers = new Array();
	var trId = $(tr).attr("id");
	if (trId) {
		var idArray = trId.split("_");
		brothers = $("tr[id^='" + idArray[0] + "']");
	}
	return brothers;
}

/**
 * ����ƶ���ȥ
 */
function table_content_Out(event) {
	var td = getChangeTd(event);
	var tr = $(td).parents("tr");
	var handleValue = "table_mouseout:" + tr.index() + ":" + $(td).index() + ":" + tr.attr("id");
	handleEvent(handleValue);
}

function tableMouseOut(rowid) {
	if (rowid) {
		var idArray = rowid.split("_");
		var brothers = table_getBrothers($("tr[id^='" + idArray[0] + "']"));
		$(brothers).each(function(){
			changColor($(this),"FFFFFF");
		});
	}
	
	// changColor($("#"+rowid),"FFFFFF");
}

/**
 * ��body����¼�
 */
function table_content_Click(event) {
	var obj = getObj(event);

	if(obj.nodeName.toLowerCase() == "input") {
		if($(obj).attr("type").toLowerCase() == "checkbox") {
			selectedOne($(obj));
		} else if($(obj).attr("type").toLowerCase() == "radio") {
			selectRadio($(obj));
		}
		return;
	}

	if(obj.nodeName.toLowerCase() == "td") {
		initSelClick(obj);
	} else if($(obj).parents("td").length > 0) {
		initSelClick($(obj).parents("td").eq(0));
	}
}

function initSelClick(td) {
	var $td = $(td);
	var row = $td.parents("tr").index();
	var col = $td.index();
	if($td.parent().find("input").length > 0 && $td.parent().find("input").attr("type").toLowerCase() == "radio") {
		$td.parent().find("input").attr("checked", true);
		selectRadio($td);
	}
	selClick(row, col, $td);
}

/**
 * ����ÿһ�У�ÿһ�еĿ��
 */
function setColumnWidths(columnWidths, isScrollbar,rowids) {
	if(isScrollbar) {
		$("table").find("tr").each(function() {
			if($(this).find("td").eq($(this).find("td").length - 1).css("border-right-width") == "1px") {
				return;
			}
			$(this).find("td").eq($(this).find("td").length - 1).css("border-right-width", "1px");
		});
	} else {
		$("table").find("tr").each(function() {
			if($(this).find("td").eq($(this).find("td").length - 1).css("border-right-width") == "0px") {
				return;
			}
			$(this).find("td").eq($(this).find("td").length - 1).css("border-right-width", "0px");
		});
	}
	if(!$.browser.msie) {
		if(rowids != null) {
			for(var i = 0; i < rowids.length; i++) {
				$("#" + rowids[i]).find("td").each(function(index) {
					$(this).css("width", columnWidths[index]);
					$(this).find("div").css("width", Number(columnWidths[index]) - 10);
				});
			}
		} else {
			$("tr").each(function(index) {
				$(this).find("td").each(function(index) {
					$(this).css("width", columnWidths[index]);
					$(this).find("div").css("width", Number(columnWidths[index]) - 10);
				})
			});
		}
	}else {
		if($("table").find("tr").length > 0) {
					$("table").find("tr").eq(0).find("td").each(function(index) {
						$(this).css("width", columnWidths[index]);
					});
				}
		/*
		$("table").find("tr").each(function(index) {
						$(this).find("td").each(function(index) {
							$(this).css("width", columnWidths[index]);
							$(this).find("div").css("width", Number(columnWidths[index]) - 10);
						})
					});*/
		
	}
}


/**
 * checkboxѡ��֮���DNA����
 */
function checkbox_selected(issel) {
	var ids = new Array();
	$("[name=checkbox1]:checked").each(function(index) {
		if($(this).parent().parent().attr("id") != null) {
			ids[index] = $(this).parent().parent().attr("id");
		}
	});
	handleEvent("tableSelection" + ":" + ids + ":" + issel);
}

/**
 * ˫��
 */
function table_content_dblClick(event) {
	var obj = getObj(event);
	var id = null;
	if(obj.nodeName.toLowerCase() == "td" || $(obj).parent("td").length > 0) {
		id = $(obj).parents("tr").attr("id");
		handleEvent("dblClick:"+ id);
	} 
}

/**
 * �����û����λ�õõ���ǰ���Ǹ�DOM����
 */
function getObj(event) {
	event = event ? event : window.event;
	var obj = event.srcElement ? event.srcElement : event.target;
	return obj;
}

/**
 * ����һ��
 */
function addRow(rowData) {
	$("table").append(rowData);
}

/**
 * �޸�һ��
 */
function updateRow(rowData, id) {
	if($("table").find("#" + id).length > 0) {
		$("table").find("#" + id).each(function() {
			$(this).replaceWith(rowData);
		})
	}

}

/**
 * ɾ��һ��
 */
function removeRow(id) {
	if($("#" + id).length > 0) {
		$("#" + id).remove();
	}
	checkbox_selected(null);
}

/**
 * �޸�һ��
 */
function updateCell(column, id, text,tooltip) {
	setDivText($("#"+id).find("td").eq(column).find("div"),text);
	if(tooltip != null) {
		$("#"+id).find("td").eq(column).find("div").attr("title",tooltip);
	}
	
}

/**
 * ����div��text��title
 */
function setDivText(div,text) {
	div.text(text);
	div.attr("title",text);
}



/**
 * �����ַ�
 */
function findText(text) {
	var textarray = new Array();
	$("table").find("td").css("background-color", "#FFFFFF");
	if(text == "" || text.length == 0) {
	    return;
	}
	$("table").find("tr").each(function(index) {
		var row = index;
		$(this).find("td").each(function(index) {
			var col = index;
			if($(this).attr("class") != null && $(this).attr("class") == "search") {
				var div = $(this).find("div");
				if(div.text().indexOf(text) != -1) {
					$(this).css("background-color", "#caf2ba");
					textarray.push(row + "-" + col);
				}
			}

		});
	});
	if(textarray != null && textarray.length > 0) {
		var number = textarray[0].split("-");
		$("table").find("tr").eq(number[0]).find("td").eq(number[1]).css("background-color", "#fdeaa4");
	}
	handleEvent("findText" + ":" + text +":"+ textarray);
}

/*
 * �޸ĵ�Ԫ����ɫ(��һ������һ��)
 */
function changeSearchColor(searchArray,searchNumber) {
	if(searchArray.length > 0) {
		for(var i =0;i< searchArray.length;i++) {
			var number = searchArray[i].split("-");
			$("table").find("tr").eq(number[0]).find("td").eq(number[1]).css("background-color", "#caf2ba");
		}
		var number = searchArray[searchNumber].split("-");
		$("table").find("tr").eq(number[0]).find("td").eq(number[1]).css("background-color", "#fdeaa4");	
	}
}

/**
 * �ı䵥Ԫ����ɫ
 * @param {} rowId
 * @param {} columnIndex
 * @param {} color
 */
function changeTextBackgroundColor(textBackgroundColors) {
	for(var i=0;i<textBackgroundColors.length;i++) {
		var textBackgroundColor = textBackgroundColors[i];
		$("#"+textBackgroundColor.rowId).find("td").eq(textBackgroundColor.columnIndex).css("background-color", textBackgroundColor.color);
	}
}


/**
 * �����жϸ�JS�Ƿ��Ѿ��������
 */
function loaded() {
}