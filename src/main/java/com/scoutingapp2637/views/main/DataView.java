package com.scoutingapp2637.views.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="/data", layout = MainLayout.class) 
@PageTitle("ScoutingApp | Phantom Catz")
public class DataView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public DataView() {
		buildForm();
	}

	private void buildForm() {
		setSpacing(false);

		H1 scoutLookup = new H1("Scouting Data Lookup");
		add(scoutLookup);

		TextField teamNumberEnter = new TextField("Team Number");
		teamNumberEnter.setHelperText("Enter a team to lookup data. ");
		teamNumberEnter.setMaxLength(4);
		Button teamNumberEnterButton = new Button("Submit");
		HorizontalLayout formLayout = new HorizontalLayout(teamNumberEnter, teamNumberEnterButton);
		Div wrapperLayout = new Div(formLayout);
		formLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
		wrapperLayout.setWidth("100%");
		teamNumberEnterButton.addClickListener(clickEvent -> {
			try {
				createSummary(teamNumberEnter.getValue());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		add(wrapperLayout);

	}

	public Object[] getData(String teamNumberEnter) throws ClassNotFoundException, SQLException {

		// Iterator<Item> iterator = items.iterator();
		// Item item = null;
		ArrayList<ScoutForm> scoutList = new ArrayList<ScoutForm>();
		ArrayList<QualScoutForm> qualScoutList = new ArrayList<QualScoutForm>();
		ArrayList<DataForm> dataList = new ArrayList<DataForm>();
		int avgMobilityYes = 0;
		int avgMobilityTotal = 0;
		int dockedYes = 0;
		int dockedTotal = 0;
		int engagedYes = 0;
		int engagedTotal = 0;
		int piecesScoredAvg = 0;
		int cycleTimeAvg = 0;
		int avgTimeDocked = 0;
		int avgTimeEngaged = 0;
		int avgTeleopTimeDocked = 0;
		int avgTeleopTimeEngaged = 0;
		int avgCycleTime = 0;
		int TpiecesScoredAvg = 0;
		int TdockedYes = 0;
		int TdockedTotal = 0;		
		int TengagedYes = 0;
		int TengagedTotal = 0;
		int TdockedTimeAvg = 0;
		int TengagedTimeAvg = 0;
		int TParkedYes = 0;
		int TParkedTotal = 0;
		int avgAutoConesScored = 0;
		int avgAutoCubesScored = 0;
		int avgAutoTotalGridPoints = 0;
		int avgTeleopConesScored = 0;
		int avgTeleopCubesScored = 0;
		int avgTeleopTotalGridPoints = 0;
		int avgNumPenalties = 0;
		int avgNumDroppedPieces = 0;

		ArrayList<String> indOrDepList = new ArrayList<>();
		ArrayList<String> substationUsedList = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);

		// Use the connection to perform database operations here
		// Create a statement object
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM MatchScouting WHERE team_number = " + teamNumberEnter;
		ResultSet item = stmt.executeQuery(sql);
		while (item.next()) {
			ScoutForm scoutData = new ScoutForm();
			scoutData.setTeamNum(item.getString("team_number"));
			scoutData.setMatchNum(item.getInt("match_number"));
			scoutData.setEvent(item.getString("match_event"));
			scoutData.setMatchLvl(item.getString("match_level"));
			scoutData.setRobot(item.getString("robot"));
			scoutData.setInitials(item.getString("initials"));
			scoutData.setMobility(item.getString("auto_mobility"));
			avgMobilityTotal++;
			if (item.getString("auto_mobility").equals("Y")) {
				avgMobilityYes++;
			}
			scoutData.setDocked(item.getString("auto_docked"));
			dockedTotal++;
			if (item.getString("auto_docked").equals("Y")) {
				dockedYes++;
			}
			scoutData.setEngaged(item.getString("auto_engaged"));
			engagedTotal++;
			if (item.getString("auto_engaged").equals("Y")) {
				engagedYes++;
			}
			scoutData.setTimeEngaged(item.getInt("auto_engaged_time"));
			avgTimeEngaged += item.getInt("auto_engaged_time");
			scoutData.setAutoConesScored(item.getInt("auto_cones_scored"));
			avgAutoConesScored += item.getInt("auto_cones_scored");
			scoutData.setAutoCubesScored(item.getInt("auto_cubes_scored"));
			avgAutoCubesScored += item.getInt("auto_cubes_scored");
			scoutData.setAutoTotalPoints(item.getInt("auto_total_points"));
			avgAutoTotalGridPoints += item.getInt("auto_total_points");

			scoutData.setTeleopConesScored(item.getInt("T_cones_scored"));
			avgTeleopConesScored += item.getInt("T_cones_scored");
			scoutData.setTeleopCubesScored(item.getInt("T_cubes_scored"));
			avgTeleopCubesScored += item.getInt("T_cubes_scored");
			scoutData.setTeleopTotalPoints(item.getInt("T_total_points"));
			avgTeleopTotalGridPoints += item.getInt("T_total_points");

			scoutData.setTimeEngaged(item.getInt("T_engaged_time"));
			TengagedTimeAvg += item.getInt("T_engaged_time");
 
			scoutData.setTeleopDocked(item.getString("T_docked"));
			TdockedTotal++;
			if (item.getString("T_docked").equals("Y")) {
				TdockedYes++;
			}
			scoutData.setTeleopEngaged(item.getString("T_engaged"));
			TengagedTotal++;
			if (item.getString("T_engaged").equals("Y")) {
				TengagedYes++;
			}

			scoutData.setTeleopParked(item.getString("parked"));
			TParkedTotal++;
			if (item.getString("parked").equals("Y")) {
				TParkedYes++;
			}

			scoutData.setTeleopIndOrDependant(item.getString("T_ind_or_dep"));

			scoutData.setAutoStartLocation(item.getString("auto_start_location"));
			scoutData.setAutoEndLocation(item.getString("auto_end_location"));
			scoutData.setDefensiveLocation(item.getString("defensive_location"));
			scoutData.setPushingCap(item.getInt("pushing_capability"));
			scoutData.setOffManeuverability(item.getInt("offensive_maneuverability"));
			scoutData.setCounterDefense(item.getInt("counter_defense"));
			scoutData.setIntakeSpeed(item.getInt("intake_speed"));
			
			scoutData.setAutonPath(item.getString("autonPath"));
			scoutData.setAutonPathInfo(item.getString("autonPathInfo"));

			scoutData.setAutoMissedCones(item.getInt("auto_missed_cone"));
			scoutData.setAutoMissedCubes(item.getInt("auto_missed_cube"));
			scoutData.setTeleopMissedCones(item.getInt("teleop_missed_cone"));
			scoutData.setTeleopMissedCubes(item.getInt("teleop_missed_cube"));
			
			scoutData.setNumAutoPiecePickedUp(item.getString("numAutoPiecePickedUp"));

			indOrDepList.add(item.getString("T_ind_or_dep"));

			scoutData.setSubstationUsed(item.getString("substation"));
			substationUsedList.add(item.getString("substation"));

			scoutData.setNumOfDroppedPieces(item.getInt("dropped_pieces"));
			avgNumDroppedPieces += item.getInt("dropped_pieces");

			scoutData.setNumOfPenalties(item.getInt("num_penalties"));
			avgNumPenalties += item.getInt("num_penalties");
			scoutData.setComments(item.getString("comments"));
			scoutData.setRobotDied(item.getString("robot_died"));
			
			scoutData.setLinksMade(item.getInt("links_made"));
			scoutData.setScoringZone(item.getString("scoringZone"));
			scoutData.setintakeGroundOrElevate(item.getString("intakeGroundOrElevate"));
			scoutData.setSuperchargedNodes(item.getInt("superchargedNodes"));
			
			scoutList.add(scoutData);
		}

		Statement stmt2 = conn.createStatement();
		String sql2 = "SELECT * FROM StrategicScouting WHERE teamNum = " + teamNumberEnter;
		ResultSet item2 = stmt2.executeQuery(sql2);
		while (item2.next()) {
			QualScoutForm qlScoutData = new QualScoutForm();
			qlScoutData.setInitials(item2.getString("initials"));
			qlScoutData.setMatchNum(item2.getInt("matchNum"));
			qlScoutData.setEvent(item2.getString("event"));
			qlScoutData.setMatchLvl(item2.getString("level"));
			qlScoutData.setTeamNum(item2.getString("teamNum"));
			qlScoutData.setRobot(item2.getString("robot"));
			qlScoutData.setStartPosition(item2.getString("robotStartingPosition"));
			qlScoutData.setRobotPathing(item2.getString("robotPathing"));
			qlScoutData.setPickUp(item2.getString("pickedUp"));
			qlScoutData.setPlacedOnGrid(item2.getString("placedOnGrid"));
			qlScoutData.setEndPosition(item2.getString("endPosition"));
			qlScoutData.setT_defense_effect(item2.getString("TdefenseAffectCycle"));
			qlScoutData.setT_effective_defense(item2.getString("TeffectiveDefense"));
			qlScoutData.setT_over_charging_station(item2.getString("ToverChargingStation"));
			qlScoutData.setT_collect_pieces_substation(item2.getString("TcollectPiecesSubstation"));
			qlScoutData.setT_enter_community(item2.getString("T_enter_community"));
			qlScoutData.setT_go_after_auton(item2.getString("T_go_after_auton"));
			qlScoutData.setT_robot_balanced(item2.getString("TrobotBalanced"));
			qlScoutData.setT_penalties_incurred(item2.getString("TpenaltiesIncurred"));
			qlScoutData.setComments(item2.getString("comments"));
			qlScoutData.setDriverSkill(item2.getInt("driverSkill"));
			qlScoutData.setRobotSpeed(item2.getInt("robotSpeed"));
			qualScoutList.add(qlScoutData);

		}

		Statement stmt3 = conn.createStatement();
		String sql3 = "SELECT * FROM TeamSummary WHERE team_number = " + teamNumberEnter;
		ResultSet item3 = stmt3.executeQuery(sql3);
		while (item3.next()) {
			DataForm dataData = new DataForm();
			dataData.setTeamNum(item3.getString("team_number"));
			dataData.setAuton_avg_cones_scored(item3.getDouble("auton_avg_cones_scored"));
			dataData.setAuton_avg_cubes_scored(item3.getDouble("auton_avg_cubes_scored"));
			dataData.setAuton_peak_cones_scored(item3.getInt("auton_peak_cones_scored"));
			dataData.setAuton_peak_cubes_scored(item3.getInt("auton_peak_cubes_scored"));
			dataData.setAuton_percent_cubes_and_cones_accuracy(item3.getDouble("auton_percent_cubes_and_cones_accuracy"));
			dataData.setAuton_total_points(item3.getInt("auton_total_points"));
			dataData.setAuton_avg_points(item3.getDouble("auton_avg_points"));
			dataData.setAuton_percent_docked(item3.getDouble("auton_percent_docked"));
			dataData.setAuton_percent_engaged(item3.getDouble("auton_percent_engaged"));
			dataData.setAuton_percent_mobility(item3.getDouble("auton_percent_mobility"));
			dataData.setAuton_avg_docking_time(item3.getDouble("auton_avg_docking_time"));
			dataData.setAuton_avg_engaging_time(item3.getDouble("auton_avg_engaging_time"));
			dataData.setTeleop_avg_cones_scored(item3.getDouble("teleop_avg_cones_scored"));
			dataData.setTeleop_avg_cubes_scored(item3.getDouble("teleop_avg_cubes_scored"));
			dataData.setTeleop_peak_cubes_scored(item3.getInt("teleop_peak_cubes_scored"));
			dataData.setTeleop_peak_cones_scored(item3.getInt("teleop_peak_cones_scored"));
			dataData.setTeleop_percent_cubes_and_cones_accuracy(item3.getDouble("teleop_percent_cubes_and_cones_accuracy"));
			dataData.setTeleop_avg_dropped_pieces(item3.getDouble("teleop_avg_dropped_pieces"));
			dataData.setTeleop_total_points(item3.getInt("teleop_total_points"));
			dataData.setTeleop_avg_points(item3.getDouble("teleop_avg_points"));			
			dataData.setTeleop_avg_cycle_time(item3.getDouble("teleop_avg_cycle_time"));
			dataData.setTeleop_intake_state(item3.getString("teleop_intake_state"));
			dataData.setTeleop_total__times_traversed(item3.getInt("teleop_total__times_traversed"));
			dataData.setEndgame_percent_docked(item3.getDouble("endgame_percent_docked"));
			dataData.setEndgame_percent_engaged(item3.getDouble("endgame_percent_engaged"));
			dataData.setEndgame_percent_parked(item3.getDouble("endgame_percent_parked"));
			dataData.setEndgame_times_robot_died(item3.getInt("endgame_times_robot_died"));
			dataData.setEndgame_percent_defended(item3.getDouble("endgame_percent_defended"));
			dataData.setEndgame_avg_pushing_power(item3.getDouble("endgame_avg_pushing_power"));
			dataData.setEndgame_avg_counter_denfense(item3.getDouble("endgame_avg_counter_denfense"));
			dataData.setAuton_peak_points(item3.getInt("auton_peak_points"));
			dataData.setAuton_lowest_points(item3.getInt("auton_lowest_points"));
			dataData.setTeleop_peak_points(item3.getInt("teleop_peak_points"));
			dataData.setTeleop_lowest_points(item3.getInt("teleop_lowest_points"));
			dataList.add(dataData);
		}

		return new Object[] { scoutList, dataList, qualScoutList};
	}

	public void createSummary(String teamNumberEnter) throws ClassNotFoundException, SQLException {
		removeAll();
		buildForm();

		// getData
		Object[] object = getData(teamNumberEnter);
		@SuppressWarnings("unchecked")
		ArrayList<ScoutForm> scoutList = (ArrayList<ScoutForm>) object[0];
		@SuppressWarnings("unchecked")
		ArrayList<DataForm> dataList = (ArrayList<DataForm>) object[1];	
		@SuppressWarnings("unchecked")
		ArrayList<QualScoutForm> qlScoutList = (ArrayList<QualScoutForm>) object[2];

		// grid
		Grid<ScoutForm> grid = new Grid<>(ScoutForm.class, false);
		grid.addColumn(ScoutForm::getInitials).setHeader("Scouter's Initials").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeamNum).setHeader("Team Number").setAutoWidth(true);
		grid.addColumn(ScoutForm::getMatchNum).setHeader("Match Number").setAutoWidth(true);
		grid.addColumn(ScoutForm::getEvent).setHeader("Event Name").setAutoWidth(true);
		grid.addColumn(ScoutForm::getMatchLvl).setHeader("Match Level").setAutoWidth(true);
		grid.addColumn(ScoutForm::getRobot).setHeader("Robot").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoStartLocation).setHeader("Auto Start Location").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoEndLocation).setHeader("Auto End Location").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutonPath).setHeader("Auton Path").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutonPathInfo).setHeader("Auto Path Info").setAutoWidth(true);
		grid.addColumn(ScoutForm::getNumAutoPiecePickedUp).setHeader("Auton Pieces Picked").setAutoWidth(true);
		grid.addColumn(ScoutForm::getMobility).setHeader("Autonomous Mobility").setAutoWidth(true);
		grid.addColumn(ScoutForm::getDocked).setHeader("Docked? ").setAutoWidth(true);
		grid.addColumn(ScoutForm::getEngaged).setHeader("Engaged? ").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTimeDocked).setHeader("Dock Time").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTimeEngaged).setHeader("Engage Time").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoConesScored).setHeader("Auto Cones Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoCubesScored).setHeader("Auto Cubes Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoMissedCones).setHeader("Auto Cones Missed").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoMissedCubes).setHeader("Auto Cubes Missed").setAutoWidth(true);
		grid.addColumn(ScoutForm::getAutoTotalPoints).setHeader("Auto Total Grid Points Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopConesScored).setHeader("Teleop Cones Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopCubesScored).setHeader("Teleop Cubes Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopMissedCones).setHeader("Teleop Cones Missed").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopMissedCubes).setHeader("Teleop Cubes Missed").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopTotalPoints).setHeader("Teleop Total Grid Points Scored").setAutoWidth(true);
		grid.addColumn(ScoutForm::getSuperchargedNodes).setHeader("Supercharged Nodes").setAutoWidth(true);
		grid.addColumn(ScoutForm::getLinksMade).setHeader("Teleop Links Made").setAutoWidth(true);
		grid.addColumn(ScoutForm::getScoringZone).setHeader("Teleop Scoring Zone").setAutoWidth(true);
		grid.addColumn(ScoutForm::getDefensiveLocation).setHeader("Defensive Location").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopDocked).setHeader("Teleop Docked? ").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopEngaged).setHeader("Teleop Engaged?").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopTimeDocked).setHeader("Teleop Dock Time").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopTimeEngaged).setHeader("Teleop Engaged Time").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopIndOrDependant).setHeader("Teleop Independant or Dependant? ").setAutoWidth(true);
		grid.addColumn(ScoutForm::getTeleopParked).setHeader("Parked? ").setAutoWidth(true);
		grid.addColumn(ScoutForm::getSubstationUsed).setHeader("Substation Used").setAutoWidth(true);
		grid.addColumn(ScoutForm::getintakeGroundOrElevate).setHeader("Intake from where").setAutoWidth(true);
		grid.addColumn(ScoutForm::getPushingCap).setHeader("Pushing Capability").setAutoWidth(true);
		grid.addColumn(ScoutForm::getOffManeuverability).setHeader("Offensive Maneuverability").setAutoWidth(true);
		grid.addColumn(ScoutForm::getCounterDefense).setHeader("Counter Defense").setAutoWidth(true);
		grid.addColumn(ScoutForm::getIntakeSpeed).setHeader("Intake Speed").setAutoWidth(true);
		grid.addColumn(ScoutForm::getNumOfPenalties).setHeader("Number of Penalties").setAutoWidth(true);
		grid.addColumn(ScoutForm::getNumOfDroppedPieces).setHeader("Number of Dropped Pieces").setAutoWidth(true);
		grid.addColumn(ScoutForm::getComments).setHeader("Comments").setAutoWidth(true);
		grid.setItems(scoutList);
		add(grid);

		// dataGrid
		Grid<DataForm> dataGrid = new Grid<>(DataForm.class, false);
		dataGrid.addColumn(DataForm::getTeamNum).setHeader("Team Number").setFrozen(true).setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_avg_cones_scored).setHeader("Auto Avg Cones Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_avg_cubes_scored).setHeader("Auto Avg Cubes Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_peak_cubes_scored).setHeader("Auto Peak Number Cubes Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_peak_cones_scored).setHeader("Auto Peak Number Cones Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_peak_points).setHeader("Auto Peak Number Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_lowest_points).setHeader("Auto Low Number Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_percent_cubes_and_cones_accuracy).setHeader("Auto Accuracy (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_total_points).setHeader("Auto Total Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_avg_points).setHeader("Auto Avg Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_percent_docked).setHeader("Auto Docked (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_percent_engaged).setHeader("Auto Engaged (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_percent_mobility).setHeader("Auto Mobility (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_avg_docking_time).setHeader("Auto Avg Docking Time").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getAuton_avg_engaging_time).setHeader("Auto Avg Engaging Time").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_avg_cones_scored).setHeader("Teleop Avg Cones Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_avg_cubes_scored).setHeader("Teleop Avg Cubes Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_peak_cubes_scored).setHeader("Teleop Peak Cubes Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_peak_cones_scored).setHeader("Teleop Peak Cones Scored").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_peak_points).setHeader("Teleop Peak Number Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_lowest_points).setHeader("Teleop Low Number Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_percent_cubes_and_cones_accuracy).setHeader("Teleop Accuracy (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_avg_dropped_pieces).setHeader("Teleop Avg Dropped Pieces").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_total_points).setHeader("Teleop Total Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_avg_points).setHeader("Teleop Avg Points").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_avg_cycle_time).setHeader("Teleop Avg Cycle Time").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_intake_state).setHeader("Teleop Intake State").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getTeleop_total__times_traversed).setHeader("Teleop Total Times Traversed").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_percent_docked).setHeader("Endgame Percent Docked").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_percent_engaged).setHeader("Endgame Percent Engaged").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_percent_parked).setHeader("Endgame Percent Parked").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_times_robot_died).setHeader("Endgame Times Robot Died").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_percent_defended).setHeader("Endgame Defended (%)").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_avg_pushing_power).setHeader("Avg Pushing Power").setAutoWidth(true);
		dataGrid.addColumn(DataForm::getEndgame_avg_counter_denfense).setHeader("Avg Counter Defense").setAutoWidth(true);
		//dataGrid.addColumn(DataForm::getR1r2score).setHeader("R1 and R2 Score").setAutoWidth(true);
		dataGrid.setItems(dataList);
		add(dataGrid);

		Grid<QualScoutForm> qlGrid = new Grid<>(QualScoutForm.class, false);
		qlGrid.addColumn(QualScoutForm::getInitials).setHeader("Strategic Scouter's Initials").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getTeamNum).setHeader("Team Number").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getMatchNum).setHeader("Match Number").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getMatchLvl).setHeader("Match Level").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getEvent).setHeader("Event").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getRobot).setHeader("Robot").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getStartPosition).setHeader("Auto Starting Position").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getRobotPathing).setHeader("Auto Robot Pathing").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getPickUp).setHeader("Where robot picked up").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getPlacedOnGrid).setHeader("Where robot placed on grid").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getEndPosition).setHeader("Auto End Position").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_go_after_auton).setHeader("Where robot went directly after auton").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_defense_effect).setHeader("How did defense effect cycle time").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_effective_defense).setHeader("Did the robot play effective defense").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_over_charging_station).setHeader("Capable of traversing charge station").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_collect_pieces_substation).setHeader("Where can robot collect game pieces").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_enter_community).setHeader("Where robot enter community from").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_robot_balanced).setHeader("Robot well balanced").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getT_penalties_incurred).setHeader("Penalties incurred").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getDriverSkill).setHeader("Driver Skill").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getRobotSpeed).setHeader("Robot Speed").setAutoWidth(true);
		qlGrid.addColumn(QualScoutForm::getComments).setHeader("Comments").setAutoWidth(true);
		//qlGrid.addColumn(QualScoutForm::getR1r2score).setHeader("R1 and R2 Score").setAutoWidth(true);
		qlGrid.setItems(qlScoutList);
		add(qlGrid);

	}

}
