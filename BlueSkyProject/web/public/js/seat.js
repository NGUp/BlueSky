'use strict';

(function() {

var booked = false;

$('.seats td.seat').click(function() {
    var seat = $(this).context;
    
    if ($(seat).hasClass('booked') === false) {
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
    }
});

$.ajax({
    type: 'POST',
    url: '/employee/getbookedseat',
    data: {
        flightID: $('.txt-flight').val(),
        cabinID: $('.txt-cabin').val()
    }
}).done(function(data) {
    data.data.forEach(function(seat, index) {
        $('#' + seat).addClass('booked');
    });
});

})();