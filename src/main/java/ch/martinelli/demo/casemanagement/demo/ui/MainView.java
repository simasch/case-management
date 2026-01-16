package ch.martinelli.demo.casemanagement.demo.ui;

import ch.martinelli.demo.casemanagement.core.ui.layout.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

	public MainView() {
		add(new H1());
	}

}
