package org.iesalandalus.programacion.poo.intervalotiempo.vista;

import org.iesalandalus.programacion.poo.intervalotiempo.modelo.IntervaloTiempo;
import org.iesalandalus.programacion.poo.intervalotiempo.modelo.Tiempo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
    private Consola() {
    }

    public static Tiempo leerTiempoHora() {
        while (true) {
            try {
                int hora = leerHora();
                return new Tiempo(hora, 0, 0);
            } catch (IllegalArgumentException e) {
                System.out.println("Hora no valida. Las horas deben estar comprendidas entre 0 y 23 ambas incluidas:");
            }
        }
    }

    private static int leerHora() {
        System.out.println("Introduce la hora:");
        int hora = Entrada.entero();
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException();
        }
        return hora;
    }

    public static Tiempo leerTiempoHoraMinuto() {
        while (true) {
            try {
                int hora = leerHora();
                int minuto = leerMinuto();
                return new Tiempo(hora, minuto, 0);
            } catch (IllegalArgumentException e) {
                System.out.println("Hora o minuto no valido. Intentelo de nuevo:");
            }
        }
    }

    private static int leerMinuto() {
        System.out.println("Introduce el minuto:");
        int minuto = Entrada.entero();
        if (minuto < 0 || minuto > 59) {
            throw new IllegalArgumentException();
        }
        return minuto;
    }

    public static Tiempo leerTiempoCompleto() {
        while (true) {
            try {
                int hora = leerHora();
                int minuto = leerMinuto();
                int segundo = leerSegundo();
                return new Tiempo(hora, minuto, segundo);
            } catch (IllegalArgumentException e) {
                System.out.println("Hora, minuto o segundo no valido. Intentelo de nuevo:");
            }
        }
    }

    private static int leerSegundo() {
        System.out.println("Introduce el segundo:");
        int segundo = Entrada.entero();
        if (segundo < 0 || segundo > 59) {
            throw new IllegalArgumentException();
        }
        return segundo;
    }

    public static IntervaloTiempo leerIntervalo() {
        while (true) {
            try {
                System.out.println("Introduce el inicio del intervalo:");
                Tiempo inicio = leerTiempoCompleto();
                System.out.println("Introduce el fin del intervalo:");
                Tiempo fin = leerTiempoCompleto();
                return new IntervaloTiempo(inicio, fin);
            } catch (IllegalArgumentException e) {
                System.out.println("Intervalo no valido. Intentelo de nuevo:");
            }
        }
    }
}