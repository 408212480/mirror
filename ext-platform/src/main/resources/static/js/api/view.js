/**
 * Created by Administrator on 2017/8/8.
 */
$(document).ready(function () {
    getshopView();
});
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}

function getshopView() {
    var shopId = GetQueryString('shopId')
    var data = {shopId: shopId}
    $.ajax({
        url: "/api/shop/view",
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            $("#img1").attr("src",data.data.img1);
            $("#img2").attr("src",data.data.img2);
            $("#img3").attr("src",data.data.img3);
            $("#logo").attr("src",data.data.md5);
            $("#shop-name").html("<b>"+data.data.shopName+"</b>");
            $(".shopView").append(data.data.content);
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
}