package it.unirc.twb.progetto.servlet.messaggio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Messaggio;
import it.unirc.twb.progetto.been.MessaggioDAO;

/**
 * Servlet implementation class SegnaComeLetto
 */
@WebServlet("/SegnaComeLetto")
public class SegnaComeLetto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnaComeLetto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		int id_m=Integer.valueOf(request.getParameter("id_m"));
		int id_u=(int)se.getAttribute("id_utente");
		int idu=(int) se.getAttribute("id_utente");
		if(idu==id_u) {
		Messaggio m=new Messaggio();
		m.setId_messaggio(id_m);
		MessaggioDAO mdao=new MessaggioDAO();
		m=mdao.getMessaggio(m);
		if(id_u==m.getId_destinatatio())
		{
			
			if(mdao.SegnaLetto(m))
			{
				
				response.sendRedirect("/index.jsp");
			}
		}
		
		
	
	}
	else
		response.sendRedirect("/index.jsp");
	}
}
