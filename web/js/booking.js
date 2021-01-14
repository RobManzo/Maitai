//Script per il caricamento e la gestione della mappa interattiva

var selected = [];                                                      //Posti selezionati
var tot = 0.00;
var thisday;
var timeslot;

$(document).ready(function () {
    loadDate();

    var s = Snap('#postazioni');

    Snap.load("/Maitai/assets/img/svg/lido.svg", function(f){           //Caricamento SVG postazioni

        seatState();
        console.log(selected);

        f.selectAll('[id^="r-"]').forEach(function(el){

            $('\#'+(el.parent()).node.id).data('status', 'D');

            el.click(function(ev){                                      //Ad ogni click viene verificato lo stato dei posti
                var elem = el.parent();
                var id = elem.node.id;

                if($('\#'+ id).data('status')=='O'){
                    //Occupied
                }
                else if($('\#'+ id).data('status')=='S')                //Posto già selezionato
                {
                    $('\#'+ id).find('path').removeClass().addClass('st0');
                    $('\#'+ id).data('status', 'D');
                    var index = selected.indexOf(id);
                    selected.splice(index, 1);
                    insertRow(selected);
                }
                else
                {
                    if(selected.length >= 5){
                        alert('Puoi prenotare fino ad un massimo di 5 postazioni!');
                    }else {                                                             //Posto libero
                        $('\#' + id).find('path').addClass('st0-selected');
                        $('\#'+ id).data('status', 'S');
                        selected.push(id);
                        insertRow(selected);
                    }
                }

            });
        });

        s.append(f);
    });

});

/**
 * Funzione per l'aggiornamento della tabella con i posti selezionati
 * @param sel
 */
function insertRow(sel) {
    var intestazione = '<table id="tabella" class=\" table table-striped\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID#</th> ' +
        '<th scope="col">Fascia oraria</th>' +
        '<th scope="col">Prezzo (+IVA)</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '</table>';

    $('#details').html(intestazione);
    tot = 0.00;

    sel.forEach(function (id) {
        $('#tabella').append('<tr id=' + '\'#riga\#' + id + '\'> <th scope="row">' + id + '</th> <td>' + getTimeslot(timeslot).toString() + '</td> <td>' + setPrice(id).toFixed(2) + '€</td> <td> </td>  </tr>');
        tot += setPrice(id);
    });
    $('#details').append('<div style="text-align: right; margin-right: 4rem;" id="totale"><b> TOTALE ' + tot.toFixed(2) + '€</b></div>');
    $('#details').append('<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#payment"> Conferma e paga </button>');
};


/**
 * Funzione per il caricamento dello stato dei posti
 */
function seatState(){
    thisday = $('#selectday').val();
    timeslot = selectTimeslot();
    selected = [];
    tot = 0.00;

    var intestazione = '<table id="tabella" class=\" table table-striped\">' +
        ' <thead> <tr style="background-color: #844c04; color: wheat;"> ' +
        '<th scope="col">ID#</th> ' +
        '<th scope="col">Fascia oraria</th>' +
        '<th scope="col">Prezzo (+IVA)</th> ' +
        '<th scope="col"></th> ' +
        '</tr> ' +
        '</thead>' +
        '</table>';

    $('#details').html(intestazione);
    $('#details').append('<div style="text-align: right; margin-right: 4rem;" id="totale"><b> TOTALE ' + tot.toFixed(2) + '€</b></div>');
    $('#details').append('<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#payment"> Conferma e paga </button>');
    console.log(thisday);
    console.log(timeslot);

    Snap.selectAll('[id^="r-"]').forEach(function(el){
        var elem = el.parent();
        var id = elem.node.id;
        $('\#'+ id).data('status', 'D');
        $('\#'+ id).find('path').removeClass();
        $('\#'+ id).find('path').addClass('st0');
    });

        $.ajax({
            url: './booking',
            dataType: 'json',
            type: 'post',
            data: {
                'DataPrenotazione': thisday,
                'FasciaOraria': timeslot
            },
            success: function (data) {
                $.each(data.Id, function(key, val){
                    $('#g-0'+val).data('status', 'O');
                    $('#p-0'+val).removeClass().addClass('st0-occupied');
                });
            },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
        });

};

/**
 * Funzione per la determinazione della fascia oraria selezionata
 * @returns {number}
 */
function selectTimeslot() {
    if($('#fullday').hasClass("active")){
        return 1;
    } else if($('#mattina').hasClass("active")) return 2;
    else if($('#pomeriggio').hasClass("active")) return 3;
};

function getTimeslot(ts){
    if(ts == 1){
        return 'Fullday';
    } else if(ts == 2){
        return 'Mattina';
    } else if(ts == 3){
        return 'Pomeriggio';
    }
};


/**
 * Funzione per l'inserimento dei giorni all'interno della select box
 */
function loadDate() {
    var timestamp = new Date();
    var dd = timestamp.getDate();
    var mm = timestamp.getMonth()+1;
    var yyyy = timestamp.getFullYear();

    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }

    var today = dd+'/'+mm+'/'+yyyy;
    $('#selectday').append('<option selected>'+today+'</option>');      //Eren trova il gay

    for(i=1; i<=6; i++){
        timestamp.setDate(new Date().getDate()+i);
        var dd = timestamp.getDate();
        var mm = timestamp.getMonth()+1;
        var yyyy = timestamp.getFullYear();
        if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }
        var curr = dd+'/'+mm+'/'+yyyy;
        var succ = '<option>'+curr+'</option>';
        $('#selectday').append(succ);
    }
};


/**
 * Funzione per il settaggio dei prezzi per postazione
 * @param id
 * @returns {number}
 */
function setPrice(id) {
    var subid = parseInt(id.substring(2,4));
    if(subid >= 1 && subid <=8){
        return 8.00;
    }else if (subid >= 9 && subid <=12){
        return 10.00;
    } else return 12.00;
};

function pagamento() {

//PARSING E AJAX POST

};
