package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Shooter {
    private CANSparkMax topMotor;
    private CANSparkMax bottomMotor;
    private SparkMaxPIDController topMotorPID;
    private SparkMaxPIDController bottomMotorPID;
    private double kP = 0.0002;
    private double kI = 0.00002;
    private double kD = 0;
    private double kFF = 0.002;
    Limelight limelight = new Limelight();



    private DoubleSolenoid shooterSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 4);

    public Shooter(int topMotorPort, int bottomMotorPort) {
        topMotor = new CANSparkMax(topMotorPort, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(bottomMotorPort, MotorType.kBrushless);
        topMotorPID = topMotor.getPIDController();
        bottomMotorPID = bottomMotor.getPIDController();
        topMotor.restoreFactoryDefaults();
        bottomMotor.restoreFactoryDefaults();
        
        topMotorPID.setP(kP);
        bottomMotorPID.setP(kP);
        topMotorPID.setI(kI);
        bottomMotorPID.setI(kI);
        topMotorPID.setD(kD);
        topMotorPID.setFF(kFF);
        bottomMotorPID.setFF(kFF);
    }

    public void setShooterRPMs(double topShooterVelocity, double bottomShooterVelocity) {
        topMotorPID.setReference(topShooterVelocity, CANSparkMax.ControlType.kVelocity);
        bottomMotorPID.setReference(bottomShooterVelocity, CANSparkMax.ControlType.kVelocity);
    }

    public void switchShooterSolenoid() {
        shooterSolenoid.toggle();
    }
    
   
   public double setSpeedByRange(double range) {
       if (range < 20) {
           if (shooterSolenoid.get() == Value.kReverse) {
            return limelight.speedDown();
           } else {
               shooterSolenoid.toggle();
               return limelight.speedDown();
           }
       }

       if (range > 20) {
        if (shooterSolenoid.get() == Value.kForward) {
            return limelight.speedUp();
        } else {
            shooterSolenoid.toggle();
            return limelight.speedUp();
        }
       } else {
           return -1;
       }
    

    }
}
