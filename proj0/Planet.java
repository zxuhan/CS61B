public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double GravitationalNumber = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xxDistance = p.xxPos - this.xxPos;
        double yyDistance = p.yyPos - this.yyPos;
        double Distance = Math.sqrt(xxDistance * xxDistance + yyDistance * yyDistance);
        return Distance;
    }
        
    public double calcForceExertedBy(Planet p) {
        double ForceExerted = GravitationalNumber * (this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
        return ForceExerted;
    }

    
    public double calcForceExertedByX(Planet p) {
        double ForceExertedX = this.calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / this.calcDistance(p));
        return ForceExertedX;
    }

    public double calcForceExertedByY(Planet p) {
        double ForceExertedY = this.calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / this.calcDistance(p));
        return ForceExertedY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double NetForcedExertedByX = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            NetForcedExertedByX += this.calcForceExertedByX(allPlanets[i]);
        }
        return NetForcedExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double NetForcedExertedByY = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            NetForcedExertedByY += this.calcForceExertedByY(allPlanets[i]);
        }
        return NetForcedExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;
        double xxNewVel = this.xxVel + dt * aNetX;
        double yyNewVel = this.yyVel + dt * aNetY;
        double xxNewPos = this.xxPos + dt * xxNewVel;
        double yyNewPos = this.yyPos + dt * yyNewVel;
        this.xxVel = xxNewVel;
        this.yyVel = yyNewVel;
        this.xxPos = xxNewPos;
        this.yyPos = yyNewPos;
    }

    public void draw() {
        String filename = "/Users/guangtumuxixirihan/CS/cs61b/proj0/images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, filename);
    }


}
    


