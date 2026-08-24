package org.factoriaf5.implementations;

import java.util.List;

public interface InterfaceGenericGetService<T, S> {

    public List<T> getEntities();
    public T getById(Long id);
    public T getByName(S text);
    public List<T> getByNameStartingWith(String letter);
    public List<T> getBySyllable(String syllable);

}
