$(document).ready(function () {
    var order_id = "";
    // 订单信息列表
    var row_index=0;
    var goodInfoSpecTable = $("#order_info_list").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "bFilter": false, //搜索栏
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "goodsorderinfo",
                type: "GET",
                cache: false,
                data: param,
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    var resultData = {};
                    resultData.draw = data.draw;
                    resultData.recordsTotal = result.total;
                    resultData.recordsFiltered = result.total;
                    resultData.data = result.data;
                    callback(resultData);
                },
                error: function (jqXhr) {
                    toastr.warning("请求列表数据失败, 请重试");
                }
            });
        },
        columns: [
            {"data": "id","render":function (data,index,obj) {
                row_index++;
                return row_index;
            }},
            {"data": "orderId"},
            {"data": "username"},
            {"data": "goodsName"},
            {"data": "color"},
            {"data": "size"},
            {"data": "acount"},
            {"data": "price"},
            {"data": "shopName"},
            {
                "data": "orderStatus", "render": function (data) {
                switch (data) {
                    case 1:
                        return '<span style="color: #F00">未支付</span>';

                    case 2:
                        return '<span style="color: #84C1FF">待发货</span>';

                    case 3:
                        return '<span style="color: #A6FFFF">待收货</span>';

                    case 4:
                        return '<span style="color: #FFBB77">待评价</span>';

                    case 5:
                        return '<span style="color: #FF5809">退款</span>';
                    default:
                        return '<span style="color: #FFCBB3">关闭订单</span>';
                }
            }
            },
            {"data": "submitTime"}

        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [11],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '<div class="btn-group">';
                    buttons += '<div class="btn-group">';
                    buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';
                    if (accessUpdate) {
                        if (c.orderStatus == 2) {
                            buttons += '<li><a href="javascript:;" class="btn-send" data-id="' + c.orderId + '">发货</a></li>';
                        }
                        buttons += '<li><a href="javascript:;" class="btn-del" data-id="' + c.orderId  + '">退款</a></li>';
                        buttons += '<li><a href="javascript:;" class="btn-close" data-id="' + c.orderId  + '">关闭</a></li>';
                    }
                    buttons += '</ul></div></div>';
                    return buttons;
                }
            }
        ]
    });
    //发货
    $("#send_form").validate({
        submitHandler: function (form) {
            //提交数据
            var data = {
                orderId: order_id,
                orderExpressNo: $("#orderExpressNo").val(),
                orderExpressCompany: $("#orderExpressCompany").val()
            };
            console.log(data);
            var api = "goodsorderinfo/sendOrder/";
            // ajax
            $('button[type="submit"]').attr('disabled', true);
            Request.put(api, data, function (e) {
                console.log(e);
                $('button[type="submit"]').attr('disabled', false);
                if (e.success) {
                    toastr.info("发货成功");
                    $("#modal-send").modal('hide');
                    goodInfoSpecTable.ajax.reload();
                }
                else {
                    toastr.warning(e.message)
                }
            });
        }
    });
    //点击发货弹出操作
    $(document).off('click', '.btn-add').on('click', '.btn-send', function () {
        clearData();
        order_id = $(this).data("id");
        $("#modal-send").modal('show');
    });
    //清空表单数据
    function clearData() {
        $("#orderExpressNo").val("");
        $("#orderExpressCompany").val("");
    }

});

