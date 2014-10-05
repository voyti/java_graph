package generator;

public class Runner {

	public static void main(String[] args) {
		float x = 300;
		float y = 300;
		float R = 200;
		int N = 15;
		float rmin = 50;
		float rmax = 100;
		int nmin = 10;
		int nmax = 100;
		
		Generator gen = new Generator(x - R, y - R, R, N, rmin, rmax, nmin, nmax);

	}

}
