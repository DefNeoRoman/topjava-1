var ajaxUrl = "ajax/admin/users/";
var datatableApi;
function enable(checkbox, userId) {
    var enabled = checkbox.is(':checked')
    $.ajax({
        type: "POST",
        url: ajaxUrl + userId+"/"+enabled,
        data: "enabled="+enabled, // передается как параметр запроса
        //но знак вопроса при передаче параметров почему-то не ставится
        //он ставится как бы сам в строке запроса браузера или когда выводишь через
        //Console.log
        success: function () {

            checkbox.closest("tr").toggleClass("disabled");
            successNoty(enabled ? "Enabled" : "Disabled");
        },
        error: function () {
            $(checkbox).prop("checked", !enabled);
        }
    });

}
// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();

});