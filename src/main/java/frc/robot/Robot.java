// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.ControllerConstants;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.SpinjitzuMaster;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotMap.DrivebaseConstants;
import frc.robot.Subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.SpinjitzuMaster;
import java.lang.ModuleLayer.Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import frc.robot.Commands.DefaultDrive;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Commands.SpinjitzuMaster;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.kauailabs.navx.frc.AHRS;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

	/* RoboRio Sensors */
	public static final AHRS NAVX = new AHRS();

	public static AHRS getNavx() {
		return NAVX;
	}

	public static final DriveSubsystem tank = new DriveSubsystem();
	public static DriveSubsystem getDrivebase() {
			return tank;
		}


	public static final XboxController XBOX_CONTROLLER = new XboxController(ControllerConstants.CONTROLLER_ID);
	public static Trigger aButton = new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kA.value);
	public static Trigger bButton = new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kB.value);

  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    tank.getLeftSideGroup().setInverted(true);

	//Robot.XBOX_CONTROLLER.get

	Robot.getDrivebase().setDefaultCommand(new DefaultDrive());
	Robot.bButton.onTrue(new SequentialCommandGroup(new SpinjitzuMaster(0.9, 0.05, 0)));
	SmartDashboard.putBoolean("garmadon", Robot.XBOX_CONTROLLER.getLeftBumperPressed());
	//Robot.XBOX_CONTROLLER.a(new EventLoop()).onTrue(new InstantCommand(new SpinjitzuMaster(0.9, .05, 0)));

    //m_myRobot = new DifferentialDrive(tank.getLeftSideGroup(), tank.getRightSideGroup());
    // XBOX_CONTROLLER.leftTrigger().onTrue(new InstantCommand());
		// XBOX_CONTROLLER.rightBumper().onTrue(new InstantCommand());
  }
  @Override
	public void robotPeriodic() {

		/*
		 * Runs the Scheduler. This is responsible for polling buttons, adding
		 * newly-scheduled
		 * commands, running already-scheduled commands, removing finished or
		 * interrupted commands,
		 * and running subsystem periodic() methods. This must be called from the
		 * robot's periodic
		 * block in order for anything in the Command-based framework to work.
		 */
		CommandScheduler.getInstance().run();
		//System.out.print("Left Joystick: "); System.out.println(-XBOX_CONTROLLER.getLeftY());
		//System.out.print("Right Joystick: "); System.out.println(-XBOX_CONTROLLER.getRightY());
		
	}
}
