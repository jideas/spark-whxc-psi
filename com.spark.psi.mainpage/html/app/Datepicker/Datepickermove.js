datepickermove = function() {

}
datepickermove.init = function(CalculatorX, CalculatorY) {
    var today = new Date();
    var body = $(window.parent.document.body);
    initdatepicker(today);
}
function initdatepicker(today) {
    var d = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    var div = $("#datepickermove");
    div.children().remove();
    var divtop = $("<div style='height:50px;width:100%;margin-top:5px;'></div>");
    divtop.mouseover(function() {
        $(".datepicker-cilck").css("display", "none");
        $(".datepicker-click-divtop").css("display", "none");
    });
    div.append(divtop);
    var divtopleft = $("<div class='datepicker_top_left'></div>");
    divtop.append(divtopleft);

    //前一月
    var img = $("<div>", {
        "class" : "date_month_before"
    });
    divtopleft.append(img);
    img.hover(function() {
        $(this).removeClass("date_month_before");
        $(this).addClass("date_month_before_hover");
    }, function() {
        $(this).removeClass("date_month_before_hover");
        $(this).addClass("date_month_before");
    });
    img.click(function() {
        var currentMonth = Number($("#months").text());
        var currentYear = Number($("#years").text());
        if(currentMonth == 1) {
            currentMonth = 12;
            currentYear = currentYear - 1;
        } else {
            currentMonth = currentMonth - 1;
        }
        var today = new Date();
        today.setMonth(currentMonth - 1);
        today.setYear(currentYear);
        initdatepicker(today);
    });
    var month = $("<div>", {
        "class" : "date_monthDiv"
    }).attr("id", "months").html((today.getMonth() + 1));
    divtopleft.append(month);

    var img = $("<div>", {
        "class" : "date_month_after"
    });
    divtopleft.append(img);
    img.hover(function() {
        $(this).removeClass("date_month_after");
        $(this).addClass("date_month_after_hover");
    }, function() {
        $(this).removeClass("date_month_after_hover");
        $(this).addClass("date_month_after");
    });
    img.click(function() {
        var currentMonth = Number($("#months").text());
        var currentYear = Number($("#years").text());
        if(currentMonth == 12) {
            currentMonth = 1;
            currentYear = currentYear + 1;
        } else {
            currentMonth = currentMonth + 1;
        }
        var today = new Date();
        today.setMonth(currentMonth - 1);
        today.setYear(currentYear);
        initdatepicker(today);
    });
    var divtopright = $("<div class='datepicker_top_right'></div>");

    divtop.append(divtopright);

    //显示当前年
    var divtopright1 = $("<div class='divtopright1'></div>");
    var $fontdiv = $("<div class='fontdiv' id='years'>" + today.getFullYear() + "</div>");
    divtopright1.append($fontdiv);
    //divtopright1.css("border","1px solid red");
    divtopright.append(divtopright1);

    //年份操作区域
    var divtopright1_1 = $("<div class='fontdiv'></div>");
    divtopright1.append(divtopright1_1);
    //上一年的按钮
    var $yearup = $("<div>", {
        "class" : "date_year_before"
    });
    divtopright1_1.append($yearup);
    $yearup.hover(function() {
        $(this).removeClass("date_year_before");
        $(this).addClass("date_year_before_hover");
    }, function() {
        $(this).removeClass("date_year_before_hover");
        $(this).addClass("date_year_before");
    });
    $yearup.click(function() {
        var currentYear = Number($("#years").text());
        var today = new Date();
        today.setYear(currentYear - 1);
        initdatepicker(today);
    });
    //下一年的按钮
    var $yeardown = $("<div>", {
        "class" : "date_year_after"
    });
    divtopright1_1.append($yeardown);
    $yeardown.hover(function() {
        $(this).removeClass("date_year_before");
        $(this).addClass("date_year_before_hover");
    }, function() {
        $(this).removeClass("date_year_before_hover");
        $(this).addClass("date_year_before");
    });
    $yeardown.click(function() {
        var currentYear = Number($("#years").text());
        var today = new Date();
        today.setYear(currentYear + 1);
        initdatepicker(today);
    });
    //星期
    var $fontdiv = $("<div class='fontdiv'>" + d[today.getDay()] + "</div>");
    divtopright1.append($fontdiv);

    var html = '<table height="21px" width="49px" border="0" cellpadding= "0" cellspacing= "0" class="tableca-datepicker" >';
    html += '<tr class="tr1" height="1px" >';
    html += '<td class="button-top-left-datepicker" width="2px"></td>';
    html += '<td class="button-top-center-datepicker" width="45px" height="3px"></td>';
    html += '<td class="button-top-right-datepicker" width="2px"></td>';
    html += '</tr>';
    html += '<tr class="tr2">';
    html += '<td class="button-center-left-datepicker" width="2px"></td>';
    html += '<td class="button-center-center-datepicker" height="18px" width="45px">今天';
    html += '</td><td class="button-center-right-datepicker" width="2px"></td>';
    html += '</tr>';
    html += '<tr class="tr3" height="1px" >';
    html += '<td class="button-bottom-left-datepicker" width="2px"></td>';
    html += '<td class="button-bottom-center-datepicker" width="45px" height="3px"></td>';
    html += '<td class="button-bottom-right-datepicker" width="2px"></td>';
    html += '</tr>';
    html += '</table>';
    var $html = $(html);
    divtopright1.append($html);
    $(".button-center-center-datepicker").click(function() {
        var today = new Date();
        initdatepicker(today);
    });
    $(".button-center-center-datepicker").mousedown(function() {
        $(this).css("background-image", $(this).css("background-image").replace("datebtn_n_bg", "datebtn_s_bg"));
    });

    $(".button-center-center-datepicker").mouseup(function() {
        $(this).css("background-image", $(this).css("background-image").replace("datebtn_s_bg", "datebtn_n_bg"));
    });
    var divtopright2 = $("<div class='divtopright2'></div>");
    divtopright.append(divtopright2);
    var $p = $("<div class='fontdiv'>" + CnDateofDateStr(today) + "</div>");
    divtopright2.append($p);
    var table = $('<table  class="datepicker_table"   CellSpacing=1 CellPadding=1 ></table>');
    div.append(table);
    getdatepickerTable();
}

function getdatepickerTable() {
    table = $(".datepicker_table");
    table.children().remove();
    var tr = $("<tr class='datepicker_table_tr' ></tr>");
    tr.mouseover(function() {
        $(".datepicker-cilck").css("display", "none");
        $(".datepicker-click-divtop").css("display", "none");
    });
    table.append(tr);
    var td = $("<td class='datepicker_table_td'>日</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>一</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>二</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>三</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>四</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>五</td>");
    tr.append(td);
    var td = $("<td class='datepicker_table_td'>六</td>");
    tr.append(td);

    var date = new Date();
    var year = Number($("#years").text());
    var month = Number($(".datepicker_top_left").text());
    date.setDate(1);
    date.setYear(year);
    date.setMonth(month - 1);

    var boforeDate = new Date();
    boforeDate.setYear(year);
    boforeDate.setMonth(month - 1);
    boforeDate.setDate(1);
    var afterDate = new Date();
    afterDate.setYear(year);
    afterDate.setMonth(month);
    afterDate.setDate(1);

    var firstDay = date.getDay();
    for(var i = 0; i < firstDay; i++) {
        boforeDate.setDate(boforeDate.getDate() - 1);
    }
    var times = new Date();
    var isCurrent = false;
    for(var i = 1; i <= 6; i++) {//周次
        var tr = $("<tr class='datepicker_table_tr2'></tr>");
        table.append(tr);
        for(var j = 0; j <= 6; j++) {//星期
            if(i == 1) {
                if(date.getDay() == j) {
                    var td = $("<td>" + date.getDate() + "</td>");
                    date.setDate(date.getDate() + 1);
                    isCurrent = true;
                } else {
                    //上一月的最后几天
                    var td = $("<td style='color:#666666;' class='notcurrent'>" + boforeDate.getDate() + "</td>");
                    boforeDate.setDate(boforeDate.getDate() + 1);
                    isCurrent = false;
                    //var td = $("<td>&nbsp;</td>");
                }
            } else {
                if(date.getDate() == 1) {
                    //下一月的前几天
                    var td = $("<td style='color:#666666;' class='notcurrent'>" + afterDate.getDate() + "</td>");
                    afterDate.setDate(afterDate.getDate() + 1);
                    isCurrent = false;
                    //var td = $("<td>&nbsp;</td>");
                } else {
                    var td = $("<td>" + date.getDate() + "</td>");
                    date.setDate(date.getDate() + 1);
                    isCurrent = true;
                }

            }
            if(j == 6) {
                td.addClass("datepicker_table_td3");
            } else {
                td.addClass("datepicker_table_td2");
            }
            if(times.getFullYear() == $("#years").text() && times.getMonth() == Number($(".datepicker_top_left").text()) - 1 && times.getDate() == td.text() && isCurrent) {
                td.css("background-color", "#166b00");
            }

            td.click(function() {
                if(this.className.indexOf("notcurrent") >= 0) {
                    return;
                }
                var times = new Date();
                $(".datepicker_table").find("td").each(function() {
                    if($.trim($(this).text()).length > 0 && times.getFullYear() == $("#years").text() && times.getMonth() == Number($(".datepicker_top_left").text()) - 1 && times.getDate() == $(this).text()) {
                        if(this.className.indexOf("notcurrent") < 0) {
                            $(this).css("background-color", "#166b00");
                        }
                    } else {
                        $(this).css("background-color", "");
                    }
                });
                $(this).css("background-color", "#506a96");
            });

            td.mouseover(function(event) {
                if(this.className.indexOf("notcurrent") >= 0) {
                    return;
                }
                $(".datepicker-cilck").remove();
                $(".datepicker-click-divtop").remove();

                if($.trim($(this).text()).length > 0) {
                    var DateGL = new Date();
                    DateGL.setYear(Number($("#years").text()));
                    DateGL.setMonth(Number($(".datepicker_top_left").text()) - 1);
                    DateGL.setDate($(this).text());

                    var div = $("<div class='datepicker-cilck'></div>");
                    div.css("top", $(this).offset().top + 23);
                    div.css("z-index", parseInt(new Date().getTime() / 1000));
                    $("body").append(div);

                    var width = 230;

                    var divtop = $("<div class='datepicker-click-divtop'>&nbsp;</img></div>");
                    divtop.css("top", div.offset().top - 6);

                    if(width - 16 - $(this).position().left < 73) {
                        var number = width - 16 - $(this).position().left;
                        var num = 73 - number;
                        div.css("left", $(this).offset().left - num);
                        divtop.css("left", div.offset().left + num);
                    } else {
                        div.css("left", $(this).offset().left);
                        divtop.css("left", div.offset().left);
                    }

                    divtop.css("z-index", parseInt(new Date().getTime() / 1000));
                    $("body").append(divtop);
                    var divleft = $("<div class='datepicker-cilck-left'></div>");
                    div.append(divleft);
                    var divcenter = $("<div class='datepicker-cilck-center'></div>");
                    var p = $("<span>" + CnMonthofDate(DateGL) + CnDayofDate(DateGL) + SolarTerm(DateGL) + "</span>");
                    p.css("height", "20px");
                    divcenter.css("padding-top", "8px");
                    divcenter.append(p);
                    div.append(divcenter);
                    var divright = $("<div class='datepicker-cilck-right'></div>");
                    div.append(divright);
                }
            });
            tr.append(td);
        }
    }
}