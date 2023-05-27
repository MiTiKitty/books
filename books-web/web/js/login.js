$(document).ready(() => {
    $("#loginBtn").click(() => {
        let username = $("#username").val();
        let password = $("#inputPassword").val();
        password = encrypt(password)
        $.ajax({
            type: "POST",
            url: "/books/users/login",
            data: {
                username: username,
                password: password
            },
            dataType: "json",
            success: (data) => {
                window.location.href = "/books/home.html";
            },
            error: (jqXHR) => {
                console.log(jqXHR.status, jqXHR.responseText)
            }
        })
    })

    function encrypt(password) {
        let encrypted = '';
        for (let i = 0; i < password.length; i++) {
            let num = password.charCodeAt(i);
            num = num + 3; // 加密算法：将每个字符的Unicode码值加上3
            encrypted += String.fromCharCode(num);
        }
        return encrypted;
    }
})