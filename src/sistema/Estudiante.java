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
public class Estudiante extends Usuario{
    private LinkedList<CopiaLibro> librosPrestados;

    public Estudiante(String username, String password, String nombre) {
        super(username, password, nombre);
        librosPrestados = new LinkedList<>();
    }
    
    public void verLibrosPrestados() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-35s%-15s%-15s%-15s", "Titulo", "Autor", "Emision", "Devolucion"));
        for (CopiaLibro cl: librosPrestados) {
            sb.append(String.format("\n%-35s%-15s%-15s%-15s", cl.getTitulo(), cl.getAutor(), cl.getFechaEmision().toString(), cl.getFechaDevolucion().toString()));
        }
        System.out.println(sb.toString());
    }
    
    public void prestarLibro(CopiaLibro libro) {
        librosPrestados.add(libro);
    }
}
