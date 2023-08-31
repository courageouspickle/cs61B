public class NBody {
  public static double readRadius(String fileName){
    In in = new In(fileName);
    in.readLine();
    return in.readDouble();
  }

  public static Body[] readBodies(String fileName) {
    In in = new In(fileName);
    Body[] bodies = new Body[in.readInt()];
    in.readDouble();
    for (int i = 0; i < bodies.length; i++) {
      bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
    }
    return bodies;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = NBody.readRadius(filename);
    Body[] bodies = NBody.readBodies(filename);
    StdDraw.enableDoubleBuffering();

    StdDraw.setScale(-1 * radius, radius);

    for(double time = 0; time < T; time += dt) {
      double[] xForces = new double[bodies.length];
      double[] yForces = new double[bodies.length];
      for (int i = 0; i < bodies.length; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }
      for (int i = 0; i < bodies.length; i++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
      for (Body b : bodies) {
        b.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);

    }
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }

  }
}
