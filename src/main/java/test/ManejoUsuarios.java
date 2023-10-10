package test;

import datos.Conexion;
import datos.UsuarioDao;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.*;
import java.util.List;

public class ManejoUsuarios {

    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            UsuarioDao usuarioDao = new UsuarioDaoJDBC(conexion);

            UsuarioDTO nuevoUsuario = new UsuarioDTO();
            nuevoUsuario.setUsername("Angelito");
            nuevoUsuario.setPassword("Shinyuu1902");

            int resultado = usuarioDao.insert(nuevoUsuario);

            boolean exito = (resultado > 0);

            if (exito) {
                System.out.println("Se ha agregado un nuevo usuario.");
            } else {
                System.out.println("No se pudo agregar el usuario.");
            }

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
