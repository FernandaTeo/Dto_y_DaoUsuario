package domain;


//DTO: Data Transfer Object
//Los DTO son un tipo de objetos que sirven únicamente para transportar datos. 
//EL DTO contiene las propiedades del objeto. 
//Datos que pueden tener su origen en una o más entidades de información. 
//Estos datos son incorporados a una instancia de un JavaBean.

//El patrón DTO tiene como finalidad de crear un objeto plano (POJO) 
//con una serie de atributos que puedan ser enviados o recuperados del servidor en una 
//sola invocación, de tal forma que un DTO puede contener información de múltiples fuentes 
//o tablas y concentrarlas en una única clase simple


public class UsuarioDTO {
    private int id_usuario;
    private String username;
    private String password;

    private  String passwordEncriptado;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public UsuarioDTO(int id_usuario, String username, String password) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
    }
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
