package PaqC07;

import java.io.Serializable;
import java.util.Arrays;

public class Registro implements Serializable {
    private int numHab, numPisos;
    protected Cliente [][] habitaciones;

    public Registro() {
        numPisos = 8;
        numHab = 6;
        habitaciones = new Cliente[numPisos][numHab];
    }

    public String muestraPantalla(){
        String str = "";
        for(int i = 0; i < this.numPisos; i++){
            for(int j = 0; j< this.numHab; j++){
                if(this.habitaciones[i][j] == null){
                    str += " L ";
                }else str += " R ";
            }
            str += "\n";
        }
        return str;
    }

    public int [] encuentraHab(int tipo){
        int [] coordenadas = new int [2];
        if(tipo == 1) coordenadas = búsquedaHabLibre(0,5,coordenadas);
        else if (tipo == 2) coordenadas = búsquedaHabLibre(5,7,coordenadas);
        else if (tipo == 3) coordenadas =  búsquedaHabLibre(7,8,coordenadas);
        return coordenadas;
    }

    private int [] búsquedaHabLibre(int indInf, int indSup, int [] coordenadas){
        for(int i = indInf; i < indSup; i++){
            for(int j = 0; j < numHab; j++){
                if(this.habitaciones[i][j] == null){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public String verDatosCliente(int [] c){
        if (habitaciones[c[0]][c[1]] != null)
            return habitaciones[c[0]][c[1]].toString();
        else
            return "No existe reserva en la habitación " + (c[1]+1)+ " de la planta " + (c[0]+1) + ".";
    }


    public void almacenaReserva(int [] coordenadas, Cliente cl){
        habitaciones[coordenadas[0]][coordenadas[1]]=cl;
    }

    public void anulaReserva(int dni, int tipo, int numHab){
        int [] coordenadas=new int[2];
        for(int i=0; i<numHab; i++){
            coordenadas=buscarReserva(dni, tipo);
            habitaciones[coordenadas[0]][coordenadas[1]]=null;
        }
    }


    public int[] buscarReserva(int dni, int tipo){
        int [] coordenadas = new int [2];
        if(tipo == 1) coordenadas = búsquedaReservas(dni, 0,5,coordenadas);
        else if (tipo == 2) coordenadas = búsquedaReservas(dni,5,7,coordenadas);
        else if (tipo == 3) coordenadas =  búsquedaReservas(dni,7,8,coordenadas);
        return coordenadas;
    }

    private int [] búsquedaReservas(int dni, int indInf, int indSup, int coordenadas []){
        for (int i = indInf; i < indSup; i++)
            for (int j = 0; j < numHab; j++)
                if (habitaciones[i][j] != null)
                    if (habitaciones[i][j].getDni() == dni)
                        return new int[]{i, j};
        return new int[] {-1,-1};
    }

}



