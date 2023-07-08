<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">

  <%@ include file="/back-end/gym_index/back-end-head-source.file" %>
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
   
     <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
   
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
     
     <%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
     
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper site-min-height">
        <h3><i class="fa fa-angle-right"></i> Inline Editor</h3>
        <div class="row content-panel mt mb">
          <div class="col-md-6">
            <h2 contenteditable="true">Dashio is a fully responsive admin dashboard template built with Bootstrap 3.1.1 Framework</h2>
            <h3 contenteditable="true">Following the Equator, Complete</h3>
            <h4 contenteditable="true">Mark Twain (Samuel Clemens)</h4>
          </div>
          <div class="col-md-6">
            <p contenteditable="true" class="mt">Later, when we reached the city, and glanced down the chief avenue, smouldering in its crushed-strawberry tint, those splendid effects were repeated; for every balcony, and every fanciful bird-cage of a snuggery countersunk in the house-fronts,
              and all the long lines of roofs were crowded with people, and each crowd was an explosion of brilliant color.</p>
            <p contenteditable="true">For color, and picturesqueness, and novelty, and outlandishness, and sustained interest and fascination, it was the most satisfying show I had ever seen, and I suppose I shall not have the privilege of looking upon its like again.</p>
            <p contenteditable="true">In the first place God made idiots. This was for practice. Then He made School Boards. --Pudd'nhead Wilson's New Calendar.</p>
            <p contenteditable="true">"I pray please to give me some action (work) for I am very poor boy I have no one to help me even so father for it so it seemed in thy good sight, you give the Telegraph Office, and another work what is your wish I am very poor boy, this understand
              what is your wish you my father I am your son this understand what is your wish.</p>
          </div>
        </div>
        <div class="mt"></div>
        <div class="row content-panel mt mb">
          <div class="col-md-6">
            <h3 contenteditable="true">The Count of Monte Cristo</h3>
            <h4 contenteditable="true">Alexander Dumas, Pere</h4>
          </div>
          <div class="col-md-6">
            <p contenteditable="true" class="mt">"What, still keeping up this silly jest? My dear fellow, it is perfectly ridiculous--stupid! You had better tell me at once that you intend starving me to death."</p>
            <p contenteditable="true">"And what am I to pay with, brute?" said Danglars, enraged. "Do you suppose I carry 100,000 francs in my pocket?"</p>
            <p contenteditable="true">"Your excellency has 5,050,000 francs in your pocket; that will be fifty fowls at 100,000 francs apiece, and half a fowl for the 50,000."</p>
            <p contenteditable="true">Danglars shuddered. The bandage fell from his eyes, and he understood the joke, which he did not think quite so stupid as he had done just before. "Come," he said, "if I pay you the 100,000 francs, will you be satisfied, and allow me to eat
              at my ease?"</p>
          </div>
        </div>
        <div class="mt"></div>
        <div class="row content-panel mt mb">
          <div class="col-md-6 mt">
            <blockquote contenteditable="true">A bird sitting on a tree is never afraid of the branch breaking, because her trust is not in the branch, but in her own wings.</blockquote>
            <h5 contenteditable="true">Believe in yourself</h5>
          </div>
          <div class="col-md-6 mt">
            <blockquote contenteditable="true">Gossip is started by the insecure, spread by fools, and accepted by idiots. Don't be any of the three.</blockquote>
            <h5 contenteditable="true">Unknown</h5>
          </div>
        </div>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <p>
          &copy; Copyrights <strong>Dashio</strong>. All Rights Reserved
        </p>
        <div class="credits">
          More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
        </div>
        <a href="inline_editor.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>

  <!--script for this page-->
  <script src="lib/ckeditor/ckeditor.js"></script>

</body>

</html>
