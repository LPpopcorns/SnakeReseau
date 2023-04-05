<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Design by foolishdeveloper.com -->
    <title>Profil</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <!--Stylesheet-->
    
</head>
<body>  
	<form method="post" action="profil">
        <h3>Profil</h3>
		<%
			boolean modif = (boolean) request.getAttribute("modif");
			String identifiant = (String) request.getAttribute("identifiant");
			String prenom = (String) request.getAttribute("prenom");
			String nom = (String) request.getAttribute("nom");
			if(modif == true){
				out.println("<label for='identifiant'>Identifiant</label>");
				out.println("<input class='modifier' type='text' id='identifiant' name='identifiant' value='"+ identifiant +"'>");
				out.println("<label for='nom'>Nom</label>");
				out.println("<input class='modifier' type='text' id='nom' name='nom' value='"+ nom +"'>");
				out.println("<label for='prenom'>Prénom</label>");
				out.println("<input class='modifier' type='text' id='prenom' name='prenom' value='"+ prenom +"'>");
			}
			else{
				out.println("<label for='identifiant'>Identifiant</label>");
				out.println("<input class='pasmodifier' type='text' id='identifiant' name='identifiant' value='"+ identifiant +"' disabled>");
				out.println("<label for='nom'>Nom</label>");
				out.println("<input class='pasmodifier' type='text' id='nom' name='nom' value='"+ nom +"' disabled>");
				out.println("<label for='prenom'>Prénom</label>");
				out.println("<input class='pasmodifier' type='text' id='prenom' name='prenom' value='"+ prenom +"' disabled>");
			}
		
		%>
        <!--  <label for="identifiant">Identifiant</label>
        <input type="text" id="identifiant" name="identifiant" value="${ identifiant }" disabled>
        
        <label for="nom">Nom</label>
        <input type="text" id="nom" name="nom" value="${ nom }" disabled>
        
        <label for="prenom">Prénom</label>
        <input type="text" id="prenom" name="prenom" value="${ prenom }" disabled>-->

        <label for="bestScore">Meilleur Score</label>
        <input class="pasmodifier" type="text" id="bestScore" name="bestScore" value="${ bestScore }" disabled>
        
        <label for="lastScore">Dernier Score</label>
        <input class="pasmodifier" type="text" id="lastScore" name="lastScore" value="${ lastScore }" disabled>  
        
        <br> 
        <%
			if(modif == true){
				out.println("<button>Valider</button>");
			}
			else{
				out.println("<button>Modifier</button>");
			}
		
		%>
        
        
        <br>
        <a href="/projetWeb/classement">Classement</a>
   </form>
</body>


<style media="screen">
      *,
*:before,
*:after{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background-color: #080710;
}
.background{
    width: 800px;
    height: 800px;
    position: absolute;
    transform: translate(-50%,-50%);
    left: 50%;
    top: 50%;
}
.background .shape{
    height: 200px;
    width: 200px;
    position: absolute;
    border-radius: 50%;
}
.shape:first-child{
    background: linear-gradient(
        #1845ad,
        #23a2f6
    );
    left: -80px;
    top: -80px;
}
.shape:last-child{
    background: linear-gradient(
        to right,
        #ff512f,
        #f09819
    );
    right: -30px;
    bottom: -80px;
}
form{
    height: 800px;
    width: 800px;
    background-color: rgba(255,255,255,0.13);
    position: absolute;
    transform: translate(-50%,-50%);
    top: 50%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
}
form *{
    font-family: 'Poppins',sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
}
form h3{
    font-size: 32px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
}

label{
    display: block;
    margin-top: 30px;
    font-size: 16px;
    font-weight: 500;
}
input.modifier{
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255,255,255,0.07);
    border-radius: 3px;
    padding: 0 10px;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 300;
}
input.pasmodifier{
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255,255,255,0.02);
    border-radius: 3px;
    padding: 0 10px;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 300;
}
::placeholder{
    color: #e5e5e5;
}
button{
    margin-top: 50px;
    width: 100%;
    background-color: #ffffff;
    color: #080710;
    padding: 15px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
}
.social{
  margin-top: 30px;
  display: flex;
}
.social div{
  background: red;
  width: 150px;
  border-radius: 3px;
  padding: 5px 10px 10px 5px;
  background-color: rgba(255,255,255,0.27);
  color: #eaf0fb;
  text-align: center;
}
.social div:hover{
  background-color: rgba(255,255,255,0.47);
}
.social .fb{
  margin-left: 25px;
}
.social i{
  margin-right: 4px;
}

table{
  border-collapse: collapse;
  margin: auto;
  text-align:center;
}

th, td{
  border: 1px solid white;
  padding: 10px;
}

    </style>
</html>
		


