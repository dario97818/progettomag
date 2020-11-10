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
 * Servlet implementation class InviaMessaggio
 */
@WebServlet("/InviaMessaggio")
public class InviaMessaggio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InviaMessaggio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		int id_mittente=(int) se.getAttribute("id_utente");
		int id_a=Integer.valueOf(request.getParameter("id_annuncio"));
		int id_d=Integer.valueOf(request.getParameter("id_destinatario"));
		String messaggio=request.getParameter("messaggio");
		Annuncio a=new Annuncio();
		a.setId_annuncio(id_a);
		AnnuncioDAO adao=new AnnuncioDAO();
		a=adao.getAnnuncio(a);
		Messaggio m=new Messaggio();
		m.setId_annuncio(id_a);
		m.setId_destinatatio(id_d);
		m.setMessaggio(messaggio);
		m.setId_mittente(id_mittente);
		MessaggioDAO mdao=new MessaggioDAO();
		if(id_d==id_mittente)
		{
			response.sendRedirect("/Errore.jsp");
		}
		else
		{
			if(mdao.NuovoMessaggio(m))
			{
				response.sendRedirect("/index.jsp");
			}
			else
				response.sendRedirect("/Errore.jsp");
		}
	}

}
