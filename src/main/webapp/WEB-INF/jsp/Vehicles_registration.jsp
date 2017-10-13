<!DOCTYPE html>
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
                                <li><a href="/admin/member_password/<%= request.getSession().getAttribute("userid") %>"><i class="fa fa-user"></i>Canviar Password</a></li>
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
                            Registre de nou voluntari</div>
                    </div>
                    <ol class="breadcrumb page-breadcrumb pull-right">
                        <li><i class="fa fa-home"></i>&nbsp;<a href="dashboard.html">Home</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="hidden"><a href="#">Vehicles</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="active">Vehicles</li>
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
                                                    <form id="registermember" action="/admin/register_vehicle" method="post">
                                                        <div class="form-body pal">
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <i class="fa fa-user"></i>
                                                                    <input id="brand" name="brand" type="text" placeholder="brand" class="form-control" /></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <i class="fa fa-user"></i>
                                                                    <input id="credential" name="credential" type="text" placeholder="credential" class="form-control" /></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <i class="fa fa-user"></i>
                                                                    <input id="model" name="model" type="text" placeholder="model" class="form-control" /></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="input-icon right">
                                                                    <i class="fa fa-user"></i>
                                                                    <input id="registrationNumber" name="registrationNumber" type="text" placeholder="registrationNumber" class="form-control" /></div>
                                                            </div>


                                                            <hr />

                                                        </div>
                                                        <div class=" text-right pal">
                                                            <button type="submit" class="btn btn-primary">
                                                                Afegir vehicle</button>
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
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
        <script src="/script/voluntaris.js"></script>
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


</script>
    </div>
</body>
</html>
