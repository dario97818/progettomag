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
<%@ include file="/misc/auth.txt"%>
<%
	AnnuncioDAO aDAO=new AnnuncioDAO();
	String stringa=(String)request.getAttribute("nuovo_annuncio");
	boolean nuovo_annuncio=Boolean.valueOf(stringa); 
	
	int id=0;
	
	
	
%>

<%
	HttpSession se = request.getSession();
	int id_u=(int) se.getAttribute("id_utente");
	Vector<Annuncio> annunci=null;
	if(nuovo_annuncio)
		id=(int) request.getAttribute("id_annuncio") ;
	else{
		Utente u=new Utente();
		u.setId(id_u);
		annunci=aDAO.getAnnunciFomIdUtente(u);
	}
		

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
				<form name="Annuncio" method="get" action="/NuovoPiano"
					enctype="multipart/form-data">





					<div class="row gtr-uniform">


						<div class="col-12">
							Percentuale di anticipo<select name="percentuale"
								id="percentuale">
								<option value="10">10%</option>
								<option value="20">20%</option>
								<option value="30">30%</option>
								<option value="40">40%</option>
								<option value="50">50%</option>
							</select>
						</div>
						<div class="col-12">
							Numero massimo di mesi consentiti<select name="max_mesi"
								id="max_mesi">
								<option value="6">6</option>
								<option value="12">12</option>
								<option value="18">18</option>
								<option value="24">24</option>
							</select>
						</div>
						<%if (nuovo_annuncio) {%>
						<%=id%>
						<input type="hidden" name="id" name="id" value="<%=id%>">
						<%} %>
						<%if(nuovo_annuncio==false){	%>
						<div class="col-12">
							Scegli il tuo annuncio a cui vuoi associare il Piano<select name="id_annuncio" id="id_annuncio">
							<% for(Annuncio a:annunci) {%>
								<option value="<%= a.getId_annuncio()%>"> TITOLO: <%= a.getTitolo() %>| PREZZO: <%=a.getPrezzo() %></option>
							<%} %>
							</select>
						</div>
						VUOI VEDERE TUTTI GLI ANNUNCI
						
						<%} %>


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