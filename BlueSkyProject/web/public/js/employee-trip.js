$('#btn-insert').click(function() {
    window.location.href = '/employee/trip/create.jsp';
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.trip-id');
    window.location.href = '/employee/trip/update.jsp?trip=' + $(id).text();
});