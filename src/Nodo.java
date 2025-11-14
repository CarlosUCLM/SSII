import java.util.*;

public class Node{
    public final int id;            
    public final Integer Idpadre; 
    public final String accion;     
    public final String estado;      
    public final int coste;        
    public final int profundidad;         
    public final int heuristica;     
    public final int valor

    public Nodo( int id, Integer Idpadre, string accion, string estado, int coste,int profundidad, int heuristica, int valor){
        this.id= id;
        this.Idpadre= Idpadre;
        this.accion= accion;
        this.estado= estado;
        this.coste= coste;
        this.profundidad= profundidad;
        this.heuristica=heuristica;
        this.valor= valor;
        
    }

    public string Frase(){
        
        String escribir = "["+id+","+Idpadre+","+accion+","+estado+","+coste+","+profundidad+","+heuristica+","+valor"]";
    }































}