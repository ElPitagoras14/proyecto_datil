/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.Scanner;
import usuarios.Administrador;
import sistema.Biblioteca;
import sistema.CopiaLibro;
import usuarios.Estudiante;
import sistema.Libro;
import usuarios.Usuario;
import utilitarios.Utils;

/**
 *
 * @author jhony
 */
public class Main {

    private static Biblioteca sistema;
    private static Scanner sc;

    public static void main(String[] args) {
        sistema = Biblioteca.getInstance();
        sc = new Scanner(System.in);

        String opcion = "";
        while (!opcion.equals("2")) {
            presentarMenuPrincipal();
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    System.out.println("");
                    break;
            }
        }

    }

    private static void presentarMenuPrincipal() {
        System.out.println("Bienvenido a su biblioteca en linea");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    private static void iniciarSesion() {
        System.out.println("");
        System.out.println("Introduzca sus credenciales");
        System.out.print("Ingrese su usuario: ");
        String username = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

        Usuario us = sistema.iniciarSesion(username, password);
        if (us == null) {
            System.out.println("Usuario o contraseña incorrecta");
            System.out.println("");
        } else {
            if (us instanceof Administrador) {
                logicaAdministrador((Administrador) us);
            } else {
                logicaEstudiante((Estudiante) us);
            }
        }
    }

    public static void logicaAdministrador(Administrador admin) {
        String opcion = "";
        while (!opcion.equals("3")) {
            presentarMenuAdm();
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    agregarLibroAdm();
                    break;
                case "2":
                    buscarInfoAdm();
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    System.out.println("");
                    break;
            }
        }
        System.out.println("");
    }

    private static void presentarMenuAdm() {
        System.out.println("");
        System.out.println("Menu Principal");
        System.out.println("1. Agregar nuevos libros.");
        System.out.println("2. Buscar información de un libro.");
        System.out.println("3. Salir.");
        System.out.print("Ingrese una opcion: ");
    }

    private static void agregarLibroAdm() {
        System.out.println("");
        System.out.println("Nota: Si el codigo ingresado existe, se aumentará la cantidad indiferentemente del titulo o autor ingresados");
        System.out.println("Agregue los siguientes datos");
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        if (cantidad > 0) {
            Libro libro = new CopiaLibro(codigo, titulo, autor);
            sistema.getCatalogo().agregarLibro(libro, cantidad);
            System.out.println("Libro agregado exitosamente");
        } else {
            System.out.println("La cantidad debe ser mayor a 0");
        }
        System.out.println("");
    }

    private static void buscarInfoAdm() {
        System.out.println("");
        System.out.println("Busqueda de libro");
        System.out.print("Ingrese el código del libro: ");
        String codigo = sc.nextLine();

        sistema.getCatalogo().mostrarInformacionLibro(codigo);
        System.out.println("");
    }

    public static void logicaEstudiante(Estudiante est) {
        String opcion = "";
        while (!opcion.equals("3")) {
            presentarMenuEst();
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    prestarLibroEst(est);
                    break;
                case "2":
                    mostrarPrestamosEst(est);
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
        System.out.println("");
    }

    private static void presentarMenuEst() {
        System.out.println("");
        System.out.println("Menu Principal");
        System.out.println("1. Ver libros disponibles.");
        System.out.println("2. Ver libros prestados.");
        System.out.println("3. Salir.");
        System.out.println("");
        System.out.print("Ingrese una opcion: ");
    }

    private static void prestarLibroEst(Estudiante est) {
        LinkedList<CopiaLibro> carritoPrestamo = new LinkedList<>();
        String opcion = "";

        while (!opcion.equals("3")) {
            sistema.getCatalogo().mostrarCatalogo();
            System.out.println("");
            System.out.println("1. Prestar un libro.");
            System.out.println("2. Confirmar prestamo");
            System.out.println("3. Cancelar y Salir");
            System.out.print("Ingrese una opcion: ");

            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    seleccionarLibro(est, carritoPrestamo);
                    break;
                case "2":
                    confirmarPrestamo(est, carritoPrestamo);
                    break;
                case "3":
                    cancelarPrestamo(carritoPrestamo);
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    System.out.println("");
                    break;
            }
        }

    }

    private static void seleccionarLibro(Estudiante est, LinkedList<CopiaLibro> carritoPrestamo) {
        System.out.print("Ingrese el codigo: ");
        String codigo = sc.nextLine();
        if (Utils.contieneLibro(codigo, carritoPrestamo) || est.contieneLibro(codigo)) {
            System.out.println("No puedes solicitar un libro ya reservado o prestado.");
            System.out.println("");
        } else {
            CopiaLibro cl = sistema.getCatalogo().seleccionarLibro(codigo);
            if (cl == null) {
                System.out.println("Codigo no existe");
            } else {
                carritoPrestamo.add(cl);
                System.out.println("Libro seleccionado exitosamente");
            }
            System.out.println("");
        }
    }

    private static void confirmarPrestamo(Estudiante est, LinkedList<CopiaLibro> carritoPrestamo) {
        if (carritoPrestamo.isEmpty()) {
            System.out.println("No ha seleccionado algún libro.");
        } else {
            sistema.getCatalogo().confirmarPrestamo(carritoPrestamo, est);
            System.out.println("Su prestamo ha sido exitoso");
        }

    }

    private static void cancelarPrestamo(LinkedList<CopiaLibro> carritoPrestamo) {
        if (carritoPrestamo.isEmpty()) {
            System.out.println("No hay ningun libro seleccionado por cancelar.");
        } else {
            sistema.getCatalogo().cancelarPrestamo(carritoPrestamo);
            System.out.println("Se ha cancelado su prestamo");
        }

    }

    private static void mostrarPrestamosEst(Estudiante est) {
        est.mostrarLibrosPrestados();
    }
}
