<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>AVPC Sant Vicenç de Castellet</title>

    <!-- Bootstrap core CSS -->
    <c:url value="/styles/bootstrap.min.css" var="bootst" />
    <link rel="stylesheet" type="text/css" href="${bootst}" />

    <!-- Custom styles for this template -->
    <c:url value="/styles/jumbotron.css" var="jstlCss" />
    	<link href="${jstlCss}" rel="stylesheet" />

      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/user/inici">AVPC Sant Vicenç de Castellet</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <li class="navbar-right"><a href="/user/inici" class="btn btn-success">Administració</a></li>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->



    <div class="jumbotron text-center">
      <div class="container">
        <h1>Protecció Civil</h1>
        <h1>Sant Vicenç de Castellet</h1>
        <p>Associació de voluntaris de protecció civil de Sant Vicenç de Castellet</p>
        <p><img src="/images/icons/escut.png" class="center-block" height="20%" width="20%"></p>
      </div>
    </div>


    <div class="container-fluid" >
      <!-- Example row of columns -->

      <div class="row" >
        <div class="col-md-4">
          <h2>Qui som?</h2>
          <p>L'AVPC Sant Vicenç de Castellet és una entitat sense ànim de lucre que té per a objectius dotar el poble de Sant Vicenç de Castellet d'un servei municipal d'emergències.
            La nostra associació està reconeguda per l'Ajuntament de Sant Vicenç i, tots els seus membres tenen el nomenament oficial de voluntaris de protecció civil expedit pel mateix ajuntament. </p>
        </div>
        <div class="col-md-4">
          <h2>Què fem?</h2><p>Tasques preventives com són la inspecció i manteniment de camins, control d'hidrants...
          Atendre les urgències que es produeixen dins del terme municipal, col·laborant amb els serveis ordinaris d'emergències en accidents de tràfic, talls de carretera, caiguda d'arbres, esllavissaments de terra, incendis, inundacions, col·laboració amb els serveis sanitaris. </p>
        </div>
        <div class="col-md-4">
          <h2>Vols Col·laborar?</h2>
          <p>Si vols formar part de la nostra associació, ja sigui com a soci o voluntari, deixa'ns les teves dades. Si vols donar-nos la teva opinió o suggeriment, no dubtis en fer-nos arribar un missatge a la següent adreça de correu electrònic: protecciocivil.svc@gmail.com</p>
        </div>
      </div>

    </div>

    <hr class="featurette-divider">

      <div class="container" >
        <div class="row" style ='background-color: #d3d3d3;'>
          <div class="col-md-12">
            <h1>INFORMACIÓ PROTECCIÓ CIVIL</h1>
          </div>
        </div>
      <div class="row" style ='background-color: #d3d3d3;'>
        <div class="col-md-4">
          <h2>Avisos SMP</h2>
          <p> <iframe id="smcsmp" width="380" height="450" style="border: 0;"></iframe></p>
          <p><a class="btn btn-default" href="http://www.meteo.cat" role="button">Veure detalls &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Meteo</h2>
          <p> <iframe id="smcpredgen3d" width="380" height="450" style="border: 0;"></iframe></p>
          <p><a class="btn btn-default" href="http://www.meteo.cat/prediccio/general" role="button">Veure detalls &raquo;</a></p>
        </div>

        <div class="col-md-4">
          <h2>Radar</h2>
          <p> <iframe id="smcxrad" width="380" height="450" style="border: 0;"></iframe></p>
          <p><a class="btn btn-default" href="http://www.meteo.cat/observacions/radar" role="button">Veure detalls &raquo;</a></p>
        </div>
      </div>

      <div class="row" style ='background-color: #d3d3d3;'>
        <div class="col-md-4">
          <h2>Pla Alfa</h2>
          <p> <img src="http://www.gencat.cat/medinatural/incendis/mapes/pla_alfa.gif" alt="Mapa del Pla Alfa (dia en curs)" style="display: inline-block" class="img-responsive"></p>
          <p><a class="btn btn-default" href="http://www.gencat.cat/medinatural/incendis/mapes/pla_alfa.gif" role="button">Veure detalls &raquo;</a></p>
        </div>
        <div class="col-md-8">
          <h2>Twitter @avpcsvc</h2>
          <p>               <a class="twitter-timeline"
                               href="https://twitter.com/avpcsvc"
                               data-width="1100"
                               data-height="500">
                               Tweets by @avpcsvc
                            </a>
          </p>
          <p><a class="btn btn-default" href="https://twitter.com/avpcsvc" role="button">Veure detalls &raquo;</a></p>
        </div>
      </div>
      </div>

      <hr>

      <footer>
        <p>&copy; 2016 Associació de voluntaris de protecció civil de Sant Vicenç de Castellet.</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    <script>
        document.getElementById('smcpredgen3d').setAttribute('src', 'https://static-m.meteo.cat/ginys/prediccio3d/v1/?rnd=' + Math.random());
        document.getElementById('smcxrad').setAttribute('src', 'https://static-m.meteo.cat/ginys/mapaRadar/v1/?rnd=' + Math.random());
        document.getElementById('smcsmp').setAttribute('src', 'https://static-m.meteo.cat/ginys/mapaAvisos/v1/?rnd=' + Math.random());
        document.getElementById('smcpredgen').setAttribute('src', 'https://static-m.meteo.cat/ginys/mapaPrediccio/v1/?rnd=' + Math.random());
    </script>
    <script src="https://platform.twitter.com/widgets.js" async></script>
  </body>
</html>
