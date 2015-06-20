var booked = false;

$('.seats td.seat').click(function() {
    var seat = $(this).context;
    
    if ($(seat).hasClass('checked')) {
        if (booked === true) {
            $(seat).removeClass('checked');
            $('.txt-seat').val('');
            booked = false;
        }
    } else {
        if (booked === false) {
            $(seat).addClass('checked');
            $('.txt-seat').val($(seat).text());
            booked = true;
        }
    }
});

$('#btn-back').click(function() {
    window.location.href = '/employee/flight/details.jsp?flight=' + $('.txt-flight').val();
});