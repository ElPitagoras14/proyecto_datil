/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.LinkedList;

/**
 *
 * @author jhony
 */
public class Catalogo {

    private LinkedList<LinkedList<CopiaLibro>> libros;

    public Catalogo(LinkedList<LinkedList<CopiaLibro>> libros) {
        this.libros = libros;
    }

    public void mostrarCatalogo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-35s%-15s%-15s", "Codigo", "Titulo", "Autor", "Cantidad disponible"));
        for (LinkedList<CopiaLibro> cl : libros) {
            Libro copia = cl.getFirst();
            sb.append(String.format("\n%-15s%-35s%-15s%-15s", copia.getCodigo(), copia.getTitulo(), copia.getAutor(), cl.size()));
        }
        System.out.println(sb.toString());
        sb = null;
    }

    public void agregarLibro(Libro libro, int cantidad) {
        for (LinkedList<CopiaLibro> cl : libros) {
            if (!cl.isEmpty()) {
                if (cl.getFirst().getCodigo().equals(libro.getCodigo())) {
                    for (int i = 0; i < cantidad; i++) {
                        cl.add(new CopiaLibro(libro));
                    }
                    return;
                }
            }
        }
        LinkedList<CopiaLibro> tmp = new LinkedList<>();
        libros.add(tmp);
        for (int i = 0; i < cantidad; i++) {
            tmp.add(new CopiaLibro(libro));
        }
    }

    public boolean prestarLibro(String codigo, Estudiante estudiante) {
        for (LinkedList<CopiaLibro> cl : libros) {
            if (!cl.isEmpty()) {
                if (cl.getFirst().getCodigo().equals(codigo)) {
                    estudiante.prestarLibro(cl.removeLast());
                    return true;
                }
            }
        }
        return false;
    }

    private LinkedList<Libro> librosUnicos(LinkedList<LinkedList<CopiaLibro>> libros) {
        LinkedList<Libro> catalogoLibros = new LinkedList<>();
        for (LinkedList<CopiaLibro> cl : libros) {
            catalogoLibros.add((Libro) cl.getFirst());
        }
        return catalogoLibros;
    }
    
    public void mostrarInformacionLibro(String codigo) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-35s%-15s", "Codigo", "Titulo", "Autor"));
        for (LinkedList<CopiaLibro> cl : libros) {
            Libro copia = cl.getFirst();
            if (copia.getCodigo().equals(codigo)) {
                sb.append(String.format("\n%-15s%-35s%-15s", copia.getCodigo(), copia.getTitulo(), copia.getAutor()));
            }
        }
        System.out.println(sb.toString());
    }
}
