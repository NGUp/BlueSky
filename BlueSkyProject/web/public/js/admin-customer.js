$('#btn-clear').click(function() {
    window.location.href = '/admin/customer.jsp';
});

$('.btn-remove').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/admin/customer/removehandler');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'customerID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});

$('.btn-disable').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/admin/customer/disablehandler');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'customerID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});

$('.btn-enable').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/admin/customer/enablehandler');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'customerID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});