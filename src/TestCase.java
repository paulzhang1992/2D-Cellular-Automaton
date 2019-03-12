public class TestCase {
    public TestCase() {
    }

    public static void main(String[] args) {
        TestCase tc = new TestCase();
        SqTen st = SqTen.getInstance();
        st.generator(1);
        st.showGrid();
    }
}
