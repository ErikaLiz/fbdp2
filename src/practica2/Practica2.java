/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

/**
 *
 * @author Moctezuma19
 */
public class Practica2 {

    public static void main(String[] args) {
        System.out.println("Bienvenido!");
        LinkedList<RPreliminar> rep = new LinkedList<>();
        LinkedList<Casilla> casillas = new LinkedList<>();
        try {
            Scanner sc = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            /*INICIO, lee el archivo casillas y obtiene objetos Casilla que agrega a una lista,
            si existe previamente un archivo de representantes, lo lee y obtiene objetos RPreliminar
            que agrega a una lista.
             */
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Moctezuma19\\Documents\\NetBeansProjects\\Practica2\\src\\practica2\\casillas.csv")));

            File f = new File("C:\\Users\\Moctezuma19\\Documents\\NetBeansProjects\\Practica2\\src\\practica2\\representantes_preliminares.csv");
            if (f.exists()) {
                BufferedReader br2 = new BufferedReader(new FileReader(f));

                String r = br2.readLine();
                while (r != null) {
                    String[] rp = r.split(",");
                    rep.add(new RPreliminar(Integer.parseInt(rp[0]), rp[1], rp[2], rp[3], rp[4], Integer.parseInt(rp[5]), Integer.parseInt(rp[6])));
                    r = br2.readLine();

                }
                br2.close();
            }
            String r = br.readLine();
            while (r != null) {
                String[] rp = r.split(",");
                casillas.add(new Casilla(Integer.parseInt(rp[0]), Integer.parseInt(rp[1]), Integer.parseInt(rp[2]), rp[3], rp[4]));
                r = br.readLine();
            }
            br.close();
            /*FIN*/

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            ////id actua en el que va la cuenta
            int id = rep.size();

            int opt,idc, s;
            String n1, p,m,c;
            
            while (true) {
                System.out.println("Elige una opcion:\n1. Captura.\n2. Modifica.\n3. Elimina.\n4. Salir.");
                opt = sc.nextInt();
                switch (opt) {
                    case 1: {
                        System.out.println("Favor de llenar el siguiente formulario para agregar tu representante");
                            System.out.println("Cual es el nombre sin apellidos del representante "); 
                                n1 = sc.nextLine();
                                if (esNumero(n1)){
                                    System.out.println("Verifique que el nombre no tenga un caracter numerico");
                                    break;                                    
                                } 
                            System.out.println("Apellido Paterno del representante");
                                p = sc.nextLine();
                                if (esNumero(p)){
                                    System.out.println("Verifique que el nombre no tenga un caracter numerico");
                                    break;                                    
                                }
                            System.out.println("Apellido Materno del representante");
                                m = sc.nextLine();
                                if (esNumero(m)){
                                    System.out.println("Verifique que el nombre no tenga un caracter numerico");
                                    break;                                    
                                }
                            System.out.println("Calidad del represetante");
                                c = sc.nextLine();
                                if (esNumero(c)){
                                    System.out.println("Verifique que el nombre no tenga un caracter numerico");
                                    break;                                    
                                }
                            System.out.println("Identificador de casilla");
                               try {
                                    idc = sc.nextInt();
                                    for(Casilla cas : casillas){
                                      if(cas.getId()!=idc){ 
                                         break;
                                        }
                                    }
//                                   System.out.println("No existe esa casilla"); 
                                } catch (NumberFormatException nf) {
                                    System.out.println("Solo se aceptan numeros.");
                                    continue;
                                }
                            System.out.println("Seccion");
                                try {
                                    s = sc.nextInt();
                                } catch (NumberFormatException nf) {
                                    System.out.println("Solo se aceptan numeros.");
                                    continue;
                                }

                        rep.add(new RPreliminar(n1,p,m,c,idc,s));
                        System.out.println("RPreliminar agregado satisfactoriamente");
                    }
                    break;

                    case 2: {
                        while (true) {
                            System.out.println("MODIFICA\nElige una opcion.\n1. Busca por nombre completo.\n2. Busca por ID.\n3. Regresar.");
                            try {
                                opt = sc.nextInt();
                            } catch (NumberFormatException nf) {
                                System.out.println("Solo se aceptan numeros.");
                                continue;
                            }
                            if (opt >= 3) {
                                break;
                            }
                            RPreliminar rp = null;
                            int ide = -1;
                            if (opt == 1) {
                                System.out.println("Introduce el nombre completo :");
                                String nombre = sc2.nextLine();
                                rp = Practica2.Busca(rep, nombre);
                            }
                            if (opt == 2) {
                                try {
                                    System.out.println("Introduce el ID :");
                                    ide = sc.nextInt();
                                } catch (NumberFormatException e) {
                                    System.out.println("Solo caracteres numericos.");
                                    continue;
                                }
                                rp = Practica2.BuscaId(rep, ide);
                            }
                            if (rp == null) {
                                System.out.println("Representante Preliminar no encontrado.");
                                continue;
                            }
                            RPreliminar bk = rp;
                            rep.remove(rp);
                            while (true) {
                                System.out.println("¿Cambiar nombre? S/N");
                                String opt2 = sc2.nextLine();
                                if (opt2.equals("S")) {
                                    System.out.println("Escribe nombre completo. (maximo 2 nombres, minimo 2 apellidos)");
                                    String[] n = sc2.nextLine().split(" ");
                                    //cambiar nombre
                                    if (n.length > 4 || n.length <= 2) {
                                        System.out.println("Formato incorrecto, cambio no realizado.");
                                    } else if (n.length == 4) {
                                        rp.setNombre(n[0] + n[1]);
                                        rp.setAPaterno(n[2]);
                                        rp.setAMaterno(n[3]);
                                    } else {
                                        rp.setNombre(n[0]);
                                        rp.setAPaterno(n[1]);
                                        rp.setAMaterno(n[2]);
                                    }

                                }
                                System.out.println("¿Cambiar calidad de representante? S/N");
                                opt2 = sc2.nextLine();
                                if (opt2.equals("S")) {
                                    System.out.println("Escribe la calidad del representante.");
                                    opt2 = sc2.nextLine();
                                    rp.setCalidadR(opt2);
                                }
                                System.out.println("¿Cambiar id de casilla? S/N");
                                opt2 = sc2.nextLine();
                                if (opt2.equals("S")) {
                                    System.out.println("Escribe el nuevo Id");
                                    opt = sc.nextInt();
                                    try {
                                        casillas.get(opt);
                                        rp.setCasilla(opt);
                                    } catch (Exception ss) {
                                        System.out.println("Casilla inexistente, valor no modificado.");
                                    }
                                }
                                System.out.println("¿Cambiar seccion? S/N");
                                opt2 = sc2.nextLine();
                                if (opt2.equals("S")) {
                                    System.out.println("Escribe la nueva seccion.");
                                    opt = sc.nextInt();
                                    rp.setSeccion(opt);
                                }
                                if (rep.contains(rp)) {
                                    System.out.println("Representante ya existente!, sin modificaciones.");
                                    rep.add(bk);
                                }
                                rep.add(rp);
                                RPreliminar[] aux = new RPreliminar[rep.size()];
                                for(int i = 0; i < aux.length; i++){
                                    aux[i] = rep.get(i);
                                }
                                Arrays.sort(aux);
                                rep.clear();
                                for (RPreliminar rr : aux) {
                                    rep.add(rr);
                                }
                                //ordenar lista
                                f.delete();
                                bw.close();
                                bw = new BufferedWriter(new FileWriter(f));
                                for (RPreliminar rr : rep) {
                                    bw.write(rr.toString());
                                    bw.newLine();
                                }
                                System.out.println("Cambio Realizado!");
                                break;

                            }

                        }

                    }
                    break;case 3: {
                    while (true) {
                            System.out.println("MODIFICA\nElige una opcion.\n1. Busca por nombre completo.\n2. Busca por ID.\n3. Regresar.");
                            try {
                                opt = sc.nextInt();
                            } catch (NumberFormatException nf) {
                                System.out.println("Solo se aceptan numeros.");
                                continue;
                            }
                            if (opt >= 3) {
                                break;
                            }
                            RPreliminar rp = null;
                            int ide = -1;
                            if (opt == 1) {
                                System.out.println("Introduce el nombre completo :");
                                String nombre = sc2.nextLine();
                                rp = Practica02Datos.Busca(rep, nombre);
                            }
                            if (opt == 2) {
                                try {
                                    System.out.println("Introduce el ID :");
                                    ide = sc.nextInt();
                                } catch (NumberFormatException e) {
                                    System.out.println("Solo caracteres numericos.");
                                    continue;
                                }
                                rp = Practica02Datos.BuscaId(rep, ide);
                            }
                            if (rp == null) {
                                System.out.println("Representante Preliminar no encontrado.");
                                continue;
                            }
                            RPreliminar bk = rp;
                            rep.remove(rp);
                            while (true) {
                                System.out.println("Estas a punto de eliminar un representante. Si estas seguro escribe S");
                                c = sc.nextLine();
                                if (esNumero(c)){
                                    System.out.println("Verifique que el nombre no tenga un caracter numerico");
                                    break;                                    
                                }else if(c.equals(t)){    
                                    rep.remove(rp.getId());
                                    System.out.println("El representante se elimino correctamente");
                                }

                            }

                        }    
                        
                    }
                    break;
                        
                    case 4: {
                        bw.close();
                        System.out.println("Adios!");
                        System.exit(0);
                    }
                    break;
                    default:
                        bw.close();
                        System.out.println("Adios!");
                        System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }

    /**
     * Metodo estatico que busca en a un representante preliminar por id en una
     * lista de prepresentantes.
     *
     * @param lista lista en la cual buscar.
     * @param id ID del representante preliminar.
     * @return devuelve null si no se encuentra, de lo contrario devuelve el
     * representante.
     */
    public static RPreliminar BuscaId(LinkedList<RPreliminar> lista, int id) {
        RPreliminar rp = new RPreliminar(id, "", "", "", "", 0, 0);
        for (RPreliminar p : lista) {
            if (rp.equals(p)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo estatico que busca en a un representante preliminar por nombre
     * completo en una lista de prepresentantes.
     *
     * @param lista lista en la cual buscar.
     * @param nombre nombre completo del representante preliminar.
     * @return devuelve null si no se encuentra, de lo contrario devuelve el
     * representante.
     */
    public static RPreliminar Busca(LinkedList<RPreliminar> lista, String nombre) {
        String[] n = nombre.split(" ");
        RPreliminar rp;
        if (n.length == 4) {
            rp = new RPreliminar(0, n[0] + n[1], n[2], n[3], "", 0, 0);
        } else {
            rp = new RPreliminar(0, n[0], n[1], n[2], "", 0, 0);
        }
        for (RPreliminar p : lista) {
            if (rp.equals(p)) {
                return p;
            }
        }
        return null;
    }
    
        public static boolean esNumero(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException e) {
            resultado = false;
        }

        return resultado;
    }

}
