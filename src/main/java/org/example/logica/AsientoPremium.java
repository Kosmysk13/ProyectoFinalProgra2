package org.example.logica;
public class AsientoPremium implements Asiento{
    private int num;
    private boolean disponible;
    public AsientoPremium(int a){
        num = a;
    }
    public String getPosicion(){
        if (num<5){
            return "ventana";
        }else{
            return null;
        }
    }
    public int getNumAsiento(){
        return num;
    }
    public int getPrecio(){
        return (7000);
    }
    public String getTipoAsiento(){
        return "Premium";
    }
    public void setDisponibilidad(boolean aux){
        disponible = aux;
    }
    public String getDisponibilidad(){
        if (disponible==true){
            return "disponible";
        }else{
            return "ocupado";
        }
    }
}

