$(document).ready(function () {
    ordersum();

});

function ordersum(){
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Ordine</th> ' +
        '<th scope="col">ora</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">importo</th> ' +
        '<th scope="col"></th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>'
        '</table>';

    $('#ordertab').html(intestazione);

    $.ajax({
        url: './kitchen',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getOrders'
        },
        success: function (data) {
            $.each(data.Ordini, function(key, val){
                $('#tabella').append('<tr class="text-center"> <th scope="row">' + val.idOrdine + '</th> <td>' + val.orario + '</td> <td>' + val.statoOrdine + '</td> <td>' + val.importo + '</td> <td> <a href="#" class="prenotazione" id="' + val.idOrdine + '" onclick="infoOrder('+ val.idOrdine +')"><img class="img-responsive" src="\\Maitai\\assets\\img\\lente.png"></a> </td> <td></td>  </tr>');
            });
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function infoOrder(id) {
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prodotto</th> ' +
        '<th scope="col">Ora Ordine</th>' +
        '<th scope="col">Postazioni</th> ' +
        '<th scope="col">Importo</th> ' +
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
            'rtype': 'getOrderDet',
            'idOrder': id
        },
        success: function (data) {
            var ordine = data.Ordine;
            var time = ordine.ordertime;

            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Ordine #' + id + ' Ore ' + time +'</h4> ' +
                '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                '<button type="button" class="btn btn-danger" data-dismiss="modal">Chiudi</button>' +
                '<div class="modal-footer"> <button type="button" class="btn btn-success" data-dismiss="modal" onclick="orderReady('+ id +')">Ordine Pronto</button> '+
                '</div> </div> </div>';

            var intestazione = '<table class=\" table table-striped text-center\">' +
                ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
                '<th scope="col">ID Prodotto</th> ' +
                '<th scope="col">Quantità</th>' +
                '<th scope="col">Importo €</th> ' +
                '<th scope="col"></th> ' +
                '</tr> ' +
                '</thead>' +
                '<tbody id="tabella">' +
                '</tbody>'
            '</table>';

            $('#infobox').html(mhead);
            $('#modalinfo').append(intestazione);

            $.each(ordine.prodotti, function(key, val){
                var idprod = key;
                var qntimp = val;
                $('#tabella').append('<tr class="text-center"> <th scope="row"> '+ idprod +' </th> <td>'+ qntimp +''+ qntimp +'€</td> <td></td> </tr>');
            });

            $('#infobox').modal('toggle');
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function orderReady(id) {



}