function messageText() {

	var messageNow = document.getElementById("message").value;
	var firstName = document.getElementById("fname").value;
	var lastName = document.getElementById("lname").value;
	var college = "Administration Department,\nMarimallappa's Junior College";
	var gender;
	// var now = new Date().getFullYear();

	if (document.getElementById('male').checked) {
		gender = "son";
	} else if (document.getElementById('female').checked) {
		gender = "daughter";
	}
	var subjects = "";
	var selObj = document.getElementById('subjects');

	for (var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected) {
			subjects += selObj.options[i].value + "\n";
		}
	}
	var messageContent = "Dear Parent,\n\n" + "Your " + gender + "," + firstName + " " + lastName + " is facing a shortage in attendance for this academic year in the following subjects:\n\n" + subjects + "\nPlease visit our college and meet the concerned teaching faculty as soon as possible.\n\n" + "Thanks,\n" + college;
	var messageId = document.getElementById("message");
	messageId.value = messageContent;
	// messageId.value = "This is a Test Message";

}

document.addEventListener('DOMContentLoaded', function() {
	var btn = document.getElementById('Fill_Messsage');
	if (btn) {
		btn.addEventListener('click', message);
	}
});