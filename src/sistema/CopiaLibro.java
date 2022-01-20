/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.time.LocalDate;

/**
 *
 * @author jhony
 */
public class CopiaLibro extends Libro {
    private LocalDate fechaEmision;
    private LocalDate fechaDevolucion;

    public CopiaLibro(Libro libro) {
        super(libro);
    }

    public CopiaLibro(String codigo, String titulo, String autor) {
        super(codigo, titulo, autor);
    }
    
    public void actualizarFechasPrestamo() {
        fechaEmision = LocalDate.now();
        fechaDevolucion = fechaEmision.plusDays(30);
    }
    
    public void actualizarFechasDevolucion() {
        fechaEmision = null;
        fechaDevolucion = null;
    }


    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}
