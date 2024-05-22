package org.oop.app;

import FormCandidatura.FormCandidaturaView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route
public class MainView extends Div implements RouterLayout {

    public MainView() {
        Button applyButton = new Button("Aplică acum");
        applyButton.addClickListener(event -> openApplicationForm());
        add(applyButton);
    }

    private void openApplicationForm() {
        FormCandidaturaView applicationForm = new FormCandidaturaView();
        applicationForm.open(); // Deschide dialogul
    }

}
