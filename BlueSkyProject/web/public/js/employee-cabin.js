$('#btn-insert').click(function() {
    window.location.href = '/employee/plane/cabin/create.jsp';
});

$('#btn-back').click(function() {
    window.location.href = '/employee/plane.jsp';
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.cabin-id');
    window.location.href = '/employee/plane/cabin/seat.jsp?plane=' + $(id).text() + '&cabin=' + $(id).text();
});

$('.btn-remove').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/employee/plane/cabin/removehandler');

    var inputCabin = document.createElement('input');
    $(inputCabin).attr('type', 'hidden');
    $(inputCabin).val($(id).text());
    $(inputCabin).attr('name', 'cabinID');
    
    var inputPlane = document.createElement('input');
    $(inputPlane).attr('type', 'hidden');
    $(inputPlane).val($('#plane-id').text());
    $(inputPlane).attr('name', 'planeID');
    
    form.appendChild(inputCabin);
    form.appendChild(inputPlane);
    document.body.appendChild(form);
    form.submit();
});