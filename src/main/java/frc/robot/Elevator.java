package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class Elevator {

    private DigitalInput bottomLimitSensor = new DigitalInput(0);
    private DigitalInput topLimitSensor = new DigitalInput(1);
    private TalonFX motor = new TalonFX(0);

    public Elevator() {}
//use talonfx to reset the elevator, then zero the position
    public boolean resetElevator() {
        if (bottomLimitSensor.get()) {
            motor.set(TalonFXControlMode.PercentOutput, 0.5);
            return false;
        } else {
            return true;
        }
    }

    public void moveElevator(double percentOutput) {
        if (!topLimitSensor.get() && percentOutput < 0 || !bottomLimitSensor.get() && percentOutput > 0) {
           motor.set(TalonFXControlMode.PercentOutput, percentOutput);
        }
    }
}
