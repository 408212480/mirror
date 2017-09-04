jQuery(document).ready(function () {

    var isAdd = true;
    var deviceADId = null;
    //上传图片id
    var resourceId = null;

    var fileinputOption = {
        required: true,
        uploadUrl: Request.BASH_PATH + 'file/singleUpload',
        dropZoneTitle: "拖拽文件到这里...",
        language: 'zh', //设置语言
        showUpload: true, //是否显示上传按钮
        showRemove: true,
        showCaption: true,//是否显示标题
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions: ['jpg', 'gif', 'png'],
        maxFileCount: 1,
        autoReplace: true,
        initialPreviewAsData: false,
        previewSettings: {
            image: {width:'auto', height: "180px"},
            video: {width:'auto', height: "180px"},
            other: {width:'auto', height: "180px"}
        }
    };


    //设置上传图片后,用resourceId记录后台返回的生成的资源id
    var fileuploadMethod = function (event, data, previewId, index) {
        if (data.response.code != '200') {
            toastr("上传图片/视频失败，请重试！", opts);
        }
        else {
            resourceId = data.response.data.id;
        }
    };

    $("#resource").fileinput(fileinputOption).unbind("fileuploaded filebatchuploadsuccess").on("fileuploaded filebatchuploadsuccess", fileuploadMethod);

    // 页面整体事件
    $('#btn_add_new').off('click').on('click', function () {
        var addType = $('.tab-content .tab-pane.active').attr('id');

        // 这里做判断
        switch (addType) {
            case 'base_list':
                // 新增广告
                isAdd = true;

                $("#effectiveTime").removeAttr("readonly");
                $("#endTime").removeAttr("readonly");
                $("#deviceADType").removeAttr("disabled");

                $("#deviceADType").selectpicker('val','0');
                $("#resource").fileinput("clear");
                $("#effectiveTime").val('');
                $("#endTime").val('');
                resourceId = null;

                $(".modal-title").html("新增广告");
                $("#modal-add").modal('show');
                $('#submitButton').html("保存");
                break;
            case 'ad_dist_list':
                // 广告发布
                var shop;
                if($('#area_tree').treeview('getSelected')){
                    shop = $('#area_tree').treeview('getSelected')[0];
                }
                if (shop != null && shop != undefined && shop['id'] != 'area_tree') {
                    loadDeviceDist(shop['id']);
                    $("#ad_dist_modal").modal('show');
                    $(".modal-title").html("选择要发布的广告");
                    $(".group-checkable").prop('checked', false);
                }
                else {
                    toastr.info("请先选择需要发布广告的店铺或区域!");
                }
                break;
        }

    });


    $('#deviceADTab').off('click').on('click', function (e) {
        $('#btn_add_new').html(" 新建广告")
    });

    $('#ADDistTab').off('click').on('click', function (e) {
        $('#btn_add_new').html(" 广告发布")
    });

    $("form#deviceAD_form").validate({
        rules: {
            deviceADType: {required: true},
            effectiveTime: {required: true},
            file: {required: true},
            endTime: {required: true},
        },
        messages: {
            deviceADType: {required: "请选择广告类型."},
            effectiveTime: {required: "请选择广告开始时间."},
            endTime: {required: "请选择广告结束时间."},
        },
        submitHandler: function (form) {
            if($(".modal-title").html() == '广告详情'){
                $("#modal-add").modal('hide');
                return;
            }
            var startTime = $("#effectiveTime").val();
            var endTime = $("#endTime").val();
            if (endTime < startTime) {
                toastr.error("生效时间需早于结束时间", opts);
                return false;
            }
            var btn = $('#submitButton');
            btn.attr('disabled', "true");
            btn.html("保存中..请稍后");
            var params = {
                resourceTypeInt: $("#deviceADType").val(),
                resourceId: resourceId,
                effectiveTime: new Date(startTime),
                endTime: new Date(endTime)
            };
            var req = Request.post;
            var url = "device-ad/";
            if (!isAdd) {
                req = Request.put;
                url = "device-ad/" + deviceADId;
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
        "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "device-ad",
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
            {"data": "resourceType"},
            {"data": "effectiveTime"},
            {"data": "endTime"},
            {"data": null}
        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [4],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var buttons = ''
                    buttons += '<button type="button" data-id="' + c.id + '" data-type="' + c.resourceType + '" data-effectivetime="' + c.effectiveTime + '" data-endtime="' + c.endTime + '" class="btn btn-default btn-sm btn-info">详情</button>\n';
                    buttons += '<button type="button" data-id="' + c.id + '" data-type="' + c.resourceType + '" data-effectivetime="' + c.effectiveTime + '" data-endtime="' + c.endTime + '" class="btn btn-default btn-sm btn-edit">编辑</button>\n';
                    return buttons;
                }
            }
        ]
    });


    //设置上传资源类型与广告类型一致
    $("#deviceADType").off("hide.bs.select").on("hide.bs.select", function (e) {
        var typeSelected = $("#deviceADType").val();
        if (typeSelected == "0") {
            $("#resource").fileinput('refresh', {
                allowedPreviewTypes: ['image'],
                allowedFileTypes: ['image'],
                allowedFileExtensions: ['jpg', 'gif', 'png']
            });
        }
        else {
            $("#resource").fileinput('refresh', {
                allowedPreviewTypes: ['video'],
                allowedFileTypes: ['video'],
                allowedFileExtensions: ['mp4']
            });
        }
    });


    var initForm = function (title, that, isEdit) {
        var type = that.data('type');
        var url = "device-ad/resource/" + that.data('id');
        Request.get(url, null, function (e) {
            if (e.success) {

                if (e.data["type"] == 0) {
                    $("#deviceADType").selectpicker('val','0');

                    $('#resource').fileinput('refresh',{
                        initialPreview:['<img src="' + e.data["md5"] + '" class="file-preview-image" id="initPreview" height="160px"/>'],
                        initialPreviewConfig: [{caption: e.data['name'], url:null}],
                        allowedPreviewTypes: ['image'],
                        allowedFileTypes: ['image'],
                        allowedFileExtensions: ['jpg', 'gif', 'png']
                    });
                }
                else {
                    $("#deviceADType").selectpicker('val','1');

                    $('#resource').fileinput('refresh',{
                        initialPreview:['<video src="' + e.data["md5"] + '" class="file-preview-other" id="initPreview" height="160px" />'],
                        initialPreviewConfig: [{caption: e.data['name'], url:null}],
                        allowedPreviewTypes: ['video'],
                        allowedFileTypes: ['video'],
                        allowedFileExtensions: ['mp4']
                    });
                }

                resourceId = e.data["resourceId"];

                $("#effectiveTime").val(that.data('effectivetime').replace(" ", "T"));
                $("#endTime").val(that.data('endtime').replace(" ", "T"));

                if(isEdit){
                    $('#resource').fileinput('enable');
                    $("#effectiveTime").removeAttr("readonly");
                    $("#endTime").removeAttr("readonly");
                    $("#deviceADType").removeAttr("disabled");
                    $("#submitButton").html("保存");
                }
                else{
                    $('#resource').fileinput('disable');
                    $("#effectiveTime").attr("readonly", "readonly");
                    $("#endTime").attr("readonly", "readonly");
                    $("#deviceADType").attr("disabled", "disabled");
                    $("#submitButton").html("确定");
                }
                $(".modal-title").html(title);
                $("#modal-add").modal('show');

            }
            else{
                toastr.info("数据加载失败");
            }

        });

    };


    //详情按钮事件
    $("#base_list").off('click', '.btn-info').on('click', '.btn-info', function () {
        var that = $(this);
        that.attr("disabled", "disabled");
        deviceADId = that.data('id');

        initForm("广告详情", that, false);
        that.removeAttr("disabled")
    });

    //编辑按钮事件
    $("#base_list").off('click', '.btn-edit').on('click', '.btn-edit', function () {
        isAdd = false;
        var that = $(this);
        deviceADId = that.data('id');

        that.attr("disabled", "disabled");
        initForm("编辑广告", that, true);
        that.removeAttr("disabled");
    });
});

$(document).ready(function () {
    var inited = false;
    var area_list = [];
    var ad_dist = null;

    var initAreaTree = function () {
        Request.get("area?paging=false&sorts[0].name=sortIndex", function (e) {
            area_list = e;
            Request.get("shop?paging=false", function (e) {
                for (var i = 0; i < e.length; i++) {
                    e[i].parentId = e[i]['areaId'];
                    e[i].areaName = e[i]['shopName'];
                    area_list.push(e[i]);
                }
                var tree = areaTree.init();

                var rootNodes = tree.getRootNodes(area_list);
                $('#area_tree').treeview({
                    data: rootNodes
                });
                $('#area_tree').on('nodeSelected', function (event, data) {
                    if (ad_dist != null) {
                        ad_dist.columns().draw();
                    }
                });
                // $('#area_tree').treeview('selectNode', [0]);
            });
        });
    };

    initAreaTree();

    var areaTree = {
        init: function () {
            if (inited) return this;
            if (jQuery === undefined) {
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
                        is_shop: item['shopName'] ? true : false,//本节点区域还是店铺
                        // level: 0,
                        parentId: item.parentId,
                        text: item.areaName,
                        nodes: []
                    };
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
                        is_shop: item['shopName'] ? true : false,        //本节点区域还是店铺
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

    ad_dist = $('#ad_dist').DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "bFilter": true, //搜索栏
        "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            var selectedNode = $("#area_tree").treeview('getSelected', null);
            $.ajax({
                url: BASE_PATH + "device-ad/ad-dist/" + selectedNode[0]['id'],
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
                data: "id",
                bSortable: false,
                targets: 0,
                width: "30px",
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {data: "userName"},
            {data: "resourceType"},
            {data: "uploadTime"},
            {data: "effectiveTime"},
            {data: "endTime"},
            {data: "md5"},
            {data: 'status'}
        ],
        "order": [[1, 'asc']],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [6],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var buttons = '';
                    if(c.resourceType == "图片"){
                        buttons += "<img src='" + c.md5 + "' width='50px' height='40px' />\n";
                    }
                    else{
                        buttons += "<video src='" + c.md5 + "' width='60px'/>\n";
                    }
                    return buttons;
                }
            },
            {
                "sClass": "center",
                "aTargets": [7],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '';
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

    $("input[class='form-control input-sm']").on('keydown', function (event) {

        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc
            //要做的事情
        }
        if (e && e.keyCode == 113) { // 按 F2
            //要做的事情
        }
        if (e && e.keyCode == 13) { // enter 键
            alert(this.value);
            ad_dist.search(this.value).draw();
        }
    });

    //禁用和启用按钮事件
    $("#ad_dist").off('click', '.btn-disable').on('click', '.btn-disable', function () {
        var that = $(this);
        var adDeviceId = that.data('id');
        if(that.html() == '禁用'){
            confirm('警告', '真的要禁用该广告吗?', function () {
                Request.put("device-ad/disable/" + adDeviceId, {}, function (e) {
                    if (e.success) {
                        toastr.success("解绑成功");
                        that.removeClass('btn-danger');
                        that.addClass('btn-info');
                        that.html('启用');
                    } else {
                        toastr.error(e.message);
                    }
                });
            });
        }
        else{           //启用按钮事件
            Request.put("device-ad/enable/" + adDeviceId, {}, function (e) {
                if (e.success) {
                    toastr.success("解绑成功");
                    that.removeClass('btn-info');
                    that.addClass('btn-danger');
                    that.html('禁用');
                } else {
                    toastr.error(e.message);
                }
            });
        }


    });
});
//get use data list
var loadDeviceDist = function (shopId) {
    // 加载使用列表数据
    var ad_List = $('#ad_list').DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "serverSide": true,
        "sPaginationType": "full_numbers",
        "retrieve": true,
        "ajax": function (data, callback, settings) {
            var param = {
                pageSize: data.length,
                pageIndex: data.start,
                page: (data.start / data.length) + 1
            };
            $.ajax({
                url: BASE_PATH + "device-ad/ad_list",
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
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {
                data: "id",
                bSortable: false,
                targets: 0,
                width: "30%",
                render: function (data, type, row, meta) {
                    // 显示行号
                    var startIndex = meta.settings._iDisplayStart;
                    return startIndex + meta.row + 1;
                }
            },
            {data: "resourceType"},
            {data: "effectiveTime"},
            {data: "endTime"},
            {data: "md5"}
        ],
        drawCallback: function () {
            // 取消全选
            $(":checkbox[name='keeperDeviceGroup-checkable']").prop('checked', false);
        },
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [5],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var buttons = '';
                    if(c.resourceType=="图片"){
                        buttons += "<img src='" + c.md5 + "' width='50px' height='40px'/>\n";
                    }
                    else{
                        buttons += "<video src='" + c.md5 + "' width='60px'/>\n";
                    }

                    return buttons;
                }
            }
        ]
    });

    $('#ad_dist_modal').on("change", ":checkbox", function () {
        // 列表复选框
        if ($(this).is("[name='keeperDeviceGroup-checkable']")) {
            // 全选
            $(":checkbox", '#ad_list').prop("checked", $(this).prop("checked"));
        } else {
            // 一般复选
            var checkbox = $("tbody :checkbox", '#ad_list');
            $(":checkbox[name='cb-check-all']", '#ad_list').prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    });

    //处理发布确定按钮
    $("#ad_dist_modal").off('click', '#distSubmit').on('click', '#distSubmit', function () {
        $("#distSubmit").html("保存中...");
        $("#distSubmit").attr('disabled',"disabled");
        var idChecked = [];
        var adList = $(".group-checkable");
        for (var i = 0; i < adList.length; i++) {
            if (adList[i]['checked'] == true) {
                idChecked.push(adList[i].dataset['id']);
            }
        }

        Request.post("device-ad/ad-distribution/area/" + shopId, idChecked, function (e) {
            if (e.success) {
                toastr.info("保存完毕", opts);
                $("#ad_dist_modal").modal('hide');
                $("#base_data_table").DataTable().draw();
            } else {
                toastr.error("保存失败", opts)
            }
            $("#distSubmit").html("保存");
            $("#distSubmit").removeAttr('disabled');
        } );

    });
};

var CONSTANT = {
    DATA_TABLES: {
        DEFAULT_OPTION: { // DataTables初始化选项
            LANGUAGE: {
                sProcessing: "处理中...",
                sLengthMenu: "显示 _MENU_ 项结果",
                sZeroRecords: "没有匹配结果",
                sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                sInfoFiltered: "(由 _MAX_ 项结果过滤)",
                sInfoPostFix: "",
                sSearch: "搜索:",
                sUrl: "",
                sEmptyTable: "表中数据为空",
                sLoadingRecords: "载入中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNext: "下页",
                    sLast: "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            // 禁用自动调整列宽
            autoWidth: false,
            // 取消默认排序查询,否则复选框一列会出现小箭头
            order: [],
            // 隐藏加载提示,自行处理
            processing: false,
            // 启用服务器端分页
            serverSide: true
        },
        COLUMN: {
            // 复选框单元格
            CHECKBOX: {
                className: "td-checkbox",
                orderable: false,
                bSortable: false,
                width: "15%",
                data: "id",
                render: function (data, type, row, meta) {
                    var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';
                    content += '    <input type="checkbox" class="group-checkable" data-id="' + data + '" />';
                    content += '    <span></span>';
                    content += '</label>';
                    return content;
                }
            }
        }
    }
};
