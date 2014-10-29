/**
 * Created by Administrator on 2014/10/10.
 */


/**
 * 这个函数用来解析来自URL的查询串中的name=value参数对
 * 它将name=value对存储到在一个对象的属性中，并返回该对象
 * 这样来使用它
 */
function urlArgs(href) {
    var args = {};
    var query = href.substring(href.lastIndexOf("?")+1);
    var paris = query.split("&");
    for (var i = 0; i < paris.length; i++) {
        var pos = paris[i].indexOf("=");
        if (pos == -1) {
            continue;
        }
        var name = paris[i].substring(0,pos);
        var value = paris[i].substring(pos+1);
        value = decodeURIComponent(value);
        args[name] = value;
    }
    return args;
}
