package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.MathUtil;

public class Limelight {
    private double angularP = 0.1;
    private double angularD = 0.001;
    private PIDController turnController = new PIDController(angularP, 0, angularD);
    private PIDController driveController = new PIDController(angularP, 0, angularD);
    private PhotonCamera camera = new PhotonCamera("photonvision");
    public Limelight() {}

    final double cameraHeightMeters = Units.inchesToMeters(24);
    final double targetHeightMeters = Units.feetToMeters(5);
    final double cameraPitchRadians= Units.degreesToRadians(0);
    final double goalRangeMeters = Units.feetToMeters(3);

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
    public double rangeOfTargets() {
        var result = camera.getLatestResult();
        boolean hasTargets = result.hasTargets();
        if (hasTargets == true) {
            double range = PhotonUtils.calculateDistanceToTargetMeters(cameraHeightMeters, targetHeightMeters, cameraPitchRadians, Units.degreesToRadians(result.getBestTarget().getPitch()));
            return range;
        } else {
            return -1;
        }
    }
    public double speedUp() {
        double forwardSpeed;
        forwardSpeed = Math.pow(rangeOfTargets(), 2);
        return forwardSpeed;
    }
    public double speedDown() {
        double forwardSpeed;
        forwardSpeed = Math.pow(rangeOfTargets(), 3);
        return forwardSpeed;
    }

}
