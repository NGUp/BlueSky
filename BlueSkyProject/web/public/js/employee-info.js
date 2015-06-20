$('.input-group.date').datepicker({
    format: 'dd/mm/yyyy',
    todayBtn: true,
    todayHighlight: true
});

var textboxs = $('input[type=text], input[type=tel], input[type=email]');

$.each(textboxs, function(index, value) {
    if ($(value).val() === 'null') {
        $(value).val('');
    }
});

$('.btn-cancel').click(function() {
    window.location.href = '/employee/index.jsp';
});