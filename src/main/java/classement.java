

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.User;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;
import com.octest.varglobale.varglobale;

/**
 * Servlet implementation class classement
 */
@WebServlet("/classement")
public class classement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		varglobale compteConnect = (varglobale) getServletContext().getAttribute("varglobale");
        User compteActif = compteConnect.compteConnect;
		System.out.println(request.getAttribute("identifiant"));
		var classement = utilisateurDao.RecupClassement();
        String[] pseudo = new String[classement.size()];
        int[] scoreMax = new int[classement.size()];
        for(int i=0;i<classement.size();i++) {
        	User user = classement.get(i);
        	if(user.BEST_SCORE == compteActif.BEST_SCORE) {
        		request.setAttribute("positionClassement", i);
        		request.setAttribute("bestScore", compteActif.BEST_SCORE);
        	}
        	pseudo[i] = user.IDENTIFIANT;
        	scoreMax[i] = user.BEST_SCORE;
        }
		request.setAttribute("scoreMax", scoreMax);
		request.setAttribute("pseudo", pseudo);
		this.getServletContext().getRequestDispatcher("/WEB-INF/classement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
