package com.github.peholmst.soapsamples.client;

import com.github.peholmst.soapsamples.server.Contact;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;

public class ClientUI extends UI {

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = ClientUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }

    private Table contacts;
    private BeanItemContainer<Contact> contactsContainer;
    private Button refresh;
    private Button create;
    private Button edit;
    private Button delete;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.setSizeFull();

        contactsContainer = new BeanItemContainer<>(Contact.class);
        contacts = new Table();
        contacts.setContainerDataSource(contactsContainer);
        contacts.setSelectable(true);
        contacts.setSizeFull();
        contacts.setVisibleColumns("uuid", "firstName", "lastName", "gender");
        contacts.addValueChangeListener(this::updateButtonStates);
        contacts.setImmediate(true);
        root.addComponent(contacts);
        root.setExpandRatio(contacts, 1);

        refresh = new Button("Refresh", this::refresh);
        refresh.setDisableOnClick(true);

        create = new Button("Create", this::create);
        create.setDisableOnClick(true);

        edit = new Button("Edit", this::edit);
        edit.setDisableOnClick(true);

        delete = new Button("Delete", this::edit);
        delete.setDisableOnClick(true);

        HorizontalLayout buttons = new HorizontalLayout(refresh, create, edit, delete);
        buttons.setSpacing(true);
        root.addComponent(buttons);

        setContent(root);

        updateButtonStates(null);
    }

    private void updateButtonStates(ValueChangeEvent evt) {
        boolean hasSelection = contacts.getValue() != null;
        edit.setEnabled(hasSelection);
        delete.setEnabled(hasSelection);
    }

    private void refresh(ClickEvent evt) {
    }

    private void create(ClickEvent evt) {
    }

    private void edit(ClickEvent evt) {
    }

    private void delete(ClickEvent evt) {
    }

}
