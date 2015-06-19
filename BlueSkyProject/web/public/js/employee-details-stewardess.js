$('.combobox').addClass('form-control');
$('.combobox').combobox();

$('#btn-back').click(function() {
    window.location.href = '/employee/flight.jsp';
});

$('.btn-remove').click(function() {
    var self = this;
    var parent = $(self).parents()[1];
    var cabinId = $(parent).children('.cabin-id');
    var flightId = $('#flight-id');
    var employeeId = $(parent).children('.stewardess-id');
    
    var form = document.createElement('form');
    $(form).attr('method', 'post');
    $(form).attr('action', '/employee/flight/cabin/stewardess/deletehandler');

    var inputEmployee = document.createElement('input');
    $(inputEmployee).attr('type', 'hidden');
    $(inputEmployee).val($(employeeId).text());
    $(inputEmployee).attr('name', 'stewardessID');

    var inputFlight = document.createElement('input');
    $(inputFlight).attr('type', 'hidden');
    $(inputFlight).val($(flightId).text());
    $(inputFlight).attr('name', 'flightID');
    
    var inputCabin = document.createElement('input');
    $(inputCabin).attr('type', 'hidden');
    $(inputCabin).val($(cabinId).text());
    $(inputCabin).attr('name', 'cabinID');
    
    form.appendChild(inputEmployee);
    form.appendChild(inputFlight);
    form.appendChild(inputCabin);
    document.body.appendChild(form);
    form.submit();
});