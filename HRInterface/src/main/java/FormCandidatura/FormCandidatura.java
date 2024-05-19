package FormCandidatura;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.oop.app.Departament;
import org.oop.app.MainView;
import org.oop.app.Pozitie;

@Route(value = "form-candidatura", layout = MainView.class)
@PageTitle("Formular Candidatură")
public class FormCandidatura extends Dialog {
    private final TextField nameField = new TextField("Nume");
    private final TextField addressField = new TextField("Adresă");
    private final TextField emailField = new TextField("Email");
    private final TextField phoneField = new TextField("Telefon");
    private final ComboBox<Departament> departmentComboBox = new ComboBox<>("Departament");
    private final ComboBox<Pozitie> positionComboBox = new ComboBox<>("Poziție");

    private final RadioButtonGroup<String> cvOptionGroup = new RadioButtonGroup<>();
    private final Upload cvUpload = new Upload();
    private final TextField personalWebsiteField = new TextField("Website personal");
    private final TextField linkedinField = new TextField("Link LinkedIn");

    private final Button saveButton = new Button("Salvează");
    private final Button updateButton = new Button("Modifică");
    private final Button cancelButton = new Button("Anulează");

    public FormCandidatura() {
        // Configurarea ComboBox-urilor
        departmentComboBox.setItems(/* Obțineți lista departamentelor din baza de date */);
        departmentComboBox.setItemLabelGenerator(Departament::getDenumire);

        positionComboBox.setItemLabelGenerator(Pozitie::getTitlu);

        // Configurarea opțiunilor pentru încărcarea CV-ului
        cvOptionGroup.setLabel("Opțiuni încărcare CV");
        cvOptionGroup.setItems("Încarcă CV", "Website personal", "Link LinkedIn");
        cvOptionGroup.addValueChangeListener(event -> {
            String selectedOption = event.getValue();
            cvUpload.setVisible("Încarcă CV".equals(selectedOption));
            personalWebsiteField.setVisible("Website personal".equals(selectedOption));
            linkedinField.setVisible("Link LinkedIn".equals(selectedOption));
        });

        // Configurarea Upload-ului pentru CV
        cvUpload.setUploadButton(new Button("Încarcă CV"));
        cvUpload.setAcceptedFileTypes("application/pdf", "application/msword", "text/plain");
        cvUpload.addSucceededListener(event -> {
            Notification.show("CV-ul a fost încărcat cu succes!");
        });

        // Ascunderea inițială a câmpurilor
        cvUpload.setVisible(false);
        personalWebsiteField.setVisible(false);
        linkedinField.setVisible(false);

        // Acțiuni pentru butoane
        saveButton.addClickListener(event -> {
            // Acțiunea care trebuie efectuată la apăsarea butonului "Salvează"
        });

        updateButton.addClickListener(event -> {
            // Acțiunea care trebuie efectuată la apăsarea butonului "Modifică"
        });

        cancelButton.addClickListener(event -> {
            // Acțiunea care trebuie efectuată la apăsarea butonului "Anulează"
        });

        // Butonul de închidere
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> this.close());

        // Layout-ul pentru butonul de închidere
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidthFull();
        headerLayout.add(closeButton);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        // Layout-urile pentru coloane
        VerticalLayout leftColumnLayout = new VerticalLayout(nameField, addressField, emailField, phoneField);
        VerticalLayout rightColumnLayout = new VerticalLayout(departmentComboBox, positionComboBox, cvOptionGroup, cvUpload, personalWebsiteField, linkedinField);

        // Layout-ul pentru butoane
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, updateButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        // Layout-ul general
        HorizontalLayout mainColumnsLayout = new HorizontalLayout(leftColumnLayout, rightColumnLayout);
        mainColumnsLayout.setWidthFull(); // Asigură că layout-ul ocupă toată lățimea

        VerticalLayout mainLayout = new VerticalLayout(headerLayout, mainColumnsLayout, buttonLayout);
        mainLayout.setSpacing(true); // Adaugă spațiu între elemente
        mainLayout.setPadding(true); // Adaugă padding la layout

        // Adăugarea layout-ului general în acest dialog
        add(mainLayout);
    }
}
