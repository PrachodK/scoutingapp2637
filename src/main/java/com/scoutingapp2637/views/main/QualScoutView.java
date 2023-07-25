package com.scoutingapp2637.views.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="/qualscout", layout = MainLayout.class) 
@PageTitle("ScoutingApp | Phantom Catz")
public class QualScoutView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public QualScoutView() {
		add(
				new H1("Strategic Scout Form"),
				buildForm()

				);

	}

	private Component buildForm() {

		TextField initialField = new TextField("Scouter's Initials");
		initialField.setClearButtonVisible(true);
		H1 auto = new H1("Autonomous");
		H1 teleop = new H1("Teleop");

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

		TextArea robotStartingPositionField = new TextArea("What was the robot's starting position?");
		robotStartingPositionField.setClearButtonVisible(true);
		TextArea robotPathingField = new TextArea("What was the robot's first move when the round started?");
		robotPathingField.setClearButtonVisible(true);
		TextArea pickedUpField = new TextArea("Where did they pick up game pieces from the field?");
		pickedUpField.setClearButtonVisible(true);
		TextArea placedOnGridField = new TextArea("Where did they place game pieces on the grid?");
		placedOnGridField.setClearButtonVisible(true);
		TextArea endPositionField = new TextArea("What was the robot's ending position?");
		endPositionField.setClearButtonVisible(true);
		
		TextArea directlyAfterAutonField = new TextArea("Where does the robot go directly after autonomous?");
		directlyAfterAutonField.setClearButtonVisible(true);
		TextArea enterCommunityField = new TextArea("How does the robot enter the community from the scoring table’s perspective? (Top/Charging Station/Bottom)");
		enterCommunityField.setClearButtonVisible(true);
		TextArea TdefenseAffectCycleField = new TextArea("How, if any, did defense affect the cycle time? Did the robot have good counter-defense?");
		TdefenseAffectCycleField.setClearButtonVisible(true);
		TextArea TeffectiveDefenseField = new TextArea("How, if any, did they play defense? Was it effective?");
		TeffectiveDefenseField.setClearButtonVisible(true);
		TextArea TpenaltiesIncurredField = new TextArea("What were the penalties that were incurred by this robot?");
		TpenaltiesIncurredField.setClearButtonVisible(true);
		TextArea ToverChargingStationField = new TextArea("Is this robot capable of going over the charging station?");
		ToverChargingStationField.setClearButtonVisible(true);
		TextArea TcollectPiecesSubstationField = new TextArea("Where is this robot able to collect game pieces in the substations? (Top/Bottom/Both) ");
		TcollectPiecesSubstationField.setClearButtonVisible(true);
		TextArea TrobotBalancedField = new TextArea("Is the robot well balanced? Or prone to tip.");
		TrobotBalancedField.setClearButtonVisible(true);
		TextArea commentsField = new TextArea("Comments");
		commentsField.setClearButtonVisible(true);
		IntegerField driverSkillField = new IntegerField("Driver Skill Rating (1-4)");
		driverSkillField.setStepButtonsVisible(true);
		driverSkillField.setMin(1);
		driverSkillField.setValue(1);
		driverSkillField.setMax(4);
		driverSkillField.setWidth("250px");
		IntegerField robotSpeedField = new IntegerField("Robot Speed (1-4)");
		robotSpeedField.setStepButtonsVisible(true);
		robotSpeedField.setMin(1);
		robotSpeedField.setValue(1);
		robotSpeedField.setMax(4);
		robotSpeedField.setWidth("250px");

		directlyAfterAutonField.setWidthFull();
		enterCommunityField.setWidthFull();
		robotStartingPositionField.setWidthFull();
		robotPathingField.setWidthFull();
		pickedUpField.setWidthFull();
		placedOnGridField.setWidthFull();
		endPositionField.setWidthFull();
		TdefenseAffectCycleField.setWidthFull();
		TeffectiveDefenseField.setWidthFull();
		TpenaltiesIncurredField.setWidthFull();
		ToverChargingStationField.setWidthFull();
		TcollectPiecesSubstationField.setWidthFull();
		TrobotBalancedField.setWidthFull();
		commentsField.setWidthFull();

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

		//i = 0;

		/*while (i == 0) {
			if (lowCargoScoredField.getValue() > lowCargoAttemptedField.getValue())
				lowCargoAttemptedField.setErrorMessage("Attempts cannot be less than the amount scored");
		}*/

		button.addClickListener((clickEvent -> {
			if (initialField.isEmpty() || event.isEmpty() || teamNumField.isEmpty() || matchNum.isEmpty() ||
					lvlSelect.isEmpty() || robotSelect.isEmpty() || teamNumField.isEmpty() || robotStartingPositionField.isEmpty() || robotPathingField.isEmpty() || pickedUpField.isEmpty() ||
					placedOnGridField.isEmpty() || endPositionField.isEmpty() || TdefenseAffectCycleField.isEmpty() || TeffectiveDefenseField.isEmpty() ||
					ToverChargingStationField.isEmpty() || TcollectPiecesSubstationField.isEmpty() || TeffectiveDefenseField.isEmpty() || directlyAfterAutonField.isEmpty() || enterCommunityField.isEmpty() ||
					TrobotBalancedField.isEmpty() || TpenaltiesIncurredField.isEmpty()  || commentsField.isEmpty() || robotSpeedField.isEmpty() || driverSkillField.isEmpty()) {

				Notification notification = Notification
						.show("Please fill all data fields!");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

			} else {

				Notification notification = Notification
						.show("Data submitted!");
				notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
				try {
					saveForm((initialField.getValue()), event.getValue(),
							matchNum.getValue(),lvlSelect.getValue(),robotSelect.getValue(),teamNumField.getValue(), robotStartingPositionField.getValue(),
							robotPathingField.getValue(), pickedUpField.getValue(), placedOnGridField.getValue(), endPositionField.getValue(),
							TdefenseAffectCycleField.getValue(), TeffectiveDefenseField.getValue(), ToverChargingStationField.getValue(), 
							TcollectPiecesSubstationField.getValue(), enterCommunityField.getValue(), directlyAfterAutonField.getValue(), TrobotBalancedField.getValue(), 
							TpenaltiesIncurredField.getValue(), commentsField.getValue(), robotSpeedField.getValue(), driverSkillField.getValue());
					//initialField.setValue("");
					teamNumField.setValue("");
					matchNum.setValue("");
					teamNumField.setValue("");
					robotSelect.setValue("");
					lvlSelect.setValue("");
					robotStartingPositionField.setValue("");
					robotPathingField.setValue("");
					pickedUpField.setValue("");
					placedOnGridField.setValue("");
					endPositionField.setValue("");
					TdefenseAffectCycleField.setValue("");
					TeffectiveDefenseField.setValue("");
					ToverChargingStationField.setValue("");
					TcollectPiecesSubstationField.setValue("");
					enterCommunityField.setValue("");
					directlyAfterAutonField.setValue("");
					TrobotBalancedField.setValue("");
					TpenaltiesIncurredField.setValue("");
					commentsField.setValue("");
					robotSpeedField.setValue(1);
					driverSkillField.setValue(1);
				
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace(); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}));

		VerticalLayout formLayout = new VerticalLayout(initialField,  event,  matchNum,  lvlSelect,  robotSelect,  teamNumField, auto, robotStartingPositionField,
				robotPathingField,  pickedUpField, placedOnGridField, endPositionField, teleop, directlyAfterAutonField, TdefenseAffectCycleField, enterCommunityField, TeffectiveDefenseField,  ToverChargingStationField,  TcollectPiecesSubstationField, 
				TrobotBalancedField,  TpenaltiesIncurredField,  driverSkillField, robotSpeedField, commentsField, button);

		Div wrapperLayout = new Div(formLayout, errorsLayout);
		formLayout.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
		wrapperLayout.setWidth("100%");

		return wrapperLayout;

	}

	private void saveForm(String initialField, String event, String matchNum, String lvlSelect, String robotSelect, String teamNumField, String robotStartingPosition, String robotPathingField, String pickedUpField,
			String placedOnGridField, String endPositionField, String TdefenseAffectCycleField, String TeffectiveDefenseField, String ToverChargingStationField, 
			String TcollectPiecesSubstationField, String enterCommunityField, String directlyAfterAutonField, String TrobotBalancedField, String TpenaltiesIncurredField, String commentsField, int robotSpeed, int driverSkill) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = " insert into StrategicScouting (initials, event, matchNum, level, robot, teamNum, robotStartingPosition, robotPathing, pickedUp, placedOnGrid, endPosition, TdefenseAffectCycle, TeffectiveDefense, ToverChargingStation, TcollectPiecesSubstation, T_enter_community, T_go_after_auton, TrobotBalanced, TpenaltiesIncurred, comments, robotSpeed, driverSkill)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString (1, initialField);
		preparedStmt.setString (2, event);
		preparedStmt.setString   (3, matchNum);
		preparedStmt.setString (4, lvlSelect);
		preparedStmt.setString    (5, robotSelect);
		preparedStmt.setString    (6, teamNumField);
		preparedStmt.setString    (7, robotStartingPosition);
		preparedStmt.setString    (8, robotPathingField);
		preparedStmt.setString    (9, pickedUpField);
		preparedStmt.setString    (10, placedOnGridField);
		preparedStmt.setString    (11, endPositionField);
		preparedStmt.setString    (12, TdefenseAffectCycleField);
		preparedStmt.setString    (13, TeffectiveDefenseField);
		preparedStmt.setString    (14, ToverChargingStationField);
		preparedStmt.setString    (15, TcollectPiecesSubstationField);
		preparedStmt.setString    (16, enterCommunityField);
		preparedStmt.setString    (17, directlyAfterAutonField);
		preparedStmt.setString    (18, TrobotBalancedField);
		preparedStmt.setString    (19, TpenaltiesIncurredField);
		preparedStmt.setString    (20, commentsField);
		preparedStmt.setInt		  (21, robotSpeed);
		preparedStmt.setInt    (22, driverSkill);

		// execute the preparedstatement
		preparedStmt.execute();
		//i = 1;
//		/* Create an Object of PutItemRequest */
//		PutItemRequest request = new PutItemRequest();
//
//		/* Setting Table Name */		
//		request.setTableName("qlscout");
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
//		map.put("start_position", new AttributeValue(robotStartingPosition));
//		map.put("robot_pathing", new AttributeValue(robotPathingField));
//		map.put("pick_up", new AttributeValue(pickedUpField));
//		map.put("placed_on_grid", new AttributeValue(placedOnGridField));
//		map.put("end_position", new AttributeValue(endPositionField));
//		map.put("T_robot_pathing", new AttributeValue(TRobotPathingField));
//		map.put("T_defense_affect", new AttributeValue(TdefenseAffectCycleField));
//		map.put("T_effective_defense", new AttributeValue(TeffectiveDefenseField));
//		map.put("T_over_charging_station", new AttributeValue(ToverChargingStationField));
//		map.put("T_collect_pieces_substation", new AttributeValue(TcollectPiecesSubstationField));
//		map.put("T_reach_in_grid", new AttributeValue(TreachInGridField));
//		map.put("T_man_or_zone", new AttributeValue(TmanOrZoneDefenseField));
//		map.put("T_robot_balanced", new AttributeValue(TrobotBalancedField));
//		map.put("T_penalties_incurred", new AttributeValue(TpenaltiesIncurredField));
//		map.put("comments", new AttributeValue(commentsField));
//
//		request.setItem(map);
//
//		/* Put into scout table */
//		PutItemResult result = dynamoDB.putItem(request);
//		System.out.println(result); 

	}

//	private void addToGrid() {
//
//		ArrayList<QualScoutForm> qlscoutList = new ArrayList<QualScoutForm>();
//		Class.forName("com.mysql.cj.jdbc.Driver"); 
//
//		// Set up the connection parameters
//		String url = "jdbc:mysql://50.87.253.209:3306/restockt_Scouting?useSSL=false&allowPublicKeyRetrieval=true";
//
//
//		String username = "restockt_2637";
//		String password = "K$JQE+4L#T5?";
//
//		// Establish the connection
//		Connection conn = DriverManager.getConnection(url, username, password);
//
//		// Use the connection to perform database operations here
//		// Create a statement object
//		Statement stmt = conn.createStatement();
//		String sql = "SELECT * FROM MatchScouting WHERE team_number = " + teamNumberEnter;
//		ResultSet item = stmt.executeQuery(sql);
//		while (item.next()) {
//			//System.out.println(item);
//			QualScoutForm qlscoutData = new QualScoutForm();
//			qlscoutData.setTeamNum(item.getString("team_number"));
//			qlscoutData.setStartPosition(item.getString("start_position"));
//			qlscoutData.setRobotPathing(item.getString("robot_pathing"));
//			qlscoutData.setPickUp(item.getString("pick_up"));
//			qlscoutData.setPlacedOnGrid(item.getString("placed_on_grid"));
//			qlscoutData.setEndPosition(item.getString("end_position"));
//			qlscoutData.setT_defense_effect(item.getString("T_defense_affect"));
//			qlscoutData.setT_effective_defense(item.getString("T_effective_defense"));
//			qlscoutData.setT_over_charging_station(item.getString("T_over_charging_station"));
//			qlscoutData.setT_collect_pieces_substation(item.getString("T_collect_pieces_substation"));
//			qlscoutData.setT_reach_in_grid(item.getString("T_reach_in_grid"));
//			qlscoutData.setT_man_or_zone(item.getString("T_man_or_zone"));
//			qlscoutData.setT_robot_balanced(item.getString("T_robot_balanced"));
//			qlscoutData.setT_penalties_incurred(item.getString("T_penalties_incurred"));
//			qlscoutData.setComments(item.getString("comments"));
//			//qlscoutData.setR1r2score(item.getDouble("r1r2_score"));
//			qlscoutList.add(qlscoutData);
//		}
//
//	}

}
