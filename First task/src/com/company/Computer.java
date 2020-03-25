package com.company;

public class Computer implements Hardwarable {
    private Processor processor;

    private Drive drive;

    private HardDisk hardDisk;

    private RAM ram;

    private boolean working;

    public Computer(Processor processor, Drive drive, HardDisk hardDisk, RAM ram) {
        this.processor = processor;
        this.drive = drive;
        this.hardDisk = hardDisk;
        this.ram = ram;
    }

    public Computer(Processor processor, Drive drive, HardDisk hardDisk, RAM ram, boolean working) {
        this.processor = processor;
        this.drive = drive;
        this.hardDisk = hardDisk;
        this.ram = ram;
        this.working = working;
    }

    public boolean isWorking() {
        return working;
    }

    @Override
    public void turnOnOff() {
        processor.turnOnOff();
        drive.turnOnOff();
        hardDisk.turnOnOff();
        ram.turnOnOff();
        working = !working;
    }

    boolean checkViruses() {
        return hardDisk.isThereViruses();
    }

    int getHardDiskCapacity() {
        return hardDisk.getCapacityGB();
    }
}
