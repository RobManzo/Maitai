//Script per il caricamento e la gestione della mappa interattiva

var posSelected = [];
var posOccupied = [];


$(document).ready(function () {
    var s = Snap('#postazioni');


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
                    $(id+' path.st0-selected').removeClass("st0-selected").addClass("st0");
                    elem.data('status', 'D');
                }
                else
                {
                    // the seat is free, the user wants to select it
                    $(id+' path.st0').removeClass("st0").addClass("st0-selected");
                    elem.data('status', 'S');
                }

            });
        });

        s.append(f);
    });

    selectdate();

});

function selection() {

}

function seatState(){
    var thisday = $('#selectday').val();
    var timeslot = $('#selectday').val();


        $.ajax({
            url: './booking',
            dataType: 'json',
            type: 'post',
            data: {
                'DataPrenotazione': thisday,
                'FasciaOraria': 2
            },
            success: function (data) {
                $.each(data.Id, function(key, val){
                    var group = '#r-0'+val;
                    var pos = '#p-0'+val;
                    console.log(group);
                    $('#r-0'+val).data('status', 'O');
                    $('#p-0'+val).removeClass("st0").addClass("st0-occupied");                                                                //Selezionare i vari svg
                });
                console.log(data);
            },
            error: function (errorThrown) {
                console.log(errorThrown);
            }
        });

}

/* Funzione per inserire i giorni nel select box*/
function selectdate() {

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