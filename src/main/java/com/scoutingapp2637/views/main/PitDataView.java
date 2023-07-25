package com.scoutingapp2637.views.main;

import com.vaadin.flow.component.upload.Upload;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import elemental.json.Json;
import net.coobird.thumbnailator.Thumbnails;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Route(value="/pitdata", layout = MainLayout.class) 
@PageTitle("ScoutingApp | Phantom Catz")
public class PitDataView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String filesUploaded = "";
	static int imageCount = 0;
	
	AmazonS3 s3client = AmazonS3ClientBuilder
			  .standard()
			  .withRegion(Regions.US_WEST_2)
			  .build();
	
	public PitDataView() {

		add(new H1("Pit Data Form"));

		TextField initialField = new TextField("Pit Scouter's Initials");
		initialField.setClearButtonVisible(true);

		TextField matchNumField = new TextField("Match Number");
		matchNumField.setClearButtonVisible(true);
		TextField teamNumField = new TextField("Team Number");
		teamNumField.setClearButtonVisible(true);
		ComboBox<String> driveTrainField = new ComboBox<>("Drivetrain Type");
		driveTrainField.setAllowCustomValue(true);
		driveTrainField.setItems("Swerve", "Tank", "H-Drive", "Other");
		IntegerField numWheelsField = new IntegerField("# of wheels on chassis");
		numWheelsField.setStepButtonsVisible(true);;
		numWheelsField.setMin(0);
		numWheelsField.setValue(0);
		numWheelsField.setWidth("250px");
		ComboBox<String> wheelTypeField = new ComboBox<>("Wheel Type");
		wheelTypeField.setClearButtonVisible(true);
		wheelTypeField.setItems("Nitrile / Neoprene / Plaction", "HiGrip", "Colson", "Stealth / Smooth grip", "Pneumatasic", "Omni", "Mechanum", "Versa", "Other");
		wheelTypeField.setAllowCustomValue(true);
		ComboBox<String> driveTrainMotorType = new ComboBox<>("Motor Type");
		driveTrainMotorType.setClearButtonVisible(true);
		driveTrainMotorType.setItems("Falcon 500", "NEO", "CIM", "Other");
		driveTrainMotorType.setAllowCustomValue(true);
		IntegerField numMotorsField = new IntegerField("# motors per drive gearbox");
		numMotorsField.setStepButtonsVisible(true);;
		numMotorsField.setMin(0);
		numMotorsField.setValue(0);
		numMotorsField.setWidth("250px");
		RadioButtonGroup<String> driveTrainFunctionalField = new RadioButtonGroup<>();
		driveTrainFunctionalField.setLabel("Drivetrain functional");
		driveTrainFunctionalField.setItems("Yes", "No");
		RadioButtonGroup<String> coneAnyOrientation = new RadioButtonGroup<>();
		coneAnyOrientation.setLabel("Intake cone any orientation?");
		coneAnyOrientation.setItems("Yes", "No");
		ComboBox<String> possessionMechanismType = new ComboBox<>("Possession Mechanism Type");
		possessionMechanismType.setClearButtonVisible(true);
		possessionMechanismType.setItems("Over the bumper", "Through the bumper", "Claw", "Roller", "None", "Other");
		possessionMechanismType.setAllowCustomValue(true);
		RadioButtonGroup<String> possessionMechanismFunctional = new RadioButtonGroup<>();
		possessionMechanismFunctional.setLabel("Possession Mechanism functional");		
		possessionMechanismFunctional.setItems("Yes", "No");
		ComboBox<String> scoringMechanismType = new ComboBox<>("Scoring Mechanism Type");
		scoringMechanismType.setClearButtonVisible(true);
		scoringMechanismType.setItems("Same as Possession Mechanism", "Claw", "Roller", "Shooter", "None", "Other");
		scoringMechanismType.setAllowCustomValue(true);
		RadioButtonGroup<String> scoringMechanismFunctional = new RadioButtonGroup<>();
		scoringMechanismFunctional.setLabel("Scoring Mechanism functional");	
		scoringMechanismFunctional.setItems("Yes", "No");
		RadioButtonGroup<String> needsPhysicalAlignment = new RadioButtonGroup<>();
		needsPhysicalAlignment.setLabel("Needs physical alignment");	
		needsPhysicalAlignment.setItems("Yes", "No");
		RadioButtonGroup<String> hasAimingSystem = new RadioButtonGroup<>();
		hasAimingSystem.setLabel("Has aiming system");	
		hasAimingSystem.setItems("Yes", "No");
		RadioButtonGroup<String> aimingSystemFunctional = new RadioButtonGroup<>();
		aimingSystemFunctional.setLabel("Aiming system functional");
		aimingSystemFunctional.setItems("Yes", "No");
		RadioButtonGroup<String> hasBalanceSystem = new RadioButtonGroup<>();
		hasBalanceSystem.setLabel("Has balance system");	
		hasBalanceSystem.setItems("Yes", "No");
		IntegerField balanceTime = new IntegerField("Balance time (sec)");
		balanceTime.setStepButtonsVisible(true);;
		balanceTime.setMin(0);
		balanceTime.setValue(0);
		balanceTime.setWidth("150px");
		
		CheckboxGroup<String> nodeLevelsCapable = new CheckboxGroup<>();
		nodeLevelsCapable.setLabel("Node levels capable");
		nodeLevelsCapable.setItems("Top", "Middle", "Bottom");
		CheckboxGroup<String> preferredNodeLevel = new CheckboxGroup<>();
		preferredNodeLevel.setLabel("Preferred node level");
		preferredNodeLevel.setItems("Top", "Middle", "Bottom",  "No Preference");
		CheckboxGroup<String> preferredIntake = new CheckboxGroup<>();
		preferredIntake.setLabel("Preferred way to intake game pieces");
		preferredIntake.setItems("Ground intake", "Single substation", "Double substation", "No Preference");
		CheckboxGroup<String> capableGamePiece = new CheckboxGroup<>();
		capableGamePiece.setLabel("Capable Game Pieces");
		capableGamePiece.setItems("Cone", "Cube");
		RadioButtonGroup<String> preferredGamePiece = new RadioButtonGroup<>();
		preferredGamePiece.setLabel("Preferred Game Piece");
		preferredGamePiece.setItems("Cone", "Cube", "No Preference");
		CheckboxGroup<String> preferredGrid = new CheckboxGroup<>();
		preferredGrid.setLabel("Preferred Grid");
		preferredGrid.setItems("Substation", "Community", "Scoring Table", "No Preference");
		IntegerField cycleTime = new IntegerField("Cycle time (sec)");
		cycleTime.setMin(0);
		cycleTime.setValue(0);
		IntegerField numPiecesAuton = new IntegerField("# of pieces scored in autonomous");
		numPiecesAuton.setStepButtonsVisible(true);;
		numPiecesAuton.setMin(0);
		numPiecesAuton.setValue(0);
		numPiecesAuton.setWidth("250px");
		TextArea autonPathField = new TextArea("Auton Path");
		autonPathField.setClearButtonVisible(true);
		autonPathField.setWidthFull();
		IntegerField numBatteriesField = new IntegerField("Number of batteries");
		numBatteriesField.setStepButtonsVisible(true);;
		numBatteriesField.setMin(0);
		numBatteriesField.setValue(0);
		numBatteriesField.setWidth("250px");
		IntegerField numChargersField = new IntegerField("Number of chargers");
		numChargersField.setStepButtonsVisible(true);;
		numChargersField.setMin(0);
		numChargersField.setValue(0);
		numChargersField.setWidth("250px");
		ComboBox<String> pitOrgField = new ComboBox<>("Pit Organization [1-3]");
		pitOrgField.setAllowCustomValue(false);
		pitOrgField.setItems("1", "2", "3");
		ComboBox<String> teamSafeField = new ComboBox<>("Team Safety [1-3]");
		teamSafeField.setAllowCustomValue(false);
		teamSafeField.setItems("1", "2", "3");
		ComboBox<String> teamWorkField = new ComboBox<>("Team Workmanship [1-3]");
		teamWorkField.setAllowCustomValue(false);
		teamWorkField.setItems("1", "2", "3");
		ComboBox<String> gpField = new ComboBox<>("Gracious Professionalism [1-3]");
		gpField.setAllowCustomValue(false);
		gpField.setItems("1", "2", "3");
		TextArea commentsField = new TextArea("Comments");
		commentsField.setSizeFull();
		commentsField.setClearButtonVisible(true);

		MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
		Upload upload = new Upload(buffer);

		Span dropLabel = createDropLabel();
		Icon dropIcon = VaadinIcon.FILE_PICTURE.create();

		upload.setDropLabel(dropLabel);
		upload.setDropLabelIcon(dropIcon);

		Div errorsLayout = new Div();
		VerticalLayout formLayout = new VerticalLayout(initialField, matchNumField, teamNumField, driveTrainField, numWheelsField, 
				wheelTypeField, driveTrainMotorType, numMotorsField, driveTrainFunctionalField, possessionMechanismType, possessionMechanismFunctional, 
				scoringMechanismType, scoringMechanismFunctional, coneAnyOrientation, needsPhysicalAlignment, 
				hasAimingSystem, aimingSystemFunctional, hasBalanceSystem, balanceTime, nodeLevelsCapable, preferredNodeLevel, 
				capableGamePiece, preferredGamePiece, preferredIntake, preferredGrid, cycleTime, numPiecesAuton, autonPathField, numBatteriesField, 
				numChargersField, pitOrgField, teamSafeField, teamWorkField, gpField, commentsField, upload);

		Button button = new Button("Submit");
		Div wrapperLayout = new Div(formLayout, errorsLayout, button);
		formLayout.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
		wrapperLayout.setWidth("100%");

		upload.addSucceededListener(event -> {
			String bucketName = "pitimages";
			String localFileName = event.getFileName();
			imageCount++;

			String s3FileName = teamNumField.getValue()
					+ "_" + matchNumField.getValue()
					+ "_" + imageCount
					+ ".jpg";
					// + "." + FilenameUtils.getExtension(localFileName);

			System.out.println(localFileName);
			InputStream inputStream = buffer.getInputStream(localFileName);
			ObjectMetadata metadata = new ObjectMetadata();
			try {
				byte[] contentBytes = IOUtils.toByteArray(inputStream);
				Long contentLength = Long.valueOf(contentBytes.length);
				System.out.println("Orginal Size: " + contentLength);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				inputStream = buffer.getInputStream(localFileName);
			    Thumbnails.of(inputStream)
			        .scale(0.3)
			        .outputFormat("JPEG")
			        .toFile(new File(s3FileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				inputStream = new FileInputStream(s3FileName);
				byte[] contentBytes = IOUtils.toByteArray(inputStream);
				Long contentLength = Long.valueOf(contentBytes.length);
				System.out.println("Reduced Size: " + contentLength);
				metadata.setContentLength(contentLength);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				inputStream = new FileInputStream(s3FileName);
				s3client.putObject(new PutObjectRequest(bucketName, s3FileName, inputStream, metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (filesUploaded.isEmpty())
				filesUploaded = "https://pitimages.s3.us-west-2.amazonaws.com/"+s3FileName;
			else
				filesUploaded = filesUploaded + " | " + "https://pitimages.s3.us-west-2.amazonaws.com/"+s3FileName;

		}); 

		button.addClickListener((clickEvent -> {
			if (initialField.isEmpty() || matchNumField.isEmpty() || teamNumField.isEmpty() || driveTrainField.isEmpty() ||
					numWheelsField.isEmpty() || wheelTypeField.isEmpty() || driveTrainMotorType.isEmpty() || numMotorsField.isEmpty() || driveTrainFunctionalField.isEmpty() || possessionMechanismType.isEmpty() ||
					possessionMechanismFunctional.isEmpty() || scoringMechanismType.isEmpty() || scoringMechanismFunctional.isEmpty() || coneAnyOrientation.isEmpty() || needsPhysicalAlignment.isEmpty() ||
					hasAimingSystem.isEmpty() || aimingSystemFunctional.isEmpty() || hasBalanceSystem.isEmpty() || balanceTime.isEmpty() ||
					nodeLevelsCapable.isEmpty() || capableGamePiece.isEmpty() || preferredGrid.isEmpty() || cycleTime.isEmpty() ||
					numPiecesAuton.isEmpty() || autonPathField.isEmpty()  || numBatteriesField.isEmpty() || numChargersField.isEmpty()  || pitOrgField.isEmpty()
					|| teamSafeField.isEmpty()  || teamWorkField.isEmpty() || gpField.isEmpty()  || commentsField.isEmpty() || preferredNodeLevel.isEmpty() || preferredIntake.isEmpty() || preferredGamePiece.isEmpty()) {
				
				Notification notification = Notification
						.show("Please fill all data fields!");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

			} else {
				try {
					saveForm((initialField.getValue()), matchNumField.getValue(), teamNumField.getValue(), driveTrainField.getValue(), numWheelsField.getValue().toString(), wheelTypeField.getValue().toString(), driveTrainMotorType.getValue(), numMotorsField.getValue().toString(),
							driveTrainFunctionalField.getValue(), possessionMechanismType.getValue(), possessionMechanismFunctional.getValue(), scoringMechanismType.getValue(), scoringMechanismFunctional.getValue(), coneAnyOrientation.getValue(),
							needsPhysicalAlignment.getValue(), hasAimingSystem.getValue(), aimingSystemFunctional.getValue(), hasBalanceSystem.getValue(), balanceTime.getValue().toString(), nodeLevelsCapable.getValue().toString(), preferredNodeLevel.getValue().toString(), capableGamePiece.getValue().toString(), preferredGamePiece.getValue().toString(), preferredIntake.getValue().toString(), preferredGrid.getValue().toString(), cycleTime.getValue().toString(), numPiecesAuton.getValue().toString(),
							autonPathField.getValue(), numBatteriesField.getValue().toString(), numChargersField.getValue().toString(), pitOrgField.getValue(), teamSafeField.getValue(), teamWorkField.getValue(), gpField.getValue(), commentsField.getValue(), filesUploaded);
					initialField.setValue("");
					matchNumField.setValue("");
					teamNumField.setValue("");
					driveTrainField.setValue("");
					teamNumField.setValue("");
					numWheelsField.setValue(0);
					wheelTypeField.setValue("");
					driveTrainMotorType.setValue("");
					numMotorsField.setValue(0);
					driveTrainFunctionalField.setValue("");
					possessionMechanismType.setValue("");
					possessionMechanismFunctional.setValue("");
					scoringMechanismType.setValue("");
					scoringMechanismFunctional.setValue("");
					needsPhysicalAlignment.setValue("");
					hasAimingSystem.setValue("");
					aimingSystemFunctional.setValue("");
					hasBalanceSystem.setValue("");
					balanceTime.setValue(0);
					nodeLevelsCapable.clear();
					capableGamePiece.clear();
					preferredGrid.clear();
					cycleTime.setValue(0);
					numPiecesAuton.setValue(0);
					autonPathField.setValue("");
					numBatteriesField.setValue(0);
					numChargersField.setValue(0);
					preferredGamePiece.setValue("");
					pitOrgField.setValue("");
					teamSafeField.setValue("");
					teamWorkField.setValue("");
					gpField.setValue("");
					commentsField.setValue("");
					coneAnyOrientation.setValue("");
					preferredNodeLevel.clear();
					preferredIntake.clear();
					filesUploaded = "";
					imageCount = 0;
					upload.getElement().setPropertyJson("files", Json.createArray());
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
		

		add(wrapperLayout);


	}

	private void saveForm(String initialField, String matchNumField, String teamNumField, String driveTrainField, String numWheelsField, 
			String wheelTypeField, String driveTrainMotorType, String numMotorsField, String driveTrainFunctionalField, String possessionMechanismType, String possessionMechanismFunctional, 
			String scoringMechanismType, String scoringMechanismFunctional, String coneAnyOrientation, String needsPhysicalAlignment, 
			String hasAimingSystem, String aimingSystemFunctional, String hasBalanceSystem, String balanceTime, String nodeLevelsCapable, String preferredNodeLevel,
			String capableGamePiece, String preferredGamePiece, String preferredIntake, String preferredGrid, String cycleTime, String numPiecesAuton, String autonPathField, String numBatteriesField, 
			String numChargersField, String pitOrgField, String teamSafeField, 
			String teamWorkField, String gpField, String commentsField, String images) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Set up the connection parameters
		String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9613072?useSSL=false&allowPublicKeyRetrieval=true";


		String username = "sql9613072";
		String password = "ZHkj5zjV8l";

		// Establish the connection
		Connection conn = DriverManager.getConnection(url, username, password);
		String query = " insert into PitData (initials, matchNum, teamNum, driveTrain, numWheels, wheelType, driveTrainMotorType, numMotors, driveTrainFunctional, possessionMechanism, possessionMechanismFunctional, scoringMechanism, scoringMechanismFunctional, preferredIntake, preferredNodeLevel, needsAlignment, hasAimingSystem, aimingSystemFunctional, hasBalancing, balancingTime, nodeLevelsCapable, capableGamePiece, preferredGrid, cycleTime, numPiecesScoredAuton, autonPath, numBatteries, numChargers, pitOrg, teamSafe, teamWork, gp, comments, images, coneAnyOrientation, preferredGamePiece)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString (1, initialField);
		preparedStmt.setInt (2, Integer.parseInt(matchNumField.trim()));
		preparedStmt.setInt   (3, Integer.parseInt(teamNumField.trim()));
		preparedStmt.setString (4, driveTrainField);
		preparedStmt.setString    (5, numWheelsField);
		preparedStmt.setString    (6, wheelTypeField);
		preparedStmt.setString    (7, driveTrainMotorType);
		preparedStmt.setString    (8, numMotorsField);
		preparedStmt.setString    (9, driveTrainFunctionalField);
		preparedStmt.setString    (10, possessionMechanismType);
		preparedStmt.setString    (11, possessionMechanismFunctional);
		preparedStmt.setString    (12, scoringMechanismType);
		preparedStmt.setString    (13, scoringMechanismFunctional);
		preparedStmt.setString    (14, preferredIntake);
		preparedStmt.setString    (15, preferredNodeLevel);
		preparedStmt.setString    (16, needsPhysicalAlignment);
		preparedStmt.setString    (17, hasAimingSystem);
		preparedStmt.setString    (18, aimingSystemFunctional);
		preparedStmt.setString    (19, hasBalanceSystem);
		preparedStmt.setString    (20, balanceTime);
		preparedStmt.setString    (21, nodeLevelsCapable);
		preparedStmt.setString    (22, capableGamePiece);
		preparedStmt.setString    (23, preferredGrid);
		preparedStmt.setString    (24, cycleTime);
		preparedStmt.setString    (25, numPiecesAuton);
		preparedStmt.setString    (26, autonPathField);
		preparedStmt.setString    (27, numBatteriesField);
		preparedStmt.setString    (28, numChargersField);
		preparedStmt.setString    (29, pitOrgField);
		preparedStmt.setString    (30, teamSafeField);
		preparedStmt.setString    (31, teamWorkField);
		preparedStmt.setString    (32, gpField);
		preparedStmt.setString    (33, commentsField);
		preparedStmt.setString    (34, images);
		preparedStmt.setString    (35, coneAnyOrientation);
		preparedStmt.setString    (36, preferredGamePiece);


		// execute the preparedstatement
		preparedStmt.execute();
		
		/* Create an Object of PutItemRequest 
		PutItemRequest request = new PutItemRequest();

        /* Setting Table Name 
        request.setTableName("pit");

        /* Create a Map of attributes 
        HashMap<String, AttributeValue> map = new HashMap<>();

        map.put("initials", new AttributeValue(initialField));
        map.put("match_number", new AttributeValue().withN(matchNumField));
        map.put("team_number", new AttributeValue().withN(teamNumField));   
        map.put("drivetrain_type", new AttributeValue(driveTrainField));
        map.put("number_wheels_chassis", new AttributeValue(numWheelsField));
        map.put("wheel_type", new AttributeValue(wheelTypeField));
        map.put("motor_type", new AttributeValue(driveTrainMotorTypeField));
        map.put("num_motors", new AttributeValue(numMotorsField));
        map.put("drivetrain_functional", new AttributeValue(driveTrainFunctionalField));
        map.put("intake_type", new AttributeValue(intakeTypeField));
        map.put("intake_full_speed_capable", new AttributeValue(intakeFullSpeedField));
        map.put("intake_functional", new AttributeValue(intakeFunctionalField));
        map.put("indexer_capacity", new AttributeValue(indexerCapacityField));
        map.put("indexer_jamming", new AttributeValue(indexerJammingField));
        map.put("indexer_functional", new AttributeValue(indexerFunctionalField));
        map.put("shooter_type", new AttributeValue(shooterTypeField));
        map.put("scoring_location", new AttributeValue(scoringLocationField));
        map.put("needs_alignment", new AttributeValue(alignmentField));
        map.put("shooter_functional", new AttributeValue(shooterFunctionalField));
        map.put("has_aiming_system", new AttributeValue(hasAimingSystemField));
        map.put("max_aim_rotation", new AttributeValue(maxAimRotationField));
        map.put("aim_system_functional", new AttributeValue(aimingSystemFunctionalField));
        map.put("has_vision", new AttributeValue(hasVisionField));
        map.put("vision_functional", new AttributeValue(visionFunctionalField));
        map.put("has_climb", new AttributeValue(hasClimbingField));
        map.put("rungs_traversed", new AttributeValue(rungsTraversedField));
        map.put("climb_time", new AttributeValue(climbTimeField));
        map.put("enter_location", new AttributeValue(enterLocationField));
        map.put("climb_functional", new AttributeValue(climbFunctionalField));
        map.put("auto_start_location", new AttributeValue(autoStartLocationField));
        map.put("cargo_scored", new AttributeValue(cargoScoredField.toString()));
        map.put("auton_path", new AttributeValue(autonPathField));
        map.put("num_batteries", new AttributeValue(numBatteriesField));
        map.put("num_chargers", new AttributeValue(numChargersField));
        map.put("is_wired", new AttributeValue(wiredField));
        map.put("robot_materials", new AttributeValue(robotMatsField));
        map.put("pit_organization", new AttributeValue(pitOrgField));
        map.put("team_safety", new AttributeValue(teamSafeField));
        map.put("team_workmanship", new AttributeValue(teamWorkField));
        map.put("gracious_profressionalism", new AttributeValue(gpField));
        map.put("comments", new AttributeValue(commentsField));

        request.setItem(map);
        
        /* Put into pit table 
        PutItemResult result = dynamoDB.putItem(request);
        System.out.println(result); */
	} 

	private static Span createDropLabel() {
		Span cloudHint = new Span("Drop the Pit Scouting image file(s) here.");
		return new Span(cloudHint);
	}
}
