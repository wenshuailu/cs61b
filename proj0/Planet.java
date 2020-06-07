public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;

        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet b) {
        double dist = calcDistance(b);

        return G * this.mass * b.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double f = calcForceExertedBy(b);
        return f * dx / calcDistance(b);
    }

    public double calcForceExertedByY(Planet b) {
        double dy = b.yyPos - this.yyPos;
        double f = calcForceExertedBy(b);
        return f * dy / calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet [] planets) {
        double netX = 0;
        for (Planet p: planets) {
            if (!this.equals(p)) {
                netX += calcForceExertedByX(p);
            }
        }

        return netX;
    }

    public double calcNetForceExertedByY(Planet [] planets) {
        double netY = 0;

        for (Planet p: planets) {
            if (!this.equals(p)) {
                netY += calcForceExertedByY(p);
            }
        }

        return netY;
    }

    public void update(double dt, double fX, double fY) {
        xxVel += dt * fX / mass;
        yyVel += dt * fY / mass;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        String imageToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imageToDraw);
    }

}

