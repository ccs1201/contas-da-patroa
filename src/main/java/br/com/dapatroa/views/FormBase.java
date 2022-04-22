package br.com.dapatroa.views;

import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

import javax.annotation.PostConstruct;

public abstract class FormBase extends FormLayout {

    private Button btnSave = new Button();
    private Button btnDelete = new Button();
    private Button btnCancel = new Button();
    private boolean isEditMode;
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    protected Binder<Object> binder;

    @PostConstruct
    protected void configureFormLayout() {

        setSizeFull();
        configureButtonsLayout();

    }

    void configureButtonsLayout() {

        btnDelete.setVisible(false);

        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        buttonsLayout = new HorizontalLayout();

        buttonsLayout.add(btnSave, btnDelete, btnCancel);
        buttonsLayout.setMargin(true);

        buttonsLayout.setPadding(true);
        buttonsLayout.setSizeFull();

        configureButtonEvents();

        add(buttonsLayout, 2);

    }

    private void configureButtonEvents() {

        btnSave.addClickListener(saveEvent -> save());
        btnDelete.addClickListener(deleteEvent -> delete());
        btnCancel.addClickListener(cancelEvent -> clearForm());

    }

    protected abstract void save();

    protected abstract void delete();

    protected void clearForm() {

        // Desabilita o botão para excluir um Bean Cadastrado
        btnDelete.setVisible(false);

        // Seta editmode para falso, garantido que a proxima interação
        // seja com um bean novo
        isEditMode = false;

        clearBinder();

    }

    protected void clearBinder() {

        binder.readBean(null);

    }

    protected void showSucess(String message) {

        createNotification(message, NotificationVariant.LUMO_SUCCESS, 5000);

    }

    protected void showError(String message) {

        createNotification(message, NotificationVariant.LUMO_ERROR, 5000);

    }

    protected void showWarnig(String message) {
        createNotification(message, NotificationVariant.LUMO_CONTRAST, 10000);

    }

    private void createNotification(String message, NotificationVariant variant, int duration) {

        Notification n = new Notification(message);
        n.setDuration(duration);
        n.addThemeVariants(variant);
        n.setPosition(Notification.Position.TOP_CENTER);

        n.open();

    }
}
