public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int firstItem = in.readInt();
        double secondItem = in.readDouble();
        return secondItem;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNum = in.readInt();
        double secondItem = in.readDouble();

        Planet[] allPlanets = new Planet[planetNum];
        for (int i = 0; i < planetNum; i += 1) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            allPlanets[i] = p;
        }

        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();    
        StdDraw.enableDoubleBuffering();

        double time = 0.0;
        while (time <= T) {
            double[] xForce = new double[allPlanets.length];
            double[] yForce = new double[allPlanets.length];

            for (int i = 0; i < allPlanets.length; i += 1) {
                xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
               
            }

            for (int i = 0; i < allPlanets.length; i += 1) {
                allPlanets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : allPlanets) {
                p.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;

        }
    }
}
