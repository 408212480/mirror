jQuery(document).ready(function () {

    var isAdd = true;
    var goodsPercentageId = null;
    // 页面整体事件

    $('#btn_add_new').off('click').on('click', function () {
        var addType = $('.tab-content .tab-pane.active').attr('id');
        console.log('选择分页卡', addType);

        // 这里做判断
        switch (addType) {
            case 'base_list':
                // 基本信息分页新增
                isAdd = true;
                var data;
                var goodsCodeList = [];
                $("#goods_code").attr("disabled", false);
                $("#goods_code").empty();
                $.ajax({
                    url: "/dividend/goods_shop",
                    type: "GET",
                    cache: false,
                    data: "",
                    dataType: "json",
                    success: function (result) {
                        console.log(result);
                        data = result.data;
                        for (var i = 0; i < data.length; i++) {
                            $('#goods_code').append("<option value=" + data[i]["id"] + ">" + data[i]["id"] + "</option>");
                        }
                        $('#goods_code').selectpicker('refresh');
                        $('#goods_code').selectpicker('render');
                    },
                    error: function (jqXhr) {
                        toastr.warning("请求列表数据失败, 请重试");
                    }
                });

                $("#goods_code").off("hide.bs.select").on("hide.bs.select", function (e) {
                    var selectedCode = $("#goods_code").val();
                    if (selectedCode != undefined) {
                        for(var i = 0; i< data.length; i++){
                            if(selectedCode == data[i]["id"]){
                                $("#goods_name").val(data[i]["title"]);
                                $("#shop_name").val(data[i]["shopName"]);
                                break;
                            }
                        }
                    }
                });
                $("#goods_name").val('');
                $("#shop_name").val('');
                $("#percentage").val('');
                $("#modal-add").modal('show');
                $(".modal-title").html("新增");

                break;
            case 'class_list':
                // 规格列表分页新增
                break;
            case 'comment_list':
                // 评价列表分页新增
                break;
        }

    });

    $("form#dividend_form").validate({
        rules: {
            goods_code: {required: true},
            goods_name: {required: true},
            shop_name: {required: true},
            percentage: {required: true ,number:true}
        },
        messages: {
            goods_code: {required: "服装编码不能为空."},
            goods_name: {required: "商品名不能为空."},
            shop_name: {required: "店铺名称不能为空."},
            percentage: {number: "请输入一个正整数折扣."}
        },
        submitHandler: function (form) {
            $("#goods_code").attr("disabled", false);
            var btn = $('button[type="submit"]');
            btn.attr('disabled', "true");
            btn.html("保存中..请稍后");
            var params = {
                id: null,
                goodsId: $("#goods_code").val(),
                percentage: $(form).find("#percentage").val()
            };
            var req = Request.post;
            var url = "dividend/";
            if (!isAdd) {
                params.id = goodsPercentageId;
                req = Request.put
                url = "dividend/" + goodsPercentageId;
            }

            req(url, JSON.stringify(params), function (e) {
                if (e.success) {
                    toastr.info("保存完毕", opts);
                    $("#modal-add").modal('hide');
                    baseTable.draw();
                    userDividendList.draw();
                } else {
                    toastr.error("保存失败", opts)
                }

                btn.html("保存");
                btn.removeAttr('disabled');
            });
        }
    });

    // 分佣管理列表
    var baseTable = $("#base_data_table").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "dividend",
                type: "GET",
                cache: false,
                data: param,
                dataType: "json",
                success: function (result) {
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
            {
                "data": "id",
                bSortable: false,
                targets: 0,
                width: "30px",
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {"data": "goodsId"},
            {"data": "title"},
            {"data": "shopName"},
            {"data": "percentage"},
            {"data": "dividend"},
            {"data": "buyedCount"},
            {"data": "dividendCount"},
            {"data": "status"}
        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [8],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var buttons = ''
                    buttons += '<button type="button" data-id="' + c.id + '" class="btn btn-default btn-sm btn-edit">编辑</button>\n';
                    if(c.status == 0){
                        buttons += '<button type="button" data-id="' + c.id + '" class="btn btn-danger btn-sm btn-disable">禁用</button>\n';
                    }else{
                        buttons += '<button type="button" data-id="' + c.id + '" class="btn btn-info btn-sm btn-disable">启用</button>\n';
                    }
                    return buttons;
                }
            }
        ]
    });

    //分佣用户列表
    var userDividendList = $("#user_list_table").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "bFilter": true, //搜索栏
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "dividend/userDividendList",
                type: "GET",
                cache: false,
                data: param,
                dataType: "json",
                success: function (result) {
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
            {
                "data": "id",
                bSortable: false,
                targets: 0,
                width: "30px",
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {"data": "userAccount"},
            {"data": "name"},
            {"data": "buyedCount"},
            {"data": "dividend"}
        ]
    });

    //禁用和启用按钮事件
    $("#base_list").off('click', '.btn-disable').on('click', '.btn-disable', function () {
        var that = $(this);
        var percentge_id = that.data('id');
        if(that.html() == "禁用"){
            confirm('警告', '真的要禁用这项分佣吗?', function () {

                Request.put("dividend/percentage/enable/" + percentge_id, {}, function (e) {
                    if (e.success) {
                        that.removeClass('btn-danger');
                        that.addClass('btn-info');
                        that.html('启用');
                    } else {
                        toastr.error(e.message);
                    }
                });
            });
        }else{
            Request.put("dividend/percentage/disable/" + percentge_id, {}, function (e) {
                if (e.success) {
                    that.removeClass('btn-info');
                    that.addClass('btn-danger');
                    that.html('禁用');
                } else {
                    toastr.error(e.message);
                }
            });
        }

    });

    //编辑按钮事件
    $("#base_list").off('click', '.btn-edit').on('click', '.btn-edit', function () {
        isAdd=false;
        var that = $(this);
        var percentge_id = that.data('id');
        goodsPercentageId = percentge_id;
        var rows = baseTable.rows().data();
        for (var i = 0; i < rows.length; i++) {
            if (rows[i]["id"] == percentge_id) {
                $(".modal-title").html("编辑");
                $("#modal-add").modal('show');

                $("input#shop_name").val(rows[i]["shopName"]);
                $("input#goods_name").val(rows[i]["title"]);
                $("input#percentage").val(rows[i]["percentage"]);
                $("#goods_code").off("hide.bs.select");
                $("#goods_code").attr("disabled", true);
                $("#goods_code").empty();
                $('#goods_code').append("<option value=" + rows[i]["goodsId"] + ">" + rows[i]["goodsId"] + "</option>");
                $("#goods_code").selectpicker('val', rows[i]["goodsId"]);
                break;
            }
        }
    });

});
