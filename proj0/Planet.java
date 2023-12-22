public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public static final double GRAVITY = 6.67e-11;

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
        double distanceSquare = xxDistance * xxDistance + yyDistance * yyDistance;
        return Math.sqrt(distanceSquare);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        double forceExerted = (this.mass * p.mass) * GRAVITY / (distance * distance);
        return forceExerted;
    }

    public double calcForceExertedByX(Planet p) {
        double xxDistance = p.xxPos - this.xxPos; 
        double distance = calcDistance(p);
        double forceExerted = calcForceExertedBy(p);
        double forceExertedByX = forceExerted * xxDistance / distance;
        return forceExertedByX;
    }

    public double calcForceExertedByY(Planet p) {
        double yyDistance = p.yyPos - this.xxPos;
        double distance = calcDistance(p);
        double forceExerted = calcForceExertedBy(p);
        double forceExertedByY = forceExerted * yyDistance / distance;
        return forceExertedByY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceExertedByX = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            netForceExertedByX += calcForceExertedByX(p);
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceExertedByY = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            netForceExertedByY += calcForceExertedByY(p);
        }
        return netForceExertedByY;
    }
    
    public void update(double dt, double netForceX, double netForceY) {
        double accelerationX = netForceX / this.mass;
        double accelerationY = netForceY / this.mass;
        this.xxVel += (accelerationX * dt);
        this.yyVel += (accelerationY * dt);
        this.xxPos += (xxVel * dt);
        this.yyPos += (yyVel * dt);
    }

    public void draw() {
        String filename = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, filename);
    }
}