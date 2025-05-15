package pe.edu.upeu.msvcgestion_usuario.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nombre;

    private String apellido;

    private String avatar_url;

    @Column (nullable = false)
    private String pais;

    @Column (length = 9)
    private String celular;

    //Relacion de uno a uno Profil-Usuario

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") // Este es el correcto
    @JsonBackReference
    private Usuario usuario;



    public Profile() {
    }

    public Profile(Long id, String nombre, String apellido, String avatar_url, String pais, String celular, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.avatar_url = avatar_url;
        this.pais = pais;
        this.celular = celular;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

