$(document).ready(() => {
    search()

    $('#search-btn').click(() => {
        search()
    })

    $('#add-btn').click(() => {
        resetAForm()
        $('#addModal').modal('show')
    })

    // 监听图书名输入框的 input 事件
    $('#book-name').on('input', function () {
        var inputVal = $(this).val();
        if (!inputVal) {
            $('#book-list div').remove();
            return;
        }
        // 发起 Ajax 请求，根据输入值获取匹配的图书名列表
        $.post('/books/book/find', {
            name: inputVal
        }, function (res) {
            let data = res.data
            $('#book-list div').remove();
            let e = ``
            for (var i = 0; i < data.length; i++) {
                e += `<div class="list-item" bid='${data[i].id}' t="${data[i].title}">
                        <div class="left-item">${data[i].title}</div>
                        <div class="item">作者：${data[i].author}</div>
                        <div class="right-item">库存：${data[i].currentStock}本</div>
                    </div>`
            }
            $('#book-list').append(e)
            // 监听列表中每个条目的 click 事件
            $('#book-list').on('click', '.list-item', function () {
                let id = this.getAttribute('bid');
                let t = this.getAttribute('t')
                // 将该条目的值填入输入框中
                $('#book-name').val(t);
                $('#abook-id').val(id)
                // 隐藏搜索框下方的列表
                $('#book-list div').remove();
            });
        });
    });

    // 监听借阅人输入框的 input 事件
    $('#auser').on('input', function () {
        var inputVal = $(this).val();
        if (!inputVal) {
            $('#borrower-list div').remove();
            return;
        }
        // 发起 Ajax 请求，根据输入值获取匹配的借阅人列表
        $.post('/books/borrowers/find', {
            keyword: inputVal
        }, function (res) {
            let data = res.data
            $('#borrower-list div').remove();
            // 将结果填入搜索框下方的列表中
            let e = ``
            for (var i = 0; i < data.length; i++) {
                e += `<div class="list-item" bid='${data[i].id}' t="${data[i].name}">
                        <div class="left-item">${data[i].name}</div>
                        <div class="item">${data[i].phone}</div>
                        <div class="right-item">${data[i].email}</div>
                    </div>`
            }
            $('#borrower-list').append(e)
            // 监听列表中每个条目的 click 事件
            $('#borrower-list').on('click', '.list-item', function () {
                let id = this.getAttribute('bid');
                let t = this.getAttribute('t')
                // 将该条目的值填入输入框中
                $('#auser').val(t);
                $('#auser-id').val(id)
                // 隐藏搜索框下方的列表
                $('#borrower-list div').remove();
            });
        });
    });

    $('#addSaveBtn').click(() => {
        let bookId = $('#abook-id').val()
        let userId = $('#auser-id').val()
        let bookName = $('#book-name').val().trim()
        let borrower = $('#auser').val().trim()
        let borrowTimeStart = $('#aldate').val()
        let dueDate = $('#addate').val()
        let status = $('#status').val()
        if (bookId.length === 0 || userId.length === 0) {
            alert('请正确选择合法的图书和借阅者')
            return
        }
        if (bookName.length === 0 || borrower.length === 0 || borrowTimeStart.length === 0 || dueDate.length === 0) {
            alert('请填写完整信息')
            return
        }
        $.ajax({
            type: 'POST',
            url: '/books/loans/add',
            data: {
                bookId: bookId,
                userId: userId,
                startDate: borrowTimeStart,
                endDate: dueDate,
                status: status,
            },
            success: function (resp) {
                if (resp.code === 200) {
                    alert('添加成功')
                    $('#addModal').modal('hide')
                } else {
                    alert('添加失败')
                }
            }
        })
    })

    function resetAForm() {
        $('#book-name').val('')
        $('#auser').val('')
        $('#aldate').val('')
        $('#addate').val('')
        $('#abook-id').val('')
        $('#auser-id').val('')
    }

    function search(is = true) {
        let bookName = $('#bookName').val();
        let borrower = $('#borrower').val();
        let borrowTimeStart = $('#borrowTimeStart').val();
        let borrowTimeEnd = $('#borrowTimeEnd').val();
        $.ajax({
            type: 'POST',
            url: '/books/loans/search',
            data: {
                pageNo: is ? 1 : $('#pageNo').val(),
                name: bookName,
                borrower: borrower,
                startDate: borrowTimeStart,
                endDate: borrowTimeEnd
            },
            success: function (resp) {
                if (resp.code === 200) {
                    let data = resp.data
                    let table = $('#data-table tbody');
                    $('#data-table tbody tr').remove()
                    let e = ``
                    for (let i = 0; i < data.data.length; i++) {
                        let item = data.data[i];
                        e += `
                            <tr>
                                <td>${i + 1}</td>
                                <td>${item.bookName}</td>
                                <td>${item.borrower}</td>
                                <td>${formatDate(item.loanDate)}</td>
                                <td>${item.returnDate ? formatDate(item.returnDate) : '-'}</td>
                                <td>${item.status == 0 ? '未归还' : (item.status == 1 ? '借阅中' : (item.status == 2 ? '已逾期' : (item.status == 3 ? '已归还' : '未知状态')))}</td>
                                <td>
                                    <a href="#" the-id="${item.id}" name="edit" class="btn btn-primary btn-sm">修改状态</a>
                                </td>
                            </tr>
                             `
                    }
                    table.append(e)
                    if (is) {
                        createPageList(1, data.pageTotal)
                    }
                }
            }
        })
    }

    // 编辑
    $('#data-table').on('click', 'a[name=edit]', function() {
        $('#editReturnDate').val('')
        let id = this.getAttribute('the-id');
        $.ajax({
            type: 'POST',
            url: '/books/loans/info',
            data: {
                id: id
            },
            success: function (res) {
                if (res.code == 200) {
                    let v = res.data
                    $('#editLoansId').val(v.id)
                    $('#bookTitle').text(v.bookName)
                    $('#bookAuthor').text('——' + v.author)
                    $('#bookCover').prop('src', v.coverUrl)
                    $('#userName').text(v.borrower)
                    $('#userPhone').text(v.userPhone)
                    $('#userEmail').text(v.userEmail)
                    $('#showLoansDate').text(formatDate(v.loanDate))
                    $('#showDueDate').text(formatDate(v.dueDate))
                    let s = '未归还'
                    if (v.status == 0) {
                        s = '未归还'
                    } else if (v.status == 1) {
                        s = '借阅中'
                    } else if (v.status == 2) {
                        s = '已逾期'
                    } else if (v.status == 3) {
                        s = '已归还'
                    }
                    $('#currentStatusSrc').text(s)
                    if (v.returnDate != null) {
                        $('#editReturnDate').val(formatDate(v.returnDate))
                    }
                    let op = $('#edit-status option');
                    $.each(op, (index, value) => {
                        let r = $(value)
                        if (r.val() == v.status) {
                            r.prop('selected', true)
                        }
                    })
                    $('#editModal').modal('show')
                }
            }
        })
    })

    // 发起修改请求
    $('#editSaveBtn').click(() => {
        let id = $('#editLoansId').val()
        let returnDate = $('#editReturnDate').val()
        let status = $('#edit-status').val()
        $.ajax({
            type: 'POST',
            url: '/books/loans/edit',
            data: {
                id: id,
                date: returnDate,
                status: status
            },
            success: function (resp) {
                if (resp.code === 200) {
                    alert('修改成功')
                    $('#editModal').modal('hide')
                } else {
                    alert('修改失败')
                }
            }
        })
    })

    // 创建分页组件
    function createPageList(curPage, totalPage) {
        theTotalPage = totalPage
        // 获取分页视图元素
        var pagination = $('#pageList');
        $('#pageList li').remove();
        if (theTotalPage === 0) {
            return
        }
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
            search(false);

            // 更新链接状态
            pagination.find('.page-item').removeClass('page-active');
            pagination.find('a:contains(' + cc + ')').parent().addClass('page-active');
        });
    }

    function formatDate(time) {
        let date = new Date(time);
        let yyyy = date.getFullYear();
        let mm = String(date.getMonth() + 1).padStart(2, '0'); // 将月份转换为两位数，例如 "06"
        let dd = String(date.getDate()).padStart(2, '0'); // 将日期转换为两位数，例如 "06"
        // 将日期格式化为 "yyyy-mm-dd" 的字符串
        return yyyy + '-' + mm + '-' + dd;
    }
})