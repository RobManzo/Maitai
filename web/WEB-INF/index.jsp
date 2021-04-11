<%@ page contentType="text/html;charset=UTF-8" %>
<html>

  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Maitai - Index</title>
    <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version) -->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap) -->
    <link href="/Maitai/css/styles.css" rel="stylesheet" />
      <style>
          @media (min-width: 992px) {
              #mainNav {
                  background: transparent;
              }
          }
      </style>

  <body>
    <!-- Navigation -->
    <%@ include file="/WEB-INF/navbar.jsp"%>

    <!-- Masthead -->
    <header class="masthead">
      <div class="container">
        <div class="masthead-subheading">Lido MaiTai</div>
        <div class="masthead-heading text-uppercase">Benvenuto presso la nostra struttura</div>
          <% if(request.getSession().getAttribute("user") == null){ %>
          <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="${pageContext.request.contextPath}/login">Riserva il tuo posto</a>
          <% } else {%>
          <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="${pageContext.request.contextPath}/cliente/booking">Riserva il tuo posto</a>
          <% }%>

      </div>
    </header>

    <!-- Services -->
    <section class="page-section" id="services" style="background-color: antiquewhite;">
      <div class="container">
        <div class="text-center">
          <h2 class="section-heading text-uppercase">I Nostri Servizi</h2>
        </div>

        <div class="row text-center">
          <div class="col-md-4">
              <div class="limit">
                  <img class="img-responsive" src="\Maitai\assets\img\beach.png">
              </div>
              <h4 class="my-3">Goditi la riva</h4>
              <p class="text-muted">Una comodissima postazione dotata di lettino e ombrellone, garantendoti tutta la privacy.</p>
          </div>

          <div class="col-md-4">
              <div class="limit">
                  <img class="img-responsive" src="\Maitai\assets\img\drink.png">
              </div>
              <h4 class="my-3">Bar e Ristorazione</h4>
              <p class="text-muted">I nostri chef e i nostri baristi sono sempre a tua disposizione per qualsiasi tuo desiderio.</p>
          </div>

          <div class="col-md-4">
              <div class="limit">
                  <img class="img-responsive" src="\Maitai\assets\img\salvagente.png">
              </div>
              <h4 class="my-3">Il nostro Staff</h4>
              <p class="text-muted">Il nostro personale altamente qualificato ti assicurerà una permanenza indimenticabile.</p>
          </div>
        </div>
      </div>
    </section>

    <!-- Google Map -->
    <section class="page-section" id="findus" style="background-color: antiquewhite">
        <div class="container pt-2">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Dove Siamo</h2>
            </div>
            <div class="embed-responsive embed-responsive-16by9">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d932.200111320649!2d13.332567980815664!3d38.19747577451334!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1319e938b2a8432b%3A0xb6afdee4c47ab5d2!2sMondello%20beach!5e0!3m2!1sit!2sit!4v1606302842084!5m2!1sit!2sit" width="auto" height="auto" frameborder="0" style="border:0;" allowfullscreen="0" aria-hidden="false" tabindex="0"></iframe>
            </div>
        </div>
    </section>

    <!-- Footer-->
   <%@ include file="/WEB-INF/footer.jsp"%>

    <!-- Bootstrap core JS-->
    <script src="/Maitai/jquery/jquery.min.js"></script>
    <script src="/Maitai/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="/Maitai/jquery-easing/jquery.easing.min.js"></script>
    <!-- Core theme JS-->
    <script src="/Maitai/js/scripts.js"></script>

  </body>

</html>
