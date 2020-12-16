//Scripts    per registrazione

$(document).ready(function () {

    $('#password, #confirmpassword').on('keyup', function () {

        if ($('#password').val() == $('#confirmpassword').val() && $('#confirmpassword').val() && $('#password').val() ) {
            $('#confmess').html('Matching').css('color', 'green');
        } else if (!$('#password').val() && !$('#confirmpassword').val()){
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


});