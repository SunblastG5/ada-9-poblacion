/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoblacionVicuñas;

import java.awt.event.WindowEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PoblacionVicuñas {
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter salArch;
    
    FileReader fr;
    BufferedReader entArch;
    
    String arch = "poblacion.txt";
    int max = 101;
    
    long []V=new long[max];
    int num;
    int k=0;
    String cadena;
    
    public PoblacionVicuñas(){
        this.V[0]=200;
        System.out.println("Registro de Logs \n\n");
    }
    
    public void IngresarNumero(int n){
        this.num=n;
    }
    
    public String Crear() throws IOException{
        fw=new FileWriter(arch);
        bw=new BufferedWriter(fw);
        salArch=new PrintWriter(bw);
        salArch.print(V[0]+",");
        System.out.println("Se creo el archivo "+arch);
        Cerrar(1);
        return "Se creo el archivo "+arch;
    }
    
    public void Abrir(int i) throws IOException{
        if (i==0){
            fr=new FileReader(arch);
            entArch= new BufferedReader(fr);
            System.out.println("Se abrio archivo "+arch+" en lectura");
        }
        else{
            fw=new FileWriter(arch);
            bw=new BufferedWriter(fw);
            salArch=new PrintWriter(bw);
            System.out.println("Se abrio archivo "+arch+" en escritura");
        }
    }
    
    public void Cerrar(int i) throws IOException{
        if (i==0){
            entArch.close();
            System.out.println("Se cerro archivo "+arch+" en lectura");
        }
        else{
            salArch.close();
            System.out.println("Se cerro archivo "+arch+" en escritura");
        }
    }
    
    public String Salvar() throws IOException{
        Abrir(1);
        for(int i=0;i<=k;i++){
            salArch.print(V[i]+",");
            System.out.println("Dato guardado: V["+i+"] = "+V[i]);
        }
        Cerrar(1);
        return "Datos guardados";
    }    
    
    public int recuperar() throws IOException{
        String []datos=new String[max];
        int i=0;
        Abrir(0);
        cadena=entArch.readLine();
        if(cadena!=null){
            datos=cadena.split(",");
            for (i=0;i<datos.length;i++){
                System.out.println("largo de datos: "+datos.length);
                System.out.println("datos["+i+"] = "+datos[i]);
                V[i]=Integer.parseInt(datos[i]);
                System.out.println("V["+i+"] = "+V[i]);
            }
        }
        System.out.println("largo de V: "+i);
        Cerrar(0);
        return i-1;
    }
    
    public long CalcularPoblacion(){
        int i;
        if(k==0){
            try {
                this.k=recuperar();
            }catch (IOException ex) {
                Logger.getLogger(PoblacionVicuñas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (num<=k){
            return V[num];
        }
        else{
            i=k+1;
            while(i<=num){
                V[i]=(long)Math.round(V[i-1]*1.15);
                System.out.println("valor de V["+i+"] = "+V[i]);
                i++;
            }
            k=num;
            return V[num];
        }
    }
    
    public String MostrarPoblacion(){
        long startTime=System.nanoTime(); //medicion del tiempo de ejecucion de la funcion CalcularPoblacion
        long pob=CalcularPoblacion();
        long endTime=System.nanoTime(); //Notese la diferencia de tiempo cuando ya tiene los datos operados en memoria
        System.out.println("Tiempo de ejecucion de la funcion: "+(endTime-startTime)+" ns");
        return "La poblacion de vicuñas para el instante "+num+" es: "+pob;
    }
}
