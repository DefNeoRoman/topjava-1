var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    $('#dateTime').datetimepicker();
    $('#startDate').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',

    });
    $('#endDate').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',

    });
    $('#startTime').datetimepicker({
        datepicker:false,
        format: 'H:i'
    });
    $('#endTime').datetimepicker({
        datepicker:false,
        format: 'H:i'
    });

    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "createdRow": function( row, data, dataIndex ) {
            //row - это уже обернутые JSON данные в формате HTML
            //data - есть те самые JSON данные
            //dataIndex - номер рядка

            if(data.exceed == true){
                $(row).addClass("exceeded");
            }else{
                $(row).addClass("normal");
            }
            },
           "columns": [
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    //здесь data уже распарсена в то что надо
                    var dateTime = data;
                    if (type === "display") {
                        return dateTime.replace("T"," ");
                    }
                    return dateTime;
                    //без return выскакивает какое-то предупреждение

                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"

            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "initComplete": makeEditable
    });

});