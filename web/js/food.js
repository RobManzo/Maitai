$(document).ready(function () {
    loadprod();
});

function loadprod() {
    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'getProd'
        },
        success: function (data) {
            $.each(data.Prodotto, function(key, val){
                var nome = val.nome;
                var ingredienti = val.ingredienti;
                var importo = val.importo;
                var id = val.idProdotto;
                $('#prodotti').append('<div class="col-md-3" style="padding:15px;"> ' +
                    '<div style="display:inline-block; padding:15px"> ' +
                    '<div> ' +
                    '<img class="img card-img" alt="eCommerce Product List" src="\Maitai\assets\img\book.png" /> ' +
                    '<h3 class="pt-2">'+ nome +'</h3> ' +
                    '<h3 class="float-xs-right">'+ importo +'</h3> ' +
                    '<small>'+ ingredienti +'</small> ' +
                    '<br /> ' +
                    '</div> ' +
                    '<div class="btn-ground text-xs-center" style="padding-bottom: 30px"> ' +
                    '<button type="button" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Aggiungi al carrello</button> ' +
                    '</div> ' +
                    '</div> </div>');
            });
        },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
    });

};