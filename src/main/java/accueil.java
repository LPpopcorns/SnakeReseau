import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.varglobale.*;
import com.octest.bdd.Utilisateur;
import com.octest.beans.User;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;
import com.octest.dao.UtilisateurDaoImpl;
import com.octest.forms.ConnexionForms;


/**
 * Servlet implementation class accueil
 */
@WebServlet("/accueil")
public class accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public profil profil;
	private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
    	varglobale compte = new varglobale();
        getServletContext().setAttribute("varglobale", compte);
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.profil = new profil();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        String sId = request.getParameter("id");
        String sMdp = request.getParameter("mdp"); 
        System.out.println(sId);
        System.out.println(sMdp);
        User user = utilisateurDao.VerifCompte(sId, sMdp);
		varglobale compteConnect = (varglobale) getServletContext().getAttribute("varglobale");
        compteConnect.compteConnect = user;
        compteConnect.modification = false;
        getServletContext().setAttribute("varglobale", compteConnect);
        if(user.IDENTIFIANT != null && user.MDP != null) {
        	//request.setAttribute("compteConnect" , user);
        	this.getServletContext().getRequestDispatcher("/classement").forward(request, response);
        	request.setAttribute("res", "");
        	request.setAttribute(user.IDENTIFIANT, "identifiant");
        	request.setAttribute(user.MDP, "motdepasse");
        }
        else {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
        	request.setAttribute("res", "Erreur de connexion !");
        }
	}

}
