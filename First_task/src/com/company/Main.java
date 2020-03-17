package com.company;

public class Main {

    public static void main(String[] args) {
        Processor processor = new Processor(8800);
        HardDisk hardDisk = new HardDisk(2048);
        Drive drive = new Drive(false);
        RAM ram = new RAM(32);

        Computer computer = new Computer(
                processor,
                drive,
                hardDisk,
                ram
        );

        computer.turnOnOff();

        if (computer.checkViruses()) {
            System.out.println("Computer has viruses");
        } else {
            System.out.println("Computer does not has viruses");
        }

        System.out.println("Hard disk capacity is " + computer.getHardDiskCapacity() + " GB");

        computer.turnOnOff();
    }
}
