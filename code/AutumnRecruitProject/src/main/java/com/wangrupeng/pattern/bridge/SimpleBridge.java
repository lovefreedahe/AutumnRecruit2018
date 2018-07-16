package com.wangrupeng.pattern.bridge;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import javax.swing.*;

public class SimpleBridge {
    public static void main(String[] args) {
        new LenoveComputer(new Amd()).discribe();
        new HaseeComputer(new Intel()).discribe();
    }
}

interface Cpu {
    String discribe();
}

class Amd implements Cpu {
    @Override
    public String discribe() {
        return "just so so...";
    }
}

class Intel implements Cpu {
    @Override
    public String discribe() {
        return "great !";
    }
}

abstract class AbstractComputer {
    Cpu cpu;
    public AbstractComputer(Cpu cpu) {
        this.cpu = cpu;
    }
    public abstract void discribe();
}

class LenoveComputer extends AbstractComputer {
    public LenoveComputer(Cpu cpu) {
        super(cpu);
    }

    @Override
    public void discribe() {
        System.out.println("联想笔记本cpu:" + super.cpu.discribe());
    }
}

class HaseeComputer extends AbstractComputer {
    public HaseeComputer(Cpu cpu) {
        super(cpu);
    }

    @Override
    public void discribe() {
        System.out.println("神舟笔记本cpu:" + super.cpu.discribe());
    }
}
