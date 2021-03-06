<%@ page import="test.CheckVersion" %>
<%--
  Created by IntelliJ IDEA.
  User: Yang ZHANG
  Date: 2014/11/3
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 9]> <html class="no-js ie9" lang="en"> <![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="en" itemscope itemtype="http://schema.org/Product"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <!-- Use the .htaccess and remove these lines to avoid edge case issues.
         More info: h5bp.com/b/378 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>YAGO</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="humans.txt">

    <link rel="shortcut icon" href="favicon.png" type="image/x-icon"/>

    <!-- Facebook Metadata /-->
    <meta property="fb:page_id" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:description" content=""/>
    <meta property="og:title" content=""/>

    <!-- Google+ Metadata /-->
    <meta itemprop="name" content="">
    <meta itemprop="description" content="">
    <meta itemprop="image" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">

    <!-- We highly recommend you use SASS and write your custom styles in sass/_custom.scss.
         However, there is a blank style.css in the css directory should you prefer -->
    <link rel="stylesheet" href="css/gumby.css">
    <!-- <link rel="stylesheet" href="css/style.css"> -->

    <script src="js/libs/modernizr-2.6.2.min.js"></script>
</head>
<body id="home">
<nav id="navbar-main-nav" class="navbar">
    <div class="row">
        <a class="toggle" gumby-trigger="#navbar-main-nav #main-nav" href="#"><i class="icon-menu"></i></a>

        <h1 class="four columns logo">
            <%--<a href="/">
                <img src="img/SJTU-white.png" gumby-retina="">
                <span>Team</span>
            </a>--%>
        </h1>
        <nav class="eight columns pull_right">
            <ul id="main-nav">
                <li><a href="#"><span>Features</span><i class="icon-archive" title="Features"></i></a></li>
                <li><a href="#" gumby-trigger="#drawer-examples" class="switch"><span>Examples</span><i class="icon-database" title="Examples"></i></a></li>
                <li><a href="#"><span>About</span><i class="icon-users" title="About"></i></a></li>
            </ul>
        </nav>
    </div>
</nav>
<div class="wrapper">
    <header class="row">
        <hgroup>
            <h1>YAGO</h1>
            <h4>Yet Another Great Ontology</h4>
            <h5>Using <%=CheckVersion.getMessage()%>
            </h5>
        </hgroup>
    </header>
</div>
<div class="wrapper">
    <section class="row">
        <form action="query" method="post" id="query-form">
            <div class="picker">
                <select name="query-type">
                    <option value="#" disabled>Query type....</option>
                    <option value="name">Name</option>
                    <option value="id1">ID&rightarrow;fact</option>
                    <option value="type">Type</option>
                    <option value="entity1">Entity&rightarrow;relations</option>
                    <option value="entity2">Entity&rightarrow;types</option>
                    <option value="id2">ID&rightarrow;geo & time info</option>
                </select>
            </div>
            <div class="field append six columns centered">
                <input class="wide input" name="query-content" type="text" placeholder="Search...."/>

                <div class="medium primary btn">
                    <button id="button-submit">Go</button>
                </div>
            </div>
        </form>
    </section>
</div>
<div class="wrapper">
    <div class="row">
        <div class="four columns centered drawer" id="drawer-examples">
            <a class="close switch active" gumby-trigger="|#drawer-examples"><i class="icon-cancel" title=".icon-cancel"></i></a>
            <ol>
                <li>jack</li>
                <li>id_8onowc_115_529y5y</li>
                <li>yagoLegalActor</li>
                <li>Rimburg</li>
                <li>Rimburg</li>
                <li>id_1knwl1c_1xk_wxl0mf</li>
            </ol>
        </div>
    </div>
</div>
<div class="wrapper">
    <section id="result" class="row"></section>
</div>
<div class="wrapper loader hide">
    <section class="row">
        <div id="floatingBarsG" class="one column centered">
            <div class="blockG" id="rotateG_01">
            </div>
            <div class="blockG" id="rotateG_02">
            </div>
            <div class="blockG" id="rotateG_03">
            </div>
            <div class="blockG" id="rotateG_04">
            </div>
            <div class="blockG" id="rotateG_05">
            </div>
            <div class="blockG" id="rotateG_06">
            </div>
            <div class="blockG" id="rotateG_07">
            </div>
            <div class="blockG" id="rotateG_08">
            </div>
        </div>
    </section>
</div>

<!-- Grab Google CDN's jQuery, fall back to local if offline -->
<!-- 2.0 for modern browsers, 1.10 for .oldie -->
<script>
    var oldieCheck = Boolean(document.getElementsByTagName('html')[0].className.match(/\soldie\s/g));
//    if (!oldieCheck) {
//        document.write('<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"><\/script>');
//    } else {
//        document.write('<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"><\/script>');
//    }
</script>
<script>
    if (!window.jQuery) {
        if (!oldieCheck) {
            document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
        } else {
            document.write('<script src="js/libs/jquery-1.10.1.min.js"><\/script>');
        }
    }
</script>

<!--
Include gumby.js followed by UI modules followed by gumby.init.js
Or concatenate and minify into a single file -->
<script gumby-touch="js/libs" src="js/libs/gumby.js"></script>
<script src="js/libs/ui/gumby.retina.js"></script>
<script src="js/libs/ui/gumby.fixed.js"></script>
<script src="js/libs/ui/gumby.skiplink.js"></script>
<script src="js/libs/ui/gumby.toggleswitch.js"></script>
<script src="js/libs/ui/gumby.checkbox.js"></script>
<script src="js/libs/ui/gumby.radiobtn.js"></script>
<script src="js/libs/ui/gumby.tabs.js"></script>
<script src="js/libs/ui/gumby.navbar.js"></script>
<script src="js/libs/ui/jquery.validation.js"></script>
<script src="js/libs/gumby.init.js"></script>

<!--
Google's recommended deferred loading of JS
gumby.min.js contains gumby.js, all UI modules and gumby.init.js

Note: If you opt to use this method of defered loading,
ensure that any javascript essential to the initial
display of the page is included separately in a normal
script tag.

<script type="text/javascript">
function downloadJSAtOnload() {
var element = document.createElement("script");
element.src = "js/libs/gumby.min.js";
document.body.appendChild(element);
}
if (window.addEventListener)
window.addEventListener("load", downloadJSAtOnload, false);
else if (window.attachEvent)
window.attachEvent("onload", downloadJSAtOnload);
else window.onload = downloadJSAtOnload;
</script> -->

<script src="js/plugins.js"></script>
<script src="js/main.js"></script>

<!-- Change UA-XXXXX-X to be your site's ID -->
<!--<script>
window._gaq = [['_setAccount','UAXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];
Modernizr.load({
  load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
});
</script>-->

<!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.
   chromium.org/developers/how-tos/chrome-frame-getting-started -->
<!--[if lt IE 7 ]>
<script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
<script>window.attachEvent('onload', function () {
    CFInstall.check({mode: 'overlay'})
})</script>
<![endif]-->
</body>
</html>
