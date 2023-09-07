public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        Planet[] allPlanets = new Planet[firstItemInFile];
        double secondItemInFile = in.readDouble();
        for (int i = 0; i < firstItemInFile; i++) {
            allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
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
        StdDraw.picture(0.0000e+00, 0.0000e+00, "/Users/guangtumuxixirihan/CS/cs61b/proj0/images/starfield.jpg");
        for (int i = 0; i < allPlanets.length; i++) {
            allPlanets[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0.0;
        while (time != T){
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            

            StdDraw.picture(0.0000e+00, 0.0000e+00, "/Users/guangtumuxixirihan/CS/cs61b/proj0/images/starfield.jpg");
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        
        System.out.println(allPlanets.length);
        System.out.println(readRadius(filename));
        for (Planet p : allPlanets) {
            System.out.print(
                    p.xxPos + " " + p.yyPos + " " + p.xxVel + " " + p.yyVel + " " + p.mass + " " + p.imgFileName);
            System.out.println();
        }
        
        
    }
}
