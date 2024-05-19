package FormCandidatura;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.oop.app.Candidatura;

import java.util.List;

public class ApplicationsGrid extends VerticalLayout {
    private final Grid<Candidatura> grid = new Grid<>(Candidatura.class);

    public ApplicationsGrid(List<Candidatura> applications) {
        grid.setItems(applications);
        add(grid);
    }
}

