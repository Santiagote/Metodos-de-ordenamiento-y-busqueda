package com.trabajo.controller.dao;

import java.util.Comparator;
import java.util.function.ToIntBiFunction;

import com.google.gson.Gson;
import com.trabajo.controller.dao.implement.AdapterDao;
import com.trabajo.controller.exception.ListEmptyException;
import com.trabajo.controller.tda.list.LinkedList;
import com.trabajo.models.Familia;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList listAll;

    private Gson g = new Gson();
    
    public FamiliaDao(){
        super(Familia.class);
    }

    public Familia getFamilia(){
        if(this.familia == null){
            this.familia = new Familia();
        }
        return this.familia;
    }

    public void setFamilia(Familia familia){
        this.familia = familia;
    }

    public LinkedList<Familia> getListAll() throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception{
        Integer id = getListAll().getSize()+1;
        familia.setId(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true; 
    }

    public Boolean update() throws Exception {
        this.merge(getFamilia(), getFamilia().getId() -1);
        this.listAll = listAll();
        return true;
    }

    public LinkedList order(Integer type_order, String atributo){
        LinkedList listita = listAll();
        if (!listAll().isEmpty()) {
            Familia[] lista = (Familia[]) listita.toArray();
            listita.reset();
            for(int i = 1; i < lista.length; i++){
                Familia aux = lista[i];
                int j = i - 1;
                while (j>=0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];
                }
                lista[j + 1] = aux;
            }
            listita.toList(lista);
        }
        return listita;
    }


    

    private Boolean verify(Familia a, Familia b, Integer type_order, String atributo){
        if(type_order == 1){
            if (atributo.equalsIgnoreCase("apellidos")) {
                return a.getApellidos().compareTo(b.getApellidos()) > 0;
            }else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() > b.getId();
            }
        }else{
            if (atributo.equalsIgnoreCase("apellidos")) {
                return a.getApellidos().compareTo(b.getApellidos()) < 0;
            }else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() < b.getId();
            }
        }
    return false;
    }

    //Metodos nuevos de ordenamiento
    //metodo quicksort
    public LinkedList<Familia> orderByQuickSort(Integer type_order, String atributo) throws Exception {
        LinkedList<Familia> listita = getListAll();
    
        if (!listita.isEmpty()) {

            Familia[] array = (Familia[]) listita.toArray();
    
            quickSort(array, 0, array.length - 1, type_order, atributo);
            
            listita.reset();
            listita.toList(array);
        }
    
        return listita;
    }
    
    private void quickSort(Familia[] arr, int low, int high, Integer type_order, String atributo) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high, type_order, atributo);
    
            quickSort(arr, low, partitionIndex - 1, type_order, atributo);
            quickSort(arr, partitionIndex + 1, high, type_order, atributo);
        }
    }
    
    private int partition(Familia[] arr, int low, int high, Integer type_order, String atributo) {
        Familia pivot = arr[high];
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot, type_order, atributo)) {  
                i++;
                Familia temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    
        Familia temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
    
        return i + 1;
    }
    
    private Boolean compare(Familia a, Familia b, Integer type_order, String atributo) {
        int comparison = 0;
    
        if (atributo.equalsIgnoreCase("apellidos")) {
            comparison = a.getApellidos().compareTo(b.getApellidos());
        } else if (atributo.equalsIgnoreCase("id")) {
            comparison = Integer.compare(a.getId(), b.getId());
        }

        return (type_order == 1) ? comparison <= 0 : comparison >= 0;
    }
    
    
    //Metodo mergeSort
    public LinkedList<Familia> mergeSort(Integer type_order, String atributo) throws Exception {
        LinkedList<Familia> listita = getListAll();

        if (!listita.isEmpty()) {
            Familia[] array = (Familia[]) listita.toArray();
            mergeSort(array, 0, array.length - 1, type_order, atributo);
            listita.reset();
            listita.toList(array);
        }

        return listita;
    }

    private void mergeSort(Familia[] array, int low, int high, Integer type_order, String atributo) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid, type_order, atributo);
            mergeSort(array, mid + 1, high, type_order, atributo);
            merge(array, low, mid, high, type_order, atributo);
        }
    }

    private void merge(Familia[] array, int low, int mid, int high, Integer type_order, String atributo) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        
        Familia[] left = new Familia[n1];
        Familia[] right = new Familia[n2];

        System.arraycopy(array, low, left, 0, n1);
        System.arraycopy(array, mid + 1, right, 0, n2);

        int i = 0, j = 0, k = low;
        
        while (i < n1 && j < n2) {
            if (compare(left[i], right[j], type_order, atributo)) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    private Boolean compareFamilia(Familia a, Familia b, Integer type_order, String atributo) {
        int comparison = 0;
    
        if (atributo.equalsIgnoreCase("apellidos")) {
            comparison = a.getApellidos().compareTo(b.getApellidos());
        } else if (atributo.equalsIgnoreCase("id")) {
            comparison = Integer.compare(a.getId(), b.getId());
        }
    
        return (type_order == 1) ? comparison <= 0 : comparison >= 0;
    }
    
    


    //Metodo shellSort

    public LinkedList<Familia> shellSort(Integer type_order, String atributo) throws Exception {
        LinkedList<Familia> listita = getListAll();
    
        if (!listita.isEmpty()) {
            Familia[] array = listita.toArray();
            int n = array.length;
    
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    Familia temp = array[i];
                    int j = i;
    
                    while (j >= gap && compare(array[j - gap], temp, type_order, atributo)) {
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
    
            listita.reset();
            for (Familia familia : array) {
                listita.add(familia);
            }
        }
    
        return listita;
    }
    

    
    //Fin de los metodos nuevos

    public LinkedList<Familia> buscar_apellidos(String texto){
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for(int i = 0; i < aux.length; i++){
                if (aux[i].getApellidos().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    //nuevos metodos de busqueda
    public LinkedList<Familia> findLinearSearch(String atributo, Object valor) {
        LinkedList<Familia> resultList = new LinkedList<>();
        try {
            LinkedList<Familia> listita = getListAll();
            Familia[] array = (Familia[]) listita.toArray();
            
            for (Familia f : array) {
                if (matchAttribute(f, atributo, valor)) {
                    resultList.add(f);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en findLinearSearch: " + e.getMessage());
        }
        return resultList;
    }
    
    private boolean matchAttribute(Familia familia, String atributo, Object valor) {
        switch (atributo.toLowerCase()) {
            case "apellidos":
                return familia.getApellidos().equalsIgnoreCase(valor.toString());
            case "id":
                return familia.getId() == Integer.parseInt(valor.toString());
            case "direccion":
                return familia.getDireccion().equalsIgnoreCase(valor.toString());
            case "usogenerador":
                return familia.getUsoGenerador().equalsIgnoreCase(valor.toString());
            default:
                return false;
        }
    }
    

    // Método para búsqueda binaria

    public LinkedList<Familia> findBinarySearch(String atributo, Object valor, Integer type) {
        LinkedList<Familia> resultList = new LinkedList<>();
        try {
            LinkedList<Familia> listita = order(type, atributo); 

            Familia[] array = (Familia[]) listita.toArray();
            int left = 0;
            int right = array.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                Object attributeValue = getAttributeValue(array[mid], atributo);

                if (attributeValue != null && attributeValue.equals(valor)) {
                    resultList.add(array[mid]);
                    break; 
                }

                if (compare(attributeValue, valor, type)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        } catch (Exception e) {
            System.err.println("Error en findBinarySearch: " + e.getMessage());
        }
        return resultList;
    }

        private Object getAttributeValue(Familia familia, String atributo) {
            switch (atributo.toLowerCase()) {
                case "apellidos":
                    return familia.getApellidos();
                case "id":
                    return familia.getId();
                case "direccion":
                    return familia.getDireccion();
                case "usogenerador":
                    return familia.getUsoGenerador();
                default:
                    return null;  
            }
        }
        private Boolean compare(Object a, Object b, Integer type) {
            switch (type) {
                case 0:
                    if (a instanceof Number) {
                        Number a1 = (Number) a;
                        Number b1 = (Number) b;
                        return a1.doubleValue() > b1.doubleValue();
                    } else {
                        return a.toString().compareTo(b.toString()) > 0;
                    }
                default:
                    if (a instanceof Number) {
                        Number a1 = (Number) a;
                        Number b1 = (Number) b;
                        return a1.doubleValue() < b1.doubleValue();
                    } else {
                        return a.toString().compareTo(b.toString()) < 0;
                    }
            }
        }
    
        //fin de los metodos de busqueda
        
} 


