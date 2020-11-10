package it.unirc.twb.progetto.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;

/**
 * Servlet implementation class Test2
 */
@WebServlet("/Test2")
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Annuncio a=new Annuncio();
		AnnuncioDAO adao=new AnnuncioDAO();
		a.setId_annuncio(8);
		a=adao.getAnnuncio(a);
		InputStream inputstream = new FileInputStream(a.getImg().getAbsolutePath());
		
		 File directory = new File(getServletContext().getRealPath("/")+"ImgTmp/");
		    if (! directory.exists()){
		        directory.mkdir();
		        
		    }
		
		File sav=new File(getServletContext().getRealPath("/")+"ImgTmp/"+a.getId_annuncio()+"_"+a.getId_utente()+".png");
		Files.copy(inputstream, sav.toPath(),StandardCopyOption.REPLACE_EXISTING);
		a.Pulisci();
		BufferedImage image = ImageIO.read(sav);
		ImageIO.write(image, "PNG", response.getOutputStream());
		
		//da utilizzare per eliminare il filedopo l'uso
		//sav.delete();
	}

}
