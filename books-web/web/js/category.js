$(document).ready(function () {
    let data = [{
        id: 1,
        name: '小说',
        count: 13
    },
        {
            id: 2,
            name: '杂志',
            count: 12
        }, {
            id: 3,
            name: '历史',
            count: 22
        }]
    $.each(data, function (index, v) {
        let e = `<div class="card col-md-3 shadow">
                    <div class="card-body">
                        <h4 class="card-title mb-3 my-title">${v.name}</h4>
                        <p class="card-text">共有<span class="desc-text">${v.count}</span>本书</p>
                        <a href="./books.html" class="btn btn-success btn-sm center-block">查看书籍</a>
                    </div>
                </div>`
        $("#categoryListRow").append(e)
    })

    $("#addCategoryBtn").click(function () {
        resetAForm()
        $("#addModal").modal("show");
    })

    $("#addSaveBtn").click(function () {
        let categoryName = $("#categoryName").val()
        $.ajax({
            type: "POST",
            url: "/admin/category/add",
            data: {
                name: categoryName
            },
            success: function (res) {
                console.log("添加成功")
                $("#addModal").modal("hide");
            },
            error: function () {
                console.log("添加失败")
            }
        })
    })

    function resetAForm() {
        $("#categoryName").val('')
    }

})