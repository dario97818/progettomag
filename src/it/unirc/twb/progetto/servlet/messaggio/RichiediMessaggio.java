package it.unirc.twb.progetto.servlet.messaggio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Messaggio;
import it.unirc.twb.progetto.been.MessaggioDAO;

/**
 * Servlet implementation class RichiediMessaggio
 */
@WebServlet("/RichiediMessaggio")
public class RichiediMessaggio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediMessaggio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		int id_a=Integer.valueOf(request.getParameter("id_a"));
		int id_d=Integer.valueOf(request.getParameter("id_d"));
		int id_mes=Integer.valueOf(request.getParameter("id_m"));
		int id_m=(int) se.getAttribute("id_utente");
		Messaggio m=new Messaggio();
		Messaggio me=new Messaggio();
		me.setId_messaggio(id_mes);//messaggio vecchio
		m.setId_annuncio(id_a);
		m.setId_destinatatio(id_d);
		m.setId_mittente(id_m);
		if(id_d==id_m)
		{
			response.sendRedirect("/Errore.jsp");
		}
		else
		{
			MessaggioDAO mdao= new MessaggioDAO();
			mdao.SegnaLetto(me);
			request.setAttribute("messaggio", m);
			request.getRequestDispatcher("/Messaggio/Messaggio.jsp").forward(request, response);
		}
	}

}
