//Scripts per registrazione

$(document).ready(function () {

    $('#first input').on('keyup',function () {
        var empty = 0;

        $('#first input:text').map(function() {
            if($(this).val() == ''){
                empty += 1;
            }
        });

        alert(empty);
        if (empty != 0){
            $('#next-1').addClass('disabled');
        } else{
            $('#next-1').removeClass('disabled');
            alert("ensommer");
        }
    });

    $('#second input:text').on('keyup',function () {
        if ($('#second input:text').val() == ''){
            $('#next-2').addClass('disabled');
        } else{
            $('#next-2').removeClass('disabled');
        }
    });

    $('#password, #confirmpassword').on('keyup', function () {

        if ($('#password').val() == $('#confirmpassword').val() && $('#confirmpassword').val() && $('#password').val()) {
            $('#passhelp').html('Ok').css('color', 'green');
            $('#confpasshelp').html('Ok').css('color', 'green');
            $('#password').css('border-color', '');
            $('#confirmpassword').css('border-color', '');
            $('#next-2').removeClass('disabled');

        } else if (!$('#password').val() || !$('#confirmpassword').val()){
            $('#passhelp').html('');
            $('#confpasshelp').html('');
            $('#password').css('border-color', '');
            $('#confirmpassword').css('border-color', '');
        } else {
            $('#passhelp').html('Le password non coincidono.').css('color', 'red');
            $('#confpasshelp').html('Le password non coincidono').css('color', 'red');
            $('#password').css('border-color', 'red');
            $('#confirmpassword').css('border-color', 'red');
            $('#next-2').addClass('disabled');
        }
    });

    //Check campi email e conferma email

    $('#email, #confemail').on('keyup', function () {

        if (!$('#email').val()() || !$('#confemail').val()()){
            $('#confmess').html('').css('color', 'transparent');
            $('#next-2').addClass('disabled');
        } else if(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/ == $('#email').val()){
            if ($('#email').val() == $('#confemail').val()) {
                $('#mailhelp').html('Ok').css('color', 'green');
                $('#confemailhelp').html('Ok').css('color', 'green');
                $('#next-2').removeClass('disabled');
            }
            else {
                $('#mailhelp').html('Le email non corrispondono.').css('color', 'red');
                $('#confemailhelp').html('Le email non corrispondono.').css('color', 'red');}
                $('#next-2').addClass('disabled');
            }
    });

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