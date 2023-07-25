package com.scoutingapp2637.views.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="/pitdatalookup", layout = MainLayout.class) 
@PageTitle("ScoutingApp | Phantom Catz")
public class PitDataLookup extends VerticalLayout {

	static final long serialVersionUID = 1L;

	public PitDataLookup() {
		add(
				buildForm()

		);
	}
	
	private Component buildForm() {
		setSpacing(false);
		H1 pitLookup = new H1("Pit Data Lookup");
		TextField teamNumberEnter = new TextField("Team Number");
		teamNumberEnter.setHelperText("Enter a team to lookup data. ");
		teamNumberEnter.setMaxLength(4);
		Button teamNumberEnterButton = new Button("Submit");
		add(pitLookup);
		HorizontalLayout formLayout = new HorizontalLayout(teamNumberEnter, teamNumberEnterButton);
		Div wrapperLayout = new Div(formLayout);
		formLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
		wrapperLayout.setWidth("100%");
		teamNumberEnterButton.addClickListener(clickEvent -> {
			try {
				printPitData(teamNumberEnter.getValue());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return wrapperLayout;

	}

	private void printPitData(String teamNum) throws SQLException, ClassNotFoundException {
		removeAll();
		add(
				buildForm()

		);
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);
        // Use the connection to perform database operations here
        // Create a statement object
        
        ArrayList<PitForm> pitList = new ArrayList<PitForm>();
        Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM PitData WHERE teamNum = " + teamNum;
        ResultSet item = stmt.executeQuery(sql);
        
		while (item.next()) {
			PitForm pitData = new PitForm();
			pitData.setInitials(item.getString("initials"));
			pitData.setMatchNum(item.getInt("matchNum"));
			pitData.setTeamNum(item.getString("teamNum"));
			pitData.setDriveTrain(item.getString("driveTrain"));
			pitData.setNumWheels(item.getInt("numWheels"));
			pitData.setWheelType(item.getString("wheelType"));
			pitData.setDriveTrainMotorType(item.getString("driveTrainMotorType"));
			pitData.setNumMotors(item.getInt("numMotors"));
			pitData.setDriveTrainFunctional(item.getString("driveTrainFunctional"));
			pitData.setPossessionMechanism(item.getString("possessionMechanism"));
			pitData.setPossessionMechanismFunctional(item.getString("possessionMechanismFunctional"));
			pitData.setScoringMechanism(item.getString("scoringMechanism"));
			pitData.setScoringMechanismFunctional(item.getString("scoringMechanismFunctional"));
			pitData.setPreferredIntake(item.getString("preferredIntake"));
			pitData.setPreferredNodeLevel(item.getString("preferredNodeLevel"));
			pitData.setConeAnyOrientation(item.getString("coneAnyOrientation"));
			pitData.setHasAimingSystem(item.getString("hasAimingSystem"));
			pitData.setAimingSystemFunctional(item.getString("aimingSystemFunctional"));
			pitData.setHasBalancing(item.getString("hasBalancing"));
			pitData.setBalancingTime(item.getInt("balancingTime"));
			pitData.setNodeLevelsCapable(item.getString("nodeLevelsCapable"));
			pitData.setCapableGamePiece(item.getString("capableGamePiece"));
			pitData.setPreferredGrid(item.getString("preferredGrid"));
			pitData.setNumPiecesScoredAuton(item.getString("numPiecesScoredAuton"));
			pitData.setAutonPath(item.getString("autonPath"));
			pitData.setNumBatteries(item.getString("numBatteries"));
			pitData.setNumChargers(item.getString("numChargers"));
			pitData.setPitOrg(item.getString("pitOrg"));
			pitData.setTeamSafe(item.getString("teamSafe"));
			pitData.setTeamWork(item.getString("teamWork"));
			pitData.setGp(item.getString("gp"));
			pitData.setCycleTime(item.getString("cycleTime"));
			pitData.setComments(item.getString("comments"));
			pitData.setImages(item.getString("images"));
			pitData.setPreferredGamePiece(item.getString("preferredGamePiece"));
			pitList.add(pitData);
		}

		Grid<PitForm> tSumdataGrid = new Grid<>(PitForm.class, false);
		tSumdataGrid.addColumn(PitForm::getInitials).setHeader("Initials").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getTeamNum).setHeader("Team Number").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getMatchNum).setHeader("Match Number").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getDriveTrain).setHeader("Drivetrain Type").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNumWheels).setHeader("Number of Wheels").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getWheelType).setHeader("Wheel Type").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getDriveTrainMotorType).setHeader("Drivetrain Motor Type").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNumMotors).setHeader("Number of motors per drive gearbox").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getDriveTrainFunctional).setHeader("Drivetrain Functional").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPossessionMechanism).setHeader("Possession Mechanism").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPossessionMechanismFunctional).setHeader("Possession Mechanism Functional").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getScoringMechanism).setHeader("Scoring Mechanism").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getScoringMechanismFunctional).setHeader("Scoring Mechanism Functional").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getConeAnyOrientation).setHeader("Intake cone any orientation").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNeedsAlignment).setHeader("Needs alignment").setFrozen(true).setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getHasAimingSystem).setHeader("Has aiming system").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getAimingSystemFunctional).setHeader("Aiming system functional").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getHasBalancing).setHeader("Has balancing system").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getBalancingTime).setHeader("Balancing time").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNodeLevelsCapable).setHeader("Node levels capable").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPreferredNodeLevel).setHeader("Preferred node level").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getCapableGamePiece).setHeader("Game pieces capable").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPreferredGamePiece).setHeader("Preferred game piece").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPreferredIntake).setHeader("Preferred way to intake").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPreferredGrid).setHeader("Preferred grid").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNumPiecesScoredAuton).setHeader("Number of pieces scored in autonomous").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getAutonPath).setHeader("Autonomous Path").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNumBatteries).setHeader("Number of batteries").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getNumChargers).setHeader("Number of chargers").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getPitOrg).setHeader("Pit Organization [1-3]").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getTeamSafe).setHeader("Team Safety [1-3]").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getTeamWork).setHeader("Team Workmanship [1-3]").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getGp).setHeader("Gracious Professionalism [1-3]").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getComments).setHeader("Comments").setAutoWidth(true);
		tSumdataGrid.addColumn(PitForm::getImages).setHeader("Pit images").setAutoWidth(true);
		tSumdataGrid.setItems(pitList);
		add(tSumdataGrid);
	}
}
