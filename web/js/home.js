
$(document).ready(function () {



});

function access(){
    var timestamp = new Date();
    var dd = timestamp.getDate();
    var mm = timestamp.getMonth()+1;
    var yyyy = timestamp.getFullYear();

    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }

    var today = yyyy+'/'+mm+'/'+dd;

    $.ajax({
        url: './booking',
        dataType: 'json',
        type: 'post',
        data: {
            'DataOdierna' : timestamp,
        },
        success: function (data) {

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};



