
$("#table").on("click","a",function  () {
	var element=this;
	var data={};
	var flag='';
	var parentId=$(element).children("span").attr("id")
	data.index=$(element).parent().parent().attr("data-index");
	// change($(element));
	if($(element).parent().hasClass("showed")&&$(element).parent().hasClass("menu")){
		change($(element));
		//收起子项
		var allTr = $('#table tr[data-index="'+data.index+'"]');
		// for(var i=1;i<allTr.length;i++){
		// 	allTr[i].remove();
		// }
		allTr.each(function(index){
			if(index == 0) return;
			$(this).remove();
		});
	}else if($(element).parent().hasClass("showed")&&$(element).parent().hasClass("submenu")){
		// var nodes=$('i[data-flag*='+data.id+']');
		//翻转
		change($(element));
		//收起
		deldesub(data);

	}else{
		//翻转
		
		if($(element).parent().hasClass("submenu")){
			$(".submenu").each(function(){	
			if($(this).hasClass("showed")){
				change($(this).children("a"));
				deldesub(data);
			}		
					
			});
		}
		change($(element));
		
		var url="getData?parentId="+parentId;
		$.ajax({
			type:'get',
			url:url,
			success:function(callData){
				test_data=callData
				//插入行(展开)
				for(var i=test_data.length-1;i>=0;i--){
					insert(element,test_data[i],data.index);
				}
				var $selectItem = $("#table").find('[name="btSelectItem"]');
        			$selectItem.off('click').on('click', function (event) {
            			event.stopImmediatePropagation();
            			var $this = $(this),
                			checked = $this.prop('checked');
                // row = that.data[$this.data('index')];
        				var selectAll = $("#table").find('[name="btSelectAll"]').prop('checked');
        				if(checked){
        					$this.parent().parent().addClass("selected");
        					var trn = $('tbody tr'),
        						num = 0;
        					for(var i=0,num=0;i<trn.length;i++){
        						if($(trn[i]).hasClass("selected")){
        							num++;
        						}
        					}
        					if(num == trn.length-1){
        						$("#table").find('[name="btSelectAll"]').prop({checked:true});
        					}
        				}else{
        					if(selectAll){
        						$("#table").find('[name="btSelectAll"]').prop({checked:false});
        						$selectItem.parent().parent().addClass("selected");
        					}
        					$this.parent().parent().removeClass("selected");
        				}      
            // that.updateSelected();
            // that.trigger(checked ? 'check' : 'uncheck', row, $this);
        });
			}
		});
		
		
	}

});
$(document).on('click','#table input[name="btSelectAll"]',function(event){
	event.stopImmediatePropagation();
	console.log("selectall");
	var $this = $(this),
    checked = $this.prop('checked');
    if(checked){
    	$("#table").find('[name="btSelectItem"]').prop({checked:true}).parent().parent().addClass("selected");
    }else{
    	$("#table").find('[name="btSelectItem"]').prop({checked:false}).parent().parent().removeClass("selected");

    }
});
function deldesub(data){
			var allssub = $('#table tr[data-index="'+data.index+'"]').find(".ssubmenu").parent();
			// console.log(allssub);
		/*for(var i=0;i<allssub.length;i++){
			allssub[i].remove();
		}*/
			allssub.each(function(){
				$(this).remove();
			});
}

//状态翻转
function change(element,data) {
	var span = element.children("span");
	    strong = element.children("strong");
	    element.parent().toggleClass(" showed");
	span.toggleClass(" opended");
	strong.toggleClass(" opended");
}
//子菜单模板

function insert(elements,data,index){
	//层次标识
	var level2=$(elements).parent().hasClass("submenu"),
		level3=$(elements).parent().hasClass("ssubmenu");
	var flag = level2?'2':level3?'3':'0';
	//t第二级
	var tem2='<tr data-index="'+index+'"><td style="height:37px;" class="bs-checkbox"><input data-index="'+index+'" name="btSelectItem" type="checkbox"></td><td class="submenu" style="height:37px;"><a href="javascript:;">&nbsp;&nbsp;<span title="'+data.resName+'" id="'+data.id+'" data-flag="sub1-boot" class="fordericon"></span><strong class="fordericon"></strong>'+data[tv_data_field[1]]+'</a></td><td style="">'+data[tv_data_field[2]]+'</td><td style="height:37px;">'+data[tv_data_field[3]]+'</td><td style="height:37px;">'+data[tv_data_field[4]]+'</td><td style="height:37px;">'+data[tv_data_field[5]]+'</td></tr>';
	//第三级
	var tem3='<tr data-index="'+index+'"><td style="height:37px;" class="bs-checkbox"><input data-index="'+index+'" name="btSelectItem" type="checkbox"></td><td style="height:37px;" class="ssubmenu fix-pdl pageicon"><a href="javascript:;"><span title="'+data.resName+'" id="'+data.id+'"></span></a>'+data[tv_data_field[1]]+'</td><td style="height:37px;">'+data[tv_data_field[2]]+'</td><td style="height:37px;">'+data[tv_data_field[3]]+'</td><td style="">'+data[tv_data_field[4]]+'</td><td style="height:37px;">'+data[tv_data_field[5]]+'</td></tr>';
	switch(flag){
		case '0':
			return $(elements).parent().parent().after($(tem2));
		case '2':
			return $(elements).parent().parent().after($(tem3));
	}
}

// $('input[name="btSelectAll"]').change(function () {
// 	var self=this;
// 		$('input[name="btSelectItem"]').each(function() {
// 		if (this.checked) {
// 			if(self.checked){
// 				return;
// 			}
// 			this.checked=0;
// 		}else{
// 			this.checked=1;
// 		}	
// 	});
// });


//text code
var test_data = [
    {
        "name": "bootstrap-table",
        "stargazers_count": "526",
        "forks_count": "122",
        "description": "An extended Bootstrap table with radio,. (supports twitter bootstrap v2 and v3) ",
        "id":"boot"
    },
    {
        "name": "multiple-select",
        "stargazers_count": "288",
        "forks_count": "150",
        "description": "A jQuery plugin to select multiple elements with checkboxes :)",
        "id":"multiple"
    }];
var tv_data_field = [];
var tv_data_thLength = 0;
var tv_data_fieldIndex = [];
function getData(){
	$("#table th[data-field]").each(function(){
		tv_data_field.push($(this).attr("data-field"));
	});
	tv_data_thLength = $("#table th").length;
	$("#table th").each(function(index){
		tv_data_fieldIndex[index] = !!$(this).attr("data-checkbox");
	});
}
getData();
