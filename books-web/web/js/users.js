let theTotalPage = 1;

$(document).ready(function () {
    searchPage()

    $('#searchBtn').click(() => {
        searchPage()
    })

    $('#addUserBtn').click(() => {
        resetAForm()
        $('#addModal').modal('show')
    })

    function resetAForm() {
        $('#aName').val('')
        $('#aEmail').val('')
        $('#aAddress').val('')
        $('#aPhone').val('')
    }

    $('#editSaveBtn').click(() => {
        let id = $('#editUserId').val()
        let name = $('#editName').val().trim()
        let email = $('#editEmail').val().trim()
        let address = $('#editAddress').val().trim()
        let phone = $('#editPhone').val().trim()
        if (name.length === 0 || address.length === 0 || phone.length === 0 || email.length === 0) {
            alert('请填写完整信息')
            return
        }
        $.ajax({
            url: '/books/borrowers/edit',
            type: 'POST',
            data: {
                id: id,
                name: name,
                email: email,
                address: address,
                phone: phone
            },
            success: function (data) {
                if (data.code == 200) {
                    alert('修改成功');
                    $('#editModal').modal('hide');
                } else {
                    alert('修改失败')
                }
            }
        })
    })

    function resetForm() {
        $('#editUserId').val('')
        $('#editName').val('')
        $('#editEmail').val('')
        $('#editAddress').val('')
        $('#editPhone').val('')
    }

    $('#addSaveBtn').click(function () {
        let name = $('#aName').val().trim()
        let email = $('#aEmail').val().trim()
        let address = $('#aAddress').val().trim()
        let phone = $('#aPhone').val().trim()
        if (name.length === 0 || email.length === 0 || address.length === 0 || phone.length === 0) {
            alert('请填写完整信息')
            return
        }
        $.ajax({
            url: '/books/borrowers/add',
            type: 'POST',
            data: {
                name: name,
                email: email,
                address: address,
                phone: phone
            },
            success: function (data) {
                if (data.code == 200) {
                    alert('添加成功');
                    $('#addModal').modal('hide');
                } else {
                    alert('添加失败')
                }
            }
        })
    });

    function searchPage(is = true) {
        // 获取输入框的值
        let keyword = $('#searchKeyword').val().trim()
        $.ajax({
            type: 'GET',
            url: '/books/borrowers/search',
            data: {
                keyword: keyword,
                pageNo: is ? 1 : $('#pageNo').val()
            },
            success: function (res) {
                if (res.code == 200) {
                    let obj = res.data
                    let e = ``;
                    $('#usersListTable tbody tr').remove()
                    $.each(obj.data, (index, v) => {
                        e += `
                        <tr>
                            <td>${index + 1}</td>
                            <td>${v.name}</td>
                            <td>${v.email}</td>
                            <td>${v.address}</td>
                            <td>${v.phone}</td>
                            <td>
                                <a the-id="${v.id}" href="#" name="edit" class="btn btn-success btn-sm">修改信息</a>
                                <a the-id="${v.id}" href="#" name="del" class="btn btn-danger btn-sm">删除</a>
                            </td>
                        </tr>`
                    })
                    $('#usersListTable tbody').append(e)
                    if (is) {
                        createPageList(1, obj.pageTotal)
                    }
                }
            }
        })
    }

    // 编辑用户
    $('#usersListTable').on('click', 'a[name=edit]', function() {
        let id = this.getAttribute('the-id');
        $.ajax({
            type: 'POST',
            url: '/books/borrowers/info',
            data: {
                id: id
            },
            success: function (res) {
                if (res.code == 200) {
                    let data = res.data
                    $('#editUserId').val(data.id)
                    $('#editName').val(data.name)
                    $('#editEmail').val(data.email)
                    $('#editAddress').val(data.address)
                    $('#editPhone').val(data.phone)
                    $('#editModal').modal('show')
                }
            }
        })
    })

    // 删除用户
    $('#usersListTable').on('click', 'a[name=del]', function() {
        let id = this.getAttribute('the-id');
        if (confirm('您确定您要删除这条记录吗')) {
            $.ajax({
                type: 'POST',
                url: '/books/borrowers/del',
                data: {
                    id: id
                },
                success: function (res) {
                    if (res.code == 200) {
                        alert('删除成功')
                        searchPage()
                    } else {
                        alert('删除失败')
                    }
                },
                error: function () {
                    alert('网络问题，删除失败')
                }
            })
        }
    })

    // 创建分页组件
    function createPageList(curPage, totalPage) {
        theTotalPage = totalPage
        // 获取分页视图元素
        var pagination = $('#pageList');
        $('#pageList li').remove();
        if (theTotalPage == 0) {
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
            searchPage(false);

            // 更新链接状态
            pagination.find('.page-item').removeClass('page-active');
            pagination.find('a:contains(' + cc + ')').parent().addClass('page-active');
        });
    }
})