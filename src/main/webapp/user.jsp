<%-- 
    Document   : index
    Created on : 13.09.2016, 19:58:49
    Author     : deonisius
--%>

<%@page import="ru.ezhov.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--«•—©» encoding force-->
        <title>Sticky-notes</title>
        <meta name="description" content="Easy to make, easy to find... " />
        <meta name="keywords" content="notes" />
        <meta name="author" content="Ezhov Denis" />
        <meta name="revision" content="201609121505" />        
        <script type='text/javascript' src="src/js/jquery-1.12.0.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link href="src/css/dashboard.css" rel="stylesheet">


        <!-- include summernote css/js-->
        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>        


        <link type="text/css" rel="stylesheet" href="src/css/my_css.css"/>
        <script src="src/js/my_scripts.js"></script>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.6.0/styles/default.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.6.0/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>

        <!--подключаем редактор-->

        <script src="//cdn.ckeditor.com/4.5.11/full/ckeditor.js"></script>   




    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Sticky-notes</a>
                </div>
                <div class="navbar-collapse collapse">
                    <!--
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Settings</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                    -->
                    <form class="navbar-form navbar-right">
                        <!--<input type="text" class="form-control" placeholder="Search...">-->
                        <%
                            HttpSession httpSession = request.getSession(true);
                            User user = (User) httpSession.getAttribute("user");
                            out.print(
                                    "<li role=\"presentation\" ><a href=\"#\">" + user.getEmail() + "</a></li>"
                                    + "<li role=\"presentation\" ><a href=\"exit\">Exit</a></li>");
                        %>
                    </form>
                    <form class="navbar-form navbar-right">
                        <button type="button" class="btn btn-info btn-sm" data-toggle="collapse"   data-target="#addFrame"  onClick="            CKEDITOR.replace('editorAdd');">Add note</button>
                    </form>
                </div>
            </div>
        </div>



        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <!-- Trigger the modal with a button -->
                    <div class="row">
                        <p>
                            <input  type="text" id="find" placeholder="Find"/>
                        </p>
                    </div>
                    <ul class="nav nav-sidebar" id="menu">
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">



                    <div class="row">          



                        <div id="addFrame" class="collapse">
                            <form action="" method="post" class="note-form" id="addNewToBase">
                                <div class="label-caption">
                                    <label for="caption">Name:</label>
                                    <input  id="nameInsert" type="text"  name="name">
                                </div>
                                <div class="label-caption">
                                    <label for="outlink">Link:</label>
                                    <input  id="linkInsert" type="text" name="link">
                                </div>
                                <button  type="button" class="btn btn-info btn-sm" id="insertCodeToTextAdd">Inser code</button>
                                <div class="label-caption">
                                    <label for="content">Text:</label>		
                                    <span class="area-text-block"><textarea id="textInsert"  name="editorAdd"></textarea></span>
                                    
                                       <span class=\"area-text-block\"><textarea id=\"texEdit\" name=\""
                                    
                                </div>
                                <input  id="dateTimeAdd" type="text" hidden="true" name="dateTime">
                                <div class="footer-button-block">
                                    <input id="insertToBase" name="edit" value="Add" type="button" class="btn btn-info btn-md"  onclick="
                                            $('#dateTimeAdd').val(getDateTimeNowStr());
                                            ajaxFormRequest('addNewToBase', 'addNewToBase', function () {
                                                $('#myModalAdd').modal('hide');
                                                loadList();
                                            })">
                                </div>
                            </form>            
                        </div>             

                    </div>

                    <br>
                    <br>











                    <div id="info">
                        <div class="hero-unit">
                            <h1>Welcome to sticky-notes</h1>
                            <p></p>
                            <!--
                            <p>
                                <a class="btn btn-primary btn-large">
                                    Узнать больше
                                </a>
                            </p>
                            -->
                        </div>                        
                    </div>


                </div>
            </div>
        </div>








    </body>
</html>
