var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if (!("name" in options) && !("type" in options)) {
		$data = this._data;// the root tree
		callback({
			data : $data
		});
		return;
	} else if ("type" in options && options.type == "folder") {
		if ("additionalParameters" in options
				&& "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else
			$data = {}// no data
	}

	if ($data != null)// this setTimeout is only for mimicking some random
		// delay
		setTimeout(function() {
			callback({
				data : $data
			});
		}, parseInt(Math.random() * 500) + 200);

	// we have used static data here
	// but you can retrieve your data dynamically from a server using ajax call
	// checkout examples/treeview.html and examples/treeview.js for more info
};
var menu1 = $('#menu1').val();
var menu2 = $('#menu2').val();
var menu3 = $('#menu3').val();
var json = eval('(' + menu1 + ')');
var json2 = eval('(' + menu2 + ')');
var json3 = eval('(' + menu3 + ')');
//console.log(json);
//console.log(json2);
//console.log(json3);
var tree_data_2 = json;
//console.log(tree_data_2);
var m2 = [];
var m3 = [];
for ( var o in tree_data_2) {
	for ( var i in json2) {
		console.log("text:" + tree_data_2[o].name + " value:"
				+ tree_data_2[o].code);

		var code = tree_data_2[o].code;
		if (code == json2[i].menuCode) {

			var item = json2[i].code + ":" + {
				code : json2[i].code,
				name : json2[i].name,
				type : json2[i].type,
				'icon-class' : json2[i].colour
			};
			m2.push(item);

			for ( var z in json3) {
				if (json2[i].code == json3[z].menu1Code) {
					var code3 = "'" + json3[z].code + "'";
					var type = "bdfy";
					if (json3[z].baiduType == "BAIKE") {

						type = "bdbk";
					}

					var item = json2[i].code + ":" + {
						code : json2[i].code,
						name : json2[i].name,
						type : json2[i].type
					};
					m3.push(item);

				}

			}
			console.log(m3);
			tree_data_2[code]['additionalParameters']['children'][json2[i].code]['additionalParameters'] = {

					'children' : [ m3]
				};
		}
		console.log(m2);
		tree_data_2[code]['additionalParameters'] = {
			'children' : m2
		};
	}

}
var treeDataSource2 = new DataSourceTree({
	data : tree_data_2
});

function bdfy(code) {
	var url = 'http://fanyi.baidu.com/translate?aldtype=16047#en/zh/' + code;
	$('#baidu').html(
			'<iframe width="100%" height="100%" src="' + url + '"></iframe>');
}

function bdbk(code) {
	var url = 'http://baike.baidu.com/item/' + code + '?sefr=enterbtn';
	$('#baidu').html(
			'<iframe width="100%" height="100%" src="' + url + '"></iframe>');
}