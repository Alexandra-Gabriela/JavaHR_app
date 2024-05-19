package Layout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import org.oop.app.MainView;

public class NavBar extends HorizontalLayout {
    public NavBar() {
        Button applyButton = new Button("Aplică Acum", event -> {
            // Deschide formularul de aplicare
        });

        Button applicationsButton = new Button("Candidaturi", event -> {
            // Afișează lista de candidaturi
        });

        RouterLink homeLink = new RouterLink("Acasă", MainView.class);

        add(homeLink, applyButton, applicationsButton);
    }
}
