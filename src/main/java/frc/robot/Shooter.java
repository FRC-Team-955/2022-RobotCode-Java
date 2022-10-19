package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Shooter {
    private CANSparkMax topMotor = new CANSparkMax(1, MotorType.kBrushless);
    private CANSparkMax bottomMotor = new CANSparkMax(2, MotorType.kBrushless);
    private SparkMaxPIDController topMotorPID = topMotor.getPIDController();
    private SparkMaxPIDController bottomMotorPID = bottomMotor.getPIDController();
    private double kP = 0.4;
    private double kI = 0.003;
    private double kD = 0.002;

    private Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);

    public Shooter() {
        topMotor.restoreFactoryDefaults();
        bottomMotor.restoreFactoryDefaults();
        
        topMotorPID.setP(kP);
        bottomMotorPID.setP(kP);
        topMotorPID.setI(kI);
        bottomMotorPID.setI(kI);
        topMotorPID.setD(kD);
        bottomMotorPID.setD(kD);
    }

    public void setShooterRPMs(double topShooterVelocity, double bottomShooterVelocity) {
        topMotorPID.setReference(topShooterVelocity, CANSparkMax.ControlType.kVelocity);
        bottomMotorPID.setReference(bottomShooterVelocity, CANSparkMax.ControlType.kVelocity);
    }

    public void setShooter(boolean isShooterUp) {
        shooterSolenoid.set(isShooterUp);
    }
}
