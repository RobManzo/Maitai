$(document).ready(function () {
    bookings();
});

function bookings(){
    var intestazione = '<table class=\" table table-striped text-center\"">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prenotazione</th> ' +
        '<th scope="col">idPostazione</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">Fascia Oraria</th> ' +
        '<th scope="col">Info</th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>'
        '</table>';

    $('#bookings').html(intestazione);

    $.ajax({
        url: './histbook',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getBookings'
        },
        success: function (data) {
            var books = data.Prenotazioni
            $.each(books, function(key, val){
                console.log(val);
                var entry;
                if(val.oraIngresso!== null && val.oraUscita === null) entry = 'ENTRATO';
                else if (val.oraIngresso === null) entry = 'PRENOTATO';
                else entry = 'USCITO';
               $('#tabella').append('<tr class="text-center"> <th scope="row"> '+ val.idPrenotazione +'</th><td>'+ val.idPostazione +'</td><td>'+ entry +'</td> <td>'+ val.fasciaOraria +'</td> <td> <a href="#" class="prenotazione" id="' + val.idPrenotazione + '" onclick="infopren('+ val.idPrenotazione +')"><img class="img-responsive" src="\\Maitai\\assets\\img\\lente.png"></a> </td> </tr>');

            });
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
};

function infopren(id) {
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prenotazione</th> ' +
        '<th scope="col">ID Utente</th> ' +
        '<th scope="col">Data Prenotazione</th>' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">Costo</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabinfo">' +
        '</tbody>'+
        '</table>';

    $.ajax({
        url: './histbook',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getPren',
            'idPren': id
        },
        success: function (data) {
            var pren = data.Prenotazione;
            var pos = pren.idPostazione.split(",").join("-");
            var dp = pren.dataPrenotazione;
            var month;

            console.log(data.UserId);

            if(dp.monthValue < 10){
                month = "0" + dp.monthValue;
            } else month = dp.monthValue;

            var datepren = dp.dayOfMonth + "/" + month + "/" + dp.year;

            var today = data.Today
            if(today.monthValue < 10){
                nowmonth= "0" + today.monthValue;
            } else nowmoth = today.monthValue;

            let entry;
            if(pren.oraIngresso!== null && pren.oraUscita === null) entry = 'ENTRATO';
            else if (pren.oraIngresso === null) entry = 'PRENOTATO';
            else entry = 'USCITO';

            if(entry === 'ENTRATO'){
                var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                    ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                    '<div class="modal-header">  <h4 class="modal-title">Prenotazione #' + pren.idPrenotazione + '</h4> ' +
                    '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                    '</div> ' +
                    '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                    '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="setExit('+ pren.idPrenotazione +')">Fai Uscire</button> '+
                    '<button type="button" class="btn btn-danger" data-dismiss="modal">Chiudi</button>' +
                    '</div> </div> </div>';
            } else var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Prenotazione #' + pren.idPrenotazione + '</h4> ' +
                '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal">Chiudi</button>' +
                '</div> </div> </div>';

            $('#infobook').html(mhead);
            $('#modalinfo').html(intestazione);
            $('#tabinfo').append('<tr class="text-center"> <th scope="row"> '+ pren.idPrenotazione +' </th> <td>'+ data.UserId+'</td> <td>'+ datepren +'</td> <td>'+ pos +'</td><td>'+ entry +'</td> <td>'+ pren.price +'€</td> <td></td> </tr>');

            $('#infobook').modal('toggle');
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};
