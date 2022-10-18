import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Microondas {
	private int min;
	private int sec;
	private boolean pausado; // true = pausado false = funcionando
	private boolean fechado; // false = aberto true = fechado

	public Microondas() {
		this.min = 0;
		this.sec = 0;
		this.pausado = true;
		this.fechado = true;
	}

	public int setTempo(int tempo) {
		if (tempo < 0)
			return 0;
		this.min = tempo / 100;
		this.sec = tempo % 100;
		return 1;
	}

	public boolean getPausado() {
		return this.pausado;
	}

	public boolean getFechado() {
		return this.fechado;
	}

	public void abrir() {
		this.fechado = false;
		this.pausado = true;

	}

	public void ligar() {
		if (this.fechado == false)
			return;
		if (min != 0 || sec != 0) {
			this.pausado = false;
		} else {
			this.add30();
			this.pausado = false;
		}

	}

	public void add30() {
		this.sec += 30;
		if (this.sec > 60) {
			this.min++;
			this.sec = this.sec % 60;
		}
	}

	public void pausar() {
		if (this.pausado == true) {
			this.reset();
		}
		this.pausado = true;
	}

	private void reset() {
		this.min = 0;
		this.sec = 0;
	}

	public boolean getReiniciado() {
		return (this.min + this.sec) == 0;
	}

	public int getMinutos() {
		return this.min;
	}

	public int getSegundos() {
		return this.sec;
	}

	public static void main(String[] args) {
		Microondas m = new Microondas();
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o tempo:");
		int t = sc.nextInt();
		m.setTempo(t);
		m.ligar();
		sc.close();
		Duration deltaTime = Duration.ZERO;
		Instant beginTime = Instant.now();
		while (true) {
			deltaTime = Duration.between(beginTime, Instant.now());
			if (deltaTime.getSeconds() > 1) {
				m.menos();
				beginTime = Instant.now();
			}
		}
	}

	private void menos() {
		if (this.getPausado())
			return;
		this.sec--;
		if (this.sec == 0) {
			if (this.min > 0) {
				this.min--;
				this.sec = 60;
			} else {
				this.pausar();
			}
		}
		System.out.printf("%d:%d\n", this.min, this.sec);
		if (this.getPausado()) {
			System.out.println("Microondas Pausado.");
		}
	}

}
