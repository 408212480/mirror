$(document).ready(function () {
    var specId = "";
    var device_fid_detail_list = "";
    // 射频信息、射频详情列表
    $.ajax({
        url: BASE_PATH + "radiofrequency/getShopFid/",
        type: "GET",
        cache: false,
        dataType: "json",
        success: function (result) {
            var device_fid_list = "";
            $.each(result.data, function (i, e) {
                device_fid_list += "<tr><td>" + e.id + "</td>" + "<td>" + e.devCode + "</td></tr>"
                device_fid_detail_list += "<tr><td><input type='checkbox'  class='checkchild' data-value='" + e.id + "'></td><td>" + e.id + "</td><td>" + e.devNum + "</td><td>" + e.devCode + "</td></tr>"

            });
            $("#device_fid_list").append(device_fid_list);
            $("#device_fid_detail_list").append(device_fid_detail_list);
        },
        error: function (jqXhr) {
            toastr.warning("请求列表数据失败, 请重试");
        }
    });
    // 商品规格及信息列表
    var goodInfoSpecTable = $("#goods_spec_list").DataTable({
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
                url: BASE_PATH + "goodsinfospec/getShopGoods/",
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
            {"data": "id"},
            {"data": "classCode"},
            {"data": "title"},
            {"data": "size"},
            {"data": "color"}
        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [5],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '<div class="btn-group">';
                    buttons += '<div class="btn-group">';
                    buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';
                    if (accessUpdate) {
                        buttons += '<li><a href="javascript:;" class="btn-add" data-id="' + a + '">分配</a></li>';
                        buttons += '<li><a href="javascript:;" class="btn-del" data-id="' + a + '">解绑</a></li>';
                    }
                    buttons += '</ul></div></div>';
                    return buttons;
                }
            }
        ]
    });
    //新增或修改用户验证
    $("form#device_goods_form").validate({
        submitHandler: function (form) {
            var fidId = $('.checkchild:checked ').attr('data-value');
            //提交数据
            var data = {specId: specId, fidId: fidId};
            var api = "goodsinfospec/addFidSpec/";
            // ajax
            $('button[type="submit"]').attr('disabled', true);
            Request.put(api, data, function (e) {
                console.log(e);
                $('button[type="submit"]').attr('disabled', false);
                if (e.success) {
                    toastr.info("分配成功");
                    $("#modal-add").modal('hide');
                }
                else {
                    toastr.warning(e.message)
                }
            });
        }
    });
    //解绑弹窗确认操作
    $(document).off('click', '.btn-del-sure').on('click', '.btn-del-sure', function () {
        Request.put("goodsinfospec/" + specId + "/delFidSpec", {}, function (e) {
            if (e.success) {
                toastr.info("解绑成功!");
            } else {
                toastr.warning(e.message);
            }
        });
    });
    //点击分配弹出操作
    $(document).off('click', '.btn-add').on('click', '.btn-add', function () {
        specId = $(this).data("id");
        $(".modal-title").html("选择射频");
        $("#modal-add").modal('show');
        clearData();
        Request.get("goodsinfospec/"+specId+"/getFidSpec",{},function (e) {
            if (e.success) {
                var fidId=e.data;
                var checkbox=$(".checkchild");
                for (i=0;i<checkbox.length;i++){
                    if ($(checkbox[i]).attr("data-value")==fidId){
                        $(checkbox[i]).prop("checked", true);
                    }
                }
            } else {
                toastr.error("请求数据失败, 请重试");
            }
        })
    });
    //点击解绑弹出操作
    $(document).off('click', '.btn-del').on('click', '.btn-del', function () {
        specId = $(this).data("id");
        $("#modal-remove").modal('show');
    });
    //表单数据清空
    function clearData() {
        $("input.checkchild").prop("checked", false);
    }

    //复选框单选
    $(document).off('click', '.checkchild').on('click', '.checkchild', function () {
        if ($(this).is(':checked'))
            $(this).parents('tr').siblings().find('.checkchild').prop("checked", false);
    })
});

