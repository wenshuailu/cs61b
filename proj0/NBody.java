public class NBody {
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);

        /* Clears the drawing window. */
        StdDraw.clear();

        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        int len = planets.length;
        double[] xForces = new double[len];
        double[] yForces = new double[len];

        for (int t = 0; t < T; t+=dt) {
            for (int i = 0; i < len; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < len; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numPlanet];

        for(int i = 0; i < numPlanet; i++) {
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String dir = in.readString();
            planets[i] = new Planet(xpos, ypos, xv, yv, mass, dir);
        }

        return planets;
    }
}
