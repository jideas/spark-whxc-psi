var QueryClient = function() {
};
QueryClient.handleActionPerformed = function(event, widget) {
	var eventData = event.getEventData();
	if(eventData.actionName == "ViewDetail") {
		eventData.actionValue = (eventData.x - 10) + ":" + (eventData.y+25);
	}
};
