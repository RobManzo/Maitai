$(document).ready(function () {

});

function loadprod() {
    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'getProd'
        },
        success: function (data) {

        },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
    });

};