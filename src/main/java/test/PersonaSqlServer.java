/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import datos.Conexion;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ruldin
 */
public class PersonaSqlServer {
    public static void main(String[] args) {
         //definimos la variable conexion
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            //el autocommit por default es true, lo pasamos a false
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaDaoJDBC personaJdbc = new PersonaDaoJDBC(conexion);
            
            PersonaDTO cambioPersona = new PersonaDTO();
            cambioPersona.setId_persona(2);
            cambioPersona.setNombre("Harry");
            cambioPersona.setApellido("Potter");
            cambioPersona.setEmail("Hpotter@mail.com");
            cambioPersona.setTelefono("12345678910");
            personaJdbc.update(cambioPersona);
            
            PersonaDTO nuevaPersona = new PersonaDTO();
            nuevaPersona.setNombre("Rodrigo");
            //nuevaPersona.setApellido("Asturias===============================================");
            nuevaPersona.setApellido("Asturias");
            personaJdbc.insert(nuevaPersona);
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
