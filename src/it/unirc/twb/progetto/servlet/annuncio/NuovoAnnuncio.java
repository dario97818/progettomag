package it.unirc.twb.progetto.servlet.annuncio;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.jasper.tagplugins.jstl.core.Out;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;

/**
 * Servlet implementation class NuovoAnnuncio
 */
@WebServlet("/NuovoAnnuncio")
@MultipartConfig
public class NuovoAnnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NuovoAnnuncio() {
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
		Part fil=request.getPart("foto");
		InputStream f=fil.getInputStream();
		File img;
		if(!fil.getSubmittedFileName().equals(""))
		{
			String nome=fil.getSubmittedFileName();
			File sav=new File(getServletContext().getRealPath("/")+"ImgTmp/"+nome);
			img=new File(getServletContext().getRealPath("/")+"ImgTmp/"+nome);
			Files.copy(f, sav.toPath(), StandardCopyOption.REPLACE_EXISTING);
			BufferedImage image = ImageIO.read(sav);
			BufferedImage resized = resize(image, 640, 480);
			File output = new File(getServletContext().getRealPath("/")+"ImgTmp/"+nome);
			ImageIO.write(resized, "png", output);
		}
		else
			img=null;
		String titolo=request.getParameter("titolo");
		String tipologia=request.getParameter("tipologia");
		String marca=request.getParameter("marca");
		String piano=request.getParameter("piano");
		System.out.println(titolo+"\n"+piano);
		boolean richiesta_piano=false;
		if (piano!=null)
			richiesta_piano=true;
		else
			richiesta_piano=false;





		long prezzo=Long.valueOf(request.getParameter("prezzo"));
		String carburante=request.getParameter("carburante");
		int cilindrata=Integer.valueOf(request.getParameter("cilindrata"));
		int kilometraggio=Integer.valueOf(request.getParameter("km"));
		String modello=request.getParameter("modello");
		int id_u=(int) se.getAttribute("id_utente");


		Annuncio a=new Annuncio();
		a.setTitolo(titolo);
		a.setImg(img);
		a.setTipologia(tipologia);
		a.setMarca(marca);
		a.setPrezzo(prezzo);
		a.setCarburante(carburante);
		a.setCilindrata(cilindrata);
		a.setKm(kilometraggio);
		a.setModello(modello);
		a.setId_utente(id_u);
		AnnuncioDAO adao=new AnnuncioDAO();
		if(adao.salvaAnnuncio(a))
		{
			//img.delete();//elimino il file dalla cartella temporanea
			if (richiesta_piano) {
				request.setAttribute("nuovo_annuncio", "true");//indico alla pagina di creazione del piano che non devo scegliere tra gli annunci ma che sto lavorando sull'annuncio appena inserito
				int id_annuncio=adao.lastInsertAnnuncioFromIdUtente(id_u);

				request.setAttribute("id_annuncio", id_annuncio);	//è l'id dell'annuncio appena inserito
				request.getRequestDispatcher("/Preventivi/CreaPiano.jsp").forward(request, response);
			}
			else
				response.sendRedirect("/index.jsp");
		}
		else
		{
			img.delete();//elimino il file dalla cartella temporanea
			response.sendRedirect("/Annuncio/NuovoAnnuncio.jsp");
		}

	}
	private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

}
