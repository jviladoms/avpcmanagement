<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <title>Voluntaris | Voluntaris</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/images/icons/favicon.ico">
    <link rel="apple-touch-icon" href="/images/icons/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/images/icons/favicon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/images/icons/favicon-114x114.png">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700">
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="/styles/jquery-ui-1.10.4.custom.min.css">
    <link type="text/css" rel="stylesheet" href="/styles/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="/styles/all.css">
    <link type="text/css" rel="stylesheet" href="/styles/main.css">
    <link type="text/css" rel="stylesheet" href="/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="/styles/jquery.news-ticker.css">
    <link type="text/css" rel="stylesheet" href="/styles/jplist-custom.css">
    <link type="text/css" href="/styles/jquery.simple-dtpicker.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    <script src="/script/voluntari.js"></script>
</head>
<body>
    <div>
        <!--BEGIN THEME SETTING-->
        <div id="theme-setting">

            <div class="content-theme-setting">
                <select id="list-style" class="form-control">
                    <option value="style1">Flat Squared style</option>
                    <option value="style2">Flat Rounded style</option>
                    <option value="style3" selected="selected">Flat Border style</option>
                </select>
            </div>
        </div>
        <!--END THEME SETTING-->
        <!--BEGIN BACK TO TOP-->
        <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <!--BEGIN TOPBAR-->
        <div id="header-topbar-option-demo" class="page-header-topbar">
            <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
                <div class="navbar-header">
                    <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a id="logo" href="/" class="navbar-brand"><span class="fa fa-rocket"></span><span class="logo-text">AVPC SVC</span><span style="display: none" class="logo-text-icon">µ</span></a></div>
                <div class="topbar-main"><a id="menu-toggle" href="#" class="hidden-xs"><i class="fa fa-bars"></i></a>

                    <!--<form id="topbar-search" action="" method="" class="hidden-sm hidden-xs">
                        <div class="input-icon right text-white"><a href="#"><i class="fa fa-search"></i></a><input type="text" placeholder="Search here..." class="form-control text-white"/></div>
                    </form>-->
                    <div class="news-update-box hidden-xs"><span class="text-uppercase mrm pull-left text-white"></span>
                        <ul id="news-update" class="ticker list-unstyled">
                            <c:forEach items="${sessionScope.tweets}" var="tweet">
                                <li>${tweet.fromUser} - ${tweet.text}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    <ul class="nav navbar navbar-top-links navbar-right mbn">
                        <a class="twitter-share-button"
                           href="https://twitter.com/intent/tweet">
                            Tweet</a>
                        <a href="https://twitter.com/avpcsvc?ref_src=twsrc%5Etfw" class="twitter-follow-button" data-show-count="false">Follow @avpcsvc</a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
                        <!--<li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span class="badge badge-green">3</span></a>

                        </li>
                        <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-envelope fa-fw"></i><span class="badge badge-orange">7</span></a>

                        </li>
                        <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-tasks fa-fw"></i><span class="badge badge-yellow">8</span></a>

                        </li>-->
                        <li class="dropdown topbar-user"><a data-hover="dropdown" href="#" class="dropdown-toggle"><img src="/member/image/display?name=<%= request.getSession().getAttribute("userid") %>" alt="" class="img-responsive img-circle"/>&nbsp;<span class="hidden-xs"><%= request.getSession().getAttribute("username") %></span>&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu dropdown-user pull-right">
                                <li><a href="/user/member_password"><i class="fa fa-user"></i>Canviar Password</a></li>
                                <li><a href="/admin/member_update/<%= request.getSession().getAttribute("userid") %>"><i class="fa fa-calendar"></i>El meu perfil</a></li>
                                <li class="divider"></li>
                                <li><a href="/logout"><i class="fa fa-key"></i>Log Out</a></li>
                            </ul>
                        </li>
                        <!--<li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat"><i class="fa fa-comments"></i><span class="badge badge-info">3</span></a></li>-->
                    </ul>
                </div>
            </nav>
            <!--BEGIN MODAL CONFIG PORTLET-->
            <div id="modal-config" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">
                                &times;</button>
                            <h4 class="modal-title">
                                Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend et nisl eget
                                porta. Curabitur elementum sem molestie nisl varius, eget tempus odio molestie.
                                Nunc vehicula sem arcu, eu pulvinar neque cursus ac. Aliquam ultricies lobortis
                                magna et aliquam. Vestibulum egestas eu urna sed ultricies. Nullam pulvinar dolor
                                vitae quam dictum condimentum. Integer a sodales elit, eu pulvinar leo. Nunc nec
                                aliquam nisi, a mollis neque. Ut vel felis quis tellus hendrerit placerat. Vivamus
                                vel nisl non magna feugiat dignissim sed ut nibh. Nulla elementum, est a pretium
                                hendrerit, arcu risus luctus augue, mattis aliquet orci ligula eget massa. Sed ut
                                ultricies felis.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" class="btn btn-default">
                                Close</button>
                            <button type="button" class="btn btn-primary">
                                Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-content">
                    <ul class="list-inline item-details">
                        <li><a href="http://themifycloud.com">Admin templates</a></li>
                        <li><a href="http://themescloud.org">Bootstrap themes</a></li>
                    </ul>
                </div>
            </div>
            <!--END MODAL CONFIG PORTLET-->
        </div>
        <!--END TOPBAR-->
        <div id="wrapper">
            <!--BEGIN SIDEBAR MENU-->
            <nav id="sidebar" role="navigation" data-step="2" data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;"
                data-position="right" class="navbar-default navbar-static-side">
            <div class="sidebar-collapse menu-scroll">
                <ul id="side-menu" class="nav">
                    
                     <div class="clearfix"></div>
                    <li><a href="/user/inici"><i class="fa fa-desktop fa-fw">
                        <div class="icon-bg bg-pink"></div>
                    </i><span class="menu-title">Inici</span></a>
                       
                    </li>
                    <li><a href="/admin/voluntaris"><i class="fa fa-phone-square fa-fw">
                        <div class="icon-bg bg-green"></div>
                    </i><span class="menu-title">Voluntaris</span></a>
                       
                    </li>
                    <li><a href="/admin/vehicles"><i class="fa fa-automobile fa-fw">
                        <div class="icon-bg bg-violet"></div>
                    </i><span class="menu-title">Vehicles</span></a>
                      
                    </li>
                    <li><a href="/user/missatges"><i class="fa fa-mobile-phone fa-fw">
                        <div class="icon-bg bg-blue"></div>
                    </i><span class="menu-title">Missatges</span></a>
                          
                    </li>
                    <li><a href="/admin/serveis"><i class="fa fa-fire-extinguisher fa-fw">
                        <div class="icon-bg bg-red"></div>
                    </i><span class="menu-title">Serveis</span></a>
                      
                    </li>
                    <li><a href="/admin/mapa"><i class="fa fa-map-marker fa-fw">
                        <div class="icon-bg bg-grey"></div>
                    </i><span class="menu-title">Mapa Voluntaris</span></a>
                      
                    </li>
                    </ul>
            </div>
        </nav>
          
          
            <div id="page-wrapper">
                <!--BEGIN TITLE & BREADCRUMB PAGE-->
                <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
                    <div class="page-header pull-left">
                        <div class="page-title">
                            Configuració dades voluntari</div>
                    </div>
                    <ol class="breadcrumb page-breadcrumb pull-right">
                        <li><i class="fa fa-home"></i>&nbsp;<a href="dashboard.html">Home</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="hidden"><a href="#">Voluntaris</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="active">Voluntaris</li>
                    </ol>
                    <div class="clearfix">
                    </div>
                </div>
                <!--END TITLE & BREADCRUMB PAGE-->
                <!--BEGIN CONTENT-->
                <div class="page-content">
                    <div id="tab-general">
                        <div class="row mbl">
                            <div class="col-lg-12">

                                <div class="col-md-12">
                                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;">
                                    </div>
                                </div>

                            </div>

                            <div class="col-lg-12">
                                <div class="page-content">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel">
                                                <div class="panel-body">

                                                   <c:if test="${fromUpdate}">
                                                       <div class="panel panel-success">
                                                           <div class="panel-heading">
                                                               <h3 class="panel-title">Usuari ${member.name} correctament canviat</h3>
                                                           </div>
                                                       </div>
                                                   </c:if>
                                                   <c:if test="${fromRegister}">
                                                       <div class="panel panel-success">
                                                           <div class="panel-heading">
                                                               <h3 class="panel-title">Usuari ${member.name} correctament afegit</h3>
                                                           </div>
                                                       </div>
                                                   </c:if>

                                                   <form action="/admin/member/uploadFile" method="post" enctype="multipart/form-data">
                                                           <input type="file" name="file" />
                                                           <input type="hidden" name="member" value="${member.id}">
                                                           <input type="submit" value="upload" />
                                                   </form>
                                                   <img src="/member/image/display?name=${member.id}" alt="" title="" class="img-responsive img-thumbnail"/>
                                                    <c:if test="${member.role=='USER'}">
                                                        <form id="updatemember" action="/user/update_member" method="post">
                                                    </c:if>
                                                    <c:if test="${member.role=='ADMIN'}">
                                                        <form id="updatemember" action="/admin/update_member/${member.id}" method="post">
                                                    </c:if>
                                                        <input id="memberid" type="hidden" class="form-control" value="${memberData.id}"/></div>
                                                        <div class="form-body pal">
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="dni" style="font-weight:bold;">DNI</label>
                                                                    <input id="dni" name="dni" type="text" placeholder="DNI" class="form-control" value="${member.dni}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="tip" style="font-weight:bold;" >TIP</label>
                                                                    <input id="tip" name="tip" type="text" placeholder="tip" class="form-control" value="${member.tip}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="name" style="font-weight:bold;" >Nom</label>
                                                                    <input id="name" name="name" type="text" placeholder="Nom" class="form-control" value="${member.name}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="surname1" style="font-weight:bold;">Primer cognom</label>
                                                                    <input id="surname1" name="surname1" type="text" placeholder="Primer cognom" class="form-control" value="${member.surname1}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="surname2" style="font-weight:bold;">Segon cognom</label>
                                                                    <input id="surname2" name="surname2" type="text" placeholder="Segon cognom" class="form-control" value="${member.surname2}"/></div>
                                                            </div>

                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="birthDate" style="font-weight:bold;">Data aniversari</label>
                                                                    <input id="birthDate" name="birthDate" type="datetime" placeholder="Data naixement" class="form-control" value="${member.birthDate}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="address" style="font-weight:bold;">Adreça</label>
                                                                    <input id="address" name="address" type="text" placeholder="Adreça" class="form-control" value="${member.address}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="city" style="font-weight:bold;">Ciutat</label>
                                                                    <input id="city" name="city" type="text" placeholder="Ciutat" class="form-control" value="${member.city}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="postalCode" style="font-weight:bold;">Codi Postal</label>
                                                                    <input id="postalCode" name="postalCode" type="text" placeholder="Codi Postal" class="form-control" value="${member.postalCode}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                   <label for="postalCode" style="font-weight:bold;">Codi Postal</label>
                                                                    <input id="email" name="email" type="text" placeholder="Email address" class="form-control" value="${member.email}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="landPhoneNumber" style="font-weight:bold;">Telefon fix</label>
                                                                    <input id="landPhoneNumber" name="landPhoneNumber" type="text" placeholder="Telefon fix" class="form-control" value="${member.landPhoneNumber}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="mobilePhoneNumber" style="font-weight:bold;">Telefon mòbil</label>
                                                                    <input id="mobilePhoneNumber" name="mobilePhoneNumber" type="text" placeholder="Telefon mòbil" class="form-control" value="${member.mobilePhoneNumber}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="userGroup" style="font-weight:bold;">Rang</label>
                                                                    <select id="userGroup" name="userGroup" type="text" class="form-control" value="${member.userGroup}"/>
                                                                        <option value="VOL">VOLUNTARI</option>
                                                                        <option value="CDG">CAP DE GRUP</option>
                                                                        <option value="CDS">CAP DE SECCIO</option>
                                                                        <option value="CDU">CAP UNITAT</option>
                                                                    </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <label for="role" style="font-weight:bold;">Tipus d'usuari</label>
                                                                    <select id="role" name="role" type="text" placeholder="Rol" class="form-control" value="${member.role}"/>
                                                                         <option value="ADMIN">ADMIN</option>
                                                                         <option value="USER">USER</option>
                                                                    </select>
                                                            </div>
                                                            <hr />
                                                        <div class=" text-right pal">
                                                            <button type="submit" class="btn btn-primary">
                                                                Modificar
                                                            </button>
                                                            <button type="button" class="btn btn-primary" onclick="window.location.href='/admin/delete_member/${member.id}'">
                                                                Eliminar Voluntari
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END CONTENT-->
                <!--BEGIN FOOTER-->
                <div id="footer">
                    <div class="copyright">
                       2017 © AVPC Sant Vicenç de Castellet
                </div>
                <!--END FOOTER-->
            </div>
            <!--END PAGE WRAPPER-->
        </div>
    </div>
    <script src="/script/jquery-1.10.2.min.js"></script>
    <script src="/script/jquery-migrate-1.2.1.min.js"></script>
    <script src="/script/jquery-ui.js"></script>
    <script src="/script/bootstrap.min.js"></script>
    <script src="/script/bootstrap-hover-dropdown.js"></script>
    <script src="/script/html5shiv.js"></script>
    <script src="/script/respond.min.js"></script>
    <script src="/script/jquery.metisMenu.js"></script>
    <script src="/script/jquery.slimscroll.js"></script>
    <script src="/script/jquery.cookie.js"></script>
    <script src="/script/icheck.min.js"></script>
    <script src="/script/custom.min.js"></script>
    <script src="/script/jquery.news-ticker.js"></script>
    <script src="/script/jquery.menu.js"></script>
    <script src="/script/pace.min.js"></script>
    <script src="/script/holder.js"></script>
    <script src="/script/responsive-tabs.js"></script>
    <script src="/script/jquery.flot.js"></script>
    <script src="/script/jquery.flot.categories.js"></script>
    <script src="/script/jquery.flot.pie.js"></script>
    <script src="/script/jquery.flot.tooltip.js"></script>
    <script src="/script/jquery.flot.resize.js"></script>
    <script src="/script/jquery.flot.fillbetween.js"></script>
    <script src="/script/jquery.flot.stack.js"></script>
    <script src="/script/jquery.flot.spline.js"></script>
    <script src="/script/zabuto_calendar.min.js"></script>
    <script src="/script/index.js"></script>
    <script src="/script/highcharts.js"></script>
    <script src="/script/data.js"></script>
    <script src="/script/drilldown.js"></script>
    <script src="/script/exporting.js"></script>
    <script src="/script/highcharts-more.js"></script>
    <script src="/script/charts-highchart-pie.js"></script>
    <script src="/script/charts-highchart-more.js"></script>
    <script src="/script/modernizr.min.js"></script>
    <script src="/script/jplist.min.js"></script>
    <script src="/script/jplist.js"></script>
    <script src="/script/animation.js"></script>
    <script src="/script/jquery.simple-dtpicker.js"></script>
    <!--CORE JAVASCRIPT-->
    <script src="/script/main.js"></script>
    <script>        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-145464-12', 'auto');
        ga('send', 'pageview');

$(function(){
			$('#birthDate').appendDtpicker({
             inline: true,
             disableEntry: true,
             format: 'YYYY-mm-dd HH:mm'
            });
		});
</script>
</body>
</html>
