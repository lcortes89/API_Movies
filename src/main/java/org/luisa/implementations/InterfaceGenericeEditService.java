package org.factoriaf5.implementations;

public interface InterfaceGenericeEditService<T, S> {
    
    public S storeEntity(T dto);

}
