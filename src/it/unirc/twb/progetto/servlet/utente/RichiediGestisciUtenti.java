package it.unirc.twb.progetto.servlet.utente;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class RichiediGestisciUtenti
 */
@WebServlet("/RichiediGestisciUtenti")
public class RichiediGestisciUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediGestisciUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		UtenteDAO uDAO =new UtenteDAO();
		if(se.getAttribute("auth-admin")==null)
		{
			response.sendRedirect("/index.jsp");
		}
		Vector<Utente> listaUtenti=uDAO.getUtenti();
		request.setAttribute("utenti", listaUtenti);
		request.getRequestDispatcher("/Utente/GestisciUtenti.jsp").forward(request, response);
	}
     
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
