$(document).ready(function () {
    var inited = false;
    var area_list = [];
    var shop_list = null;
    var is_add = true;  //记录是否添加图片，如果为false则为编辑图片
    var shop_id = null; //记录店铺id

    if(typeof String.prototype.startsWith != 'function'){
        String.prototype.startsWith = function(prefix){
            return this.slice(0, prefix.length) === prefix;
        }
    };

    if(typeof String.prototype.endsWith != 'function'){
        String.prototype.endsWith = function(suffix){
            return this.indexOf(suffix, this.length - suffix.length) !== -1;
        }
    };

    //文件提交框选项设置
    var fileinputoption = {
        required: true,
        uploadUrl: Request.BASH_PATH + 'file/shopImgUpload',
        dropZoneTitle: "拖拽文件到这里...",
        language: 'zh', //设置语言
        showUpload: false, //是否显示上传按钮
        showRemove: false,
        showCaption: true,//是否显示标题
        showClose: false,
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions: ['jpg'],
        maxFileCount: 1,
        maxFileSize: 2000,
        autoReplace: true,
        validateInitialCount: true,
        initialPreviewAsData: true,
        deleteUrl: '/shop/img/delete',
        uploadAsync: false //同步上传
    };

    $("#business_url").fileinput(fileinputoption);

    $("#logo").fileinput(fileinputoption);

    fileinputoption["maxFileCount"] = 3;
    $("#img1").fileinput(fileinputoption);

    $('input#img1').on('filebatchselected', function(event){
        //如果选择的图片大于3张，则清空图片预览区
        if($('input#img1').fileinput('getFilesCount') > 3){
            $('input#img1').fileinput('clear');
            toastr.info("店铺图片不能多于3张", opts);
        }
    });

    var initAreaTree = function () {
        Request.get("area?paging=false&sorts[0].name=sortIndex", function (e) {
            area_list = e;
            var tree = areaTree.init();

            var rootNodes = tree.getRootNodes(e);
            $('#area_tree').treeview({
                data: rootNodes
            });

            $('#area_tree').on('nodeSelected', function (event, data) {
                if (shop_list != null) {
                    shop_list.columns().draw();
                }
            });
            $('#area_tree').treeview('selectNode', [0]);


            shop_list = $('#shop_list').DataTable({
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
                        url: BASE_PATH + "shop/InArea/" + selectedNode[0]['id'],
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
                            if(resultData.data == null){
                                resultData.data =[];
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
                    {data: "shopName"},
                    {data: "principal"},
                    {data: "principalTel"},
                    {data: "legalName"},
                    {data: "businessUrl"},
                    {data: "address"},
                    {data: "img1"},
                    {data: null}
                ],
                "order": [[1, 'asc']],
                "aoColumnDefs": [
                    {
                        "sClass": "center",
                        "aTargets": [5],
                        "mData": "id",
                        "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                            // 修改 删除 权限判断
                            var image = '';
                            if (c.img1 != null) {
                                image = '<img src="' + c.businessUrl + '" height="35" width="50"></img>';
                                return image;
                            }
                            else {
                                return null;
                            }
                        }
                    },
                    {
                        "sClass": "center",
                        "aTargets": [7],
                        "mData": "id",
                        "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                            // 修改 删除 权限判断
                            var image = '';
                            if (c.img1 != null) {
                                image = '<img src="' + c.img1 + '" height="35" width="50"></img>';
                                return image;
                            }
                            else {
                                return null;
                            }
                        }
                    },
                    {
                        "sClass": "center",
                        "aTargets": [8],
                        "mData": "id",
                        "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                            // 还未进行修改 删除 权限判断
                            var buttons = '';
                            buttons += '<button type="button" data-id="' + c.id + '" class="btn btn-default btn-sm btn-info">详情</button>\n';
                            buttons += '<button type="button" data-id="' + c.id + '" data-shopname="' + c.shopName + '" class="btn btn-default btn-sm btn-edit">编辑</button>';
                            buttons += '<button type="button" data-id="' + c.id + '" data-shopname="' + c.shopName + '" class="btn btn-default btn-sm btn-delete">删除</button>';

                            return buttons;
                        }
                    }
                ]

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


    //添加店铺按钮事件
    $(".box-tools").off('click', '.btn-add').on('click', '.btn-add', function () {
        var selected = $('#area_tree').treeview('getSelected');
        if (selected.length == 0) {
            toastr.info("请选择新增店铺所处区域", opts);
        }
        else {
            $('#modal-add input').val('');

            //初始化图片上传控件
            $("input#business_url").fileinput('clear');
            $("input#logo").fileinput('clear');
            $("input#img1").fileinput('clear');
            $("#modal-add input").removeAttr("disabled");
            $("input#logo").fileinput("enable");
            $("input#business_url").fileinput("enable");
            $("input#img1").fileinput("enable");
            window.editor.readonly(false);

            //初始化图文信息框
            window.editor.html('');

            $('#footer').show();
            $(".modal-title").html("新增店铺");
            $("#modal-add").modal('show');
            is_add = true;
        }
    });

    $("form#shop_form").validate({
        rules: {
            shop_name: {required: true},
            principal: {required: true},
            principal_tel: {required: true, number: true},
            legal_name: {required: true},
            address: {required: true}
        },
        messages: {
            shop_name: {required: "店铺名不能为空."},
            principal: {required: "负责人不能为空."},
            principal_tel: {required: "负责人电话不能为空.",number: "请输入正确的电话号码"},
            legal_name: {required: "法人不能为空."},
            address: {required: "详细地址不能为空."}
        },
        submitHandler: function (form) {

            var logoUrl = null; //记录店铺logo 资源id
            var businessUrl = null; //记录店铺营业执照 资源id
            var imgUrl = [];    //记录店铺图片 资源id

            var btn = $('#submitBtn');

            btn.attr('disabled', "true");
            btn.html("保存中..请稍后");
            var selected = $('#area_tree').treeview('getSelected');
            //同步富文本编辑器，才能取得富文本编辑器内地内容
            editor.sync();

            //获取logo资源id
            var logoSrc = $('#logo-div .kv-preview-thumb .file-preview-image');
            if(logoSrc && logoSrc.length > 0){
                for(var i = 0; i < logoSrc.length; i++){
                    if(logoSrc[i].getAttribute('src').startsWith('/file/image/')){
                        logoUrl = logoSrc[i].getAttribute('src').slice(12, 18);
                        break;
                    }
                }
            }

            var businessSrc = $('#business-div .kv-preview-thumb .file-preview-image');
            if(businessSrc && businessSrc.length > 0){
                for(var i = 0; i < businessSrc.length; i++){
                    if(businessSrc[i].getAttribute('src').startsWith('/file/image/')){
                        businessUrl = businessSrc[i].getAttribute('src').slice(12, 18);
                        break;
                    }
                }
            }

            var imgSrc = $('#shop-img-div .kv-preview-thumb .file-preview-image');
            if(imgSrc && imgSrc.length > 0){
                for(var i = 0; i < imgSrc.length; i++){
                    if(imgSrc[i].getAttribute('src').startsWith('/file/image/')){
                        imgUrl.push(imgSrc[i].getAttribute('src').slice(12, 18));
                    }
                }
            }

            var params = {
                shopName: $("#shop_name").val(),
                logo: logoUrl,
                principal: $("#principal").val(),
                principalTel: $("#principal_tel").val(),
                legalName: $("#legal_name").val(),
                businessUrl: businessUrl,
                address: $("#address").val(),
                img1: imgUrl[0],
                img2: null,
                img3: null,
                areaId: selected[0].id,
                content: window.editor.html()
            };

            if (!is_add) {      //编辑店铺
                delete params.areaId;
            }

            if (imgUrl.length >= 2) {
                params.img2 = imgUrl[1];
            }
            if (imgUrl.length >= 3) {
                params.img3 = imgUrl[2];
            }

            var req = is_add ? Request.post : Request.put;

            req("shop/" + (is_add ? "" : shop_id), JSON.stringify(params), function (e) {
                if (e.success) {
                    toastr.info("保存完毕", opts);
                    $("#modal-add").modal('hide');
                    shop_list.draw();
                    window.editor.html('');
                } else {
                    toastr.error("保存失败", opts)
                }
            });

            btn.html("保存");
            btn.removeAttr('disabled');
        }
    });

    //删除店铺操作
    $("#shop_list").off('click', '.btn-delete').on('click', '.btn-delete', function () {
        var that = $(this);
        shop_id = that.data('id');
        var shop_name = that.data('shopname');
        if (shop_id == null) {
            toastr.error("请选择需要删除的店铺");
        }
        else {
            confirm('警告', '真的要删除：' + shop_name + ' 吗?', function () {
                // 请求 module_id 删除
                Request.delete("shop/" + shop_id, {}, function (e) {
                    if (e.success) {
                        toastr.success("删除成功");
                        shop_list.draw();
                    } else {
                        toastr.error(e.message);
                    }
                });
            });

        }
    });

    //编辑或查看店铺详情，初始化页面信息
    var initEditorPage = function (that, title) {
        shop_id = that.data('id');
        var url = 'shop/shopInfo/' + shop_id;
        $.get(url, function (data) {            //根据店铺id，获得店铺详细信息
            if (data.success && data.data != null) {
                //初始化各输入框
                $("input#shop_name").val(data.data["shopName"]);
                $("input#principal").val(data.data["principal"]);
                $("input#principal_tel").val(data.data["principalTel"]);
                $("input#legal_name").val(data.data["legalName"]);
                $("input#address").val(data.data["address"]);

                //初始化上传图片预览区域
                $("input#logo").fileinput('clear');
                if (data.data['logo'] != null && data.data['logo'] != '') {
                    $("input#logo").fileinput('refresh', {initialPreview: ["/file/image/" + data.data['logo']],
                            initialPreviewConfig:[{
                            width: '160px',
                            url: '/shop/img/delete',
                            key: 1}]
                    });
                }

                $("input#business_url").fileinput('clear');
                if (data.data['businessUrl'] != null && data.data['businessUrl'] != '') {
                    $("input#business_url").fileinput('refresh', {initialPreview: ["/file/image/" + data.data['businessUrl']],
                                                                        initialPreviewConfig:[{
                                                                        width: '160px',
                                                                        url: '/shop/img/delete',
                                                                        key: 11
                                                                        }]
                                                                    });
                }

                var initialPreview = [];
                var initialPreviewConfig = [];
                $('#img1').fileinput('clear');
                if (data.data['img1'] != null && data.data['img1'] != '') {
                    initialPreview.push("/file/image/" + data.data['img1']);
                    initialPreviewConfig.push({
                            width: '160px',
                            url: '/shop/img/delete',
                            key: 100
                    });
                }
                if (data.data['img2'] != null && data.data['img2'] != '') {
                    initialPreview.push("/file/image/" + data.data['img2']);
                    initialPreviewConfig.push({
                        width: '160px',
                        url: '/shop/img/delete',
                        key: 101
                    });
                }
                if (data.data['img3'] != null && data.data['img3'] != '') {
                    initialPreview.push("/file/image/" + data.data['img3']);
                    initialPreviewConfig.push({
                        width: '160px',
                        url: '/shop/img/delete',
                        key: 102
                    });
                }
                if (initialPreview.length != 0) {     //判断店铺图片是否为空
                    $('input#img1').fileinput('refresh', {initialPreview: initialPreview, initialPreviewConfig:initialPreviewConfig});
                }

                window.editor.html(data.data['content']);
                is_add = false;

                if (title == '店铺详情') {
                    $("#modal-add input").attr("disabled", "disabled");
                    $("input#logo").fileinput("disable");
                    $("input#business_url").fileinput("disable");
                    $("input#img1").fileinput("disable");
                    window.editor.readonly(true);
                    $('#footer').hide();
                } else {
                    $("#modal-add input").removeAttr("disabled");
                    $("input#logo").fileinput("enable");
                    $("input#business_url").fileinput("enable");
                    $("input#img1").fileinput("enable");
                    window.editor.readonly(false);
                    $('#footer').show();
                }

                $(".modal-title").html(title);
                $("#modal-add").modal('show');

            }
            else {       //根据店铺id请求店铺数据失败
                toastr.warning("数据加载失败，请重试");
            }
        });
    };

    //编辑店铺
    $("#shop_list").off('click', '.btn-edit').on('click', '.btn-edit', function () {
        initEditorPage($(this), '编辑店铺');
    });

    //店铺详情
    $("#shop_list").off('click', '.btn-info').on('click', '.btn-info', function () {
        initEditorPage($(this), '店铺详情');
    });
});