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
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) - 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/task.jsp?page=' + (parseInt(currentPage) + 1) + '&keyword=' + keyword;
        }
    }
});