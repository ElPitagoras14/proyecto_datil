/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import utilitarios.Utils;
import usuarios.Estudiante;
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
        sb.append(String.format("%-15s%-35s%-25s%-15s", "Codigo", "Titulo", "Autor", "Cantidad disponible"));
        for (LinkedList<CopiaLibro> cl : libros) {
            Libro copia = cl.getFirst();
            sb.append(String.format("\n%-15s%-35s%-25s%-15s", copia.getCodigo(), copia.getTitulo(), copia.getAutor(), cl.size()));
        }
        System.out.println(sb.toString());
    }

    public void agregarLibro(Libro libro, int cantidad) {
        LinkedList<CopiaLibro> listaLibro = Utils.getCopiasLibro(libro.getCodigo(), libros);
        if (listaLibro == null) {
            listaLibro = new LinkedList<>();
            for (int i = 0; i < cantidad; i++) {
                listaLibro.add(new CopiaLibro(libro));
            }
            libros.add(listaLibro);
        } else {
            for (int i = 0; i < cantidad; i++) {
                listaLibro.add(new CopiaLibro(libro));
            }
        }
    }

    public CopiaLibro seleccionarLibro(String codigo) {
        LinkedList<CopiaLibro> cl = Utils.getCopiasLibro(codigo, libros);
        if (cl == null) {
            return null;
        }
        CopiaLibro libroPrestar = cl.removeLast();
        return libroPrestar;
    }

    public void confirmarPrestamo(LinkedList<CopiaLibro> seleccionados, Estudiante estudiante) {
        while(!seleccionados.isEmpty()) {
            CopiaLibro cl = seleccionados.removeLast();
            cl.actualizarFechasPrestamo();
            estudiante.prestarLibro(cl);
        }
    }
    
    public void cancelarPrestamo(LinkedList<CopiaLibro> seleccionados) {
        while(!seleccionados.isEmpty()) {
            agregarLibro(seleccionados.removeLast(), 1);
        }
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
