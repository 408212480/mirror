/**
 * Created by Administrator on 2017/8/14.
 */
jQuery(document).ready(function () {
    var deviceADId = null;
    //上传图片id
    var resourceId = null;
    var activityId = "";


    //按钮切换
    $("#btn_release").hide();
    $("#default-tab").click(function () {
        $("#btn_release").hide();
        $("#add_btn").show();
    });
    $("#release_tab").click(function () {
        $("#add_btn").hide();
        $("#btn_release").show();
    });

//区域树
    var inited = false;
    var area_list = [];

    var initAreaTree = function () {
        Request.get("area?paging=false&sorts[0].name=sortIndex", function (e) {
            area_list = e;
            console.log(e);
            var tree = areaTree.init();
            var rootNodes = tree.getRootNodes(e);
            $('#area_tree').treeview({
                data: rootNodes
                // onNodeSelected: function (event, data) {
                //     alert(data.text + "->  parentId :" + data.parentId);
                // }
            });
            $('#area_tree').treeview('selectNode', [0]);
        });
    };
    initAreaTree();
    var areaTree = {
        init: function () {
            if (inited) return this;
            if (jQuery === undefined) {
                console.error("Required jQuery support is not available");
            } else {
                inited = true;
                var that = this;
                $(function () {

                });
            }
            return this;
        },
        load: function () {

        },
        reload: function () {

        },
        getRootNodes: function (data) {
            var that = this;
            var result = [];
            $.each(data, function (index, item) {
                if (item['parentId'] == '-1') {
                    var obj = {
                        id: item.id,
                        cid: index,
                        // level: 0,
                        parentId: item.parentId,
                        text: item.areaName,
                        nodes: []
                    };
                    // console.log(item.areaName);
                    // console.log(obj);
                    obj.nodes = that.getChildNodes(data, item);
                    result.push(obj);
                }
            });
            return result;
        },
        getChildNodes: function (data, parentNode) {
            var that = this;
            var result = [];
            $.each(data, function (i, item) {
                if (item['parentId'] == parentNode['id']) {
                    var obj = {
                        id: item.id,
                        cid: i,
                        // level: parentNode[level]+1,
                        parentId: item.parentId,
                        text: item.areaName,
                        nodes: null
                    };
                    result.push(obj);
                    var childNodes = that.getChildNodes(data, item);
                    if (childNodes != null && childNodes.length > 0) {
                        obj.nodes = childNodes;
                    }
                }
            });
            return result;
        }
    };

//初始化管理页表格
    var baseTable = $("#list_table").DataTable({
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
                url: BASE_PATH + "Activity",
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
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {"data": "title"},
            {"data": "begintime"},
            {"data": "endtime"},
        ], "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [0],
                "mData": "id",
                "mRender": function (a, b, c, d) {
                    return d.row;
                }
            },
            {
                "sClass": "center",
                "aTargets": [4],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var buttons = '<div class="btn-group">';
                    buttons += '<div class="btn-group">';
                    buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';
                    if (accessUpdate) {
                        buttons += '<li><a href="javascript:;" class="btn-edit" data-id="' + c.id + '">编辑</a></li>';
                        switch (c.status) {
                            case 0:
                                buttons += '<li><a href="javascript:;" class="btn-push" data-id="' + c.id + '">推送</a></li>';
                                break;
                            case 1:
                                buttons += '<li><a href="javascript:;" class="btn-disable" data-id="' + c.id + '">禁用</a></li>';
                                break;
                            default:
                                break;
                        }
                    }
                    buttons += '<li><a href="javascript:;" class="btn-detail" data-id="' + c.id + '">详情</a></li>';
                    return buttons;
                }
            }
        ]
    });
//初始化发布页用户列表
    var userList = $("#user_list").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": false,
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
                url: BASE_PATH + "userInfo",
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
                "sClass": "text-center",
                "data": "userAccount",
                "render": function (data, type, full, meta) {
                    return '<input  type="checkbox" name="userId" class="checkchild"  value="' + data + '" />';
                },
                "bSortable": false
            },
            {
                "data": "id",
                bSortable: false,
                targets: 0,
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {"data": "telephone"},
        ]
    });
//发布活动列表初始化
    var activityTable = $("#activity_list").DataTable({
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
                url: BASE_PATH + "Activity",
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
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    return '<input  type="checkbox" name="id" class="activity-checked"  value="' + data + '" />';
                },
                "bSortable": false
            },
            {"data": "title"},
            {"data": "begintime"},
            {"data": "endtime"},
        ]
    });


//新增活动表单提交
    $("form#add_form").validate({
        rules: {
            begintime: {required: true},
            endtime: {required: true},
        },
        messages: {
            begintime: {required: "请选择活动开始时间."},
            endtime: {required: "请选择活动结束时间."},
        },
        submitHandler: function (form) {
            var begintime = $("#begintime").val();
            var endtime = $("#endtime").val();
            if (begintime > endtime) {
                toastr.error("开始时间需早于结束时间", opts);
                return false;
            }
            var btn = $('button[type="submit"]');
            btn.attr('disabled', "true");
            btn.html("保存中..请稍后");
            var params = {
                title: $("#title").val(),
                begintime: new Date(begintime),
                endtime: new Date(endtime),
                content: $("#content").val()
            };
            if (activityId == "" || activityId == null) {
                var req = Request.post;
                var url = "Activity/";
            } else {
                var req = Request.put;
                var url = "Activity/" + activityId;
            }
            req(url, JSON.stringify(params), function (e) {
                if (e.success) {
                    toastr.info("保存完毕", opts);
                    $("#modal-add").modal('hide');
                    baseTable.draw();
                } else {
                    toastr.error("保存失败", opts);
                }
                btn.html("保存");
                btn.removeAttr('disabled');
            });
        }
    });
    //编辑
    $("#list_table").off('click', '.btn-detail').on('click', '.btn-detail', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        toastr.info('加载数据中..请稍后', opts);
        var activityId = $(this).data('id');
        // modal-readonly
        Request.get('Activity/' + activityId, {}, function (e) {
            if (e.success) {
                setModalData(e.data);
                $("#modal-readonly").modal('show');
                that.removeAttr('disabled');
            } else if (e.code == 401) {
                window.location.reload();
            }
        });
    });

    //发布活动
    $("form#release_form").validate({
        submitHandler: function (form) {
            var userId = [];
            var user = $('#user_list .checkchild:checked ');
            for (var i = 0; i < user.length; i++)
                userId.push($(user[i]).attr("value"));
            activityId = $('.activity-checked:checked').val();
            // 校验数据
            if (activityId == "" || activityId == null) {
                toastr.warning("请选择一个活动发布")
                return;
            }
            if (userId.length==0) {
                toastr.warning("请选择一个用户发布活动")
                return;
            }
            //提交数据
            var data = {activityid: activityId, userIds: userId};
            console.log(data);
            var api = "Activity/release";
            // ajax
            $('button[type="submit"]').attr('disabled', true);
            Request.post(api, data, function (e) {
                console.log(e);
                $('button[type="submit"]').attr('disabled', false);
                if (e.success) {
                    toastr.info("发布成功");
                    $("#modal-release").modal('hide');
                    userList.ajax.reload();
                    activityTable.ajax.reload();
                }
                else {
                    toastr.warning(e.message)
                }
            });
        }
    });

    // 查看详细信息
    $("#list_table").off('click', '.btn-edit').on('click', '.btn-edit', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        toastr.info('加载数据中..请稍后', opts);
        activityId = $(this).data('id');
        // modal-readonly
        Request.get('Activity/' + activityId, {}, function (e) {
            if (e.success) {
                $("#modal-add").modal('show');
                $("#title").val(e.data.title);
                var begintime = e.data.begintime.replace(" ", "T")
                var endtime = e.data.endtime.replace(" ", "T");
                $("#endtime").val(endtime);
                $("#begintime").val(begintime);
                $("#content").val(e.data.content);
                that.removeAttr('disabled');
            } else if (e.code == 401) {
                window.location.reload();
            }
        });
    });
//推送
    $("#list_table").off('click', '.btn-push').on('click', '.btn-push', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        activityId = $(this).data('id');
        // modal-readonly
        Request.put('Activity/push/' + activityId, {}, function (e) {
            if (e.success) {
                toastr.info('推送成功');
                baseTable.ajax.reload();
            } else {
                toastr.error("推送失败", opts);
            }
        });
    });
    //禁用
    $("#list_table").off('click', '.btn-disable').on('click', '.btn-disable', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        activityId = $(this).data('id');
        Request.put('Activity/disabled/' + activityId, {}, function (e) {
            if (e.success) {
                toastr.info('禁用成功');
                baseTable.ajax.reload();
            } else {
                toastr.error("禁用失败", opts);
            }
        });
    });
    // 发布活动
    $('#btn_release').click(function () {
        //清空上次选择
        $("#modal-release").modal('show');
    });
    // 新增活动
    $('#add_btn').off('click').on('click', function () {
        $(".modal-title").html("新增活动");
        $("#title").val('');
        $("#content").val('');
        $("#begintime").val('');
        $("#endtime").val('');
        resourceId = null;
        $("#modal-add").modal('show');
        $(".btn-primary").html("保存");
    });


    var setModalData = function (obj) {
        $('#r-title').val(obj.title);
        $('#r-begintime').val(obj.begintime);
        $('#r-endtime').val(obj.endtime);
        $('#r-content').val(obj.content);
    };
    //复选框单选
    $(document).off('click', '.activity-checked').on('click', '.activity-checked', function () {
        if ($(this).is(':checked'))
            $(this).parents('tr').siblings().find('.activity-checked').prop("checked", false);
    })
});
