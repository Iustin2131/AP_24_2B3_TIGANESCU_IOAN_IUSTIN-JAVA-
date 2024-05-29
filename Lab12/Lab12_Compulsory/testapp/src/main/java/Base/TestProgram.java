package Base;


public class TestProgram {
    @Test
    public static void tes1() {
        System.out.println("test1 passed");
    }

    @Test
    public static void test2() {
        throw new RuntimeException("Boom");
    }

    @Test
    public static void test3() {
        int result = 2 + 3;
        assert result == 5 : "Test3 failed";
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

}
