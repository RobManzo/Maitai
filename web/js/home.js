
$(document).ready(function () {



});

function access(){

    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'setEntry',
        },
        success: function (data) {
            if(data.status == 'Ok'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\off.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Uscita</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Esci dalla struttura.</p>');
            } else if(data.status == 'Error'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\on.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Accesso</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Accedi alla struttura.</p>');
            }

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function checkEntry() {

    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'getEntry',
        },
        success: function (data) {
            if(data.status == 'Ok'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\on.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Accesso</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Accedi alla struttura.</p>');
            } else if(data.status == 'Error'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\off.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Uscita</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Esci dalla struttura.</p>');
            }
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });



}



