desktop = {
    createContent : function(JQParent) {
        var area = $("<div>", {
            "class" : "saas_tool_window_bg"
        }); 
        this.createIconSort(area);
        this.createTheme(area);
        return area;
    },
    createIconSort : function(parent) {
        var $div = $("<div>");
        $div.css("height", "125px");

        var $title = $("<div>", {
            "class" : "tool_title_bg"
        });
        var icon = $("<span>", {
            "class" : "tool_title_flag"
        });
        var text = $("<label>", {
            "class" : "tool_title_text"
        }).text("排列图标");
        $title.append(icon).append(text);

        $div.append($title);

        var sortType = $("<div>");
        sortType.css("margin", "11px 6px 11px 6px");
        sortType.css("height", "78px");
        sortType.css("overflow", "hidden");
        $div.append(sortType);

        this.createSortType(sortType, "app/theme/common/sort/sort_h.png", "横向排列", function() {
            $(".saas_sort_border").find(".saas_sortflag").remove();
            $(".saas_sort_border").find(".sorttext").css("color", "#ADB4BB");
            var checked = $("<div>", {
                "class" : "saas_sortflag"
            })
            $(this).append(checked);
            $(this).find(".sorttext").css("color", "#EFEFEF");
            orderImage("crosswise", 8);
        });
        this.createSortType(sortType, "app/theme/common/sort/sort_s.png", "纵向排列", function() {
            $(".saas_sort_border").find(".saas_sortflag").remove();
            $(".saas_sort_border").find(".sorttext").css("color", "#ADB4BB");
            var checked = $("<div>", {
                "class" : "saas_sortflag"
            })
            $(this).append(checked);
            $(this).find(".sorttext").css("color", "#EFEFEF");
            orderImage("lengthways", 8);
        });
        parent.append($div);
    },
    createSortType : function(parent, imgn, text, func) {
        var $Div = $("<div>", {
            "class" : "saas_sort_border"
        });
        $icon = $("<div>", {
            "class" : "icon"
        });
        $icon.css("width", 54);
        $icon.css("height", 41);
        $icon.css("margin", " 10px 0px 0px 17px");
        $icon.css("background-repeat", "no-repeat");
        $icon.css("background-image", "url(" + imgn + ")");
        $Div.css("cursor", "pointer");
        $Div.append($icon);

        var $text = $("<div>", {
            "class" : "sorttext"
        }).text(text);
        $text.css("width", "55px");
        $text.css("margin-left", "16px");
        $text.css("margin-top", "3px");
        $text.css("float", "left");
        $text.css("font-family", "宋体");
        $text.css("font-size", "12px");
        $text.css("color", "#ADB4BB");
        $Div.append($text);
        $Div.click(func);

        $Div.hover(function() {
            $(this).removeClass("saas_sort_border");
            $(this).addClass("saas_sort_border_hover");
        }, function() {
            $(this).removeClass("saas_sort_border_hover");
            $(this).addClass("saas_sort_border");
        })

        parent.append($Div);
    },
    createTheme : function(parent) {

        var $div = $("<div>");
        $div.css("height", "300px");

        var $title = $("<div>", {
            "class" : "tool_title_bg"
        });
        var icon = $("<span>", {
            "class" : "tool_title_flag"
        });
        var text = $("<span>", {
            "class" : "tool_title_text"
        }).text("切换主题");
        $title.append(icon).append(text);

        $div.append($title);

        var $contentDiv = $("<div>");
        $contentDiv.css("overflow", "auto");
        $div.append($contentDiv);

        var $themeType = $("<div>", {
            "class" : "saas_theme_type"
        });
        var $themeBody = $("<div>", {
            "class" : "saas_theme_body"
        });
        $themeBody.css("overflow", "hidden");
        $themeBody.css("margin", "10px 5px 10px 5px");

        var imageArr = ["app/theme/blue/images/bg/blue_bg0.jpg", "app/theme/blue/images/bg/blue_bg1.jpg", "app/theme/blue/images/bg/blue_bg2.jpg", "app/theme/blue/images/bg/blue_bg3.jpg", "app/theme/blue/images/bg/blue_bg4.jpg", "app/theme/blue/images/bg/blue_bg5.jpg"];
        var minImageArr = ["app/theme/blue/images/minbg/blue_bg0.png", "app/theme/blue/images/minbg/blue_bg1.png", "app/theme/blue/images/minbg/blue_bg2.png", "app/theme/blue/images/minbg/blue_bg3.png", "app/theme/blue/images/minbg/blue_bg4.png", "app/theme/blue/images/minbg/blue_bg5.png"];
        var bgImageName = ["美女背景", "风景背景", "鲜花背景", "风景背景", "风景背景", "风景背景"];
        var themeDiv0 = $("<div>", {
            "class" : "saas_theme_div"
        });
        themeDiv0.attr("id", "saas_theme_blue");
        if(MainProperty.style.indexOf("blue") == -1) {
            themeDiv0.css("display", "none");
        }
        $themeBody.append(themeDiv0);
        //themeDiv0.css("border","1px solid red");

        this.createThemeType($themeType, themeDiv0, "梦幻光影", 110, "blue", imageArr, minImageArr, bgImageName, function() {
            $(".saas_theme_type_title").find(".saas_theme_type_title_text").css("color", "#97999E");
            $(".saas_theme_type_title").find(".saas_theme_type_flag").remove();
            $(this).find(".saas_theme_type_title_text").css("color", "#83B43F");
            var flag = $("<div>", {
                "class" : "saas_theme_type_flag"
            });
            $(this).append(flag);
            $themeType.css("background-position", "0px 0px");
            $(".saas_theme_div").css("display", "none");
            $(".saas_theme_body").find("#saas_theme_blue").css("display", "block");
        });
        var imageArr1 = ["app/theme/qing/images/bg/qing_bg0.jpg", "app/theme/qing/images/bg/qing_bg1.jpg", "app/theme/qing/images/bg/qing_bg2.jpg", "app/theme/qing/images/bg/qing_bg3.jpg", "app/theme/qing/images/bg/qing_bg4.jpg", "app/theme/qing/images/bg/qing_bg5.jpg"];
        var minImageArr1 = ["app/theme/qing/images/minbg/qing_bg0.jpg", "app/theme/qing/images/minbg/qing_bg1.jpg", "app/theme/qing/images/minbg/qing_bg2.jpg", "app/theme/qing/images/minbg/qing_bg3.jpg", "app/theme/qing/images/minbg/qing_bg4.jpg", "app/theme/qing/images/minbg/qing_bg5.jpg"];
        var bgImageName1 = ["美女背景", "风景背景", "鲜花背景", "风景背景", "风景背景", "美女背景"];
        var themeDiv1 = $("<div>", {
            "class" : "saas_theme_div"
        });
        if(MainProperty.style.indexOf("qing") == -1) {
            themeDiv1.css("display", "none");
        }
        themeDiv1.attr("id", "saas_theme_qing");
        $themeBody.append(themeDiv1);
        this.createThemeType($themeType, themeDiv1, "青青世界", 110, "qing", imageArr1, minImageArr1, bgImageName1, function() {
            /*  $(".saas_theme_type_title").find(".saas_theme_type_title_text").css("color","#97999E");
             $(".saas_theme_type_title").find(".saas_theme_type_flag").remove();
             $(this).find(".saas_theme_type_title_text").css("color","#83B43F");
             var flag=$("<div>",{"class":"saas_theme_type_flag"});
             $(this).append(flag);
             $themeType.css("background-position","0px -33px");
             $(".saas_theme_div").css("display","none");
             $(".saas_theme_body").find("#saas_theme_qing").css("display","block");
             */
        });
        var imageArr2 = ["app/theme/feng/images/bg/feng_bg0.jpg", "app/theme/feng/images/bg/feng_bg1.jpg", "app/theme/feng/images/bg/feng_bg2.jpg", "app/theme/feng/images/bg/feng_bg3.jpg", "app/theme/feng/images/bg/feng_bg4.jpg", "app/theme/feng/images/bg/feng_bg5.jpg"];
        var minImageArr2 = ["app/theme/feng/images/minbg/feng_bg0.jpg", "app/theme/feng/images/minbg/feng_bg1.jpg", "app/theme/feng/images/minbg/feng_bg2.jpg", "app/theme/feng/images/minbg/feng_bg3.jpg", "app/theme/feng/images/minbg/feng_bg4.jpg", "app/theme/feng/images/minbg/feng_bg5.jpg"];
        var bgImageName2 = ["美女背景", "风景背景", "鲜花背景", "风景背景", "风景背景", "风景背景"];
        var themeDiv2 = $("<div>", {
            "class" : "saas_theme_div"
        });
        if(MainProperty.style.indexOf("feng") == -1) {
            themeDiv2.css("display", "none");
        }
        themeDiv2.attr("id", "saas_theme_feng");
        $themeBody.append(themeDiv2);
        this.createThemeType($themeType, themeDiv2, "粉红之夜", 100, "feng", imageArr2, minImageArr2, bgImageName2, function() {
            /*
             $(".saas_theme_type_title").find(".saas_theme_type_title_text").css("color","#97999E");
             $(".saas_theme_type_title").find(".saas_theme_type_flag").remove();
             $(this).find(".saas_theme_type_title_text").css("color","#83B43F");
             var flag=$("<div>",{"class":"saas_theme_type_flag"});
             $(this).append(flag);
             $themeType.css("background-position","0px -66px");
             //$(".saas_theme_div").css("display","none");
             //$(".saas_theme_body").find("#saas_theme_feng").css("display","block");
             */
        });
        $contentDiv.append($themeType);
        $contentDiv.append($themeBody);
        parent.append($div);
    },
    createThemeType : function(parent, preViewDiv, name, width, typeName, imageArr, minImageArr, bgImageName, func) {
        var type = $("<div>", {
            "class" : "saas_theme_type_title"
        });
        type.css("float", "left");
        type.css("position", "relative");
        type.css("width", width);
        type.css("height", "34px");
        var text = $("<div>", {
            "class" : "saas_theme_type_title_text"
        }).text(name);
        text.css("color", "#97999E");
        text.css("font-family", "宋体;");
        text.css("font-size", "13px");
        text.css("width", "60px");
        text.css("margin-top", "13px");
        text.css("margin-left", "13px");
        text.css("float", "left");
        text.css("display", "block");
        text.css("overflow", "hidden");

        type.append(text);
        type.click(func);
        parent.append(type);
        for(var i = 0; i < imageArr.length; i++) {
            this.createPreviewImage(preViewDiv, minImageArr[i], bgImageName[i], typeName, i, function(i) {
                $(this).find(".themeFlageIcon").remove();
                FlagSelectThemeBg($(this))
                $(".saas_theme_preview_name").css("color", "#AEB5BD");
                $(this).find(".saas_theme_preview_name").css("color", "#ffffff");
            });
        }
        if(MainProperty.style.indexOf(typeName) > -1) {
            type.click();
        }
    },
    createPreviewImage : function(parent, src, picName, typeName, index, func) {
        var outDiv = $("<div>", {
            "class" : "saas_theme_preview_Div"
        });
        outDiv.css("cursor", "pointer");
        outDiv.css("width", 100);
        outDiv.css("height", 90);
        outDiv.css("float", "left");
        outDiv.css("overflow", "hidden");
        //outDiv.css("border","1px solid red");
        outDiv.css("position", "relative");
        outDiv.css("margin", "10px 2px 10px 2px");

        var $Div = $("<div>", {
            "class" : "saas_theme_border"
        });
        $Div.css("width", "100px");
        $Div.css("height", "90px");
        $Div.css("border", "1px hidden");
        outDiv.append($Div);
        $icon = $("<img>");
        $icon.css("width", "90px");
        $icon.css("height", "65px");
        $icon.css("top", "2px");
        $icon.css("left", "5px");
        $icon.css("margin", "3px");
        $icon.css("z-index", "-1");
        $Div.css("position", "absolute");
        $icon.attr("src", src);
        outDiv.append($icon);

        var $text = $("<span>", {
            "class" : "saas_theme_preview_name"
        });
        $text.text(picName);
        $text.css("position", "absolute");
        $text.css("height", "12px");
        $text.css("width", "65px");
        $text.css("top", "72px");
        $text.css("left", "10px");
        $text.css("text-align", "left");
        $text.css("font-size", "12px");
        $text.css("font-family", "宋体;");
        $text.css("color", "#AEB5BD");
        $Div.append($text);
        $Div.click(func);
        $Div.click(function() {
            changeService(typeName, "_bg" + index);
            themeDoSave(typeName+"_bg"+index);
        });
        $Div.hover(function() {
            $(this).removeClass("saas_theme_border");
            $(this).addClass("saas_theme_border_hover");
        }, function() {
            $(this).removeClass("saas_theme_border_hover");
            $(this).addClass("saas_theme_border");
        });
        if(src.indexOf(MainProperty.style) > 0) {
            FlagSelectThemeBg($Div)
            $text.css("color", "#ffffff");
        }
        parent.append(outDiv);
    }
};
//标志被选中的图片
function FlagSelectThemeBg(parent) {
    $(".saas_themeFlageIcon").remove();
    var img = $("<div>", {
        "class" : "saas_themeFlageIcon"
    });
    img.css("position", "absolute");
    img.css("top", "72px");
    img.css("left", "75px");
    parent.append(img);
}

function changeService(type, pic) {
    var src = type + "/images/bg/" + type + pic + ".jpg";
    var bgSrc = "app/theme/" + src;
    if($(".main_img").length > 0) {
        $(".main_img").attr("src", bgSrc);
    }
}

function themeDoSave(themeStyleValue) {
    var theme = {
        "style" : themeStyleValue
    };
    toServer("theme", theme);
}

/**
 * 排序的方法
 */
function orderImage(type, nums) {
    if($(".bigImg").length < 1) {
        return;
    }
    var keys = $(".bigImg");
    var $width = $(window).width();
    var $height = $(window).height();
    //默认位置为左上角
    var startX = 300;
    var x = startX;
    var y = 100;
    var number = $(".bigImg").length;
    var count = 1;
    var ordernumber = false;
    var toServerArray = new Array();
    for(var i = 0; i < keys.length; i++) {
        var bigImg = $(keys[i]);
        //组织数据，和后台交互保存坐标
        var returnObj = {
            "code" : bigImg.data("imgfunction").code,
            "x" : x,
            "y" : y
        }
        toServerArray.push(returnObj);

        //把移动之后的坐标设置到array里面去，不然移动的时候做碰撞会出问题
        for(var j = 0; j < MainProperty.arrayfucicons.length; j++) {
            var imgfunction = MainProperty.arrayfucicons[j];
            if(imgfunction.code == bigImg.data("imgfunction").code) {
                imgfunction.x = x;
                imgfunction.y = y;
            }
        }

        //做移动的动作
        bigImg.stop();
        bigImg.animate({
            left : x + "px",
            top : y + "px"
        }, 1000, "swing");
        if(type == "crosswise") {
            //横向排列
            if(number < nums * 2 && number > nums) {
                if(ordernumber) {
                    if(x + 200 > $width) {
                        x = startX;
                        y += 200;
                        ordernumber = false;
                    } else {
                        x += 100;
                        y -= 100;
                        ordernumber = false;
                    }
                } else {
                    y += 100;
                    ordernumber = true;
                }
            } else {
                x += 100;
                if($width < (x + 100) || count == nums) {
                    //撞墙换行
                    y += 100;
                    x = startX;
                    count = 0;
                }
            }
        } else {
            //纵向排列
            y += 100;
            if($height < (y + 150)) {
                y = 100;
                x += 100;
            }
        }
        count++;

    }
    var openWindowsjsonObject = {
        "toServerArray" : toServerArray
    }
    toServer("openWindowsjsonObject", openWindowsjsonObject);
}