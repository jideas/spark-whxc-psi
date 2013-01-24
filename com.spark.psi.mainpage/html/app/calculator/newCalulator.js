function gethtml(td,oper) {
	var number = 33;
	var height = 20;
	if($(td).text() == "0") {
		number = 75;
	}
	if($(td).text() == "=") {
		height = 50;
	}
	var html = "<div>";
	html += '<table height='+height+'"px" width='+number+'"px" border="0" cellpadding= "0" cellspacing= "0" class="tableca">';
	html += '<tr class="tr1" height="1px" >';
	html += '<td class="button-top-left" width="3px"></td>';
	html += '<td class="button-top-center" width='+(Number(number-1))+'"px" height="3px"></td>';
	html += '<td class="button-top-right" width="2px"></td>';
	html += '</tr>';
	
	html += '<tr class="tr2">';
	html += '<td class="button-center-left" width="2px"></td>';
	html += '<td class="button-center-center" height="95%" width='+(Number(number-1))+'"px">'+oper;
	html += '</td><td class="button-center-right" width="2px"></td>';
	html += '</tr>';
	html += '<tr class="tr3" height="1px" >';
	html += '<td class="button-bottom-left" width="2px"></td>';
	html += '<td class="button-bottom-center" width='+(Number(number-1))+'"px"></td>';
	html += '<td class="button-bottom-right" width="2px"></td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$(td).empty();
	$(td).append(html);
}

function initTable() {
	$.each($("table").find("td"),function(i,n) {
		gethtml(this,$(this).text());
		$(this).css("cursor","pointer");
		$(this).mouseover(function() {
			var tds = $(this).find("td");
			tds.each(function() {
				var h = $(this).css("background-image").replace("btns_n","btns_h");
				$(this).css("background-image",h);
			});
		}).
		mousedown(function() {
			var tds = $(this).find("td");
			tds.each(function() {
				var f = $(this).css("background-image").replace("btns_h","btns_f");
				$(this).css("background-image",f);
			});
		}).
		mouseup(function() { 
			var tds = $(this).find("td");
			tds.each(function() {
				var h = $(this).css("background-image").replace("btns_f","btns_h");
				$(this).css("background-image",h);
			});
		}).
		mouseout(function() {
			var tds = $(this).find("td");
			tds.each(function() {
				var n = $(this).css("background-image").replace("btns_h","btns_n");
				$(this).css("background-image",n);
			});
		 }).
		 click(function() {
		 	var tds = $(this).find(".button-center-center");
		 	tds.each(function() {
				var n = $(this).text();
				if(Number(n) >=0 && Number(n) <= 9) {
					
					if(calulator_Number != "0") {
						calulator_Number += String(n);
					}else {
						calulator_Number = String(n);
					}
					$("#div2right").text(calulator_Number);
					checkSquare();
				}
				switch(n) {
					case ".":
						calulator_Number += n;
						$("#div2right").text(calulator_Number);
						break;
					case "MC":
						MC();
						break;
					case "MS":
						MS();
						break;
					case "MR":
						MR();
						break;
					case "M+":
						Madd();
						break;
					case "M-":
						mdel();
						break;
					case "C":
						C();
						break;
					case "CE":
						CE();
						break;
					case "+":
						//�ӷ�
						addition();
						break;
					case "-":
						//����
						subtraction();	
						break;
					case "*":
						//�˷�
						multiplication();	
						break;
					case "/":
						//����
						division();	
						break;
					case "=":
						//����
						equalMark();
						break;
					case "��":
						//����
						back();
						break;
					case "%":
						//�ٷֱ�
						remainder();
						break;
					case "��":
						//������
						plusMinus();
						break;
					case "��":
						//��ƽ��
						square();
						break;
					case "1/x":
						//���ٷ�֮һ
						reciproc();
						break;
						
				}
			});
		 });
	});
	
	
}

/**
 * ������������
 */
$(document).keydown(function (e) {
		//����ֻ����������
		var keyStr = "";
		if($.browser.msie) {
    		keyStr=e.keyCode;
		} else {
			keyStr=e.which;
		}
		keyStr = Number(keyStr);
		if((keyStr>=48&&keyStr<=57)) { 
			//����̵�����
			var number = calulator_Number += String.fromCharCode(keyStr);
			$("#div2right").text(number);
			checkSquare();
		}else if((keyStr>=96&&keyStr<=105) ||keyStr==190 ||keyStr==110) {
			//С���̵����ֺ�.
			$("#div2right").text(getCode(keyStr));
			checkSquare();
		}
		
		switch(keyStr) {
			case 107:
				//�ӷ�
				addition();
				break;
			case 109:
				//����
				subtraction();	
				break;
			case 106:
				//�˷�
				multiplication();	
				break;
			case 111:
				//����
				division();	
				break;
			case 13:
				//����
				equalMark();
				break;
			case 8:
				//����
				back();
				return false;
				break;
		}
		
		
		
	});

//����ǲ����ڼ���ƽ����,1/x������ǣ���ô��ɾ��div1������
function checkSquare() {
	var text = $("#div1").text();
	if($.trim(text) != "" && (text.substring(0,1) == "s" || text.substring(0,1) == "r")) {
		$("#div1").text("");
		$("#div1").append("&nbsp;");
	}
		
}

initTable();