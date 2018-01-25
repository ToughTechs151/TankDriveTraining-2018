package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickThrottleCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class TankDriveThrottleSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private SpeedController l1 = null;
	private SpeedController l2 = null;
	private SpeedController r1 = null;
	private SpeedController r2 = null;
	
	private SpeedControllerGroup left = null;
	private SpeedControllerGroup right = null;
	
	private DifferentialDrive drive = null;
	
	
	public TankDriveThrottleSubsystem() {
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
    	setDefaultCommand(new DriveWithJoystickThrottleCommand());
    }
    
    public void drive(OI oi) {
		double throttle = deadzone(oi, 1);
		double turn = deadzone(oi, 4);
		
		if(throttle != 0)
			turn = turn * Math.abs(throttle);
		
		double initLeft = throttle - turn;
		double initRight = throttle + turn;
		
		double left = initLeft + skim(initRight);
		double right = initRight + skim(initLeft);
		
		drive.tankDrive(left, right);;
	}
    
    private double deadzone(OI oi, int axis) {
		double rawAxis = oi.getJoystick().getRawAxis(axis);
		if(rawAxis > 0.03 || rawAxis < -0.03) {
			return rawAxis;
		} else {
			return 0;
		}
	}
    
    private double skim(double speed) {
		//Maximum PWM range is -1<=x<=1, so make up for that
		if(speed > 1.0) {
			return -(speed - 1.0);
		} else if(speed < -1.0) {
			return -(speed + 1.0);
		}
		return 0;
	}
}

