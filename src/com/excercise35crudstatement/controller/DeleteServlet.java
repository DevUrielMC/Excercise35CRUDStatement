package com.excercise35crudstatement.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excercise35crudstatement.model.Products;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se añade una configuracion para que pueda leer Ñ
		response.setContentType("text/html charset='utf-8'");
		//objeto para que pueda leer
		PrintWriter output = response.getWriter();
		
		//1. Declaramos variables
		Products myProduct = new Products();
		myProduct.setIdProduct(Integer.parseInt(request.getParameter("txtIdProduct")));
		
		String urlServer="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="admin";
		String sentenciaSQL="DELETE FROM productos WHERE idProducto="+myProduct.getIdProduct();
		int rowsAffected=0;
		
		//2. Declaramos objetos
		Connection conn = null;
		Statement stmnt = null;
		
		try
		{
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//4. Abrimos la conexión
			conn = DriverManager.getConnection(urlServer, username, pass);
			//5. Preparamos el statement
			stmnt = conn.createStatement();
			
			//6. Ejecutamos la sentencia sql
			rowsAffected = stmnt.executeUpdate(sentenciaSQL);
			
			//7. Procesamos los datos
			if(rowsAffected>0)
			{
				output.append("Registro borrado con éxito!!!");
			}
			else
			{
				output.append("Registro NO Encontrado!!!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				stmnt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//8. Cerramos la conexión
		output.close();
	}

}