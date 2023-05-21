$(document).ready(function () {
    selectAll()

    $("#addCategoryBtn").click(function () {
        resetAForm()
        $("#addModal").modal("show");
    })

    $("#addSaveBtn").click(function () {
        let categoryName = $("#categoryName").val()
        $.ajax({
            type: "POST",
            url: "/books/category/add",
            data: {
                name: categoryName
            },
            success: function (res) {
                if (res.code == 200) {
                    console.log("添加成功")
                    selectAll()
                    $("#addModal").modal("hide");
                } else {
                    console.log("添加失败")
                }
            },
            error: function () {
                console.log("添加失败")
            }
        })
    })

    function resetAForm() {
        $("#categoryName").val('')
    }

    function selectAll() {
        $.ajax({
            url: "/books/category/all",
            type: "POST",
            success: function (res) {
                if (res.code == 200) {
                    let data = res.data
                    console.log(data)
                    $.each(data, function (index, v) {
                        let e = `<div class="card col-md-3 shadow">
                                    <div class="card-body">
                                        <h4 class="card-title mb-3 my-title">${v.name}</h4>
                                        <p class="card-text center-block">共有<span class="desc-text">${v.count}</span>本书</p>
                                        <a href="./books.html" class="btn btn-success btn-sm center-block">查看书籍</a>
                                    </div>
                                </div>`
                        $("#categoryListRow").append(e)
                    })
                }
            }
        })
    }

})