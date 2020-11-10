package it.unirc.twb.progetto.servlet.admin;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Amministratore;
import it.unirc.twb.progetto.been.AmministratoreDAO;

/**
 * Servlet implementation class RichiediGestisciAdimn
 */
@WebServlet("/RichiediGestisciAdimn")
public class RichiediGestisciAdimn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediGestisciAdimn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth-admin")!=null)
		{
			AmministratoreDAO adao=new AmministratoreDAO();
			Vector<Amministratore> admins=adao.getAmministratori();
			request.setAttribute("admins", admins);
			request.getRequestDispatcher("/Amministratore/MostraAmministratori.jsp").forward(request, response);
		}
		else
			response.sendRedirect("/Errore.jsp");
	}

}
