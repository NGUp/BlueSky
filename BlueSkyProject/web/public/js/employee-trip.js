$('#btn-insert').click(function() {
    window.location.href = '/employee/trip/create.jsp';
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    window.location.href = '/employee/trip/update.jsp?trip=' + $(id).text();
});

$('#btn-clear').click(function() {
    window.location.href = '/employee/trip.jsp?page=1';
});

$('#btn-previous').click(function() {
    var currentPage = $('#current-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage > 1) {
            window.location.href = '/employee/trip.jsp?page=' + (parseInt(currentPage) - 1);
        }
    } else {
        if (currentPage > 1) {
            window.location.href = '/employee/trip.jsp?page=' + (parseInt(currentPage) - 1) + '&keyword=' + keyword;
        }
    }
});

$('#btn-next').click(function() {
    var currentPage = $('#current-page').text(),
        totalPage = $('#total-page').text(),
        keyword = $('#keyword').val();
        
    if (keyword === '') {
        if (currentPage < totalPage) {
            window.location.href = '/employee/trip.jsp?page=' + (parseInt(currentPage) + 1);
        }
    } else {
        if (currentPage < totalPage) {
            window.location.href = '/employee/trip.jsp?page=' + (parseInt(currentPage) + 1) + '&keyword=' + keyword;
        }
    }
    
});

$('#btn-search').click(function() {
    var keyword = $('#keyword').val();
    
    window.location.href = '/employee/trip.jsp?keyword=' + keyword;
});

$('.btn-remove').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/employee/trip/removehandler');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'tripID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});