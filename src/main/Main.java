/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import sistema.Biblioteca;
import sistema.Estudiante;

/**
 *
 * @author jhony
 */
public class Main {
    public static void main(String[] args) {
        Biblioteca sistema = Biblioteca.getInstance();
        
        sistema.getCatalogo().mostrarCatalogo();
        Estudiante es = (Estudiante) sistema.getCuentas().getFirst();
        es.verLibrosPrestados();
    }
}
