//Script per il caricamento e la gestione della mappa interattiva

$(document).ready(function () {
    loadDate();

    var s = Snap('#postazioni');
    var selected = [];

    Snap.load("/Maitai/assets/img/svg/lido.svg", function(f){

        seatState();

        f.selectAll('[id^="r-"]').forEach(function(el){

            el.parent().data('status', 'D');

            el.click(function(ev){
                var elem = el.parent();
                var id = '#'+ elem.node.id;

                if( elem.data('status')=='O' ){
                    //Occupied
                }
                else if( elem.data('status')=='S' )
                {
                    // The seat is already selected, the user wants to deselect it
                    $(id+' path.st0-selected').removeClass("st0-selected");
                    elem.data('status', 'D');
                    var index = selected.indexOf(id);
                    selected.splice(index);
                    $('#riga' + id).remove();
                }
                else
                {
                    // the seat is free, the user wants to select it
                    $(id+' path.st0').addClass("st0-selected");
                    elem.data('status', 'S');
                    selected.push(id);
                    $('#selezionati').append('<tbody> <tr id=' + '\'riga' + id + '\'> <th scope="row">' + id + '</th> <td>' + $('#timeslot').val() + '</td> <td>' + /*PREZZO POSTAZIONE*/ + '</td> <td> </td>  </tr> </tbody>');
                }

            });
        });

        s.append(f);
    });

});


function seatState(){                                   //Prima volta che si carica la pagina oppure ogni volta che si cambia giorno o fascia oraria
    var thisday = $('#selectday').val();
    var timeslot = selectTimeslot();
    selected = [];
    $('#selezionati').html('<tbody></tbody>');

    console.log(thisday);
    console.log(timeslot);

    Snap.selectAll('[id^="r-"]').forEach(function(el){
        var elem = el.parent();
        var id = '#'+ elem.node.id;
        elem.data('status', 'D');
        $(id+' path.st0-selected').removeClass("st0-selected");
        $(id+' path.st0-occupied').removeClass("st0-occupied");
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
                    var group = '#r-0'+val;
                    //var pos = '#p-0'+val;
                    $('#r-0'+val).data('status', 'O');
                    $('#p-0'+val).removeClass("st0").addClass("st0-occupied");
                });
                console.log(data);
            },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
        });

}

function selectTimeslot() {
    if($('#fullday').hasClass("active")){
        return 1;
    } else if($('#mattina').hasClass("active")) return 2;
    else if($('#pomeriggio').hasClass("active")) return 3;
}

/* Funzione per inserire i giorni nel select box*/
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
    $('#selectday').append('<option selected>'+today+'</option>');

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
}
