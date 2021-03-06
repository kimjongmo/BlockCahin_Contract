<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	
	<!-- 1. 세션값 저장 -->
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	%>
  <head>

	<!-- 2. 부트스트랩에서 가져온 css  -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Contract Master</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/creative.min.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <!-- Navigation -->
    <!-- 3. 상단메뉴 네비게이션 바 이용 -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Contract Master</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          
          <!-- userID 값이 없을 때 : 로그인 안한 상태 -->
          <%
          	if(userID == null){
          %> 
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="validation.jsp">검증하기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#services">서비스 특징</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#portfolio">서비스 기능</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#contact">문의</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="login.jsp" >로그인</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="join.jsp" role="button">회원가입</a>
            </li>
          </ul>
          
          <!-- userID 값이 있을 때 : 로그인 한 상태 -->
          <%
          	}else{
          		
          %>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="board.jsp">계약서 작성</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="validation.jsp">검증</a>
            </li>
            <li class="nav-item">
              <font size="2em" color="orange"><%=session.getAttribute("userID")%> 님</font>
            </li>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="logoutAction.jsp" role="button">로그아웃</a>
            </li>
          </ul>
          <%
          	}
          %>
        </div>
      </div>
    </nav>

	<!-- 4. 페이지 본문 부분  -->
	<%
          	if(userID == null){
    %> 
    <header class="masthead text-center text-white d-flex">
      <div class="container my-auto">
        <div class="row">
          <div class="col-lg-10 mx-auto">
            <h1 class="text-uppercase">
              <strong>똑똑한 계약, <br>쓰지말고 체인에 연결하세요.</strong>
            </h1>
            <hr>
          </div>
          <div class="col-lg-8 mx-auto">
            <p class="text-faded mb-5">Contract Master는 계약서 파일을 생성하여 안전하고 암호화된 블록체인에 연결하여 관리할 수 있는 서비스입니다. </p>
            <a class="btn btn-primary btn-xl js-scroll-trigger" href="validation.jsp">검증하러 가기</a>
          </div>
        </div>
      </div>
    </header>

    <section class="bg-primary" id="about">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto text-center">
            <h2 class="section-heading text-white">Contract more smart!</h2>
            <hr class="light my-4">
            <p class="text-faded mb-4">Contract Master로 생성하고 등록하는 계약서는 임의적인 조작이 불가능하도록 블록체인에 연결되므로 문서 보호가 보장됩니다.</p>
            <a class="btn btn-light btn-xl js-scroll-trigger" href="#services">Get Started!</a>
          </div>
        </div>
      </div>
    </section>

    <section id="services">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            	<h2 class="section-heading">우리가 제공하는 Service는?</h2>
	           <hr class="my-4">
	            <a class="btn btn-primary btn-xl js-scroll-trigger" href="#portfolio">우리 서비스</a>
          </div>
        </div>
      </div>
      
      <!-- 아이콘 넣는 부분  -->
      <div class="container" >
      
        <div class="row" >
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-diamond text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">문서 저장</h3>
              <p class="text-muted mb-0">You can save your important files. Yeah~</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-paper-plane text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">검증 시스템</h3>
              <p class="text-muted mb-0">You can check if there are any changes in your file.</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-newspaper-o text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">안전한 보안</h3>
              <p class="text-muted mb-0">We demonstrate integrity by using block chains.</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-heart text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">간편함</h3>
              <p class="text-muted mb-0">We can veriy without special subscription.</p>
            </div>
          </div>
        </div>

      </div>
    </section>


	<!-- 사진 넣는 부분  -->
    <section class="p-0" id="portfolio">
      <div class="container-fluid p-0">
        <div class="row no-gutters popup-gallery">
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/1.png">
              <img class="img-fluid" src="img/portfolio/thumbnails/1.png" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    	첫 번째
                  </div>
                  <div class="project-name">
                  		 회원가입을 해줍니다^_^
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/2.PNG">
              <img class="img-fluid" src="img/portfolio/thumbnails/2.PNG" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                   		 두 번째
                  </div>
                  <div class="project-name">
                   		로그인을 해줍니다!
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/3.JPG">
              <img class="img-fluid" src="img/portfolio/thumbnails/3.JPG" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    	세 번째
                  </div>
                  <div class="project-name">
                    	카카오로 가능합니다~
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/4.JPG">
              <img class="img-fluid" src="img/portfolio/thumbnails/4.JPG" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    	네 번째
                  </div>
                  <div class="project-name">
                    	중요한 문서를 저장합니다.
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/5.JPG">
              <img class="img-fluid" src="img/portfolio/thumbnails/5.JPG" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    	다섯 번째
                  </div>
                  <div class="project-name">
                    	올린 파일을 검증합니다.
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/6.JPG">
              <img class="img-fluid" src="img/portfolio/thumbnails/6.JPG" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    	여섯 번째
                  </div>
                  <div class="project-name">
                    	비회원도 검증이 가능하답니다!!
                  </div>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>

	<!-- 로그인 했을 때 안했을 때 차이  -->
    <section class="bg-dark text-white">
      <div class="container text-center">
        <h2 class="mb-4">MADE BY LEE&KIM</h2>
			<a class="btn btn-light btn-xl sr-button" href="#contact">로그인을 해주세요~!</a>
		</div>
    </section>

    <section id="contact">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto text-center">
            <h2 class="section-heading">문의해주세요!</h2>
            <hr class="my-4">
            <p class="mb-5">어려운 부분이 있나요?? 이곳으로 언제든지 문의해 주세요</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 ml-auto text-center">
            <i class="fa fa-phone fa-3x mb-3 sr-contact"></i>
            <p>관리자 번호</p>
          </div>
          <div class="col-lg-4 mr-auto text-center">
            <i class="fa fa-envelope-o fa-3x mb-3 sr-contact"></i>
            <p>
              <a href="mailto:your-email@your-domain.com">admin @ gmail.com</a>
            </p>
          </div>
        </div>
      </div>
    </section>
	<%
          	}else{
          		
    %>
    
    <header class="masthead text-center text-white d-flex">
      <div class="container my-auto">
        <div class="row">
          <div class="col-lg-10 mx-auto">
            <h1 class="text-uppercase">
              <strong> <br>환영합니다.</strong>
            </h1>
            <hr>
          </div>
          <div class="col-lg-8 mx-auto">
            <p class="text-faded mb-5">Contract Master는 계약서 파일을 생성하여 안전하고 암호화된 블록체인에 연결하여 관리할 수 있는 서비스입니다. </p>
            <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">서비스이용해보기</a>
          </div>
        </div>
      </div>
    </header>
    
     <section class="bg-primary" id="about">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto text-center">
            <h2 class="section-heading text-white">Contract more smart!</h2>
            <hr class="light my-4">
            <p class="text-faded mb-4">Contract Master로 생성하고 등록하는 계약서는 임의적인 조작이 불가능하도록 블록체인에 연결되므로 문서 보호가 보장됩니다.</p>
            <a class="btn btn-light btn-xl js-scroll-trigger" href="#services">Get Started!</a>
          </div>
        </div>
      </div>
    </section>

    <section id="services">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            	<h2 class="section-heading">우리가 제공하는 Service는?</h2>
	           <hr class="my-4">
	            <a class="btn btn-primary btn-xl js-scroll-trigger" href="#portfolio">우리 서비스</a>
          </div>
        </div>
      </div>
      
      <!-- 아이콘 넣는 부분  -->
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-diamond text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">계약서 작성</h3>
              <p class="text-muted mb-0">Our templates are updated regularly so they don't break.</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-paper-plane text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">계약서 생성</h3>
              <p class="text-muted mb-0">You can use this theme as is, or you can make changes!</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-newspaper-o text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">계약서 조회</h3>
              <p class="text-muted mb-0">We update dependencies to keep things fresh.</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
              <i class="fa fa-4x fa-heart text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3">안전한 보안</h3>
              <p class="text-muted mb-0">You have to make your websites with love these days!</p>
            </div>
          </div>
        </div>
      </div>
    </section>


	<!-- 사진 넣는 부분  -->
    <section class="p-0" id="portfolio">
      <div class="container-fluid p-0">
        <div class="row no-gutters popup-gallery">
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/1.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/1.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/2.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/2.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/3.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/3.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/4.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/4.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/5.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/5.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-4 col-sm-6">
            <a class="portfolio-box" href="img/portfolio/fullsize/6.jpg">
              <img class="img-fluid" src="img/portfolio/thumbnails/6.jpg" alt="">
              <div class="portfolio-box-caption">
                <div class="portfolio-box-caption-content">
                  <div class="project-category text-faded">
                    Category
                  </div>
                  <div class="project-name">
                    Project Name
                  </div>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>

	<!-- 로그인 했을 때 안했을 때 차이  -->
    <section class="bg-dark text-white">
      <div class="container text-center">
        <h2 class="mb-4">[↑ 기능을 추가한 후에 사진을 넣을 예정..]</h2>
			<a class="btn btn-light btn-xl sr-button" href="#contact"><%=session.getAttribute("userID")%>님 기능을 사용해 보세요!</a>
		</div>
    </section>

    <section id="contact">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto text-center">
            <h2 class="section-heading">문의해주세요!</h2>
            <hr class="my-4">
            <p class="mb-5">어려운 부분이 있나요?? 이곳으로 언제든지 문의해 주세요</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 ml-auto text-center">
            <i class="fa fa-phone fa-3x mb-3 sr-contact"></i>
            <p>관리자 번호</p>
          </div>
          <div class="col-lg-4 mr-auto text-center">
            <i class="fa fa-envelope-o fa-3x mb-3 sr-contact"></i>
            <p>
              <a href="mailto:your-email@your-domain.com">admin@gmail.com</a>
            </p>
          </div>
        </div>
      </div>
    </section>
    
    <%
          	}
          %>
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="vendor/scrollreveal/scrollreveal.min.js"></script>
    <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/creative.min.js"></script>

  </body>

</html>
