$(document).ready(function () {

    var inited = false;
    var area_list = [];
    var is_add = true;

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
            // $('#area_tree').treeview('selectNode', [0]);
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


    $("form#area_form").validate({
        rules: {
            area_name: {required: true}
        },
        messages: {
            area_name: {required: "区域名不能为空."},
        },
        submitHandler: function (form) {
            var btn = $('button[type="submit"]');
            btn.attr('disabled', "true");
            btn.html("保存中..请稍后");
            var selected = $('#area_tree').treeview('getSelected');
            //在选中节点的子节点添加节点

            if (is_add) {     //添加
                var params = {
                    id: null,
                    areaName: $(form).find("#area_name").val(),
                    parentId: selected.length == 0 ? '-1' : selected[0].id,
                    level: selected[0].parentId
                };
                if (selected.length == 0) {
                    params.level = 0;
                }
                else {
                    var parent = $('#area_tree').treeview('getParent', selected[0]);
                    var level = 1;
                    while (parent.nodeName == "DIV") {
                        level += 1;
                        parent = $('#area_tree').treeview('getParent', parent);
                    }
                    params.level = level;
                }
            }
            else {           //修改
                var parent = $('#area_tree').treeview('getParent', selected[0]);
                var params = {
                    id: selected[0].id,
                    areaName: $(form).find("#area_name").val(),
                    parentId: parent.nodeName == "DIV" ? '-1' : parent.id,
                    level: selected[0].parentId
                };
                var level = 0;
                while (parent.nodeName == "DIV") {
                    level += 1;
                    parent = $('#area_tree').treeview('getParent', parent);
                }
                params.level = level;
            }
            var req = is_add ? Request.post : Request.put;

            req("area/" + (is_add ? "" : params.id), JSON.stringify(params), function (e) {
                if (e.success) {
                    toastr.info("保存完毕", opts);
                    $("#modal-add").modal('hide');
                    initAreaTree();
                } else {
                    toastr.error("保存失败", opts)
                }

                btn.html("保存");
                btn.removeAttr('disabled');
            });
        }
    });

    //添加区域按钮事件
    $(".box-body").off('click', '.btn-add').on('click', '.btn-add', function () {
        $(".modal-title").html("新增区域");
        $("#modal-add").modal('show');
        $("input#area_name").val("");
        is_add = true;
    });

    //修改区域按钮事件
    $(".box-body").off('click', '.btn-modify').on('click', '.btn-modify', function () {
        var selected = $('#area_tree').treeview('getSelected');
        if (selected.length == 0) {
            toastr.info("请选择需要修改的节点", opts);
        }
        else {
            var areaName = selected[0].text;
            $("input#area_name").val(areaName);
            is_add = false;
            $(".modal-title").html("修改区域");
            $("#modal-add").modal('show');
        }
    });

    //删除区域按钮事件
    $(".box-body").off('click', '.btn-delete').on('click', '.btn-delete', function () {
        var selected = $('#area_tree').treeview('getSelected');
        if (selected.length == 0) {
            toastr.info("请选择需要删除的节点", opts);
        }
        else {
            var area_id = selected[0].id;
            var area_name = selected[0].text;

            confirm('警告', '真的要删除' + area_name + '吗，如果为主节点，将会导致子节点都被删除?', function () {
                var id = area_id;
                $('.btn-delete').attr('disabled', 'disabled');
                $(document).mask('正在删除中...');
                Request.delete("area/" + id, {}, function (e) {
                    if (e.success) {
                        toastr.success("删除成功");
                        initAreaTree();
                    } else {
                        toastr.error(e.message);
                    }
                    $('.btn-delete').removeAttr('disabled');
                    $(document).unmask();
                });
            });
        }

    });

    $("#search").on("keydown",function(event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc
            //要做的事情
        }
        if (e && e.keyCode == 113) { // 按 F2
            //要做的事情
        }
        if (e && e.keyCode == 13) { // enter 键
            var areaName = $("#search").val();
            $("#area_tree").treeview('search', [areaName,{
                ignoreCase:true,
                exactMatch:false,
                revealResults:true
            }]);
        }
    });

    $("#search_icon").on("click",function(){
        var areaName = $("#search_content").val();
        $("#area_tree").treeview('search', [areaName,{
            ignoreCase:true,
            exactMatch:false,
            revealResults:true
        }]);
    });

    (function(){
        $.extend($.fn,{
            mask: function(msg,maskDivClass){
                this.unmask();
                // 参数
                var op = {
                    opacity: 0.8,
                    z: 10000,
                    bgcolor: '#ccc'
                };
                var original=$(document.body);
                var position={top:0,left:0};
                if(this[0] && this[0]!==window.document){
                    original=this;
                    position=original.position();
                }
                // 创建一个 Mask 层，追加到对象中
                var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
                maskDiv.appendTo(original);
                var maskWidth=$(window).width();
                var maskHeight=$(window).height();
                maskDiv.css({
                    position: 'absolute',
                    top: position.top,
                    left: position.left,
                    'z-index': op.z,
                    width: maskWidth,
                    height:maskHeight,
                    'background-color': op.bgcolor,
                    opacity: 0
                });
                if(maskDivClass){
                    maskDiv.addClass(maskDivClass);
                }
                if(msg){
                    var msgDiv=$('<div style="position:absolute;border:#6593cf 1px solid; padding:2px;background:#ccca"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px">'+msg+'</div></div>');
                    msgDiv.appendTo(maskDiv);
                    var widthspace=(maskDiv.width()-msgDiv.width());
                    var heightspace=(maskDiv.height()-msgDiv.height());
                    msgDiv.css({
                        cursor:'wait',
                        top:(heightspace/2-2),
                        left:(widthspace/2-2)
                    });
                }
                maskDiv.fadeIn('fast', function(){
                    // 淡入淡出效果
                    $(this).fadeTo('slow', op.opacity);
                })
                return maskDiv;
            },
            unmask: function(){
                var original=$(document.body);
                if(this[0] && this[0]!==window.document){
                    original=$(this[0]);
                }
                original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                    $(this).remove();
                });
            }
        });
    })();

});

