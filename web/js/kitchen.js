$(document).ready(function () {
    ordersum();

});

function ordersum(){
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Ordine</th> ' +
        '<th scope="col">Ora</th> ' +
        '<th scope="col">Stato</th> ' +
        '<th scope="col">Importo</th> ' +
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
                let ts = val.ora;
                let hour;
                let minute;
                let second;
                if(ts.hour < 10) hour = '0'+ts.hour; else hour = ts.hour;
                if(ts.minute<10) minute = '0'+ts.minute; else minute = ts.minute;
                if(ts.second<10) second = '0'+ts.minute; else second = ts.second;
                let time = hour+':'+minute+':'+second;
                $('#tabella').append('<tr class="text-center"> <th scope="row">' + val.id + '</th> <td>' + time + '</td> <td>' + val.stato.toUpperCase() + '</td> <td>' + val.importo + '0 €</td> <td> <a href="#" class="prenotazione" id="' + val.id + '" onclick="infoOrder('+ val.id +')"><img class="img-responsive" src="\\Maitai\\assets\\img\\lente.png"></a> </td> <td></td>  </tr>');
            });
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
};

function infoOrder(id) {

    $.ajax({
        url: './kitchen',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype': 'getOrderDet',
            'ID': id
        },
        success: function (data) {
            let order = data.Ordine;
            let ts = order.ora;
            let hour;
            let minute;
            let second;
            if(ts.hour < 10) hour = '0'+ts.hour; else hour = ts.hour;
            if(ts.minute<10) minute = '0'+ts.minute; else minute = ts.minute;
            if(ts.second<10) second = '0'+ts.minute; else second = ts.second;
            let time = hour+':'+minute+':'+second;
            console.log(order);

            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Ordine #' + order.id + ' Ore ' + time +'</h4> ' +
                '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center" id="modalinfo"> </div> ' +
                '<button type="button" class="btn btn-danger" data-dismiss="modal">Chiudi</button>' +
                '<div class="modal-footer"> <button type="button" class="btn btn-success" data-dismiss="modal" onclick="orderReady('+ order.id +')">Ordine Pronto</button> '+
                '</div> </div> </div>';

            var intestazione = '<table class=\" table table-striped text-center\">' +
                ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
                '<th scope="col">ID Prodotto</th> ' +
                '<th scope="col">Quantità</th>' +
                '<th scope="col">Importo €</th> ' +
                '<th scope="col"></th> ' +
                '</tr> ' +
                '</thead>' +
                '<tbody id="tabinfo">' +
                '</tbody>'
            '</table>';

            $('#infobox').html(mhead);
            $('#modalinfo').append(intestazione);

            var prodotti = order.prodotti;
            console.log(prodotti);

            for(let prod in prodotti){
                console.log(prod);
                //$('#tabinfo').append('<tr class="text-center"> <th scope="row"> '+ key  +' </th>'+ qnt + '<td>'+ imp +'€</td> <td></td> </tr>');
            }

            $('#infobox').modal('toggle');
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function orderReady(id) {



}