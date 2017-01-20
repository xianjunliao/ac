<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript"> 
		var index=$('#tx').length;
$(function() {
	
	var tables = $('#tx');
	
	$("#addRow").click(function(){
		index++;
		var txName="<input name='txName' type='text' width='140px' />";
		var txType='<select width="28px" name="select" name="txType" id="txType" class="xla_k"> <option value="0" selected="selected">消费</option> <option value="1">收入</option></select>';
		var ca="<a onClick='saveRow(this)'>保存</a>   <a id='rowDelete' onClick='delRow("+index+")'>删除</a>";
		var trtd = $('<tr id='+index+'><td>'+txName+'</td><td>'+txType+'</td><td align="center" width="100px">' +ca+'</td></tr>');
		trtd.appendTo(tables);
		});
	
});
//删除行  
function delRow(rowIndex){  
    $("#"+rowIndex).remove();  
    index--;  
}  

//删除行数据
function delRowData(id){  
	$.ajax({
		cache : true,
		type : "POST",
		url : "${base}book/deleteTx?id="+id,
		async : false,
		success : function(data) {
			 window.location.reload();//刷新当前页面.
		}
	});
} 
//保存行
function saveRow(obj){  
	var $td= $(obj).parents('tr').children('td');  
    var txType = $td.find('option:selected').val(); 
    var txName = $td.find('input').val();   

	$.ajax({
		cache : true,
		type : "POST",
		url : "${base}book/addTemp?txName="+txName+"&txType="+txType,
		async : false,
		success : function(data) {
			 window.location.reload();//刷新当前页面.
		}
	});
} 
function Map() {     
    this.elements = new Array();     
       
    //获取MAP元素个数     
    this.size = function() {     
        return this.elements.length;     
    }     
       
    //判断MAP是否为空     
    this.isEmpty = function() {     
        return(this.elements.length < 1);     
    }     
       
    //删除MAP所有元素     
    this.clear = function() {     
        this.elements = new Array();     
    }     
       
    //向MAP中增加元素（key, value)      
    this.put = function(_key, _value) {     
        this.elements.push( {     
            key : _key,     
            value : _value     
        });     
    }     
       
    //删除指定KEY的元素，成功返回True，失败返回False     
    this.remove = function(_key) {     
        var bln = false;     
        try{     
            for(i = 0; i < this.elements.length; i++) {     
                if(this.elements[i].key == _key) {     
                    this.elements.splice(i, 1);     
                    return true;     
                }     
            }     
        } catch(e) {     
            bln = false;     
        }     
        return bln;     
    }     
       
    //获取指定KEY的元素值VALUE，失败返回NULL     
    this.get = function(_key) {     
        try{     
            for(i = 0; i < this.elements.length; i++) {     
                if(this.elements[i].key == _key) {     
                    return this.elements[i].value;     
                }     
            }     
        } catch(e) {     
            return null;     
        }     
    }     
       
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL     
    this.element = function(_index) {     
        if(_index < 0 || _index >= this.elements.length) {     
            return null;     
        }     
        return this.elements[_index];     
    }     
       
    //判断MAP中是否含有指定KEY的元素     
    this.containsKey = function(_key) {     
        varbln = false;     
        try{     
            for(i = 0; i < this.elements.length; i++) {     
                if(this.elements[i].key == _key) {     
                    bln = true;     
                }     
            }     
        } catch(e) {     
            bln = false;     
        }     
        return bln;     
    }     
       
    //判断MAP中是否含有指定VALUE的元素     
    this.containsValue = function(_value) {     
        var bln = false;     
        try{     
            for(i = 0; i < this.elements.length; i++) {     
                if(this.elements[i].value == _value) {     
                    bln = true;     
                }     
            }     
        } catch(e) {     
            bln = false;     
        }     
        return bln;     
    }     
       
    //获取MAP中所有VALUE的数组（ARRAY）     
    this.values = function() {     
        var arr = new Array();     
        for(i = 0; i < this.elements.length; i++) {     
            arr.push(this.elements[i].value);     
        }     
        return arr;     
    }     
       
    //获取MAP中所有KEY的数组（ARRAY）     
    this.keys = function() {     
        var arr = new Array();     
        for(i = 0; i < this.elements.length; i++) {     
            arr.push(this.elements[i].key);     
        }     
        return arr;     
    }     
}   
function saveRowBook(obj){  
	var $td= $(obj).parents('tr').children('td');  
	var map = new Map();  
	var arr=new Array();
	  $td.find('input').each(function () {    
       var id=$(this).attr('id');
       var val=$(this).val();
       map.put(id,val);
       arr.push(id+":"+val);
    });
	  
	$.ajax({
		cache : true,
		type : "POST",
		url : "${base}book/addBook?array="+arr,
		async : false,
		success : function(data) {
			 window.location.reload();//刷新当前页面.
		}
	});
} 
</script>
<head>

</head>
<body>

	<div id="books">
		<h1>欢迎用户[<a href="#">${username}</a>]来到记账薄</h1><h5><a href="${base}">退出</a></h5>
			<div id="temp">
		<a id="addRow" href="#">新增一项</a>
		<table id="tx" width="500px">
		<tr>
		  <td width="150px "><b>名 称</b> </td><td width="30px"><b>类 型</b></td><td width="100px" align="center"><b>操 作</b></td>
		</tr>
		<c:forEach items="${temps }" var="t">
		<tr>
		 <td>${t.txName}</td>
		<td><c:if test="${t.txType==0 }">消费</c:if><c:if test="${t.txType==1 }">收入</c:if></td>
		<td align="center"><a >修改</a> <a onClick="delRowData(${t.id})"> 删除</a></td>
		</tr>         
		</c:forEach>
		</table>
		</div>
		
		
		<c:if test="${not empty temps }">
		
	
		
		<table border="1">
		<thead>
		 <tr >
		    <th>
		      记账编号
		  </th>
		      <th width="165px">
		      记账日期
		  </th>
		  <c:forEach items="${temps }" var="bk">
		   <th width="25px">
	${bk.txName  }
		  </th>
		  	</c:forEach>
		  	 <th>
		      操作
		  </th>
		 </tr>
		 
		</thead>
		<tr>
		<td>1</td>
		<td width="165px">${newDate}</td>
		 <c:forEach items="${temps }" var="bk">
		   <td width="25px">
		   <input id="${bk.id}" name="tempTxId" type="text"  width="25px"/>
 		  </td>
		  	</c:forEach>
		  <td align="center"><a onClick='saveRowBook(this)'>保存 </a></td>
		 </tr>
	
		</table>
		</c:if>
		<p>----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
		
			<table border="1">
		<thead>
		 <tr >
		    <th>
		      记账编号
		  </th>
		      <th width="165px">
		      记账日期
		  </th>
		  <c:forEach items="${list }" var="l">
		   <th width="158    px">
	      ${l.txName  }
		  </th>
		  	</c:forEach>
		 </tr>
		 
		</thead>
		<tr>
		<td>1</td>
		<td width="165px">${newDate}</td>
		 <c:forEach items="${list }" var="l">
		   <td width="25px">
	       ${l.acAmount }
 		  </td>
		  	</c:forEach>
		 </tr>
	
		</table>
		
	</div>
</body>
</html>