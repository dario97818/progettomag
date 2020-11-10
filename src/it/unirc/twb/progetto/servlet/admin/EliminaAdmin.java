package it.unirc.twb.progetto.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Amministratore;
import it.unirc.twb.progetto.been.AmministratoreDAO;

/**
 * Servlet implementation class EliminaAdmin
 */
@WebServlet("/EliminaAdmin")
public class EliminaAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		if(session.getAttribute("auth-admin")!=null)
		{
			AmministratoreDAO adao=new AmministratoreDAO();
			Amministratore a=new Amministratore();
			a.setId_amministratore(Integer.valueOf(request.getParameter("id")));
			if(adao.Delete(a))
			{
				response.sendRedirect("/RichiediGestisciAdimn");
			}
		}
		else
			response.sendRedirect("/Errore.jsp");
	}

}
