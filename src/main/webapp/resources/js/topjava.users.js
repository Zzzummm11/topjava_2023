const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.get(ctx.ajaxUrl, drawTable);
    }
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
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
        })
    );
});

function enable(checkbox, userId) {
    let enabled = checkbox.is(":checked");
    $.ajax({
        url: userAjaxUrl + userId,
        type: "POST",
        data: {enabled: enabled},
        success: function () {
            checkbox.closest("tr").attr("data-user-enabled", enabled)
            successNoty(enabled ? "Enabled" : "Disabled");
        },
        error: function () {
            checkbox.prop('checked', !enabled);
        }
    })
}
