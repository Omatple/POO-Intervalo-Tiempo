package org.iesalandalus.programacion.poo.intervalotiempo;

import org.iesalandalus.programacion.poo.intervalotiempo.modelo.Tiempo;
import org.iesalandalus.programacion.poo.intervalotiempo.vista.Consola;

public class Main {
    public static void main(String[] args) {
        Tiempo tiempo1 = Consola.leerTiempoHora();
        System.out.println(tiempo1);
        Tiempo tiempo2 = Consola.leerTiempoHoraMinuto();
        System.out.println(tiempo2);
        Tiempo tiempo3 = Consola.leerTiempoCompleto();
        System.out.println(tiempo3);
        System.out.println("Introduce el primer intervalo.........");
        System.out.println("Introduce el inicio........");
        Tiempo inicio1 = Consola.leerTiempoCompleto();
        System.out.println("Introduce el fin........");
        Tiempo fin1 = Consola.leerTiempoCompleto();
        //no me da tiempo para mas lo siento :(
    }
}