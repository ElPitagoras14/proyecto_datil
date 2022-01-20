/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.LinkedList;
import sistema.CopiaLibro;

/**
 *
 * @author jhony
 */
public class Utils {
    
    public static boolean contieneLibro(String codigo, LinkedList<CopiaLibro> lista) {
        for (CopiaLibro cl: lista) {
            if (cl.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    
    public static LinkedList<CopiaLibro> getCopiasDisponibles(String codigo, LinkedList<LinkedList<CopiaLibro>> lista) {
        for (LinkedList<CopiaLibro> cl: lista) {
            if (!cl.isEmpty()) {
                if (cl.getFirst().getCodigo().equals(codigo) && cl.size() > 1) {
                    return cl;
                }
            }
        }
        return null;
    }
    
    public static LinkedList<CopiaLibro> getListaLibros(String codigo, LinkedList<LinkedList<CopiaLibro>> lista) {
        for (LinkedList<CopiaLibro> cl: lista) {
            if (!cl.isEmpty()) {
                if (cl.getFirst().getCodigo().equals(codigo)) {
                    return cl;
                }
            }
        }
        return null;
    }
    
}
