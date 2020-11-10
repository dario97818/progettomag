<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<!--
	Industrious by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<head>

<%
	if (session.getAttribute("auth") != null) {
		response.sendRedirect("/index.jsp");
	}
%>
<%
	if (request.getAttribute("errore") != null) {
%>
<script type="text/javascript">
	alert("<%=request.getAttribute("errore")%>
	");
</script>

<%
	//cook.setMaxAge(0); // elimino il cookie appena mostra il messaggio 
	}
%>
<title>Registrazione</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/assets/css/main.css" />
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a class="logo" href="index.jspl">Pongo</a>
		<nav>
			<a href="#menu">Menu</a>
		</nav>
	</header>

	<!-- Nav -->
	<nav id="menu">
		<%@ include file="/misc/menu.txt"%>
	</nav>

	<!-- Heading -->
	<div id="heading">
		<h1>Registrazione</h1>
	</div>

	<!-- Main -->
	<section id="main" class="wrapper">
		<div class="inner">
			<div class="content">
				<form method="post" action="/Registrazione">
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							Nome <input type="text" name="name" id="name" value=""
								placeholder="Nome" required />
						</div>
						<div class="col-6 col-12-xsmall">
							Cognome <input type="text" name="cognome" id="cognome" value=""
								placeholder="Cognome" required />
						</div>
						<div class="col-6 col-12-xsmall">
							E-mail <input type="email" name="email" id="email" value=""
								placeholder="Email" required />
						</div>
						<div class="col-6 col-12-xsmall">
							password <input type="password" name="pwd" id="pwd" value=""
								placeholder="Password" required />
						</div>
						<div class="col-6 col-12-xsmall">
							Telefono <input type="text" name="tel" id="tel" value=""
								placeholder="Telefono" pattern="[0-9]+" required />
						</div>
						<div class="col-6 col-12-xsmall">
							Indirizzo <input type="text" name="ind" id="ind" value=""
								placeholder="Indirizzo" required />
						</div>
						<div class="col-6 col-12-xsmall">
							Data Di Nascita <input type="date" name="data" id="data" value=""
								placeholder="Data di Nascita" required />
						</div>
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="Registrati" class="primary" /></li>
								<li><input type="reset" value="Reset" /></li>
							</ul>
						</div>

					</div>
				</form>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="content">
				<section>
					<h3>Accumsan montes viverra</h3>
					<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing
						gravida odio porttitor sem non mi integer non faucibus ornare mi
						ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien
						lorem accumsan varius montes viverra nibh in adipiscing. Lorem
						ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum.
						Blandit adipiscing eu felis iaculis volutpat ac adipiscing sed
						feugiat eu faucibus. Integer ac sed amet praesent. Nunc lacinia
						ante nunc ac gravida.</p>
				</section>
				<section>
					<h4>Sem turpis amet semper</h4>
					<ul class="alt">
						<li><a href="#">Dolor pulvinar sed etiam.</a></li>
						<li><a href="#">Etiam vel lorem sed amet.</a></li>
						<li><a href="#">Felis enim feugiat viverra.</a></li>
						<li><a href="#">Dolor pulvinar magna etiam.</a></li>
					</ul>
				</section>
				<section>
					<h4>Magna sed ipsum</h4>
					<ul class="plain">
						<li><a href="#"><i class="icon fa-twitter">&nbsp;</i>Twitter</a></li>
						<li><a href="#"><i class="icon fa-facebook">&nbsp;</i>Facebook</a></li>
						<li><a href="#"><i class="icon fa-instagram">&nbsp;</i>Instagram</a></li>
						<li><a href="#"><i class="icon fa-github">&nbsp;</i>Github</a></li>
					</ul>
				</section>
			</div>
			<div class="copyright">
				&copy; Untitled. Photos <a href="https://unsplash.co">Unsplash</a>,
				Video <a href="https://coverr.co">Coverr</a>.
			</div>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>