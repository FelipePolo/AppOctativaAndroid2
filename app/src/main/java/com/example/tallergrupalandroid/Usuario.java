package com.example.tallergrupalandroid;

import java.util.Objects;

public class Usuario {
    public String usuario;
    public String password;
    public String correo;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(String usuario, String password,String correo) {
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario) &&
                Objects.equals(password, usuario1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, password);
    }
}
