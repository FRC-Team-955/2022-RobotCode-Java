package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Hopper {
    TalonSRX bottomMotor;
    TalonSRX topMotor;

    public Hopper() {
        bottomMotor = new TalonSRX(0);
        topMotor = new TalonSRX(1);
    }

    public void hopperSpin(double bottomMotorSpeed, double topMotorSpeed) {
        bottomMotor.set(ControlMode.PercentOutput, bottomMotorSpeed);
        topMotor.set(ControlMode.PercentOutput, topMotorSpeed);
    }
}
