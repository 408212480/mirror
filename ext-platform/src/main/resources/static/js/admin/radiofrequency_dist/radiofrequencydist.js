$(document).ready(function () {
    var inited = false;
    var area_list = [];
    var device_list = null;
    var initAreaTree = function () {
        Request.get("area?paging=false&sorts[0].name=sortIndex", function (e) {
            area_list = e;
            console.log(e);
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

    // 点击分配获取射频列表数据
    var roleList = $('#device_fid_detail_list').DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "bFilter": false,
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "radiofrequency-dist/List/",
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
                data: null,
                title: "<input type='checkbox' name='checklist' id='checkall' />"
            },

            {"data": "id"},
            {"data": "devNum"},
            {"data": "devCode"}
        ],
        "aoColumnDefs": [
            {
                "sClass":"center",
                "aTargets":[4],
                targets: 0,
                render: function(data, type, row, meta) {
                    return '<input type="checkbox" name="checklist" value="' + row.id + '" />'
                }
            }
        ]
    });
// batch-save select data-list to database table t_fid_goodsspec
    $("#device_goods_form").off('click', '.batch-save').on('click', '.batch-save', function () {

        var btn = $('button[type="submit"]');
                btn.attr('disabled',"true");
    var selected = $('#area_tree').treeview('getSelected');
    var shopId = selected[0].id;
    var id_array=new Array();
    $("input[name='checklist']:checked").each(function(){
        id_array.push($(this).val());//将选中的射频ID一次添加到数组里
    });
    id_array.push(shopId);
        // 发送分配请求
        Request.post("radiofrequency-dist/BulkInsert/",id_array, function (e) {
            if (e.success) {
                // 弹出 新增成功 提示，关闭 modal 框
                toastr.info("分配成功", opts);
                $("#modal-add").modal('hide');
                // 移除保存按钮的禁用状态
                btn.removeAttr('disabled');
                // 列表重载
                device_list.ajax.reload();
            } else {
                toastr.error("分配失败", opts);
            }
        });

    });

    initAreaTree();

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
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "radiofrequency-dist/shopdevicefid/" + $('#area_tree').treeview('getSelected', null)[0]['id'],
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
            {"data": "fidId"},
            {"data": "gmtCreate"}
            // {"data": "deviceId"}
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

    $('#device_list').on('draw.dt', function () {
        var nodes = device_list.column(0).nodes();
        for (var i = 0; i < nodes.length; i++) {
            nodes[i].innerHTML = i + 1;
        }
    });
    device_list.columns().draw();

    //解绑按钮事件
    $("#device_list").off('click', '.btn-relieve').on('click', '.btn-relieve', function () {
        var that = $(this);
        var shop_device_id = that.data('id');
        confirm('警告', '真的要解绑设备吗?', function () {
            Request.delete("radiofrequency-dist/" + shop_device_id, {}, function (e) {
                if (e.success) {
                    toastr.success("解绑成功");
                    device_list.draw();
                } else {
                    toastr.error(e.message);
                }
            });
        });

    });

    $("#device_list").off('click', '.btn-info').on('click', '.btn-info', function () {
    });

    //点击分配弹出操作
    $(document).off('click', '.btn-dist').on('click', '.btn-dist', function () {
        var shop = $('#area_tree').treeview('getSelected', null)[0];
        if (shop != undefined && shop['is_shop']) {
            $("#modal-add").modal('show');
        }
        else {
            toastr.warning("请先选择需要添加射频的店铺!");
        }
     });

    //处理分配初始化按钮事件
    $(".box-tools").off('click', '.btn-init').on('click', '.btn-init', function () {
        var treeNode = $('#area_tree').treeview('getSelected', null)[0];
         if (treeNode != undefined && treeNode['is_shop']) {

            confirm('警告', '初始化后与该店铺绑定的所有射频将与店铺解绑！', function () {
                Request.delete("radiofrequency-dist/reset/" +treeNode.id , {}, function (e) {
                    if (e.success) {
                        toastr.success("初始化成功！");
                        device_list.ajax.reload();
                    } else {
                        toastr.error(e.message);
                    }
                });
            });
        }
        else {
            toastr.warning("请先选择需要初始化射频的店铺节点!");
        }
    });

});

