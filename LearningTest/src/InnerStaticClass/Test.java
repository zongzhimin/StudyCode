package InnerStaticClass;

public class Test {
	
	private String str1 = "";
	
	private static String str2 = "";
	
	private static StaticInnerClass sic = new StaticInnerClass();
	
	//private static NormalInnerClass nis = new NormalInnerClass();//ERROR NormalInnerClass不是static class
	
	public static void staticMethod(){
		StaticInnerClass sic = new StaticInnerClass();
		//NormalInnerClass nic = new NormalInnerClass();//Error 必须是静态的class
	}
	
	public  void normalMethod(){
		StaticInnerClass sic = new StaticInnerClass();
		NormalInnerClass nic = new NormalInnerClass();//Error 必须是静态的class
	}
	
	public static void main(String[] args) {
		
	}
	static class StaticInnerClass{
		//String s1 = str1;//ERROR static class无法获取到str1(非静态)
		String s2 = str2;
		private static String staticName = "Zzm";
		private String name;
		public void setName(String name) {
			this.name = name;
		}
		public void sayHello(){
			System.out.println(name+"你好！");
		}
		public static void staticSayHello(){
			System.out.println(staticName+"你好！");
		}
		
	}
	class NormalInnerClass{
		//private static String staticName = "zzm";//Error 必须final
		private final static String staticName = "zzm";
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void sayHello1(){
			System.out.println("name"+"你好!");
		}
//		public static void staticSayHello(){//Error 无法创建static方法
//			System.out.println();
//		}
	}
}
