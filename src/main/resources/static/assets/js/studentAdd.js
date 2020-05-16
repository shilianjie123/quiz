// function doRegister() {
//     var number = $("#number").val();
//     var name = $("#name").val();
//     var account = $("#account").val();
//     var password = $("#password").val();
//     var email = $("#email").val();
//     if (number == "" || name == "" || account == "" || password == "" || email == "") {
//         layer.msg("属性不能为空！", {time: 2000, icon: 5, shift: 6}, function () {
//         });
//         return;
//     }
//
//     var loadingIndex;
//     $.post({
//         type: "POST",
//         url: "/prexam/AddStudent",
//         data: {
//             "registerNumber": number,
//             "studentName": name,
//             "studentAccount": account,
//             "studentGender": $("input[type='radio']:checked").val(),
//             "studentEmail": email,
//             "studentPwd": password,
//             "classeId": $("#classe").val()
//         },
//         //加载图标
//         beforeSend: function () {
//             loadingIndex = layer.msg('处理中', {icon: 16});
//         },
//         success: function (result) {
//             console.log(result);
//             layer.close(loadingIndex);
//             if (result.success) {
//                 window.location.href = "foreLogin";
//             } else {
//                 layer.msg("验证码不存在或输入错误！", {
//                     time: 2000,
//                     icon: 5,
//                     shift: 6
//                 }, function () {
//                 });
//             }
//         }
//     });
//     return false;
// }