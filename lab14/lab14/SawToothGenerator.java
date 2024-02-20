package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        this.state = 0;
    }    

    @Override
    public double next() {
        double sample = normalize(state % period);
        state += 1;
        return sample;
    }
    
    private double normalize(int x) {
        if (x % period == 0) {
            return -1.0;
        }
        return 2.0 * x / (period - 1) - 1.0;
    }
}
