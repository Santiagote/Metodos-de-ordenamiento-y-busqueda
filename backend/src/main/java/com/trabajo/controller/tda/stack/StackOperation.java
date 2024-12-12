package com.trabajo.controller.tda.stack;

import com.trabajo.controller.exception.OverFlowException;
import com.trabajo.controller.exception.ListEmptyException;
import com.trabajo.controller.tda.list.LinkedList;

public class StackOperation <E> extends LinkedList<E>{
    private Integer top;

    public StackOperation(Integer top) {
        this.top = top;
    }
    
    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }
    
    public void push(E dato) throws Exception {
        if(verify()) {
            add(dato, 0);
        } else {
            throw new OverFlowException("Pila llena");
        }
    }
    
    public E pop() throws ListEmptyException {
        if(isEmpty()) {
            throw new ListEmptyException("Pila vacia");
        } else {
            return deleteFirst();
        }
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
    
}
