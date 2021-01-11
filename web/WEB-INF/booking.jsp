<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Maitai - Prenotazione</title>
    <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version) -->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap) -->
    <link href="/Maitai/css/styles.css" rel="stylesheet" />

</head>

<body>
    <!-- Navigation -->
    <div><%@ include file="/WEB-INF/navbar.jsp"%></div>

    <div class="page-section row" id="book">
        <div class="container col-md-4 align-items-center py-2">
            <div class="d-flex">
                <div class="input-group mb-3 col-md-6 align-self-center">
                    <div class="input-group-prepend" style="fill: #844c04">
                        <label class="input-group-text" for="selectday">Giorno</label>
                    </div>
                    <select class="custom-select" id="selectday" onchange="seatState()">
                    </select>
                </div>
                <div class="btn-group btn-group-toggle col-md-6 align-self-start pb-3" id="timeslot" data-toggle="buttons">
                    <label class="btn btn-secondary active" id="fullday"> Full Day
                        <input type="radio" name="options" checked onchange="seatState()"> H 8/18
                    </label>
                    <label class="btn btn-secondary" id="mattina"> Mattina
                        <input type="radio" name="options" onchange="seatState()"> H 8/13
                    </label>
                    <label class="btn btn-secondary" id="pomeriggio"> Pomeriggio
                        <input type="radio" name="options" onchange="seatState()"> H 13/18
                    </label>
                </div>
            </div>
            <div class="container-bg rounded shadow p-2" id="postazioni"></div>
        </div>

        <div class="container col-md-4 align-items-end pt-4 align-self-baseline" id="details" style="margin-top: 5rem;">
            <table class="table table-striped" id="tab">
                <thead>
                    <tr style="background-color: #844c04; color: wheat;">
                        <th scope="col">ID#</th>
                        <th scope="col">Fascia oraria</th>
                        <th scope="col">Prezzo (+IVA)</th>
                        <th scope="col"></th>
                    </tr>
                </thead>

                <div>
                    <tbody id="selezionati">
                    </tbody>
                </div>

            </table>
            <div class="" id="totale" style="text-align: right; margin-right: 4rem;"><b>TOT 0,00€</b></div>
        </div>

    </div>


    <!-- Footer-->
    <div class="footer"><%@ include file="/WEB-INF/footer.jsp"%></div>

    <!-- Bootstrap core JS-->
    <script src="/Maitai/jquery/jquery.min.js"></script>
    <script src="/Maitai/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="/Maitai/jquery-easing/jquery.easing.min.js"></script>
    <!-- Contact form JS-->
    <script src="/Maitai/assets/mail/jqBootstrapValidation.js"></script>
    <script src="/Maitai/assets/mail/contact_me.js"></script>
    <!-- Core theme JS-->
    <script src="/Maitai/js/scripts.js"></script>
    <script src="/Maitai/js/snap.svg.js"></script>
    <script src="/Maitai/js/booking.js"></script>

</body>

</html>