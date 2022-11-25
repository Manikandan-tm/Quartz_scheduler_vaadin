package com.example.application.views;

import com.example.application.scheduler.SchedulerController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route("/scheduler")
@PermitAll
public class SchedulerView extends Div {
    private final SchedulerController schedulerController;
    public SchedulerView(SchedulerController schedulerController){
        this.schedulerController = schedulerController;

        Button scheduleButton = new Button("Schedule");
        scheduleButton.addClickListener(e -> this.schedulerController.runInstanceJob());
        scheduleButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_PRIMARY);
        scheduleButton.getElement().getStyle().set("margin-left", "auto").set("cursor", "pointer");
        add(scheduleButton);
    }
}
