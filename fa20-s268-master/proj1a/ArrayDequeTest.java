public class ArrayDequeTest {
        /* Utility method for printing out empty checks. */
        public static boolean checkEmpty(boolean expected, boolean actual) {
            if (expected != actual) {
                System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
                return false;
            }
            return true;
        }

        /* Utility method for printing out empty checks. */
        public static boolean checkSize(int expected, int actual) {
            if (expected != actual) {
                System.out.println("size() returned " + actual + ", but expected: " + expected);
                return false;
            }
            return true;
        }

        /* Prints a nice message based on whether a test passed.
         * The \n means newline. */
        public static void printTestStatus(boolean passed) {
            if (passed) {
                System.out.println("Test passed!\n");
            } else {
                System.out.println("Test failed!\n");
            }
        }

        /** Adds a few things to the list, checking isEmpty() and size() are correct,
         * finally printing the results.
         *
         * && is the "and" operation. */
        public static void addIsEmptySizeTest() {
            System.out.println("Running add/isEmpty/Size test.");

            ArrayDeque<String> lld1 = new ArrayDeque<String>();

            boolean passed = checkEmpty(true, lld1.isEmpty());

            lld1.addFirst("front");

            // The && operator is the same as "and" in Python.
            // It's a binary operator that returns true if both arguments true, and false otherwise.
            passed = checkSize(1, lld1.size()) && passed;
            passed = checkEmpty(false, lld1.isEmpty()) && passed;

            lld1.addLast("middle");
            passed = checkSize(2, lld1.size()) && passed;

            lld1.addLast("back");
            passed = checkSize(3, lld1.size()) && passed;

            System.out.println("Printing out deque: ");
            lld1.printDeque();

            printTestStatus(passed);

        }

        /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
        public static void addRemoveTest() {

            System.out.println("Running add/remove test.");


            ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
            // should be empty
            boolean passed = checkEmpty(true, lld1.isEmpty());

            lld1.addFirst(10);
            // should not be empty
            passed = checkEmpty(false, lld1.isEmpty()) && passed;

            lld1.removeFirst();
            // should be empty
            passed = checkEmpty(true, lld1.isEmpty()) && passed;

            printTestStatus(passed);
        }
        public static void addSelfBasicTest() {
            System.out.println("running my test");
            ArrayDeque<Integer> list = new ArrayDeque<>();
            for (int i = 0; i < 6; i++) {
                list.addLast(i);
                list.addFirst(i);
            }
            for (int i = 0; i < 0; i++) {
                System.out.print(list.removeFirst());
            }

            list.printDeque();
        }
        public static void addGetTest() {
            System.out.println("running my test");
            ArrayDeque<Integer> list = new ArrayDeque<>();
            list.addFirst(1);
            list.addFirst(5);
            list.addFirst(10);
            System.out.println(list.get(0));
        }
        public static void autograderGet() {
            ArrayDeque<Integer> list = new ArrayDeque<>();
            list.addFirst(0);
            list.addFirst(1);
            list.addLast(2);
            list.addLast(3);
            list.removeFirst();
            list.addFirst(5);
            list.removeFirst();
            list.addLast(7);
            list.addLast(8);
            list.addLast(9);
            list.addFirst(10);
            list.addLast(11);
            list.addFirst(12);
            System.out.println(list.get(5));
            list.addFirst(14);
            list.addFirst(15);
            System.out.println(list.get(0));
            System.out.println(list.get(0));
            list.addLast(18);
            list.removeFirst();
            list.addLast(20);
            list.addLast(21);
            System.out.println(list.get(12));
        }

        public static void autograderGet2() {
            ArrayDeque<Integer> list = new ArrayDeque<>();
            list.addFirst(0);
            list.removeFirst();
            list.addLast(2);
            list.addLast(3);
            list.addLast(4);
            list.addLast(5);
            list.get(1) ;
            list.addLast(7);
            list.removeLast() ;
            list.addLast(9);
            list.removeFirst();
            list.removeLast() ;
            list.addLast(12);
            list.addFirst(13);
            list.removeFirst();
            list.get(3) ;
            list.addFirst(16);
            list.removeFirst();
            list.get(2) ;
            list.get(2) ;
            System.out.println(list.removeFirst());
            System.out.println(list.get(0));

        }
        public static void autograderGet3() {
            ArrayDeque<Integer> list = new ArrayDeque<>();
            list.addFirst(0);
            list.addLast(1);
            list.removeLast() ;
            list.removeFirst();
            list.addFirst(4);
            list.addFirst(5);
            list.addFirst(6);
            list.removeFirst();
            list.addLast(8);
            list.addFirst(9);
            list.printDeque();
            list.removeLast() ;
            list.addFirst(11);
            list.addLast(12);
            list.addLast(13);
            list.addLast(14);
            list.addLast(15);
            list.removeFirst();
            list.get(4) ;
            list.removeLast() ;
            list.removeLast() ;
            list.removeLast() ;
            list.get(0) ;

            list.printDeque();
            list.removeLast() ;
            System.out.println(list.removeLast());
        }

        public static void main(String[] args) {
            System.out.println("Running tests.\n");
            //addIsEmptySizeTest();
            //addRemoveTest();
            //addSelfBasicTest();
            //addGetTest();
            //autograderGet();
            //autograderGet2();
            autograderGet3();
        }
}
