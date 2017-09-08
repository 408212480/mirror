/**
 * Created by Administrator on 2017/8/8.
 */
$(document).ready(function () {
    view();
});
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}

function view() {
    var activityId = GetQueryString('activityId')
    $.ajax({
        url: "/api/Activity/view/"+activityId,
        type: "GET",
        dataType: "json",
        success: function (data) {
        $("#title").html("<b>"+data.data.title+"<b>");
        $("#pushtime").html(data.data.pushtime);
        $("#content").html(data.data.content);
        },
        error:function (data) {
            if (data.status==401){
                toastr.error("未登录,请先登录");
            }
            if (data.status==500){
                toastr.error("未知错误");
            }
        }
    })
}/**
 * Created by Administrator on 2017/9/8.
 */
