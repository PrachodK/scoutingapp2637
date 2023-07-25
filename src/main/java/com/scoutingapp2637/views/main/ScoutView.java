package com.scoutingapp2637.views.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Route(value="/scout", layout = MainLayout.class)
@PageTitle("ScoutingApp | Phantom Catz")
@VaadinSessionScope 
public class ScoutView extends VerticalLayout { 

	// private final long serialVersionUID = 1L;
	// long start = System.nanoTime();
	// long finish = System.nanoTime();
	// long timeElapsed = finish - start;

	private static final long serialVersionUID = 1L;

	// StopWatch watch = new StopWatch();

	int autoConesScored = 0;
	int autoCubesScored = 0;
	int autoTotalPoints = 0;

	int autoConesMissed = 0;
	int autoCubesMissed = 0;

	int teleopConesScored = 0;
	int teleopCubesScored = 0;
	int teleopTotalPoints = 0;

	int teleopConesMissed = 0;
	int teleopCubesMissed = 0;
	
	Set<Integer> numAutoPiecePickedUp = new HashSet<Integer>();
	
	Set<String> scoringZone = new HashSet<String>();
	
	int links = 0;

	double cycleTime = 0.0;
	double times = 0.0;

	public ScoutView() {
		add(
				new H1("Match Scouting Form"),
				buildForm()
				);
	}

	private Component buildForm() {

		// watch.reset();
		
		H3 header1 = new H3("Left");
		H3 header2 = new H3("Middle");
		H3 header3 = new H3("Right");
		H3 header4 = new H3("Left");
		H3 header5 = new H3("Middle");
		H3 header6 = new H3("Right");
		
		Button cob1 = new Button();
		Button cob2 = new Button();
		Button cob3 = new Button();
		Button cob4 = new Button();
		Button cob5 = new Button();
		Button cob6 = new Button();
		Button cob7 = new Button();
		Button cob8 = new Button();
		Button cob9 = new Button();
		Button cub1 = new Button();
		Button cub2 = new Button();
		Button cub3 = new Button();
		Button cub4 = new Button();
		Button cub5 = new Button();
		Button cub6 = new Button();
		Button cub7 = new Button();
		Button cub8 = new Button();
		Button cub9 = new Button();

		Button m1 = new Button();
		Button m2 = new Button();
		Button m3 = new Button();
		Button m4 = new Button();
		Button m5 = new Button();
		Button m6 = new Button();
		Button m7 = new Button();
		Button m8 = new Button();
		Button m9 = new Button();

		Button t1 = new Button();
		Button t2 = new Button();
		Button t3 = new Button();
		Button t4 = new Button();
		Button t5 = new Button();
		Button t6 = new Button();
		Button t7 = new Button();
		Button t8 = new Button();
		Button t9 = new Button();

		Button t_cob1 = new Button();
		Button t_cob2 = new Button();
		Button t_cob3 = new Button();
		Button t_cob4 = new Button();
		Button t_cob5 = new Button();
		Button t_cob6 = new Button();
		Button t_cob7 = new Button();
		Button t_cob8 = new Button();
		Button t_cob9 = new Button();

		Button t_cub1 = new Button();
		Button t_cub2 = new Button();
		Button t_cub3 = new Button();
		Button t_cub4 = new Button();
		Button t_cub5 = new Button();
		Button t_cub6 = new Button();
		Button t_cub7 = new Button();
		Button t_cub8 = new Button();
		Button t_cub9 = new Button();

		Button t_m1 = new Button();
		Button t_m2 = new Button();
		Button t_m3 = new Button();
		Button t_m4 = new Button();
		Button t_m5 = new Button();
		Button t_m6 = new Button();
		Button t_m7 = new Button();
		Button t_m8 = new Button();
		Button t_m9 = new Button();

		Button t_t1 = new Button();
		Button t_t2 = new Button();
		Button t_t3 = new Button();
		Button t_t4 = new Button();
		Button t_t5 = new Button();
		Button t_t6 = new Button();
		Button t_t7 = new Button();
		Button t_t8 = new Button();
		Button t_t9 = new Button();
		
		Button piece1 = new Button("Left");
		Button piece2 = new Button("Left Middle");
		Button piece3 = new Button("Right Middle");
		Button piece4 = new Button("Right");
		
		H3 autoPathTitle = new H3("Auton Pathing");
		H4 autoNote = new H4("NOTE: All directions are from DRIVER'S POINT OF VIEW");
		H4 autoNote2 = new H4("Auton Game Pieces Picked");
		HorizontalLayout autoPathHeader = new HorizontalLayout();
		autoPathHeader.add(piece1, piece2, piece3, piece4);
		
		IntegerField autoConesMissedField = new IntegerField("Cones Missed");
		autoConesMissedField.setMin(0);
		autoConesMissedField.setValue(0);
		autoConesMissedField.setStepButtonsVisible(true);
		
		IntegerField autoCubesMissedField = new IntegerField("Cubes Missed");
		autoCubesMissedField.setMin(0);
		autoCubesMissedField.setValue(0);
		autoCubesMissedField.setStepButtonsVisible(true);
	
		IntegerField teleopConesMissedField = new IntegerField("Cones Missed");
		teleopConesMissedField.setMin(0);
		teleopConesMissedField.setValue(0);
		teleopConesMissedField.setStepButtonsVisible(true);
		
		IntegerField teleopCubesMissedField = new IntegerField("Cubes Missed");
		teleopCubesMissedField.setMin(0);
		teleopCubesMissedField.setValue(0);
		teleopCubesMissedField.setStepButtonsVisible(true);

		ArrayList<String> lvlsList = new ArrayList<>();
		lvlsList.add(new String("Quals"));
		lvlsList.add(new String("Round 1"));
		lvlsList.add(new String("Round 2"));
		lvlsList.add(new String("Round 3"));
		lvlsList.add(new String("Round 4"));
		lvlsList.add(new String("Round 5"));
		lvlsList.add(new String("Finals"));

		ArrayList<String> robotsList = new ArrayList<>();
		robotsList.add(new String("r1"));
		robotsList.add(new String("r2"));
		robotsList.add(new String("r3"));
		robotsList.add(new String("b1"));
		robotsList.add(new String("b2"));
		robotsList.add(new String("b3"));
		
		ArrayList<String> startingLocationList = new ArrayList<>();
		startingLocationList.add(new String("Left"));
		startingLocationList.add(new String("Middle"));
		startingLocationList.add(new String("Right"));
		
		ArrayList<String> intakeFromList = new ArrayList<>();
		intakeFromList.add(new String("Ground"));
		intakeFromList.add(new String("Elevated"));
		intakeFromList.add(new String("Both"));
		intakeFromList.add(new String("None"));

		ArrayList<String> substationList = new ArrayList<>();
		substationList.add(new String("Single"));
		substationList.add(new String("Double"));
		substationList.add(new String("Both"));
		substationList.add(new String("N/A"));

		TextField initialField = new TextField("Scouter's Initials");
		initialField.setClearButtonVisible(true);
		H1 auto = new H1("Autonomous");
		H1 teleop = new H1("Teleop");
		H2 teleopGrid = new H2("Teleop Pieces Scored Grid");
		RadioButtonGroup<String> mobilityField = new RadioButtonGroup<String>();
		mobilityField.setLabel("Mobility?");
		mobilityField.setItems("Yes", "No");
		RadioButtonGroup<String> dockedField = new RadioButtonGroup<String>();
		dockedField.setLabel("Docked?");
		dockedField.setItems("Yes", "No");
		RadioButtonGroup<String> engagedField = new RadioButtonGroup<String>();
		engagedField.setLabel("Engaged?");
		engagedField.setItems("Yes", "No");
		IntegerField timeEngagedField = new IntegerField("Time it took to engage");
		timeEngagedField.setStepButtonsVisible(true);
		timeEngagedField.setMin(0);
		timeEngagedField.setValue(0);
		IntegerField superchargedNodesField = new IntegerField("# of supercharged nodes");
		superchargedNodesField.setStepButtonsVisible(true);;
		superchargedNodesField.setMin(0);
		superchargedNodesField.setValue(0);
		superchargedNodesField.setWidth("250px");
		H2 autoGrid = new H2("Autonomous Pieces Scored Grid");

		cub1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cub9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		cob9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		m9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t9.addThemeVariants(ButtonVariant.LUMO_SMALL);

		t_cub1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cub9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_cob9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_m9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t4.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t5.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t6.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t7.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t8.addThemeVariants(ButtonVariant.LUMO_SMALL);
		t_t9.addThemeVariants(ButtonVariant.LUMO_SMALL);
		
		piece1.addThemeVariants(ButtonVariant.LUMO_SMALL);
		piece2.addThemeVariants(ButtonVariant.LUMO_SMALL);
		piece3.addThemeVariants(ButtonVariant.LUMO_SMALL);
		piece4.addThemeVariants(ButtonVariant.LUMO_SMALL);

		HorizontalLayout hLayout = new HorizontalLayout();
		VerticalLayout vLayouta = new VerticalLayout();
		VerticalLayout vLayoutb = new VerticalLayout();
		VerticalLayout vLayoutc = new VerticalLayout();
		HorizontalLayout a = new HorizontalLayout();
		HorizontalLayout b = new HorizontalLayout();
		HorizontalLayout c = new HorizontalLayout();
		HorizontalLayout d = new HorizontalLayout();
		HorizontalLayout e = new HorizontalLayout();
		HorizontalLayout f = new HorizontalLayout();
		HorizontalLayout g = new HorizontalLayout();
		HorizontalLayout h = new HorizontalLayout();
		HorizontalLayout i = new HorizontalLayout();
		HorizontalLayout j = new HorizontalLayout();
		HorizontalLayout k = new HorizontalLayout();
		HorizontalLayout l = new HorizontalLayout();
		HorizontalLayout misses1 = new HorizontalLayout();
		HorizontalLayout misses2 = new HorizontalLayout();

		a.add(cub1, cub2, cub3);
		b.add(cob1, cob2, cob3);
		c.add(m1, m2, m3);
		d.add(t1, t2, t3);
		misses1.add(autoConesMissedField, autoCubesMissedField);
		misses2.add(teleopConesMissedField, teleopCubesMissedField);
		
		vLayouta.add(header1, d, c, b, a, misses1);

		e.add(cub4, cub5, cub6);
		f.add(cob4, cob5, cob6);
		g.add(m4, m5, m6);
		h.add(t4, t5, t6);
		vLayoutb.add(header2, h, g, f, e);

		i.add(cub7, cub8, cub9);
		j.add(cob7, cob8, cob9);
		k.add(m7, m8, m9);
		l.add(t7, t8, t9);
		vLayoutc.add(header3, l, k, j, i);
		vLayouta.setMargin(false);
		vLayoutb.setMargin(false);
		vLayoutc.setMargin(false);

		hLayout.setSpacing(false);
		hLayout.setPadding(false);

		hLayout.setAlignItems(Alignment.AUTO);
		hLayout.add(vLayouta, vLayoutb, vLayoutc);
		
		piece1.setDisableOnClick(false);
		piece2.setDisableOnClick(false);
		piece3.setDisableOnClick(false);
		piece4.setDisableOnClick(false);
		
		piece1.addClickListener(clickEvent -> {
			if (piece1.getText().equals("Left")) {
				piece1.setText("Left ✓");
				numAutoPiecePickedUp.add(1);
			} else {
				piece1.setText("Left");
				numAutoPiecePickedUp.remove(1);
			}
		});
		
		piece2.addClickListener(clickEvent -> {
			if (piece2.getText().equals("Left Middle")) {
				piece2.setText("Left Middle ✓");
				numAutoPiecePickedUp.add(2);
			} else {
				piece2.setText("Left Middle");
				numAutoPiecePickedUp.remove(2);
			}
		});
		
		piece3.addClickListener(clickEvent -> {
			if (piece3.getText().equals("Right Middle")) {
				piece3.setText("Right Middle ✓");
				numAutoPiecePickedUp.add(3);
			} else {
				piece3.setText("Right Middle");
				numAutoPiecePickedUp.remove(3);
			}
		});
		
		piece4.addClickListener(clickEvent -> {
			if (piece4.getText().equals("Right")) {
				piece4.setText("Right ✓");
				numAutoPiecePickedUp.add(4);
			} else {
				piece4.setText("Right");
				numAutoPiecePickedUp.remove(4);
			}
		});

		cub1.addClickListener(clickEvent -> {
			if (cob1.getText().isBlank()) {
				if (cub1.getText().isBlank()) {
					cub1.setText("Cube");
					autoLowCube();
				} else {
					cub1.setText("");
					autoLowCubeBack();
				}
			} else {
				cob1.setText("");
				autoLowConeBack();
				cub1.setText("Cube");
				autoLowCube();
			}
		});

		cub2.addClickListener(clickEvent -> {
			if (cob2.getText().isBlank()) {
				if (cub2.getText().isBlank()) {
					cub2.setText("Cube");
					autoLowCube();
				} else {
					cub2.setText("");
					autoLowCubeBack();
				}
			} else {
				cob2.setText("");
				autoLowConeBack();
				cub2.setText("Cube");
				autoLowCube();
			}
		});

		cub3.addClickListener(clickEvent -> {
			if (cob3.getText().isBlank()) {
				if (cub3.getText().isBlank()) {
					cub3.setText("Cube");
					autoLowCube();
				} else {
					cub3.setText("");
					autoLowCubeBack();
				}
			} else {
				cob3.setText("");
				autoLowConeBack();
				cub3.setText("Cube");
				autoLowCube();
			}
		});

		cub4.addClickListener(clickEvent -> {
			if (cob4.getText().isBlank()) {
				if (cub4.getText().isBlank()) {
					cub4.setText("Cube");
					autoLowCube();
				} else {
					cub4.setText("");
					autoLowCubeBack();
				}
			} else {
				cob4.setText("");
				autoLowConeBack();
				cub4.setText("Cube");
				autoLowCube();
			}
		});

		cub5.addClickListener(clickEvent -> {
			if (cob5.getText().isBlank()) {
				if (cub5.getText().isBlank()) {
					cub5.setText("Cube");
					autoLowCube();
				} else {
					cub5.setText("");
					autoLowCubeBack();
				}
			} else {
				cob5.setText("");
				autoLowConeBack();
				cub5.setText("Cube");
				autoLowCube();
			}
		});

		cub6.addClickListener(clickEvent -> {
			if (cob6.getText().isBlank()) {
				if (cub6.getText().isBlank()) {
					cub6.setText("Cube");
					autoLowCube();
				} else {
					cub6.setText("");
					autoLowCubeBack();
				}
			} else {
				cob6.setText("");
				autoLowConeBack();
				cub6.setText("Cube");
				autoLowCube();
			}
		});

		cub7.addClickListener(clickEvent -> {
			if (cob7.getText().isBlank()) {
				if (cub7.getText().isBlank()) {
					cub7.setText("Cube");
					autoLowCube();
				} else {
					cub7.setText("");
					autoLowCubeBack();
				}
			} else {
				cob7.setText("");
				autoLowConeBack();
				cub7.setText("Cube");
				autoLowCube();
			}
		});

		cub8.addClickListener(clickEvent -> {
			if (cob8.getText().isBlank()) {
				if (cub8.getText().isBlank()) {
					cub8.setText("Cube");
					autoLowCube();
				} else {
					cub8.setText("");
					autoLowCubeBack();
				}
			} else {
				cob8.setText("");
				autoLowConeBack();
				cub8.setText("Cube");
				autoLowCube();
			}
		});

		cub9.addClickListener(clickEvent -> {
			if (cob9.getText().isBlank()) {
				if (cub9.getText().isBlank()) {
					cub9.setText("Cube");
					autoLowCube();
				} else {
					cub9.setText("");
					autoLowCubeBack();
				}
			} else {
				cob9.setText("");
				autoLowConeBack();
				cub9.setText("Cube");
				autoLowCube();
			}
		});

		cob1.addClickListener(clickEvent -> {
			if (cub1.getText().isBlank()) {
				if (cob1.getText().isBlank()) {
					cob1.setText("Cone");
					autoLowCone();
				} else {
					cob1.setText("");
					autoLowConeBack();
				}
			} else {
				cub1.setText("");
				autoLowCubeBack();
				cob1.setText("Cone");
				autoLowCone();
			}
		});

		cob2.addClickListener(clickEvent -> {
			if (cub2.getText().isBlank()) {
				if (cob2.getText().isBlank()) {
					cob2.setText("Cone");
					autoLowCone();
				} else {
					cob2.setText("");
					autoLowConeBack();
				}
			} else {
				cub2.setText("");
				autoLowCubeBack();
				cob2.setText("Cone");
				autoLowCone();
			}
		});

		cob3.addClickListener(clickEvent -> {
			if (cub3.getText().isBlank()) {
				if (cob3.getText().isBlank()) {
					cob3.setText("Cone");
					autoLowCone();
				} else {
					cob3.setText("");
					autoLowConeBack();
				}
			} else {
				cub3.setText("");
				autoLowCubeBack();
				cob3.setText("Cone");
				autoLowCone();
			}
		});

		cob4.addClickListener(clickEvent -> {
			if (cub4.getText().isBlank()) {
				if (cob4.getText().isBlank()) {
					cob4.setText("Cone");
					autoLowCone();
				} else {
					cob4.setText("");
					autoLowConeBack();
				}
			} else {
				cub4.setText("");
				autoLowCubeBack();
				cob4.setText("Cone");
				autoLowCone();
			}
		});

		cob5.addClickListener(clickEvent -> {
			if (cub5.getText().isBlank()) {
				if (cob5.getText().isBlank()) {
					cob5.setText("Cone");
					autoLowCone();
				} else {
					cob5.setText("");
					autoLowConeBack();
				}
			} else {
				cub5.setText("");
				autoLowCubeBack();
				cob5.setText("Cone");
				autoLowCone();
			}
		});

		cob6.addClickListener(clickEvent -> {
			if (cub6.getText().isBlank()) {
				if (cob6.getText().isBlank()) {
					cob6.setText("Cone");
					autoLowCone();
				} else {
					cob6.setText("");
					autoLowConeBack();
				}
			} else {
				cub6.setText("");
				autoLowCubeBack();
				cob6.setText("Cone");
				autoLowCone();
			}
		});

		cob7.addClickListener(clickEvent -> {
			if (cub7.getText().isBlank()) {
				if (cob7.getText().isBlank()) {
					cob7.setText("Cone");
					autoLowCone();
				} else {
					cob7.setText("");
					autoLowConeBack();
				}
			} else {
				cub7.setText("");
				autoLowCubeBack();
				cob7.setText("Cone");
				autoLowCone();
			}
		});

		cob8.addClickListener(clickEvent -> {
			if (cub8.getText().isBlank()) {
				if (cob8.getText().isBlank()) {
					cob8.setText("Cone");
					autoLowCone();
				} else {
					cob8.setText("");
					autoLowConeBack();
				}
			} else {
				cub8.setText("");
				autoLowCubeBack();
				cob8.setText("Cone");
				autoLowCone();
			}
		});

		cob9.addClickListener(clickEvent -> {
			if (cub9.getText().isBlank()) {
				if (cob9.getText().isBlank()) {
					cob9.setText("Cone");
					autoLowCone();
				} else {
					cob9.setText("");
					autoLowConeBack();
				}
			} else {
				cub9.setText("");
				autoLowCubeBack();
				cob9.setText("Cone");
				autoLowCone();
			}
		});

		m1.addClickListener(clickEvent -> {
			if (m1.getText().isBlank()) {
				m1.setText("Cone");
				autoMidCone();
			} else {
				m1.setText("");
				autoMidConeBack();
			}
		});

		m2.addClickListener(clickEvent -> {
			if (m2.getText().isBlank()) {
				m2.setText("Cube");
				autoHighCube();
			} else {
				m2.setText("");
				autoHighCubeBack();
			}
		});

		m3.addClickListener(clickEvent -> {
			if (m3.getText().isBlank()) {
				m3.setText("Cone");
				autoMidCone();
			} else {
				m3.setText("");
				autoMidConeBack();
			}
		});

		m4.addClickListener(clickEvent -> {
			if (m4.getText().isBlank()) {
				m4.setText("Cone");
				autoMidCone();
			} else {
				m4.setText("");
				autoMidConeBack();
			}
		});

		m5.addClickListener(clickEvent -> {
			if (m5.getText().isBlank()) {
				m5.setText("Cube");
				autoHighCube();
			} else {
				m5.setText("");
				autoHighCubeBack();
			}
		});

		m6.addClickListener(clickEvent -> {
			if (m6.getText().isBlank()) {
				m6.setText("Cone");
				autoMidCone();
			} else {
				m6.setText("");
				autoMidConeBack();
			}
		});

		m7.addClickListener(clickEvent -> {
			if (m7.getText().isBlank()) {
				m7.setText("Cone");
				autoMidCone();
			} else {
				m7.setText("");
				autoMidConeBack();
			}
		});

		m8.addClickListener(clickEvent -> {
			if (m8.getText().isBlank()) {
				m8.setText("Cube");
				autoHighCube();
			} else {
				m8.setText("");
				autoHighCubeBack();
			}
		});

		m9.addClickListener(clickEvent -> {
			if (m9.getText().isBlank()) {
				m9.setText("Cone");
				autoMidCone();
			} else {
				m9.setText("");
				autoMidConeBack();
			}
		});

		t1.addClickListener(clickEvent -> {
			if (t1.getText().isBlank()) {
				t1.setText("Cone");
				autoHighCone();
			} else {
				t1.setText("");
				autoHighConeBack();
			}
		});

		t2.addClickListener(clickEvent -> {
			if (t2.getText().isBlank()) {
				t2.setText("Cube");
				autoHighCube();
			} else {
				t2.setText("");
				autoHighCubeBack();
			}
		});

		t3.addClickListener(clickEvent -> {
			if (t3.getText().isBlank()) {
				t3.setText("Cone");
				autoHighCone();
			} else {
				t3.setText("");
				autoHighConeBack();
			}
		});

		t4.addClickListener(clickEvent -> {
			if (t4.getText().isBlank()) {
				t4.setText("Cone");
				autoHighCone();
			} else {
				t4.setText("");
				autoHighConeBack();
			}
		});

		t5.addClickListener(clickEvent -> {
			if (t5.getText().isBlank()) {
				t5.setText("Cube");
				autoHighCube();
			} else {
				t5.setText("");
				autoHighCubeBack();
			}
		});

		t6.addClickListener(clickEvent -> {
			if (t6.getText().isBlank()) {
				t6.setText("Cone");
				autoHighCone();
			} else {
				t6.setText("");
				autoHighConeBack();
			}
		});

		t7.addClickListener(clickEvent -> {
			if (t7.getText().isBlank()) {
				t7.setText("Cone");
				autoHighCone();
			} else {
				t7.setText("");
				autoHighConeBack();
			}
		});

		t8.addClickListener(clickEvent -> {
			if (t8.getText().isBlank()) {
				t8.setText("Cube");
				autoHighCube();
			} else {
				t8.setText("");
				autoHighCubeBack();
			}
		});

		t9.addClickListener(clickEvent -> {
			if (t9.getText().isBlank()) {
				t9.setText("Cone");
				autoHighCone();
			} else {
				t9.setText("");
				autoHighConeBack();
			}
		});

		HorizontalLayout hLayout2 = new HorizontalLayout();
		VerticalLayout vLayouta2 = new VerticalLayout();
		VerticalLayout vLayoutb2 = new VerticalLayout();
		VerticalLayout vLayoutc2 = new VerticalLayout();
		HorizontalLayout a2 = new HorizontalLayout();
		HorizontalLayout b2 = new HorizontalLayout();
		HorizontalLayout c2 = new HorizontalLayout();
		HorizontalLayout d2 = new HorizontalLayout();
		HorizontalLayout e2 = new HorizontalLayout();
		HorizontalLayout f2 = new HorizontalLayout();
		HorizontalLayout g2 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		HorizontalLayout i2 = new HorizontalLayout();
		HorizontalLayout j2 = new HorizontalLayout();
		HorizontalLayout k2 = new HorizontalLayout();
		HorizontalLayout l2 = new HorizontalLayout();
		a2.add(t_cub1, t_cub2, t_cub3);
		b2.add(t_cob1, t_cob2, t_cob3);
		c2.add(t_m1, t_m2, t_m3);
		d2.add(t_t1, t_t2, t_t3);
		vLayouta2.add(header4, d2, c2, b2, a2, misses2);

		e2.add(t_cub4, t_cub5, t_cub6);
		f2.add(t_cob4, t_cob5, t_cob6);
		g2.add(t_m4, t_m5, t_m6);
		h2.add(t_t4, t_t5, t_t6);
		vLayoutb2.add(header5, h2, g2, f2, e2);

		i2.add(t_cub7, t_cub8, t_cub9);
		j2.add(t_cob7, t_cob8, t_cob9);
		k2.add(t_m7, t_m8, t_m9);
		l2.add(t_t7, t_t8, t_t9);
		vLayoutc2.add(header6, l2, k2, j2, i2);
		vLayouta2.setMargin(false);
		vLayoutb2.setMargin(false);
		vLayoutc2.setMargin(false);

		hLayout2.setSpacing(false);
		hLayout2.setPadding(false);

		hLayout2.setAlignItems(Alignment.AUTO);
		hLayout2.add(vLayouta2, vLayoutb2, vLayoutc2);


		t_cub1.addClickListener(clickEvent -> {
			if (t_cob1.getText().isBlank()) {
				if (t_cub1.getText().isBlank()) {
					t_cub1.setText("Cube");
					teleopLowCube();
					scoringZone.add("L1");
				} else {
					t_cub1.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L1");
				}
			} else {
				t_cob1.setText("");
				teleopLowConeBack();
				t_cub1.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub2.addClickListener(clickEvent -> {
			if (t_cob2.getText().isBlank()) {
				if (t_cub2.getText().isBlank()) {
					t_cub2.setText("Cube");
					teleopLowCube();
					scoringZone.add("L2");
				} else {
					t_cub2.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L2");
				}
			} else {
				t_cob2.setText("");
				teleopLowConeBack();
				t_cub2.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub3.addClickListener(clickEvent -> {
			if (t_cob3.getText().isBlank()) {
				if (t_cub3.getText().isBlank()) {
					t_cub3.setText("Cube");
					teleopLowCube();
					scoringZone.add("L3");
				} else {
					t_cub3.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L3");
				}
			} else {
				t_cob3.setText("");
				teleopLowConeBack();
				t_cub3.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub4.addClickListener(clickEvent -> {
			if (t_cob4.getText().isBlank()) {
				if (t_cub4.getText().isBlank()) {
					t_cub4.setText("Cube");
					teleopLowCube();
					scoringZone.add("L4");
				} else {
					t_cub4.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L4");
				}
			} else {
				t_cob4.setText("");
				teleopLowConeBack();
				t_cub4.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub5.addClickListener(clickEvent -> {
			if (t_cob5.getText().isBlank()) {
				if (t_cub5.getText().isBlank()) {
					t_cub5.setText("Cube");
					teleopLowCube();
					scoringZone.add("L5");
				} else {
					t_cub5.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L5");
				}
			} else {
				t_cob5.setText("");
				teleopLowConeBack();
				t_cub5.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub6.addClickListener(clickEvent -> {
			if (t_cob6.getText().isBlank()) {
				if (t_cub6.getText().isBlank()) {
					t_cub6.setText("Cube");
					teleopLowCube();
					scoringZone.add("L6");
				} else {
					t_cub6.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L6");
				}
			} else {
				t_cob6.setText("");
				teleopLowConeBack();
				t_cub6.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub7.addClickListener(clickEvent -> {
			if (t_cob7.getText().isBlank()) {
				if (t_cub7.getText().isBlank()) {
					t_cub7.setText("Cube");
					teleopLowCube();
					scoringZone.add("L7");
				} else {
					t_cub7.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L7");
				}
			} else {
				t_cob7.setText("");
				teleopLowConeBack();
				t_cub7.setText("Cube");
				teleopLowCube();
			}
		});

		t_cub8.addClickListener(clickEvent -> {
			if (t_cob8.getText().isBlank()) {
				if (t_cub8.getText().isBlank()) {
					t_cub8.setText("Cube");
					teleopLowCube();
					scoringZone.add("L8");
				} else {
					t_cub8.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L8");
				}
			} else {
				t_cob8.setText("");
				teleopLowConeBack();
				t_cub8.setText("Cube");
				teleopLowCube();
			}	
		});

		t_cub9.addClickListener(clickEvent -> {
			if (t_cob9.getText().isBlank()) {
				if (t_cub9.getText().isBlank()) {
					t_cub9.setText("Cube");
					teleopLowCube();
					scoringZone.add("L9");
				} else {
					t_cub9.setText("");
					teleopLowCubeBack();
					scoringZone.remove("L9");
				}
			} else {
				t_cob9.setText("");
				teleopLowConeBack();
				t_cub9.setText("Cube");
				teleopLowCube();
			}
		});

		t_cob1.addClickListener(clickEvent -> {
			if (t_cub1.getText().isBlank()) {
				if (t_cob1.getText().isBlank()) {
					t_cob1.setText("Cone");
					teleopLowCone();
					scoringZone.add("L1");
				} else {
					t_cob1.setText("");
					teleopLowConeBack();
					scoringZone.remove("L1");
				}
			} else {
				t_cub1.setText("");
				teleopLowCubeBack();
				t_cob1.setText("Cone");
				teleopLowCone();
			}
			
		});

		t_cob2.addClickListener(clickEvent -> {
			if (t_cub2.getText().isBlank()) {
				if (t_cob2.getText().isBlank()) {
					t_cob2.setText("Cone");
					teleopLowCone();
					scoringZone.add("L2");
				} else {
					t_cob2.setText("");
					teleopLowConeBack();
					scoringZone.remove("L2");
				}
			} else {
				t_cub2.setText("");
				teleopLowCubeBack();
				t_cob2.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob3.addClickListener(clickEvent -> {
			if (t_cub3.getText().isBlank()) {
				if (t_cob3.getText().isBlank()) {
					t_cob3.setText("Cone");
					teleopLowCone();
					scoringZone.add("L3");
				} else {
					t_cob3.setText("");
					teleopLowConeBack();
					scoringZone.remove("L3");
				}
			} else {
				t_cub3.setText("");
				teleopLowCubeBack();
				t_cob3.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob4.addClickListener(clickEvent -> {
			if (t_cub4.getText().isBlank()) {
				if (t_cob4.getText().isBlank()) {
					t_cob4.setText("Cone");
					teleopLowCone();
					scoringZone.add("L4");
				} else {
					t_cob4.setText("");
					teleopLowConeBack();
					scoringZone.remove("L4");
				}
			} else {
				t_cub4.setText("");
				teleopLowCubeBack();
				t_cob4.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob5.addClickListener(clickEvent -> {
			if (t_cub5.getText().isBlank()) {
				if (t_cob5.getText().isBlank()) {
					t_cob5.setText("Cone");
					teleopLowCone();
					scoringZone.add("L5");
				} else {
					t_cob5.setText("");
					teleopLowConeBack();
					scoringZone.remove("L5");
				}
			} else {
				t_cub5.setText("");
				teleopLowCubeBack();
				t_cob5.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob6.addClickListener(clickEvent -> {
			if (t_cub6.getText().isBlank()) {
				if (t_cob6.getText().isBlank()) {
					t_cob6.setText("Cone");
					teleopLowCone();
					scoringZone.add("L6");
				} else {
					t_cob6.setText("");
					teleopLowConeBack();
					scoringZone.remove("L6");
				}
			} else {
				t_cub6.setText("");
				teleopLowCubeBack();
				t_cob6.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob7.addClickListener(clickEvent -> {
			if (t_cub7.getText().isBlank()) {
				if (t_cob7.getText().isBlank()) {
					t_cob7.setText("Cone");
					teleopLowCone();
					scoringZone.add("L7");
				} else {
					t_cob7.setText("");
					teleopLowConeBack();
					scoringZone.remove("L7");
				}
			} else {
				t_cub7.setText("");
				teleopLowCubeBack();
				t_cob7.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob8.addClickListener(clickEvent -> {
			if (t_cub8.getText().isBlank()) {
				if (t_cob8.getText().isBlank()) {
					t_cob8.setText("Cone");
					teleopLowCone();
					scoringZone.add("L8");
				} else {
					t_cob8.setText("");
					teleopLowConeBack();
					scoringZone.remove("L8");
				}
			} else {
				t_cub8.setText("");
				teleopLowCubeBack();
				t_cob8.setText("Cone");
				teleopLowCone();
			}
		});

		t_cob9.addClickListener(clickEvent -> {
			if (t_cub9.getText().isBlank()) {
				if (t_cob9.getText().isBlank()) {
					t_cob9.setText("Cone");
					teleopLowCone();
					scoringZone.add("L9");
				} else {
					t_cob9.setText("");
					teleopLowConeBack();
					scoringZone.remove("L9");
				}
			} else {
				t_cub9.setText("");
				teleopLowCubeBack();
				t_cob9.setText("Cone");
				teleopLowCone();
			}
		});

		t_m1.addClickListener(clickEvent -> {
			if (t_m1.getText().isBlank()) {
				t_m1.setText("Cone");
				teleopMidCone();
				scoringZone.add("M1");
			} else {
				t_m1.setText("");
				teleopMidConeBack();
				scoringZone.remove("M1");
			}
		});

		t_m2.addClickListener(clickEvent -> {
			if (t_m2.getText().isBlank()) {
				t_m2.setText("Cube");
				teleopMidCube();
				scoringZone.add("M2");
			} else {
				t_m2.setText("");
				teleopMidCubeBack();
				scoringZone.remove("M2");
			}
		});

		t_m3.addClickListener(clickEvent -> {
			if (t_m3.getText().isBlank()) {
				t_m3.setText("Cone");
				teleopMidCone();
				scoringZone.add("M3");
			} else {
				t_m3.setText("");
				teleopMidConeBack();
				scoringZone.remove("M3");
			}
		});

		t_m4.addClickListener(clickEvent -> {
			if (t_m4.getText().isBlank()) {
				t_m4.setText("Cone");
				teleopMidCone();
				scoringZone.add("M4");
			} else {
				t_m4.setText("");
				teleopMidConeBack();
				scoringZone.remove("M4");
			}
		});

		t_m5.addClickListener(clickEvent -> {
			if (t_m5.getText().isBlank()) {
				t_m5.setText("Cube");
				teleopMidCube();
				scoringZone.add("M5");
			} else {
				t_m5.setText("");
				teleopMidCubeBack();
				scoringZone.remove("M5");
			}
		});

		t_m6.addClickListener(clickEvent -> {
			if (t_m6.getText().isBlank()) {
				t_m6.setText("Cone");
				teleopMidCone();
				scoringZone.add("M6");
			} else {
				t_m6.setText("");
				teleopMidConeBack();
				scoringZone.remove("M6");
			}
		});

		t_m7.addClickListener(clickEvent -> {
			if (t_m7.getText().isBlank()) {
				t_m7.setText("Cone");
				teleopMidCone();
				scoringZone.add("M7");
			} else {
				t_m7.setText("");
				teleopMidConeBack();
				scoringZone.remove("M7");
			}
		});

		t_m8.addClickListener(clickEvent -> {
			if (t_m8.getText().isBlank()) {
				t_m8.setText("Cube");
				teleopMidCube();
				scoringZone.add("M8");
			} else {
				t_m8.setText("");
				teleopMidCubeBack();
				scoringZone.remove("M8");
			}
		});

		t_m9.addClickListener(clickEvent -> {
			if (t_m9.getText().isBlank()) {
				t_m9.setText("Cone");
				teleopMidCone();
				scoringZone.add("M9");
			} else {
				t_m9.setText("");
				teleopMidConeBack();
				scoringZone.remove("M9");
			}
		});

		t_t1.addClickListener(clickEvent -> {
			if (t_t1.getText().isBlank()) {
				t_t1.setText("Cone");
				teleopHighCone();
				scoringZone.add("T1");
			} else {
				t_t1.setText("");
				teleopHighConeBack();
				scoringZone.remove("T1");
			}
		});

		t_t2.addClickListener(clickEvent -> {
			if (t_t2.getText().isBlank()) {
				t_t2.setText("Cube");
				teleopHighCube();
				scoringZone.add("T2");
			} else {
				t_t2.setText("");
				teleopHighCubeBack();
				scoringZone.remove("T2");
			}
		});

		t_t3.addClickListener(clickEvent -> {
			if (t_t3.getText().isBlank()) {
				t_t3.setText("Cone");
				teleopHighCone();
				scoringZone.add("T3");
			} else {
				t_t3.setText("");
				teleopHighConeBack();
				scoringZone.remove("T3");
			}
		});

		t_t4.addClickListener(clickEvent -> {
			if (t_t4.getText().isBlank()) {
				t_t4.setText("Cone");
				teleopHighCone();
				scoringZone.add("T4");
			} else {
				t_t4.setText("");
				teleopHighConeBack();
				scoringZone.remove("T4");
			}
		});

		t_t5.addClickListener(clickEvent -> {
			if (t_t5.getText().isBlank()) {
				t_t5.setText("Cube");
				teleopHighCube();
				scoringZone.add("T5");
			} else {
				t_t5.setText("");
				teleopHighCubeBack();
				scoringZone.remove("T5");
			}
		});

		t_t6.addClickListener(clickEvent -> {
			if (t_t6.getText().isBlank()) {
				t_t6.setText("Cone");
				teleopHighCone();
				scoringZone.add("T6");
			} else {
				t_t6.setText("");
				teleopHighConeBack();
				scoringZone.remove("T6");
			}
		});

		t_t7.addClickListener(clickEvent -> {
			if (t_t7.getText().isBlank()) {
				t_t7.setText("Cone");
				teleopHighCone();
				scoringZone.add("T7");
			} else {
				t_t7.setText("");
				teleopHighConeBack();
				scoringZone.remove("T7");
			}
		});

		t_t8.addClickListener(clickEvent -> {
			if (t_t8.getText().isBlank()) {
				t_t8.setText("Cube");
				teleopHighCube();
				scoringZone.add("T8");
			} else {
				t_t8.setText("");
				teleopHighCubeBack();
				scoringZone.remove("T8");
			}
		});

		t_t9.addClickListener(clickEvent -> {
			if (t_t9.getText().isBlank()) {
				t_t9.setText("Cone");
				teleopHighCone();
				scoringZone.add("T9");
			} else {
				t_t9.setText("");
				teleopHighConeBack();
				scoringZone.remove("T9");
			}
		});

		H1 endgame = new H1("Endgame");
		IntegerField teleopTimeEngaged = new IntegerField("Time it took to engage");
		teleopTimeEngaged.setMin(0);
		teleopTimeEngaged.setValue(0);
		teleopTimeEngaged.setWidth("250px");
		teleopTimeEngaged.setStepButtonsVisible(true);
		RadioButtonGroup<String> teleopDockedField = new RadioButtonGroup<String>();
		teleopDockedField.setLabel("Docked?");
		teleopDockedField.setItems("Yes", "No");
		RadioButtonGroup<String> teleopEngagedField = new RadioButtonGroup<String>();
		teleopEngagedField.setLabel("Engaged?");
		teleopEngagedField.setItems("Yes", "No");
		RadioButtonGroup<String> wasDefendedField = new RadioButtonGroup<String>();
		wasDefendedField.setLabel("Was robot defended?");
		wasDefendedField.setItems("Yes", "No");
		RadioButtonGroup<String> teleopIndOrDepField = new RadioButtonGroup<String>();
		teleopIndOrDepField.setLabel("Engaged independent or dependent?");
		teleopIndOrDepField.setItems("Independent", "Dependent", "N/A");
		RadioButtonGroup<String> parkedField = new RadioButtonGroup<String>();
		parkedField.setLabel("Parked?");
		parkedField.setItems("Yes", "No");
		ComboBox<String> substationField = new ComboBox<String>("Substation used", Collections.emptyList());
		substationField.setItems(substationList);
		substationField.setClearButtonVisible(true);
		IntegerField droppedPiecesField = new IntegerField("Number of Dropped Pieces");
		droppedPiecesField.setStepButtonsVisible(true);
		droppedPiecesField.setMin(0);
		droppedPiecesField.setValue(0);
		droppedPiecesField.setWidth("250px");
		IntegerField numOfPenaltiesField = new IntegerField("Number of Penalties");
		numOfPenaltiesField.setStepButtonsVisible(true);
		numOfPenaltiesField.setMin(0);
		numOfPenaltiesField.setValue(0);
		numOfPenaltiesField.setWidth("250px");
		TextArea commentsField = new TextArea("Comments");
		commentsField.setClearButtonVisible(true);
		commentsField.setWidthFull();
		TextArea autonPathField = new TextArea("Auton Path");
		autonPathField.setClearButtonVisible(true);
		autonPathField.setWidthFull();
		RadioButtonGroup<String> autonPathInfoField = new RadioButtonGroup<String>(); 
		autonPathInfoField.setLabel("Preload scored?");
		autonPathInfoField.setItems("Yes", "No");
		RadioButtonGroup<String> diedField = new RadioButtonGroup<String>();
		diedField.setLabel("Robot died? ");
		diedField.setItems("Yes", "No");
		TextArea penaltiesIncurred = new TextArea("Penalties incurred");
		penaltiesIncurred.setClearButtonVisible(true);
		penaltiesIncurred.setWidthFull();
		IntegerField overChargeStation = new IntegerField("Times traversed charge station for scoring");
		overChargeStation.setStepButtonsVisible(true);
		overChargeStation.setMin(0);
		overChargeStation.setValue(0);
		overChargeStation.setWidth("300px");
		IntegerField pushingCapField = new IntegerField("Pushing rating (1-4)");
		pushingCapField.setStepButtonsVisible(true);
		pushingCapField.setMin(0);
		pushingCapField.setMax(4);
		pushingCapField.setValue(0);
		pushingCapField.setWidth("250px");
		IntegerField offManeuverabilityField = new IntegerField("Maneuverability rating (1-4)");
		offManeuverabilityField.setStepButtonsVisible(true);
		offManeuverabilityField.setMin(0);
		offManeuverabilityField.setMax(4);
		offManeuverabilityField.setValue(0);
		offManeuverabilityField.setWidth("250px");
		IntegerField counterDefenseField = new IntegerField("Counter Defense rating (1-4))");
		counterDefenseField.setStepButtonsVisible(true);
		counterDefenseField.setMin(0);
		counterDefenseField.setMax(4);
		counterDefenseField.setValue(0);
		counterDefenseField.setWidth("250px");
		IntegerField intakeSpeedField = new IntegerField("Intake Speed rating (1-4)");
		intakeSpeedField.setStepButtonsVisible(true);
		intakeSpeedField.setMin(0);
		intakeSpeedField.setMax(4);
		intakeSpeedField.setValue(0);
		intakeSpeedField.setWidth("250px");
		TextField defensiveLocationField = new TextField("Where did the robot play defense mostly?");
		defensiveLocationField.setClearButtonVisible(true);
		defensiveLocationField.setWidthFull();
		ComboBox<String> autoStartLocationField = new ComboBox<String>("Robot starting location", Collections.emptyList());
		autoStartLocationField.setItems(startingLocationList);
		autoStartLocationField.setClearButtonVisible(true);
		ComboBox<String> intakeGroundOrElevate = new ComboBox<String>("Intook game pieces where?", Collections.emptyList());
		intakeGroundOrElevate.setItems(intakeFromList);
		intakeGroundOrElevate.setClearButtonVisible(true);
		TextField autoEndLocationField = new TextField("Robot ending location");
		autoEndLocationField.setClearButtonVisible(true);
		timeEngagedField.setWidth("250px");
		TextField event = new TextField("Event Name");
		event.setValue("2023cmptx");
		TextField matchNum = new TextField("Match Number");
		matchNum.setClearButtonVisible(true);
		ComboBox<String> lvlSelect = new ComboBox<String>("Match Level", Collections.emptyList());
		lvlSelect.setItems(lvlsList);
		lvlSelect.setClearButtonVisible(true);
		ComboBox<String> robotSelect = new ComboBox<String>("Robot", Collections.emptyList());
		robotSelect.setClearButtonVisible(true);
		robotSelect.setItems(robotsList);
		TextField teamNumField = new TextField("Team Number");
		teamNumField.setClearButtonVisible(true);
		teamNumField.setMaxLength(4);
		Div errorsLayout = new Div();
		Button button = new Button("Submit");

		intakeSpeedField.setValue(1);
		counterDefenseField.setValue(1);
		pushingCapField.setValue(1);
		offManeuverabilityField.setValue(1);
		
		
		dockedField.addValueChangeListener(e1 -> {
				if (dockedField.getValue().equals("No")) {
					timeEngagedField.setValue(0);
				}
		});
		
		engagedField.addValueChangeListener(e1 -> {
				if (engagedField.getValue().equals("No")) {
					timeEngagedField.setValue(0);
				}
		});
		
		teleopEngagedField.addValueChangeListener(e1 -> {
			if (teleopEngagedField.getValue().equals("Yes")) {
				parkedField.setValue("No");
			} else if (teleopEngagedField.getValue().equals("No")) {
				teleopTimeEngaged.setValue(0);
				teleopIndOrDepField.setValue("N/A");
			}
		});
		
		teleopDockedField.addValueChangeListener(e1 -> {
			if (teleopDockedField.getValue().equals("Yes")) {
				parkedField.setValue("No");
			} else if (teleopDockedField.getValue().equals("No")) {
				teleopTimeEngaged.setValue(0);
				teleopIndOrDepField.setValue("N/A");
			}
		});

		/* autoMissedCone.addClickListener((clickEvent -> {
			if (watch.isStarted()) {
				watch.stop();
				cycleTime += watch.getTime();
				times++;
				System.out.println("cycleTime: " + cycleTime + " Times: " + times);
				watch.reset();
				watch.start();

			} else if (watch.isStopped()) {
				watch.reset();
				watch.start();
			}
			autoConesMissed++;
		}));

		autoMissedCube.addClickListener((clickEvent -> {
			if (watch.isStarted()) {
				watch.stop();
				cycleTime += watch.getTime();
				times++;
				System.out.println("cycleTime: " + cycleTime + " Times: " + times);
				watch.reset();
				watch.start();

			} else if (watch.isStopped()) {
				watch.reset();
				watch.start();
			}
			autoCubesMissed++;
		}));
		
		teleopMissedCone.addClickListener((clickEvent -> {
			if (watch.isStarted()) {
				watch.stop();
				cycleTime += watch.getTime();
				times++;
				System.out.println("cycleTime: " + cycleTime + " Times: " + times);
				watch.reset();
				watch.start();

			} else if (watch.isStopped()) {
				watch.reset();
				watch.start();
			}
			teleopConesMissed++;
		}));
		
		teleopMissedCube.addClickListener((clickEvent -> {
			if (watch.isStarted()) {
				watch.stop();
				cycleTime += watch.getTime();
				times++;
				System.out.println("cycleTime: " + cycleTime + " Times: " + times);
				watch.reset();
				watch.start();

			} else if (watch.isStopped()) {
				watch.reset();
				watch.start();
			}
			teleopCubesMissed++;
		})); */
		
		button.addClickListener((clickEvent -> {
			
			calculateLinks();

			if (initialField.isEmpty() || matchNum.isEmpty() || lvlSelect.isEmpty() || robotSelect.isEmpty() ||
					teamNumField.isEmpty() || autoStartLocationField.isEmpty() || mobilityField.isEmpty() || dockedField.isEmpty() || engagedField.isEmpty() || autoStartLocationField.isEmpty() ||
					timeEngagedField.isEmpty()|| defensiveLocationField.isEmpty() || wasDefendedField.isEmpty() || teleopDockedField.isEmpty() || teleopEngagedField.isEmpty() || teleopTimeEngaged.isEmpty() ||
					teleopIndOrDepField.isEmpty() || parkedField.isEmpty() || substationField.isEmpty() || numOfPenaltiesField.isEmpty() ||
					diedField.isEmpty() || overChargeStation.isEmpty() || penaltiesIncurred.isEmpty() ||
					commentsField.isEmpty() || pushingCapField.isEmpty()  || counterDefenseField.isEmpty() || offManeuverabilityField.isEmpty()  || intakeSpeedField.isEmpty() || 
					autonPathField.isEmpty() || autonPathInfoField.isEmpty() || intakeGroundOrElevate.isEmpty() || superchargedNodesField.isEmpty()) {

				Notification notification = Notification
						.show("Please fill all data fields!");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

			} else {
	
				try { 	
					//System.out.println("While saving... cycleTime: " + cycleTime + " Times: " + times);
					teleopTotalPoints += (superchargedNodesField.getValue() * 3);
					saveForm((initialField.getValue()), event.getValue(), matchNum.getValue(),lvlSelect.getValue(),robotSelect.getValue(),teamNumField.getValue(), autoStartLocationField.getValue(), mobilityField.getValue().toString(),
							dockedField.getValue().toString(), engagedField.getValue().toString(), timeEngagedField.getValue().toString(), Integer.toString(autoTotalPoints), Integer.toString(autoConesScored), Integer.toString(autoCubesScored), autoConesMissedField.getValue().toString(), autoCubesMissedField.getValue().toString(),  autoEndLocationField.getValue(), numAutoPiecePickedUp.toString(), autonPathField.getValue(), autonPathInfoField.getValue().toString(), defensiveLocationField.getValue(), wasDefendedField.getValue().toString(), 
							(cycleTime/times), Integer.toString(teleopTotalPoints), Integer.toString(teleopConesScored), Integer.toString(teleopCubesScored), teleopConesMissedField.getValue().toString(), teleopCubesMissedField.getValue().toString(), superchargedNodesField.getValue().toString(), teleopDockedField.getValue().toString(), teleopEngagedField.getValue().toString(), 
							teleopTimeEngaged.getValue().toString(), teleopIndOrDepField.getValue(), parkedField.getValue().toString(), substationField.getValue(), intakeGroundOrElevate.getValue(), pushingCapField.getValue().toString(), counterDefenseField.getValue().toString(), offManeuverabilityField.getValue().toString(), intakeSpeedField.getValue().toString(),
							numOfPenaltiesField.getValue().toString(), droppedPiecesField.getValue().toString(), diedField.getValue(), overChargeStation.getValue().toString(), penaltiesIncurred.getValue(), commentsField.getValue());
					
					//initialField.setValue("");
					matchNum.setValue((Integer.parseInt(matchNum.getValue()) + 1) + "");
					//lvlSelect.setValue("");
					//robotSelect.setValue("");
					teamNumField.setValue("");
					mobilityField.setValue("");
					dockedField.setValue("");
					engagedField.setValue("");
					autoStartLocationField.setValue("");
					autoEndLocationField.setValue("");
					autonPathField.setValue("");
					autonPathInfoField.setValue("");
					wasDefendedField.setValue("");
					timeEngagedField.setValue(0);
					teleopDockedField.setValue("");
					teleopEngagedField.setValue("");
					teleopTimeEngaged.setValue(0);
					teleopIndOrDepField.setValue("");
					parkedField.setValue("");
					substationField.setValue("");
					intakeGroundOrElevate.setValue("");
					numOfPenaltiesField.setValue(0);
					droppedPiecesField.setValue(0);
					superchargedNodesField.setValue(0);
					diedField.setValue("");
					overChargeStation.setValue(0);
					penaltiesIncurred.setValue("");
					commentsField.setValue("");
					autoStartLocationField.setValue("");
					autoEndLocationField.setValue("");
					defensiveLocationField.setValue("");
					intakeSpeedField.setValue(1);
					counterDefenseField.setValue(1);
					pushingCapField.setValue(1);
					offManeuverabilityField.setValue(1);
					autoConesMissedField.setValue(0);
					autoCubesMissedField.setValue(0);
					teleopConesMissedField.setValue(0);
					teleopCubesMissedField.setValue(0);
					piece1.setText("Left");
					piece2.setText("Left Middle");
					piece3.setText("Right Middle");
					piece4.setText("Right");
					
					cub1.setText("");
					cub2.setText("");
					cub3.setText("");
					cub4.setText("");
					cub5.setText("");
					cub6.setText("");
					cub7.setText("");
					cub8.setText("");
					cub9.setText("");
					cob1.setText("");
					cob2.setText("");
					cob3.setText("");
					cob4.setText("");
					cob5.setText(""); 
					cob6.setText("");
					cob7.setText("");
					cob8.setText("");
					cob9.setText("");
					m1.setText("");
					m2.setText("");
					m3.setText("");
					m4.setText("");
					m5.setText("");
					m6.setText("");
					m7.setText("");
					m8.setText("");
					m9.setText("");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					t7.setText("");
					t8.setText("");
					t9.setText("");

					t_cub1.setText("");
					t_cub2.setText("");
					t_cub3.setText("");
					t_cub4.setText("");
					t_cub5.setText("");
					t_cub6.setText("");
					t_cub7.setText("");
					t_cub8.setText("");
					t_cub9.setText("");
					t_cob1.setText("");
					t_cob2.setText("");
					t_cob3.setText("");
					t_cob4.setText("");
					t_cob5.setText("");
					t_cob6.setText("");
					t_cob7.setText("");
					t_cob8.setText("");
					t_cob9.setText("");
					t_m1.setText("");
					t_m2.setText("");
					t_m3.setText("");
					t_m4.setText("");
					t_m5.setText("");
					t_m6.setText("");
					t_m7.setText("");
					t_m8.setText("");
					t_m9.setText("");
					t_t1.setText("");
					t_t2.setText("");
					t_t3.setText("");
					t_t4.setText("");
					t_t5.setText("");
					t_t6.setText("");
					t_t7.setText("");
					t_t8.setText("");
					t_t9.setText("");
					
					t_t8.setText("");
					t_t9.setText("");
					
					
					//watch.reset();
					cycleTime = 0;
					times = 0;
					autoConesMissed = 0;
					autoCubesMissed = 0;
					teleopConesMissed = 0;
					teleopCubesMissed = 0;
					autoConesScored = 0;
					autoCubesScored = 0;
					autoTotalPoints = 0;
					teleopConesScored = 0;
					teleopCubesScored = 0;
					teleopTotalPoints = 0;
					numAutoPiecePickedUp.clear();
					scoringZone.clear();
					links = 0;

					Notification notification = Notification
							.show("Data submitted!");
					notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace(); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}));
		
		HorizontalLayout horiz1 = new HorizontalLayout();
		horiz1.add(dockedField);
		horiz1.add(engagedField);
		
		HorizontalLayout horiz2 = new HorizontalLayout();
		horiz2.add(teleopDockedField);
		horiz2.add(teleopEngagedField);
		
		VerticalLayout formLayout = new VerticalLayout(initialField,  event,  matchNum,  lvlSelect,  robotSelect,  teamNumField, auto, autoStartLocationField, mobilityField,
				horiz1, timeEngagedField, autoEndLocationField, autoPathTitle, autoNote, autoNote2, autoPathHeader, autonPathField, autonPathInfoField, autoGrid, hLayout, teleop, teleopGrid, hLayout2, superchargedNodesField, droppedPiecesField, overChargeStation, defensiveLocationField, wasDefendedField, endgame, horiz2,
				teleopTimeEngaged, teleopIndOrDepField,  parkedField,  pushingCapField, counterDefenseField, offManeuverabilityField, intakeSpeedField, substationField,  intakeGroundOrElevate, numOfPenaltiesField, 
				diedField, penaltiesIncurred, commentsField, button);

		Div wrapperLayout = new Div(formLayout, errorsLayout);
		formLayout.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
		wrapperLayout.setWidth("100%");

		return wrapperLayout;
	}
	
	public void calculateLinks() {
		if (scoringZone.contains("L1") && (scoringZone.contains("L2") && (scoringZone.contains("L3")))) {
			links++;
		}
		
		if (scoringZone.contains("L4") && (scoringZone.contains("L5") && (scoringZone.contains("L6")))) {
			links++;
		}
		
		if (scoringZone.contains("L7") && (scoringZone.contains("L8") && (scoringZone.contains("L9")))) {
			links++;
		}
		
		if (scoringZone.contains("M1") && (scoringZone.contains("M2") && (scoringZone.contains("M3")))) {
			links++;
		}
		
		if (scoringZone.contains("M4") && (scoringZone.contains("M5") && (scoringZone.contains("M6")))) {
			links++;
		}
		
		if (scoringZone.contains("M7") && (scoringZone.contains("M8") && (scoringZone.contains("M9")))) {
			links++;
		}
		
		if (scoringZone.contains("T1") && (scoringZone.contains("T2") && (scoringZone.contains("T3")))) {
			links++;
		}
		
		if (scoringZone.contains("T4") && (scoringZone.contains("T5") && (scoringZone.contains("T6")))) {
			links++;
		}
		
		if (scoringZone.contains("T7") && (scoringZone.contains("T8") && (scoringZone.contains("T9")))) {
			links++;
		}
	}

	public void autoLowCone() {
		autoConesScored++;
		autoTotalPoints +=3;
	}
	public void autoLowCube() {
		autoCubesScored++;
		autoTotalPoints +=3;
	}
	public void autoMidCone() {
		autoConesScored++;
		autoTotalPoints +=4;
	}
	public void autoMidCube() {
		autoCubesScored++;
		autoTotalPoints +=4;
	}
	public void autoHighCone() {
		autoConesScored++;
		autoTotalPoints +=6;
	}
	public void autoHighCube() {
		autoCubesScored++;
		autoTotalPoints +=6;
	}

	public void teleopLowCone() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopConesScored++;
		teleopTotalPoints +=2;
	}
	public void teleopLowCube() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopCubesScored++;
		teleopTotalPoints +=2;
	}
	public void teleopMidCone() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopConesScored++;
		teleopTotalPoints +=3;
	}
	public void teleopMidCube() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopCubesScored++;
		teleopTotalPoints +=3;
	}
	public void teleopHighCone() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopConesScored++;
		teleopTotalPoints +=5;
	}
	public void teleopHighCube() {
//		if (watch.isStarted()) {
//			watch.stop();
//			cycleTime += watch.getTime();
//			times++;
//			System.out.println("cycleTime: " + cycleTime + " Times: " + times);
//			watch.reset();
//			watch.start();
//
//		} else if (watch.isStopped()) {
//			watch.reset();
//			watch.start();
//		}
		teleopCubesScored++;
		teleopTotalPoints +=5;
	}

	public void autoLowConeBack() {
		autoConesScored--;
		autoTotalPoints -=3;
	}
	public void autoLowCubeBack() {
		autoCubesScored--;
		autoTotalPoints -=3;
	}
	public void autoMidConeBack() {
		autoConesScored--;
		autoTotalPoints -=4;
	}
	public void autoMidCubeBack() {
		autoCubesScored--;
		autoTotalPoints -=4;
	}
	public void autoHighConeBack() {
		autoConesScored--;
		autoTotalPoints -=6;
	}
	public void autoHighCubeBack() {
		autoCubesScored--;
		autoTotalPoints -=6;
	}

	public void teleopLowConeBack() {
		times--;
		teleopConesScored--;
		teleopTotalPoints -=2;
	}
	public void teleopLowCubeBack() {
		times--;
		teleopCubesScored--;
		teleopTotalPoints -=2;
	}
	public void teleopMidConeBack() {
		times--;
		teleopConesScored--;
		teleopTotalPoints -=3;
	}
	public void teleopMidCubeBack() {
		times--;
		teleopCubesScored--;
		teleopTotalPoints -=3;
	}
	public void teleopHighConeBack() {
		times--;
		teleopConesScored--;
		teleopTotalPoints -=5;
	}
	public void teleopHighCubeBack() {
		times--;
		teleopCubesScored--;
		teleopTotalPoints -=5;
	}

	private void saveForm(String initialField, String event, String matchNum, String lvlSelect, String robotSelect, String teamNumField, String autoStartPosition, String mobilityField,
			String dockedField, String engagedField, String timeEngagedField, String autoTotalPoints, String autoConesScored, String autoCubesScored, String autoConesMissed, String autoCubesMissed, String autoEndPosition, String numAutoPiecePickedUp, String autonPathField, String autonPathInfoField, String defensiveLocation, String wasDefended, double cycleTime,  String teleopTotalPoints, String teleopConesScored, String teleopCubesScored, String teleopConesMissed, String teleopCubesMissed, String superchargedNodes, String teleopDockedField, String teleopEngagedField, 
			String teleopTimeEngaged, String teleopIndOrDepField, String parkedField, String substationField, String intakeGroundOrElevate, String pushingCapField, String counterDefenseField, String offManeuverabilityField, String intakeSpeedField,
			String numOfPenaltiesField, String droppedPiecesField, String robotDiedField, String overChargeStation, String penaltiesIncurred, String commentsField) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = " insert into MatchScouting (initials, team_number, match_number, match_event, match_level, robot, auto_start_location, auto_mobility, auto_docked, auto_engaged, auto_engaged_time, auto_cones_scored, auto_cubes_scored, auto_total_points, auto_missed_cone, auto_missed_cube, auto_end_location, numAutoPiecePickedUp, autonPath, autonPathInfo, defensive_location, was_defended, T_cones_scored, T_cubes_scored, T_total_points, teleop_missed_cone, teleop_missed_cube, T_docked, T_engaged, T_engaged_time, T_ind_or_dep, parked, substation, pushing_capability, counter_defense, offensive_maneuverability, intake_speed, dropped_pieces, num_penalties, robot_died, overChargeStation, penaltiesIncurred, comments, links_made, scoringZone, intakeGroundOrElevate, superchargedNodes)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString (1, initialField);
		preparedStmt.setInt (2, Integer.parseInt(teamNumField.trim()));
		preparedStmt.setInt   (3, Integer.parseInt(matchNum.trim()));
		preparedStmt.setString (4, event);
		preparedStmt.setString    (5, lvlSelect);
		preparedStmt.setString    (6, robotSelect);
		preparedStmt.setString    (7, autoStartPosition);
		preparedStmt.setString    (8, mobilityField);
		preparedStmt.setString    (9, dockedField);
		preparedStmt.setString    (10, engagedField);
		preparedStmt.setInt    (11, Integer.parseInt(timeEngagedField.trim()));
		preparedStmt.setInt    (12, Integer.parseInt(autoConesScored.trim()));
		preparedStmt.setInt    (13, Integer.parseInt(autoCubesScored.trim()));
		preparedStmt.setInt    (14, Integer.parseInt(autoTotalPoints.trim()));
		preparedStmt.setInt    (15, Integer.parseInt(autoConesMissed.trim()));
		preparedStmt.setInt    (16, Integer.parseInt(autoCubesMissed.trim()));
		preparedStmt.setString    (17, autoEndPosition);
		preparedStmt.setString    (18, numAutoPiecePickedUp);
		preparedStmt.setString    (19, autonPathField);
		preparedStmt.setString    (20, autonPathInfoField);
		preparedStmt.setString    (21, defensiveLocation);
		preparedStmt.setString    (22, wasDefended);
		preparedStmt.setInt    (23, Integer.parseInt(teleopConesScored.trim()));
		preparedStmt.setInt    (24, Integer.parseInt(teleopCubesScored.trim()));
		preparedStmt.setInt    (25, Integer.parseInt(teleopTotalPoints.trim()));
		preparedStmt.setInt    (26, Integer.parseInt(teleopConesMissed.trim()));
		preparedStmt.setInt    (27, Integer.parseInt(teleopCubesMissed.trim()));
		preparedStmt.setString    (28, teleopDockedField);
		preparedStmt.setString    (29, teleopEngagedField);
		preparedStmt.setInt    (30, Integer.parseInt(teleopTimeEngaged.trim()));
		preparedStmt.setString    (31, teleopIndOrDepField);
		preparedStmt.setString    (32, parkedField);
		preparedStmt.setString    (33, substationField);
		preparedStmt.setInt    (34, Integer.parseInt(pushingCapField.trim()));
		preparedStmt.setInt    (35, Integer.parseInt(counterDefenseField.trim()));
		preparedStmt.setInt    (36, Integer.parseInt(offManeuverabilityField.trim()));
		preparedStmt.setInt    (37, Integer.parseInt(intakeSpeedField.trim()));
		preparedStmt.setInt    (38, Integer.parseInt(droppedPiecesField.trim()));
		preparedStmt.setInt    (39, Integer.parseInt(numOfPenaltiesField.trim()));
		preparedStmt.setString    (40, robotDiedField);
		preparedStmt.setInt    (41, Integer.parseInt(overChargeStation));
		preparedStmt.setString    (42, penaltiesIncurred);
		preparedStmt.setString    (43, commentsField);
		preparedStmt.setInt    (44, links);
		preparedStmt.setString    (45, scoringZone.toString());
		preparedStmt.setString    (46, intakeGroundOrElevate);
		preparedStmt.setInt    (47, Integer.parseInt(superchargedNodes));

		// execute the preparedstatement
		preparedStmt.execute();

		//		/* Create an Object of PutItemRequest */
		//		PutItemRequest request = new PutItemRequest();
		//
		//		/* Setting Table Name */		
		//		request.setTableName("scout");
		//
		//		/* Create a Map of attributes */
		//		HashMap<String, AttributeValue> map = new HashMap<>();
		//
		//		map.put("initials", new AttributeValue(initialField));
		//		map.put("team_number", new AttributeValue().withN(teamNumField));
		//		map.put("match_number", new AttributeValue().withN(matchNum));   
		//		map.put("event", new AttributeValue(event));
		//		map.put("level", new AttributeValue(lvlSelect));
		//		map.put("robot", new AttributeValue(robotSelect));
		//		map.put("auto_mobility", new AttributeValue(mobilityField));
		//		map.put("auto_docked", new AttributeValue(dockedField));
		//		map.put("auto_docked_time", new AttributeValue(timeDockedField));
		//		map.put("auto_engaged", new AttributeValue(engagedField));
		//		map.put("auto_engaged_time", new AttributeValue(timeEngagedField));
		//		map.put("auto_cones_scored", new AttributeValue(autoConesScored));
		//		map.put("auto_cubes_scored", new AttributeValue(autoCubesScored));
		//		map.put("auto_total_points", new AttributeValue(autoTotalPoints));
		//		map.put("cycle_time", new AttributeValue(cycleTimeField));
		//		map.put("T_cones_scored", new AttributeValue(teleopConesScored));
		//		map.put("T_cubes_scored", new AttributeValue(teleopCubesScored));
		//		map.put("T_total_points", new AttributeValue(teleopTotalPoints));
		//		map.put("T_docked_time", new AttributeValue(teleopTimeDocked));
		//		map.put("T_docked", new AttributeValue(teleopDockedField));
		//		map.put("T_engaged_time", new AttributeValue(teleopTimeEngaged));
		//		map.put("T_engaged", new AttributeValue(teleopEngagedField));
		//		map.put("T_ind_or_dep", new AttributeValue(teleopIndOrDepField));
		//		map.put("parked", new AttributeValue(parkedField));
		//		map.put("substation", new AttributeValue(substationField));
		//		map.put("dropped_pieces", new AttributeValue(droppedPiecesField));
		//		map.put("num_penalties", new AttributeValue(numOfPenaltiesField));
		//		map.put("comments", new AttributeValue(commentsField));
		//
		//		request.setItem(map);
		//
		//		/* Put into scout table */
		//		PutItemResult result = dynamoDB.putItem(request);
		//		System.out.println(result);
		//
		//
		//		/* Create an Object of PutItemRequest for tsum table */
		//		PutItemRequest requestTsum = new PutItemRequest();
		//
		//		/* Setting Table Name */
		//		requestTsum.setTableName("tsum");
		//
		//		// getData
		//		DataView dv = new DataView();
		//		Object[] object = dv.getData(teamNumField);
		//		@SuppressWarnings("unchecked")
		//		ArrayList<DataForm> dataList = (ArrayList<DataForm>) object[1];
		//
		//		/* Create a Map of attributes */
		//		HashMap<String, AttributeValue> mapTsum = new HashMap<>();
		//		mapTsum.put("team_number", new AttributeValue().withN(teamNumField));
		//		mapTsum.put("avg_auto_mobility", new AttributeValue().withN(""+dataList.get(0).getAvgAutoMobility()));
		//		mapTsum.put("avg_auto_docked", new AttributeValue().withN(""+dataList.get(0).getAvgAutoDocked()));
		//		mapTsum.put("avg_auto_engaged", new AttributeValue().withN(""+dataList.get(0).getAvgAutoEngaged()));
		//		mapTsum.put("avg_auto_docked_time", new AttributeValue().withN(""+dataList.get(0).getAvgAutoDockedTime()));
		//		mapTsum.put("avg_auto_engaged_time", new AttributeValue().withN(""+dataList.get(0).getAvgAutoEngagedTime()));
		//		mapTsum.put("avg_auto_cones_scored", new AttributeValue().withN(""+dataList.get(0).getAvgAutoConesScored()));
		//		mapTsum.put("avg_auto_cubes_scored", new AttributeValue().withN(""+dataList.get(0).getAvgAutoCubesScored()));
		//		mapTsum.put("avg_auto_total_points", new AttributeValue().withN(""+dataList.get(0).getAvgAutoTotalPoints()));
		//		mapTsum.put("avg_cycle_time", new AttributeValue().withN(""+dataList.get(0).getAvgCycleTime()));
		//		mapTsum.put("avg_teleop_pieces_scored", new AttributeValue().withN(""+dataList.get(0).getAvgTeleopPiecesScored ()));
		//		mapTsum.put("avg_teleop_docked", new AttributeValue().withN(""+dataList.get(0).getAvgTeleopDocked()));
		//		mapTsum.put("avg_teleop_engaged", new AttributeValue().withN(""+dataList.get(0).getAvgTeleopEngaged()));
		//		mapTsum.put("avg_teleop_docked_time", new AttributeValue().withN(""+dataList.get(0).getAvgTeleopDockedTime()));
		//		mapTsum.put("avg_teleop_engaged_time", new AttributeValue().withN(""+dataList.get(0).getAvgTeleopEngagedTime()));
		//		mapTsum.put("avg_num_dropped_pieces", new AttributeValue().withN(""+dataList.get(0).getAvgNumDroppedPieces()));
		//		mapTsum.put("substation_used", new AttributeValue().withS(""+dataList.get(0).getSubstationUsed()));
		//		mapTsum.put("teleop_ind_or_dep", new AttributeValue().withS(""+dataList.get(0).getTeleopIndOrDep()));
		//		mapTsum.put("avg_num_penalties", new AttributeValue().withN(""+dataList.get(0).getAvgNumPenalties()));
		//		//mapTsum.put("r1r2_score", new AttributeValue().withN(""+dataList.get(0).getR1r2score()));
		//
		//		System.out.println(mapTsum);
		//		requestTsum.setItem(mapTsum);
		//
		//		/* Put into tsum table */
		//		PutItemResult resultTsum = dynamoDB.putItem(requestTsum);
		//		System.out.println(resultTsum);
		//

	}
}
