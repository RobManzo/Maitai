var prodotti=[];
var cart= new Map();

$(document).ready(function () {
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
            console.log(prodotti);
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

};

function addToCart(id){
    if(cart.has(id)){
        let n = cart.get(id) + 1;
        cart.set(id, n)
    } else cart.set(id, 1);
};

function cartshow() {
    cart.forEach(function (x,y) {
        prodotti[0].forEach(function (p) {
            if(p.idProdotto === y){
                console.log(p);
            }
        })
    });
};