public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Body(double xP, double yP, double xV, double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  public Body(Body b) {
    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b) {
    double xDis = this.xxPos - b.xxPos;
    double yDis = this.yyPos - b.yyPos;
    return Math.sqrt(xDis*xDis + yDis*yDis);
  }

  public double calcForceExertedBy(Body b) {
    if (this.equals(b)) {
      return 0;
    }
    double dist = this.calcDistance(b);
    return (6.67e-11 * this.mass * b.mass / (dist * dist));
  }

  public double calcForceExertedByX(Body b) {
    if (this.xxPos == b.xxPos) {
      return 0;
    }
    return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
  }

  public double calcForceExertedByY(Body b) {
    if (this.yyPos == b.yyPos) {
      return 0;
    }
    return calcForceExertedBy(b) * Math.cos(Math.acos(-1 * (this.yyPos - b.yyPos) / this.calcDistance(b)));
  }

  public double calcNetForceExertedByX(Body[] bodies) {
    double net = 0;
    for (int i = 0; i < bodies.length; i++) {
      if (this.equals(bodies[i])) {
        continue;
      }
      net += calcForceExertedByX(bodies[i]);
    }
    return net;
  }

  public double calcNetForceExertedByY(Body[] bodies) {
    double net = 0;
    for (int i = 0; i < bodies.length; i++) {
      if (this.equals(bodies[i])) {
        continue;
      }
      net += calcForceExertedByY(bodies[i]);
    }
    return net;
  }

  public void update (double dt, double fx, double fy) {
    double ax = fx/this.mass;
    double ay = fy/this.mass;
    this.xxVel = this.xxVel + ax * dt;
    this.yyVel = this.yyVel + ay * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

}
