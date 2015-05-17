/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.components;

import com.mycompany.methotels.persistance.GenericDao;
import com.mycompany.methotels.entities.AbstractEntity;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PropertyConduitSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Rados
 * @param <T>
 */
public class GenericEditor<T extends AbstractEntity> {

    @Inject
    private PropertyConduitSource conduit;
    @Inject
    private GenericDao genericDao;
    @Property
    @Persist
    private T bean;
    @Property
    private T row;
    @Property
    private List<T> list;

    @InjectComponent
    private Zone formZone;

    @InjectComponent
    private Zone gridZone;

    @Inject
    private Request request;

    @Inject
    private AjaxResponseRenderer ajaxRenderer;

    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private ComponentResources componentResources;
    private Class klasa;

    {
        PropertyConduit conduit1 = conduit.create(getClass(), "bean");
        klasa = conduit1.getPropertyType();
    }

    void onActivate() {
        list = genericDao.loadAllActive(klasa);
    }

    public BeanModel<T> getFormModel() {
        return beanModelSource.createEditModel(klasa,
                componentResources.getMessages()).exclude("id");
    }

    public BeanModel<T> getGridModel() {
        return beanModelSource.createDisplayModel(klasa,
                componentResources.getMessages()).exclude("id");
    }

    @CommitAfter
    Object onActionFromBrisanje(int id) {
        genericDao.delete(id, klasa);
        list = genericDao.loadAllActive(klasa);
        return request.isXHR() ? gridZone.getBody() : null;
    }

    @CommitAfter
    Object onActionFromEdit(int row) {
        bean = (T) genericDao.getElementById(row, klasa);
        return request.isXHR() ? formZone.getBody() : null;
    }

    @CommitAfter
    public Object onSuccess() {
        genericDao.merge(bean);
        list = genericDao.loadAllActive(klasa);
        try {
            bean = (T) klasa.newInstance();
        } catch (Exception ex) {
        }
        if (request.isXHR()) {
            ajaxRenderer.addRender(gridZone).addRender(formZone);
        }
        return request.isXHR() ? gridZone.getBody() : null;
    }

}
