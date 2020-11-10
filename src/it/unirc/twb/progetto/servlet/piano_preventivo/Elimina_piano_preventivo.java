package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Piano;
import it.unirc.twb.progetto.been.PianoDAO;
import it.unirc.twb.progetto.been.Preventivo;
import it.unirc.twb.progetto.been.PreventivoDAO;
import it.unirc.twb.progetto.been.utils.DBManager;

/**
 * Servlet implementation class Elimina_piano_preventivo
 */
@WebServlet("/Elimina_piano_preventivo")
public class Elimina_piano_preventivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Elimina_piano_preventivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se =request.getSession();
		int id_u=(int) se.getAttribute("id_utente");

		if (request.getParameter("piano").equals("0")) {
			response.getWriter().append("VUOI ELIMINARE IL PREVENTIVO CON ID: "+request.getParameter("id"));
			Preventivo p=new Preventivo();
			p.setId_Preventivo(Integer.valueOf(request.getParameter("id")));
			PreventivoDAO preventivoDAO=new PreventivoDAO();
			p=preventivoDAO.getPreventivo(p);
			if(id_u==p.getId_Richiedente())
			{
				if (preventivoDAO.deletePreventivo(p))
					response.getWriter().append("ELIMINAZIONE AVVENUTA CORRETTAMENTE");
				else
					response.getWriter().append("ELIMINAZIONE FALLITA");
			}
			else
				response.sendRedirect("/Errore.jsp");

		}



		//PARTE RELATIVA AL PIANO
		if (request.getParameter("piano").equals("1")) {
			response.getWriter().append("VUOI ELIMINARE IL PIANO CON ID: "+request.getParameter("id"));
			Piano p=new Piano();
			p.setId_Piano(Integer.valueOf(request.getParameter("id")));
			PianoDAO pianoDAO=new PianoDAO();
			p=pianoDAO.getPiano(p);
			AnnuncioDAO adao=new AnnuncioDAO();
			Annuncio a=new Annuncio();
			a.setId_annuncio(p.getId_annuncio());
			a=adao.getAnnuncio(a);
			if(id_u==a.getId_utente()) {
				if (pianoDAO.deletePiano(p))
					response.getWriter().append("ELIMINAZIONE AVVENUTA CORRETTAMENTE");
				else
					response.getWriter().append("ELIMINAZIONE FALLITA");
			}
			else
				response.sendRedirect("/Errore.jsp");
		}

		request.getRequestDispatcher("/RichiestaGestionePreventivi").forward(request, response);




	}

}
