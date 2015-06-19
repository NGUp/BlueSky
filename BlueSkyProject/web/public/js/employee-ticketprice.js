$('#btn-insert').click(function() {
    window.location.href = '/employee/ticket/create.jsp';
});

$('#btn-previous').click(function() {
    var currentPage = $('#current-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/ticket.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/ticket.jsp?page=' + (parseInt(currentPage) - 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/ticket.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/plane.jsp?page=' + (parseInt(currentPage) + 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-search').click(function() {
    var keyword = $('#keyword').val();
    
    window.location.href = '/employee/ticket.jsp?keyword=' + keyword;
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.ticket-price-id');
    window.location.href = '/employee/ticket/update.jsp?ticketprice=' + $(id).text();
});