/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

/**
 *
 * @author jhony
 */
public abstract class Libro {
    private String codigo;
    private String titulo;
    private String autor;
    
    public Libro(Libro libro) {
        codigo = libro.codigo;
        autor = libro.autor;
        titulo = libro.titulo;
    }

    public Libro(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
    }
    
    public Libro() {
        autor = "Jose";
        titulo = "Las aventuras de Don Juanito";
        codigo = "001";
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        
        if (!(o instanceof Libro)) {
            return false;
        }
        
        Libro other = (Libro) o;
        if (other.codigo.equals(this.codigo)) {
            return true;
        }
        
        return false;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
