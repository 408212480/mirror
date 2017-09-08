$(document).ready(function () {
    var videoList = $("#video_list").DataTable({
        "language": lang,
        "paging": true,
        "lengthChange": true,
        "searching": false,
        "ordering": false,
        "info": true,
        "autoWidth": true,
        "bStateSave": true,
        "bFilter": false, //搜索栏
        // "serverSide": true,
        "sPaginationType": "full_numbers",
        "ajax": function (data, callback, settings) {
            var param = {};
            param.pageSize = data.length;
            param.pageIndex = data.start;
            param.page = (data.start / data.length) + 1;
            $.ajax({
                url: BASE_PATH + "video",
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
            {"data": "userName"},
            {"data": "fidId"},
            {"data": "code"},
            {"data": "uploadTime"},

            {"data": "name","render":function (data,index,obj) {
                if(obj.name.indexOf("jpg")!=-1||obj.name.indexOf("png")!=-1){
                    return "<img src='file/image/"+obj.md5+"'>";
                }else
                    return "<video style='width: 60px' src='file/video/"+obj.md5+"' controls='controls'>";
            }},
            {"data": "status"},
            {"data": "likeNum"},
        ],
        "aoColumnDefs": [
            {
                "sClass": "center",
                "aTargets": [8],
                "mData": "id",
                "mRender": function (a, b, c, d) {//a表示statCleanRevampId对应的值，c表示当前记录行对象
                    // 修改 删除 权限判断
                    var buttons = '<div class="btn-group">';
                    buttons += '<div class="btn-group">';
                    buttons += '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">操作';
                    buttons += '<span class="caret"></span></button>';
                    buttons += '<ul class="dropdown-menu">';
                    buttons += '<li><a href="javascript:;" class="btn-detail" data-id="' + a + '">详情</a></li>';
                    if (accessUpdate) {
                        if (c.status==0)
                        {
                            buttons += '<button type="button" data-id="'+a+'" class="btn btn-success btn-sm btn-open">启用</button>';

                        }
                        else {
                            buttons += '<button type="button" data-id="'+a+'" class="btn btn-danger btn-sm btn-close">禁用</button>';
                        }

                    }
                    buttons += '</ul></div></div>';
                    return buttons;
                }
            }
        ],
        fnRowCallback : function(nRow,aData,iDataIndex){
            var status=aData.status;
            var html = '启用';
            if (status==0)
            {
                html = '禁用';
            }
            $('td:eq(6)', nRow).html(html);
            return nRow;
        }
    });
    // 点击显示模态框
    $("#video_list").off('click', '.btn-detail').on('click', '.btn-detail', function () {
        var id  = $(this).data('id');
        // 加载要修改的数据到 modal
        loadData(id);
        document.getElementById("videoImgList").innerHTML="";
        $("#modal-info").modal('show');
    });

    // 加载视频信息
    var loadData = function (id) {
        Request.get("video/videodetail/" + id, {}, function (e) {
            if (e.success) {
                setTimeout(function () {
                }, 10);
                $("form#info_form").find("#userName").val(e.data.userName);
                $("form#info_form").find("#fidId").val(e.data.fidId);
                $("form#info_form").find("#code").val(e.data.code);
                $("form#info_form").find("#uploadTime").val(e.data.uploadTime);
                $("form#info_form").find("#likeNum").val(e.data.likeNum);
                $("form#info_form").find("#video").attr("src",$("#video").val(e.data.likeNum));
                $.each(e.data.videoImgs,function (i, o) {
                    var div = document.getElementById("videoImgList");
                    var img = document.createElement("img");
                    img.src = o;
                    div.appendChild(img);
                })
            }
        });
    }
    // // 加载视频图片列表
    // var loadVideoImgListData = function (id) {
    //     Request.get("video/videodetailimg/" + id, {}, function (e) {
    //         if (e.success) {
    //             setTimeout(function () {
    //             }, 10);
    //                 $.each(e.data,function (i, o) {
    //                     var videoUrl = "file/download/" + o.md5;
    //                     var div = document.getElementById("videoImgList");
    //                     var img = document.createElement("img");
    //                     img.src = videoUrl;
    //                     div.appendChild(img);
    //                 })
    //             }
    //     });
    // }

    //视频启用
    $("#video_list").off('click', '.btn-open').on('click', '.btn-open', function () {
        var that = $(this);
        var id = that.data('id');
        var status = 1;
        toastr.info("启用中...");
        Request.put("video/update/" + id +"/"+status, {}, function (e) {
            if (e.success) {
                toastr.info("启用成功!");
                videoList.draw( false );
            } else {
                toastr.error(e.message);
            }
        });
    });

    //视频禁用提示框
     var videoId = null;
    $("#video_list").off('click', '.btn-close').on('click', '.btn-close', function () {
         videoId = $(this).data('id');;
        $("#modal-disabled").modal('show');
    });

    //视频禁用
    $("#modal-disabled").off('click', '.btn-close-sure').on('click', '.btn-close-sure', function () {
        var id = videoId;
        var status = 0;
        toastr.info("禁用中...");
        Request.put("video/update/" + id +"/"+status, {}, function (e) {
            if (e.success) {
                toastr.info("禁用成功!");
                videoList.draw( false );
            } else {
                toastr.error(e.message);
            }
        });
    });
});

