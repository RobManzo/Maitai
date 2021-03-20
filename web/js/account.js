$(document).ready(function () {

});

function cambiadet() {
   var newtel = $('#cell').val();
   var newaddr = $('#address').val();

   if(newtel!=''){
       var phoneno = /^\d{10}$/;
       if(newtel.match(phoneno)){
           $.ajax({
               url: './account',
               dataType: 'json',
               type: 'post',
               data: {
                   'rtype' : 'changetel',
                   'newtel' : newtel
               },
               success: function (data) {
                   alert(data.message);

               },
               error: function (errorThrown) {
                   console.log(errorThrown);
               }
           });

       }
   }

    if(newaddr!=''){
            $.ajax({
                url: './account',
                dataType: 'json',
                type: 'post',
                data: {
                    'rtype' : 'changeaddr',
                    'newtel' : newaddr
                },
                success: function (data) {
                    alert(data.message);

                },
                error: function (errorThrown) {
                    console.log(errorThrown);
                }
            });
        }
    alert("Modifiche Salvate. Verrai reinderizzato.");
    setTimeout(function() {
        location.reload();
    }, 3000);

}

function cambiapsw() {
    if (checkPass()){
        var newpass = $('#newpsw').val();

        $.ajax({
            url: './account',
            dataType: 'json',
            type: 'post',
            data: {
                'rtype' : 'changepsw',
                'newpass' : newpass
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
