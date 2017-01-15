//uploadplug extend(功能扩展)
(function($){
    var fileinput = $.fn.fileinput.Constructor,
        // _initFileActions = fileinput.prototype.initFileActions,
        _clear = fileinput.prototype.clear;
    fileinput.prototype.clear = function () {
        var that = this;
        var index = layer.confirm("确定要移除所有附件?",function(){
            layer.close(index);
            _clear.apply(that, Array.prototype.slice.apply(arguments));
        });
    }
})(jQuery);