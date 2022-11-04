package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor {
    ColorSensorV3 colorSensor;
    ColorMatch colorMatch;
    
    public ColorSensor(Port port) {
        colorSensor = new ColorSensorV3(port);
        colorMatch = new ColorMatch();
        colorMatch.addColorMatch(Color.kBlue);
        colorMatch.addColorMatch(Color.kRed);
    }
    
    public boolean isBall(){
        if(colorSensor.getProximity() >= 2000) {
            return true;
        }
        else {
            return false;
        }
    }

    public String senseColor() {
        if(colorMatch.matchClosestColor(colorSensor.getColor()).color == Color.kBlue && colorMatch.matchClosestColor(colorSensor.getColor()).confidence >= 0.8) {
            return "blue";
        }
        else if(colorMatch.matchClosestColor(colorSensor.getColor()).color == Color.kRed && colorMatch.matchClosestColor(colorSensor.getColor()).confidence >= 0.8) {
            return "red";
        }
        else {
            return null;
        }
    }
}
