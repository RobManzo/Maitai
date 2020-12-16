//Scripts per registrazione

$(document).ready(function () {
    e.preventDefault();

    checkForm();
});

function checkForm(){

    $('#password, #confirmpassword').on('keyup', function () {

        const nome = document.getElementById("nome");
        const cognome = document.getElementById("cognome");
        const birthdate =  document.getElementById("birthdate");
        const codfisc = document.getElementById("codfisc");
        const address = document.getElementById("address");
        const provincia = document.getElementById("prov");
        const email = document.getElementById("email");
        const password = document.getElementById("password");
        const confirmpassword = document.getElementById("confirmpassword");
        const telefono = document.getElementById("phone")

        if ($('#password').val() == $('#confirmpassword').val() && $('#confirmpassword').val() && $('#password').val() ) {
            //$('#confmess').html('Ok').css('color', 'green');
            password.setCustomValidity("Ok.");
            confirmpassword.setCustomValidity("Ok.");
            $('#password').css('border-color', '');
            $('#confirmpassword').css('border-color', '');
            $('#next-2').removeClass('disabled');

        } else if (!$('#password').val() && !$('#confirmpassword').val()){
            //$('#confmess').html('').css('color', 'transparent');
            password.setCustomValidity("");
            confirmpassword.setCustomValidity("");
            $('#password').css('border-color', '');
            $('#confirmpassword').css('border-color', '');
        } else {
            //$('#confmess').html('Not Matching').css('color', 'red');
            password.innerHTML = "Le password non coincidono.";
            confirmpassword.innerHTML = "Le password non coincidono.";
            $('#password').css('border-color', 'red');
            $('#confirmpassword').css('border-color', 'red');
        }
    });

    $('#email, #confemail').on('keyup', function () {

        if ($('#email').val() == $('#email').val() && $('#email').val() && $('#confemail').val() ) {
            $('#confmess').html('Matching').css('color', 'green');
        } else if (!$('#email').val() && !$('#confemail').val()){
            $('#confmess').html('').css('color', 'transparent');
        } else {
            $('#confmess').html('Not Matching').css('color', 'red');
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


};