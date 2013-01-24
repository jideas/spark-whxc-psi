<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<META HTTP-EQUIV="pragma" CONTENT="public">
		<META HTTP-EQUIV="Cache-Control" CONTENT="max-age=2592000">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <script type="text/javascript" src="../common/jquery.js"></script>
        <script type="text/javascript" src="note.js"></script>
        <link rel="stylesheet" type="text/css" href="main.css" />
    </head>
    <body style="width:95%;height:95%;background-color:transparent; " unselectable="on"  style="-moz-user-select:none;" oncontextmenu=self.event.returnValue=false>
        <div id="note_outer_div">
            <!-- top   -->
            <div class="note_left_top"></div>
            <div class="note_center_top"></div>
            <div class="note_right_top"></div>
            <!-- center  -->
            <div id="note_left_center"></div>
            <div id="note_center_center">
                <textarea rows="8" cols="35" id="textInput" onKeyUp="checklength(this);"  maxlength="139" onBlur="saveNote(this)" ></textarea>
                <div id="note_div">
                    <div id="may_input_text_note_left">
                         您还可以输入
                    </div>
                    <div id="may_input_text_note_count">
                        139
                    </div>
                    <div id="may_input_text_note_right">
                         个字符
                    </div>
                </div>
                <div id="clear">
                    <a href="javascript:clearText();" hidefocus="hidefocus">清空</a>
                </div>
            </div>
            <div id="note_right_center"></div>
            <div class="layout"></div>
            <br />
            <div id="note_left_bottom"></div>
            <div id="note_center_bottom"></div>
            <div id="note_right_bottom"></div>
        </div>
        <script>
            var text = '<%=request.getParameter("notetext")%>';
            if(text != "null" && text != null) {
                $("#textInput").text(text);
            }
        </script>
    </body>
</html>