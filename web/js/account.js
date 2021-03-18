$(document).ready(function () {


});

function cambiapsw() {
    if (checkPass()){
        var newpass = $('#newpsw').val();

        $.ajax({
            url: './account',
            dataType: 'json',
            type: 'post',
            data: {
                'rtype' : changepsw,
                'newpass' : newpass,
            },
            success: function (data) {
                alert(data.message);
                setTimeout(function() {
                    location.reload();
                }, 3000);

            },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
        });
    }

}

function checkPass(){
    var regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");

    if(regex.test($('#newpsw').val())) {

        if ($('#newpsw').val() == $('#reppsw').val() && $('#reppsw').val() && $('#newpsw').val())
            return true;
        else
            alert('Le password non coincidono');
            return false;

    } else{
        alert('La password deve contenere almeno 8 caratteri e una lettera maiuscola.');
        return false;
    }

};

