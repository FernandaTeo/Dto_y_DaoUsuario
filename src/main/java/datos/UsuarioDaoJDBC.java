package datos;

import domain.UsuarioDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conexionTransaccional;

    // Sentencias SQL para CRUD
    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username=?, password=? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";

    // Constructor predeterminado
    public UsuarioDaoJDBC() {

    }

    // Constructor con conexión transaccional (para transacciones)
    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método para seleccionar todos los usuarios de la base de datos
    public List<UsuarioDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        try {
            // Obtener la conexión de la transacción si está presente, de lo contrario, obtener una nueva conexión

            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new UsuarioDTO();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);

                usuarios.add(usuario);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            // Cerrar la conexión solo si no se proporciona una conexión transaccional
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return usuarios;
    }

    // Método para insertar un nuevo usuario en la base de datos
    public int insert(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }
    // Método para actualizar un usuario en la base de datos

    public int update(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_usuario());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);
        } finally {
            Conexion.close(stmt);
            // Cerrar la conexión solo si no se proporciona una conexión transaccional

            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    public int delete(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } finally {
            Conexion.close(stmt);
            // Cerrar la conexión solo si no se proporciona una conexión transaccional

            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    @Override
    public UsuarioDTO buscarPorUsername(String username) {

        return null;
    }

    @Override
    public boolean validarCredenciales(String username, String password) {
        UsuarioDTO usuario = buscarPorUsername(username);
        if (usuario != null) {
            if (usuario.getPassword().equals(password)) {
                return BCrypt.checkpw(password, usuario.getPassword());
            }
        }
        return false;
    }

    @Override
    public boolean crearUsuario(UsuarioDTO usuario) {
        String contraseñaEncriptada = encriptarContraseña(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
        try {
            insert(usuario);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }

    private String encriptarContraseña(String password) {
        String contraseñaEncriptada = BCrypt.hashpw(password, BCrypt.gensalt());
        return contraseñaEncriptada;
    }
}

