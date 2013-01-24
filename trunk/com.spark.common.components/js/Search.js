/**
 * 定位工具使用的js
 * 具体策略是通过发消息的方式进行数据传递
 * 控件会发起一个消息，让他们的父容器获取到，然后父容器通过find（）方法找到相应的控件
 * 不直接设置table来获取数据时因为table如果和定位控件在一个层级下面的话,有可能会收不到信息
 * 
 */
var Search = function() {}

/**
 * 定位
 */
Search.handleServerNotify = function(event,widget) {
    var text = widget.getCustomObject("text").name;
    //发消息给父容器
    widget.bubbleMessage("find",text);
};

/**
 * 接受search控件发过来的消息，用于查找到stable之后，定位
 */
Search.getMessage = function(event,widget) {
    var message = event.getMessage();
    event.getTransmitter().terminate();
    var Stable = widget.find("STable_MainTable");
    Stable.findText(message);
}

/**
 * 接受上一个，下一个的label发过来的信息，用于查找到stable之后，切换颜色
 */
Search.changColor = function(event,widget) {
    var message = event.getMessage();
    event.getTransmitter().terminate();
    if(message == "findNext") {
        var findtext = widget.getData("findText");
        findtext.arrayNumber += 1;
    }else {
        var findtext = widget.getData("findText");
        findtext.arrayNumber -= 1;
    }
    var Stable = widget.find("STable_MainTable");
    Search.setText(widget);
    Stable.changeColor(widget.getData("findText").arrayText,widget.getData("findText").arrayNumber);
}

/**
 * 接受stable发过来的消息
 */
Search.getStableMessage = function(event,widget) {
    var message = event.getMessage();
    widget.setData("findText",message);
    event.getTransmitter().terminate();
    Search.setText(widget);
}

/**
 * 修改定位的文字描述
 */
Search.setText = function(widget) {
    var message = widget.getData("findText");
    var textComposite = widget.find("textComposite",3);
    var labelName = textComposite.find("labelName");
    var arrayTextNumber = message.arrayText == null ? 0:message.arrayText.length;
    var arrayNumber = arrayTextNumber == 0 ? 0 : message.arrayNumber+1;
    labelName.setText("共找到于\""+message.textName+"\"相关信息"+arrayTextNumber+"条,当前为第 "+arrayNumber+" 条");
    var labelBefore = textComposite.find("labelBefore");
    var labelNext = textComposite.find("labelNext");
    if(message.arrayNumber == 0) {
        labelBefore.setEnabled(false);
    }else {
        labelBefore.setEnabled(true);
    }
    if(arrayNumber == arrayTextNumber) {
        labelNext.setEnabled(false);
    }else {
        labelNext.setEnabled(true);
    }
    textComposite.setVisible(true);
    textComposite.layout();
}

/**
 * 下一个
 */
Search.findNext = function(event,widget) {
    widget.bubbleMessage("changColor","findNext");
}

/**
 * 上一个
 */
Search.findBefore = function(event,widget) {
    widget.bubbleMessage("changColor","findBefore");
}
