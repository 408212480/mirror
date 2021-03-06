$(document).ready(function () {
    var inited = false;
    var area_list = [];

    var initAreaTree = function () {
        Request.get("area?paging=false&sorts[0].name=sortIndex", function (e) {
            area_list = e;
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

    var user_list = $('#user_list').DataTable({
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
            {data: "userId"},
            {data: "avatarId"},
            {data: "name"},
            {data: "sex"},
            {data: "age"},
            {data: "height"},
            {data: "weight"},
            {data: "telephone"},
            {data: "status"}
        ],

        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [2],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    var image = '';
                    if (c.avatarId != null) {
                        image = '<img src="' + c.avatarId + '" height="40" width="40"></img>';
                        return image;
                    }
                    else {
                        return null;
                    }
                }
            },
            {
                "sClass": "center",
                "aTargets": [9],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '';
                    if (a == 1) {
                        buttons = '<button type="button" data-id="' + c.id + '" data-name="' + c.name + '" class="btn btn-danger btn-sm btn-close">禁用</button>';
                    }
                    else {
                        buttons = '<button type="button" data-id="' + c.id + '" data-name="' + c.name + '" class="btn btn-success btn-sm btn-open">启用</button>';
                    }
                    return buttons;
                }
            }
        ]

    });

    user_list.columns().draw();

    //禁用操作
    $("#user_list").off('click', '.btn-close').on('click', '.btn-close', function () {
        var that = $(this);
        var userInfo_id = that.data('id');
        var user_name = that.data('name');
        confirm('警告', '真的要禁用：  ' + user_name + ' 吗?', function () {
            // 请求 module_id 删除
            Request.delete("userInfo/close/" + userInfo_id, {}, function (e) {
                if (e.success) {
                    toastr.success("禁用成功");
                    user_list.draw();
                } else {
                    toastr.error(e.message);
                }
            });
        });
    });

    //启用操作
    $("#user_list").off('click', '.btn-open').on('click', '.btn-open', function () {
        var that = $(this);
        var userInfo_id = that.data('id');
        var user_name = that.data('name');
        confirm('真的要启用： ' + user_name + ' 吗?', function () {
            // 请求 module_id 删除
            Request.delete("userInfo/open/" + userInfo_id, {}, function (e) {
                if (e.success) {
                    toastr.success("启用成功");
                    user_list.draw();
                } else {
                    toastr.error(e.message);
                }
            });
        });
    });

});