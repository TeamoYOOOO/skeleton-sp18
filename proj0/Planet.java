public class Planet {
    public double xxPos;//Its current x position
    public double yyPos;//Its current y position
    public double xxVel;//Its current velocity in the x direction
    public double yyVel;//Its current velocity in the y direction
    public double mass;//Its mass
    public String imgFileName;//The name of the file that corresponds to the image that depicts the planet
    private double G = 6.67 * Math.pow(10,-11);

    //constructor 1
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    //constructor 2
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos,2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p){
        //Java supports scientific notation. double G = 6.67e-11;.
        return (G * this.mass * p.mass) / Math.pow(this.calcDistance(p),2);
    }

    public double calcForceExertedByX(Planet p){
        //double dx = (p.xxPos > this.xxPos)? p.xxPos - this.xxPos : this.xxPos - p.xxPos;
        double dx = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        //double dy = (p.yyPos > this.yyPos)? p.yyPos - this.yyPos : this.yyPos - p.yyPos;
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }


    public double calcNetForceExertedByX(Planet[] p){
        double netForceX = 0.0;
        for(int x = 0; x < p.length; x++){
            if(!p[x].equals(this)){
                netForceX += this.calcForceExertedByX(p[x]);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double netForceY = 0.0;
        for(int x = 0; x < p.length; x++){
            if(!p[x].equals(this)){
                netForceY += this.calcForceExertedByY(p[x]);
            }
        }
        return netForceY;
    }

    public void update(double dt, double xForce, double yForce){
        double accX = xForce/this.mass;
        double accY = yForce/this.mass;
        this.xxVel += dt * accX;
        this.yyVel += dt * accY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+ this.imgFileName);
    }


























}
