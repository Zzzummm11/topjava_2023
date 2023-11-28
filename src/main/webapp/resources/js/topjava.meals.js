const mealAjaxUrl = "profile/meals/";

const ctx = {
    ajaxUrl: mealAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "language": {
                "search": "Искать:",
            },
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
        })
    );
});

function applyFilter() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(function (data) {
        filterTable(data)
        successNoty("Filtered");
    });
}

function filterTable(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}
