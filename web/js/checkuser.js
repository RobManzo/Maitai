$(document).ready(function () {
    takeusers();
});

/**
 * Funzione per il caricamento della lista degli utenti su tabella
 */
function takeusers(){
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Utente</th> ' +
        '<th scope="col">Nome</th> ' +
        '<th scope="col">Cognome</th> ' +
        '<th scope="col">Codice Fiscale</th> ' +
        '<th scope="col">Ruolo</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>' +
        '</table>';

    $('#users').html(intestazione);

    $.ajax({
        url: './checkuser',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getUsers'
        },
        success: function (data) {
            var users = data.Utenti;
            $.each(users, function(key, val){
                console.log(val);
               $('#tabella').append('<tr class="text-center"> <th scope="row"> '+ val.idUtente +'</th><td>'+ val.nome +'</td><td>'+ val.cognome +'</td> <td>'+ val.codFisc +'</td> <td>'+ val.ruolo +'</td> <td> <a href="#" class="prenotazione" id="' + val.idUtente + '" onclick="infouser('+ val.idUtente +')"><img class="img-responsive" src="\\Maitai\\assets\\img\\lente.png" alt="INFO"></a> </td> </tr>');
            });
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
};

/**
 * Funzione per ottenere i dettagli riguardante un utente
 * @param id
 */
function infouser(id) {
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Utente</th> ' +
        '<th scope="col">Nome</th> ' +
        '<th scope="col">Cognome</th>' +
        '<th scope="col">Codice Fiscale</th> ' +
        '<th scope="col">Telefono</th> ' +
        '<th scope="col">Data di Nascita</th> ' +
        '<th scope="col">Domicilio</th> ' +
        '<th scope="col"> </th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabinfo">' +
        '</tbody>' +
        '</table>';

    $.ajax({
        url: './checkuser',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getUserDet',
            'ID': id
        },
        success: function (data) {
            var user = data.Utente;
            var date = user.dataNasc.split("-").join("/");
            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite; overflow-x: auto;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Utente #' + user.idUtente + '</h4> ' +
                '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="removeUser('+ user.idUtente +')">Cancella Utente</button> '+
                '<button type="button" class="btn btn-danger" data-dismiss="modal">Chiudi</button>' +
                '</div> </div> </div>';

            $('#infouser').html(mhead);
            $('#modalinfo').html(intestazione);
            $('#tabinfo').html('<tr class="text-center"> <th scope="row"> '+ user.idUtente +' </th> <td>'+ user.nome +'</td> <td>'+ user.cognome +'</td> <td>'+ user.codFisc +'</td><td>'+ user.telefono +'</td> <td>'+ date +'</td> <td> '+ user.indirizzo +'</td> <td> </td> </tr>');
            $('#infouser').modal('toggle');
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

/**
 * Funzione per la rimozione di un utente dal DB
 * @param id
 */
function removeUser(id) {
    $.ajax({
        url: './checkuser',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'removeUser',
            'ID': id
        },
        success: function (data) {
            alert(data.Message +" Ora verrai reindirizzato");

            setTimeout(function() {
                location.reload();
            }, 500);

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

}