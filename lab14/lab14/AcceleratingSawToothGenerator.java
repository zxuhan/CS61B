package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private double factor;
    private int period;
    private int count;
    private int state;
    private SawToothGenerator stg;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        count = 0;
        state = 0;
        stg = new SawToothGenerator(period);
    }
    
    private void acceleratingPeriod() {
        period = (int) Math.round(period * factor);
        stg = new SawToothGenerator(period);
    }

    @Override
    public double next() {
        double sample = stg.next();
        state += 1;
        if (state == count + period) {
            count += period;
            acceleratingPeriod();
        }
        return sample;
    }
   
}