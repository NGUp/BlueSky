'use strict';

(function() {

angular.module('update-ticket-price', [])

    .controller('UpdateCtrl', ['$scope', '$http', function(scope, http) {
            
        $('.input-group.date').datepicker({
            format: 'dd/mm/yyyy',
            todayBtn: true,
            todayHighlight: true
        });

        http.get('/ajax/employee/getcabinbyflight?flightID=' + $('#cbx-flight').val()).then(function(data) {
            scope.cabins = data.data.data;
        });
        
        // enable Bootstrap Combobox
        $('.combobox').addClass('form-control');
        $('.combobox').combobox();
        
        $('.combobox').change(function(e) {
            var flight = $('#cbx-flight').val();

            if (flight !== null) {
                http.get('/ajax/employee/getcabinbyflight?flightID=' + flight).then(function(data) {
                    scope.cabins = data.data.data;
                });
            }
        });
        
        $('#btn-cancel').click(function() {
            window.location.href = '/employee/ticket.jsp';
        });
    }])
;
    
})();