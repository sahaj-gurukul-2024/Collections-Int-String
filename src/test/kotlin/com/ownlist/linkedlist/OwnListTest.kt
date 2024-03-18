import com.ownlist.linkedlist.EmptyIntegerList
import com.ownlist.linkedlist.MyIntegerList
import com.ownlist.linkedlist.NonEmptyIntegerList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IntegerListTest {

    private lateinit var integers: MyIntegerList

    @BeforeEach
    fun initIntegers() {
        integers = NonEmptyIntegerList(1, EmptyIntegerList)
    }

    @Test
    fun `test append`() {


        val expected = NonEmptyIntegerList(1, NonEmptyIntegerList(2, EmptyIntegerList))
        assertEquals(
            expected, integers.append(2)
        )
    }

    @Test
    fun `test square and cube`() {
        integers = integers.append(2) as NonEmptyIntegerList
        val expected = NonEmptyIntegerList(1, NonEmptyIntegerList(4, EmptyIntegerList))
        assertEquals(
            expected, integers.getSquareValues()
        )
    }

    @Test
    fun `test increment`() {
        val expected = NonEmptyIntegerList(11, EmptyIntegerList)
        assertEquals(
            expected, integers.incrementBy(10)
        )
    }

    @Test
    fun `test odd values`() {
        integers = integers.append(2)
        integers = integers.append(3)

        val oddValues = integers.getOddValues()

        val expected = NonEmptyIntegerList(1, NonEmptyIntegerList(3, EmptyIntegerList))
        assertEquals(
            expected, oddValues
        )
    }

    @Test
    fun `test even values`() {
        integers = integers.append(2)
        integers = integers.append(3)

        val oddValues = integers.getEvenValues()

        val expected = NonEmptyIntegerList(2, EmptyIntegerList)
        assertEquals(
            expected, oddValues
        )
    }

    @Test
    fun `test sum`() {
        integers = integers.append(2)
        assertEquals(
            3,
            integers.getSum()
        )
    }


    @Test
    fun `test min`() {
        integers = integers.append(10)
        assertEquals(
            1,
            integers.getMin()
        )
    }


    @Test
    fun `test max`() {
        integers = integers.append(10)
        integers = integers.append(20)
        assertEquals(
            20,
            integers.getMax()
        )
    }

}