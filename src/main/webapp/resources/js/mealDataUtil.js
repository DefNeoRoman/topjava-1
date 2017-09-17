function makeEditableMeal() {
    $("#mealDetailsForm").submit(function () {
        save();
        return false;
    });
    $("#mealDetailsEditForm").submit(function () {
        edit();
        return false;
    });

    $(".delete").click(function () {
        deleteRow($(this).attr("id"));
    });
    $(".update").click(function () {
        var id = $(this).attr("id");
        $.get(ajaxUrlMeal + id, function(data, status){
            $("#mealId").val(data.id);
            $("#editDescription").val(data.description);
            $("#editCalories").val(data.calories);
            $("#editDateTime").val(data.dateTime);
        });
        $("#editMeal").modal();

    });
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}
function addMeal() {
    $("#mealDetailsForm").find(":input").val("");
    $("#mealAddRow").modal();
}
function save() {
    var form = $("#mealDetailsForm");
    $.ajax({
        type: "POST",
        url: ajaxUrlMeal,
        data: form.serialize(),
        success: function () {
            $("#mealAddRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}
function edit() {
    var form = $("#mealDetailsEditForm");
    $.ajax({
        type: "POST",
        url: ajaxUrlMeal,
        data: form.serialize(),
        success: function () {
            $("#editMeal").modal("hide");
            updateTable();
            successNoty("Updated");
        }
    });
}
$("#mealFilter").submit(function () {
    filterMeal();
    return false;
});
function filterMeal() {
    var form = $("#mealFilter");
    $.ajax({
        url: $(form).attr('action'),
        type: $(form).attr('method'),
        data: $(form).serialize(),
        success: function (data) {
            ajaxUrl = ajaxUrlMeal + '?' + $(form).serialize();// в параметр запроса можно передавать Json
            mealDataApi.clear();
            $.each(data, function (key, item) {
                mealDataApi.row.add(item);
            });
            mealDataApi.draw();
            successNoty('Filtered');
        }
    });
    return false;
}

function clearFilter() {
    $("#mealFilter").find(":input").val(null);
    ajaxUrl = ajaxUrlMeal;
    updateTable();
    successNoty('Filter cleared');
}
//mealFilter
function updateTable() {
    $.get(ajaxUrlMeal, function (data) {
        mealDataApi.clear().rows.add(data).draw();
    });
}
function deleteRow(id) {
    $.ajax({
        url: ajaxUrlMeal + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}
var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='glyphicon glyphicon-ok'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}