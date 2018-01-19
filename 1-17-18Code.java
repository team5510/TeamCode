/*Added 3 new cases for a total of 6 autonomous options 
(leftStartAlpha, 
midStartAlpha, 
rightStartAlpha, 
leftStartBeta, 
midStartBeta, 
rightStartBeta)*/

package org.usfirst.frc.team5510.robot;
 
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Timer.StaticInterface;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
//
// IF YOU WANT TO DEPLOY CODE TO ROBORIO, GO TO RUN AT THE TOP AND PRESS RUN AS WPILIB JAVA
//
 
 
public class Robot extends IterativeRobot implements PIDOutput{
    final String defaultAuto = "Default";
    final String leftStartAlpha = "Left Start Alpha";
    final String midStartAlpha = "Mid Start Alpha";
    final String rightStartAlpha = "Right Start Alpha";
    final String leftStartBeta = "Left Start Beta";
    final String midStartBeta = "Mid Start Beta";
    final String rightStartBeta = "Right Start Beta";
    String autoSelected;
    SendableChooser <String> chooser = new SendableChooser<>();
   
    RobotDrive eggroll;
    RobotDrive tomato;
    //spm  eed controllers, find the ports on the roboRIO
    Victor rightFront;
    Victor rightBack;
    Victor leftFront;
    Victor leftBack;
    Victor tomatoClimb;
   
    Talon gearMotor;
    Spark iceClimb;
   
    boolean reverseDrive;
    boolean forwardDrive;
   
    Joystick xboxController;
   
    PIDController turnController;
    double rotateToAngleRate;
    static double kP = 0.001; //increase until oscillations and then half
    static double kI = 0.00; //increase until offset is corrected in time
    static double kD = 0.00; //increase until quick enough
    static final double kF = 0.00;
    static final double kToleranceDegrees = 2.0f;
    Timer autoTimerTing;
    //private boolean haha;
   
    double autoTimerStart;
 
   
    @Override
    public void robotInit() {
        //chooser.addDefault("Default Auto", defaultAuto);
        //chooser.addObject("My Auto", customAuto);
        chooser.addObject("Left Start Alpha", leftStartAlpha);
        chooser.addObject("Mid Start Alpha", midStartAlpha);
        chooser.addObject("Right Start Alpha" , rightStartAlpha);
        chooser.addObject("Left Start Beta", leftStartBeta);
        chooser.addObject("Mid Start Beta", midStartBeta);
        chooser.addObject("Right Start Beta", rightStartBeta);
       
        SmartDashboard.putData("Auto modes", chooser);
       
        xboxController = new Joystick(0);
       
        reverseDrive=false;
        forwardDrive=true;
       
        rightFront = new Victor (1); //port 1
        rightBack = new Victor (2); //port 2
        leftFront = new Victor (3); //port 3
        leftBack = new Victor (4); //port 4
        //tomatoClimb = new Victor (5); //port 5
        gearMotor = new Talon (0);  
       
        iceClimb = new Spark (5);
        eggroll = new RobotDrive(rightFront, rightBack, leftFront, leftBack);
        //tomato = new RobotDrive(tomatoClimb);
    }
 
    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
   
     */
 
    @Override
    public void autonomousInit() {
        eggroll.setSafetyEnabled(false);
        autoSelected = chooser.getSelected();
        //autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
        chooser.addObject("Left Start Alpha", leftStartAlpha);
        chooser.addObject("Mid Start Alpha", midStartAlpha);
        chooser.addObject("Right Start Alpha" , rightStartAlpha);
        chooser.addObject("Left Start Beta", leftStartBeta);
        chooser.addObject("Mid Start Beta", midStartBeta);
        chooser.addObject("Right Start Alpha", rightStartBeta);
        System.out.println("Auto selected: " + autoSelected);
        //System.out.println("Auto Timer: " + autoTimer.get());
        autoTimerStart = Timer.getFPGATimestamp();
       
        //
    }
   
    /*public class DriveStraightForXAtY extends Command {
        protected double power;
        protected double time;
        protected long endTime;
    }
   
    public DriveStraightForDistance(double (1.0, 0.0), double 15){
        this.power = 1;
        this.time = 1;
        requires();
    }
    */
   
    @Override
    public void autonomousPeriodic() {
        double currTimer = Timer.getFPGATimestamp();
        double timeElapsed = currTimer-autoTimerStart;
        /*Timer.getMatchTime();
            StaticInterface ti;
            while(true){
        Timer.getFPGATimeStamp(15);
                Timer.SetImplementation(ti);   
            */
   
        switch (autoSelected) {    
       
        case leftStartAlpha:
            // Put Mid Start code here
            if (timeElapsed <2){
                eggroll.drive(-0.6, 0.0);
            }
           
            else if (timeElapsed <5){
                eggroll.drive(0.6, 0.0);
            }
           
            else if (timeElapsed < 6) {
                eggroll.drive(0.2, 0.0);
            }
            else if (timeElapsed==15){
                eggroll.drive(0.0, 0.0);          
                eggroll.setSafetyEnabled(true);
            }
            break;
           
        case midStartAlpha:
            //Put Left Start Code here
           
            if (timeElapsed <2) {
                eggroll.drive(-0.6, 0.5);
            }
           
            else if (timeElapsed <5){
                eggroll.drive(0.0, 0.0);
                eggroll.setSafetyEnabled(true);
            }
            break;
           
        case rightStartAlpha:
            //Put Right Start Code here
            if (timeElapsed<2){
                eggroll.drive(-0.5, 0.5);
            }
            else if (timeElapsed<5){
                eggroll.drive(0.5, 0.5);
            }
            else if (timeElapsed<6){
                eggroll.drive(0.0, 0.0);
                eggroll.setSafetyEnabled(true);
            }
            break;
       
        case leftStartBeta:
            if (timeElapsed<2){
                eggroll.drive(-0.5, 0.5);
            }
            else if (timeElapsed<5){
                eggroll.drive(0.5, 0.5);
            }
            else if (timeElapsed<6){
                eggroll.drive(0.0, 0.0);
                eggroll.setSafetyEnabled(true);
            }
            break;
           
        case midStartBeta:
            if (timeElapsed<2){
                eggroll.drive(-0.5, 0.5);
            }
            else if (timeElapsed<5){
                eggroll.drive(0.5, 0.5);
            }
            else if (timeElapsed<6){
                eggroll.drive(0.0, 0.0);
                eggroll.setSafetyEnabled(true);
            }
            break;
           
        case rightStartBeta:
            if (timeElapsed<2){
                eggroll.drive(-0.5, 0.5);
            }
            else if (timeElapsed<5){
                eggroll.drive(0.5, 0.5);
            }
            else if (timeElapsed<6){
                eggroll.drive(0.0, 0.0);
                eggroll.setSafetyEnabled(true);
            }
            break;
           
       
        default:
           
           
           
           
           
           
           
           
            /*int a = 0;
            for(a = 0; a < 2; a++){
                eggroll.drive(-0.2, 0.0);
            }
            eggroll.drive(-0.1, 0.2);
            int b = 0;
            for(b = 0; b < 2; b++){
                eggroll.drive(0.5,  0.0);
            }
            // Put default auto code here
            */
       
       
       
            /*while(!haha)
               
               
            for (int x = 1; x < 3; x= x +1){
                    eggroll.drive(-0.1, 0.0);  
               
            if (x == 3){
            autoTimer.stop();
            haha = true;
            eggroll.drive(0.0,0.0);
            }
            */
                   
                /* autoTimer.stop();
                 erroll.drive(-0.1, 0.6); Turn*/
 
           
            break;
               
       
        }
    }
 
 
@Override
    public void teleopPeriodic() {
        eggroll.setSafetyEnabled(true);
        smartBoardData();
        switchDrive();
        xboxDrive();
        highGoalSucks();
        iceClimbers();
    }
 
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
 
    }
   
    public void pidWrite(double output){
       
    }  
   
    //double throttle = (-xboxController.getThrottle() + 1.0d) / 2.0d;
   
   
    private void xboxDrive(){           //system for determining which speed desired. Joystick.getRawAxis(Axis Number)*Speed[Range:0-1.0]
        if (forwardDrive){
            if ((xboxController.getRawButton(5)) ) {
                eggroll.tankDrive(-xboxController.getRawAxis(5) * -0.5, -xboxController.getRawAxis(1) * -0.5, true);
               
                //eggroll.tankDrive(xboxController.getRawAxis(5) * ((-xboxController.getThrottle()+1/2)), xboxController.getRawAxis(1) * ((xboxController.getThrottle()+1/2)), true;
            }
            else if (xboxController.getRawButton(6)){       //HIT THE NOS
                eggroll.tankDrive(-xboxController.getRawAxis(5) * -0.5, -xboxController.getRawAxis(1) * -0.5, true);
            }
            else {
                eggroll.tankDrive(xboxController.getRawAxis(5) * 0.7, xboxController.getRawAxis(1) * 0.7, true);
                //eggroll.tankDrive(xboxController.getRawAxis(5) * 0.7, xboxController.getRawAxis(1) * 0.7, true);
            }
        }
        else{
            if ((xboxController.getRawButton(5)) ) {
                eggroll.tankDrive(-xboxController.getRawAxis(1) * 0.5, -xboxController.getRawAxis(5) * 0.5, true);
                }
            else if (xboxController.getRawButton(6)){       //HIT THE NOS
                eggroll.tankDrive(-xboxController.getRawAxis(1) * 0.85, -xboxController.getRawAxis(5) * 0.85, true);
                }
            else {
                eggroll.tankDrive(-xboxController.getRawAxis(1) * 0.7, -xboxController.getRawAxis(5) * 0.7, true);
                }
            }
    }
   
    private void switchDrive(){
        if (xboxController.getRawButton(4)){
            if (!reverseDrive){
            forwardDrive=!forwardDrive;
            reverseDrive=true;
            }
        }
            else{
                reverseDrive=false;
            }
    }
   
 
   
   
    private void highGoalSucks(){   // method for dumping balls
       
    }
   
    private void smartBoardData(){
        SmartDashboard.putBoolean("Forward Mode", forwardDrive);
       
    }
   
    private void iceClimbers(){ //method for climbing onto the ship
        if (xboxController.getRawButton(3)){
            iceClimb.set(-1);
           
        }
       
        if (xboxController.getRawButton(1)) {
            iceClimb.set(0);
        }
       
        if (xboxController.getRawButton(2)) {
            iceClimb.set(1);
        }
       
       
        //if ((xboxController.getRawButton(2)) ) {
            //eggroll.tankDrive(-xboxController.getRawAxis(1) * 0.5, -xboxController.getRawAxis(5) * 0.5, true);
            //}
    }
 
}
