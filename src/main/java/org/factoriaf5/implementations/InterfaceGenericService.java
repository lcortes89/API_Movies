package org.factoriaf5.implementations;

import java.util.List;

public interface InterfaceGenericService<T> {

    public List<T> getEntities();
    public T getById(Long id);

}
