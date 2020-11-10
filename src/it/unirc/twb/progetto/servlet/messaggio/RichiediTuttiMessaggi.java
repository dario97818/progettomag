package it.unirc.twb.progetto.servlet.messaggio;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Messaggio;
import it.unirc.twb.progetto.been.MessaggioDAO;
import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class RichiediTuttiMessaggi
 */
@WebServlet("/RichiediTuttiMessaggi")
public class RichiediTuttiMessaggi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediTuttiMessaggi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		int idu=(int) se.getAttribute("id_utente");

		Vector<Messaggio> v;
		int id_utente=Integer.valueOf(request.getParameter("id"));
		if(idu==id_utente) {
			MessaggioDAO mdao=new MessaggioDAO();
			Utente u=new Utente();

			u.setId(id_utente);

			v=mdao.getMessaggiUtente(u);
			request.setAttribute("messaggi", v);
			request.getRequestDispatcher("/Messaggio/LeggiMessaggi.jsp").forward(request, response);


		}
		else
			response.sendRedirect("/Errore.jsp");
	}

}
