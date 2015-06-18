$('.combobox').addClass('form-control');
$('.combobox').combobox();

$('.input-group.date').datepicker({
    format: 'dd/mm/yyyy',
    todayBtn: true,
    todayHighlight: true
});

$('#btn-cancel').click(function() {
    window.location.href = '/employee/flight.jsp';
});