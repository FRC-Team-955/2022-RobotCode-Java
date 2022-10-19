package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Drivebase {
    CANSparkMax leftFrontCAN = new CANSparkMax(1, MotorType.kBrushless);
    CANSparkMax leftBackCAN = new CANSparkMax(2, MotorType.kBrushless);
    CANSparkMax rightFrontCAN = new CANSparkMax(3, MotorType.kBrushless);
    CANSparkMax rightBackCAN = new CANSparkMax(4,MotorType.kBrushless);

    private SparkMaxPIDController leftFrontCANPID = leftBackCAN.getPIDController();
    private SparkMaxPIDController leftBackCANPID = leftBackCAN.getPIDController();
    private SparkMaxPIDController rightFrontCANPID = rightFrontCAN.getPIDController();
    private SparkMaxPIDController rightbackCANPID = rightBackCAN.getPIDController();

    private double kP = 0.4;
    private double kI = 0.003;
    private double kD = 0.002;

    MotorControllerGroup m_left = new MotorControllerGroup(leftBackCAN, leftFrontCAN);
    MotorControllerGroup m_right = new MotorControllerGroup(rightBackCAN, rightFrontCAN);
 
    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    NetworkTable table = NetworkTableInstance.getDefault().getTable("driveLimelight");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tx = table.getEntry("tx");


    Limelight limelight = new Limelight();

    double min_command = 0.05;
    double targetOffsetAngle_Verticle = ty.getDouble(0.0);

    public void drive(double xSpeed, double zRotation) {
        if (xSpeed >= 0.05 || xSpeed <= -0.05) {
            m_drive.curvatureDrive(xSpeed, zRotation, true);
        } else {
            m_drive.curvatureDrive(xSpeed, zRotation, false);
        }
    }

    public void limelightGroundTurn(double xSpeed) {
        m_drive.arcadeDrive(xSpeed, limelight.driveLimelightPID());;
    }
    
}