<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.unirc.twb.progetto.been.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.nio.file.Files"%>
<%@ page import="java.nio.file.StandardCopyOption"%>
<!DOCTYPE HTML>
<!--
	Industrious by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<head>
<%@ include file="/misc/auth.txt"%>
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
		Vector<Messaggio> messaggi = (Vector<Messaggio>) request.getAttribute("messaggi");
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



			<%
					for (Messaggio m : messaggi) {
						AnnuncioDAO adao=new AnnuncioDAO();
						Annuncio a=new Annuncio();
						a.setId_annuncio(m.getId_annuncio());
						a=adao.getAnnuncio(a);
						Utente u=new Utente();
						UtenteDAO udao=new UtenteDAO();
						u.setId(m.getId_mittente());
						u=udao.getUtente(u);
						
				%>
			<table class="alt"
				style="margin-left: auto; margin-right: auto; table-layout: fixed;">
				<tbody>
					<tr>
						
						<td colspan="3" style="text-align: center;"><a
							href="/RichiediVisualizzaAnnuncio?id=<%=a.getId_annuncio()%>&id_utente=<%=a.getId_utente()%>"><%=a.getTitolo()%></a></td>
					</tr>
					<tr >
						<td colspan="3" style="text-align: center;">Mittente:<br> <%=u.getCognome()+" "+u.getNome()%></td>
					</tr>
					<tr>
						<td colspan="2" style="word-wrap:break-word"><%=m.getMessaggio()%></td>
						
						<td><a href="/RichiediMessaggio?id_a=<%= a.getId_annuncio()%>&id_d=<%=m.getId_mittente()%>&id_m=<%=m.getId_messaggio()%>">Rispondi</a><br> 
						<%if(!m.isLetto()) {%>
						<a href="/SegnaComeLetto?id_m=<%=m.getId_messaggio()%>">Segna come letto</a><br>
						<%} %>
						</td>
					</tr>
				</tbody>
				<%
						}
						
					%>
			</table>
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