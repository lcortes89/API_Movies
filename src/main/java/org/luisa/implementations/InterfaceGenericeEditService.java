package org.luisa.implementations;

public interface InterfaceGenericeEditService<T, S> {
    
    public S storeEntity(T dto);

}
