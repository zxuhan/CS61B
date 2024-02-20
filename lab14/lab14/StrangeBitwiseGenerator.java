package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator{
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        this.state = 0;
    }    

    @Override
    public double next() {
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        double sample = normalize(weirdState);
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
