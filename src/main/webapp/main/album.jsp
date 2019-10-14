<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/findAllAlbum",
            editurl: "${pageContext.request.contextPath}/album/edit",
            datatype: "json",
            colNames: ["主键", "标题", "封面路径", "评分", "作者", "播音", "集数", "发布时间", "简介", "状态"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "' />"
                    }

                },
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "beam", editable: true},
                {name: "count"},
                {name: "publishDate"},
                {name: "content", editable: true},
                {
                    name: "status", editable: true,
                    edittype: 'select',
                    editoptions: {value: 'y:展示;n:不展示'}
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#albumPager",
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#cover").attr("disabled", true);
                    obj.find("#score").attr("readonly", true);

                }


            },
            {//在添加数据 之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("2")
                }*/
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid");
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("3")
                }*/
            }
        )
    });

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "' ></table>" + "<div id='" + divId + "' ></div>"
        );
        $("#" + tableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/findAllAlbum?albumId=" + albumId,
            editurl: "${pageContext.request.contextPath}/chapter/edit?albumId=" + albumId,
            datatype: "json",
            colNames: ["主键", "标题", "大小", "时长", "音频", "创建时间", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "longs"},
                {name: "filepath", editable: true, edittype: "file"},
                {name: "createDate", formatter: "date"},
                {
                    name: "filepath",
                    formatter: function (cellValue, options, rowObject) {
                        return "<a onclick=\"playAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-play-circle'></span></a>" + "                       " +
                            "<a onclick=\"downloadAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#" + divId,
            page: 1,
            rowNum: 3,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#" + divId,
            {},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#filepath").attr("disabled", true);
                    /*obj.find("#score").attr("readonly",true);
*/
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload?albumId=" + albumId,
                        fileElementId: "filepath",
                        data: {chapterId: chapterId},
                        success: function (data) {
                            $("#" + tableId).trigger("reloadGrid");
                            $("#albumList").trigger("reloadGrid");
                        }
                    });
                    return response
                }
            },
            {

                afterSubmit: function () {
                    $("#" + tableId).trigger("reloadGrid");
                    $("#albumList").trigger("reloadGrid");
                    return "adf";
                }
            }
        )

    }

    function playAudio(d) {
        $("#dialogId").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/audio/" + d);
    }

    function downloadAudio(a) {
        location.href = "${pageContext.request.contextPath}/chapter/download?audioName=" + a;
    }

</script>


<div id="dialogId" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" controls src=""></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="albumList"></table>
<div id="albumPager"></div>