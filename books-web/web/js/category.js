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
                    $.each(data, function (index, v) {
                        let the = createColor()
                        let i = Math.floor(Math.random() * 3 + 1)
                        let s = (index + 1) < 10 ? '0' + (index + 1) : (index + 1)
                        let e = `<div class="drop child${i}" style="--clr: ${the.color};--cf: ${the.fontColor};">
                                    <div class="content">
                                        <h2>${s}</h2>
                                        <p class="p-title">${v.name}
                                        </p>
                                        <p>共有<span class="desc-text">${v.count}</span>本书</p>
                                        <a href="./books.html?c=${v.id}">去查看</a>
                                    </div>
                                </div>`
                        $("#categoryListRow").append(e)
                    })
                }
            }
        })
    }

    function createColor() {
        const s = '0123456789abcdef'
        let color = '#'
        let fontColor = '#'
        for (let i = 0; i < 6; i++) {
            let index = Math.floor(Math.random() * 16)
            color += s[index]
            fontColor += s[15 - index]
        }
        return {
            color: color,
            fontColor: fontColor
        }
    }

})