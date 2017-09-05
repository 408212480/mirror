
$(document).ready(function () {
    var inited = false;
    var area_list = [];
    var device_list = null;
    var shopId = null;
    var device_dist_List = null;

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
                    if (device_list != null) {
                        device_list.columns().draw();
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

    device_list = $('#device_list').DataTable({
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
                url: BASE_PATH + "devicedist/shopdevice/" + selectedNode[0]['id'],
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
                    if(result.data == null){
                        resultData.data = [];
                    }
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
            {data: "code"},
            {data: "gmtCreate"},
            {data: "deviceId"}
        ],
        "order": [[1, 'asc']],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [3],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '';
                    buttons += '<button type="button" data-id="' + c.id + '" class="btn btn-default btn-sm btn-relieve">解绑</button>\n';
                    return buttons;
                }
            }
        ]
    });

    $("input[class='form-control input-sm']").on( 'keydown', function (event) {

        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc
            //要做的事情
        }
        if (e && e.keyCode == 113) { // 按 F2
            //要做的事情
        }
        if (e && e.keyCode == 13) { // enter 键
            alert(this.value);
            device_list.search( this.value ).draw();
        }
    } );

    //解绑按钮事件
    $("#device_list").off('click', '.btn-relieve').on('click', '.btn-relieve', function () {
        var that = $(this);
        var shop_device_id = that.data('id');
        confirm('警告', '真的要解绑设备吗?', function () {
            Request.delete("devicedist/" + shop_device_id, {}, function (e) {
                if (e.success) {
                    toastr.success("解绑成功");
                    device_list.columns().draw();
                } else {
                    toastr.error(e.message);
                }
            });
        });

    });

    //处理分配按钮按钮事件
    $(".box-tools").off('click', '.btn-dist').on('click', '.btn-dist', function () {
        var shop = $('#area_tree').treeview('getSelected', null)[0];
        if (shop != undefined && shop['is_shop']) {
            shopId = shop['id'];
            loadDeviceDist(shop['id']);
            $("#dist_modal").modal('show');
            $(".group-checkable").prop('checked',false);
        }
        else {
            toastr.warning("请先选择需要添加设备的店铺!");
        }
    });

    //处理分配初始化按钮事件
    $(".box-tools").off('click', '.btn-init').on('click', '.btn-init', function () {
        var areaSelected = $('#area_tree').treeview('getSelected', null)[0];
        if(areaSelected == undefined || areaSelected['id'] == 'area_tree'){
            toastr.info("请先选择需要进行分配初始化的区域或者店铺!");
            return false;
        }
        confirm('警告', '真的要初始化列表内的设备吗?，初始化后所有设备与店铺将解绑！', function () {
            Request.delete("devicedist/reset/" + areaSelected['id'], function (e) {
                if (e.success) {
                    toastr.success("解绑成功");
                    device_list.draw();
                } else {
                    toastr.error(e.message);
                }
            });
        });
    });

    //导出表单按钮事件
    $(".box-tools").off('click', '.btn-export').on('click', '.btn-export', function () {
        $("#device_list").table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: "deviceList.xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    });

    //get use data list
    var loadDeviceDist = function (shopId) {
        // 加载使用列表数据
        if(device_dist_List == null){
            device_dist_List = $('#device_dist').DataTable({
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
                "retrieve": true,
                "ajax": function (data, callback, settings) {
                    var param = {
                        shopId: shopId,
                        pageSize: data.length,
                        pageIndex: data.start,
                        page: (data.start / data.length) + 1
                    };
                    $.ajax({
                        url: BASE_PATH + "devicedist/undist",
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
                            pageIndex = param.pageIndex;
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
                    {data: "code"}
                ],
                drawCallback: function () {
                    // 取消全选
                    $(":checkbox[name='keeperDeviceGroup-checkable']").prop('checked', false);
                }
            });


            $('#dist_modal').on("change", ":checkbox", function () {
                // 列表复选框
                if ($(this).is("[name='keeperDeviceGroup-checkable']")) {
                    // 全选
                    $(":checkbox", '#device_dist').prop("checked", $(this).prop("checked"));
                } else {
                    // 一般复选
                    var checkbox = $("tbody :checkbox", '#device_dist');
                    $(":checkbox[name='cb-check-all']", '#device_dist').prop('checked', checkbox.length == checkbox.filter(':checked').length);
                }
            });

            //处理分配确定按钮
            $("#dist_modal").off('click', '#dist_commit').on('click', '#dist_commit', function () {
                var deviceIds = [];
                var deviceList = $(".group-checkable");
                for (var i = 0; i < deviceList.length; i++) {
                    if (deviceList[i]['checked'] == true) {
                        deviceIds.push(deviceList[i].dataset['id']);
                    }
                }
                deviceIds.push(shopId);
                var url = "devicedist/all";

                Request.post(url, deviceIds, function (e) {
                    if (e.success) {
                        toastr.info("保存完毕", opts);
                        $("#dist_modal").modal('hide');
                        device_list.draw();
                    } else {
                        toastr.error("保存失败", opts)
                    }
                    $("#dist_commit").html("保存");
                    $("#dist_commit").removeAttr('disabled');
                } );

            });
        }
        else{
            device_dist_List.draw();
        }
    };

    var CONSTANT = {
        // datatables常量
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
});
