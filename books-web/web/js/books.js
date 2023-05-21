$(document).ready(function () {
    searchPage()

    $('#editSaveBtn').click(function () {
        var bookName = $('#bookName').val();
        var author = $('#author').val();
        var publisher = $('#publisher').val();
        var date = $('#date').val();
        var isbn = $('#isbn').val();
        var price = $('#price').val();
        var stock = $('#stock').val();
        $.ajax({
            url: "/books/book/edit",
            type: "POST",
            data: {
                id: $('#editBookId').val(),
                title: bookName,
                coverUrl: $('#bookCover').val(),
                author: author,
                publisher: publisher,
                publicationDate: date,
                isbn: isbn,
                price: price,
                stock: stock
            },
            success: function (response) {
                console.log(response);
                $('#editModal').modal('hide');
            },
            error: function (error) {
                console.log(error);
            }
        });
    })

    function resetForm() {
        $('#bookName').val('');
        $('#author').val('');
        $('#publisher').val('');
        $('#date').val('');
        $('#isbn').val('');
        $('#price').val('');
        $('#total').val('');
        $('#stock').val('');
    }

    $('#addBookBtn').click(function () {
        resetAForm()
        $('#addModal').modal('show');
    });

    $('#addSaveBtn').click(function () {
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
                total: $('#atotal').val()
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
        $('#apublisher').val('');
        $('#adate').val('');
        $('#aisbn').val('');
        $('#aprice').val('');
        $('#atotal').val('');
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

    function searchPage() {
        $.ajax({
            url: '/books/book/search',
            type: 'POST',
            data: {
                pageNo: $('#pageNo').val(),
                name: $('#searchName').val(),
                isbn: $('#searchIsbn').val(),
                category: $('#bookCategory').val() == 'all' ? null : $('#bookCategory').val()
            },
            dataType: 'json',
            success: function (rep) {
                if (rep.code == 200) {
                    let data = rep.data.data
                    var table = $("#dataTable tbody");
                    $.each(data, function (index, value) {
                        var row = $("<tr class='my-tr'>");
                        row.append($("<th scope='row' style='line-height: 120px;'>").text(index + 1));
                        row.append($("<td>").append($("<img class=\"cover-img\">").attr("src", (value.coverUrl ? value.coverUrl : './img/defaultBookCover.jpg'))));
                        row.append($("<td>").text(value.title));
                        row.append($("<td>").text(value.author));
                        row.append($("<td>").text(value.publisher));
                        row.append($("<td>").text(value.publicationDate));
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

                        // TODO 发起编辑请求
                        editBtn.click(function () {
                            resetForm();
                            $.ajax({
                                url: "/books/book_info",
                                type: "GET",
                                data: {id: value.id},
                                success: function (response) {
                                    $('#editBookId').val(response.id)
                                    $('#bookName').val(response.title);
                                    $('#author').val(response.author);
                                    $('#publisher').val(response.publisher);
                                    $('#date').val(response.publicationDate);
                                    $('#isbn').val(response.isbn);
                                    $('#price').val(response.price);
                                    $('#total').val(response.total);
                                    $('#stock').val(response.stock);
                                    $('#editModal').modal('show');
                                },
                                error: function (error) {
                                    console.log(error);
                                }
                            });
                        });
                        deleteBtn.click(function () {
                            if (confirm("确定删除吗？")) {
                                $.ajax({
                                    url: "/books/del_book",
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
                    changePageList(1, rep.data.pageTotal)
                } else {
                    alert(data.message)
                }
            },
            error: function (xhr, status, error) {
                alert('搜索出错');
            }
        })
        let data = []
        var table = $("#dataTable tbody");
        $.each(data, function (index, value) {
            var row = $("<tr class='my-tr'>");
            row.append($("<th scope='row' style='line-height: 120px;'>").text(index + 1));
            row.append($("<td>").append($("<img class=\"cover-img\">").attr("src", (value.coverUrl ? value.coverUrl : './img/defaultBookCover.jpg'))));
            row.append($("<td>").text(value.title));
            row.append($("<td>").text(value.author));
            row.append($("<td>").text(value.publisher));
            row.append($("<td>").text(value.publicationDate));
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

            // TODO 发起编辑请求
            editBtn.click(function () {
                resetForm();
                $.ajax({
                    url: "/books/book/info",
                    type: "GET",
                    data: {id: value.id},
                    success: function (response) {
                        $('#bookName').val(response.name);
                        $('#author').val(response.author);
                        $('#publisher').val(response.publisher);
                        $('#date').val(response.date);
                        $('#isbn').val(response.isbn);
                        $('#price').val(response.price);
                        $('#total').val(response.total);
                        $('#stock').val(response.stock);
                        $('#editModal').modal('show');
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            });
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
    }

    function pSearch() {

    }

    function nSearch() {

    }

    function changePageList(page, size) {

    }
});

