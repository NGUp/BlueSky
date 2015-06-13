$('#btn-clear').click(function() {
    window.location.href = '/employee/plane.jsp';
});

$('#btn-insert').click(function() {
    window.location.href = '/employee/plane/create.jsp';
});

$('.btn-details').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    window.location.href = '/employee/plane/cabin.jsp?plane=' + $(id).text();
});

$('.btn-remove').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/employee/plane/removehandler');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'planeID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});