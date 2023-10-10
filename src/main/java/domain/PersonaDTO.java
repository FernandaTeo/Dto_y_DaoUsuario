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

public class PersonaDTO {
    
    private int id_persona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    
}
