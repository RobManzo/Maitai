$(document).ready(function () {

    $('#next-1').addClass('disabled');
    $('#next-2').addClass('disabled');

    emptyfields1();
    emptyfields2();
    parseProvince();

    $("#next-1").click(function () {
        $("#first").hide();
        $("#second").show();
        $("#progressBar").css("width", "60%").html("STEP 2");
    });

    $("#prev-2").click(function () {
        $("#first").show();
        $("#second").hide();
        $("#progressBar").css("width", "30%").html("STEP 1");
    });

    $("#next-2").click(function () {
        $("#third").show();
        $("#second").hide();
        $("#progressBar").css("width", "100%").html("STEP 3");
    });

    $("#prev-3").click(function () {
        $("#second").show();
        $("#third").hide();
        $("#progressBar").css("width", "60%").html("STEP 2");
    });

});

/*function checkvoid(elem) {
    if (elem.val() == ''){
        elem.siblings("small").html("Il campo non può essere vuoto.").css('color', 'red');
        elem.addClass('wrong');
    } else {
        elem.siblings("small").html("");
        elem.removeClass('wrong');
    }
};*/

/**
 * Funzioni check dei campi
 */
function emptyfields1() {
    $('#first input').on('keyup', function () {
        var empty = 0;

        $('#first input:text').map(function () {
            if ($(this).val() === '') {
                empty += 1;
            }
        });

        if (empty === 0 && checkCodFisc()){
            $('#next-1').removeClass('disabled');
        } else {
            $('#next-1').addClass('disabled');
        }
    });
};

function emptyfields2(){
    $('#second input').on('keyup',function () {
        var empty = 0;

        $('#second input:text').map(function() {
            if($(this).val() == ''){
                empty += 1;
            }
        });

        if (empty === 0 && checkPass() && checkEmail() && checkPhone()){         //verifica prima che i campi non siano vuoti, poi passa alla checkpass e checkemail - Devo valutare le espressioni contemporaneamente
            $('#next-2').removeClass('disabled');

        } else{
            $('#next-2').addClass('disabled');
        }
    });

};

/**
 * Funzione check codice fiscale
 * @returns {boolean}
 */
function checkCodFisc(){
    var cod = $('#codfisc').val().toUpperCase();
    $('#codfisc').val(cod);

    if($('#codfisc').val().length == 16){
        $('#codfisc').siblings("small").html("");
        $('#codfisc').removeClass('wrong');
        return true;
    } else if($('#codfisc').val() == ''){
        $('#codfisc').siblings("small").html("");
        $('#codfisc').removeClass('wrong');
        return false;
    } else{
        $('#codfisc').siblings("small").html("Si prega di inserire un codice fiscale corretto.").css('color', 'red');
        $('#codfisc').addClass('wrong');
        return false;
    }
};

/**
 * Funzione check campo email
 * @returns {boolean}
 */
function checkEmail(){
    if ($('#email').val() === $('#confirmemail').val() && $('#email').val() && $('#confirmemail').val()) {
        $('#emailhelp').html('Ok').css('color', 'green');
        $('#confemailhelp').html('Ok').css('color', 'green');
        $('#email').removeClass('wrong');
        $('#confirmemail').removeClass('wrong');
        return true;

    } else if (!$('#email').val() || !$('#confirmemail').val()){
        $('#emailhelp').html('');
        $('#confemailhelp').html('');
        $('#email').removeClass('wrong');
        $('#confirmemail').removeClass('wrong');
        return false;
    } else {
        $('#emailhelp').html('Le email non coincidono.').css('color', 'red');
        $('#confemailhelp').html('Le email non coincidono').css('color', 'red');
        $('#email').addClass('wrong');
        $('#confirmemail').addClass('wrong');
        return false;
    }
};


/**
 * Funzione check password
 * @returns {boolean}
 */
function checkPass(){
    var regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");

    if(regex.test($('#password').val()) || $('#password').val() == '') {
        $('#passhelp').html('');

        if ($('#password').val() === $('#confirmpassword').val() && $('#confirmpassword').val() && $('#password').val()) {
            $('#passhelp').html('Ok').css('color', 'green');
            $('#confpasshelp').html('Ok').css('color', 'green');
            $('#password').removeClass('wrong');
            $('#confirmpassword').removeClass('wrong');
            return true;

        } else if (!$('#password').val() || !$('#confirmpassword').val()) {
            $('#passhelp').html('');
            $('#confpasshelp').html('');
            $('#password').removeClass('wrong');
            $('#confirmpassword').removeClass('wrong');
            return false;
        } else {
            $('#passhelp').html('Le password non coincidono.').css('color', 'red');
            $('#confpasshelp').html('Le password non coincidono').css('color', 'red');
            $('#password').addClass('wrong');
            $('#confirmpassword').addClass('wrong');
            return false;
        }
    } else{
        $('#passhelp').html('Almeno 8 caratteri, una maiuscola minimo.').css('color', 'red');
    }

};

/**
 * Funzione check campo telefono
 * @returns {boolean}
 */
function checkPhone() {
    var regex = new RegExp("[0-9]{10}$");

    if(regex.test($('#phone').val())){
        $('#phone').removeClass('wrong');
        $('#phonehelp').css('color', '').html('');
        return true;
    } else {
        $('#phonehelp').css('color', 'red').html('Inserire un numero di telefono corretto.');
        $('#phone').addClass('wrong');
        return false;
    }

};

/**
 * Funzione per il parsing delle provincie, caricandole da un file CSV
 */
var dati;
function parseProvince() {
    $.ajax({
        url: '/Maitai/assets/misc/comuni.csv',
        dataType: 'text',
    }).done(function (data) {
        var value = data.split("\n");
        dati = value;
        $.each(value, function(key, val){
            document.getElementById("province").innerHTML +=
                "<option>" + val + "</option>";

        });
        /*for(com in value){
            console.log(com);
            console.log(value);
            document.getElementById("prov").innerHTML +=
                "<option>" + com + "</option>";
        }*/
    });

}


function submitform(){
    var nome = $('#nome').val();
    var cognome = $('#cognome').val();
    var codfisc = $('#codfisc').val();
    var address = $('#address').val();
    var prov = $('#province').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var confpass = $('#confirmpassword').val();
    var phone = $('#phone').val();
    var birthdate = ($('#birthdate').val());
    var role =($('#role').val());

    $.ajax({
        url: './adduser',
        dataType: 'json',
        type: 'post',
        data: {
            'Nome': nome,
            'Cognome': cognome,
            'Birthdate' : birthdate,
            'CFiscale' : codfisc,
            'Indirizzo' : address,
            'Provincia' : prov,
            'Email' : email,
            'Password' : password,
            'Confpass' : confpass,
            'Telefono' : phone,
            'Ruolo' : role,
        },
        success: function (data) {
            var text ='<div class="row" style="justify-content: center">' + data.Message +'</div> </div>';

            if(data.RESPONSE === 'Correct'){
                $('#progressBar').css("width", "100%").removeClass('bg-danger').addClass('bg-success').html("COMPLETE");
                $('#response').html(text);
                setTimeout(function(){
                    window.location.href = './';
                }, 10000);
            }  else{
                $('#progressBar').css("width", "100%").html("ERROR");
                $('#response').html(text);
                setTimeout(function(){
                    window.location.href = './adduser';
                }, 10000);
            }
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};