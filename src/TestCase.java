public class TestCase {
    public TestCase() {
    }

    public static void main(String[] args) {
        TestCase tc = new TestCase();
        SqTen st = SqTen.getInstance();
        st.generator(0);
        st.cellAlter(0,6,1);
        //st.showGrid();
        System.out.printf("\n\n\n\n");

        ConnectPattern cp = new ConnectPattern();
        cp.entry[0] = 0;
        cp.entry[1] = 0;
        for (int i = 0; i < 1000000; i++) {
            cp.pattern(cp.getAdjacentStatus());
        }
        st.showGrid();
    }
}
