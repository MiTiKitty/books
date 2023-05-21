$(document).ready(() => {
    $("#loginBtn").click(() => {
        let username = $("#username").val();
        let password = $("#inputPassword").val();
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
})