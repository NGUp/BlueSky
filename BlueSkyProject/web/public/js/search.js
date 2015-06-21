$('.btn-book').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var id = $(parent).children('.hidden');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/book.jsp');

    var input = document.createElement('input');
    $(input).attr('type', 'hidden');
    $(input).val($(id).text());
    $(input).attr('name', 'flightID');
    
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
});