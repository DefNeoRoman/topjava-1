var ajaxUrlMeal = "/ajax/profile/meals/";
var mealDataApi;

// $(document).ready(function () {
$(function () {
    mealDataApi = $("#mealDataTable").DataTable({
        "paging": false,
        "info": true,
      "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
    makeEditableMeal();

});
