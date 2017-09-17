function makeEditable() {
    $(".delete").click(function () {
        deleteRow($(this).attr("id"));
    });
    $(".update").click(function () {
        var id = $(this).attr("id");
        $.get(ajaxUrl + id, function(data, status){
            $("#userId").val(data.id);
          $("#editName").val(data.name);
          $("#editEmail").val(data.email);
          $("#editPassword").val(data.password);
        });
        $("#editUser").modal();
    });
    $("#detailsForm").submit(function () {
        save();
        return false;
    });
    $("#detailsEditForm").submit(function () {
        updateUser();
        return false;
    });
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    $("#detailsForm").find(":input").val("");
    $("#editRow").modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function save() {
    var form = $("#detailsForm");
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}
function updateUser() {
    var form = $("#detailsEditForm");
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editUser").modal("hide");
            updateTable();
            successNoty("Updated");
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