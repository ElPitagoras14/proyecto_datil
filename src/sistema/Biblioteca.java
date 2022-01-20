/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import usuarios.Estudiante;
import usuarios.Usuario;
import usuarios.Administrador;
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
    
    public Usuario iniciarSesion(String usuario, String password) {
        for (Usuario us: cuentas) {
            if (us.getUsername().equals(usuario) && us.getPassword().equals(password)) {
                return us;
            }
        }
        return null;
    }
    
    private void inicializar() {
        //Creando cuentas
        Administrador adm1 = new Administrador("adm1", "12345", "José Gavilanez");
        Estudiante est1 = new Estudiante("est1", "12345", "Cecilia Freire");
        Estudiante est2 = new Estudiante("est2", "12345", "Carlos Vargas");
        
        cuentas.add(adm1);
        cuentas.add(est1);
        cuentas.add(est2);
        
        //Creando libros
        Libro lb1 = new CopiaLibro("001", "El Príncipe", "Nicolas Maquiavelo");
        Libro lb2 = new CopiaLibro("002", "El Código da Vinci", "Dan Brown");
        Libro lb3 = new CopiaLibro("003", "El Alquimista", "Paulo Coelho");
        Libro lb4 = new CopiaLibro("004", "El diario de Ana Frank", "Anna Frank");
        
        //Agregando libros
        agregarLibrosInicializar(lb1, 5);
        agregarLibrosInicializar(lb2, 7);
        agregarLibrosInicializar(lb3, 8);
        agregarLibrosInicializar(lb4, 9);
    }
    
    private void agregarLibrosInicializar(Libro libro, int cantidad) {
        LinkedList<CopiaLibro> lista = new LinkedList<>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new CopiaLibro(libro));
        }
        libros.add(lista);
    }
    
    public LinkedList<CopiaLibro> getLibrosPrestados() {
        LinkedList<CopiaLibro> prestados = new LinkedList<>();
        for (Usuario us: cuentas) {
            if (us instanceof Estudiante) {
                Estudiante tmp = (Estudiante) us;
                prestados.addAll(tmp.getLibrosPrestados());
            }
        }
        return prestados;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public LinkedList<Usuario> getCuentas() {
        return cuentas;
    }
}
