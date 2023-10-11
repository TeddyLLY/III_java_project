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
  <link rel="stylesheet" href="css/to-do.css">

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
        <h3><i class="fa fa-angle-right"></i> To-Do Lists</h3>
        <!-- SIMPLE TO DO LIST -->
        <div class="row mt">
          <div class="col-md-12">
            <div class="white-panel pn">
              <div class="panel-heading">
                <div class="pull-left">
                  <h5><i class="fa fa-tasks"></i> Todo List - Basic Style</h5>
                </div>
                <br>
              </div>
              <div class="custom-check goleft mt">
                <table id="todo" class="table table-hover custom-check">
                  <tbody>
                    <tr>
                      <td>
                        <span class="check"><input type="checkbox" class="checked"></span>
                        <a href="index.html#">Send invoice</a></span>
                        <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="check"><input type="checkbox" class="checked"></span>
                        <a href="index.html#">Check messages</a></span>
                        <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="check"><input type="checkbox" class="checked"></span>
                        <a href="index.html#">Pay bills</a></span>
                        <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="check"><input type="checkbox" class="checked"></span>
                        <a href="index.html#">Schedule site </a></span>
                        <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <!-- /table-responsive -->
            </div>
            <!--/ White-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
        <!--  row -->
        <!-- COMPLEX TO DO LIST -->
        <div class="row mt">
          <div class="col-md-12">
            <section class="task-panel tasks-widget">
              <div class="panel-heading">
                <div class="pull-left">
                  <h5><i class="fa fa-tasks"></i> Todo List - Complex Style</h5>
                </div>
                <br>
              </div>
              <div class="panel-body">
                <div class="task-content">
                  <ul class="task-list">
                    <li>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Dashio - Admin Panel & Front-end Theme</span>
                        <span class="badge bg-theme">Done</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs"><i class=" fa fa-check"></i></button>
                          <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                          <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Extensive collection of plugins</span>
                        <span class="badge bg-warning">Cool</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs"><i class=" fa fa-check"></i></button>
                          <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                          <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Free updates always, no extra fees.</span>
                        <span class="badge bg-success">2 Days</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs"><i class=" fa fa-check"></i></button>
                          <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                          <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">More features coming soon</span>
                        <span class="badge bg-info">Tomorrow</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs"><i class=" fa fa-check"></i></button>
                          <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                          <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Hey, seriously, you should buy this Dashboard</span>
                        <span class="badge bg-important">Now</span>
                        <div class="pull-right">
                          <button class="btn btn-success btn-xs"><i class=" fa fa-check"></i></button>
                          <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                          <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                        </div>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class=" add-task-row">
                  <a class="btn btn-success btn-sm pull-left" href="todo_list.html#">Add New Tasks</a>
                  <a class="btn btn-default btn-sm pull-right" href="todo_list.html#">See All Tasks</a>
                </div>
              </div>
            </section>
          </div>
          <!-- /col-md-12-->
        </div>
        <!-- /row -->
        <!-- SORTABLE TO DO LIST -->
        <div class="row mt mb">
          <div class="col-md-12">
            <section class="task-panel tasks-widget">
              <div class="panel-heading">
                <div class="pull-left">
                  <h5><i class="fa fa-tasks"></i> Todo List - Sortable Style</h5>
                </div>
                <br>
              </div>
              <div class="panel-body">
                <div class="task-content">
                  <ul id="sortable" class="task-list">
                    <li class="list-primary">
                      <i class=" fa fa-ellipsis-v"></i>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Dashio - Admin Panel & Front-end Theme</span>
                        <span class="badge bg-theme">Done</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs fa fa-check"></button>
                          <button class="btn btn-primary btn-xs fa fa-pencil"></button>
                          <button class="btn btn-danger btn-xs fa fa-trash-o"></button>
                        </div>
                      </div>
                    </li>
                    <li class="list-danger">
                      <i class=" fa fa-ellipsis-v"></i>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Extensive collection of plugins</span>
                        <span class="badge bg-warning">Cool</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs fa fa-check"></button>
                          <button class="btn btn-primary btn-xs fa fa-pencil"></button>
                          <button class="btn btn-danger btn-xs fa fa-trash-o"></button>
                        </div>
                      </div>
                    </li>
                    <li class="list-success">
                      <i class=" fa fa-ellipsis-v"></i>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Free updates always, no extra fees.</span>
                        <span class="badge bg-success">2 Days</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs fa fa-check"></button>
                          <button class="btn btn-primary btn-xs fa fa-pencil"></button>
                          <button class="btn btn-danger btn-xs fa fa-trash-o"></button>
                        </div>
                      </div>
                    </li>
                    <li class="list-warning">
                      <i class=" fa fa-ellipsis-v"></i>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">More features coming soon</span>
                        <span class="badge bg-info">Tomorrow</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs fa fa-check"></button>
                          <button class="btn btn-primary btn-xs fa fa-pencil"></button>
                          <button class="btn btn-danger btn-xs fa fa-trash-o"></button>
                        </div>
                      </div>
                    </li>
                    <li class="list-info">
                      <i class=" fa fa-ellipsis-v"></i>
                      <div class="task-checkbox">
                        <input type="checkbox" class="list-child" value="" />
                      </div>
                      <div class="task-title">
                        <span class="task-title-sp">Hey, seriously, you should buy this Dashboard</span>
                        <span class="badge bg-important">Now</span>
                        <div class="pull-right hidden-phone">
                          <button class="btn btn-success btn-xs fa fa-check"></button>
                          <button class="btn btn-primary btn-xs fa fa-pencil"></button>
                          <button class="btn btn-danger btn-xs fa fa-trash-o"></button>
                        </div>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class=" add-task-row">
                  <a class="btn btn-success btn-sm pull-left" href="todo_list.html#">Add New Tasks</a>
                  <a class="btn btn-default btn-sm pull-right" href="todo_list.html#">See All Tasks</a>
                </div>
              </div>
            </section>
          </div>
          <!--/col-md-12 -->
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
        <a href="todo_list.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>


  <!--script for this page-->
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script src="lib/tasks.js" type="text/javascript"></script>
  <script>
    jQuery(document).ready(function() {
      TaskList.initTaskWidget();
    });

    $(function() {
      $("#sortable").sortable();
      $("#sortable").disableSelection();
    });
  </script>

</body>

</html>
