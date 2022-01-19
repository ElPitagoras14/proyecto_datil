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
public class Biblioteca {

    private static Biblioteca instance;
    private LinkedList<LinkedList<CopiaLibro>> libros;
    private Catalogo catalogo;
    private LinkedList<Usuario> cuentas;

    private Biblioteca() {
        libros = new LinkedList<>();
        cuentas = new LinkedList<>();
        inicializar();
        catalogo = new Catalogo(libros);
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public void inicializar() {
        Estudiante estudiante = new Estudiante("", "", "");
        cuentas.add(estudiante);
        for (int i = 0; i < 3; i++) {
            LinkedList<CopiaLibro> tmp = new LinkedList<>();
            Libro libro = new CopiaLibro("" + i, "Las Aventuras de " + i, "Jose " + i);
            for (int j = 0; j < 3; j++) {
                tmp.add(new CopiaLibro(libro));
            }
            estudiante.prestarLibro(new CopiaLibro(libro));
            libros.add(tmp);
        }

    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public LinkedList<Usuario> getCuentas() {
        return cuentas;
    }
}
