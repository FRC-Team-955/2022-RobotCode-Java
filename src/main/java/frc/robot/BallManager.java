package frc.robot;

import frc.robot.Hopper;
import frc.robot.ColorSensor;

import edu.wpi.first.wpilibj.I2C.Port;

public class BallManager {
    ColorSensor bottomColorSensor = new ColorSensor(port);
    ColorSensor topColorSensor = new ColorSensor(port);
    Hopper hopper = new Hopper();
    String bottomColor;
    String topColor;

    public void ballManager() {
        if(topColorSensor.isBall() == true && bottomColorSensor.isBall() == true) {
            hopper.hopperSpin(0, 0);
            bottomColor = bottomColorSensor.senseColor();
            topColor = topColorSensor.senseColor();
        }
        else if(topColorSensor.isBall() == true) {
            hopper.hopperSpin(1, 0);
            topColor = topColorSensor.senseColor();
        }
        else {
            hopper.hopperSpin(1, 1);
        }
    }

    public void manualBallManager(double joystickOutput) {
        hopper.hopperSpin(joystickOutput, joystickOutput);
    }

    public void reject() {
        if(topColor == "red") {
            
        }
        if(bottomColor == "red") {

        }
    }
}
