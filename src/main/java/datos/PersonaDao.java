package datos;

import domain.PersonaDTO;
import java.sql.SQLException;
import java.util.List;

//diseño de la interface persona.
//DAO Data Access Object

public interface PersonaDao {
    
    public List<PersonaDTO> select() throws SQLException;
    
    public int insert(PersonaDTO persona) throws SQLException;
    
    public int update(PersonaDTO persona) throws SQLException;
    
    public int delete(PersonaDTO persona) throws SQLException;
}
