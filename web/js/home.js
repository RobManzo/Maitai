$(document).ready(function () {
    checkEntry();

});

function accesso(){

    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'setEntry'
        },
        success: function (data) {
            if(data.status == 'ok'){
                var message = data.message;
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\off.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Uscita</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Esci dalla struttura.</p>');

                $('#access').html('<div class="modal-dialog modal-dialog-centered modal-lg"> ' +
                    '<div class="modal-content"  style="background-color: antiquewhite;"> ' +
                    '<div class="modal-header"> ' +
                    '<h4 class="modal-title">Uscita dalla struttura</h4> ' +
                    '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                    '</div> <div class="modal-body text-center"> <p class="text-center">Scannerizza per uscire dalla struttura.</p> ' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\qrcode.png"> ' +
                    '</div> <div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="uscita()">Uscita</button> ' +
                    '</div> </div> </div>');

                $('#message').html('<p class="text-center">' + message + '</p>\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\allowed.png">');
                $('#accessinfo').modal('toggle');
                location.reload();

            } else if(data.status == 'error'){
                var message = data.message;

                $('#message').html('<p class="text-center">' + message + '</p>\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\denied.png">');
                $('#accessinfo').modal('toggle');
            }

        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });

};

function uscita() {

    $.ajax({
        url: './home',
        dataType: 'json',
        type: 'post',
        data: {
            'rtype' : 'setExit',
        },
        success: function (data) {
            if(data.status == 'ok'){
                var message = data.message;

                $('#entry').html('<a href="" data-toggle="modal" data-target="#access" id="accesso">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\on.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Accesso</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Accedi alla struttura.</p>');

                $('#access').html('<div class="modal-dialog modal-dialog-centered modal-lg"> ' +
                    '<div class="modal-content"  style="background-color: antiquewhite;"> ' +
                    '<div class="modal-header"> ' +
                    '<h4 class="modal-title">Accesso alla struttura</h4> ' +
                    '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                    '</div> <div class="modal-body text-center"> <p class="text-center">Scannerizza per accedere alla struttura.</p> ' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\qrcode.png"> ' +
                    '</div> <div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="accesso()">Accedi</button> ' +
                    '</div> </div> </div>');

                $('#message').html('<p class="text-center">' + message + '</p>\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\allowed.png">');
                $('#accessinfo').modal('toggle');
                location.reload();

            } else if(data.status == 'error'){
                var message = data.message;

                $('#message').html('<p class="text-center">' + message + '</p>\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\denied.png">');
                $('#accessinfo').modal('toggle');
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
            if(data.status === 'out'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\on.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Accesso</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Accedi alla struttura.</p>');

                $('#access').html('<div class="modal-dialog modal-dialog-centered modal-lg"> ' +
                    '<div class="modal-content"  style="background-color: antiquewhite;"> ' +
                    '<div class="modal-header"> ' +
                    '<h4 class="modal-title">Accesso alla struttura</h4> ' +
                    '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                    '</div> <div class="modal-body text-center"> <p class="text-center">Scannerizza per accedere alla struttura.</p> ' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\qrcode.png"> ' +
                    '</div> <div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="accesso()">Accedi</button> ' +
                    '</div> </div> </div>');
                $('#foodlink').addClass("noclickable");
                $('#foodlink').attr("href", "#");
                $('#food').attr('title', 'Devi entrare nella struttura per ordinare!');

            } else if(data.status === 'in'){
                $('#entry').html('<a href="" data-toggle="modal" data-target="#access">\n' + '<div class="limit">\n' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\off.png">\n' +
                    '</div>\n' +
                    '<h4 class="my-3" style="color: black;">Uscita</h4>\n' +
                    '</a>\n' +
                    '<p class="text-muted">Esci dalla struttura.</p>');

                $('#access').html('<div class="modal-dialog modal-dialog-centered modal-lg"> ' +
                    '<div class="modal-content"  style="background-color: antiquewhite;"> ' +
                    '<div class="modal-header"> ' +
                    '<h4 class="modal-title">Uscita dalla struttura</h4> ' +
                    '<button type="button" class="close" data-dismiss="modal">&times;</button> ' +
                    '</div> <div class="modal-body text-center"> <p class="text-center">Scannerizza per uscire dalla struttura.</p> ' +
                    '<img class="img-responsive" src="\\Maitai\\assets\\img\\qrcode.png"> ' +
                    '</div> <div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="uscita()">Uscita</button> ' +
                    '</div> </div> </div>');
            }
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });



};