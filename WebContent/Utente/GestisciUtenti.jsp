<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.unirc.twb.progetto.been.*"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE HTML>
<!--
	Industrious by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<head>
<title>Pongo Car</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/assets/css/main.css" />
</head>
<body class="is-preload">
<%
HttpSession se=request.getSession();
			if(se.getAttribute("auth-admin")==null)
				response.sendRedirect("/index.jsp");
%>
	<%
		Vector<Utente> utenti = (Vector<Utente>) request.getAttribute("utenti");
			AnnuncioDAO aDAO =new AnnuncioDAO();
	%>
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

	<!-- Banner -->
	<section id="banner">
		<div class="inner">
			<h1>Pongo Car</h1>
			<%@ include file="/misc/CampoRicercaAnnuncio.txt"%>
			 
		</div>
		<video autoplay loop muted playsinline src="/images/banner.mp4"></video>
	</section>

	<!-- Highlights -->
	<section class="wrapper">
		<div class="inner">
		
			<div class="highlights">
				<section>
				
				
				<table class="alt">
								<thead>
									<tr>
										<th>ID</th>
										<th>Nome</th>
										<th>Cognome</th>
										<th>E-mail</th>
										<th>PW</th>
										<th>Idirizzo</th>
										<th>STATO</th>
										<th>n. Annunci</th>
										<th>Modifica</th>
									</tr>
								</thead>
								<tbody>
									
									<%for (Utente u : utenti) {%>
									<tr>
										<td><%=u.getId()%></td>
										<td><%=u.getNome()%></td>
										<td><%=u.getCognome()%></td>
										<td><%=u.getEmail()%></td>
										<td><%=u.getPass()%></td>
										<td><%=u.getIndirizzo()%></td>
										<td><%=u.getAttivo()%></td>
										<td><%=aDAO.getAnnunciUtente(u.getId())%></td>
										<td><a href="/ModificaUtente?id=<%=u.getId()%>">Modifica</a></td>		
									</tr>
									<%} %>
									
								</tbody>
								
							</table>
				</section>
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