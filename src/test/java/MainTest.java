import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.daniil.lessons.Main;

public class MainTest {

    int[] testArr1 = new int[]{1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2};
    int[] testArr2 = new int[]{1, 2, 2, 2, 3, 4, 5, 2, 2, 2, 2};

    @Test
    public void testCopyArrayFromIndex() {
        Assertions.assertArrayEquals(Main.copyArrayFromIndex(testArr1, 6), new int[]{2, 2, 2, 2});
    }

    @Test
    public void testCopyArrayAfterLastNumber() {
        Assertions.assertArrayEquals(Main.copyArrayAfterLastNumber(testArr1, 1), new int[]{2, 2, 2, 2});
    }

    @Test
    public void testCopyArrayAfterLastNumberExc() {
        Assertions.assertThrows(RuntimeException.class, () -> Main.copyArrayAfterLastNumber(testArr1, 3));
    }

    @Test
    public void testArrConsistOnlyOfOneAndTwo() {
        Assertions.assertTrue(Main.arrConsistOnlyOfOneAndTwo(testArr1));
        Assertions.assertFalse(Main.arrConsistOnlyOfOneAndTwo(testArr2));
    }

}
