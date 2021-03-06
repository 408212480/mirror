jQuery(document).ready(function () {

    $('form#add_tree_form').validate({
        rules: {
            class_code: {required: true},
            class_name: {required: true}
        },
        messages: {
            class_code: {required: "类别编码不可为空."},
            class_name: {required: "类别名称不可为空"}
        },
        submitHandler: function (form) {
            var btn = $('button[type="submit"]');
            btn.attr('disabled',"true").html("保存中..请稍后");
            var reqType = $(form).data('type') == '0';
            var id = $(form).find('#id').val();
            var req = reqType ? Request.post : Request.put;
            var params = {
                parentCode: $(form).find("#parent_code").val(),
                classCode: $(form).find("#class_code").val(),
                className: $(form).find("#class_name").val()
            };
            req("goodsclass/" + (reqType ? "" : id), JSON.stringify(params), function (e) {

                if (e.success) {
                    toastr.info("保存完毕", opts);
                } else {
                    toastr.error("保存失败", opts)
                }

                btn.html("保存").removeAttr('disabled');
                // 重载树
                window.classTree.reload();
                $("#modal-tree").modal('hide');
            });
        }
    });

    $('form#goods_form').validate({
        rules: {
            goodsTitle: {required: true},
            goodsPrice: {required: true}
        },
        messages: {
            goodsTitle: {required: "请填写商品标题"},
            goodsPrice: {required: "价格不能为空"}
        },
        submitHandler: function (form) {
            var ele = $(form);
            var btn = $('button[type="submit"]');
            btn.attr('disabled',"true").html("保存中..请稍后");
            var reqType = $(form).data('type') == '0';
            var goodsId = $(form).find('#goods_id').val();
            var req = reqType ? Request.post : Request.put;
            var imgs = '', shopId = '',carouselImgUrls='';
            ele.find('input[name="imgIds"]').each(function(i, item){
                imgs += $(this).val() + ',';
            });
            ele.find('input[name="shopId"]:checked').each(function (i, item) {
                shopId += $(this).val() + ',';
            });
            ele.find('input[name="carouselImgUrl"]').each(function(i, item){
                carouselImgUrls += $(this).val() + ',';
            });
            imgs = imgs.substring(0, imgs.length -1);
            shopId = shopId.substring(0, shopId.length -1);
            carouselImgUrls = carouselImgUrls.substring(0, carouselImgUrls.length -1);

            var params = {
                classCode: ele.find('#goods_class_code').val(),
                title: ele.find('#goods_title').val(),
                price: ele.find('#goods_price').val(),
                describe: ele.find('#goods_describe').val(),
                imgIds: imgs,
                shopId: shopId,
                carouselImgUrls:carouselImgUrls
            };

            req('goodsinfo/' + (reqType ? '' : goodsId), params, function (e) {
                if (e.success) {
                    toastr.success('操作成功！', opts);
                    baseTable.ajax.reload().draw();
                    $("#modal-basebox").modal('hide');
                } else {
                    toastr.warning('操作失败', opts);
                }

                btn.removeAttr('disabled').html('保存');

            });
        }
    });

    // 树结构操作绑定
    $('.btn-tree-add').off('click').on('click', function () {
        $("#modal-tree").modal('show');
        var selected = $('#base_tree').treeview('getSelected');
        if (selected == null || selected.length == 0) {
            $("#add_tree_form").data('type', '0');
            $("#id").val('');
            $("#parent_code").val('');
            $("#parent_code_name").val("根节点");
        } else {
            $("#add_tree_form").data('type', '0');
            $("#id").val(selected[0].id);
            $("#parent_code").val(selected[0].classCode);
            $("#parent_code_name").val(selected[0].text);
        }
    });

    $('.btn-tree-edit').off('click').on('click', function () {
        var selected = $('#base_tree').treeview('getSelected');
        if (selected == null || selected.length == 0) {
            toastr.warning('请选择类型后在编辑', opts);
            return;
        }

        console.log("selected[0]:", selected[0]);
        var parentId = selected[0].parentId === undefined ? '' : selected[0].parentId;

        $("#modal-tree").modal('show');
        $("#add_tree_form").data('type', '1');
        $("#add_tree_form #id").val(selected[0].id);
        $("#add_tree_form #parent_code").val(parentId);
        $("#add_tree_form #parent_code_name").val(parentId === '' ? '根节点' : parentId);
        $("#add_tree_form #class_code").val(selected[0].classCode);
        $("#add_tree_form #class_name").val(selected[0].text);

    });

    $('.btn-tree-del').off('click').on('click', function () {
        var selected = $('#base_tree').treeview('getSelected');
        if (selected == null || selected.length == 0) {
            toastr.warning('请选择一个类别后才能进行删除', opts);
        }

        confirm('提示', '确定要删除类别： ' + selected[0].text + ' 吗？', function () {
            // 删除操作
            var id = selected[0].id;
            Request.delete('goodsclass/' + id, {}, function (e) {

                if (e.success) {
                    toastr.success('删除成功!', opts);
                } else {
                    toastr.warning('删除失败', opts);
                }
                window.classTree.reload();
            });

        });
    });

    var inited = false, classTree = {
        baseTree: null,
        init: function () {
            if (inited) return this;

            inited = true;
            this.reload();
            return this;
        },
        reload: function () {
            var that = this;
            Request.get('goodsinfo/goodsClassTree', {}, function (e) {
                if (e.success) {
                    that.baseTree = $('#base_tree').treeview({
                        data: e.data,
                        onNodeSelected: function (event, data) {
                            baseTable.ajax.reload().draw();
                        }
                    });
                    that.baseTree.treeview('selectNode', [0]);
                }
            });
        }
    };
    window.classTree = classTree.init();

    // 页面整体事件

    $('#btn_add_new').off('click').on('click', function () {
        var addType = $('.tab-content .tab-pane.active').attr('id');
        console.log('选择分页卡', addType);

        // 这里做判断
        switch (addType) {
            case 'base_list':
                // 基本信息分页新增
                setEmptyModalData();

                $("#modal-basebox").modal('show');

                break;
            case 'class_list':
                // 规格列表分页新增
                break;
            case 'comment_list':
                // 评价列表分页新增
                break;
        }

    });

    // 基本信息列表
    var baseTable = $("#base_data_table").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
        "bStateSave": true,
        "bFilter": true, //搜索栏
        "bSort": false,
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;

            var selected = $('#base_tree').treeview('getSelected');
            if (selected == null || selected.length == 0) {
                return;
            }

            $.ajax({
                url: BASE_PATH + "goodsinfo/list/" + selected[0].classCode,
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
            {"data": "classCode"},
            {"data": "title"},
            {"data": "price"},
            {"data": "shopName"}
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
                "sClass":"center",
                "aTargets":[5],
                "mData":"id",
                "mRender":function(a,b,c,d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '<div class="btn-group">';
                    buttons += '<div class="btn-group">';
                    buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';
                        buttons += '<li><a href="javascript:;" class="btn-moreinfo" data-id="'+a+'">商品详情</a></li>';
                        buttons += '<li><a href="javascript:;" class="btn-class-list" data-id="' + a+ '">规格列表</a></li>';
                        buttons += '<li><a href="javascript:;" class="btn-comment-list" data-id="'+a+'">评论列表</a></li>';
                    if (accessUpdate) {
                        buttons += '<li><a href="javascript:;" class="btn-edit" data-id="'+a+'">编辑</a></li>';
                    }
                    if (accessDelete) {
                        buttons += '<li><a href="javascript:;" class="btn-del" data-id="'+a+'">删除</a></li>';
                    }
                    buttons += '</ul></div></div>';

                    return buttons;

                }
            }
        ]
    });

    var setModalData = function (obj) {
        $('#input_goods_name').val(obj.title);
        $('#input_goods_price').val(obj.price);
        $('#input_goods_classcode').val(obj.classCode);
        $('#input_goods_shopname').val(obj.shopName);
        $('#input_goods_describe').val(obj.describe);
    };

    var setEmptyModalData = function () {
        $('#input_goods_name').val('');
        $('#input_goods_price').val('');
        $('#input_goods_classcode').val('');
        $('#input_goods_shopname').val('');
        $('#input_goods_describe').val('');

        $("input#kv-explorer").fileinput('destroy');
        $("input#carousel_upload").fileinput('destroy');
        fileUploadInput();
        carouselUploadInput();
        $('#goods_title').val('');
        $('#goods_price').val('');
        $('#goods_class_code').val('');
        $('#goods_describe').val('');

        $('input[name="imgIds"]').remove();
        $('input[name="carouselImgUrl"]').remove();
    };

    // 初始化页面文件上传
    var fileUploadInput = function (options) {
        var defaultOption = {
            dropZoneTitle: "拖拽文件到这里...",
            language : 'zh',
            allowedPreviewTypes : [ 'image' ],
            allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
            'theme': 'explorer',
            'uploadUrl': Request.BASH_PATH + 'file/singleUpload',
            overwriteInitial: false,
            initialPreviewAsData: false
        };

        console.log(Object.prototype.toString.call(options));
        // || Object.prototype.toString.call(options) !== '[object Object]'
        if (options == null ) {
            options = defaultOption;
        }

        $("#kv-explorer").fileinput('refresh', options).on("fileuploaded", function(event, data, previewId) {
            if (data != null && data.response != null && data.response.success === true) {
                console.log(data);
                console.log("previewId:", previewId);
                var resource = data.response.data;
                if (resource.id != null && resource.id !== '') {
                    $('.img-list').append('<input type="hidden" name="imgIds" id="'+previewId+'" data-id="'+ previewId +'" value="'+resource.id+'"/>');
                }
            }
        }).on("filesuccessremove", function (event, id) {
            console.log(id);
            deleteUploadPic($("#" + id).val());
        });

/*        initialPreview: [
            "http://lorempixel.com/1920/1080/nature/1",
            "http://lorempixel.com/1920/1080/nature/2",
            "http://lorempixel.com/1920/1080/nature/3"
        ],
            initialPreviewConfig: [
            {caption: "nature-1.jpg", size: 329892, width: "120px", url: "{$url}", key: 1},
            {caption: "nature-2.jpg", size: 872378, width: "120px", url: "{$url}", key: 2},
            {caption: "nature-3.jpg", size: 632762, width: "120px", url: "{$url}", key: 3}
        ]*/
    };

    // 初始化页面文件上传
    var carouselUploadInput = function (options) {
        var defaultOption = {
                dropZoneTitle: "拖拽文件到这里...",
                language : 'zh',
                allowedPreviewTypes : [ 'image' ],
                allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
                'theme': 'explorer',
                'uploadUrl': Request.BASH_PATH + 'file/singleUpload',
                maxFileCount: 3,
                overwriteInitial: false,
                initialPreviewAsData: false
        };
        if (options == null ) {
            options = defaultOption;
        }

        $("#carousel_upload").fileinput(options).on("fileuploaded", function(event, data, previewId) {
            if (data != null && data.response != null && data.response.success === true) {
                console.log(data);
                console.log("previewId:", previewId);
                var resource = data.response.data;
                if (resource.id != null && resource.id !== '') {
                    $('.carousel_img-list').append('<input type="hidden" name="carouselImgUrl" id="'+previewId+'" data-id="'+ previewId +'" value="'+resource.id+'"/>');
                }
            }
        }).on("filesuccessremove", function (event, id) {
            console.log(id);
            deleteUploadPic($("#" + id).val());
        });
    };
    fileUploadInput();
    carouselUploadInput();


    var deleteUploadPic = function (picId) {
        Request.delete('resources/img/' + picId, {}, function (e) {
            if (e.success) {
                console.log(e);
            }
        });
    };

    // 操作按钮事件绑定
    // 查看详细信息
    $("#base_list").off('click', '.btn-moreinfo').on('click', '.btn-moreinfo', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        toastr.info('加载数据中..请稍后', opts);
        var goods_id = $(this).data('id');
        // modal-readonly
        Request.get('goodsinfo/' + goods_id, {}, function (e) {
            if (e.success) {
                setModalData(e.data);
                $("#modal-readonly").modal('show');
                that.removeAttr('disabled');

                if (e.data.imgId != null && e.data.imgId != '') {
                    Request.get('goodsinfo/pic/' + e.data.imgId, {}, function (r) {
                        if (r.success && r.data != null) {
                            var list = $('#img-list');
                            list.empty();

                            for (var i = 0; i < r.data.length; i++) {
                                list.append('<img src="'+r.data[i]+'" style="width: 100px; height: 100px"/>&nbsp;&nbsp;\n')
                            }
                        }
                    });
                }


            } else if (e.code == 401) {
                window.location.reload();
            }
        });
    });

    $("#base_list").off('click', '.btn-class-list').on('click', '.btn-class-list', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        toastr.info('加载数据中..请稍后', opts);
        var goods_id = $(this).data('id');
        initGoodsSpecTable(goods_id);
        $('#modal-classinfo').modal('show');

    });
    $("#base_list").off('click', '.btn-comment-list').on('click', '.btn-comment-list', function () {
        var that = $(this);
        that.attr('disabled', 'disabled');
        var goods_id = $(this).data('id');
        initGoodsCommentTable(goods_id);
        $('#modal-comment-box').modal('show');
    });



    $('#base_data_table').off('click', '.btn-edit').on('click', '.btn-edit', function () {
        var id = $(this).data('id');
        $('#goods_form').data('type', "1");
        toastr.info('加载中', opts);

        Request.get('goodsinfo/' + id, {}, function (e) {
            if (e.success) {
                fileUploadInput();
                carouselUploadInput();
                var data = e.data;
                $('#goods_id').val(data.id);
                $('#goods_title').val(data.title);
                $('#goods_price').val(data.price);
                $('#goods_describe').val(data.describe);

                if (data.imgId != null && data.imgId != '') {
                    $("input#kv-explorer").fileinput('destroy');
                    Request.get('goodsinfo/pic/' + data.imgId, {}, function (r) {
                        if (r.success) {

                            var preview = [];
                            var previewConfig = [];
                            for (var i = 0; i<r.data.length; i++) {
                                preview.push(
                                    '<img src="' + r.data[i] +'" style="width: auto; height: 170px" class="file-preview-image" alt="" title="" />'
                                );
                                previewConfig.push({
                                        caption: i+".jpg",
                                        width: "60px",
                                        url: 'file/',
                                        key: i+1
                                    }
                                );
                            }
                            fileUploadInput({
                                language : 'zh',
                                allowedPreviewTypes : [ 'image' ],
                                allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
                                'theme': 'explorer',
                                'uploadUrl': Request.BASH_PATH + 'file/singleUpload',
                                overwriteInitial: false,
                                initialPreviewAsData: false,
                                initialPreview: preview,
                                initialPreviewConfig:previewConfig
                            });
                        }
                    });
                }

                if (data.carouselImgUrl != null && data.carouselImgUrl != '') {
                    $("input#carousel_upload").fileinput('destroy');
                    Request.get('goodsinfo/pic/' + data.carouselImgUrl, {}, function (r) {
                        if (r.success) {
                            var preview = [];
                            var previewConfig = [];
                            for (var i = 0; i<r.data.length; i++) {
                                preview.push(
                                    '<img src="' + r.data[i] +'" style="width: auto; height: 170px" class="file-preview-image" alt="" title="" />'
                                );
                                previewConfig.push({
                                        caption: i+".jpg",
                                        width: "60px",
                                        url: 'file/',
                                        key: i+1
                                    }
                                );
                            }
                            carouselUploadInput({
                                language : 'zh',
                                allowedPreviewTypes : [ 'image' ],
                                allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
                                'theme': 'explorer',
                                'uploadUrl': Request.BASH_PATH + 'file/singleUpload',
                                overwriteInitial: false,
                                initialPreviewAsData: false,
                                initialPreview: preview,
                                initialPreviewConfig:previewConfig
                            });
                        }
                    });
                }

                $("#modal-basebox").modal('show');

            }
        });


    });

    $('#base_data_table').off('click', '.btn-del').on('click', '.btn-del', function() {
        var id = $(this).data('id');

        Request.delete('goodsinfo/' + id, {}, function (e) {
            if (e.success) {
                toastr.success('删除成功', opts);
                baseTable.ajax.reload().draw();
            } else {
                toastr.warning('删除失败，请重试', opts);
            }
        });
    });

    // goodsClass selectTree

    var loadSelectTree = function () {
        var selectTree = $('#goods_class_code');
        selectTree.empty();

        Request.get('goodsinfo/goodsClassTree', {}, function (e) {
            if (e.success) {

                for (var i = 0; i < e.data.length; i++) {
                    selectTree.append('<option value="'+e.data[i].classCode +'">'+e.data[i].text+'</option>');
                    if (e.data[i].nodes !== null) {
                        childSelectTree(selectTree, e.data[i].nodes, '┣ ');
                    }
                }
            }
        });
    };

    var childSelectTree = function (ele, data, appendStr) {
        var len = data.length;
        for (var i = 0; i < len; i++) {
            if (len-1 == i) {
                if (appendStr === '┣ ') {
                    appendStr = '┗ ';
                } else {
                    appendStr = '┣┻ '
                }

            }
            ele.append('<option value="'+data[i].classCode+'">'+ appendStr + data[i].text+'</option>');
            if (data[i].nodes !== null) {
                childSelectTree(ele, data[i].nodes, '┣╋ ');
            }

        }

    };

    loadSelectTree();

    var loadShopList = function () {

        Request.get('shop', {}, function (e) {
            if (e != null) {
                var shopList = e.data;
                console.log(shopList);
                var html = '';
                for (var i = 0; i < shopList.length; i++) {
                    html += '<label><input type="checkbox" name="shopId" value="'+ shopList[i].id +'"/>'+shopList[i].shopName+'</label>';
                }
                $("#shop_list").empty().append(html);
            }
        });
    };

    loadShopList();
    var goodsSpecTable = undefined;
    // 规格列表信息加载
    var initGoodsSpecTable = function (goodsId) {
        $('#btn-class-new').data('gid', goodsId);
        goodsSpecTable = $("#class_list_table").DataTable({
            "language": lang,
            "paging": false,
            "lengthChange": true,
            "searching": false,
            "ordering": false,
            "info": true,
            "autoWidth": false,
            "bStateSave": true,
            "bFilter": true, //搜索栏
            "destroy": true,
            "bSort": false,
            // "serverSide": true,
            "sPaginationType": "full_numbers",
            "ajax": function (data, callback, settings) {
                var param = {};
                param.paging = false;

                var selected = $('#base_tree').treeview('getSelected');
                if (selected == null || selected.length == 0) {
                    return;
                }

                $.ajax({
                    url: BASE_PATH + "goodsinfospec/specList/" + goodsId,
                    type: "GET",
                    cache: false,
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        var resultData = {};
                        resultData.draw = data.draw;
                        resultData.recordsTotal = result.data.data.length;
                        resultData.recordsFiltered = result.data.data.length;
                        resultData.data = result.data.data;
                        callback(resultData);
                    },
                    error: function (jqXhr) {
                        toastr.warning("请求列表数据失败, 请重试");
                    }
                });
            },
            columns: [
                {"data": "id"},
                {"data": "color"},
                {"data": "size"},
                {"data": "goodsId"}
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
                    "sClass":"center",
                    "aTargets":[3],
                    "mData":"id",
                    "mRender":function(a,b,c,d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                        // 修改 删除 权限判断
                        var buttons = '';
                        if (accessUpdate || accessDelete) {
                            buttons = '<div class="btn-group">';
                            buttons += '<div class="btn-group">';
                            buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                            buttons += '<span class="caret"></span></button>';
                            buttons += '<ul class="dropdown-menu">';
                            if (accessUpdate) {
                                buttons += '<li><a href="javascript:;" class="btn-class-edit" data-id="'+c.id+'" data-gid="'+goodsId+'">编辑</a></li>';
                            }
                            if (accessDelete) {
                                buttons += '<li><a href="javascript:;" class="btn-class-del" data-id="'+c.id+'" data-gid="'+goodsId+'">删除</a></li>';
                            }
                            buttons += '</ul></div></div>';
                        }
                        return buttons;

                    }
                }
            ]
        });
    };

    // 绑定规格操作事件
    // 新增规格
    $('#modal-classinfo').off('click', '#btn-class-new').on('click', '#btn-class-new', function () {
        $('#input_class_color').val('');
        $('#input_class_size').val('');
        $('#input_class_quality').val('');
        $('#class-form').data('type', '0').data('id', '').data('gid', $(this).data('gid')); // 追加表单状态为 1 ，编辑 。 0 ， 新增
        $('#modal-class-modify').modal('show');
    });
    // 修改规格
    $('#class_list_table').off('click', '.btn-class-edit').on('click', '.btn-class-edit', function () {
        var that = $(this);
        var goodsId = that.data('gid');
        var specId = that.data('id');

        if (goodsId != '' && specId != '') {
            Request.get('goodsinfospec/' + specId, {}, function (e) {
                if (e.success) {

                    $('#input_class_color').val(e.data.color);
                    $('#input_class_size').val(e.data.size);
                    $('#input_class_quality').val(e.data.quality);
                    $('#class-form').data('type', '1').data('id', specId); // 追加表单状态为 1 ，编辑 。 0 ， 新增
                    $('#modal-class-modify').modal('show');
                }
            });
        }
    });
    // 删除规格
    $('#class_list_table').off('click', '.btn-class-del').on('click', '.btn-class-del', function () {
        var that = $(this);
        var goodsId = that.data('gid');
        var specId = that.data('id');

        if (goodsId != '' && specId != '') {
            Request.delete('goodsinfospec/' + specId, {}, function (e) {
                if (e.success) {
                    toastr.success('删除成功', opts);
                    goodsSpecTable.ajax.reload().draw();
                } else {
                    toastr.warning('删除失败', opts);
                }
            });
        }
    });

    $('form#class-form').validate({
        rules: {
            input_class_size: {required: true},
            input_class_color: {required: true},
            input_class_quality: {required: true}
        },
        messages: {
            input_class_size: {required: "请输入规格尺码"},
            input_class_color: {required: "请输入规格颜色"},
            input_class_quality: {required: "请输入该规格商品数量"}
        },
        submitHandler: function (form) {

            var btn = $('button[type="submit"]');
            btn.attr('disabled',"true").html("保存中..请稍后");
            var reqType = $(form).data('type') == '0';
            var id = $(form).data('id');
            var gid =$(form).data('gid');
            var req = reqType ? Request.post : Request.put;

            var data = {
                color: $(form).find('#input_class_color').val(),
                size: $(form).find('#input_class_size').val(),
                quality: $(form).find('#input_class_quality').val()
            };
            if (gid != '' && gid != undefined && gid != null) {
                data.goodsId = gid;
            }

            req('goodsinfospec/' + (reqType ? '' : id), data, function (e) {
                btn.html("保存").removeAttr('disabled');

                if (e.success) {
                    toastr.success('修改成功' , opts);
                    goodsSpecTable.ajax.reload().draw();
                    $('#modal-class-modify').modal('hide');
                } else {
                    toastr.success('修改失败' , opts);
                }
            });
            return false;
        }
    });

    // 评价列表加载

    var goodsCommentTable = undefined;
    var initGoodsCommentTable = function (goodsId) {
        goodsCommentTable = $("#comment_list_table").DataTable({
            "language": lang,
            "paging": false,
            "lengthChange": true,
            "searching": false,
            "ordering": false,
            "info": true,
            "autoWidth": false,
            "bStateSave": true,
            "bFilter": true, //搜索栏
            "destroy": true,
            "bSort": false,
            // "serverSide": true,
            "sPaginationType": "full_numbers",
            "ajax": function (data, callback, settings) {
                var param = {};
                param.paging = false;

                $.ajax({
                    url: BASE_PATH + "goodscomment/goods/" + goodsId,
                    type: "GET",
                    cache: false,
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        var resultData = {};
                        resultData.draw = data.draw;
                        resultData.recordsTotal = result.data.total;
                        resultData.recordsFiltered = result.data.total;
                        resultData.data = result.data.data;
                        callback(resultData);
                    },
                    error: function (jqXhr) {
                        toastr.warning("请求列表数据失败, 请重试");
                    }
                });
            },
            columns: [
                {"data": "username"},
                {"data": "username"},
                {"data": "comment_level"},
                {"data": "comment"},
                {"data": "gmt_create"}
            ],
            "aoColumnDefs": [
                {
                    "sClass": "center",
                    "aTargets": [0],
                    "mData": "id",
                    "mRender": function (a, b, c, d) {
                        return d.row;
                    }
                }
            ]
        });
    }

});