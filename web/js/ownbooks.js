$(document).ready(function () {

});

function booksum(){

    var intestazione = '<table id="tabella" class=\" table table-striped\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prenotazione</th> ' +
        '<th scope="col">Data Prenotazione</th>' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col">TOT €</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '</table>';

    $('#booktab').html(intestazione);

    $.ajax({
        url: './ownbooks',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getBooks'
        },
        success: function (data) {
            $.each(data.Prenotazioni, function(key, val){
                var d1 = val.dataPrenotazione;
                console.log(d1.toLocaleString());
                //VAL.NOMECAMPO
            });
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};