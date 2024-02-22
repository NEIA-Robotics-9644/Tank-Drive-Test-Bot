package frc.robot.subsystems;

public class DriveWheelsIOSim implements DriveWheelsIO {
    
    // Uses simulated Spark Maxs to get approcimate values for the drive train
    

    
    public DriveWheelsIOSim() {
        
    }
    
    @Override
    public void drive(double leftSpeedMps, double rightSpeedMps) {
        // Simulate the drive train
        System.out.println("Left Speed (m/s): " + leftSpeedMps);
        System.out.println("Right Speed (m/s): " + rightSpeedMps);
    }

}
    

