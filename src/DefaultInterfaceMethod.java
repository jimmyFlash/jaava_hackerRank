public class DefaultInterfaceMethod {

    public interface I1
    {
        default String getGreeting() {
            return "Good Morning!";
        }
    }

    public interface I2
    {
        default String getGreeting() {
            return "Good Afternoon!";
        }
    }

    static class C1 implements I1, I2{


        @Override
        public String getGreeting() {
            return I1.super.getGreeting() + ", " + I2.super.getGreeting();
        }
    }

    public static void main(String[] args) {

        C1 c = new C1();
        System.out.println(c.getGreeting());
    }
}
