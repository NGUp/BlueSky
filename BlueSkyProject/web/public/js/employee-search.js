$('.btn-info').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.flight-id');
    window.location.href = '/employee/flight/details.jsp?flight=' + $(id).text();
});

$('#btn-clear').click(function() {
    window.location.href = '/employee/search.jsp';
});

$('#btn-previous').click(function() {
    var currentPage = $('#current-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/search.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/search.jsp?page=' + (parseInt(currentPage) - 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/search.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/search.jsp?page=' + (parseInt(currentPage) + 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-search').click(function() {
    var keyword = $('#keyword').val();
    
    window.location.href = '/employee/search.jsp?keyword=' + keyword;
});

$('#btn-cancel').click(function() {
    window.location.href = '/employee/search.jsp';
});