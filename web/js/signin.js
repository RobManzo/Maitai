
$(document).ready(function () {

    $('#next-1').addClass('disabled');
    $('#next-2').addClass('disabled');

    emptyfields1();
    emptyfields2();

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

function emptyfields1() {
    $('#first input').on('keyup', function () {
        var empty = 0;

        $('#first input:text').map(function () {
            if ($(this).val() == '') {
                empty += 1;
            }
        });

        if (empty == 0 && checkCodFisc()){
            $('#next-1').removeClass('disabled');
        } else {
            $('#next-1').addClass('disabled');
        }
    });
};

function emptyfields2(){
    $('#second input').on('keyup',function () {
        var empty = 0;
        var check = true;

        $('#second input:text').map(function() {
            if($(this).val() == ''){
                empty += 1;
            }
        });

        if (empty == 0 && checkPass() && checkEmail()){         //verifica prima che i campi non siano vuoti, poi passa alla checkpass e checkemail - Devo valutare le espressioni contemporaneamente
            $('#next-2').removeClass('disabled');

        } else{
            $('#next-2').addClass('disabled');
        }
    });

};

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

function checkEmail(){

    if ($('#email').val() == $('#confirmemail').val() && $('#email').val() && $('#confirmemail').val()) {
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

function checkPass(){

    if ($('#password').val() == $('#confirmpassword').val() && $('#confirmpassword').val() && $('#password').val()) {
        $('#passhelp').html('Ok').css('color', 'green');
        $('#confpasshelp').html('Ok').css('color', 'green');
        $('#password').removeClass('wrong');
        $('#confirmpassword').removeClass('wrong');
        return true;

    } else if (!$('#password').val() || !$('#confirmpassword').val()){
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

};