function clearText() {
   document.getElementById("textInput").value = "";
   saveNote(document.getElementById("textInput"));
}

function saveNote(obj) {
    var noteText = obj.value;
    var note = {
        text : noteText,
        oper : "set"
    };
    window.top.toServer("note", note);
}

function checklength(obj) {
    var max = obj.maxlength;
    var hasInput = $.trim(obj.value).length;
    if(max == null || max == "" || max == undefined) {
        max = 139;
    }
    var number = max - hasInput;
    if(number < 1) {
        $("#may_input_text_note_count").html(0);
    } else {
        $("#may_input_text_note_count").html(max - hasInput);
    }

    if(hasInput >= max) {
        obj.value = $.trim(obj.value).substring(0, max);
        return false;
    }
}