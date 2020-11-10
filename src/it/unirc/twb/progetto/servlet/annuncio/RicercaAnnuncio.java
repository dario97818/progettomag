package it.unirc.twb.progetto.servlet.annuncio;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Utente;

/**
 * Servlet implementation class RicercaAnnuncio
 */
@WebServlet("/RicercaAnnuncio")
public class RicercaAnnuncio extends HttpServlet {
	AnnuncioDAO aDAO=new AnnuncioDAO();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RicercaAnnuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append(request.getParameter("tipologia")+" "+ request.getParameter("name"));


		HttpSession se=request.getSession();
		Annuncio a =new Annuncio();
		Vector<Annuncio> v;
		if(request.getParameter("id")!=null)
		{
			a.setId_utente(Integer.valueOf(request.getParameter("id")));
			v =aDAO.getAnnunciUtente(a);
		}
		else
		{
			String titolo= request.getParameter("titolo");
			a.setTitolo(titolo);
			if(request.getParameter("tipologia").equals("tutti"))
			{
				a.setTipologia("");
			}
			else
				a.setTipologia(request.getParameter("tipologia"));
			//parte di ricerca
			v =aDAO.ricercaAnnuncio(a);
	
		}
		
		request.setAttribute("risultatiRicerca", v);
		request.getRequestDispatcher("/Annuncio/RisultatiRicerca.jsp").forward(request, response);
		//parte di restituzione della ricerCA AD UNA jsp con una tabella



	}

	

}
