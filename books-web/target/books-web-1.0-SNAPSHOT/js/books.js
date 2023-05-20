$(document).ready(function () {
    var data = [
        {
            cover: "",
            name: "Book 1sfdddddddddddddddd",
            author: "Author 1",
            publisher: "Publisher 1",
            date: "2021-01-01",
            isbn: "1234567890",
            price: "$10.00",
            total: 100,
            stock: 50
        },
        {
            cover: "",
            name: "Book 2",
            author: "Author 2",
            publisher: "Publisher 2",
            date: "2021-02-01",
            isbn: "0987654321",
            price: "$15.00",
            total: 50,
            stock: 20
        },
        {
            cover: "",
            name: "Book 3",
            author: "Author 3",
            publisher: "Publisher 3",
            date: "2021-03-01",
            isbn: "1357908642",
            price: "$20.00",
            total: 80,
            stock: 60
        }
    ];
    var table = $("#dataTable tbody");
    $.each(data, function (index, value) {
        var row = $("<tr class='my-tr'>");
        row.append($("<th scope='row' style='line-height: 120px;'>").text(index + 1));
        row.append($("<td>").append($("<img class=\"cover-img\">").attr("src", (value.cover ? value.cover : './img/defaultBookCover.jpg'))));
        row.append($("<td>").text(value.name));
        row.append($("<td>").text(value.author));
        row.append($("<td>").text(value.publisher));
        row.append($("<td>").text(value.date));
        row.append($("<td>").text(value.isbn));
        row.append($("<td>").text(value.price));
        row.append($("<td>").text(value.total));
        row.append($("<td>").text(value.stock));
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
                url: "/book/edit",
                type: "POST",
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
                    url: "/book/delete",
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

    $('#editSaveBtn').click(function () {
        var bookName = $('#bookName').val();
        var author = $('#author').val();
        var publisher = $('#publisher').val();
        var date = $('#date').val();
        var isbn = $('#isbn').val();
        var price = $('#price').val();
        var total = $('#total').val();
        var stock = $('#stock').val();
        $.ajax({
            url: "/book/edit",
            type: "POST",
            data: {
                name: bookName,
                author: author,
                publisher: publisher,
                date: date,
                isbn: isbn,
                price: price,
                total: total,
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
        console.log('aaaa');
        $('#addModal').modal('show');
    });

    $('#addSaveBtn').click(function () {
        $.ajax({
            url: '/add_book',
            type: 'POST',
            data: {
                bookName: $('#abookName').val(),
                author: $('#aauthor').val(),
                publisher: $('#apublisher').val(),
                date: $('#adate').val(),
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
        $.ajax({
            url: '/search_book',
            type: 'POST',
            data: {
                bookName: $('#searchName').val(),
                isbn: $('#searchIsbn').val()
            },
            success: function (data) {
                $('tbody').empty();
                for (var i = 0; i < data.length; i++) {
                    var book = data[i];
                    var row = $('<tr></tr>');
                    row.append($('<th scope="row">' + (i + 1) + '</th>'));
                    row.append($('<td><img class="cover-img" src="' + (book.cover ? book.cover : './img/defaultBookCover.jpg') + '" alt=""></td>'));
                    row.append($('<td>' + book.name + '</td>'));
                    row.append($('<td>' + book.author + '</td>'));
                    row.append($('<td>' + book.publisher + '</td>'));
                    row.append($('<td>' + book.date + '</td>'));
                    row.append($('<td>' + book.isbn + '</td>'));
                    row.append($('<td>' + book.price + '</td>'));
                    row.append($('<td>' + book.total + '</td>'));
                    row.append($('<td>' + book.stock + '</td>'));
                    var btnGroup = $('<div class="my-btn-group"></div>');
                    var editBtn = $('<button type="button" class="btn btn-primary my-btn">编辑</button>');
                    var deleteBtn = $('<button type="button" class="btn btn-danger my-btn">删除</button>');
                    btnGroup.append(editBtn);
                    btnGroup.append(deleteBtn);
                    row.append($('<td></td>').append(btnGroup));
                    editBtn.click(function () {
                        var value = {
                            id: $(this).parent().parent().find('th').text(),
                            name: $(this).parent().parent().find('td:nth-child(3)').text(),
                            author: $(this).parent().parent().find('td:nth-child(4)').text(),
                            publisher: $(this).parent().parent().find('td:nth-child(5)').text(),
                            date: $(this).parent().parent().find('td:nth-child(6)').text(),
                            isbn: $(this).parent().parent().find('td:nth-child(7)').text(),
                            price: $(this).parent().parent().find('td:nth-child(8)').text(),
                            total: $(this).parent().parent().find('td:nth-child(9)').text(),
                            stock: $(this).parent().parent().find('td:nth-child(10)').text()
                        };
                        $('#bookName').val(value.name);
                        $('#author').val(value.author);
                        $('#publisher').val(value.publisher);
                        $('#date').val(value.date);
                        $('#isbn').val(value.isbn);
                        $('#price').val(value.price);
                        $('#total').val(value.total);
                        $('#stock').val(value.stock);
                        $('#editModal').modal('show');
                    });
                    deleteBtn.click(function () {
                        if (confirm("确定删除吗？")) {
                            $.ajax({
                                url: "/book/delete",
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
                    $('tbody').append(row);
                }
            },
            error: function (xhr, status, error) {
                alert('搜索失败');
            }
        });
    });

});

