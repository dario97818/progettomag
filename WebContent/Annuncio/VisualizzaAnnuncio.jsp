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
		HttpSession se = request.getSession();
		int id_piano=(int) request.getAttribute("id_piano");
			if (id_piano!=0)
			System.out.println("IOOOOOOOOOOOO VEDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		Annuncio annuncio = (Annuncio) request.getAttribute("risultato");
		Utente venditore = (Utente) request.getAttribute("venditore");
		if (!venditore.getAttivo())
			response.sendRedirect("/index.jsp");
		if (annuncio.getImg() != null) {
			InputStream inputstream = new FileInputStream(annuncio.getImg().getAbsolutePath());

			File directory = new File(getServletContext().getRealPath("/") + "ImgTmp/");
			if (!directory.exists()) {
				directory.mkdir();

			}

			File sav = new File(getServletContext().getRealPath("/") + "ImgTmp/" + annuncio.getId_annuncio() + "_"
					+ annuncio.getId_utente() + ".png");
			Files.copy(inputstream, sav.toPath(), StandardCopyOption.REPLACE_EXISTING);
			inputstream.close();
			annuncio.Pulisci();
		} else {
			InputStream inputstream = new FileInputStream(
					getServletContext().getRealPath("/") + "ImgTmp/vuoto.jpg");
			File sav = new File(getServletContext().getRealPath("/") + "ImgTmp/" + annuncio.getId_annuncio() + "_"
					+ annuncio.getId_utente() + ".png");
			Files.copy(inputstream, sav.toPath(), StandardCopyOption.REPLACE_EXISTING);
			inputstream.close();
		}
		
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
			<header class="special">
				<h2>
					<p><%=annuncio.getTitolo()%></p>
				</h2>
			</header>

			<table class="alt" style="margin-left: auto; margin-right: auto;">
				<tbody>
					<tr style="height: 68px;">
						<td style="width: 127.2px; height: 68px; text-align: center;"
							colspan="4"><img width="320px"
							src="/ImgTmp/<%=annuncio.getId_annuncio() + "_" + annuncio.getId_utente() + ".png"%>"></td>
					</tr>
					<tr style="height: 45px;">
						<td style="width: 127.2px; height: 45px;"><%=annuncio.getMarca()%></td>
						<td style="width: 128px; height: 45px;"><%=annuncio.getModello()%></td>
						<td style="width: 128px; height: 45px;"><%=annuncio.getTipologia()%></td>
						<td style="width: 128px; height: 45px;"><%=annuncio.getCatburante()%></td>
					</tr>
					<tr style="height: 53.4px;">
						<td style="width: 127.2px; height: 53.4px;"><%=annuncio.getCilindrata()%></td>
						<td style="width: 128px; height: 53.4px;"><%=annuncio.getData_inserimento().toString()%></td>
						<td style="width: 128px; height: 53.4px;"><%=venditore.getNome()%>
							<%=venditore.getCognome()%></td>
						<td style="width: 128px; height: 53.4px;"><%=venditore.getIndirizzo()%></td>
					</tr>
					<tr style="height: 53.4px;">
						<td style="width: 127.2px; height: 53.4px; text-align: center;"
							colspan="4"><%=annuncio.getPrezzoLong()%></td>
					</tr>
				</tbody>

			</table>
			
			
			<%
				if ((se.getAttribute("auth") != null) && ((int)se.getAttribute("id_utente") != annuncio.getId_utente() )) {
			%>
			<ul class="actions ">
				<li><a
					href="/RichiediMessaggio?id_a=<%=annuncio.getId_annuncio()%>&id_d=<%=annuncio.getId_utente()%>&id_m=<%=0%>"
					class="button primary fit small">Contatta Venditore</a></li>
			</ul>
				<%
				if (id_piano!=0) {
				%>
				<ul class="actions ">
					<li><a
						href="/RichiestaPreventivo?id_piano=<%=id_piano%>"
						class="button primary fit small">Blocca prezzo e richiedi preventivo!</a></li>
				</ul>
				<%} %>
			<%} %>
			<%
				if (se.getAttribute("auth-admin") == null && se.getAttribute("auth") == null) {
			%>
			<ul class="actions ">
				<li><h3>Acccedi per avere informazioni sul venditore.</h3>
			</ul>
			<%} %>
			
			
			
			
			
			
			<%
				if (se.getAttribute("auth-admin") != null) {
			%>
			<ul class="actions ">
				<li><a
					href="/EliminaAnnuncio?id=<%=annuncio.getId_annuncio()%>"
					class="button primary fit small">Elimina</a></li>
			</ul>


			<%
				} else {
					if (se.getAttribute("id_utente") != null) {
						if ((int) se.getAttribute("id_utente") == annuncio.getId_utente()) {
			%>
			<ul class="actions ">
				<li><a
					href="/EliminaAnnuncio?id=<%=annuncio.getId_annuncio()%>"
					class="button primary fit small">Elimina</a></li>
			</ul>

			<%
				}

					}
				}
			%>
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