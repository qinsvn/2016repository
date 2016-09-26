<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://v3.bootcss.com/favicon.ico">
    <title>Cover Template for Bootstrap</title>
    <link href="${base}/css/bootstrap.css" rel="stylesheet">
    <link href="${base}/css/cover.css" rel="stylesheet">
    <script src="${base}/js/ie-emulation-modes-warning.js"></script>

  </head>

  <body>

    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">首页${pageContext.request.contextPath}</a></li>
                  <li><a href="#">其他base:${base}</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">http代理，切换ip访问</h1>
            <p class="lead">Google 的免费翻译服务可提供简体中文和另外 100 多种语言之间的互译功能,可让您即时翻译字词、短语和网页内容.</p>
            <p class="lead">
              <a href="#" class="btn btn-lg btn-default">请求</a>
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${base}/js/jquery.js"></script>
    <script src="${base}/js/bootstrap.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${base}/js/ie10-viewport-bug-workaround.js"></script>
  

</body></html>