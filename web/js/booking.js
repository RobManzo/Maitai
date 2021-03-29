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
                'FasciaOraria': timeslot,
                'rtype': 'getSeats'
            },
            success: function (data) {
                var id = data.Id;
                id.forEach(function (x) {
                    if(x<10){
                        $('#g-0'+x).data('status', 'O');
                        $('#p-0'+x).removeClass().addClass('st0-occupied');

                    } else {
                        $('#g-'+x).data('status', 'O');
                        $('#p-'+x).removeClass().addClass('st0-occupied');
                    }
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
    timestamp.setDate(timestamp.getDate() + 1);
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
    $('#selectday').append('<option selected>'+today+'</option>');

    var ts = new Date();
    ts.setDate(ts.getDate() + 2);

    for(i=0; i<=5; i++){
        var dd = ts.getDate()+i;
        var mm = ts.getMonth()+1;
        var yyyy = ts.getFullYear();
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
    if(selected.length === 0){
        $('#payment').modal('toggle');
        alert('Nessuna postazione selezionata!');
        return;
    }
    var sel=[];
    selected.forEach(function (x) {
        x = x.substring(2);
        if(x.charAt(0) === '0'){
            x = x.substring(1);
        }
        sel.push(x);
        });

    var postazioni = sel.join(",");

    $.ajax({
        url: './booking',
        dataType: 'json',
        type: 'post',
        data: {
            'Postazioni': postazioni,
            'DataPrenotazione': thisday,
            'FasciaOraria': timeslot,
            'Prezzo': tot,
            'rtype': 'setBook'
        },
        success: function (data) {
            var message = data.message;

            var mhead = '<div class="modal-dialog modal-dialog-centered modal-lg">' +
                ' <div class="modal-content"  style="background-color: antiquewhite;"> ' +
                '<div class="modal-header">  <h4 class="modal-title">Esito Prenotazione</h4> ' +
                '<button type="button" class="close" data-dismiss="modal text-center">&times;</button> ' +
                '</div> ' +
                '<div class="modal-body text-center">'+ message+'. Ora verrai reinderizzato.</div> ' +
                '<div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button> '+
                '</div> </div> </div>';

            $('#payment').modal('dispose');
            $('#confirmpayment').modal('toggle');

            setTimeout(function() {
                location.reload();
            }, 5000);

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};
