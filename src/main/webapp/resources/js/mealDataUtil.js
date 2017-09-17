function makeEditableMeal() {
    $("#mealDetailsForm").submit(function () {
        save();
        return false;
    });
    $(".delete").click(function () {
        deleteRow($(this).attr("id"));
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