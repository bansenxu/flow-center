
var prefix = "/flowable/ruExecution"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/taskList", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // code:$('#searchCode').val()
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
							{
								field : 'business_key_',
								title : '业务键'
							},
                            {
                                field : 'id_',
                                title : 'id'
                            },
                            {
                                field : 'name_',
                                title : '名称'
                            },
                            {
                                field : 'task_def_key_',
                                title : '任务key'
                            },
                            {
                                field : 'DESCRIPTION_',
                                title : '描述'
                            },
                            {
                                field : 'category_',
                                title : '分类'
                            },
                            {
                                field : 'category_name_',
                                title : '分类名称'
                            },
                            {
                                field : 'create_time_',
                                title : '创建时间',
                                formatter: function (value, row, index) {
                                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                                }
                            } ,
                            {
                                field : 'PRIORITY_',
                                title : '优先级'
                            },
                            {
                                title: '操作',
                                field: 'id',
                                align: 'center',
                                width:200,
                                formatter: function (value, row, index) {
                                    var viewBtn = "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='complete(" + '"' + row.id_ + '"' + ")'><i class=\"fa fa-gears\"></i>&nbsp;完成&nbsp;</button>&nbsp;";
                                    viewBtn += "<button class=\"btn btn-xs btn-outline btn-info\" type=\"button\"  onclick='view(" + '"' + row.id_ + '"' + ")'><i class=\"fa fa-eye\"></i>&nbsp;流程图&nbsp;</button>&nbsp;";

                                    return viewBtn;
                                }
                            }
                            ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function reBack() {
    window.history.go(-1);
}

/**
 * 根据任务ID完成任务
 * @param id
 */
function complete(taskId) {

    layer.open({
        type: 2,
        title: '完成',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: '/flowable/reProcdef/formReadonly?taskId='+taskId // iframe的u†ørl
    });

    //启动流程
    // $.ajax({
    //     cache : true,
    //     type : "POST",
    //     url : "/flowable/task/complete",
    //     data : {"taskId":taskId},
    //     async : false,
    //     error : function(request) {
    //         laryer.alert("Connection error");
    //     },
    //     success : function(data) {
    //         if (data.code == 0) {
    //             layer.alert("成功完成任务");
    //             reLoad();
    //         } else {
    //             layer.alert(data.msg)
    //         }
    //     }
    // });
}

function view(taskId) {
    $('#img1').attr("src","/flowable/task/showActivityedimageDetailPage?taskId="+taskId);
    layer.open({
        type: 1,
        title:"图片信息",
        shadeClose: true,
        shade: false,
        area: ['800px', '520px'],
        content: $('#viewImg') //这里content是一个DOM，这个元素要放在body根节点下
    });
}

