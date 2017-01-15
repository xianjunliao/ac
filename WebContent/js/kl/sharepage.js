var ue=UE.getEditor("container",{autoHeight:false,toolbars:[["undo","redo","|","bold","italic","underline","fontborder","strikethrough","superscript","subscript","removeformat","formatmatch","autotypeset","blockquote","pasteplain","|","forecolor","backcolor","insertorderedlist","insertunorderedlist","selectall","cleardoc","|","rowspacingtop","rowspacingbottom","lineheight","|","customstyle","paragraph","fontfamily","fontsize","|","directionalityltr","directionalityrtl","indent","|","justifyleft","justifycenter","justifyright","justifyjustify","|","touppercase","tolowercase","|","imagenone","imageleft","imageright","imagecenter","|","simpleupload","insertimage","attachment","template","|","horizontal","date","time","spechars","|","inserttable","deletetable","insertparagraphbeforetable","insertrow","deleterow","insertcol","deletecol","mergecells","mergeright","mergedown","splittocells","splittorows","splittocols","charts","|","searchreplace","drafts"]]});
function sendData(){
	if(ue.hasContents()){
		return true;
	}else{
		top.layer.msg('请输入内容');
		return false;
	}
}
$('.sys-tabs .nav-tabs li').click(function(){
			var position = {
				'存稿':{width:'62px',left:'0px'},
				'待审核':{width:'74px',left:'62px'},
				'审核通过':{width:'88px',left:'138px'},
				'审核未通过':{width:'104px',left:'226px'}
				};
			$('.bottom-line').css(position[$.trim($(this).text())]);
			// $('.bottom-line').animate(position[$(this).text().trim()],600,'swing');
		});
		$(function(){
			var initSharepage = {
				init:function(){
					this.clickEvent();
					this.firstStatu(1);
					this.secondStatu(1);
					this.thirdStatu(1);
					this.fourStatu(1);
				},
				totalPage:{1:1,2:1,3:1,4:1},
				currentPage:{1:1,2:1,3:1,4:1},
				totalPage:function(type,total){
					this.pageTotal[type] = total; 
					return this.pageTotal;
				},
				pagetruning:function(dataType,trunType){
					var _this = this;
					var pageNum = 0;
					switch(dataType){
						case 1:
							pageNum = this.currentPage[1];
							if(trunType == 'back'){
								pageNum--;
								if(pageNum<0) break;
							}else{
								pageNum++;
								if(pageNum>this.totalPage[1]) break;
							};
							this.firstStatu(pageNum);
						break;
						case 2:
							pageNum = this.currentPage[2];
							if(trunType == 'back'){
								pageNum--;
								if(pageNum<0) break;
							}else{
								pageNum++;
								if(pageNum>this.totalPage[2]) break;
							};
							this.secondStatu(pageNum);
						break;
						case 3:
							pageNum = this.currentPage[3];
							if(trunType == 'back'){
								pageNum--;
								if(pageNum<0) break;
							}else{
								pageNum++;
								if(pageNum>this.totalPage[3]) break;
							};
							this.thirdStatu(pageNum);
						break;
						case 4:
							pageNum = this.currentPage[4];
							if(trunType == 'back'){
								pageNum--;
								if(pageNum<0) break;
							}else{
								pageNum++;
								if(pageNum>this.totalPage[4]) break;
							};
							this.fourStatu(pageNum);
						break;
					}
				},
				clickEvent:function(){
					var _this = this;
					$(document).on('click','.goback',function(){
						_this.pagetruning($(this).data('type'),"back");
					});
					$(document).on('click','.forward',function(){
						_this.pagetruning($(this).data('type'),"go");
					});
					$(document).on('click','.pageButton',function(){
						switch($(this).data('type')){
						case 1:
							_this.firstStatu($(this).data('pagenum'));
						break;
						case 2:
							_this.secondStatu($(this).data('pagenum'));
						break;
						case 3:
							_this.thirdStatu($(this).data('pagenum'));
						break;
						case 4:
							_this.fourStatu($(this).data('pagenum'));
						break;
						}
					});
					$(document).on('click','.depositDraft',function(){
						$.ajax({
							url:'/klIndexController/editExperience/'+this.id,
							type:'get',
							success:function(data){
								var inputs = '<div><input id="state" name="state" value="" type="hidden"/><input name="id" value="'+data.id+'" type="hidden"/>'+
								'<input name="creater" value="'+data.creater+'" type="hidden"/><input name="createrName" value="'+data.createrName+'" type="hidden"/>'+
					       		'</div>';
								$('#depositDraft_unstage').before(inputs);
								$('#depositDraft_unstage').remove();
								$('#basic-addon1').val(data.title);
								$('#klType').val(data.klTypeName);
								$('#tags').val(data.tag);
								ue.ready(function() {
								    ue.setContent(data.content);
								});
								$('#home').removeClass('active in');
								$('#home-tab').parent().removeClass("active");
								$('#share-tab').parent().addClass('active');
								$('#share').addClass('active in');
							},
							error:function(){
								top.layer.msg('数据异常！');
							}
						});
					});
				},
				currentTruning:{1:[0],2:[0],3:[0],4:[0]},
				paginations:function(totalPage,pageNumber,type){
					var _this= this;
					var numButton = [];
					var truningNumbers = [];
					var isTruning = 'go';
					$.each(this.currentTruning[type],function(i){
						if(this == pageNumber) isTruning = 'current';
						if(this != pageNumber && pageNumber == _this.currentTruning[type][0]-1) isTruning = 'back';
					});
					for(var i=1;i<=totalPage;i++){
						if(pageNumber == i){
							numButton.push('<button class="btn btn-default pageButton activepage" data-pagenum="'+i+'" data-type="'+type+'">'+i+'</button>');
						}else{
							numButton.push('<button class="btn btn-default pageButton" data-pagenum="'+i+'" data-type="'+type+'">'+i+'</button>');
						}
						isTruning != 'current'?truningNumbers.push(i):void(0);
					}
					var _numButton = numButton.slice(0);
					var _truningNumbers = truningNumbers.slice(0);				
					if(isTruning == 'go'){
						numButton = numButton.slice(pageNumber-1,pageNumber+4);
						truningNumbers = truningNumbers.slice(pageNumber-1,pageNumber+4);
						if(numButton.length<5){
							numButton = _numButton.slice(-5);
							truningNumbers = _truningNumbers.slice(-5);
						}
						this.currentTruning[type] = truningNumbers;
					}else if(isTruning == 'current'){
						numButton = numButton.slice(this.currentTruning[type][0]-1,this.currentTruning[type][this.currentTruning[type].length-1]);
					}else{
						if(pageNumber>5){
							numButton = numButton.slice(pageNumber-5,pageNumber);
							truningNumbers = truningNumbers.slice(pageNumber-5,pageNumber);
						}else{
							numButton = numButton.slice(0,5);
							truningNumbers = truningNumbers.slice(0,5);
						}				
						this.currentTruning[type] = truningNumbers;
					}
					return '<div>总共'+totalPage+'页 当前显示第'+pageNumber+'页<div class="btn-toolbar right" role="toolbar" ><div class="btn-group" role="group" aria-label="..."><button class="btn btn-default goback" data-type="'+type+'">&lt;</button>'+numButton.join("")+'<button class="btn btn-default forward" data-type="'+type+'">&gt;</button></div></div></div>';

				},
				firstStatu:function(pagenum){
					var _this = this;
					var type = 1;
					$.ajax({
						url:"/klIndexController/myShare/1/"+pagenum,
						type:'get',
						success:function(result){
							_this.currentPage[1] = result.pageNumber;
							_this.totalPage[1] = result.totalPage;
							var contentTemp = '';
							$.each(result.data,function(){
								contentTemp += '<div class="shareContent"><a  id="'+this.id+'" href="javascript:;" class="title depositDraft" data-id="discuss">'+this.title+'</a><span class="stamp right">于 '+yyyymmdd(this.createTime)+'  存稿  </div></div>';
							});
							contentTemp+=_this.paginations(result.totalPage,result.pageNumber,type);
							$('#sh>.sharepage').html("");
							$('#sh>.sharepage').append(contentTemp);
						}
					});
				},	
				secondStatu:function(pagenum){
					var _this = this;
					var type = 2;
					$.ajax({
						url:"/klIndexController/myShare/2/"+pagenum,
						type:'get',
						success:function(result){
							_this.currentPage[2] = result.pageNumber;
							_this.totalPage[2] = result.totalPage;
							var contentTemp = '';
							$.each(result.data,function(){
								contentTemp += '<div class="shareContent"><a href="/klExperience/showExperience/'+this.id+'" class="title" data-id="discuss">'+this.title+'</a><span class="right auditing">审核中</span></div>';
							});
							contentTemp+=_this.paginations(result.totalPage,result.pageNumber,type);
							$('#sh2>.sharepage').html("");
							$('#sh2>.sharepage').append(contentTemp);
						}
					});
				},
				thirdStatu:function(pagenum){
					var _this = this;
					var type = 3;
					$.ajax({
						url:"/klIndexController/myShare/3/"+pagenum,
						type:'get',
						success:function(result){
							_this.currentPage[3] = result.pageNumber;
							_this.totalPage[3] = result.totalPage;
							var contentTemp = '';
							$.each(result.data,function(){
								var tags = $.map(this.tag.split(' '),function(item){return '<span class="biaoqian">'+item+' </span>'}).join(" ");
								contentTemp += '<div class="shareContent"><div style="margin-bottom:10px;">'+tags+'<span class="scran">浏览('+this.scanCount+')</span></div><a href="/klIndexController/goShareDetails/'+this.id+'" class="title" data-id="discuss">'+this.title+'</a><div style="margin-top:5px;"></div><span class="stamp right">于 '+yyyymmdd(this.createTime)+'  收录到   <i class="entry">'+this.klTypeName+'</i></div></div>';
							});
							contentTemp+=_this.paginations(result.totalPage,result.pageNumber,type);
							$('#sh3>.sharepage').html("");
							$('#sh3>.sharepage').append(contentTemp);
						}
					});
				},
				fourStatu:function(pagenum){
					
					var _this = this;
					var type = 4;
					$.ajax({
						url:"/klIndexController/myShare/4/"+pagenum,
						type:'get',
						success:function(result){
							_this.currentPage[4] = result.pageNumber;
							_this.totalPage[4] = result.totalPage;
							var contentTemp = '';
							$.each(result.data,function(){
								contentTemp += '<div class="shareContent"><a href="/klExperience/showExperience/'+this.id+'" class="title" data-id="discuss">'+this.title+'</a><span class=" right unauditing">审核未通过</span></div>';
							});
							contentTemp+=_this.paginations(result.totalPage,result.pageNumber,type);
							$('#sh4>.sharepage').html("");
							$('#sh4>.sharepage').append(contentTemp);
						}
					});
				}
			};
			initSharepage.init();
		});