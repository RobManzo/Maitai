$(document).ready(function () {

});

/**
 * Funzione per il reset della password
 */
function resetpsw(){
    var email = $('#email').val();
    var codfisc = $('#codfisc').val();

    $.ajax({
        url: './pswreset',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'pswReset',
            'email': email,
            'codfisc' : codfisc
        },
        success: function (data) {
            alert(data.Message);
            setTimeout(function() {
                location.reload();
            }, 500);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Funzione per mostrare avviso di errore
 */
function loginerror() {
    console.log("AO");
    alert("Email o Password errate! Riprovare!");
}