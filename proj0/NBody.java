public class NBody{

	public static double readRadius(String filename){
		In in = new In(filename);
		int first = in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		Planet[] p = new Planet[5];
		in.readInt();
		in.readDouble();
		for(int x = 0; x < 5; x++){
			p[x] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return p;
	}

	public static void main(String[] args){
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		Planet[] p = readPlanets(filename);
		double radius = readRadius(filename);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale((-1)*radius, radius);

		double timeV = 0.0;
		while(timeV < T){
			double[] xFroces = new double[p.length];
			double[] yFroces = new double[p.length];
			for(int x = 0; x < p.length; x++){
				xFroces[x] = p[x].calcNetForceExertedByX(p);
				yFroces[x] = p[x].calcNetForceExertedByY(p);
			}
			for(int x = 0; x < p.length; x++){
				p[x].update(dt, xFroces[x], yFroces[x]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int x = 0; x < p.length; x++){
				p[x].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			timeV += dt;
		}

		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < p.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p[i].xxPos, p[i].yyPos, p[i].xxVel, p[i].yyVel, p[i].mass, p[i].imgFileName);   
}
	}








}