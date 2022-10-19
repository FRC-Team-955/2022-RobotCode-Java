package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Shooter {
    private CANSparkMax topMotor;
    private CANSparkMax bottomMotor;
    private SparkMaxPIDController topMotorPID = topMotor.getPIDController();
    private SparkMaxPIDController bottomMotorPID = bottomMotor.getPIDController();
    private double kP = 0.4;
    private double kI = 0.003;
    private double kD = 0.002;

    private Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);

    public Shooter(int topMotorPort, int bottomMotorPort) {
        topMotor = new CANSparkMax(topMotorPort, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(bottomMotorPort, MotorType.kBrushless);
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

    public boolean getShooterSolenoid() {
        return shooterSolenoid.get();
    }

    //maybe add a setPID function
}
