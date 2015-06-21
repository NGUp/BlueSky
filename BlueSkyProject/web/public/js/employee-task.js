$('.combobox').addClass('form-control');
$('.combobox').combobox();

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    window.location.href = '/employee/trip/update.jsp?trip=' + $(id).text();
});

$('#btn-clear').click(function() {
    window.location.href = '/employee/task.jsp';
});

$('#btn-previous').click(function() {
    var currentPage = $('#current-page').text(),
        month = $('#cbx-month').val(),
        year = $('#txt-year').val();
        
    if (month === '' || year === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) - 1) + '&month=' + month + '&year=' + year;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        month = $('#cbx-month').val(),
        year = $('#txt-year').val();
        
    if (month === '' || year === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) + 1) + '&month=' + month + '&year=' + year;
        }
    }
});