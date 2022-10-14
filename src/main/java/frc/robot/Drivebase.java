package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.Solenoid;

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

    MotorControllerGroup m_left = new MotorControllerGroup(leftBackCAN, leftFrontCAN);
    MotorControllerGroup m_right = new MotorControllerGroup(rightBackCAN, rightFrontCAN);
 
    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public void drive(double xSpeed, double zRotation, boolean allowTurnInPlace) {
        m_drive.curvatureDrive​(xSpeed, zRotation, allowTurnInPlace);
    }

}