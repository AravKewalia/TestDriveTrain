package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotMap.DrivebaseConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap.DrivebaseConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.networktables.GenericEntry;


public class DriveSubsystem extends SubsystemBase{
    private final CANSparkMax m_frontLeftMotor;
    private final CANSparkMax m_frontRightMotor;
    private final CANSparkMax m_backLeftMotor;
    private final CANSparkMax m_backRightMotor;
    private final MotorController leftSideGroup;
    private final MotorController rightSideGroup;
    private final DifferentialDrive drive;
    // private Pose2d pose = new Pose2d(0, 0, new Rotation2d());

    //private final DifferentialDriveKinematics kinematics;

    public DifferentialDrive getDrive() {
        return drive;
    }


    public DriveSubsystem()
     {
        m_frontLeftMotor = new CANSparkMax(DrivebaseConstants.FRONT_LEFT_SPARK_ID, MotorType.kBrushed);
        m_frontRightMotor = new CANSparkMax(DrivebaseConstants.FRONT_RIGHT_SPARK_ID, MotorType.kBrushed);
        m_backLeftMotor = new CANSparkMax(DrivebaseConstants.BACK_LEFT_SPARK_ID, MotorType.kBrushed);
        m_backRightMotor = new CANSparkMax(DrivebaseConstants.BACK_RIGHT_SPARK_ID, MotorType.kBrushed);
      
        leftSideGroup = new MotorControllerGroup(m_frontLeftMotor, m_backLeftMotor);
        rightSideGroup = new MotorControllerGroup(m_frontRightMotor, m_backRightMotor); 
        
        /* 
        
        kinematics = new DifferentialDriveKinematics(
				new Translation2d(-0.49276 / 2.0, -0.23 / 2.0),
				new Translation2d(-0.49276 / 2.0, 0.23 / 2.0),
				new Translation2d(0.49276 / 2.0, -0.23 / 2.0),
				new Translation2d(0.49276 / 2.0, 0.23 / 2.0));
        
        */
        // leftSideGroup.setInverted(DrivebaseConstants.LEFT_SPARK_INVERTED);
        // rightSideGroup.setInverted(DrivebaseConstants.RIGHT_SPARK_INVERTED);

        m_frontLeftMotor.setIdleMode(DrivebaseConstants.BRAKE);
        m_frontRightMotor.setIdleMode(DrivebaseConstants.BRAKE);
        m_backLeftMotor.setIdleMode(DrivebaseConstants.BRAKE);
        m_backRightMotor.setIdleMode(DrivebaseConstants.BRAKE);

        m_frontLeftMotor.setInverted(true);
        m_frontRightMotor.setInverted(true);
        m_backLeftMotor.setInverted(true);
        m_backRightMotor.setInverted(true);

//         DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
//   m_gyro.getRotation2d(),
//   m_leftEncoder.getDistance(), m_rightEncoder.getDistance(),
//   new Pose2d(5.0, 13.5, new Rotation2d()));

         drive = new DifferentialDrive(leftSideGroup, rightSideGroup);
     }


    public MotorController getLeftSideGroup() {
        return leftSideGroup;
    }

    public MotorController getRightSideGroup() {
        return rightSideGroup;
    }

//     public RelativeEncoder getLeftEncoder() {
//         return leftEncoder;
//     }

//     public RelativeEncoder getRightEncoder() {
//         return rightEncoder;
//     }

//     public RelativeEncoder getleftEncoder()
//     {
//         return leftEncoder;
//     }
//     public RelativeEncoder getrightEncoder()
//     {
//         return rightEncoder;
//     }

//     public double getLeftEncoderPosition() {
//         return Math.abs(leftEncoder.getPosition());
//     }

//     public double getRightEncoderPosition() {
//         return Math.abs(rightEncoder.getPosition());
//     }
// //ninjsgo
//     public double getAverageEncoders()
//     {
//         return Math.abs((Math.abs(getLeftEncoderPosition()) + Math.abs(getRightEncoderPosition())) / 2);
//     }

//     public void resetEncoders()
//     {
//         leftEncoder.setPosition(0.0D);
//         rightEncoder.setPosition(0.0D);

//     }

//     public void resetEncoders(double value)
//     {
//         leftEncoder.setPosition(value);
//         rightEncoder.setPosition(value);
//     }


    public void arcadeDrive(double arcadeDriveSpeed, double arcadeDriveRotations)
    {
        getDrive().arcadeDrive(arcadeDriveSpeed, arcadeDriveRotations);
    }


    public void tankDrive(double leftSpeed, double rightSpeed) 
    {
        getDrive().tankDrive(leftSpeed, rightSpeed);
    }

    public void curvatureDrive(double xSpeed, double zRotations, boolean allowTurnInPlace)
    {
        getDrive().curvatureDrive(xSpeed, zRotations, allowTurnInPlace);
    }



    //public DifferentialDriveKinematics getKinematics() {
    //  return kinematics;
    //}

    @Override
    public void periodic()
    {
        // LEFT_ENCODER_ENTRY.setDouble(Math.abs(leftEncoder.getPosition()));
		// RIGHT_ENCODER_ENTRY.setDouble(Math.abs(rightEncoder.getPosition()));
		// ENCODER_DISTANCE_ENTRY.setDouble(getAverageEncoders());
		// NAVX_ANGLE_ENTRY.setDouble(Robot.navX.getAngle());
		// NAVX_RATE_ENTRY.setDouble(Robot.navX.getRate());
    }
}
