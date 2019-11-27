package com.excercise35crudstatement.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que obtiene todos los listados de productos
 * @autor Uriel MC.
 * <p>Este es el listado de los productos de la base de datos</p>
 */
@WebServlet("/ReadGeneralServlet")
public class ReadGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadGeneralServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. declaramos variables
		String urlServer= "jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String userName = "root";
		String pass = "admin";
		
		String sentenciaSQL="select * from productos";
		
		//2. declaramos objetos
		Connection conn= null;
		Statement stmnt= null;
		ResultSet rs= null;
		
		
		try 
		{
			//3.instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			//4.abrimos la conexion
			conn =DriverManager.getConnection(urlServer,userName,pass);
			
			//5. preparamos statement
			stmnt = conn.createStatement();
			
			//6. ejecutamos sentencia sql
			rs = stmnt.executeQuery(sentenciaSQL);
			
			while(rs.next())
			{
				//7.procesamos la salida
				response.getWriter().append("<p>");
				response.getWriter().append("id Producto: "+ rs.getInt(1));
				response.getWriter().append("<br/>");
				response.getWriter().append("nombre Producto: "+ rs.getString("nombreProducto"));
				response.getWriter().append("<br/>");
				response.getWriter().append("precio Producto: "+ rs.getDouble("precioProducto"));
				response.getWriter().append("</p>");
			}
			
			
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		
		{
			try {
				//8.cerramos la conexion
				
				rs.close();
				stmnt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
