$(document).ready(function () {
    booksum();

});

function booksum(){

    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prenotazione</th> ' +
        '<th scope="col">Data Prenotazione</th>' +
        '<th scope="col">Fascia Oraria</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col"></th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>'
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
                var dp = val.dataPrenotazione;
                var pos = val.idPostazione.split(",").join("-");
                var timeslot;
                var state;
                var month;

                if(dp.monthValue < 10){
                    month = "0" + dp.monthValue;
                } else month = dp.monthValue;
                var date = dp.dayOfMonth + "/" + month + "/" + dp.year;

                if(val.oraIngresso === null){
                    state = "Pagato";
                } else if(val.oraUscita === null){
                    state = "Entrato";
                } else state = "Uscito";

                if(val.fasciaOraria === 1){
                    timeslot = "Mattina";
                } else if(val.fasciaOraria === 2){
                    timeslot = "Pomeriggio";
                } else timeslot = "Fullday";

                $('#tabella').append('<tr class="text-center"> <th scope="row">' + val.idPrenotazione + '</th> <td>' + date + '</td> <td>' + timeslot + '</td> <td>' + state + '</td> <td>' + pos + '</td> <td> <a href="#" class="prenotazione" id="' + val.idPrenotazione + '" onclick="infopren('+ val.idPrenotazione +')"><img class="img-responsive" src="\\Maitai\\assets\\img\\lente.png"></a> </td> <td></td>  </tr>');
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
        '<th scope="col">Data Prenotazione</th>' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col">Costo</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>'
        '</table>';

    $.ajax({
        url: './ownbooks',
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
            if(dp.monthValue < 10){
                month = "0" + dp.monthValue;
            } else month = dp.monthValue;

            var date = dp.dayOfMonth + "/" + month + "/" + dp.year;

            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Prenotazione #' + pren.idPrenotazione + '</h4> ' +
                '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="delpren('+ pren.idPrenotazione +')">Elimina Prenotazione</button> '+
                '<button type="button" class="btn btn-danger" data-dismiss="modal">Annulla</button>' +
                '</div> </div> </div>';

            $('#infobox').html(mhead);
            $('#modalinfo').html(intestazione + '<tr class="text-center"> <th scope="row"> '+ pren.idPrenotazione +' </th> <td>'+ date +'</td> <td>'+ pos +'</td> <td>'+ pren.price +'€</td> <td></td> </tr>');

            $('#infobox').modal('toggle');
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function delpren(id) {
    $.ajax({
        url: './ownbooks',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'delPren',
            'idPren': id
        },
        success: function (data) {
            $('#infobox').modal('dispose');
            var message = data.message;

            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Esito Annullamento</h4> ' +
                '<button type="button" class="close" data-dismiss="modal text-center">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center">'+ message+'. Ora verrai reinderizzato.</div> ' +
                '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button> '+
                '</div> </div> </div>';

            $('#delconf').html(mhead);

            $('#delconf').modal('toggle');

            setTimeout(function() {
                location.reload();
            }, 5000);

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

}