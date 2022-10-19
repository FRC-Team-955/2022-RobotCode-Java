package frc.robot;

import org.photonvision.PhotonCamera;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.MathUtil;

public class Limelight {
    private double angularP = 0.1;
    private double angularD = 0.001;
    private PIDController turnController = new PIDController(angularP, 0, angularD);
    private PIDController driveController = new PIDController(angularP, 0, angularD);
    private PhotonCamera camera = new PhotonCamera("photonvision");

    public Limelight() {}

    public double limelightPID() {
        var result = camera.getLatestResult();

        if (result.hasTargets()) {
            return MathUtil.clamp(turnController.calculate(result.getBestTarget().getYaw(), 0), -1, 1);
        } else {
            return 0;
        }
    }

    public double driveLimelightPID() {
        var result = camera.getLatestResult();

        if (result.hasTargets()) {
            return MathUtil.clamp(driveController.calculate(result.getBestTarget().getYaw(), 0), -1, 1); 
        } else {
            return 0;
        }
    }
}
