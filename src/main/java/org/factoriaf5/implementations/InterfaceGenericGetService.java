package org.factoriaf5.implementations;

import java.util.List;

public interface InterfaceGenericGetService<T> {

    public List<T> getEntities();
    public T getById(Long id);

}
