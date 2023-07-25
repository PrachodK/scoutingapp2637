package com.scoutingapp2637.views.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.grid.dnd.GridDropLocation;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="/teamsummary", layout = MainLayout.class) 
@PageTitle("ScoutingApp | Phantom Catz")

public class TeamSummaryView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private TsumForm draggedItem;

	public TeamSummaryView() throws ClassNotFoundException, SQLException {

		add(new H1("Team Summary")); 
		addToGrid();

	}

	private void addToGrid() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);

        // Use the connection to perform database operations here
        // Create a statement object
        
        ArrayList<TsumForm> scoutList = new ArrayList<TsumForm>();
        Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM TeamSummary";
        ResultSet item = stmt.executeQuery(sql);
        
		while (item.next()) {
			TsumForm scoutData = new TsumForm();
			scoutData.setTeamNum(item.getString("team_number"));
			scoutData.setAuton_avg_cones_scored(item.getDouble("auton_avg_cones_scored"));
			scoutData.setAuton_avg_cubes_scored(item.getDouble("auton_avg_cubes_scored"));
			scoutData.setAuton_peak_cones_scored(item.getInt("auton_peak_cones_scored"));
			scoutData.setAuton_peak_cubes_scored(item.getInt("auton_peak_cubes_scored"));
			scoutData.setAuton_percent_cubes_and_cones_accuracy(item.getDouble("auton_percent_cubes_and_cones_accuracy"));
			scoutData.setAuton_total_points(item.getInt("auton_total_points"));
			scoutData.setAuton_avg_points(item.getDouble("auton_avg_points"));
			scoutData.setAuton_percent_docked(item.getDouble("auton_percent_docked"));
			scoutData.setAuton_percent_engaged(item.getDouble("auton_percent_engaged"));
			scoutData.setAuton_percent_mobility(item.getDouble("auton_percent_mobility"));
			scoutData.setAuton_avg_docking_time(item.getDouble("auton_avg_docking_time"));
			scoutData.setAuton_avg_engaging_time(item.getDouble("auton_avg_engaging_time"));
			scoutData.setTeleop_avg_cones_scored(item.getDouble("teleop_avg_cones_scored"));
			scoutData.setTeleop_avg_cubes_scored(item.getDouble("teleop_avg_cubes_scored"));
			scoutData.setTeleop_peak_cubes_scored(item.getInt("teleop_peak_cubes_scored"));
			scoutData.setTeleop_peak_cones_scored(item.getInt("teleop_peak_cones_scored"));
			scoutData.setTeleop_percent_cubes_and_cones_accuracy(item.getDouble("teleop_percent_cubes_and_cones_accuracy"));
			scoutData.setTeleop_avg_dropped_pieces(item.getDouble("teleop_avg_dropped_pieces"));
			scoutData.setTeleop_total_points(item.getInt("teleop_total_points"));
			scoutData.setTeleop_avg_points(item.getDouble("teleop_avg_points"));			
			scoutData.setTeleop_avg_cycle_time(item.getDouble("teleop_avg_cycle_time"));
			scoutData.setTeleop_intake_state(item.getString("teleop_intake_state"));
			scoutData.setTeleop_total__times_traversed(item.getInt("teleop_total__times_traversed"));
			scoutData.setEndgame_percent_docked(item.getDouble("endgame_percent_docked"));
			scoutData.setEndgame_percent_engaged(item.getDouble("endgame_percent_engaged"));
			scoutData.setEndgame_percent_parked(item.getDouble("endgame_percent_parked"));
			scoutData.setEndgame_times_robot_died(item.getInt("endgame_times_robot_died"));
			scoutData.setEndgame_percent_defended(item.getDouble("endgame_percent_defended"));
			scoutData.setEndgame_avg_pushing_power(item.getDouble("endgame_avg_pushing_power"));
			scoutData.setEndgame_avg_counter_denfense(item.getDouble("endgame_avg_counter_denfense"));
			scoutData.setAuton_peak_points(item.getInt("auton_peak_points"));
			scoutData.setAuton_lowest_points(item.getInt("auton_lowest_points"));
			scoutData.setTeleop_peak_points(item.getInt("teleop_peak_points"));
			scoutData.setTeleop_lowest_points(item.getInt("teleop_lowest_points"));
			//scoutData.setR1r2score(item.getDouble("r1r2_score"));
			scoutList.add(scoutData);
		}

		Grid<TsumForm> tSumdataGrid = new Grid<>(TsumForm.class, false);
		tSumdataGrid.addColumn(TsumForm::getTeamNum).setHeader("Team Number").setFrozen(true).setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_avg_cones_scored).setHeader("Auto Avg Cones Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_avg_cubes_scored).setHeader("Auto Avg Cubes Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_peak_cubes_scored).setHeader("Auto Peak Number Cubes Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_peak_cones_scored).setHeader("Auto Peak Number Cones Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_peak_points).setHeader("Auto Peak Number Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_lowest_points).setHeader("Auto Low Number Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_percent_cubes_and_cones_accuracy).setHeader("Auto Accuracy (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_total_points).setHeader("Auto Total Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_avg_points).setHeader("Auto Avg Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_percent_docked).setHeader("Auto Docked (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_percent_engaged).setHeader("Auto Engaged (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_percent_mobility).setHeader("Auto Mobility (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_avg_docking_time).setHeader("Auto Avg Docking Time").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getAuton_avg_engaging_time).setHeader("Auto Avg Engaging Time").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_avg_cones_scored).setHeader("Teleop Avg Cones Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_avg_cubes_scored).setHeader("Teleop Avg Cubes Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_peak_cubes_scored).setHeader("Teleop Peak Cubes Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_peak_cones_scored).setHeader("Teleop Peak Cones Scored").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_peak_points).setHeader("Teleop Peak Number Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_lowest_points).setHeader("Teleop Low Number Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_percent_cubes_and_cones_accuracy).setHeader("Teleop Accuracy (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_avg_dropped_pieces).setHeader("Teleop Avg Dropped Pieces").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_total_points).setHeader("Teleop Total Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_avg_points).setHeader("Teleop Avg Points").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_avg_cycle_time).setHeader("Teleop Avg Cycle Time").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_intake_state).setHeader("Teleop Intake State").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getTeleop_total__times_traversed).setHeader("Teleop Total Times Traversed").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_percent_docked).setHeader("Endgame Percent Docked").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_percent_engaged).setHeader("Endgame Percent Engaged").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_percent_parked).setHeader("Endgame Percent Parked").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_times_robot_died).setHeader("Endgame Times Robot Died").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_percent_defended).setHeader("Endgame Defended (%)").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_avg_pushing_power).setHeader("Avg Pushing Power").setAutoWidth(true);
		tSumdataGrid.addColumn(TsumForm::getEndgame_avg_counter_denfense).setHeader("Avg Counter Defense").setAutoWidth(true);
		//tSumdataGrid.addColumn(TsumForm::getR1r2score).setHeader("R1 and R2 Score").setAutoWidth(true).setSortable(true);
		tSumdataGrid.setItems(scoutList);
		tSumdataGrid.setDropMode(GridDropMode.BETWEEN);
		tSumdataGrid.setRowsDraggable(true);
		tSumdataGrid.setColumnReorderingAllowed(true);
		GridListDataView<TsumForm> dataView = tSumdataGrid.setItems(scoutList);
		
		tSumdataGrid.addDragStartListener(
		        e -> draggedItem = e.getDraggedItems().get(0));

		tSumdataGrid.addDropListener(e -> {
		    TsumForm targetPerson = e.getDropTargetItem().orElse(null);
		    GridDropLocation dropLocation = e.getDropLocation();

		    boolean personWasDroppedOntoItself = draggedItem.equals(targetPerson);

		    if (targetPerson == null || personWasDroppedOntoItself)
		        return;

		    dataView.removeItem(draggedItem);

		    if (dropLocation == GridDropLocation.BELOW) {
		        dataView.addItemAfter(draggedItem, targetPerson);
		    } else {
		        dataView.addItemBefore(draggedItem, targetPerson);
		    }
		});
 
		tSumdataGrid.addDragEndListener(e -> draggedItem = null);

		add(tSumdataGrid);
	}

}
