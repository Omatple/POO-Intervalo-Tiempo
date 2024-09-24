package org.iesalandalus.programacion.poo.intervalotiempo.modelo;

public record Tiempo(int hora, int minuto, int segundo) {
    private static final int HORAS_TOTALES = 24;
    private static final int MINUTOS_TOTALES = 60;
    private static final int SEGUNDOS_TOTALES = 60;

    public Tiempo {
        validarHora(hora);
        validarMinuto(minuto);
        validarSegundo(segundo);
    }

    public Tiempo() {
        this(0, 0, 0);
    }

    public Tiempo(int hora) {
        this(hora, 0, 0);
        validarHora(hora);
    }

    public Tiempo(int hora, int minuto) {
        this(hora, minuto, 0);
        validarHora(hora);
        validarMinuto(minuto);
    }

    private void validarHora(int hora) {
        if (hora < 0 || hora >= HORAS_TOTALES) {
            throw new IllegalArgumentException("Hora no valida.");
        }
    }

    private void validarMinuto(int minuto) {
        if (minuto < 0 || minuto >= MINUTOS_TOTALES) {
            throw new IllegalArgumentException("Minuto no valido.");
        }
    }

    private void validarSegundo(int segundo) {
        if (segundo < 0 || segundo >= SEGUNDOS_TOTALES) {
            throw new IllegalArgumentException("Segundo no valido.");
        }
    }

    public static Tiempo getMayor() {
        return new Tiempo(HORAS_TOTALES - 1, MINUTOS_TOTALES - 1, SEGUNDOS_TOTALES - 1);
    }

    public static Tiempo getMenor() {
        return new Tiempo();
    }

    public Tiempo masHoras(int horas) {
        int sumaHoras = this.hora + horas;
        int nuevaHora = sumaHoras % HORAS_TOTALES;
        if (nuevaHora < 0) {
            nuevaHora += HORAS_TOTALES;
        }
        return new Tiempo(nuevaHora, this.minuto, this.segundo);
    }

    public Tiempo masMinutos(int minutos) {
        int horasSobrantes = minutos / MINUTOS_TOTALES;
        int sumaMinutos = this.minuto + minutos;
        int nuevosMinutos = sumaMinutos % MINUTOS_TOTALES;
        if (nuevosMinutos < 0) {
            nuevosMinutos += MINUTOS_TOTALES;
            horasSobrantes--;
        }
        int nuevaHora = this.hora + horasSobrantes;
        return new Tiempo(nuevaHora, nuevosMinutos, this.segundo);
    }

    public Tiempo masSegundos(int segundos) {
        int minutosSobrantes = segundos / SEGUNDOS_TOTALES;
        int sumaSegundos = this.segundo + segundos;
        int nuevosSegundos = sumaSegundos % SEGUNDOS_TOTALES;
        if (nuevosSegundos < 0) {
            nuevosSegundos += SEGUNDOS_TOTALES;
            minutosSobrantes--;
        }
        int nuevosMinutos = this.minuto + minutosSobrantes;
        int nuevaHora = this.hora;
        if (nuevosMinutos < 0) {
            nuevosMinutos += MINUTOS_TOTALES;
            nuevaHora--;
        }
        return new Tiempo(nuevaHora, nuevosMinutos, nuevosSegundos);
    }

    public Tiempo sumar(Tiempo tiempo) {
        int sumaSegundos = this.segundo + tiempo.segundo;
        int sumaMinutos = this.minuto + tiempo.minuto + (sumaSegundos / SEGUNDOS_TOTALES);
        int sumaHoras = this.hora + tiempo.hora + (sumaMinutos / MINUTOS_TOTALES);
        sumaSegundos %= SEGUNDOS_TOTALES;
        sumaMinutos %= MINUTOS_TOTALES;
        sumaHoras %= HORAS_TOTALES;
        return new Tiempo(sumaHoras, sumaMinutos, sumaSegundos);
    }

    public Tiempo restar(Tiempo tiempo) {
        int restaSegundos = this.segundo - tiempo.segundo;
        int restaMinutos = this.minuto - tiempo.minuto;
        int restaHoras = this.hora - tiempo.hora;
        if (restaSegundos < 0) {
            restaSegundos += SEGUNDOS_TOTALES;
            restaMinutos--;
        }
        if (restaMinutos < 0) {
            restaMinutos += MINUTOS_TOTALES;
            restaHoras--;
        }
        if (restaHoras < 0) {
            restaHoras += HORAS_TOTALES;
        }
        return new Tiempo(restaHoras, restaMinutos, restaSegundos);
    }

    @Override
    public String toString() {
        String horas, minutos, segundos;
        if (this.hora < 10) {
            horas = "0" + this.hora;
        } else {
            horas = "" + this.hora;
        }
        if (this.minuto < 10) {
            minutos = "0" + this.minuto;
        } else {
            minutos = "" + this.minuto;
        }
        if (this.segundo < 10) {
            segundos = "0" + this.segundo;
        } else {
            segundos = "" + this.segundo;
        }
        return horas + ":" + minutos + ":" + segundos;
    }

    public boolean mayorQue(Tiempo tiempo) {
        if (this.hora > tiempo.hora) {
            return true;
        } else if (this.hora == tiempo.hora && this.minuto > tiempo.minuto) {
            return true;
        } else if (this.hora == tiempo.hora && this.minuto == tiempo.minuto && this.segundo > tiempo.segundo) {
            return true;
        }
        return false;
    }

    public boolean menorQue(Tiempo tiempo) {
        if (this.hora < tiempo.hora) {
            return true;
        } else if (this.hora == tiempo.hora && this.minuto < tiempo.minuto) {
            return true;
        } else if (this.hora == tiempo.hora && this.minuto == tiempo.minuto && this.segundo < tiempo.segundo) {
            return true;
        }
        return false;
    }
}