var submitForm = function(self, url) {
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', url);

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'customerID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
};

$('#btn-clear').click(function() {
    window.location.href = '/admin/customer.jsp';
});

$('.btn-remove').click(function() {
    submitForm(this, '/admin/customer/removehandler');
});

$('.btn-disable').click(function() {
    submitForm(this, '/admin/customer/disablehandler');
});

$('.btn-enable').click(function() {
    submitForm(this, '/admin/customer/enablehandler');
});