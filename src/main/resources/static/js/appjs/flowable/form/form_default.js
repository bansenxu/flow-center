$().ready(function() {
	// validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {


    var str = "{";
    $("input[name='from_title']").each(function(i,o)
    {
    	if($("input[name='from_title']").eq(i).val()==""){
            parent.layer.msg("属性不能为空！");
            return false;
		}
        if((i +1) == $("input[name='from_title']").length){
            str += '"'+$("input[name='from_title']").eq(i).val()+'":"'+$("input[name='from_value']").eq(i).val()+'"';
        }else {
            str += '"'+$("input[name='from_title']").eq(i).val()+'":"'+$("input[name='from_value']").eq(i).val()+'",';
        }
    });
    str +="}";
    $.ajax({
		cache : true,
		type : "POST",
		url : "/flowable/reProcdef/formSave",
		data : JSON.parse(str),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function addProp() {
		var ht = '<div class="form-group">'
        +'<div class="col-sm-4">'
            +'<input  name="from_title" class="form-control" type="text">'
            +'</div>'
            +'<div class="col-sm-4">'
            +'<input  name="from_value" class="form-control" type="text">'
            +'</div>'
            +'<div class="col-sm-4">'
            +'<a onclick="delProp(this)" class="btn btn-danger">删除</a>'
            +'</div>'
            +'</div>';
    $("#qd").before(ht);
    return false;
}

function delProp(obj) {
	$(obj).parent().parent().remove();
}


function validateRule() {
	// var icon = "<i class='fa fa-times-circle'></i> ";
	// $("#signupForm").validate({
	// 	rules : {
	// 		name : {
	// 			required : true
	// 		}
	// 	},
	// 	messages : {
	// 		name : {
	// 			required : icon + "请输入姓名"
	// 		}
	// 	}
	// })
}