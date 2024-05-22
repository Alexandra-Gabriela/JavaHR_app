package FormCandidatura;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
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
import org.oop.app.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@PageTitle("Formular Candidatură")
public class FormCandidaturaView extends Dialog {
    private static final long serialVersionUID = 1L;

    private FormCandidaturaController controller;

    private final TextField nameField = new TextField("Nume");
    private final TextField addressField = new TextField("Adresă");
    private final TextField emailField = new TextField("Email");
    private final TextField phoneField = new TextField("Telefon");
    private final ComboBox<Departament> departmentComboBox = new ComboBox<>("Departament");
    private final ComboBox<Pozitie> positionComboBox = new ComboBox<>("Poziție");
    private final Departament departamentCurent=new Departament();
    private final Pozitie pozitieCurenta=new Pozitie();
    private final RadioButtonGroup<String> cvOptionGroup = new RadioButtonGroup<>();
    private final Upload cvUpload = new Upload();
    private final TextField personalWebsiteField = new TextField("Website personal");
    private final TextField linkedinField = new TextField("Link LinkedIn");

    private final Button saveButton = new Button("Salvează");
    private final Button updateButton = new Button("Modifică");
    private final Button cancelButton = new Button("Anulează");

    private final Grid<Candidatura> aplicatiiGrid = new Grid<>(Candidatura.class);

    public FormCandidaturaView() {
        controller = new FormCandidaturaController();
        configureComboBox();
        configureCvOptionGroup();
        configureGrid();
        configureButtons();
        configureLayout();
    }

    private void configureComboBox() {
        List<Departament> departamente = controller.selectDepartamente();
        departmentComboBox.setItems(departamente);
        departmentComboBox.setItemLabelGenerator(Departament::getDenumire);

        departmentComboBox.addValueChangeListener(event -> {
            Departament departament = event.getValue();
            if (departament != null) {
                List<Pozitie> pozitii = controller.selectPozitii(departament);
                positionComboBox.setItems(pozitii);
            }
        });
        positionComboBox.setItemLabelGenerator(Pozitie::getTitlu);
    }

    private void configureCvOptionGroup() {
        cvOptionGroup.setLabel("Opțiuni încărcare CV");
        cvOptionGroup.setItems("Încarcă CV", "Website personal", "Link LinkedIn");
        cvOptionGroup.addValueChangeListener(event -> {
            String selectedOption = event.getValue();
            cvUpload.setVisible("Încarcă CV".equals(selectedOption));
            personalWebsiteField.setVisible("Website personal".equals(selectedOption));
            linkedinField.setVisible("Link LinkedIn".equals(selectedOption));
        });

        cvUpload.setUploadButton(new Button("Încarcă CV"));
        cvUpload.setAcceptedFileTypes("application/pdf", "application/msword", "text/plain");
        cvUpload.addSucceededListener(event -> {
            Notification.show("CV-ul a fost încărcat cu succes!");
        });

        cvUpload.setVisible(false);
        personalWebsiteField.setVisible(false);
        linkedinField.setVisible(false);
    }
    private void configureButtons() {
        saveButton.addClickListener(event -> {
            Candidat candidatCurent = new Candidat();
            candidatCurent.setNume(nameField.getValue());
            candidatCurent.setAdresa(addressField.getValue());
            candidatCurent.setEmail(emailField.getValue());
            candidatCurent.setNumarTelefon(phoneField.getValue());
            controller.saveModificari(candidatCurent);
            Notification.show("Candidatura a fost salvată!");
        });

        updateButton.addClickListener(event -> {
            Candidatura candidaturaSelectata = aplicatiiGrid.asSingleSelect().getValue();
            if (candidaturaSelectata != null) {
                controller.modificaCandidatura(candidaturaSelectata);
                Notification.show("Candidatura a fost actualizată!");
            } else {
                Notification.show("Selectează o candidatură pentru a o actualiza.");
            }
        });

        cancelButton.addClickListener(event -> this.close());

        // Creează un layout pentru butoane și configurează alinierea la dreapta
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, updateButton, cancelButton);
        buttonsLayout.setWidthFull();
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // Aliniază butoanele la dreapta

        // Adaugă layout-ul de butoane la layout-ul principal
        add(buttonsLayout);
    }

    private void configureGrid() {
        aplicatiiGrid.setColumns("pozitii", "dataAplicare", "status");
        aplicatiiGrid.setHeight("200px"); // Setează înălțimea grid-ului

        aplicatiiGrid.addColumn(candidatura -> {
            Interviu interviu = candidatura.getInterviu();
            return (interviu != null) ? interviu.getDataInterviu() : null;
        }).setHeader("Data interviu");

        aplicatiiGrid.addComponentColumn(candidatura -> {
            Button setInterviewButton = new Button("Setează data interviului", new Icon(VaadinIcon.CALENDAR));
            setInterviewButton.addClickListener(event -> {
                Dialog setInterviewDialog = new Dialog();
                DatePicker datePicker = new DatePicker("Data interviului");
                Button saveDateButton = new Button("Salvează");
                saveDateButton.addClickListener(saveEvent -> {
                    try {
                        Date dataInterviu = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        if (candidatura.getInterviu() == null) {
                            candidatura.setInterviu(new Interviu());
                        }
                        candidatura.getInterviu().setDataInterviu(dataInterviu);
                        controller.modificaCandidatura(candidatura);
                        Notification.show("Data interviului a fost setată!");
                        setInterviewDialog.close();
                        aplicatiiGrid.getDataProvider().refreshItem(candidatura);
                    } catch (Exception e) {
                        Notification.show("Eroare la setarea datei interviului: " + e.getMessage());
                    }
                });
                VerticalLayout dialogLayout = new VerticalLayout(datePicker, saveDateButton);
                setInterviewDialog.add(dialogLayout);
                setInterviewDialog.open();
            });
            return setInterviewButton;
        }).setHeader(""); // Setează un header gol pentru a elimina "Acțiuni"
    }

    private void configureLayout() {
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> this.close());
        HorizontalLayout closeLayout = new HorizontalLayout(closeButton);
        closeLayout.setWidthFull();
        closeLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout leftColumnLayout = new VerticalLayout(nameField, addressField, emailField, phoneField);
        VerticalLayout rightColumnLayout = new VerticalLayout(departmentComboBox, positionComboBox, cvOptionGroup, cvUpload, personalWebsiteField, linkedinField);

        // Creează și configurează layout-ul pentru butoane
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, updateButton, cancelButton);
        buttonsLayout.setWidthFull();
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // Aliniază butoanele la dreapta

        HorizontalLayout mainColumnsLayout = new HorizontalLayout(leftColumnLayout, rightColumnLayout);
        mainColumnsLayout.setWidthFull();

        // Adaugă grid-ul înaintea layout-ului pentru butoane
        VerticalLayout mainLayout = new VerticalLayout(closeLayout, mainColumnsLayout, aplicatiiGrid, buttonsLayout);
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);
        mainLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        mainLayout.setAlignItems(FlexComponent.Alignment.START);

        add(mainLayout);
    }



}
