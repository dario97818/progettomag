<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.unirc.twb.progetto.been.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.nio.file.Files"%>
<%@ page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.util.function.Function"%>
<%@page import="javafx.scene.control.Alert"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/basi2/js/funzioni.js"></script>
<%@ include file="/misc/auth.txt"%>
<%
	PreventivoDAO pDAO=new PreventivoDAO();
	HttpSession se = request.getSession();
	int id_u=(int) se.getAttribute("id_utente");
	Piano piano =(Piano) request.getAttribute("piano") ;
	float prezzo_bloccato=(float)request.getAttribute("prezzo_bloccato") ;
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+piano.getMax_mesi());
	
		

%>
<title>Creazione Piano Preventivo</title>
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
		<h1>Pannello creazione Piano Preventivo</h1>
	</div>

	<!-- Main -->
	<section id="main" class="wrapper">
		<div class="inner">
			<div class="content">
				<form name="Preventivo" method="post" action="/NuovoPreventivo">





					<div class="row gtr-uniform">


						<div class="col-6">
							Percentuale di anticipo: <h2><%=piano.getPerc_anticipo() %>%</h2>
						</div>
						<div class="col-6">
							Prezzo bloccato: <h2 style="color:red;"><%=prezzo_bloccato %>E</h2>
						</div>
						<div class="col-12">
								In quanti mesi?<select name="max_mesi" id="max_mesi"
								id="max_mesi">
								<%for(int i=6;i<=piano.getMax_mesi();i+=6) {%>
								<option value="<%=i %>"><%=i %></option>
								<%} %>
							</select>
						</div>
						<div>
						 <input type="hidden" id="prezzo_bloccato" name="prezzo_bloccato" value="<%= prezzo_bloccato%>">
						 <input type="hidden" id="id_piano" name="id_piano" value="<%= piano.getId_Piano()%>">
						
						
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