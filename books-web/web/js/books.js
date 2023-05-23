let theTotalPage = 1;

$(document).ready(function () {
    searchCategory()
    searchPage()

    $('#editBookCover').on('blur', () => {
        let coverUrl = $('#editBookCover').val()
        $('#show-cover').attr('src', coverUrl)
    })

    $('#abookCover').on('blur', () => {
        let coverUrl = $('#abookCover').val()
        $('#add-show-cover').attr('src', coverUrl)
    })

    // 发起编辑修改请求
    $('#editSaveBtn').click(function () {
        let box = $('#editTags input[type=checkbox]')
        let c = ''
        for (let i = 0; i < box.length; i++) {
            if (box[i].checked) {
                c += box[i].value + '&'
            }
        }
        if (c.trim().length === 0) {
            alert('请选择分类');
            return;
        }
        var id = $("#editBookId").val()
        var coverUrl = $("#editBookCover").val()
        var bookName = $('#bookName').val();
        var author = $('#author').val();
        var publisher = $('#publisher').val();
        var date = $('#date').val();
        var isbn = $('#isbn').val();
        var price = $('#price').val();
        var stock = $('#stock').val();
        let currentStock = $('#currentStock').val();
        if (parseInt(stock) + parseInt(currentStock) < 0) {
            alert('库存不足供减少，请重新输入');
            return;
        }
        $.ajax({
            url: "/books/book/edit",
            type: "POST",
            data: {
                id: id,
                title: bookName,
                coverUrl: coverUrl,
                author: author,
                publisher: publisher,
                publicationDate: date,
                isbn: isbn,
                price: price,
                c: c,
                stock: stock
            },
            success: function (response) {
                if (response.code == 200) {
                    alert('修改成功');
                    $('#editModal').modal('hide');
                } else {
                    alert('修改失败');
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    })

    // 重置编辑表单
    function resetForm() {
        $('#bookName').val('');
        $('#author').val('');
        $('#editBookCover').val('');
        $('#editBookId').val('');
        $('#publisher').val('');
        $('#date').val('');
        $('#isbn').val('');
        $('#price').val('');
        $('#total').val('');
        $('#stock').val('');
        $('#editTags input[type=checkbox]').prop('checked', false);
    }

    // 给添加图书按钮绑定监听事件
    $('#addBookBtn').click(function () {
        resetAForm()
        $('#addModal').modal('show');
    });

    // 发起添加图书请求
    $('#addSaveBtn').click(function () {
        let box = $('input[type=checkbox][name=category]')
        let c = ''
        for (let i = 0; i < box.length; i++) {
            if (box[i].checked) {
                c += box[i].value + '&'
            }
        }
        if (c.trim().length === 0) {
            alert('请选择分类');
            return;
        }
        $.ajax({
            url: '/books/book/add',
            type: 'POST',
            data: {
                title: $('#abookName').val(),
                coverUrl: $('#abookCover').val(),
                author: $('#aauthor').val(),
                publisher: $('#apublisher').val(),
                publicationDate: $('#adate').val(),
                isbn: $('#aisbn').val(),
                price: $('#aprice').val(),
                total: $('#atotal').val(),
                c: c
            },
            success: function (data) {
                alert('添加成功');
                $('#addModal').modal('hide');
            },
            error: function (xhr, status, error) {
                alert('添加失败');
            }
        });
    });

    function resetAForm() {
        $('#abookName').val('');
        $('#aauthor').val('');
        $('#abookCover').val('');
        $('#apublisher').val('');
        $('#adate').val('');
        $('#aisbn').val('');
        $('#aprice').val('');
        $('#atotal').val('');
        $('#add-show-cover').attr('src', '');
        $('#addTags input[type=checkbox]').prop('checked', false);
    }

    $('#searchName').on('input', function () {
        var input = $(this).val();
        $('tbody tr').each(function () {
            var name = $(this).find('td:nth-child(3)').text();
            if (name.indexOf(input) === -1) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });

    $('#searchIsbn').on('input', function () {
        var input = $(this).val();
        $('tbody tr').each(function () {
            var isbn = $(this).find('td:nth-child(7)').text();
            if (isbn.indexOf(input) === -1) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });

    $('#searchBookBtn').click(function () {
        searchPage()
    });

    // 发起搜索请求
    function searchPage(is=true) {
        $.ajax({
            url: '/books/book/search',
            type: 'POST',
            data: {
                pageNo: is ? 1 : $('#pageNo').val(),
                name: $('#searchName').val(),
                isbn: $('#searchIsbn').val(),
                category: $('#bookCategory').val() == 'all' ? null : $('#bookCategory').val()
            },
            dataType: 'json',
            success: function (rep) {
                if (rep.code == 200) {
                    let data = rep.data.data
                    var table = $("#dataTable tbody");
                    $("#dataTable tbody tr").remove();
                    $.each(data, function (index, value) {
                        var row = $("<tr class='my-tr'>");
                        row.append($("<th scope='row' style='line-height: 120px;'>").text(index + 1));
                        row.append($("<td>").append($("<img class=\"cover-img\">").attr("src", (value.coverUrl ? value.coverUrl : './img/defaultBookCover.jpg'))));
                        row.append($("<td>").text(value.title));
                        row.append($("<td>").text(value.author));
                        row.append($("<td>").text(value.publisher));
                        row.append($("<td>").text(formatDate(value.publicationDate)));
                        row.append($("<td>").text(value.isbn));
                        row.append($("<td>").text(value.price));
                        row.append($("<td>").text(value.total));
                        row.append($("<td>").text(value.currentStock));
                        var btnGroup = $("<div class='my-btn-group'>");
                        var editBtn = $("<button type='button' class='btn btn-primary' value='" + value.id + "'>").text("编辑");
                        var deleteBtn = $("<button type='button' class='btn btn-danger' value='" + value.id + "'>").text("删除");
                        var btnGroupDiv = $("<div class='btn-group my-btn'>");
                        btnGroupDiv.append(editBtn);
                        btnGroup.append(btnGroupDiv);
                        btnGroupDiv = $("<div class='btn-group my-btn'>");
                        btnGroupDiv.append(deleteBtn);
                        btnGroup.append(btnGroupDiv);
                        row.append($("<td>").append(btnGroup));

                        // 发起编辑请求
                        editBtn.click(function () {
                            resetForm();
                            $.ajax({
                                url: "/books/book/info",
                                type: "GET",
                                data: {id: value.id},
                                success: function (rep) {
                                    if (rep.code === 200) {
                                        let theP = rep.data
                                        let response = theP.book
                                        $('#editBookId').val(response.id);
                                        $('#editBookCover').val(response.coverUrl);
                                        $('#show-cover').attr('src', response.coverUrl);
                                        $('#bookName').val(response.title);
                                        $('#author').val(response.author);
                                        $('#publisher').val(response.publisher);
                                        $('#date').val(formatDate(response.publicationDate));
                                        $('#isbn').val(response.isbn);
                                        $('#price').val(response.price);
                                        $('#total').val(response.total);
                                        $('#stock').val(0);
                                        $('#currentStock').val(response.currentStock);
                                        let collect = theP.categoryIds
                                        let box = $('#editTags input[type=checkbox]')
                                        $.each(box, (index, v) => {
                                            let value = $(v)
                                            if (collect.indexOf(parseInt(value.val())) !== -1) {
                                                value.prop('checked', true);
                                            }
                                        })
                                        $('#editModal').modal('show');
                                    }
                                },
                                error: function (error) {
                                    console.log(error);
                                }
                            });
                        });

                        // 绑定删除事件
                        deleteBtn.click(function () {
                            if (confirm("确定删除吗？")) {
                                $.ajax({
                                    url: "/books/book/del",
                                    type: "POST",
                                    data: {id: value.id},
                                    success: function (response) {
                                        console.log(response);
                                    },
                                    error: function (error) {
                                        console.log(error);
                                    }
                                });
                            }
                        });
                        table.append(row);
                    });
                    if (is) {
                        createPageList(1, rep.data.pageTotal)
                    }
                } else {
                    alert(data.message)
                }
            },
            error: function (xhr, status, error) {
                alert('搜索出错');
            }
        })
    }

    // 创建分页组件
    function createPageList(curPage, totalPage) {
        theTotalPage = totalPage
        // 获取分页视图元素
        var pagination = $('#pageList');
        $('#pageList li').remove();
        // 获取上一页和下一页链接元素
        var prevLink = $('#p-page');
        var nextLink = $('#n-page');
        // 获取当前页元素
        var currentPage = $('#pageNo');

        let p = `<li class="page-item"><a class="page-link" href="#" id="p-page">上一页</a></li>`
        pagination.append(p);

        // 初始化分页链接
        for (var i = 1; i <= totalPage; i++) {
            var li = $('<li index="' + i + '" class="page-item"><a class="page-link" href="#">' + i + '</a></li>');
            pagination.append(li)
        }

        let n = `<li class="page-item"><a class="page-link" href="#" id="n-page">下一页</a></li>`
        pagination.append(n);

        // 初始化分页信息
        currentPage.val(curPage);

        // 隐藏无效链接
        if (curPage == 1) {
            prevLink.parent().addClass('disabled');
        }
        if (curPage == totalPage) {
            nextLink.parent().addClass('disabled');
        }

        // 绑定点击事件
        pagination.on('click', 'a', function (e) {
            let cc = $('#pageNo').val()
            e.preventDefault();
            var target = $(this);
            // 判断是否为上一页或下一页链接
            if (target.attr('id') === 'p-page') {
                cc--;
                cc = Math.max(cc, 1)
            } else if (target.attr('id') === 'n-page') {
                cc++;
                cc = Math.min(cc, theTotalPage)
            } else {
                cc = parseInt(target.text());
            }

            // 更新分页信息
            $('#pageNo').val(cc);
            searchPage(false);

            // 更新链接状态
            pagination.find('.page-item').removeClass('page-active');
            pagination.find('a:contains(' + cc + ')').parent().addClass('page-active');
        });
    }

    // 搜索全部分类
    function searchCategory() {
        $.ajax({
            url: "/books/category/all",
            type: "POST",
            success: function (res) {
                if (res.code == 200) {
                    let categories = res.data
                    let e = ``;
                    let category = `<option selected value="all">全部</option>`;
                    $.each(categories, function (index, v) {
                        e += `<label><input type="checkbox" name="category" value="${v.id}">${v.name}</label>`
                        category += `<option value="${v.id}">${v.name}</option>`
                    })
                    $('#bookCategory option').remove()
                    $('.tags label').remove()
                    $('#bookCategory').append(category)
                    $('.tags').append(e)
                }
            }
        })
    }

    function formatDate(time) {
        let date = new Date(time);
        let yyyy = date.getFullYear();
        let mm = String(date.getMonth() + 1).padStart(2, '0'); // 将月份转换为两位数，例如 "06"
        let dd = String(date.getDate()).padStart(2, '0'); // 将日期转换为两位数，例如 "06"
         // 将日期格式化为 "yyyy-mm-dd" 的字符串
        return yyyy + '-' + mm + '-' + dd;
    }
});

