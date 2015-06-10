var textboxs = $('input[type=text], input[type=tel]');

$.each(textboxs, function(index, value) {
    if ($(value).val() === 'null') {
        $(value).val('');
    }
});