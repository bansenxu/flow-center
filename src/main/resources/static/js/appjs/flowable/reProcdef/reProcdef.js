var prefix = "/flowable/reProcdef"
$(function () {
    load();

    $.ajax({
        type: "POST",
        url: "/getCategoryTree",
        data: {
        },
        async: false,
        success: function (res) {
            var menuList = res.data.menuList;
            $("#searchType").html("");
            var apt = "<option value=''>全部</option>";
            $.each(menuList,function(i,n){
                apt += "<option value='"+n.code+"'>"+n.name+"</option>";
            });
            $("#searchType").html(apt);
        }
        });
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
                singleSelect: true, // 设置为true将禁止多选
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
                        offset: params.offset,
                        name:$('#searchName').val(),
                        type:$('#searchType').val(),
                        suspensionState:$('#searchState').val(),

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
                    //     checkbox: true
                    // },
                    // {
                    //     field: 'id',
                    //     title: 'id',
                    //     align: 'center',
                    //     width:30,
                    //     hidden:true
                    // },
                    {
                        field: 'name',
                        title: '名称',
                        align: 'center',
                        width:100
                    },
                    {
                        field: 'category',
                        title: '分类',
                        align: 'center',
                        width:100
                    },
                    {
                        field: 'categoryName',
                        title: '分类名称',
                        align: 'center',
                        width:100
                    },

                    {
                        field: 'key',
                        title: 'key',
                        align: 'center',
                        width:100
                    },
                    {
                        field: 'version',
                        title: '版本',
                        align: 'center',
                        width:60
                    },
                    {
                        field: 'suspensionState',
                        title: '挂起状态',
                        formatter: function (value, row, index) {
                            var str ="";
                            if(value==1){
                                str+="<a class='btn btn-info btn-rounded'>激活</a>";
                            }
                            if(value==2){
                                str+="<a class='btn btn-danger btn-rounded'>挂起</a>";
                            }

                            return str;
                        },
                        align: 'center',
                        width:80
                    },
                    {
                        field: 'tenantId',
                        title: '租户',
                        align: 'center',
                        width:80,
                        formatter: function (value, row, index) {
                            if(value==null || value==""){
                                return "无"
                            }
                            return value;
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        width:200,
                        formatter: function (value, row, index) {
                            var viewBtn = "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='start(" + '"' + row.key + '"' + ")'><i class=\"fa fa-gears\"></i>&nbsp;启动&nbsp;</button>&nbsp;";
                            viewBtn += "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='view(" + '"' + row.id + '"' + ")'><i class=\"fa fa-eye\"></i>&nbsp;流程图&nbsp;</button>&nbsp;";
                            viewBtn += "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='viewDetail(" + '"' + row.category + '"' + ")'><i class=\"fa fa-eye\"></i>&nbsp;查看实例&nbsp;</button>&nbsp;";

                            // var e = '<a  href="#" mce_href="#" title="启动" onclick="start(\''
                            //     + row.key
                            //     + '\')"><i class="fa fa-edit"></i></a> ';
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
        content: prefix + '/add' // iframe的u†ørl
    });
}

function start(id) {

    // window.location.href="/flowable/reProcdef/startProcessInstanceByKey?processDefinitionKey="+id

    layer.open({
        type: 2,
        title: '启动',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/startProcessInstanceByKey?processDefinitionKey='+id // iframe的u†ørl
    });


    // //启动流程
    // $.ajax({
    //     cache : true,
    //     type : "POST",
    //     url : "",
    //     data : {"processDefinitionKey":id},
    //     async : false,
    //     error : function(request) {
    //         laryer.alert("Connection error");
    //     },
    //     success : function(data) {
    //         if (data.code == 0) {
    //             layer.alert("启动成功");
    //         } else {
    //             layer.alert(data.msg)
    //         }
    //     }
    // });
}
function view(id) {
    $('#img1').attr("src","/flowable/reProcdef/showImage?processId="+id);
    layer.open({
        type: 1,
        title:"图片信息",
        shadeClose: true,
        shade: false,
        area: ['800px', '520px'],
        content: $('#viewImg') //这里content是一个DOM，这个元素要放在body根节点下
    });
}
function viewDetail(code) {
    window.location.href="/flowable/ruExecution?code="+code;
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