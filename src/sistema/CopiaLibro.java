/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.time.LocalDate;
import static sistema.EstadoLibro.SINPRESTAR;

/**
 *
 * @author jhony
 */
public class CopiaLibro extends Libro {

    private EstadoLibro estado;
    private LocalDate fechaEmision;
    private LocalDate fechaDevolucion;

    public CopiaLibro(Libro libro) {
        super(libro);
        estado = SINPRESTAR;
    }

    public CopiaLibro(String codigo, String titulo, String autor) {
        super(codigo, titulo, autor);
        estado = SINPRESTAR;
    }
    
    

    public CopiaLibro() {

    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}
