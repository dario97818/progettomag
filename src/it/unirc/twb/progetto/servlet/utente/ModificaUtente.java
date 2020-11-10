package it.unirc.twb.progetto.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class ModificaUtente
 */
@WebServlet("/ModificaUtente")
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaUtente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		int idu=Integer.valueOf(request.getParameter("id"));
		Utente ut=new Utente();
		ut.setId(idu);
		UtenteDAO udao=new UtenteDAO();
		ut=udao.getUtente(ut);
		ut.setPass("");
		if(se.getAttribute("auth-admin")==null)
		{
			int p=(int)se.getAttribute("id_utente");
			if(idu==p)
			{
				request.setAttribute("utente", ut);
				request.getRequestDispatcher("/Utente/ModificaUtente.jsp").forward(request, response);
			}
			else
				response.sendRedirect("/index.jsp");
		}
		else
		{
			request.setAttribute("utente", ut);
			request.getRequestDispatcher("/Utente/ModificaUtente.jsp").forward(request, response);

		}
	}

}
