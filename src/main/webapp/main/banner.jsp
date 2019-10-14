<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/banner/queryAll",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            datatype: "json",
            colNames: ["主键", "标题", "图片", "状态", "创建时间", "描述"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "imgPath", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "' />"
                    }
                },
                {
                    name: "status", editable: true, edittype: 'select',
                    editoptions: {value: 'y:展示;n:不展示'}
                },
                {name: "createDate", formatter: "date"},
                {name: "desc", editable: true}
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#bannerPager",
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#bannerPager",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#title").attr("readonly", true);
                    obj.find("#imgPath").attr("disabled", true);
                    obj.find("#desc").attr("readonly", true);
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
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "imgPath",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid");
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

    function excel() {
        location.href = "${pageContext.request.contextPath}/banner/excel"
    }


</script>


<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图列表</a></li>
        <li role="presentation"><a href="#profile" onclick="excel()" aria-controls="profile" role="tab"
                                   data-toggle="tab">导出</a></li>
    </ul>

</div>

<table id="bannerList"></table>
<div id="bannerPager"></div>