//Script per il caricamento e la gestione della mappa interattiva

var posSelected = [];
var posOccupied = [];


$(document).ready(function () {
    var s = Snap('#postazioni');

    Snap.load("/Maitai/assets/img/svg/lido.svg", function(f){

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


});
function selection() {

}

function load(){

    var element;

    for (let i=10; i<60; i++){
        element=Snap.select('#pos_'+i);
        element.data('status', 'F');
    }
}

/*function setSelected(id, elem){
    $(id+' ellipse.st1').removeClass("st1").removeClass("st1-occupied").removeClass("st1-selected").addClass("st1-selected");
    $(id+' rect.st2').removeClass("st2 st2-occupied st2-selected").addClass("st2-selected");
    $(id+' path.st3').removeClass("st3 st3-occupied st3-selected").addClass("st3-selected");
    elem.data('status', 'S');

}
function setOccupied(id, elem){
    $(id+' ellipse.st1').removeClass("st1 st1-occupied st1-selected").addClass("st1-occupied");
    $(id+' rect.st2').removeClass("st2 st2-occupied st2-selected").addClass("st2-occupied");
    $(id+' path.st3').removeClass("st3 st3-occupied st3-selected").addClass("st3-occupied");
    elem.data('status', 'O');
}
function setUnselected(id, elem){
    $(id+' ellipse.st1-selected').removeClass("st1-selected").addClass("st1");
    $(id+' rect.st2-selected').removeClass("st2-selected").addClass("st2");
    $(id+' path.st3-selected').removeClass("st3-selected").addClass("st3");
    elem.data('status', 'F');
}*/