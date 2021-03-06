$(document).ready(function () {
    var id= null;
    // 事件绑定
    $("#role_list").off('click', '.btn-edit').on('click', '.btn-edit', function () {
        var that = $(this);
        id = that.data('id');
        // 加载要修改的数据到 modal
        loadData(id);
        $("#modal-edit").modal('show');
    });
    // 加载修改信息数据
    var loadData = function (id) {
        Request.get("device/" + id, {}, function (e) {
            if (e.success) {
                setTimeout(function () {
                    setCheckedActions(e.data.modules)
                }, 10);
                $("form#edit_form").find("#e_device_title").val(e.data.title);
                $("form#edit_form").find("#e_device_order").val(e.data.seqCode);
                $("form#edit_form").find("#e_device_code").val(e.data.code);
                $("form#edit_form").find("#e_dtp_input1").val(e.data.productionTime);
                $("form#edit_form").find("#e_dtp_input2").val(e.data.factoryTime);
                $("form#edit_form").find("#e_dtp_input3").val(e.data.usageTime);
                $("form#edit_form").find("#e_device_info").val(e.data.remark);
            }
        });
    }


    $("#role_list").off('click', '.btn-del').on('click', '.btn-del', function () {
        var that = $(this);
        var id = that.data('id');
        dialog.delete('删除提示', '真的要删除 ID:' + id + " 吗？", function () {
            Request.delete('device/' + id, function (e) {
                if (e.success) {
                    // 删除，删除成功后调用列表重载
                    toastr.info("删除成功", opts);
                    roleList.ajax.reload();
                } else {
                    toastr.error("删除失败", opts);
                }
            });
        });
    });

    // 事件绑定结束
    //点击新增数据
    $(document).off('click','.btn-add-data').on('click', '.btn-add-data', function () {
        $("input#device_title").val("");
        $("input#device_order").val("");
        $("input#device_code").val("");
        $("input#dtp_input1").val("");
        $("input#dtp_input2").val("");
        $("input#dtp_input3").val("");
        $("input#device_info").val("");

        $("#modal-add").modal('show');

    });

    // 新增
    $("form#add_form").validate({
        rules: {
            device_title: {required: true},
            device_order: {required: true},
            device_code:{required: true}
        },
        messages: {
            device_title: {required: "设备标题不能为空"},
            device_order: {required: "设备序号不能为空"},
            device_code: {required: "设备编码不能为空"}
        },

        submitHandler: function (form) {

            var btn = $('button[type="submit"]');
            btn.attr('disabled',"true")

            var params = {
                modules: [],
                title: $(form).find("#device_title").val(),
                seqCode: $(form).find("#device_order").val(),
                code: $(form).find("#device_code").val(),
                productionTime: $(form).find("#dtp_input1").val(),
                factoryTime: $(form).find("#dtp_input2").val(),
                usageTime: $(form).find("#dtp_input3").val(),
                remark: $(form).find("#device_info").val(),
                type: ""
            };
            params.modules = getCheckedActions('add_form');

            // 发送新增请求
            Request.post("device/", JSON.stringify(params), function (e) {
                if (e.success) {
                    // 弹出 新增成功 提示，关闭 modal 框
                    toastr.info("新增成功", opts);
                    $("#modal-add").modal('hide');
                    // 移除保存按钮的禁用状态
                    btn.removeAttr('disabled');
                    // 列表重载
                    roleList.ajax.reload();
                } else {
                    toastr.error("新增失败,设备编码不能超过10位，设备序号不能超过32位！！", opts);
                    // 移除保存按钮的禁用状态
                    btn.removeAttr('disabled');
                    // location.reload();
                }
            });

        }
    });


    // 修改
    $("form#edit_form").validate({
        rules: {
            e_device_title: {required: true},
            e_device_order: {required: true},
            e_device_code:{required: true}
        },
        messages: {
            e_device_title: {required: "设备标题不能为空"},
            e_device_order: {required: "设备序号不能为空"},
            e_device_code: {required: "设备编码不能为空"}
        },
        submitHandler: function (form) {
            var btn = $('button[type="submit"]');
            btn.attr('disabled',"true");
            var params = {
                modules: [],
                title: $(form).find("#e_device_title").val(),
                seqCode: $(form).find("#e_device_order").val(),
                code: $(form).find("#e_device_code").val(),
                productionTime: $(form).find("#e_dtp_input1").val(),
                factoryTime: $(form).find("#e_dtp_input2").val(),
                usageTime: $(form).find("#e_dtp_input3").val(),
                remark: $(form).find("#e_device_info").val(),
                type: ""
            };
            params.modules = getCheckedActions('edit_form');
            // 发送修改请求
            Request.put("device/" + id, JSON.stringify(params), function (e) {
                if (e.success) {
                    // 弹出 新增成功 提示，关闭 modal 框
                    toastr.info("修改成功", opts);
                    $("#modal-edit").modal('hide');
                    // 移除保存按钮的禁用状态
                    btn.removeAttr('disabled');
                    // 列表重载
                    roleList.ajax.reload();
                } else {
                    toastr.error("修改失败", opts);
                }
            });

        }
    });
    function getCheckedActions(form_id) {
        var actions = {};
        $("form#"+form_id+" input[type='checkbox']").each(function (i, e) {
            var moduleId = $(e).data("mid");
            var action = $(e).val();
            if ($(e).prop("checked")) {
                var action_mapping = actions[moduleId];
                if (!action_mapping) {
                    action_mapping = [];
                    actions[moduleId] = action_mapping;
                }
                action_mapping.push(action);
            }
        });
        var newData = [];
        for (var f in actions) {
            newData.push({moduleId: f, actions: actions[f]});
        }
        return newData;
    }

    function setCheckedActions(actions) {
        $("form#edit_form input[type='checkbox']").prop("checked", false);
        var actionsMap = {};
        $(actions).each(function (i, e) {
            actionsMap[e.moduleId] = e.actions;
        });
        $("form#edit_form input[type='checkbox']").each(function (i, e) {
            var moduleId = $(e).data("mid")
            var action = $(e).val();
            if (!actionsMap[moduleId])return;
            if (actionsMap[moduleId].indexOf(action) != -1) {
                $(e).prop("checked", "checked");
            }
        });
    }

    // 加载列表数据
    var roleList = $('#role_list').DataTable({
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
                url: BASE_PATH + "device",
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
            {"data": "id"},
            {"data": "title"},
            {"data": "seqCode"},
            {"data": "code"},
            {"data": "productionTime"},
            {"data": "factoryTime"},
            {"data": "usageTime"},
            {"data": "remark"}
        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [0],
                "mData": "id",
                "mRender": function (a, b, c, d) {
                    return d.row;
                }
            },
            {
                "sClass":"center operate-group",
                "aTargets":[8],
                "mData":"id",
                "mRender":function(a,b,c,d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '<div class="btn-group" >';
                    buttons += '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';

                    buttons += '<li><a href="javascript:;" data-toggle="modal" data-target="#myModal" class="btn-look" data-id="'+a+'">使用记录</a></li>';
                    buttons += '<li><a href="javascript:;" class="btn-detail" data-id="' + a + '">详情</a></li>';
                    if (accessUpdate) {
                        buttons += '<li><a href="javascript:;" class="btn-edit" data-id="'+a+'">编辑</a></li>';
                    }
                    if (accessDelete) {
                        buttons += '<li><a href="javascript:;" class="btn-del" data-id="'+a+'">删除</a></li>';
                    }

                    buttons += '</ul></div>';

                    return buttons;
                }
            }
        ]
    });



    // 点击显示设备详情模态框
    $("#role_list").off('click', '.btn-detail').on('click', '.btn-detail', function () {
        var id  = $(this).data('id');
        // 加载要修改的数据到 modal
        loadDataDetail(id);

        $("#modal-info").modal('show');
    });

    // 加载视频信息
    var loadDataDetail = function (id) {
        Request.get("device/" + id, {}, function (e) {
            if (e.success) {
                setTimeout(function () {
                }, 10);
                $("form#info_form").find("#title").val(e.data.title);
                $("form#info_form").find("#seqCode").val(e.data.seqCode);
                $("form#info_form").find("#code").val(e.data.code);
                $("form#info_form").find("#productionTime").val(e.data.productionTime);
                $("form#info_form").find("#factoryTime").val(e.data.factoryTime);
                $("form#info_form").find("#usageTime").val(e.data.usageTime);
                $("form#info_form").find("#remark").val(e.data.remark);
            }
        });
    }

    //点击查看使用记录
    $(document).off('click','.btn-look').on('click', '.btn-look', function () {
        var data_identify  = $(this).data('id');
        loadUserList(data_identify);

    });



    $(document).off('click','.btn-use-history-close').on('click', '.btn-use-history-close', function () {
        location.reload();
        // roleList.ajax.reload();
    });
    //get use data list
    var useroleList;
    var loadUserList = function (data_identify) {
        // 加载使用列表数据
        useroleList = $('#use_list').DataTable({
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
            "retrieve": true,
            "ajax": function (data, callback, settings) {
                var param = {
                    pageSize: data.length,
                    pageIndex: data.start,
                    page: (data.start / data.length) + 1
                };
                $.ajax({
                    url: BASE_PATH + "device/usehistory/" + data_identify,
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
                {"data": "id"},
                {"data": "shop_name"},
                {"data": "address"},
                {"data": "start_time"},
                {"data": "end_time"},
                {"data": "use_type"},
                {"data": "expense"}
            ]
        });
    };
});