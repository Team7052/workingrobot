/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.time.Year;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

//

public class Robot extends TimedRobot {
	int motor = 0; //frontright 
	int kevingay = 1;//backright
	int calebcewl = 2;//backleft
	int kevingaymore = 3;
	Spark sparkmotor;
	Spark sparkkevingay;
	Spark sparkcalebcewl;
	Spark sparkkevingaymore;
	Joystick joystick;
	SpeedControllerGroup left;
	SpeedControllerGroup right;

	Spark armMotor;
	
	double currentSpeedLeft = 0;
	double currentSpeedRight = 0;
	
	
	
	
	@Override
	public void robotInit() {
		
		
		
		
		sparkmotor = new Spark(motor);
		sparkkevingay = new Spark(kevingay);
		sparkcalebcewl = new Spark(calebcewl);
		sparkkevingaymore = new Spark(kevingaymore);

		//sparkmotor.setInverted(true);
		//sparkcalebcewl.setInverted(true);

		right = new SpeedControllerGroup(sparkkevingay, sparkmotor);
		left = new SpeedControllerGroup(sparkcalebcewl,sparkkevingaymore);		

		right.setInverted(true);
		
		joystick = new Joystick(0);

		currentSpeedLeft = 0;
		currentSpeedRight = 0; 
	}	
	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {

	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	//huge double dong!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		double x = -joystick.getRawAxis(0);
		double y = joystick.getRawAxis(1);
		double v = joystick.getRawAxis(3); //right
		double w = -joystick.getRawAxis(2); //left

		//double leftSpeed = Math.atan(y) * 1.27;
		//double rightSpeed = Math.atan(y) * 1.27;
		double leftSpeed = 0;
		double rightSpeed = 0;
		// if v = 1, and w = 1
		leftSpeed = v+w;
		rightSpeed = v+w;
	

		if (x > 0.1) {
			rightSpeed = (rightSpeed*(1+x));
		}
		else if (x < -0.1) {
			leftSpeed = (leftSpeed *(1-x));
		}

		if(0.3> Math.abs(v+w)){
			//System.out.println("y is between -0.3 and 0.3");
			if(x<-0.3){
				//System.out.println("turn left");
				rightSpeed = 0.5*x;
				leftSpeed = 0.5*-x;
			}
			else if(x>0.3){
				//System.out.println("turn right");
				leftSpeed = 0.5*-x;
				rightSpeed = 0.5*x;
			}
		}
		double finalLeftSpeed = bufferSpeedLeft(this.currentSpeedLeft, leftSpeed);
		double finalRightSpeed = bufferSpeedLeft(this.currentSpeedRight, rightSpeed);
		left.set(finalLeftSpeed);
		right.set(finalRightSpeed);
		this.currentSpeedLeft = finalLeftSpeed;
		this.currentSpeedRight = finalRightSpeed;
	
	

	}	
	

	@Override
	public void testPeriodic() {
	}





	public double bufferSpeedLeft(double currentSpeedLeft, double desiredSpeedLeft){
		double speedIncrement=desiredSpeedLeft;
		double threshold = 0.08;
		if(Math.abs(desiredSpeedLeft-currentSpeedLeft)>threshold){
			if(desiredSpeedLeft>currentSpeedLeft){
				speedIncrement=currentSpeedLeft+threshold;
			}
			if(desiredSpeedLeft<currentSpeedLeft){
				speedIncrement=currentSpeedLeft-threshold;
			}
		}
		if(Math.abs(desiredSpeedLeft-currentSpeedLeft)<0.1){
			speedIncrement=desiredSpeedLeft;
		}

		return speedIncrement;
		
	}

	public double bufferSpeedRight(double currentSpeedRight, double desiredSpeedRight){
		double speedIncrement=desiredSpeedRight;
		if(Math.abs(desiredSpeedRight-currentSpeedRight)>0.1){
			if(desiredSpeedRight>currentSpeedRight){
				speedIncrement=currentSpeedRight+0.1;
			}
			if(desiredSpeedRight<currentSpeedRight){
				speedIncrement=currentSpeedRight-0.1;
			}
		}
		if(Math.abs(desiredSpeedRight-currentSpeedRight)<0.1){
			speedIncrement=desiredSpeedRight;
		}

		return speedIncrement;
	}
}