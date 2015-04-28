/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.AbstractEntity;
import java.util.List;

/**
 *
 * @author Rados
 * @param <T>
 */
public interface GenericDao<T extends AbstractEntity> {

    public abstract T merge(T obj);

    public abstract T delete(Integer idOfObj, Class klasa);

    public abstract List<T> loadAllActive(Class klasa);

    public abstract T getElementById(Integer id, Class klasa);
}
