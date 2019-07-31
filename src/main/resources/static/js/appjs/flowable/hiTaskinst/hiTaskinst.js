var prefix = "/flowable/hiTaskinst"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    // {
                    //     field: 'ID_',
                    //     title: 'ID_'
                    // },
                    // {
                    //     field: 'category_',
                    //     title: '分类'
                    // },
                    // {
                    //     field: 'PROC_INST_ID_',
                    //     title: '实例id'
                    // },
                    {
                        field: 'NAME_',
                        align: 'center',
                        title: '名称'
                    },
                    {
                        field: 'START_TIME_',
                        align: 'center',
                        title: '开始时间',
                        formatter: function (value, row, index) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        field: 'END_TIME_',
                        align: 'center',
                        title: '结束时间',
                        formatter: function (value, row, index) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        field: 'DURATION_',
                        align: 'center',
                        title: '持续时间',
                        formatter: function (mss, row, index) {
                            var days = parseInt(mss / (1000 * 60 * 60 * 24));
                            var hours = parseInt((mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                            var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));
                            var seconds = (mss % (1000 * 60)) / 1000;
                            return days + " 天 " + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒 ";
                        }
                    },
                    {
                        field: 'category_name_',
                        align: 'center',
                        title: '分类名称'
                    },
                    {
                        field: 'task_flag',
                        title: '实例是否结束',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var str ="";
                            if(value==1){
                                str+="<a class='btn btn-danger btn-rounded'>结束</a>";
                            }
                            if(value==0){
                                str+="<a class='btn btn-info btn-rounded'>运行</a>";
                            }

                            return str;
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        field: 'id',
                        align: 'center',
                        width:200,
                        formatter: function (value, row, index) {
                            var viewBtn = "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='view(" + '"' + row.PROC_DEF_ID_ + '","'+row.PROC_INST_ID_+'","'+row.task_flag+'"' + ")'><i class=\"fa fa-eye\"></i>&nbsp;流程图&nbsp;</button>&nbsp;";
                            return viewBtn;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

function view(PROC_DEF_ID_,PROC_INST_ID_,flag) {
    if( flag==1 ){
        $('#img1').attr("src","/flowable/reProcdef/showImage?processId="+PROC_DEF_ID_);
        layer.open({
            type: 1,
            title:"图片信息",
            shadeClose: true,
            shade: false,
            area: ['800px', '520px'],
            content: $('#viewImg') //这里content是一个DOM，这个元素要放在body根节点下
        });
    }else{
        $('#img1').attr("src","/flowable/task/showActivityedimageDetailPageByProcessInstanceId?processInstanceId="+PROC_INST_ID_);
        layer.open({
            type: 1,
            title:"图片信息",
            shadeClose: true,
            shade: false,
            area: ['800px', '520px'],
            content: $('#viewImg') //这里content是一个DOM，这个元素要放在body根节点下
        });
    }

}