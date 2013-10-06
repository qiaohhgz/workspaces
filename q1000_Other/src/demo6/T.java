package demo6;

public class T {
	public static void main(String[] args) {
		boolean bl = false;
		boolean b = bl ? ((new A() {
			public boolean s() {
				System.out.println("run method");
				return true;
			}
		}).s()) : ((new A() {
			public boolean s() {
				System.out.println("run method A");
				return (new C() {
					public boolean s() {
						System.out.println("run method C");
						return (new D() {
							public boolean s() {
								System.out.println("run method D");
								return (new E() {
									public boolean s() {
										System.out.println("run method E");
										return (new F() {
											public boolean s() {
												System.out.println("run method F");
												return (new I() {
													public boolean s() {
														System.out.println("run method I");
														return (false);
													}
												}.s());
											}
										}.s());
									}
								}.s());
							}
						}.s());
					}
				}.s());
			}
		}).s());
		System.out.println(b);
	}

	public interface A {
		public boolean s();
	}

	public interface B {
		public boolean s();
	}

	public interface C {
		public boolean s();
	}

	public interface D {
		public boolean s();
	}

	public interface E {
		public boolean s();
	}

	public interface F {
		public boolean s();
	}

	public interface G {
		public boolean s();
	}

	public interface H {
		public boolean s();
	}

	public interface I {
		public boolean s();
	}

	public interface J {
		public boolean s();
	}
}
