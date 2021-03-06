$('#btn-insert').click(function() {
    window.location.href = '/employee/flight/create.jsp';
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    window.location.href = '/employee/flight/update.jsp?flight=' + $(id).text();
});

$('.btn-employee').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    window.location.href = '/employee/flight/stewardess/details.jsp?flight=' + $(id).text();
});

$('#btn-clear').click(function() {
    window.location.href = '/employee/flight.jsp';
});

$('#btn-previous').click(function() {
    var currentPage = $('#current-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/flight.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/flight.jsp?page=' + (parseInt(currentPage) - 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/flight.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/flight.jsp?page=' + (parseInt(currentPage) + 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-search').click(function() {
    var keyword = $('#keyword').val();
    
    window.location.href = '/employee/flight.jsp?keyword=' + keyword;
});