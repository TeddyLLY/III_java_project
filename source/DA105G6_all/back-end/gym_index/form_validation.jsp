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

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
      <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> Form Validation</h3>
        <!-- BASIC FORM VALIDATION -->
        <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i> Basic Validations</h4>
            <div class="form-panel">
              <form role="form" class="form-horizontal style-form">
                <div class="form-group has-success">
                  <label class="col-lg-2 control-label">First Name</label>
                  <div class="col-lg-10">
                    <input type="text" placeholder="" id="f-name" class="form-control">
                    <p class="help-block">Successfully done</p>
                  </div>
                </div>
                <div class="form-group has-error">
                  <label class="col-lg-2 control-label">Last Name</label>
                  <div class="col-lg-10">
                    <input type="text" placeholder="" id="l-name" class="form-control">
                    <p class="help-block">Aha you gave a wrong info</p>
                  </div>
                </div>
                <div class="form-group has-warning">
                  <label class="col-lg-2 control-label">Email</label>
                  <div class="col-lg-10">
                    <input type="email" placeholder="" id="email2" class="form-control">
                    <p class="help-block">Something went wrong</p>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-10">
                    <button class="btn btn-theme" type="submit">Submit</button>
                  </div>
                </div>
              </form>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
        <!-- /row -->
        <!-- FORM VALIDATION -->
        <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i> Form Validations</h4>
            <div class="form-panel">
              <div class=" form">
                <form class="cmxform form-horizontal style-form" id="commentForm" method="get" action="">
                  <div class="form-group ">
                    <label for="cname" class="control-label col-lg-2">Name (required)</label>
                    <div class="col-lg-10">
                      <input class=" form-control" id="cname" name="name" minlength="2" type="text" required />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="cemail" class="control-label col-lg-2">E-Mail (required)</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="cemail" type="email" name="email" required />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="curl" class="control-label col-lg-2">URL (optional)</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="curl" type="url" name="url" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="ccomment" class="control-label col-lg-2">Your Comment (required)</label>
                    <div class="col-lg-10">
                      <textarea class="form-control " id="ccomment" name="comment" required></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Save</button>
                      <button class="btn btn-theme04" type="button">Cancel</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
        <!-- /row -->
        <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i> Advanced Form Validations</h4>
            <div class="form-panel">
              <div class="form">
                <form class="cmxform form-horizontal style-form" id="signupForm" method="get" action="">
                  <div class="form-group ">
                    <label for="firstname" class="control-label col-lg-2">Firstname</label>
                    <div class="col-lg-10">
                      <input class=" form-control" id="firstname" name="firstname" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="lastname" class="control-label col-lg-2">Lastname</label>
                    <div class="col-lg-10">
                      <input class=" form-control" id="lastname" name="lastname" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="username" class="control-label col-lg-2">Username</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="username" name="username" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="password" class="control-label col-lg-2">Password</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="password" name="password" type="password" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="confirm_password" class="control-label col-lg-2">Confirm Password</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="confirm_password" name="confirm_password" type="password" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="email" class="control-label col-lg-2">Email</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="email" name="email" type="email" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="agree" class="control-label col-lg-2 col-sm-3">Agree to Our Policy</label>
                    <div class="col-lg-10 col-sm-9">
                      <input type="checkbox" style="width: 20px" class="checkbox form-control" id="agree" name="agree" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="newsletter" class="control-label col-lg-2 col-sm-3">Receive the Newsletter</label>
                    <div class="col-lg-10 col-sm-9">
                      <input type="checkbox" style="width: 20px" class="checkbox form-control" id="newsletter" name="newsletter" />
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Save</button>
                      <button class="btn btn-theme04" type="button">Cancel</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
        <!-- /row -->
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
        <a href="form_validation.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>

  <!--script for this page-->
  <script src="lib/form-validation-script.js"></script>

</body>

</html>
