package com.scoutingapp2637.views.main;

public class TsumForm {
	private String teamNum = "";
	private double auton_avg_cones_scored = 0.0;
	private double auton_avg_cubes_scored = 0.0;
	private Integer auton_peak_cones_scored = 0;
	private Integer auton_peak_cubes_scored = 0;
	private double auton_percent_cubes_and_cones_accuracy = 0.0;
	private Integer auton_total_points = 0;
	private double auton_avg_points = 0.0;
	private double auton_percent_docked = 0.0;
	private double auton_percent_engaged = 0.0;
	private double auton_percent_mobility = 0.0;
	private double auton_avg_docking_time = 0.0;
	private double auton_avg_engaging_time = 0.0;
	private double teleop_avg_cones_scored = 0.0;
	private double teleop_avg_cubes_scored = 0.0;
	private Integer teleop_peak_cubes_scored = 0;
	private Integer teleop_peak_cones_scored = 0;
	private double teleop_percent_cubes_and_cones_accuracy = 0.0;
	private double teleop_avg_dropped_pieces = 0.0;
	private Integer teleop_total_points = 0;
	private double teleop_avg_points = 0.0;
	private double teleop_avg_cycle_time = 0.0;
	private String teleop_intake_state = "";
	private Integer teleop_total__times_traversed = 0;
	private double endgame_percent_docked = 0.0;
	private double endgame_percent_engaged = 0.0;
	private double endgame_percent_parked = 0.0;
	private Integer endgame_times_robot_died = 0;
	private double endgame_percent_defended = 0.0;
	private double endgame_avg_pushing_power = 0.0;
	private double endgame_avg_counter_denfense = 0.0;
	private Integer auton_peak_points = 0;
	private Integer teleop_peak_points = 0;
	private Integer auton_lowest_points = 0;
	private Integer teleop_lowest_points = 0;
	
	
	public Integer getAuton_peak_points() {
		return auton_peak_points;
	}
	public void setAuton_peak_points(Integer auton_peak_points) {
		this.auton_peak_points = auton_peak_points;
	}
	public Integer getTeleop_peak_points() {
		return teleop_peak_points;
	}
	public void setTeleop_peak_points(Integer teleop_peak_points) {
		this.teleop_peak_points = teleop_peak_points;
	}
	public Integer getAuton_lowest_points() {
		return auton_lowest_points;
	}
	public void setAuton_lowest_points(Integer auton_lowest_points) {
		this.auton_lowest_points = auton_lowest_points;
	}
	public Integer getTeleop_lowest_points() {
		return teleop_lowest_points;
	}
	public void setTeleop_lowest_points(Integer teleop_lowest_points) {
		this.teleop_lowest_points = teleop_lowest_points;
	}
	public String getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(String teamNum) {
		this.teamNum = teamNum;
	}
	public double getAuton_avg_cones_scored() {
		return auton_avg_cones_scored;
	}
	public void setAuton_avg_cones_scored(double auton_avg_cones_scored) {
		this.auton_avg_cones_scored = auton_avg_cones_scored;
	}
	public double getAuton_avg_cubes_scored() {
		return auton_avg_cubes_scored;
	}
	public void setAuton_avg_cubes_scored(double auton_avg_cubes_scored) {
		this.auton_avg_cubes_scored = auton_avg_cubes_scored;
	}
	public Integer getAuton_peak_cones_scored() {
		return auton_peak_cones_scored;
	}
	public void setAuton_peak_cones_scored(Integer auton_peak_cones_scored) {
		this.auton_peak_cones_scored = auton_peak_cones_scored;
	}
	public Integer getAuton_peak_cubes_scored() {
		return auton_peak_cubes_scored;
	}
	public void setAuton_peak_cubes_scored(Integer auton_peak_cubes_scored) {
		this.auton_peak_cubes_scored = auton_peak_cubes_scored;
	}
	public double getAuton_percent_cubes_and_cones_accuracy() {
		return auton_percent_cubes_and_cones_accuracy;
	}
	public void setAuton_percent_cubes_and_cones_accuracy(double auton_percent_cubes_and_cones_accuracy) {
		this.auton_percent_cubes_and_cones_accuracy = auton_percent_cubes_and_cones_accuracy;
	}
	public Integer getAuton_total_points() {
		return auton_total_points;
	}
	public void setAuton_total_points(Integer auton_total_points) {
		this.auton_total_points = auton_total_points;
	}
	public double getAuton_avg_points() {
		return auton_avg_points;
	}
	public void setAuton_avg_points(double auton_avg_points) {
		this.auton_avg_points = auton_avg_points;
	}
	public double getAuton_percent_docked() {
		return auton_percent_docked;
	}
	public void setAuton_percent_docked(double auton_percent_docked) {
		this.auton_percent_docked = auton_percent_docked;
	}
	public double getAuton_percent_engaged() {
		return auton_percent_engaged;
	}
	public void setAuton_percent_engaged(double auton_percent_engaged) {
		this.auton_percent_engaged = auton_percent_engaged;
	}
	public double getAuton_percent_mobility() {
		return auton_percent_mobility;
	}
	public void setAuton_percent_mobility(double auton_percent_mobility) {
		this.auton_percent_mobility = auton_percent_mobility;
	}
	public double getAuton_avg_docking_time() {
		return auton_avg_docking_time;
	}
	public void setAuton_avg_docking_time(double auton_avg_docking_time) {
		this.auton_avg_docking_time = auton_avg_docking_time;
	}
	public double getAuton_avg_engaging_time() {
		return auton_avg_engaging_time;
	}
	public void setAuton_avg_engaging_time(double auton_avg_engaging_time) {
		this.auton_avg_engaging_time = auton_avg_engaging_time;
	}
	public double getTeleop_avg_cones_scored() {
		return teleop_avg_cones_scored;
	}
	public void setTeleop_avg_cones_scored(double teleop_avg_cones_scored) {
		this.teleop_avg_cones_scored = teleop_avg_cones_scored;
	}
	public double getTeleop_avg_cubes_scored() {
		return teleop_avg_cubes_scored;
	}
	public void setTeleop_avg_cubes_scored(double teleop_avg_cubes_scored) {
		this.teleop_avg_cubes_scored = teleop_avg_cubes_scored;
	}
	public Integer getTeleop_peak_cubes_scored() {
		return teleop_peak_cubes_scored;
	}
	public void setTeleop_peak_cubes_scored(Integer teleop_peak_cubes_scored) {
		this.teleop_peak_cubes_scored = teleop_peak_cubes_scored;
	}
	public Integer getTeleop_peak_cones_scored() {
		return teleop_peak_cones_scored;
	}
	public void setTeleop_peak_cones_scored(Integer teleop_peak_cones_scored) {
		this.teleop_peak_cones_scored = teleop_peak_cones_scored;
	}
	public double getTeleop_percent_cubes_and_cones_accuracy() {
		return teleop_percent_cubes_and_cones_accuracy;
	}
	public void setTeleop_percent_cubes_and_cones_accuracy(double teleop_percent_cubes_and_cones_accuracy) {
		this.teleop_percent_cubes_and_cones_accuracy = teleop_percent_cubes_and_cones_accuracy;
	}
	public double getTeleop_avg_dropped_pieces() {
		return teleop_avg_dropped_pieces;
	}
	public void setTeleop_avg_dropped_pieces(double teleop_avg_dropped_pieces) {
		this.teleop_avg_dropped_pieces = teleop_avg_dropped_pieces;
	}
	public Integer getTeleop_total_points() {
		return teleop_total_points;
	}
	public void setTeleop_total_points(Integer teleop_total_points) {
		this.teleop_total_points = teleop_total_points;
	}
	public double getTeleop_avg_points() {
		return teleop_avg_points;
	}
	public void setTeleop_avg_points(double teleop_avg_points) {
		this.teleop_avg_points = teleop_avg_points;
	}
	public double getTeleop_avg_cycle_time() {
		return teleop_avg_cycle_time;
	}
	public void setTeleop_avg_cycle_time(double teleop_avg_cycle_time) {
		this.teleop_avg_cycle_time = teleop_avg_cycle_time;
	}
	public String getTeleop_intake_state() {
		return teleop_intake_state;
	}
	public void setTeleop_intake_state(String teleop_intake_state) {
		this.teleop_intake_state = teleop_intake_state;
	}
	public Integer getTeleop_total__times_traversed() {
		return teleop_total__times_traversed;
	}
	public void setTeleop_total__times_traversed(Integer teleop_total__times_traversed) {
		this.teleop_total__times_traversed = teleop_total__times_traversed;
	}
	public double getEndgame_percent_docked() {
		return endgame_percent_docked;
	}
	public void setEndgame_percent_docked(double endgame_percent_docked) {
		this.endgame_percent_docked = endgame_percent_docked;
	}
	public double getEndgame_percent_engaged() {
		return endgame_percent_engaged;
	}
	public void setEndgame_percent_engaged(double endgame_percent_engaged) {
		this.endgame_percent_engaged = endgame_percent_engaged;
	}
	public double getEndgame_percent_parked() {
		return endgame_percent_parked;
	}
	public void setEndgame_percent_parked(double endgame_percent_parked) {
		this.endgame_percent_parked = endgame_percent_parked;
	}
	public Integer getEndgame_times_robot_died() {
		return endgame_times_robot_died;
	}
	public void setEndgame_times_robot_died(Integer endgame_times_robot_died) {
		this.endgame_times_robot_died = endgame_times_robot_died;
	}
	public double getEndgame_percent_defended() {
		return endgame_percent_defended;
	}
	public void setEndgame_percent_defended(double endgame_percent_defended) {
		this.endgame_percent_defended = endgame_percent_defended;
	}
	public double getEndgame_avg_pushing_power() {
		return endgame_avg_pushing_power;
	}
	public void setEndgame_avg_pushing_power(double endgame_avg_pushing_power) {
		this.endgame_avg_pushing_power = endgame_avg_pushing_power;
	}
	public double getEndgame_avg_counter_denfense() {
		return endgame_avg_counter_denfense;
	}
	public void setEndgame_avg_counter_denfense(double endgame_avg_counter_denfense) {
		this.endgame_avg_counter_denfense = endgame_avg_counter_denfense;
	}
	
	
}
