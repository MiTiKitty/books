window.onload = () => {
    // TODO 使用jquery发起请求,请求数据——读者借阅情况
    $.ajax({
        url: '',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = JSON.parse(data)
            drawBookCategoryChart(listData)
        }
    })

    // TODO 使用jquery发起请求,请求数据——图书借阅量和归还率
    $.ajax({
        url: '',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = JSON.parse(data)
            drawBookLoansChart(listData)
        }
    })

    // TODO 使用jquery发起请求,请求数据——图书分类比例
    $.ajax({
        url: '',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = JSON.parse(data)
            drawBookCategoriesChart(listData)
        }
    })

    // TODO 使用jquery发起请求,请求数据——借阅周期分析
    $.ajax({
        url: '',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = JSON.parse(data)
            drawBookBorrowingCycle(listData)
        }
    })

    // TODO 使用jquery发起请求,请求数据——图书库存和借阅量关系
    $.ajax({
        url: '',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let listData = JSON.parse(data)
            drawBookInventory(listData)
        }
    })
}

// 读者借阅情况
function drawBookCategoryChart(data) {
    var myChart = echarts.init(document.getElementById('bookCategoryChart'));

    // 指定图表的配置项和数据
    var option = {
        series: [{
            name: '借阅量',
            type: 'pie',
            radius: '50%',
            data: [
                { value: 100, name: '1月' },
                { value: 150, name: '2月' },
                { value: 200, name: '3月' },
                { value: 250, name: '4月' },
                { value: 300, name: '5月' },
                { value: 350, name: '6月' },
                { value: 400, name: '7月' },
                { value: 450, name: '8月' },
                { value: 500, name: '9月' },
                { value: 550, name: '10月' },
                { value: 600, name: '11月' },
                { value: 650, name: '12月' },
            ],
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
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '借阅量',
                min: 0,
                max: 800,
                interval: 200,
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '归还率',
                min: 0,
                max: 100,
                interval: 20,
                axisLabel: {
                    formatter: '{value}%'
                }
            }
        ],
        series: [
            {
                name: '借阅量',
                type: 'bar',
                data: [100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650],
                itemStyle: {
                    color: '#4cabce'
                }
            },
            {
                name: '归还率',
                type: 'line',
                yAxisIndex: 1,
                data: [80, 85, 90, 88, 92, 95, 93, 96, 98, 97, 99, 98],
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
            left: 10,
            data: ['小说', '科幻', '历史', '哲学', '心理学']
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
                data: [
                    { value: 400, name: '小说' },
                    { value: 300, name: '科幻' },
                    { value: 200, name: '历史' },
                    { value: 150, name: '哲学' },
                    { value: 100, name: '心理学' }
                ]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 借阅周期分析
function drawBookBorrowingCycle(data) {
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
            data: ['借阅周期']
        },
        xAxis: [
            {
                type: 'category',
                data: ['1周', '2周', '3周', '4周', '5周', '6周', '7周']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '借阅次数',
                min: 0,
                max: 800,
                interval: 200,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '借阅周期',
                type: 'line',
                smooth: true,
                data: [100, 150, 200, 250, 300, 350, 400],
                areaStyle: {}
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}

// 图书库存和借阅量关系
function drawBookInventory(data) {
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
            data: ['第1本书', '第2本书', '第3本书', '第4本书', '第5本书', '第6本书', '第7本书', '第8本书', '第9本书', '第10本书']
        },
        yAxis: {
            type: 'value',
            name: '数量'
        },
        series: [
            {
                name: '库存量',
                type: 'line',
                data: [120, 152, 200, 334, 390, 330, 220, 100, 50, 10],
                smooth: true,
                lineStyle: {
                    width: 3
                }
            },
            {
                name: '借阅量',
                type: 'line',
                data: [30, 78, 134, 210, 260, 250, 180, 90, 40, 5],
                smooth: true,
                lineStyle: {
                    width: 3
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
}