var prodotti=[];
var cart = new Map();

$(document).ready(function () {
    cart= new Map();
    $("#addtocart").hide();
    loadprod();
});

function loadprod() {
    $.ajax({
        url: './food',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'getProd'
        },
        success: function (data) {
            prodotti.push(data.Prodotto);
            console.log(prodotti[0]);
            $.each(data.Prodotto, function(key, val){
                var nome = val.nome;
                var ingredienti = val.ingredienti;
                var importo = parseFloat(val.importo).toFixed(2);
                var id = val.idProdotto;
                var url = val.imgurl;
                $('#prodotti').append('<div class="col-md-3" style="padding:15px;"> ' +
                    '<div style="display:inline-block; padding:15px"> ' +
                    '<div> ' +
                    '<img class="img card-img" alt="eCommerce Product List" src="'+ url +'" /> ' +
                    '<h3 class="pt-2">'+ nome +'</h3> ' +
                    '<h3 class="float-xs-right">'+ importo +' €</h3> ' +
                    '<small>'+ ingredienti +'</small> ' +
                    '<br /> ' +
                    '</div> ' +
                    '<div class="btn-ground text-xs-center" style="padding-bottom: 30px"> ' +
                    '<button type="button" class="btn btn-primary" onclick="addToCart('+ id +')"><i class="fa fa-shopping-cart"></i> Aggiungi al carrello</button> ' +
                    '</div> ' +
                    '</div> </div>');
            });
        },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
    });

    $.ajax({
        url: './food',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'getCart'
        },
        success: function (data) {
            var tempcart = data.Carrello;
            for (const [key, value] of Object.entries(tempcart)) {
                cart.set(key, value);
            }
            console.log(cart);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function addToCart(id){
    let idstring = String(id);
    if(cart.has(idstring)){
        let n = cart.get(idstring) + 1;
        cart.set(idstring, n)
    } else cart.set(idstring, 1);

    var parsedcart=JSON.stringify(mapToObj(cart));

    console.log(cart);

    $.ajax({
        url: './food',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'upCart',
            'localcart' : parsedcart
        },
        success: function (data) {
            let mess = data.Message;
            $("#addtocart").html(mess);
            $("#addtocart").fadeTo(2000, 500).slideUp(500, function(){
                $("#addtocart").slideUp(500);
            });

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function removeFromCart(id) {


};


function cartshow() {
    var intestazione = '<table class=\" table table-striped text-center\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID Prodotto</th> ' +
        '<th scope="col">Nome Prodotto</th>' +
        '<th scope="col">Quantità</th> ' +
        '<th scope="col">Costo</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '<tbody id="tabella">' +
        '</tbody>'
    '</table>';

    $('#modalinfo').html(intestazione);

    cart.forEach(function (x,y) {
        prodotti[0].forEach(function (p) {
            if(String(p.idProdotto) === y){
                $('#tabella').append('<tr class="text-center"> <th scope="row"> '+ p.idProdotto +' </th> <td>'+ p.nome +'</td> <td>'+ x +'</td> <td>'+ parseFloat(p.importo).toFixed(2) +'€</td> <td></td> </tr>');
            }
        })
    });

    $('#cartmodal').modal('toggle');

};

function mapToObj(mp){
    let obj = Object.create(null);
    mp.forEach(function (x,y) {
        obj[y] = x;
    })
    return obj;
}