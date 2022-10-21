package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Intake {
    DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 1);
    TalonSRX sucker = new TalonSRX(11);

    Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
    public void suck(double amount){
        sucker.set(TalonSRXControlMode.PercentOutput, amount);
    }

    public void spit(double amount) {
        sucker.set(TalonSRXControlMode.PercentOutput, amount);
    }

    public void open() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
