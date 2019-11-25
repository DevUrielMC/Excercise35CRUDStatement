package com.excercise35crudstatement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Clase servlet 
 * @author URIEL-PC
 *
 *@since 1.0
 
 *
 *@see <a href="https://github.com/">Esta pagona tiene la informacion que necesité</a>
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Metodo doPost 
     * @param request Este parametro me sirve para recibir los datos del cliente
     * @param response Este parato me sirve para enviar los datos al cliente
     * 
     * @return el objeto response con la respuesta a la base de datos
     */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
