function check_login() {
  var name = $("#employeeName").val();
  var pass = $("#employeePassword").val();
  if (name == "" && pass == "") {
    $("#login_content").removeClass('shake_effect');
    setTimeout(function () {
      $("#login_content").addClass('shake_effect')
    }, 1);
  }
  else {
    $.ajax({
      type: "post",
      url: "http://localhost:8888/Employee/employee/login.action",
      async: true,
      dataType: "json",
      data: $("#login_form").serialize(),
      success: function (message) {
        if (message.returnStatus == 1) {
          window.location.href = "index.html";
        } else if (message.returnStatus == -1) {
          alert(alert(message.rspMsg));
        }
      }
    });
  }
}
$(function () {
  $("#login").click(function () {
    check_login();
    return false;
  })
})