package main;

import clases.Conexion;

public class TwitterMain {
	public static void main(String[] args) {
		Conexion BD = new Conexion();
		BD.enableConnection();
		BD.crearBD("Twitter");
		BD.crearTabla("Twitter", "Usuarios", "Tweet");
		BD.crearInserts("Twitter");
		BD.getValues("Twitter", "Usuarios");
		BD.closeConnection();
	}

}
