package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class TankDriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private SpeedController l1 = null;
	private SpeedController l2 = null;
	private SpeedController r1 = null;
	private SpeedController r2 = null;
	
	private SpeedControllerGroup left = null;
	private SpeedControllerGroup right = null;
	
	private DifferentialDrive drive = null;
	
	public TankDriveSubsystem() {
		l1 = new Talon(0);
		l2 = new Talon(1);
		r1 = new Talon(2);
		r2 = new Talon(3);
		
		left = new SpeedControllerGroup(l1, l2);
		right = new SpeedControllerGroup(r1, r2);
		
		drive = new DifferentialDrive(left, right);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(OI oi) {
    	double left = oi.getJoystick().getRawAxis(1);
    	double right = oi.getJoystick().getRawAxis(2); //check values
    	
    	drive.tankDrive(left, right);
    }
}

