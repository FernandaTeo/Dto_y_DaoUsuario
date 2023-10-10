package datos;

import domain.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {

    UsuarioDTO buscarPorUsername(String username);

    boolean validarCredenciales(String username, String password);

    boolean crearUsuario(UsuarioDTO usuario);

    public List<UsuarioDTO> select() throws SQLException;
    
    public int insert(UsuarioDTO usuario) throws SQLException;
    
    public int update(UsuarioDTO usuario) throws SQLException;
    
    public int delete(UsuarioDTO usuario) throws SQLException;
}