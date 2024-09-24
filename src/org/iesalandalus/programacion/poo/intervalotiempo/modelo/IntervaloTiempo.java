package org.iesalandalus.programacion.poo.intervalotiempo.modelo;

import java.util.Objects;

public record IntervaloTiempo(Tiempo inicio, Tiempo fin) {
    public IntervaloTiempo(Tiempo inicio, Tiempo fin) {
        if (fin.mayorQue(inicio)) {
            this.inicio = inicio;
            this.fin = fin;
        } else {
            throw new IllegalArgumentException("El tiempo de fin debe superior al tiempo de inicio");
        }
    }

    public IntervaloTiempo() {
        this(new Tiempo(0, 0, 0), new Tiempo(0, 0, 1));
    }

    public Tiempo getDuracion() {
        return fin.restar(inicio);
    }

    public IntervaloTiempo ampliarPorArriba(Tiempo tiempo) {
        Tiempo nuevoFin = fin.sumar(tiempo);
        if (nuevoFin.mayorQue(fin)) {
            return new IntervaloTiempo(inicio, nuevoFin);
        } else {
            throw new IllegalArgumentException("No se puede ampliar mas que el tiempo de fin.");
        }
    }

    public IntervaloTiempo ampliarPorAbajo(Tiempo tiempo) {
        Tiempo nuevoInicio = inicio.restar(tiempo);
        if (nuevoInicio.menorQue(inicio)) {
            return new IntervaloTiempo(nuevoInicio, fin);
        } else {
            throw new IllegalArgumentException("No se puede ampliar mas que el tiempo de inicio.");
        }
    }

    public IntervaloTiempo reducirPorArriba(Tiempo tiempo) {
        Tiempo nuevoFin = fin.restar(tiempo);
        if (nuevoFin.menorQue(inicio)) {
            throw new IllegalArgumentException("No se puede reducir mas que el tiempo de inicio.");
        }
        return new IntervaloTiempo(inicio, nuevoFin);
    }

    public IntervaloTiempo reducirPorAbajo(Tiempo tiempo) {
        Tiempo nuevoInicio = inicio.sumar(tiempo);
        if (nuevoInicio.mayorQue(fin)) {
            throw new IllegalArgumentException("No se puede reducir mas que el tiempo de fin.");
        }
        return new IntervaloTiempo(nuevoInicio, fin);
    }

    public boolean mayorQue(IntervaloTiempo intervaloTiempo) {
        return this.getDuracion().mayorQue(intervaloTiempo.getDuracion());
    }

    public boolean menorQue(IntervaloTiempo intervaloTiempo) {
        return this.getDuracion().menorQue(intervaloTiempo.getDuracion());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervaloTiempo that = (IntervaloTiempo) o;
        return Objects.equals(getDuracion(), that.getDuracion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDuracion());
    }

    @Override
    public String toString() {
        return inicio.toString() + " - " + fin.toString();
    }
}