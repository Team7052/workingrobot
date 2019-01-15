/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
		double y = -joystick.getRawAxis(0);
		double x = joystick.getRawAxis(1);
		double v = joystick.getRawButton(7) ? 0.6 : 0;
		double w = joystick.getRawButton(6) ? 0.6 : 0;

		//double leftSpeed = Math.atan(y) * 1.27;
		//double rightSpeed = Math.atan(y) * 1.27;
		double leftSpeed = y * y * y;
		double rightSpeed = y * y * y;
		// if v = 1, and w = 1
		if (v > 0.1) {
			leftSpeed = v;
		} 	rightSpeed = v;

		 if (w > 0.1) {
			leftSpeed = -w;
			rightSpeed = -w;
		}


		if (x > 0.5) {
			rightSpeed = -x*0.9;
		}
		else if (x < -0.5) {
			leftSpeed = x*0.9;
		}

		if(-0.3 <y && 0.3>y){
			//System.out.println("y is between -0.3 and 0.3");
			if(x<-0.5){
				//System.out.println("turn left");
				rightSpeed = 0.5;
				leftSpeed = -0.5;
			}
			else if(x>0.5){
				//System.out.println("turn right");
				leftSpeed = 0.5;
				rightSpeed = -0.5;
			}
		}

		System.out.println(leftSpeed + " " + rightSpeed);

		left.set(leftSpeed);
		right.set(rightSpeed);
	
	

	}	
	

	@Override
	public void testPeriodic() {
	}
}