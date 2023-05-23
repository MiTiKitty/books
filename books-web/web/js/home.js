$(document).ready(() => {
    // 使用jquery发起请求,请求数据——读者借阅情况(每种图书分类下的)
    $.ajax({
        url: '/books/base/duration',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = data.data
            drawBookCategoryChart(listData)
        }
    })

    // 使用jquery发起请求,请求数据——图书借阅量和归还率
    $.ajax({
        url: '/books/base/rate',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = data.data
            drawBookLoansChart(listData)
        }
    })

    // 使用jquery发起请求,请求数据——图书分类比例
    $.ajax({
        url: '/books/base/size',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = data.data
            drawBookCategoriesChart(listData)
        }
    })

    // 使用jquery发起请求,请求数据——每种分类下的图书借阅数量分析
    $.ajax({
        url: '/books/base/count',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = data.data
            drawBookBorrowingCycle(listData)
        }
    })

    // 使用jquery发起请求,请求数据——热门图书库存和借阅量关系
    $.ajax({
        url: '/books/base/hot',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = data.data
            drawBookInventory(listData)
        }
    })
})

// 读者借阅情况
function drawBookCategoryChart(data) {
    let durationData = []
    $.each(data, (index, v) => {
        durationData.push({
            name: v.name,
            value: v.borrowDuration
        })
    })
    var myChart = echarts.init(document.getElementById('bookCategoryChart'));

    // 指定图表的配置项和数据
    var option = {
        series: [{
            name: '借阅量',
            type: 'pie',
            radius: '50%',
            data: durationData,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 图书借阅量和归还率
function drawBookLoansChart(data) {
    let monthData = [];
    let borrowData = [];
    let rateData = [];
    let max = 0
    $.each(data, (index, v) => {
        monthData.push(v.month)
        borrowData.push(v.borrowCount)
        rateData.push(v.returnRate)
        max = Math.max(max, v.borrowCount)
    })

    max = Math.floor(Math.max(max * 1.2 + 0.5, 5))
    let interval = Math.floor(max / 5)

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bookLoansChart'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        legend: {
            data: ['借阅量', '归还率']
        },
        xAxis: [
            {
                type: 'category',
                data: monthData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '借阅量',
                min: 0,
                max: max,
                interval: interval,
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '归还率',
                min: 0,
                max: 100,
                interval: 10,
                axisLabel: {
                    formatter: '{value}%'
                }
            }
        ],
        series: [
            {
                name: '借阅量',
                type: 'bar',
                data: borrowData,
                itemStyle: {
                    color: '#4cabce'
                }
            },
            {
                name: '归还率',
                type: 'line',
                yAxisIndex: 1,
                data: rateData,
                itemStyle: {
                    color: '#ff7f50'
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 图书分类比例
function drawBookCategoriesChart(data) {
    let nameData = [];
    let valueData = [];
    $.each(data, (index, v) => {
        nameData.push(v.name)
        valueData.push({
            name: v.name,
            value: v.count
        })
    })
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bookCategoriesChart'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 0,
            data: nameData
        },
        series: [
            {
                name: '图书分类',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: valueData
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 借阅周期分析
function drawBookBorrowingCycle(data) {
    let categories = []
    let counts = []
    let max = 0
    $.each(data, (index, v) => {
        categories.push(v.category)
        counts.push(v.count)
        max = Math.max(max, v.count)
    })
    max = Math.floor(Math.max(max * 1.2 + 0.5, 5))
    let interval = Math.floor(max / 5)
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bookBorrowingCycle'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        legend: {
            data: ['借阅数量']
        },
        xAxis: [
            {
                type: 'category',
                data: categories
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '借阅数',
                min: 0,
                max: max,
                interval: interval,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '借阅数',
                type: 'line',
                smooth: true,
                data: counts,
                areaStyle: {}
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 图书库存和借阅量关系
function drawBookInventory(data) {
    let titleData = [];
    let borrowData = [];
    let inventoryData = [];
    $.each(data, (index, v) => {
        titleData.push(v.bookName)
        borrowData.push(v.borrowCount)
        inventoryData.push(v.currentStock)
    })
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bookInventory'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['库存量', '借阅量']
        },
        xAxis: {
            type: 'category',
            data: titleData
        },
        yAxis: {
            type: 'value',
            name: '数量'
        },
        series: [
            {
                name: '库存量',
                type: 'line',
                data: inventoryData,
                smooth: true,
                lineStyle: {
                    width: 1
                }
            },
            {
                name: '借阅量',
                type: 'line',
                data: borrowData,
                smooth: true,
                lineStyle: {
                    width: 1
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}