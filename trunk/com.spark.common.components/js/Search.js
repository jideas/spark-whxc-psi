/**
 * ��λ����ʹ�õ�js
 * ���������ͨ������Ϣ�ķ�ʽ�������ݴ���
 * �ؼ��ᷢ��һ����Ϣ�������ǵĸ�������ȡ����Ȼ������ͨ��find���������ҵ���Ӧ�Ŀؼ�
 * ��ֱ������table����ȡ����ʱ��Ϊtable����Ͷ�λ�ؼ���һ���㼶����Ļ�,�п��ܻ��ղ�����Ϣ
 * 
 */
var Search = function() {}

/**
 * ��λ
 */
Search.handleServerNotify = function(event,widget) {
    var text = widget.getCustomObject("text").name;
    //����Ϣ��������
    widget.bubbleMessage("find",text);
};

/**
 * ����search�ؼ�����������Ϣ�����ڲ��ҵ�stable֮�󣬶�λ
 */
Search.getMessage = function(event,widget) {
    var message = event.getMessage();
    event.getTransmitter().terminate();
    var Stable = widget.find("STable_MainTable");
    Stable.findText(message);
}

/**
 * ������һ������һ����label����������Ϣ�����ڲ��ҵ�stable֮���л���ɫ
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
 * ����stable����������Ϣ
 */
Search.getStableMessage = function(event,widget) {
    var message = event.getMessage();
    widget.setData("findText",message);
    event.getTransmitter().terminate();
    Search.setText(widget);
}

/**
 * �޸Ķ�λ����������
 */
Search.setText = function(widget) {
    var message = widget.getData("findText");
    var textComposite = widget.find("textComposite",3);
    var labelName = textComposite.find("labelName");
    var arrayTextNumber = message.arrayText == null ? 0:message.arrayText.length;
    var arrayNumber = arrayTextNumber == 0 ? 0 : message.arrayNumber+1;
    labelName.setText("���ҵ���\""+message.textName+"\"�����Ϣ"+arrayTextNumber+"��,��ǰΪ�� "+arrayNumber+" ��");
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
 * ��һ��
 */
Search.findNext = function(event,widget) {
    widget.bubbleMessage("changColor","findNext");
}

/**
 * ��һ��
 */
Search.findBefore = function(event,widget) {
    widget.bubbleMessage("changColor","findBefore");
}
