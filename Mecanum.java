package org.firstinspires.ftc.teamcode.decode;


import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;




//importação de classes a serem utilizadas

@TeleOp(name="Mecanum", group="Decode")

public class Mecanum extends LinearOpMode {

    //declaração de atuadores e variáveis
    private Follower follower;



    private DcMotor frentedireita;
    private DcMotor frenteesquerda;
    private DcMotor trasdireita;
    private DcMotor trasesquerda;

    double xlinha;
    double ylinha;
    double lx;
    double ly;




    //declaração de atuadores (para a driver)
    @Override
    public void runOpMode() {

        frentedireita = hardwareMap.get(DcMotor.class, "c2");
        frenteesquerda = hardwareMap.get(DcMotor.class, "c1");
        trasdireita = hardwareMap.get(DcMotor.class, "c0");
        trasesquerda = hardwareMap.get(DcMotor.class, "c3");

        //declaração do modo de operação dos motores


        frentedireita.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        trasdireita.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frenteesquerda.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        trasesquerda.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //atualização da telemetria
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //início do programa atuador
        waitForStart();


        while (opModeIsActive()) {



                ly = gamepad1.right_stick_y == 0 ? 0 :
                        Math.pow(gamepad1.right_stick_y, 2) * Math.abs(gamepad1.right_stick_y) /
                                gamepad1.right_stick_y;
                lx = gamepad1.right_stick_x == 0 ? 0 :
                        Math.pow(gamepad1.right_stick_x, 2) * Math.abs(gamepad1.right_stick_x) /
                                gamepad1.right_stick_x;

                xlinha = lx * Math.cos(follower.getPose().getHeading() + (Math.PI / 2))
                        - ly * Math.sin(follower.getPose().getHeading() + (Math.PI / 2));
                ylinha = lx * Math.sin(follower.getPose().getHeading() + (Math.PI / 2))
                        + ly * Math.cos(follower.getPose().getHeading() + (Math.PI / 2));
                frentedireita.setPower(-ylinha - xlinha - gamepad1.left_stick_x);
                frenteesquerda.setPower(-ylinha + xlinha + gamepad1.left_stick_x);
                trasesquerda.setPower(-ylinha - xlinha + gamepad1.left_stick_x);
                trasdireita.setPower(-ylinha + xlinha - gamepad1.left_stick_x);
            }
        }

    }
