package com.scoutingapp2637.views.main;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainLayout extends AppLayout { 

	private static final long serialVersionUID = 1L;

	public MainLayout() {
		createHeader();
        createDrawer();
        // route to scout later
    }

    private void createHeader() {
        H4 logo = new H4("Scouting App");
        logo.addClassNames("text-l", "m-m");
        logo.getStyle().set("margin", "0");
        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(), 
          logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header); 

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("Match Scout", ScoutView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        RouterLink listLink7 = new RouterLink("Strategic Scout", QualScoutView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        RouterLink listLink2 = new RouterLink("Data Lookup", DataView.class); 
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());

        //RouterLink listLink3 = new RouterLink("Pit Data", PitDataView.class); 
        //listLink3.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink5 = new RouterLink("Pit Scout", PitDataView.class);
        listLink5.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink4 = new RouterLink("Team Summary", TeamSummaryView.class); 
        listLink4.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink6 = new RouterLink("Pit Data Lookup", PitDataLookup.class); 
        listLink4.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
            listLink,
            listLink7,
            listLink2,
            listLink4,
            listLink5,
            listLink6
        ));
    }
}