<%@page import="java.util.function.Function"%>
<%@page import="javafx.scene.control.Alert"%>
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
<%@ include file="/misc/auth.txt"%>
<%
	HttpSession se = request.getSession();
%>
<title>Nuovo Annuncio</title>
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
		<a class="logo" href="/index.jsp">Pongo</a>
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
		<h1>Nuovo Annuncio</h1>
	</div>

	<!-- Main -->
	<section id="main" class="wrapper">
		<div class="inner">
			<div class="content">
				<form name="Annuncio" method="post" action="/NuovoAnnuncio"
					enctype="multipart/form-data">
					<div class="row gtr-uniform">
						
						<div class="col-6 col-12-xsmall">
							Titolo <input type="text" name="titolo" id="titolo" value=""
								placeholder="Titolo" />
						</div>
						<div class="col-12">
							<select name="tipologia" id="tipologia">
								<option value="">- Select -</option>
								<option value="berlina">Berlina</option>
								<option value="due_volumi">Due volumi</option>
								<option value="monovolume">Monovolume</option>
								<option value="familiare">Familiare (o giardinetta o
									"station wagon")</option>
								<option value="coupe">Coupé</option>
								<option value="decappottabile">Decappottabile</option>
								<option value="spyder">Spyder (Roadster)</option>
								<option value="veicolo_multiuso">Veicolo multiuso (o
									monovolume grande)</option>
								<option value="altro">Altro</option>
							</select>
						</div>
						<div class="col-6 col-12-xsmall">
							Marca <input type="text" name="marca" id="marca" value=""
								placeholder="Marca" />
						</div>
						<div class="col-6 col-12-xsmall">
							Modello <input type="text" name="modello" id="modello" value=""
								placeholder="modello" />
						</div>
						<div class="col-6 col-12-xsmall">
							Prezzo <input type="text" name="prezzo" id="prezzo" value=""
								placeholder="Prezzo" pattern="[0-9]+" />
						</div>
						<div class="col-6 col-12-xsmall">
							Carburante <input type="text" name="carburante" id="carburante"
								value="" placeholder="Carburante" />
						</div>
						<div class="col-6 col-12-xsmall">
							Cilindrata <input type="text" name="cilindrata" id="cilindrata"
								value="" placeholder="Cilindrata" pattern="[0-9]+" />
						</div>
						<div class="col-6 col-12-xsmall">
							Kilometraggio <input type="text" name="km" id="km" value=""
								placeholder="Kilometraggio" pattern="[0-9]+" />
						</div>
						<div class="col-6 col-12-small">
									<input type="checkbox" id="piano" name="piano">
									<label for="piano">Crea un Piano Preventivo</label>
								</div>
						<div class="col-6 col-12-xsmall">
							Foto <input type="file" name="foto"
								accept="image/x-png,image/gif,image/jpeg">
						</div>
						<div class="col-12">
							<ul class="actions">

								<li><input type="submit" value="Crea" class="primary" /></li>

								
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
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/browser.min.js"></script>
	<script src="/assets/js/breakpoints.min.js"></script>
	<script src="/assets/js/util.js"></script>
	<script src="/assets/js/main.js"></script>

</body>
</html>