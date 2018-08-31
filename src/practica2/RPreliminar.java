/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

/**
 *
 * @author Moctezuma19
 */
public class RPreliminar implements Comparable {

    @Override
    public int compareTo(Object o) {
        RPreliminar rp = (RPreliminar) o;
        if (this.id < rp.id) {
            return -1;
        }

        if (this.id > rp.id) {
            return 1;
        }

        return 0;

    }

    private class Nombre {

        private String nombre;
        private String paterno;
        private String materno;

        /**
         * Constructor de nombre.
         *
         * @param n nombre.
         * @param p apellido paterno.
         * @param m apellido materno.
         */
        public Nombre(String n, String p, String m) {
            this.nombre = n;
            this.paterno = p;
            this.materno = m;
        }

        /**
         * Metodo para obtener el nombre.
         *
         * @return nombre.
         */
        public String getNombre() {
            return this.nombre;
        }

        /**
         * Metodo para obtener el apellido paterno.
         *
         * @return apellido paterno.
         */
        public String getPaterno() {
            return this.paterno;
        }

        /**
         * Metodo para obtener el apellido materno.
         *
         * @return apellido materno.
         */
        public String getMaterno() {
            return this.materno;
        }

        /**
         * Metodo para cambiar el nombre.
         *
         * @param nombre nuevo nombre.
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Metodo para cambiar el apellido paterno.
         *
         * @param p nuevo apellido paterno.
         */
        public void setPaterno(String p) {
            this.paterno = p;
        }

        /**
         * Metodo para cambiar el apellido materno.
         *
         * @param m nuevo apellido materno.
         */
        public void setMaterno(String m) {
            this.materno = m;
        }

        @Override
        public String toString() {
            return nombre + "," + paterno + "," + materno;
        }

        @Override
        public boolean equals(Object o) {
            Nombre n = (Nombre) o;
            if (n.getClass() != getClass()) {
                return false;
            }
            return nombre.equals(n.nombre) && paterno.equals(n.paterno) && materno.equals(n.materno);
        }
    }

    private int id=10;
    private Nombre nombre;
    private String c_repr;
    private int id_casilla;
    private int seccion;

    /**
     * Constructor de representante preliminar.
     *
     * @param id identificador del representante.
     * @param n nombre.
     * @param p apellido paterno.
     * @param m apellido materno.
     * @param c calidad de representante.
     * @param idc identificador de casilla.
     * @param s seccion.
     */
    public RPreliminar(/*int id,*/ String n, String p, String m, String c, int idc, int s) {
        this.id = id++;
        this.nombre = new Nombre(n, p, m);
        this.c_repr = c;
        this.id_casilla = idc;
        this.seccion = s;
    }

    /**
     * Metodo para cambiar el identificador.
     *
     * @param id nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo para cambiar el nombre.
     *
     * @param n nuevo nombre.
     */
    public void setNombre(String n) {
        this.nombre.setNombre(n);
    }

    /**
     * Metodo para cambiar el apellido materno.
     *
     * @param m nuevo apellido materno.
     */
    public void setAMaterno(String m) {
        this.nombre.setMaterno(m);
    }

    /**
     * Metodo para cambiar el apellido paterno.
     *
     * @param p nuevo apellido paterno.
     */
    public void setAPaterno(String p) {
        this.nombre.setPaterno(p);
    }
    /**
     * Metodo para cambiar la calidad del representante.
     * @param c nueva cadena.
     */
    public void setCalidadR(String c) {
        this.c_repr = c;
    }
    /**
     * Metodo para cambiar el id de casilla.
     * @param id nuevo id.
     */
    public void setCasilla(int id){
        this.id_casilla = id;
    }
    /**
     * Metodo para cambiar la seccion.
     * @param id nueva seccion.
     */
    public void setSeccion(int id){
        this.id_casilla = id;
    }
    /**
     * Metodo para obtener el ID.
     *
     * @return identificador.
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + c_repr + "," + id_casilla + "," + seccion;
    }

    @Override
    public boolean equals(Object o) {
        RPreliminar rp = (RPreliminar) o;
        if (rp.getClass() != getClass()) {
            return false;
        }
        return this.id == rp.id || this.nombre.equals(rp.nombre);
    }
}
