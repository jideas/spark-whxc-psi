/**
 * ������������
 */
 
calulator_Number = "0";  //��ʾ�ڼ��������������
calulator_Number_Save = "0";//���������������
/**
 * �����û�������̣��õ���Ӧ�ļ���ר����С���̵����ֺ�.��
 */
function getCode(key) {
 	if(key == 110 || key == 190) {
 		calulator_Number += ".";
 		return calulator_Number;
 	}
 	var number = "";
 	switch(key) {
 		case 96:
 			 number= 0;
 			 break;
 		case 97:
 			 number= 1;
 			 break;
 		case 98:
 			 number= 2;
 			 break;
 		case 99:
 			 number= 3;
 			 break;
 		case 100:
 			 number= 4;
 			 break;
 		case 101:
 			 number= 5;
 			 break;
 		case 102:
 			 number= 6;
 			 break;
 		case 103:
 			 number= 7;
 			 break;
 		case 104:
 			 number= 8;
 			 break;
 		case 105:
 			 number= 9;
 			 break;
 		default:
 			return "";
 	}
 	if(calulator_Number != "0") {
		calulator_Number += String(number);
	}else {
		calulator_Number = String(number);
	}
	return calulator_Number;
}
 
//��ms���洢��ʾ���е����� (��ʾ��˳���m )��
function MS() {
  	calulator_Number_Save = calulator_Number;
  	calulator_Number = "0";
  	$("#div2left").text("M");
}

//��mc������洢������   (������0��m��ʧ)��
function MC() {
	calulator_Number_Save = "0";
  	$("#div2left").text("0");
  	calulator_Number = "0";
}
//��mr������ʾ������ʾ�洢�����֡�
function MR() {
	calulator_Number = calulator_Number_Save;
	$("#div2right").text(calulator_Number);
	calulator_Number = "0";
}
// ��m+������ʾ���е����ּӵ��洢�����֡�
function Madd() {
	calulator_Number_Save = Number(calulator_Number_Save) + Number(calulator_Number);
	calulator_Number = "0";
}
//��m-�� m-�洢�����ּ�ȥ��ʾ���е����֡�
function mdel() {
	calulator_Number_Save = Number(calulator_Number_Save) - Number(calulator_Number);
	calulator_Number = "0";
}

//������������
function C() {
	calulator_Number = "0";
	$("#div1").text("");
	$("#div1").append("&nbsp;");
	$("#div2right").text("0");
	
}

//���DIV2�������
function CE() {
	calulator_Number = "0";
	$("#div2right").text("0");
}

//�ӷ�
function addition() {
	$("#div1").text($("#div2right").text() + " +");
	calulator_Number = "0";
}
//����
function subtraction() {
	$("#div1").text($("#div2right").text() + " -");
	calulator_Number = "0";
}
//����
function division() {
	$("#div1").text($("#div2right").text() + " /");
	calulator_Number = "0";
}
//�˷�
function multiplication() {
	$("#div1").text($("#div2right").text() + " *");
	calulator_Number = "0";
}

//����
function equalMark() {
	var text = $("#div1").text();
	if($.trim(text) != "") {
		text = $.trim(text);
		//���������ٷֱ�֮�������ڣ���ô�ͽ�������ж�
		if(text.indexOf("/") > 0 && text.substring(text.indexOf("/")+1,text.length).length > 0) {
			var first = text.substring(0,text.indexOf("/"));
			var second = text.substring(text.indexOf("/")+1,text.length);
			calulator_Number = Number(first) / Number(second);
			equalcommon();
			return;
		}
		else if(text.indexOf("+") > 0 && text.substring(text.indexOf("+")+1,text.length).length > 0) {
			var first = text.substring(0,text.indexOf("+"));
			var second = text.substring(text.indexOf("+")+1,text.length);
			calulator_Number = Number(first) + Number(second);
			equalcommon();
			return;
		}
		else if(text.indexOf("-") > 0 && text.substring(text.indexOf("-")+1,text.length).length > 0) {
			var first = text.substring(0,text.indexOf("-"));
			var second = text.substring(text.indexOf("-")+1,text.length);
			calulator_Number = Number(first) - Number(second);
			equalcommon();
			return;
		}
		else if(text.indexOf("*") > 0 && text.substring(text.indexOf("*")+1,text.length).length > 0) {
			var first = text.substring(0,text.indexOf("*"));
			var second = text.substring(text.indexOf("*")+1,text.length);
			calulator_Number = Number(first) * Number(second);
			equalcommon();
			return;
		}
		
		val = text.substring(0,text.length-1);
		text = text.substring(text.length-1,text.length);
		
		
		
		switch(text) {
			case "+":
				calulator_Number = Number(val) + Number($("#div2right").text());
				equalcommon();
				break;
			case "-":
				calulator_Number = Number(val) - Number($("#div2right").text());
				equalcommon();
				break;
			case "*":
				calulator_Number = Number(val) * Number($("#div2right").text());
				equalcommon();
				break;
			case "/":
				calulator_Number = Number(val) / Number($("#div2right").text());
				equalcommon();
				break;
		}
	}
}

function equalcommon() {
	$("#div2right").text(calulator_Number);
	$("#div1").text("");
	$("#div1").append("&nbsp;");
	calulator_Number = "";
}

//����
function back() {
	var text = $("#div2right").text();
	text = text.substring(0,text.length -1);
	if($.trim(text) == "") {
		text = "0";
	}
	calulator_Number = text;
	$("#div2right").text(calulator_Number);
}

//�ٷ���
function remainder() {
	var text = $("#div1").text();
	var div2right_text = $("#div2right").text();
	if($.trim(text) != "") {
		val = text.substring(0,text.length-1);
		text = text.substring(text.length-1,text.length);
		if(text == "+" || text == "-" || text == "*" || text == "/") {
			text = Number(val) * 0.01 * Number(div2right_text);
		}
		$("#div2right").text(text);
		$("#div1").text($("#div1").text() + " " + text);
		calulator_Number = "";
		
	}
}

//����ת��
function plusMinus() {
	var text = $("#div2right").text();
	if($.trim(text) != "") {
		val = text.substring(0,1);
		//text = text.substring(0,1);
		if(val == "-") {
			text = text.substring(1,text.length);
		}else if(text != "0"){
			text = "-"+text;
		}
		calulator_Number = text;
		$("#div2right").text(calulator_Number);
	}
}

//��ƽ��
function square() {
	var text = $("#div2right").text();
	if($.trim(text) != "" && text != "0") {
		var val = Math.pow(eval(text),1/2)
		calulator_Number = "";
		$("#div1").text("sqrt("+text+")");
		$("#div2right").text(val);
	}
}

//(1/x) X��֮һ�Ƕ��٣�����X=10����˼����1/10�Ƕ���
function reciproc() {
	var text = $("#div2right").text();
	if($.trim(text) != "" && text != "0") {
		calulator_Number = "";
		$("#div1").text("reciproc("+text+")");
		$("#div2right").text(1/Number(text));
	}
}

 